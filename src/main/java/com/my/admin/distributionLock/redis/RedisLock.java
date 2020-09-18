package com.my.admin.distributionLock.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCommands;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class RedisLock {

    @Autowired
    private JedisCommands jedisCommands;

    public boolean getLock(String key) {
        return getLock(key, 5);
    }

    /**
     * 拿锁
     * @param key 锁键值
     * @param expire 锁超时时间
     * @return 返回true是因为正确设置值，代表拿到锁，false则表示没有拿到锁
     */
    public boolean getLock(String key, int expire) {
        String lock = "KV_LOCK_" + key;
        //操作原子性保证分布式锁事务，如果设置时间和拿锁分开执行，可能导致在拿锁时，程序挂掉，这是锁就被一直占用了
        //第一个参数表示键值
        //第二个参数表示值
        //第三个参数有两种值：NX意思是只有当key不存在是才可以设置，XX代表key存在是才可以设置
        //第四个参数代表数据过期时间的单位，有两种值：EX代表时间单位为秒，PX代表时间单位为毫秒
        //第五个参数代表时长，单位是第四个参数设置的
        String result = jedisCommands.set(lock, "", "nx", "ex", expire);
        return result != null;
    }

    public void releaseLock(String key) {
        String lock = "KV_LOCK_" + key;
        jedisCommands.del(lock);
    }

    //可重入锁
    private ThreadLocal<Map> lockers = new ThreadLocal<>();

    private Map<String, Integer> currentLockers() {
        Map<String, Integer> refs = lockers.get();
        if (null != refs) {
            return refs;
        } else {
            lockers.set(new HashMap());
        }
        return lockers.get();
    }

    public boolean reentrantLock(String key) {
        Map<String, Integer> refs = currentLockers();
        Integer refCount = refs.get(key);
        if (null != refCount) {
            refs.put(key, refCount + 1);
            return true;
        }
        boolean ok = this.getLock(key, 24*60*60);
        if (!ok) {
            return false;
        } else {
            refs.put(key, 1);
            return true;
        }
    }

    public boolean reentrantUnlock(String key) {
        Map<String, Integer> refs = currentLockers();
        Integer refCount = refs.get(key);
        if (null == refCount) {
            return false;
        }
        if (refCount > 1) {
            refs.put(key, refCount - 1);
        } else {
            refs.remove(key);
            this.releaseLock(key);
        }
        return true;
    }

    public static void main(String[] args) {
        //第三方库结果
        //可重入锁的含义是：同一线程可以重复进入，而不同线程则不能
        ReentrantLock reentrantLock = new ReentrantLock();
        new Thread(() -> {
            boolean ok = reentrantLock.tryLock();
            boolean ok1 = reentrantLock.tryLock();
            System.out.println("1" + ok);
            System.out.println("2" + ok1);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
            reentrantLock.unlock();
            System.out.println("解锁");
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 2; i ++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean ok = reentrantLock.tryLock();
                boolean ok1 = reentrantLock.tryLock();
                System.out.println("3" + ok);
                System.out.println("4" + ok1);
            }
        }).start();
    }

}

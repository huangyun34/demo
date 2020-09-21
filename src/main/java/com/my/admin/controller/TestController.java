package com.my.admin.controller;

import com.github.AopLog;
import com.my.admin.distributionLock.redis.RedisLock;
import com.my.admin.model.Account;
import com.my.admin.model.User;
import com.my.admin.service.AccountService;
import io.swagger.annotations.*;
import org.redisson.Redisson;
import org.redisson.core.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test")
@Api(value = "tttttttttest")
@AopLog(type = "test", stackTraceOnErr = true)
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RedisLock redisLock;

    @Autowired
    private Redisson redisson;

    @ApiResponse(code = 1, message = "{hello:hello}")
    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public String connect(HttpServletRequest request){
        accountService.a();
        return "hhhh";
    }

    @RequestMapping(value = "/reentrantLock", method = RequestMethod.GET)
    public void reentrantLock(){
        new Thread(() -> {
            boolean ok = redisLock.reentrantLock("a");
            boolean ok1 = redisLock.reentrantLock("a");
            System.out.println("1" + ok);
            System.out.println("2" + ok1);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            redisLock.reentrantUnlock("a");
            redisLock.reentrantUnlock("a");
            System.out.println("解锁");

        }).start();

        new Thread(() -> {
            for (int i = 0; i < 2; i ++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean ok = redisLock.reentrantLock("a");
                boolean ok1 = redisLock.reentrantLock("a");
                System.out.println("3" + ok);
                System.out.println("4" + ok1);

            }
        }).start();
    }

    @RequestMapping(value = "/connect/redis", method = RequestMethod.GET)
    public String connectRedis(){
        redisTemplate.opsForValue().set("key", "123");
        return (String)redisTemplate.opsForValue().get("key");
    }

    @RequestMapping(value = "/connect/param", method = RequestMethod.GET)
    public String hello1(@RequestParam(value = "name")String name){
        return name;
    }

    @ApiOperation(value = "测试apimodel", notes = "有问题")
//    @ApiImplicitParams(value = {
//            @ApiImplicitParam(name = "user", value = "user"
//                    dataType = "user", required = true, paramType = "body"
//            )
//    })
    @RequestMapping(value = "/connect/address", method = RequestMethod.POST, consumes = "application/json")
    public Account address(@RequestBody @ApiParam(name = " 名字", value = "zhi",required = true) User user){
        Account ds = new Account();
        ds.setAddress("fskjdhkajhda");
        return ds;
    }

    @RequestMapping(value = "/test/redisson", method = RequestMethod.GET)
    public String connect(){
        RLock lock = redisson.getLock("hello");
        lock.lock(5, TimeUnit.SECONDS);
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {

        } finally {

        }
//        new Thread(() -> {
//            RLock lock = redisson.getLock("hello");
//            lock.lock(30, TimeUnit.SECONDS);
//            try {
//                System.out.println(11);
//                Thread.sleep(10000);
//                System.out.println(13);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                if (lock.isLocked()) {
//                    lock.unlock();
//                }
//            }
//        }).start();
//        new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            RLock lock = redisson.getLock("hello");
//            System.out.println("2" + lock.isLocked());
//            System.out.println("2" + lock.getHoldCount());
//            lock.lock(5, TimeUnit.SECONDS);
//            System.out.println("2" + lock.getHoldCount());
//            try {
//                System.out.println(22);
//            } finally {
//                if (lock.isLocked()) {
//                    lock.unlock();
//                }
//            }
//        }).start();

        return "hhhh";
    }
}

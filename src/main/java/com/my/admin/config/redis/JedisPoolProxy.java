package com.my.admin.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JedisPoolProxy implements InvocationHandler {

    private static final Logger LOG = LoggerFactory.getLogger(JedisPoolProxy.class);

    private JedisPool jedisPool;

    public JedisPoolProxy(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try (Jedis jedis = jedisPool.getResource()) {
            Object result = method.invoke(jedis, args);
            return result;
        }
    }
}

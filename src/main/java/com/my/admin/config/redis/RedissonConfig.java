package com.my.admin.config.redis;

import org.redisson.Config;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    Redisson redisson(@Autowired RedisProperties redisProperties) {
        Config config = new Config();
        config.useSingleServer().setAddress(redisProperties.getHost() + ":" + redisProperties.getPort());
//        config.useSingleServer().setPassword(redisProperties.getPassword());
        return (Redisson) Redisson.create(config);
    }
}

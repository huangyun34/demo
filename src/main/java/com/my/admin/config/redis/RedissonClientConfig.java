package com.my.admin.config.redis;

import com.google.common.base.Strings;
import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.redisson.SingleServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

//@ConfigurationProperties(prefix = "spring.redisson")
//@Configuration
public class RedissonClientConfig {
    private Logger logger = LoggerFactory.getLogger(RedissonClientConfig.class);

    @Bean
    public RedissonClient redisson(@Autowired RedisProperties redisProperties) {
        Config config = new Config();
//        config.setUseLinuxNativeEpoll(true);
        String address = String.format("redis://%s:%s", redisProperties.getHost(), redisProperties.getPort());
        logger.info("redisson address: " + address);

        SingleServerConfig singleServerConfig = config.useSingleServer()
                .setAddress(address);
//        if (redisProperties.getSsl() != null && redisProperties.getSsl()) {
//            singleServerConfig.setSslEnableEndpointIdentification(redisProperties.getSsl());
//            singleServerConfig.setPassword(redisProperties.getPassword());
//        }
        if (!Strings.isNullOrEmpty(redisProperties.getPassword())) {
            singleServerConfig.setPassword(redisProperties.getPassword());
        }
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}

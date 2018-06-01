package com.my.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.redisson.Redisson;
import org.redisson.core.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = AdminApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private Redisson redisson;

    @Test
    public void test() throws Exception {
        while (true) {
            RLock lock = redisson.getLock("chi");
            lock.tryLock(5, TimeUnit.SECONDS);
            try {
                LOGGER.info("==========");
                Thread.sleep(6000);
                LOGGER.info("----------");
            }finally {
                if (lock.isLocked()) {
                    lock.unlock();
                }
            }
        }

    }
}

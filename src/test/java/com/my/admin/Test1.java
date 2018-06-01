package com.my.admin;

import com.my.admin.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class Test1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Test1.class);

    @Autowired
    private Account account;

    @Test
    public void configurationPropertiesTest(){
        LOGGER.debug("dhshkjahdkjahdkahkj");
        System.out.println(account.getAddress());
    }
}

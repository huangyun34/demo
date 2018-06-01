package com.my.admin;

import com.my.admin.service.OBA;
import com.my.admin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class AutowiredTest {
//    @Autowired
//    private UserService userService;
//
//    @Autowired(required = false)
//    private OrderByAutowired orderByAutowired;

    @Autowired
    private OBA oba;

//    @Autowired
//    private String s;//字段声明
//
//    @Autowired
//    private void method(UserService userService){
//        System.out.println(userService);
//    }



    @Test
    public void test(){
        oba.h(null);
        System.out.println("完成");
//        method(null);
//        oba.hashCode();
//        userService.async();
    }
}

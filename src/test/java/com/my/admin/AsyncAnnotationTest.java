package com.my.admin;

import com.my.admin.service.DDService;
import com.my.admin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootTest(classes = AdminApplication.class)
@RunWith(SpringRunner.class)
public class AsyncAnnotationTest {
//    @Autowired
    private UserService userService;

    @Test
    public void testAsyncOnMethod(){
        userService.async();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束" + new Date());
    }

    @Test
    public void testNoAsyncOnMethod(){
        userService.noAsync();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束" + new Date());
    }

    @Test
    public void test(){
        DDService ddService = new DDService();
        ddService.async();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束" + new Date());
    }

    @Test
    public void testAll() throws ExecutionException, InterruptedException, TimeoutException {
        Future<String> s = userService.asyncString();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        boolean yes = s.cancel(false);
        System.out.println(yes);
        s.cancel(true);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(s.get(6, TimeUnit.SECONDS));
    }
}

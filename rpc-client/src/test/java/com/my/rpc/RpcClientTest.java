package com.my.rpc;

import com.my.rpc.remote.SendSms;
import com.my.rpc.remote.vo.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RpcClientTest {

    @Autowired
    private SendSms sendSms;

    @Test
    public void testSendSms() {
        long start = System.currentTimeMillis();
        UserInfo userInfo = new UserInfo("哈哈", "1234567890");
        boolean success = sendSms.send(userInfo);
        System.out.println("send sms:" + success);
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }
}

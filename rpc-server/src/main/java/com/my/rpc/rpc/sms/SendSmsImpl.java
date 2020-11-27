package com.my.rpc.rpc.sms;

import com.my.rpc.remote.SendSms;
import com.my.rpc.remote.vo.UserInfo;

public class SendSmsImpl implements SendSms {
    @Override
    public boolean send(UserInfo userInfo) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("已发送短信息给："+userInfo.getName()+"到【"+userInfo.getPhone()+"】");
        return true;
    }
}

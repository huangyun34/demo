package com.my.rpc.remote;

import com.my.rpc.remote.vo.UserInfo;

/**
 * 短信发送接口
 */
public interface SendSms {

    boolean send(UserInfo userInfo);
}

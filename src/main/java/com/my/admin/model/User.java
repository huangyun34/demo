package com.my.admin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "用户对象", description = "它是吃饭用的工具")
public class User implements Serializable {

    @ApiModelProperty(name = "username", value = "用户名", required = true)
    private String userName;

    @ApiModelProperty(name = "password", value = "密码", required = true)
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

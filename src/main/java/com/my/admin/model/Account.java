package com.my.admin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@ConfigurationProperties(prefix = "account")
@ApiModel(value = "account对象",description = "地址model", discriminator = "discriminator",
subTypes = {String.class}, parent = User.class)
public class Account implements Serializable {

    @ApiModelProperty(value = "具体地址", name = "address", example = "杭州市", required = true)
    private String address;

    @ApiModelProperty(value = "用户", name = "user", example = "李浩", required = true)
    private User user;

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

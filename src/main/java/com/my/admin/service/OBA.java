package com.my.admin.service;

import com.my.admin.annotation.OrderByAutowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class OBA {

    private UserService userService;

    @Autowired(required = false)
    public OBA(UserService userService) {
        Assert.notNull(userService, "userService must not be null");
        this.userService = userService;
    }

    @Autowired
    public void ht(UserService userService) {
        Assert.notNull(userService, "userService must not be null");
        this.userService = userService;
    }

    public void h(@Autowired UserService userService) {
        Assert.notNull(userService, "userService must not be null");
        this.userService = userService;
    }

}

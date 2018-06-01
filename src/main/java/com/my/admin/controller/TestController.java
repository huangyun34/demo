package com.my.admin.controller;

import com.my.admin.model.Account;
import com.my.admin.model.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
@Api(value = "tttttttttest")
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiResponse(code = 1, message = "{hello:hello}")
    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public String connect(HttpServletRequest request){
        return "hhhh";
    }

    @RequestMapping(value = "/connect/redis", method = RequestMethod.GET)
    public String connectRedis(){
        redisTemplate.opsForValue().set("key", "123");
        return (String)redisTemplate.opsForValue().get("key");
    }

    @RequestMapping(value = "/connect/param", method = RequestMethod.GET)
    public String hello1(@RequestParam(value = "name")String name){
        return name;
    }

    @ApiOperation(value = "测试apimodel", notes = "有问题")
//    @ApiImplicitParams(value = {
//            @ApiImplicitParam(name = "user", value = "地址",
//                    dataType = "User", required = true
//            )
//    })
    @RequestMapping(value = "/connect/address", method = RequestMethod.POST, consumes = "application/json")
    public Account address(@RequestBody User user){
        Account ds = new Account();
        ds.setAddress("fskjdhkajhda");
        return ds;
    }
}

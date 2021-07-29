package com.my.admin.controller;

import com.github.AopLog;
import com.my.admin.distributionLock.redis.RedisLock;
import com.my.admin.model.Account;
import com.my.admin.model.User;
import com.my.admin.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.redisson.Redisson;
import org.redisson.core.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/case1")
public class Case1Controller {

    /**
     * 模拟：抢购、秒杀场景
     * 基本信息：
     * 并发量：1000
     * 数据有：order、个人信息、各种对象等
     * 返回数据大小：1M的对象
     * 针对万级并发
     * @param request
     * @return
     */
    @GetMapping(value = "/connect")
    public String connect(HttpServletRequest request){
        List<Byte[]> list = new ArrayList<>();
        Byte[] bytes = new Byte[1024 * 1024];
        list.add(bytes);
        return "success";
    }
}

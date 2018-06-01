package com.my.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class HelloActuatorController {

    @RequestMapping(value = "/actuatorHello", method = RequestMethod.GET)
    public String hello(){
        return "Hello Spring Boot Actuator";
    }
}

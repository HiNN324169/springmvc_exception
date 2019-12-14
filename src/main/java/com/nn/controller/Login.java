package com.nn.controller;

import com.nn.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class Login {

    @Resource(name = "loginService")
    private LoginService loginService;

    @RequestMapping("login")
    public String login(){
//        loginService.login();

        // 这里会抛出 arithmeticException 异常
        System.out.println(1/0);
        return "success";
    }

//    @ExceptionHandler(ArithmeticException.class)
//    public String exception(Exception e){
//        System.out.println("=================================controller 中的 异常捕捉："+e);
//        return "error";
//    }
}

package com.nn.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Service("loginService")
public class LoginService {

    public String login(){
        System.out.println(1/0);
        return null;
    }

//    // 该方法 可以捕获类中  抛出的 arithmeticException 异常
//    @ExceptionHandler({ArithmeticException.class})
//    public ModelAndView loginException(Exception e){
//        System.out.println(e+"----------------------------service zhong d ------------------------------------------------------");
//        ModelAndView modelAndView = new ModelAndView("error");
//        modelAndView.addObject("error",e);
//        return modelAndView;
//    }
}

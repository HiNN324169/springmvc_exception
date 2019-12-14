package com.nn.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GetException {

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView runtimeException(RuntimeException runtime){
        System.out.println("runtimeException 方法异常："+runtime);

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("msg",runtime);
        return modelAndView;
    }

    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView arithmeticException(Exception ari){
        System.out.println("arithmeticException 方法异常："+ari);

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("msg",ari);
        return modelAndView;
    }

//    @ExceptionHandler(Exception.class)
//    public ModelAndView exception(Exception e){
//        System.out.println("exception 方法异常："+e);
//
//        ModelAndView modelAndView = new ModelAndView("error");
//        modelAndView.addObject("msg",e);
//        return modelAndView;
//    }
}

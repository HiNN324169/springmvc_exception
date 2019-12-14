package com.nn.controller;

import com.nn.exception.MyResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.AccountException;

@Controller
public class TestArrayException {

    /**
     *  测试 @ResponseStatus 异常注解的使用
     *  情况1、该注解 使用在类上
     * @param id
     * @return
     */
    @RequestMapping("aex")
    public String tAEx(@RequestParam("id") Integer id) throws MyResponseStatus {

        if(id == 1){
            throw new MyResponseStatus("我滴自定义异常"); // 触发 自定义 类异常
        }else{
            return "success";
        }
    }


    /**
     *  测试 @ResponseStatus 异常注解的使用
     *  情况1、该注解 使用在类上
     * @param id
     * @return
     */
    @RequestMapping("case2")
    public String case2(@RequestParam("id") Integer id) throws MyResponseStatus {

        if(id == 1){

            return "redirect:responseStatus2"; // 触发 下面的 自定义 方法异常
        }else{
            return "success";
        }
    }

    /**
     *  自定义 方法 异常
     * @return
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "自定义 方法异常")
    @RequestMapping("responseStatus2")
    public String responseStatus2(){
        return "error";
    }


    /**
     * SimpleMappingExceptionResolver 配置形式的 异常类的使用
     *
     * @return
     * @throws AccountException
     */
    @RequestMapping("simpleException")
    public String simpleException() throws AccountException {
        throw new AccountException(); // 触发异常
    }


}

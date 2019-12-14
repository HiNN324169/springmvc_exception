#  springMVC 异常处理
#### **==*顶级接口：HandlerExceptionResolver*==**
## 1、@ExceptionHandler
> **该注解由 ==ExceptionHandlerExceptionResolver== 提供** 

#### 1.1、捕获 本控制器 中的异常
> 在==Controller(handler) 控制器中== 创建一个异常捕获方法，并使用 @ExceptionHandler(指定要捕获的异常类型) 修饰，
> 该方法 会捕获 本类中出现的 指定异常

    
```
    @ExceptionHandler({ArithmeticException.class})
    public String exception(Exception e){
        System.out.println("======controller 中的 异常捕捉："+e);
        return "error";
    }
```


- 指定 多个异常

```
@ExceptionHandler({ArithmeticException.class,NumberFormatException.class})
```


- 指定 单个异常
```
@ExceptionHandler(ArithmeticException.class)
```

- 注解参数：==需要捕获的异常类.class==。
- 只有方法抛出 指定 异常时 才会调用该方法。
- ==**该注解只能使用在 handler（controller 中）**==
- ==该注解标识的方法的参数 必须在异常类型（Throwable 或 其子类），不能包含其他类型的参数==
- 多个 异常方法 调用 遵循 ==就近原则== 谁离异常近就执行那个异常方法；
    -   如：ArithmeticException、Exception 先执行 ArithmeticException 异常捕获方法

## @ExceptionHandler 默认只能 捕获 当前类 中的异常方法.
#### 如果发生异常的方法 和处理 异常的方法 不在同一个类中:通过@ControllerAdvice实现

## 使用 ==@ControllerAdvice== 实现全局异常捕获
- 定义个异常捕捉类 该类使用 @ControllerAdvice 注解修饰
- 类中异常捕捉方法 使用 @ExceptionHandler修饰 使用 ==如上 ↑== 
```
@ControllerAdvice
public class GetException {

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView runtimeException(RuntimeException runtime){
        System.out.println("runtimeException 方法异常："+runtime);

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("msg",runtime);
        return modelAndView;
    }
}
```
- 在 springmvc 配置文件中 加入以下配置
```
<!-- 注册 异常 处理类 -->
<mvc:annotation-driven></mvc:annotation-driven>
```
## 2、@ResponseStatus 自定义异常显示页面
#### 2.1、@ResponseStatus ==加到类上==
> 该注解由 ==ResponseStatusExceptionResolver== 类 提供用于 ==自定义异常显示页面==

> 注解参数：==value=====HttpStatus== 枚举类型，需要显示在页面的状态码；==reason===异常说明
> @ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "数组越界22！！！")

- 定义异常
    - 定义一个异常类（继承 Exception 类）
    - 该类使用 @ResponseStatus 注解修饰 
```
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "数组越界22！！！")
public class MyResponseStatus extends Exception { // 自定义异常 }
```
- 触发异常
```
throw new MyResponseStatus(); // 触发 自定义异常
```
- 页面响应：

> HTTP Status ==403 – Forbidden==

> Type Status Report

> Message ==数组越界22！！！==

> Description The server understood the request but refuses to authorize it.

> Apache Tomcat/8.0.53


#### 2.2、@ResponseStatus ==加到 方法 上==

```
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
```
- 页面响应：

> HTTP Status ==403 – Forbidden==

> Type Status Report

> Message ==自定义 方法异常==

> Description The server understood the request but refuses to authorize it.

> Apache Tomcat/8.0.53

---


## 3、其他 异常处理的实现类

#### 3.1、DefaultHandlerExceptionResolver

> springmvc 提供的默认异常类：如 405 异常（请求方式错误：要求post 而实际get） ，从spring3开始

#### 3.2、SimpleMappingExceptionResolver
> ==支持文件配置的异常==
- 在 springmvc 配置文件中配置 SimpleMappingExceptionResolver

```
<bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">

        <!-- 定义异常参数，相当于 catch(Exception e) 中的 e ，配置后该值会自动保存在 request域中
            没有配置时 默认 参数为：exception，在页面上直接获取显示（${requestScope.exception}）
        -->
        <property name="exceptionAttribute" value="e"></property>

        <property name="exceptionMappings">
            <props>
                <!-- 相当于 catch(AccountException e){ 跳转：error}
                    key：你要捕获的异常
                -->
                <prop key="javax.security.auth.login.AccountException">
                    error
                </prop>

                <!-- 捕获多个异常-->
                <prop key="java.rmi.AlreadyBoundException">
                    error
                </prop>
            </props>
        </property>
    </bean>
```
- 触发 异常

```
throw new AccountException(); // 触发异常
```
- 显示异常

```
<h2>自定义方法异常 显示页面</h2>
<h3>${requestScope.exception}</h3>
<h3>${requestScope.e}</h3>
```








package zy.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component("logger")
@Aspect//表示当前类是一个切面类
public class Logger {
    @Resource(name = "date")
    private Date date;

    @Pointcut("execution(* zy.service.AccountService.*(..))")
    public void pointCut(){}

    @Before("execution(* zy.service.AccountService.*(..))")
    public void beforeLog(){
        System.out.println("前置日志："+date.toString());
    }
    @AfterReturning("pointCut()")
    public void afterReturningLog(){
        System.out.println("后置日志："+date.toString());
    }
    @AfterThrowing("execution(* zy.service.AccountService.*(..))")
    public void afterThrowingLog(){
        System.out.println("异常日志："+date.toString());
    }
    @After("execution(* zy.service.AccountService.*(..))")
    public void afterLog(){
        System.out.println("最终日志："+date.toString());
    }

    /**
     * 环绕通知
     * 问题：
     *      当我们配置了环绕通知之后，切入点方法没有执行，而通知方法执行了。
     * 分析：
     *      通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的切入点方法调用，而我们的代码中没有。
     * 解决：
     *      Spring框架为我们提供了一个接口：ProceedingJoinPoint。该接口有一个方法proceed()，此方法就相当于明确调用切入点方法。
     *      该接口可以作为环绕通知的方法参数，在程序执行时，spring框架会为我们提供该接口的实现类供我们使用。
     *
     * spring中的环绕通知：
     *      它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式。
     */

    public void setDate(Date date) {
        this.date = date;
    }
}

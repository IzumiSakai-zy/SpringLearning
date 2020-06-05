package zy.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zy.service.AccountService;
import zy.service.IAccountService;

public class AccountController {
    private ApplicationContext ac=null;
    private IAccountService accountService=null;

    @Before
    public void init(){
        ac=new ClassPathXmlApplicationContext("bean.xml");
        accountService=ac.getBean("accountService",IAccountService.class);
    }

    @After
    public void destroy(){
    }

    @Test
    public void saveAccountAopTest(){
        accountService.saveAccount();
        accountService.deleteAccount();
        accountService.updateAccount(500);
    }
}

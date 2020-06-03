package zy.service;

import org.springframework.stereotype.Component;
import zy.dao.AccountDaoImpl;

import javax.annotation.Resource;

@Component
public class Service {

    @Resource(name = "accountDaoImpl")
    private AccountDaoImpl accountDao=null;

    public void saveAccount(){
        System.out.println("业务层方法调用了");
    }
}

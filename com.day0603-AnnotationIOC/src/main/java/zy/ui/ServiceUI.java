package zy.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zy.service.Service;

public class ServiceUI {
    public static void main(String[] args){
        ApplicationContext ac= new ClassPathXmlApplicationContext("bean.xml");
        Service service = ac.getBean("service", Service.class);
        service.saveAccount();
    }
}

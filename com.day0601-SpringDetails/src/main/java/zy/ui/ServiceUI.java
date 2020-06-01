package zy.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zy.service.ServiceImpl;

public class ServiceUI {
    public static void main(String[] args) {
        ApplicationContext ac= new ClassPathXmlApplicationContext("bean.xml");
        ServiceImpl serviceImpl1 = ac.getBean("serviceImpl", ServiceImpl.class);
        ServiceImpl serviceImpl2 = ac.getBean("serviceImpl", ServiceImpl.class);
        System.out.println(serviceImpl1==serviceImpl2);
    }
}

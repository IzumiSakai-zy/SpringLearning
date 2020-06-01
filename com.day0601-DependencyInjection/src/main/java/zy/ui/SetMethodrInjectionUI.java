package zy.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zy.service.SetMethodInjection;

public class SetMethodrInjectionUI {
    public static void main(String[] args) {
        ApplicationContext ac= new ClassPathXmlApplicationContext("bean.xml");
        SetMethodInjection setMethodInjection = ac.getBean("setMethodInjection", SetMethodInjection.class);
        setMethodInjection.saveAccount();
    }
}

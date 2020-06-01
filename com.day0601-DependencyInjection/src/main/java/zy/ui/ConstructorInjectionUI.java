package zy.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zy.service.ConstructorInjection;

public class ConstructorInjectionUI {
    public static void main(String[] args) {
        ApplicationContext ac= new ClassPathXmlApplicationContext("bean.xml");
        ConstructorInjection constructorInjection = ac.getBean("constructorInjection", ConstructorInjection.class);
        constructorInjection.saveAccount();
    }
}

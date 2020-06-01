package zy.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zy.service.CollectionInjection;

public class CollectionInjectionUI {
    public static void main(String[] args) {
        ApplicationContext ac= new ClassPathXmlApplicationContext("bean.xml");
        CollectionInjection collectionInjection = ac.getBean("collectionInjection", CollectionInjection.class);
        collectionInjection.saveAccount();
    }
}

package zy.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zy.domain.User;
import zy.service.UserService;

import java.util.List;

public class UserControllerTest {
    private ApplicationContext ac=null;
    private UserService userService=null;

    @Before
    public void init(){
        ac=new ClassPathXmlApplicationContext("bean.xml");
        userService=ac.getBean("userService",UserService.class);
    }

    @After
    public void destroy(){
    }

    @Test
    public void findAllTest(){
        List<User> users = userService.findAll();
        for (User user:users)
            System.out.println(user);
    }

    @Test
    public void findByIdTest(){
        User user = userService.findById(46);
        System.out.println(user);
    }

    @Test
    public void updateTest(){
        User user = userService.findById(56);
        user.setUserName("张楚岚");
        userService.update(user);
        System.out.println(userService.findById(56));
    }

    @Test
    public void findByNameTest(){
        User user = userService.findByName("王也");
        System.out.println(user);
    }

    @Test
    public void changeNameTest(){
        userService.changeName(52,56);
        System.out.println("交换名字完成");
    }
}

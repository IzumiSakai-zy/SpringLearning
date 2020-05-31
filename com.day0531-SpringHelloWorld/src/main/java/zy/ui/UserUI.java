package zy.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zy.domain.User;
import zy.service.UserService;

import java.io.IOException;
import java.util.List;

public class UserUI {

    public static void main(String[] args) throws IOException {
        ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        UserService userService= (UserService) ac.getBean("UserService");
        List<User> users=userService.getAllUsers();
        for (User user:users)
            System.out.println(user);
    }
}

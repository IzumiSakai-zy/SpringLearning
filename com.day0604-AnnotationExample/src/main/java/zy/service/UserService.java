package zy.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.core.IsNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import zy.dao.IUserMapper;
import zy.domain.User;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service("userService")
public class UserService {
    @Resource(name = "userMapper")
    private IUserMapper userMapper;

//    public void insert(User user){
//        userMapper.insert(user);
//    }
//
//
//    public void deleteById(Integer id){
//        userMapper.deleteById(id);
//    }
//
    public void update(User user){
        userMapper.update(user);
    }

    public List<User> findAll(){
        return userMapper.findAll();
    }

    public User findById(Integer id){
        return userMapper.findById(id);
    }
}

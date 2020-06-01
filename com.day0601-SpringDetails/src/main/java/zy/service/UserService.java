package zy.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import zy.dao.IUserDao;
import zy.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserService {
    private InputStream is= Resources.getResourceAsStream("SqlMapConfig.xml");
    private SqlSession sqlSession= new SqlSessionFactoryBuilder().build(is).openSession();
    private IUserDao userDao=sqlSession.getMapper(IUserDao.class);

    public UserService() throws IOException {
    }

    public List<User> getAllUsers() throws IOException {
        return userDao.findAll();
    }
}

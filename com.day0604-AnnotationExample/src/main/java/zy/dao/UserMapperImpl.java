package zy.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zy.domain.User;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository("userMapper")
public class UserMapperImpl implements IUserMapper {
    private InputStream is;
    private SqlSessionFactoryBuilder builder;
    private SqlSession sqlSession;
    private IUserMapper userMapper;

    public UserMapperImpl() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        builder=new SqlSessionFactoryBuilder();
        sqlSession=builder.build(is).openSession();
        userMapper=sqlSession.getMapper(IUserMapper.class);
    }
//    private SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
//    private InputStream is;
//    {
//        try {
//            is = Resources.getResourceAsStream("SqlMapConfig.xml");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private SqlSession sqlSession=builder.build(is).openSession();
//    private IUserMapper userMapper=sqlSession.getMapper(IUserMapper.class);

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

}

package zy.service;

import zy.dao.IUserMapper;
import zy.domain.User;

import java.util.List;

public interface IUserService {
    void update(User user);

    List<User> findAll();

    User findById(Integer id);

    User findByName(String name);

    void changeName(Integer sourceId, Integer targetId);

    void setUserMapper(IUserMapper userMapper);
}

package zy.dao;

import org.apache.ibatis.annotations.Select;
import zy.domain.User;

import java.util.List;

public interface IUserDao {
    @Select("select * from user")
    List<User> findAll();
}

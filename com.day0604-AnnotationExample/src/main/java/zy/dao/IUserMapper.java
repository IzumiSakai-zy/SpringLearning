package zy.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import zy.domain.User;

import java.util.List;

public interface IUserMapper {
//    @Insert("insert into user(username,birthday,sex,address) values(#{userName),#{birthday},#{sex},#{address}")
//    void insert(User user);
//
//    @Delete("delete from user where id=#{id}")
//    void deleteById(Integer id);
//
    @Update("update user set username=#{userName},sex=#{sex},birthday=#{birthday},address=#{address} where id=#{id}")
    void update(User user);

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where id=#{id}")
    User findById(Integer id);
}

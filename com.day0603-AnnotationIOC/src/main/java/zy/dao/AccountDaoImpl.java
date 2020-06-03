package zy.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class AccountDaoImpl implements IAccountDao {
    @Override
    public void getUserName() {
        System.out.println("获取用户名成功");
    }
}

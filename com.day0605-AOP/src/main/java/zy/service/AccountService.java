package zy.service;

public class AccountService implements IAccountService {
    @Override
    public void saveAccount() {
        System.out.println("保存方法执行了");
    }

    @Override
    public void updateAccount(int money) {
        System.out.println("更新方法执行了，现在有"+money+"元");
    }

    @Override
    public int deleteAccount() {
        System.out.println("删除方法执行了");
        return 0;
    }
}

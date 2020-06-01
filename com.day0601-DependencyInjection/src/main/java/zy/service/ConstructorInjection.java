package zy.service;

import java.util.Date;

public class ConstructorInjection {
    private String name;
    private Integer age;
    private Date birthday;

    public ConstructorInjection(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void saveAccount(){

        System.out.println("constructorInjection的saveAccount方法执行了");
        System.out.println(name+"   "+age+"   "+birthday);
    }
}

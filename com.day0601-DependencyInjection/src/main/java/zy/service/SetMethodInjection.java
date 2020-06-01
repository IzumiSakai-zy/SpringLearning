package zy.service;

import java.util.Date;

public class SetMethodInjection {
    private String name;
    private Integer age;
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void saveAccount(){

        System.out.println("setMethodInjection的saveAccount方法执行了");
        System.out.println(name+"   "+age+"   "+birthday);
    }
}

# SpringLearning
学习Spring

**********************

### 一些基本概念

* 耦合 ： 程序间的依赖关系，包括类耦合，方法耦合

* 结构 ： 解除程序间的依赖关系

* 目的 ： 编译时不依赖，运行时依赖

* 解决思路 
  * 通过反射来创建对象，避免使用new关键字
  * 通过读取配置文件来获取要创建对象的全限定类名
  
* BeanFactory实现单例模式
  * 首相给静态工厂类添加一个**Map<String, Object>**属性
  * 在静态代码块中初始化时就创建所有的Bean对象，存放在HashMap中
  * 此时这个Map就是**Spring容器**
  
* IOC概念和作用

  * 工厂模式与new模式的区别

     ![Image text](img/工厂模式IOC.png)
  
  *  IOC概念 ： 把创建对象的控制权交给框架。 通俗解释： 放弃对象创建的权利，把权利交给工厂，让工厂来创建对象

*****************************

### Spring入门案例

* 创建maven工程并导入依赖

  * ```xml
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.0.2.RELEASE</version>
    </dependency>
    ```
  
* 创建bean.xml配置，导入xml依赖并配置自己的bean对象

  * xml依赖在 https://docs.spring.io/spring/docs/5.1.14.RELEASE/spring-framework-reference/core.html#spring-core 可以找到

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
            https://www.springframework.org/schema/beans/spring-beans.xsd">
        <!--自己添加的bean配置-->
        <bean id="UserService" class="zy.service.UserService" />
    </beans>
    ```
  
* 在需要的地方使用

  * 读取配置文件创建容器对象 `ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");`
  * 根据容器对象获取想创建的对象 
    * `UserService userService= (UserService) ac.getBean("UserService");`
    * `UserService userService=ac.getBean("UserService",UserService.class)` 传入字节码不用强转类型
* 总结：通过读取配置文件反射创建对象，实现IOC控制反转。

***********************

### 容器的一些知识
* ApplicationContext的三个实现类
  * ClassPathXmlApplicationContext("bean.xml") : 只能加载类路径下的配置文件 (比较常用)
  
  * FileSystemXmlApplicationContext(C:\\\user\\izumisaki\\desktop\\bean.xml)： 可以加载磁盘路径下任意有访问权限的配置文件
  
  * AnnotationConfigApplicationContext ： 用于读取注解创建容器
* BeanFactory和ApplicationContext的区别
  * BeanFactory是Spring容器的顶层接口，ApplicationContext是BeanFactory的子接口
  * ApplicationContext在构建容器时，采用**立即加载**的方式，只要一创建就马上往Map中创建存入Bean对象
    * 适用于单例模式
  * BeanFactory在构建容器时，采用**延迟加载**方式，使用时才创建对象
    * 适用于多例模式
  * BeanFactory的实现对象已经过时，不建议使用。
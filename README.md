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

******************

### Spring中Bean的一些细节

* 创建Bean对象的三种方式

  * 第一种：使用默认构造函数创建，如果没有默认构造函数会报错。只有id和class标签

    ```xml
    <bean id="ServiceImpl" class="zy.service.ServiceImpl" />
    ```
    
  * 第二种 ：使用某个类的方法创建对象，并存入Spring容器。即类方法的返回值为对象
  
    ```xml
    <bean id="instanceFactory" class="zy.factory.InstanceFactory" />
    <bean id="serviceImpl" factory-bean="instanceFactory" factory-method="getServiceImpl" />
    ```
    * 使用**factory-bean和factory-method**属性
    
  * 第三种：使用某个类的静态方法创建对象，并存入Spring容器。即类静态方法返回值为对象，如工厂类
  
    ```xml
    <bean id="serviceImpl" class="zy.factory.StaticFactory" factory-method="getServiceImpl" />
    ```
    * 一定要是静态方法才行
  
* Bean的作用范围
  
  * <bean>标签的scope属性取值
    * singleton : 单例的，且为默认取值
    * prototype ： 多例的
    * request ： web请求域
    * session ： web会话域     * glable-session ： 全局会话范围  （5.0）把这个移除了
      
    * 全局session与负载均衡
    
      ![Image text](img/全局session与负载均衡.png)
      
      * glable-session存验证码，所有服务器都能使用
    
  * bean的生命周期
    
    * 单例对象 ：生命周期和**容器**一模一样
    * init和destroy方法 ： `<bean id="serviceImpl" class="zy.factory.StaticFactory" factory-method="getServiceImpl" scope="singleton" init-method="init" destroy-method="destroy" />`
    * 多例对象 ：使用时创建；长时间不用且没有对象引用时由java垃圾回收销毁

### Spring的依赖注入

* 依赖注入概念 ： 依赖关系的维护

* 能注入的数据类型
  * 基本类型和String
  * 其他已经配置的Bean类型
  * 复杂类型/集合类型
  
* 注入的方式

  * 使用构造函数提供

    * ```xml
      <bean id="serviceImpl" scope="singleton" class="zy.service.ServiceImpl">
          <constructor-arg name="name" value="IzumiSkai" />  <!--基本类型-->
          <constructor-arg name="age" value="40" />  <!--Integer类型-->
          <constructor-arg name="birthday" ref="now" /> <!--其他类型  使用ref属性注入-->
      </bean>
      <bean id="now" class="java.util.Date" />
      ```

    * <bean>标签下嵌套<constructor-arg>标签

      * <constructor>属性    type，index，name都是在找给谁注入，其中name最常用
      * <constructor>属性    value为依赖注入的值
      * <constructor>属性    ref用Spring核心容器中出现过的对象类注入值
    
    * 优点 ：创建时注入是必须操作
  
    * 缺点 ：要是一开始想先不注入而不能实现
  
  * 使用set方法提供
  
    * ```xml
      <bean id="setMethodInjection" scope="singleton" class="zy.service.SetMethodInjection">
          <property name="name" value="IzumiSakai" />
          <property name="age" value="40" />
          <property name="birthday" ref="now" />
      </bean>
      <bean id="now" class="java.util.Date" />
      ```
    * 使用<bean>标签嵌套<property>标签，<property>的属性和<constructor-arg>类似
    
  * 使用注解提供
  
  * 使用set为容器集合注入
  
    ```xml
    <!--为容器注入-->
    <bean id="collectionInjection" class="zy.service.CollectionInjection" scope="singleton">
        <!--String[]注入-->
        <property name="stringArray">
            <array>
                <value>first</value>
                <value>second</value>
                <value>third</value>
            </array>
        </property>
        <!--List<String>注入-->
        <property name="list">
            <list>
                <value>first</value>
                <value>second</value>
                <value>third</value>
            </list>
        </property>
        <!--map注入-->
        <property name="map">
            <map>
                <entry key="1" value="first" />
                <entry key="2" value="second" />
                <entry key="3" value="third" />
            </map>
        </property>
        <!--properties注入-->
        <property name="properties">
            <props>
                <prop key="1">first</prop>
                <prop key="2">second</prop>
                <prop key="3">third</prop>
            </props>
        </property>
    </bean>
    ```
*****************
### 基于注解的IOC

* 首先创建bean.xml，依赖为带有xmlns:context的依赖，并配置要扫描的包

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
          https://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/context
          https://www.springframework.org/schema/context/spring-context.xsd">
      <context:component-scan base-package="zy" />
  </beans>
  ```
  
* 用于创建对象的

  * `@Component` : 属性值为value，默认为类名且首字母小写
  * `@Controler` : 用于表现层
  * `@Service` : 用于业务层
  * `@Repository` : 用于持久层
  
* 用于注入数据的

  * `@Autowired` : 自动按照类型注入，只要spring容器中有唯一一个与之类型匹配的就可以注入成功
    * 出现位置 ：方法或变量
    * 细节 ： 使用注解注入，set方法就不是必须的了
    * 为接口注入，实现类的bean居然也可以。但有两个实现类就有可能会报错
    * 当遇到多个bean匹配时。首先根据数据类型匹配容器map的value，在根据变量名称匹配容器map的key
    * ![Image text](img/Autowired自动注入.png)
  * `@Resource(name='accountDao')` : 按照指定bean的id注入
    *  name属性 ：指定注入bean的id
  * `@Value` : 用于注入基本类型和String类型
* 用于改变作用范围

  * `@Scope` : value属性值为singleton或者prototype
# JAVA笔记

java学习笔记，用于记录spring boot 和mybatis还有reactor的运用。

java学习路线：https://www.bilibili.com/read/cv9965357

**入门：** Java SE基础 → Java Web(含数据库+H5+js+vue)

**中级：** Maven → Git → SSM框架 →Spring高级→ MybatisPlus → Spring Boot→ 《传智健康》项目实战 → 《瑞吉外卖》项目实战

**进阶：** Spring Cloud(微服务开发,学这一套就够了) 也可以按照以下技术点学： 
Dubbo → Zookeeper → RabbitMQ → RocketMQ → Docker → Redis → MongoDB → 搜索技术Elasticsearch → MySQL进阶

项目：《学成在线》在线教育→《SaaSiHRM》企业服务 → 《好客租房》生活服务 → 权限管理一体化解决方案 → 短信网关平台

面试： 数据结构与算法 →  Java大厂面试专题课 → Java百度地图



## 1、Java面向对象

Java面向对象（Object-Oriented Programming，简称OOP）是一种编程范式，它将现实世界中的事物抽象为对象，通过定义对象的属性和行为来描述对象的特征，并通过对象之间的互动来实现功能。Java是一种面向对象的编程语言，它支持面向对象编程的三大特性：封装、继承和多态。

封装是指将对象的属性和行为封装在一起，以隐藏对象的实现细节并保护其数据的安全性，只对外暴露必要的接口。

继承是指通过定义一个新的子类来继承父类的属性和行为，子类可以增加自己的属性和行为，也可以重写父类的方法。

多态是指同一种行为可以具有不同的表现形式，不同的子类可以对相同的消息作出不同的响应，使程序具有更好的扩展性和灵活性。

### 1.1、java继承

继承就是子类继承父类的特征和行为，使得子类对象（实例）具有父类的实例域和方法，或子类从父类继承方法，使得子类具有父类相同的行为。

继承类型：

![img](assets/java-extends-2020-12-08.png)





**implements关键字**

使用 implements 关键字可以变相的使java具有多继承的特性，使用范围为类继承接口的情况，可以同时继承多个接口（接口跟接口之间采用逗号分隔）。

```java
public interface A {
    public void eat();
    public void sleep();
}
 
public interface B {
    public void show();
}
 
public class C implements A,B {
}
```



**super和this关键字**

super关键字：我们可以通过super关键字来实现对父类成员的访问，用来引用当前对象的父类。

this关键字：指向自己的引用。

```java
class Animal {
  void eat() {
    System.out.println("animal : eat");
  }
}
 
class Dog extends Animal {
  void eat() {
    System.out.println("dog : eat");
  }
  void eatTest() {
    this.eat();   // this 调用自己的方法
    super.eat();  // super 调用父类方法
  }
}
 
public class Test {
  public static void main(String[] args) {
    Animal a = new Animal();
    a.eat();
    Dog d = new Dog();
    d.eatTest();
  }
}


输出结果为：
animal : eat
dog : eat
animal : eat
```



**final关键字**

使用 final 关键字声明类，就是把类定义定义为最终类，不能被继承，或者用于修饰方法，该方法不能被子类重写

 final 定义的类，其中的属性、方法不是 final 的。

```java
声明类：
final class 类名 {//类体}

声明方法：
修饰符(public/private/default/protected) final 返回值类型 方法名(){//方法体}
```



## 2、重写与重载

重写是子类对父类的允许访问的方法的实现过程进行重新编写, 返回值和形参都不能改变。**即外壳不变，核心重写！**

重写的好处在于子类可以根据需要，定义特定于自己的行为。 也就是说子类能够根据需要实现父类的方法。

重写方法不能抛出新的检查异常或者比被重写方法申明更加宽泛的异常。例如： 父类的一个方法申明了一个检查异常 IOException，但是在重写这个方法的时候不能抛出 Exception 异常，因为 Exception 是 IOException 的父类，抛出 IOException 异常或者 IOException 的子类异常。

在面向对象原则里，重写意味着可以重写任何现有方法。实例如下：

```java
class Animal{
   public void move(){
      System.out.println("动物可以移动");
   }
}
 
class Dog extends Animal{
   public void move(){
      System.out.println("狗可以跑和走");
   }
   public void bark(){
      System.out.println("狗可以吠叫");
   }
}
 
public class TestDog{
   public static void main(String args[]){
      Animal a = new Animal(); // Animal 对象
      Animal b = new Dog(); // Dog 对象,注意此处是Animal类型而不是Dog类型
 
      a.move();// 执行 Animal 类的方法
      b.move();//执行 Dog 类的方法
      b.bark();
   }
}

TestDog.java:30: cannot find symbol
symbol  : method bark()
location: class Animal
                b.bark();
                 ^
该程序将抛出一个编译错误，因为b的引用类型Animal没有bark方法。如果去掉"b.bark();"即可正常输出，因为b的引用类型Animal中有move方法
```


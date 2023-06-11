# JAVA基础

java全栈知识体系：https://pdai.tech/md/java/basic/java-basic-x-generic.html

java学习路线：https://www.bilibili.com/read/cv9965357

**入门：** Java SE → Java Web(含数据库+H5+js+vue)

**中级：** SSM（Spring SpringMVC  MyBatis）框架 →Spring Boot

**进阶：** Spring Cloud



Java入门基础视频教程：https://www.bilibili.com/video/BV1Cv411372m/?spm_id_from=333.999.0.0&vd_source=c72f3b18d4102d04acc65f53171cc909



## 1、常见问题

**Java面向对象**（Object-Oriented Programming，简称OOP）三大特性：封装、继承和多态。

继承: 是指通过定义一个新的子类来继承父类的属性和行为，子类可以增加自己的属性和行为，也可以重写父类的方法。

封装: 是指将对象的属性和行为封装在一起，以隐藏对象的实现细节并保护其数据的安全性，只对外暴露必要的接口。

多态: 是指同一种行为可以具有不同的表现形式，不同的子类可以对相同的消息作出不同的响应，使程序具有更好的扩展性和灵活性。



### 常见关键字

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



### 重写与重载

方法的重写(Overriding)和重载(Overloading)是java多态性的不同表现，重写是父类与子类之间多态性的一种表现，重载可以理解成多态的具体表现形式。

重载：就是同样的一个方法能够根据输入数据的不同，做出不同的处理

重写：就是当子类继承自父类的相同方法，输入数据一样，但要做出有别于父类的响应时，你就要覆盖父类方法

![img](assets/overloading-vs-overriding.png)





### 接口与类的区别

接口（英文：Interface），在JAVA编程语言中是一个抽象类型，是抽象方法的集合，接口通常以interface来声明。一个类通过继承接口的方式，从而来继承接口的抽象方法。

- 类和类之间是继承关系
- 接口和接口之间是继承关系
- 类和接口之间是实现关系（类实现接口）

```java
/* 文件名 : Animal.java */
interface Animal {
   public void eat();
   public void travel();
}
```



### Java三元运算符

```java
	int a = 99;
	int b = 10;
	int result = a > b ? a++ : b--
	System,out.print(result); //输出为99，此时a为100，b为10
```



### 单引号与双引号的区别

** java中单引号与双引号的区别：**

单引号的数据是char类型，如char[] arr={'a','b'}; 为char类型。

双引号的数据是String类型，如String[] arr = {"a","b","c"}; 为string类型。



### 常见方法定义

https://www.cnblogs.com/zhucc369/p/14716289.html

类中的无返回值方法

类中带有返回值类型的方法

带有访问修饰符的方法（public、private、protected、默认方法）

static修饰的方法（静态方法、类方法，静态变量（方法）访问方式：类名.变量名（方法名））

实例方法（实例变量/方法需要先将类实例化后才可访问：对象名.变量名（方法名））

final修饰的方法（修饰方法之后，此方法将表示最终的方法，不能在出现继承、重写等问题）

构造函数方法（又称为“构造器”或者“构造方法”，用来加载类，在类创建实例的时候，会执行此方法。）

接口中的方法（默认类型为 public static final 返回值类型 方法名()，默认写为“  返回值类型 方法名（参数列表...））

泛型方法

抽象类的方法

类中的类方法（多用于单例工厂的方法）



### static final区别

final修饰变量时，该变量在类加载时就会被初始化，会因为对象的创建而创建加载。

static修饰变量时，该变量将只被初始化一次，此后不再重新初始化。

https://www.cnblogs.com/notably/p/11791542.html





### **命名规范**

变量名称：首字母小写+驼峰法 int phoneNumber = 123

类名称：首字母大写+驼峰法 Hello.java

数据库字段名称：小写+下划线 tab_description





### **get和set用法**

get&set方法使用前提：当要访问被private封装的属性时

1.灵活性
比如我们有一个Person类，我们给它设置一个属性name，但是我们希望在取名字的时候，不是只显示名字，而是把名字按我们的要求输出，比如”我的名字叫XX”
我们只要定义一个person对象，并给他setName(xx)，再getName的时候，就会显示我的名字叫XX。在实际业务中还可以有更灵活的运用，这里就不举例了。

2.安全性。
比如我们的操作系统在新建用户或在共享文件的时候，经常会提示对针对某个用户的权限是什么，是只读还是只写还是可读写，这里的读写就好比get/set。
对于类来说，如果直接用public定义某个属性，那么这个属性是可读可写的，
如果你希望一个类的某个属性是只能读取，不能写入的时候，上面用public定义某个属性就不能满足了，
我们可以使用private定义属性，再定义一个get方法，但是不定义set方法来实现。反之，只定义set，不定义get则表示该属性是只写不能读的

总结：使用成员变量，若定义为private，则不可读，且不可写，该成员只能同过构造函数传值，其他情况无法使用。
若定义为public， 则可读又可写，不安全。
若想可读不可写，或可写不可读，就体现出get/set方法的用处了。



```java
public boolean convert(Boolean value){
    return value == null ? false : value;
}

Boolean 必须为大写！成为封装类型
```



### Integer和int的区别

1、数据类型不同：int 是基础数据类型，而 Integer 是包装数据类型；
2、默认值不同：int 的默认值是 0，而 Integer 的默认值是 null；
3、内存中存储的方式不同：int 在内存中直接存储的是数据值，而 Integer 实际存储的是对象引用，当 new 一个 Integer 时实际上是生成一个指针指向此对象；
4、实例化方式不同：Integer 必须实例化才可以使用，而 int 不需要；
5、变量的比较方式不同：int 可以使用 == 来对比两个变量是否相等，而 Integer 一定要使用 equals 来比较两个变量是否相等。



### 缓存池

缓存一些常用的数据能够提高程序的运行效率，java中给基本类型提供了缓存池，提高数据的读取速度。

类型对应的缓冲池如下：

Integer 缓存池的大小默认为 -128~127。

boolean values true and false

all byte values

short values between -128 and 127

int values between -128 and 127

char in the range \u0000 to \u007F



### String, StringBuffer and StringBuilder

**1. 可变性**

- String 不可变
- StringBuffer 和 StringBuilder 可变

**2. 线程安全**

- String 不可变，因此是线程安全的
- StringBuilder 不是线程安全的
- StringBuffer 是线程安全的，内部使用 synchronized 进行同步



**不同类型list值转换**

```java
// 将List<AirlineBasicInfo> 传给List<FlightGroupInfo>

List<FlightGroupInfo> groupInfoList = aIContentRequest.getAirlineBasicInfo().stream().map(airlineBasicInfo -> {
     FlightGroupInfo group = new FlightGroupInfo();
     group.setAirlines(airlineBasicInfo.getAirlines());
     return group;
 }).collect(Collectors.toList());


 List<FlightGroupInfo> flightGroupInfos = new ArrayList<>();
 for (AirlineBasicInfo basicInfo: aIContentRequest.getAirlineBasicInfo() ) {
     FlightGroupInfo groupInfo = new FlightGroupInfo();
     groupInfo.setAirlines(basicInfo.getAirlines());
     groupInfo.setFlightNoGroup(basicInfo.getFlightNoGroup());
     flightGroupInfos.add(groupInfo);
 }
```






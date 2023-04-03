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

方法的重写(Overriding)和重载(Overloading)是java多态性的不同表现，重写是父类与子类之间多态性的一种表现，重载可以理解成多态的具体表现形式。

- (1)方法重载是一个类中定义了多个方法名相同,而他们的参数的数量不同或数量相同而类型和次序不同,则称为方法的重载(Overloading)。
- (2)方法重写是在子类存在方法与父类的方法的名字相同,而且参数的个数与类型一样,返回值也一样的方法,就称为重写(Overriding)。
- (3)方法重载是一个类的多态性表现,而方法重写是子类与父类的一种多态性表现。

总结：

重载就是同样的一个方法能够根据输入数据的不同，做出不同的处理

重写就是当子类继承自父类的相同方法，输入数据一样，但要做出有别于父类的响应时，你就要覆盖父类方法

![img](assets/overloading-vs-overriding.png)



### 2.1、重写

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

/*
TestDog.java:30: cannot find symbol
symbol  : method bark()
location: class Animal
                b.bark();
                 ^
该程序将抛出一个编译错误，因为b的引用类型Animal没有bark方法。如果去掉"b.bark();"即可正常输出，因为b的引用类型Animal中有move方法
*/
```

### 2.2、重载

重载(overloading) 是在一个类里面，方法名字相同，而参数不同。返回类型可以相同也可以不同。

每个重载的方法（或者构造函数）都必须有一个独一无二的参数类型列表。

最常用的地方就是构造器的重载。

例：重载是同样的一个方法写多次变量修饰符不一样，当调用方法时，会自动判断给入数的类型，选取其中符合的方法。

```java
public class Overloading {
    private static int i = 1;
    private static double l = 3.1415;
    private static String k = "我是栗子";

    //参数类型不同
    public void test(int a){
        System.out.println("你输入了整数:"+a);
    }   
 
    public void test(double a){
        System.out.println("你输入了浮点数:"+a);
    }   
 
    public void test(String a){
        System.out.println("你输入了字符串:"+a);
    }   
 
    public static void main(String[] args){
        Overloading lizi = new Overloading();
        lizi.test(i);
        lizi.test(l);
        lizi.test(k);
    }
}
/*
输出结果：
你输入了整数：1
你输入了浮点数：3.1415
你输入了字符串：我是栗子
*/
```



## 3、接口与类的区别

接口（英文：Interface），在JAVA编程语言中是一个抽象类型，是抽象方法的集合，接口通常以interface来声明。一个类通过继承接口的方式，从而来继承接口的抽象方法。

接口并不是类，编写接口的方式和类很相似，但是它们属于不同的概念。类描述对象的属性和方法。接口则包含类要实现的方法。

1.接口可以多继承

2.接口的方法声明必须是 public abstract 即便不写默认也是

3.接口里面不能包含方法具体实现

4.类实继承接口必须实现接口里申明的全部方法，除非该类是抽象类

5.类里面可以声明 public static final 修饰的变量

6.接口不能被实例化，但是可以被实现类创建

### 3.1、接口与类相似点

- 一个接口可以有多个方法。
- 接口文件保存在 .java 结尾的文件中，文件名使用接口名。
- 接口的字节码文件保存在 .class 结尾的文件中。
- 接口相应的字节码文件必须在与包名称相匹配的目录结构中。

### 3.2、接口与类的区别

- 接口不能用于实例化对象。
- 接口没有构造方法。
- 接口中所有的方法必须是抽象方法，Java 8 之后 接口中可以使用 default 关键字修饰的非抽象方法。
- 接口不能包含成员变量，除了 static 和 final 变量。
- 接口不是被类继承了，而是要被类实现。
- 接口支持多继承。

```java
/* 文件名 : Animal.java */
interface Animal {
   public void eat();
   public void travel();
}
```



## 4、实际问题

4.1 get和set用法

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




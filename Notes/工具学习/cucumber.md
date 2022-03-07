# cucumber

**cucumber文档说明**：[Guides - Cucumber Documentation](https://cucumber.io/docs/guides/)

## **BDD UI 自动化测试理念**

在说 BDD-UI-Testing 之前，我们先来看看 TDD、ATDD、BDD、DDD 这 4 个开发模式。

- TDD：测试驱动开发（Test-Driven Development）

- ATDD：验收测试驱动开发（Acceptance Test Driven Development）

- BDD：行为驱动开发（Behavior Driven Development）

- DDD：领域驱动开发（Domain Drive Design）
  
  BDD的基本语法为Gherkin（黄瓜籽）

开发流程（W 模型）：

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\c047b7d7e5ab1b28b708f745730a5aa3bedfa78c.png)

BDD 流程：

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\3496397744d5d97a4508e5cac9e060323033bf4d.png)

采用 BDD 流程进行开发，由外而内，持续地描述当前系统或模块的行为，并为之实现自动化（即步骤定义）。当产品代码部分完成后，右侧的一系列测试活动都已经自动化了。

从层次上来说，BDD 是基于 TDD 的，或者说在自动化测试中，TDD 所在的位置比较底层，是基础，而 BDD 则是它的演进版本。

BDD 核心的是，开发人员、QA、非技术人员和用户都参与到项目的开发中，彼此协作。BDD 强调从用户的需求出发，最终的系统和用户的需求一致。BDD验证代码是否真正符合用户需求，因此 BDD 是从一个较高的视角来对验证系统是否和用户需求相符。

BDD工作流程：

<img src="file:///E:/Apersonal/awesome-programming-books/Notes/工具学习/assets/76d39959cc5b27ee5929281f9e204a3565ff0fdc.png" title="" alt="" width="326">

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\1ecd1aaafe6207c99c35def6ea48c75a7296c7cf.png)

总结：

- BDD 是一种敏捷软件开发的技术
- BDD 提供了一种通用的，简单的，结构化的描述语言
- BDD 一般是黑盒测试，侧重 UI，TDD 一般是白盒测试，侧重代码
- BDD 一般采用集成测试，TDD 一般采用单元测试
- BDD 不只是自动化测试

## **BDD-UI-Testing 实践模式**

例如：

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\7e8176c2bf190f781d129c65f4be89bea680ef39.jpg)

获取的自动化测试报告：

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\9eb308f70d58a6147887ddacb8469eaa742f0688.jpg)

## 1、cucumber-java环境搭建

（基于maven搭建cucumber基础环境）：

### 1.1 首先搭建java基础环境：JDK+Maven+idea

基础依赖包：cucumber-java、cucumber-junit、junit

依赖插件：Cherkin

### 1.1 创建maven项目

#### 1.1.1 新建maven项目

<img src="file:///E:/Apersonal/awesome-programming-books/Notes/工具学习/assets/0230b12bd494d8ff40e5012eb9f5df05554cff43.png" title="" alt="" data-align="inline">

#### 1.1.2 查找相关依赖包

[Maven Repository: cucumber](https://mvnrepository.com/search?q=cucumber)

到maven官网中找到相关依赖包，再将相应依赖包代码拷贝到pom.xml文件中。

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\2022-03-02-19-55-30-image.png)

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\f4c8de213d47e12e8873f2da8414ece016a2e27c.png)

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\aa5f49b909633bec41b4db406b1bc1ee28de57f5.png)

pom.xml 示例：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>craftsman.example</groupId>
    <artifactId>cucumber_offline</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-java -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>7.0.0-RC1</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-junit -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>7.0.0-RC1</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/junit/junit -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13-rc-2</version>
            <scope>test</scope>
        </dependency>


    </dependencies>




    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>


</project>
```

控制台执行：mvn -install 命令安装相应依赖包。

*注：注意在idea编辑器中安装Gherkin插件，否则无法识别cucumber格式文件。

搭建成功后运行示例：

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\5845043c229f34ebef4b993b875d7b7fb1e10540.png)

## 2、Gherkin 语法

`Gherkin` **是一种简单的英语文本语言**, 它有助于工具--Cucumber 解释和执行测试脚本。一个完整的测试脚本是由多个 `step` 组成的，`step` 即最小单元。多个 `step` 组成一个 `Scenario`，即一个完整的测试 `case`。多个 `Scenario` 组成一个 `Feature`，即一组相关的测试 `case`  .

### 2.1 关键字概述

**主要关键字：**

- `Feature`
- `Rule`
- `Example`（或 `Scenario`）
- `Given`，`When`，`Then`，`And`，`But` 步骤（或 `*`）
- `Background`
- `Scenario Outline`（或 `Scenario Template`）
- `Examples`（或 `Scenarios`）

**还有一些辅助关键字：**

- `"""` （文档注释）
- `|` （数据表、参数化）
- `@` （标签）
- `#` （注释）

**Feature**

feature 文件必须以关键字 Feature 开始。feature关键字是为了提供软件特性的高级描述，并对相关场景进行分组。feature后面的描述性文件会被忽略。

**Rule（as of Gherkin 6）**:

Rule为非必要关键字，主要用作测试用例分组，例如：

```java
Feature: Highlander

  Rule: There can be only One

    Scenario: Only One -- More than one alive
      Given there are 3 ninjas
      And there are more than one ninja alive
      When 2 ninjas meet, they will fight
      Then one ninja dies (but not me)
      And there is one ninja less alive

    Scenario: Only One -- One alive
      Given there is only 1 ninja alive
      Then he (or she) will live forever ;-)

  Rule: There can be Two (in some cases)

    Scenario: Two -- Dead and Reborn as Phoenix
```

在这个例子中，`Rule: There can be only One` 下有两个 `Scenario`，`Rule: There can be Two (in some cases)` 下只有一个 `Scenario`。

翻译来过：`Only One -- More than one alive` 和 `Only One -- One alive` 属于同一组，写代码的时候确保两个用例都遵循 `Only One` 规则，但是 Cucumber 并不会检查。

说白了类似于注释，告诉别人这两个测试用例遵循了某种规则。

**Description**

自定义的描述。相关描述也可以放在“Example/Scenario”，“Background”，“Scenario Outline”和“Rule”的下面。

**Example（or Scenario）**

说明业务规则的具体示例。由一系列步骤组成。

1：描述初始上下（Given的步骤）

2：描述一个事件（When的步骤）

3：描述一个预期的结果（Then的步骤）

**Steps:** Given，When，Then，And，But for steps（or *）

Given：用于描述系统的初始环境--场景的场景。它通常是发生在过去的事情。

When：用于描述一个事件或一个动作时。这可以是一个与系统交互的人，也可以是由另一个系统触发的事件。

Then：用于描述预期的结果。步骤的步骤定义应该使用断言来比较实际结果和预期结果。

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\5bd0847e731542ca5a8eb2a0f07a9bceb6311447.png)

**Background**

当一个feature的所有场景中重复相同的Given步骤时可以使用Background。它允许您为后面的场景添加一些上下文。（每个Rule下面只能有1个background）

建议：不要使用background设置复杂的状态、Background部分保持简短且生动。

**Scenario Outline（or Scenario Template）**

Scenario Template 是关键字Scenarior Outline 的同义词。

```java
Scenarior:eat 5 out of 12
    Given therr are 12 cucumbers
    When I eat 5 cucumbers
    Then I should hae 7 cucumbers


等同于：


Scenarior Outline:eating
    Given there are <start> cucumbers
    When I eat <eat> cucumbers
    Then I should have <left> cucumbers

    Examples:
      | start| eat| left|
      | 50   | 70 | 120 |



用中文实现：
# language:zh-CN
功能: 计算器
  简单的计算器，两个数字相加

  场景大纲: 两数相加
    假如第一个数字是 <firstNumber>
    同时第二个数字是 <secondNumber>
    当两个数字相加
    那么显示的结果为 <result>

    例子:
      | firstNumber | secondNumber | result |
      | 50          | 70           | 120    |
```

**常用符号：**

"""(Doc String)、|(Data Tables)、@(Tags)、#(Comments)

### 2.2 Tags的使用

Tag的继承逻辑

Tags由子元素继承

▪ 放置在feature上方的标记被Scenario、Scenario Outline或Examples继承。

▪ 放置在Scenario Outline上方的标记将被Examples继承。

Tag的使用场景（设计目的）

标签是组织功能和场景的好方法，比如可只执行带相应tag的用例。可用于以下目的;

▪ 运行场景的子集（Running a subset of scenarios）

▪ 将钩子限制到场景的子集（Restricting hooks to a subset of scenarios）

▪ 关联到外部系统

▪ 标记开发状态

运行场景的子集（Running a subset of scenarios）

```java
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
//                "html:target/cucumber-report.html",
//                "json:target/cucumber-json.json",
                "html:test-output",
                "json:target/cucumber-report/cucumber.json",
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"
        },
        tags = "@debug", //执行带有debu标签的用例
        features = "src/test/resources/test-case",
        objectFactory = CustomObjectFactory.class
)
public class CucumberRunner {
}
```

将钩子限制到场景的子集（Restricting hooks to a subset of scenarios）

```java
@After("@browser and not @headless")
public void doSomethingAfter(Scenario scenario){
}
// 带有browser标签的会自动执行下面步骤。
```

关联到外部系统

```java
// 标签可以引用外部系统中的id，如需求管理工具、问题跟踪器或测试管理工具

@BJ-x98.77 @BJ-z12.33
Feature: Convert transaction

//可以使用自定义的cucumber报告插件，将标签转换为指向外部工具中的文档的链接。
```

标记开发状态

```java
@qa_ready   //已开发完成
@smock      //冒烟测试用例
@regression //回归测试用例
Feature Index projects
```

### 2.3 cucumber-Hook

**cucumber执行生命周期：**

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\1d5675f0acdc247983d12078c3aac7f2836799a9.png)



**Hook有两种执行方式**

BeforeAll/AffterAll：

在所有Scenario执行之前和之后执行

▪ 方法必须是静态的

▪ 不接受任何参数

▪ Order定义执行顺序



## 3、Step参数管理

### 3.1 简单数据

3.1.1 cucumber几种常见的数据类型（直接输入型）：

{int}：匹配整型数据，例如71或-19

{float}:  匹配浮点型数字，如3.6

{word}：匹配没有空格的字符，如banana（banana split 带有空格，不能匹配）

{string}：匹配字符串，例如“banana split”

{} anonymous：匹配任意字符（/.*/等）



3.1.2 cucumber-JVM-additions（JVM自带类型）

在JVM上，还有biginteger、bigdecimal、byte、short和double的附加参数类型。

匿名参数类型将使用对象映射器转换为步骤定义的参数类型。

cucumber自带一个内置的对象映射器，可以处理大多数基本类型。除了Enum，它支持转换到BigInterger，BigDecimal

**例如：**

```java
I have a {color} ball

@ParameterType("red|blue|yellow")    // regexp 需要使用正则表达式
public Color color(String color){    // type,name(from method)
    return new Color(color)          // transformer function
}


```



3.1.3 Custom Parameter types（自定义类型）

自定义类型。比如自定义一种颜色的方法，然后再步骤定义中引入。



### 3.2 可选文本

可选文本：

```java
I have {int} cucumber(s) in my belly

//会匹配以下两种形式
I have 1 cucumber in my belly
I have 42 cucumbers in my belly
```



备选文本：

```java
I have {int} cucumber(s) in my belly/stomach

//会匹配这些文本中的任何一个
I have 42 cucumbers in my belly
I have 42 cucumbers in my stomach
```



转义字符：

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\efe427200423340035feb2e305536075ade3f403.png)



### 3.3 多行数据

 Doc String

Doc String 可以方便地将较大地文本传递给步骤定义

文本由三行双引号组成地分隔符分割：

```java
  Scenario: Multiline Step Arguments demo
    Given a blog post named "Random" with Markdown body
      """markdown
      Some Title, Eh?
      ===============
      Here is the first paragraph of my blog post. Lorem ipsum dolor sit amet,
      consectetur adipiscing elit.
      """
```



Data Tables 

Data Tables 可以方便地将值列表传递给步骤定义：

```java
    Given the following users exist:
      | name   | email              | twitter         |
      | Aslak  | aslak@cucumber.io  | @aslak_hellesoy |
      | Julien | julien@cucumber.io | @jbpros         |
      | Matt   | matt@cucumber.io   | @mattwynne      |
```





### 3.4 Cucumber Transformers

Cucumber 表达式参数（Parameters）、Data Tables和Doc Strings可以转换为任意的java对象。分为以下几种转换方式：

▪ Parament Type

▪ Data Table Type

▪ Empty Cells

▪ Transposing Tables

▪ Defaulr Transformers



feature & steps 代码示例：

```java
Feature: sample step arguments demo
  ... ...
  Scenario: for step
    Given I have 42 cucumbers in my belly
    Given I have a blue ball

  Scenario: Optional text demo
    Given I have 1 apple in my belly
    And I have 42 apples in my belly

  Scenario: Alternative text demo
    Given I have 1 banana in my belly
    Given I have 3 bananas in my stomach

  Scenario: Escaping demo
    Given I have 1 {what} beef in my belly (amazing!)


  Scenario: Multiline Step Arguments demo
    Given a blog post named "Random" with Markdown body
      """markdown
      Some Title, Eh?
      ===============
      Here is the first paragraph of my blog post. Lorem ipsum dolor sit amet,
      consectetur adipiscing elit.
      """

    Given the following users exist:
      | name   | email              | twitter         |
      | Aslak  | aslak@cucumber.io  | @aslak_hellesoy |
      | Julien | julien@cucumber.io | @jbpros         |
      | Matt   | matt@cucumber.io   | @mattwynne      |


  Scenario: Cucumber Transformers - Param Type
    Given today is 2021-11-19


  Scenario: Cucumber Transformers - Data Tables Type
#    Given a list of authors in a table
#      | firstName   | lastName | birthDate  |
#      | Annie M. G. | Schmidt  | 1911-03-20 |
#      | Roald       | Dahl     | 1916-09-13 |
#      | Alan        | Luo      |            |
#      | Clark       | Peng     |   [EmptyStr]  |
    Given a list of authors in a table
      | firstName   | Alan          | Clark  |
      | lastName    | Luo           | Peng |
      | birthDate   | 1986-09-17    | 1916-09-13 |


  Scenario: Cucumber Transformers - Default Transformers
    Given this is Json data: {firstName: 'alan',lastName: 'Luo',birthDate:'1986-09-17'}

```

```java
package behavior;

import entities.Author;
import entities.Color;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.Given;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class StepArgumentSteps {
    @Given("I have {int} cucumbers in my belly")
    public void i_have_cucumbers_in_my_belly(int number) {
        System.out.println(number);
    }
    @Given("I have a {color} ball")
    public void i_have_a_color_ball(Color color) {
        System.out.println(color.getColor());
    }

    @ParameterType("red|blue|yellow")
    public Color color(String color) {
        return new Color(color);
    }

    @Given("I have {int} apple(s) in my belly")
    public void i_have_apple_in_my_belly(int number) {
    }

    @Given("I have {int} banana(s) in my belly/stomach")
    public void i_have_banana_in_my_belly_stomach(int number) {
    }

    @Given("I have {int} \\{what} beef in my belly \\(amazing!)")
    public void i_have_beef_in_my_belly_amazing(int number) {
    }

    @Given("a blog post named {string} with Markdown body")
    public void a_blog_post_named_with_markdown_body(String name, String docString) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(docString);
    }

    @Given("the following users exist:")
    public void the_following_users_exist(DataTable dataTable) {
        System.out.println(dataTable.cell(1, 0));
    }

//    @Given("I have {float} cucumbers in my belly")
//    public void i_have_cucumbers_in_my_belly(float number) {
//        System.out.println(number);
//    }

//    @Given("I have {int} {word} in my belly")
//    public void i_have_word_in_my_belly(int number,String str) {
//        System.out.println(number);
//        System.out.println(str);
//    }

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDate isDate(String year, String month, String day) {
        return LocalDate.of(
                Integer.parseInt(year),
                Integer.parseInt(month),
                Integer.parseInt(day));
    }
    @Given("today is {isDate}")
    public void today_is(LocalDate date) {
        System.out.println("The date is:" + date.toString());
    }

    @DataTableType(replaceWithEmptyString = "[EmptyStr]")
    public  Author authorEntryTransformer(Map<String,String> entry) {
        Author author = new Author();
        author.setFirstName(entry.get("firstName"));
        author.setLastName(entry.get("lastName"));
        author.setBirthDate(entry.get("birthDate"));
        return author;
    }
    @Given("a list of authors in a table")
    public void a_list_of_authors_in_a_table(@Transpose List<Author> authors) {
        for (Author author : authors) // @Transpose 作用为将参数取值从行转换为列。
            System.out.println(String.format("author:[%s,%s,%s]", author.getFirstName(), author.getLastName(), author.getBirthDate()));
    }


    @Given("this is Json data: {}")
    public void this_is_json_data(Author author) {
        System.out.println(String.format("From Json author:[%s,%s,%s]", author.getFirstName(), author.getLastName(), author.getBirthDate()));
    }
}


```

Empty Cells

Gherkin中的数据表不能明确表示为空或者空字符串。

cucumber将空单元格解释为null。通过replaceWithEmptyString将null转换为空。避免空指针的问题。



Transposing Tables

通过使用@Transpose注解数据表参数（或数据表要转换成的参数）可以转换数据表。

```java
@ Transpose 作用为将参数取值从行改变为列（当表为纵向的表格时）。
   Scenario: Cucumber Transformers - Data Tables Type
#    Given a list of authors in a table
#      | firstName   | lastName | birthDate  |
#      | Annie M. G. | Schmidt  | 1911-03-20 |
#      | Roald       | Dahl     | 1916-09-13 |
#      | Alan        | Luo      |            |
#      | Clark       | Peng     |   [EmptyStr]  |
    Given a list of authors in a table
      | firstName   | Alan          | Clark  |
      | lastName    | Luo           | Peng |
      | birthDate   | 1986-09-17    | 1916-09-13 |
```



### 4.5 Default Transformers

默认transformer器允许我们指定在没有定义transformer时使用的transformer。

可以将字符串转换为json格式的对象进行传递。将字符串表示形式转换为java对象。

@DefaultParameterTransformer

@DefaultDataTableEntryTransformer

@DefaultDataTableCellTransformer

需要下载Gson相关的依赖包：[Maven Repository: com.google.code.gson » gson » 2.8.9](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.9)

behavior:

```java
package behavior;

import com.google.gson.Gson;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;

import java.lang.reflect.Type;

public class DataTableStepDefinitions {
    private final Gson gson = new Gson();

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object defaultTransformer(Object fromValue, Type toValueType) {
        return gson.fromJson((String)fromValue,toValueType);
    }
}
```

Feature:

```java
  Scenario: Cucumber Transformers - Default Transformers
    Given this is Json data: {firstName: 'alan',lastName: 'Luo',birthDate:'1986-09-17'}
```



## 4、状态管理



4.1 概念简述：



什么是状态管理？

* 框架程序维护对象生命周期方式

* T所有用例执行框架都提供了状态管理

*重要的是要防止由一个场景产生的状态泄露给其他场景。泄露的场景状态会使场景变得脆弱并难以单独运行。



防止在不同场景之间泄露状态：

* 避免使用全局或者静态变量。

* 确保在before Hook中清理数据库。

* 如果在不同场景建共享浏览器，请在before Hooks中删除cookie。

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\65f9a9cb652d824bb8b9b9223a785efd587da3a9.png)



如何实现在Step之间共享状态？

方式一：Cucumber提供了基础的支持是使用对象的成员变量传递状态。

               不推荐这样使用。因为必须要写在同一个类里面才能支持step之间的状态共享。

方法二：依赖注入 or World Object



4.2 Guice基础使用

cucumber支持的DI容器：

* PicoContanier

* Spring

* Guice

* OpenEJB

* Weld

* Needle

在使用DI框架时，所有步骤定义、钩子、转换器等都将由框架的实例注入器创建。





















**tips：**

1：新建feature文件时，文件后缀一定要加上feature。否则该文件不会被识别执行。

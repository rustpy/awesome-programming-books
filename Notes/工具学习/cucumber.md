# cucumber

## **BDD UI 自动化测试理念**

在说 BDD-UI-Testing 之前，我们先来看看 TDD、ATDD、BDD、DDD 这 4 个开发模式。

- TDD：测试驱动开发（Test-Driven Development）
- ATDD：验收测试驱动开发（Acceptance Test Driven Development）
- BDD：行为驱动开发（Behavior Driven Development）
- DDD：领域驱动开发（Domain Drive Design）



开发流程（W 模型）：

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\c047b7d7e5ab1b28b708f745730a5aa3bedfa78c.png)



BDD 流程：

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\3496397744d5d97a4508e5cac9e060323033bf4d.png)

采用 BDD 流程进行开发，由外而内，持续地描述当前系统或模块的行为，并为之实现自动化（即步骤定义）。当产品代码部分完成后，右侧的一系列测试活动都已经自动化了。

从层次上来说，BDD 是基于 TDD 的，或者说在自动化测试中，TDD 所在的位置比较底层，是基础，而 BDD 则是它的演进版本。

BDD 核心的是，开发人员、QA、非技术人员和用户都参与到项目的开发中，彼此协作。BDD 强调从用户的需求出发，最终的系统和用户的需求一致。BDD验证代码是否真正符合用户需求，因此 BDD 是从一个较高的视角来对验证系统是否和用户需求相符。



BDD交互流程：

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\7e3f6597d22ffecb7a9c7601631c75d897c2a44e.jpg)

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

























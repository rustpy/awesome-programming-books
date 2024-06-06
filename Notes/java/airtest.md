## 使用Airtest构建移动端自动化测试框架

### 课程目标：

- 入门移动端自动化测试
- 熟练使用Python结合AirTest进行移动端的自动化测试
- 基于开源代码做高级自定制
- 实现一个完整的项目框架。拿来即用

### 软件准备

- python3.6
- Pycharm
- AirtestIDE
- 安卓手机或者模拟器

# 阶段1

## 课程目标：

- 学会AirtestIDE的基本操作
- 能够使用Airtest录制自动化脚本

## 简介

### 安装

去官网http://airtest.netease.com/changelog.html下载最新安装包

![image-20201219143046568](https://woniumd.oss-cn-hangzhou.aliyuncs.com/test/zhangjing/20201219143046.png)

下载到本地后解压缩  **目录下别有中文**，最好直接解压到D盘下

![image-20201219143107278](https://woniumd.oss-cn-hangzhou.aliyuncs.com/test/zhangjing/20201219143107.png)

即可成功启动   启动成功后页面如下

![image-20201219143132400](https://woniumd.oss-cn-hangzhou.aliyuncs.com/test/zhangjing/20201219143132.png)

### 什么是Airtest

网易的airtest其实是个测试套件，由Airtest框架、poco框架、airtestIDE 组成。

#### Airtest框架

基于**图像识别的自动化测试框架**，是网易自己团队开发的。这个框架核心不在实现方式和技术上，而是理念！这个框架的祖宗是MIT（麻省理工）研究院的成果 [Sikuli](https://link.zhihu.com/?target=https%3A//baike.baidu.com/item/sikuli/9237768%3Ffr%3Daladdin) ，他们构思了一种全新的UI测试模式，基于图像识别控件而不是具体内存里的控件对象。理论上除了绿屏外，应该都能很好的识别并测试。

#### poco框架

网易自家的跨平台UI测试框架，原理类似appium，其实鹅厂也搞了个类似Poco的框架，叫做[GAutomator](https://link.zhihu.com/?target=https%3A//github.com/Tencent/GAutomator)，但无论是Poco还是GAutomator，他们也有个共同的祖宗，那就是xiaocong大大的[uiautomator](https://link.zhihu.com/?target=https%3A//github.com/xiaocong/uiautomator) for python，让用python调用uiautomator成为可能。

但是，xiaocong的uiautomator只能抓取原生android的控件树，抓不了其他游戏引擎的，所以Poco和GAutomator就多做了一件事情那就是给各个游戏引擎开发SDK，把控件树数据dump出来然后回传，这样我们才能够在他们的Inspector工具里看到游戏内的控件树。而这个所谓的SDK本质上就是一个TCPServer跑在游戏里。

所以**poco框架是用于抓取UI控件的**

#### airtestIDE 

这个就是完全网易自己家的东西，不开源的。IDE整合了airtest和poco两大框架，内置了Python3.6.5，本地无需安装python环境就能 **直接使用** 。提供了 adb工具、poco-inspector（抓ui控件）、设备屏录、图形化的脚本编辑器、便捷的ui截图工具等等一系列东西。已经很强大了，大大的提高了工作效率。

#### 总结

Airtest是网易出品的一款基于图像识别和poco控件识别的一款跨平台的UI自动化测试工具。适用于游戏和App(本质上就是网易自己为了给游戏做自动化测试开发出来的一套框架)。后期又渐渐支持Windows和Android平台，iOS平台。 Airtest提供了跨平台的API，包括安装应用、模拟输入、断言等。 基于图像识别技术定位UI元素， 测试脚本运行后可以自动生成详细的HTML测试报告，让你迅速定位失败的测试点。 AirtestIDE 是一个强大的GUI工具，可以帮助你录制和调试测试脚本。

### airtest和appium的区别

![image-20201219143154019](https://woniumd.oss-cn-hangzhou.aliyuncs.com/test/zhangjing/20201219143154.png)

**uiautomator2，appium, airtest几个工具浅析** 

https://www.jianshu.com/p/04a1faacc410

#### airtest优缺点

##### 优点

1.AirtestIDE操作比较简单，基本上不涉及到代码，所以非常适合刚入门没什么测试经验的人利用这个工具做UI自动化测试，同时Airetest又提供了开源的API，让**资深测试工程师可以基于Airtest的框架上再做高级的定制化扩展功能**。

2.基于图像识别和UI控件识别功能简单

3.支持python进行个性化脚本编程

4.可录制脚本一键生成报告

##### 缺点

1.如果经常使用图像识别 脚本会产生大量的图片，会让脚本整体观感不太好（个人意见）。

2.目前相关技术文档不多，需要自己去查看源代码分析或者去官网或社区探索。

## 1.AirtestIDE操作

### 1.airtest连接设备

手机开启调试模式  连接电脑  一路允许安装

#### 安装教程

https://airtest.doc.io.netease.com/IDEdocs/device_connection/1_android_phone_connection/

#### 常见问题

##### 1.未开启开发者选项 ，未允许USB调试，开发者选项内 打开禁止权限监控

##### 2.未安装Yosemite及PocoService

  https://blog.csdn.net/AirtestProject/article/details/108493205

Yosemite的作用：https://mp.weixin.qq.com/s/LnzToiXFVcfkeOGz8Vz9Pw

##### 3.poco无限重启的解决办法

① 如果开了网络代理的话，需要先 **关闭各种代理和VPN** ，否则可能会影响到poco通讯

② 检查手机助手内是否对 `pocoservice.apk` 做了限制，例如在某版本的华为手机中需要开启 **允许自启动** 和 **允许后台活动**

https://airtest.doc.io.netease.com/IDEdocs/device_connection/2_android_faq/#_6

③ 不能和uiautomator同时启动，否则会相互冲突

④ 可以尝试 **重启手机** 看看是否会恢复

##### 4.无法在密码框中输入密码

部分厂商（例如华为、VIVO等）的某些型号手机限制了密码框的输入，强制在输入密码时必须使用系统键盘输入。这样会导致需要输入密码时，直接使用`airtest`的`text()`会无法输入内容，需要把以下选项打开后才能正常输入密码内容：

![image-20201219143212898](https://woniumd.oss-cn-hangzhou.aliyuncs.com/test/zhangjing/20201219143212.png)

### 2.录制.py脚本

因为我们都是已经学过Python，熟悉Python语言的高级用户，所以我们这边直接新建py脚本。也方便我们后期整合到pycharm搭建自动化框架。

#### 2.1新建脚本

![](https://woniumd.oss-cn-hangzhou.aliyuncs.com/test/zhangjing/20201219143229.png)

**建议新建以一个要测的项目模块名称去新建一个文件夹，如：首页模块就建一个index文件夹，然后在文件夹内新建你的脚本**

**为什么要这样：主要是后期如果使用到截图功能和产出日志都会在同级文件下去产生图片和日志文件夹。如果不这样做，后期要测内容一多，所有模块log都在同一个文件夹下，图片也在同一级目录下**

![image-20201219143255240](https://woniumd.oss-cn-hangzhou.aliyuncs.com/test/zhangjing/20201219143255.png)

### 3.常用操作

#### 3.1airtestIDE常用操作

##### 1.通过安卓助手获取包名

##### 2.通过设置获取坐标

#### 3.2airtest框架常用操作

https://airtest.readthedocs.io/zh_CN/latest/all_module/airtest.core.api.html

#### 3.3poco框架常用操作

基于屏幕操作  https://poco-chinese.readthedocs.io/zh_CN/latest/source/poco.pocofw.html

基于元素对象操作   https://poco-chinese.readthedocs.io/zh_CN/latest/source/poco.proxy.html

https://juejin.im/post/6844904150728179725

#### 3.4扩展知识

##### 1.Airtest图像识别算法

airtest框架中集成了不同种类的图像识别算法。 其中包括模板匹配（TemplateMatching）以及基于特征点的图像识别方法（SURFMatching和BRISKMatching）。这两种识别方法的特点和区别如下：

##### 模板匹配

- 无法跨分辨率识别
- 一定有相对最佳的匹配结果
- 方法名：`"tpl"`

##### 特征点匹配

- 跨分辨率识别
- 不一定有匹配结果
- 方法名列表：`["kaze", "brisk", "akaze", "orb", "sift", "surf", "brief"]`

在这里我们还需要解释一下：
 1.**无法跨分辨率识别**，意思是一旦换一台不同分辨率的设备就可能识别失败；
 2.一定有相对最佳的匹配结果，即是不管怎么说也会给你找一个结果出来，虽然可能差别很大。我们使用了当前设备不存在的截图让程序去查找，屏幕上根本没有结果，`TemplateMatching`算法也会找出了一个可信度为0.4的结果。

##### 2.如何安排连接设备、初始化poco和打开应用的脚本顺序

最正确的顺序是：**先连接设备（一般在 `auto_setup` 接口里面连接）--> 再打开应用（一般用 `start_app` 接口）--> 等应用开启完毕，最后才初始化 `poco`。**

可以有效避免一大堆奇妙的报错

##### 2.airtest封装好的adb命令 

https://mp.weixin.qq.com/s?__biz=MzUxMDc4NTkwMA==&mid=2247485333&idx=1&sn=213206aa7f6755d5099147d71dba5a5a&chksm=f97ce796ce0b6e80362033702a6d325c40ec911d50b4a074ca5f7c2c1474ad065acc4bc7ab0a&token=153670092&lang=zh_CN#rd

### 4. airtest脚本代码

```python
# -*- encoding=utf8 -*-
__author__ = "zj"

from airtest.core.api import *
from airtest.cli.parser import cli_setup
# 初始化 设备连接
if not cli_setup():
    auto_setup(__file__, logdir=True, devices=[
            "android://127.0.0.1:5037/954f932f?cap_method=JAVACAP&&ori_method=ADBORI&&touch_method=ADBTOUCH",
    ])

# 初始化 poco
from poco.drivers.android.uiautomation import AndroidUiautomationPoco
poco = AndroidUiautomationPoco(use_airtest_input=True, screenshot_each_action=False)

# 启动网易云app
start_app("com.netease.cloudmusic")    
# poco定位到 元素 点击
poco(text="每日推荐").click()
# 获取到页面上的实际日期
actual_day = poco("com.netease.cloudmusic:id/dayRecommendDateInfo").get_text()
# 获取到当前系统的真实日期
real_day=time.strftime("%d",time.localtime(time.time()))
# 断言
assert_equal(actual_day, real_day)
# 判断播放按钮是否存在
exist_bool = poco("com.netease.cloudmusic:id/playAllTextView").exists()
assert_equal(exist_bool, True,)
# 获取到列表页的第一首歌元素
ele = poco("android.widget.LinearLayout").offspring("com.netease.cloudmusic:id/pagerListview").child("com.netease.cloudmusic:id/musicListItemContainer")[0].offspring("com.netease.cloudmusic:id/songName")
# 获取到歌名
song_actual_name = ele.get_text()
# 判断列表页下方是否有歌曲 有的话 点击歌名会进入详情页 需要在返回到当前页面
if poco("android.widget.LinearLayout").offspring("com.netease.cloudmusic:id/musicName").exists:
    ele.click()
    keyevent("BACK")
else:
    # 没有下方内容的话直接点击 播放就行
    ele.click()
# 判断播放按钮是否存在
assert_exists(Template(r"tpl1608535893330.png", record_pos=(0.315, 1.016), resolution=(1080, 2340)), "判断是否播放成功")
# 获取到下方播放的元素信息
ele2 = poco("android.widget.LinearLayout").offspring("com.netease.cloudmusic:id/musicName")
assert_equal(song_actual_name,ele2.get_text())

keyevent("BACK")
poco(text="私人FM").click()
assert_exists(Template(r"tpl1608536643149.png", record_pos=(-0.009, 0.954), resolution=(1080, 2340)), )
keyevent("BACK")
poco(text="歌单").click()
assert_exists(Template(r"tpl1608536677648.png", record_pos=(-0.094, -0.815), resolution=(1080, 2340)), "请填写测试点")
keyevent("BACK")
poco(text="排行榜").click()
assert_exists(Template(r"tpl1608536708310.png", record_pos=(-0.312, 0.242), resolution=(1080, 2340)), )
keyevent("BACK")

sleep(2)

stop_app("com.netease.cloudmusic")    
# # script content
# print("start...")

# generate html report
# from airtest.report.report import simple_report
# simple_report(__file__, logpath=True)
```

### 5. 常用总结

```python
"""
Airtest
AirtestIDE
airtest框架       图像识别
poco框架          元素定位
图像识别
模板匹配
    肯定有一个最佳的结果 
    不能跨分辨率
特征点匹配
    可能存在没有结果的情况
    能跨分辨率
airtest框架常用操作  涉及到坐标使用 绝对坐标
touch(v) 点击图片
exists(v)  判断图片存不存在 不存在返回 False 存在返回中心点坐标
wait(v)     等待图片出现   不出现会抛出异常，出现返回中心点坐标
text(text, enter=True,search=False)
snapshot(文件名) 截图
assert_exists(v,msg="") 断言图片存不存在
assert_equal(first, second,msg="") 断言两个元素是否一致
start_app(包名) 启动app
stop_app(包名) 关闭app
keyevent(事件代码)  执行指定的键盘事件
poco框架常用操作  涉及到坐标都可以使用 相对坐标
基于屏幕
    poco.click( (x,y) )  #点击屏幕 (x,y)处 
    poco.get_screen_size() 得到屏幕的分辨率 返回的是个 [wight,height]
    poco.scroll(方向,百分比，持续时间) 方向只能是 “vertical” or “horizontal”  百分比(-1,1)
    obj = poco(属性=属性值)  获取对象
    obj.click()  对具体的元素进行点击
    obj.attr(属性名)  获取对象对应属性名的属性值
    obj.setattr(属性名,属性值)  修改对象属性名 为指定属性值
    obj.exists()  判断元素存不存在  返回布尔值
"""
```

# 阶段2

## 课程目标

- 项目目录结构搭建
- 配置的概念
- 使用pycharm写airtest相关脚本

## 1.项目框架雏形实现

### 1.1目录结构搭建

#### 1.Base    存放公共方法文件

#### 2.Logs	存放日志数据，错误截屏图片

#### 3.TestCase 存放测试用例 

以每个大模块做文件夹  文件夹下存放每个用例的实现逻辑

#### 4.TestData   存放测试数据

跟TestCase相对应， 根据TestCase目录架构 路径编写

#### **5.TestSuite**  **存放测试套件 组装测试用例**

#### 6.Report	存放测试报告

### 1.2 构建配置

我们的目标是实现一个完整的项目框架。拿来即用但是项目中存在许多的文件路径，这些都是基于我们本地文件的路径，当我们把项目发给别人时。别人电脑上路径未必跟我们一致，这时候我们的项目就会产生问题。所以我们需要知道一个配置的概念

在Base目录下新建一个BaseSettings.py 用来专门存放我们的基本配置

```python
import os
# 项目的根目录 获取当前文件的上一级的上一级目录
BaseDIR = os.path.dirname(os.path.dirname(__file__))
# 日志文件夹
LogsDIR = os.path.join(BaseDIR,"Logs\\")
# 报告文件夹
ReportDIR = os.path.join(BaseDIR,"Report\\")
# 测试数据文件夹
TestDataDIR = os.path.join(BaseDIR,"TestData\\")
```

配置构建完成后原本的代码中的路径信息就可以全部替换成配置变量了

### 1.3 项目集成unittest

#### 1.为什么要使用unittest：

1)能够组织多个用例去执行

2)提供丰富的断言方法

3)可以使用UnitTest来管理测试用例

4)已经实现了很多基本功能避免重复造轮子

#### 2.UnitTest四大核心要素

##### 1.TestCase

```
一个TestCase的实例就是一个测试用例。什么是测试用例呢？就是一个完整的测试流程，包括测试前准备环境的搭建(setUp)，执行测试代码 (run)，以及测试后环境的还原(tearDown)。元测试(unit test)的本质也就在这里，一个测试用例是一个完整的测试单元，通过运行这个测试单元，可以对某一个问题进行验证。
使用：
    1. 导包：import unittest             --> 导入unitest框架
    2. 继承：unittest.TestCase             --> 新建测试类继承unittest.TestCase
提示：
    1). 测试用例：在自动化测试中，一条用例就是一个完整的测试流程；                
    2). 测试方法名称命名必须以test开头；
       (原因：unittest.TestCase类批量运行的方法是搜索执行test开头的方法)
```

##### 2.TestSuite

```
多个测试用例集合在一起，就是TestSuite，而且TestSuite也可以嵌套TestSuite。
使用：
    1. 实例化：     suite=unittest.TestSuite()                    
                 (suite：为TestSuite实例化的名称)
    2. 添加用例：
    TestCases = unittest.TestLoader().loadTestsFromModule(module)  从模块 py文件 中加载所有测试方法    参数py文件名
	TestCases = unittest.TestLoader().loadTestsFromTestCase(classname)     从类中添加  这个类中的所有的测试方法   参数类名，类必须继承unittest.TestCase
	TestCases = unittest.TestLoader().loadTestsFromName(Name)     加载某个单独的测试方法 必须是字符串   “module.class.def”
	TestCases = unittest.TestLoader().loadTestsFromNames()      加载某个单独的测试方法 必须是列表   [“module.class.def1”,“module.class.def2”]
    3.执行    
    suite.addTest(TestCases)
    runner=unittest.TextTestRunner()  1. 实例化：(runner：TextTestRunner实例化名称)
	runner.run(suite)           2. 执行： (suite：为测试套件名称)
提示：
    1). 一条测试用例(.py)内，多个方法也可以使用测试套件
    2). TestSuite需要配合TextTestRunner才能被执行
```

##### 3.TestRunner

```
是来执行测试用例的
使用：
    1. 实例化： runner=unittest.TextTestRunner()
                (runner：TextTestRunner实例化名称)
    2. 执行：    runner.run(suite)
                (suite：为测试套件名称)
```

##### 4.Fixture

```
对一个测试用例环境的搭建和销毁，是一个fixture，通过覆盖 TestCase的setUp()和tearDown()方法来实现。这个有什么用呢？比如说在这个测试用例中需要访问数据库，那么可以在setUp() 中建立数据库连接以及进行一些初始化，在tearDown()中清除在数据库中产生的数据，然后关闭连接。注意tearDown的过程很重要，要为以后的 TestCase留下一个干净的环境。关于fixture，还有一个专门的库函数叫做fixtures，功能更加强大。
使用：
    1. 初始化(搭建)：def setUp(self)        --> 首先执行
       (setUp:此方法继承于unittest.TestCase)        
    2. 结束(销毁):    def tearDown(self)        --> 最后执行
       (tearDown:此方法继承于unittest.TestCase)
    
提示：
    1. 必须继承unittest.TestCase类，setUp、tearDown才是一个Fixture；
    2. setUp：一般做初始化工作，比如：实例化浏览器、浏览器最大化、隐式等待设置
    3. tearDown：一般做结束工作，比如：退出登录、关闭浏览器
    4. 如果一个测试类有多个test开头方法，则每个方法执行之前都会运行setUp、结束时运行tearDown
```

## 2.pycharm编写airtest脚本

### 2.1 安装相关环境

**pip install airtest  -i https://mirrors.aliyun.com/pypi/simple/ **

**pip install pocoui**

#### 2.1.1常见问题

##### 1. 在 **`cv2`** 模块报 `ImportError: DLL load failed: 找不到指定模块` 的错：

**根本原因应该是DLL文件的缺失**，你可以直接下载一个最新版本的AirtestIDE，在解压后的目录中找到 `api-ms-win-downlevel-shlwapi-l1-1-0.dll` 和 `IEShims.dll` 两个DLL文件（或者自行在网上搜索这俩个DLL文件也是可以的），然后将它们复制到 `C:\Windows\System32` 目录，重新运行代码即可解决。

![image-20201219143314008](https://woniumd.oss-cn-hangzhou.aliyuncs.com/test/zhangjing/20201219143314.png)

##### 2.在 **`win.py`** 中 `import win32api` 时报 `DLL load failed` 

建议您运行下列指令，更新为223版本的 `pywin32`：

```python
pip uninstall pywin32
pip install pywin32==223
```

### 2.2 airtest脚本移植到python

因为我们在新建脚本时就是采用的py脚本。里面使用的语法就是Python语法。所以我们可以直接把文件内容粘贴复制到Python运行

### 2.3 如何取消脚本执行过程刷新的大量log信息

你可以在脚本代码开头加上log级别的设定：

```python
# -*- encoding=utf8 -*-
import logging
logger = logging.getLogger("airtest")
logger.setLevel(logging.ERROR)
# 日志级别有[DEBUG]、[INFO]、[WARNING] 和 [ERROR]
```

### 2.4 自动化报告的生成

我们可以对文件中下面的代码取消注释来生成报告

```python
from airtest.report.report import simple_report
simple_report(__file__, logpath=True)
```

这个方法就是airtest用来生成报告的方法

但是如果我们只是单纯的取消注释，仍然会有一些问题。下面我们就重点分析下 初始化函数auto_setup()和报告生成函数simple_report()

# 阶段3

## 课程目标

- 简单的源代码分析和重写

## 1.项目存在问题

1. 各个case互相存在关联性 
2. 报告没有独立 

## 2. 源码分析

### 2.1 auto_setup() 初始化方法

重点来看下这段初始化代码

```python
from airtest.core.api import *
from airtest.cli.parser import cli_setup
"""
cli_setup()意义：
当在命令行使用 `python xxx.py` 来运行本文件，且不带任何命令行参数时，则自动使用 `auto_setup` 这个接口来对 Airtest 相关的参数进行初始化。这样只需要在写 `.py` 脚本时，填写好期望的参数就能直接用 `python xxx.py` 指令来运行脚本。
如果是 python xxx.py --device xx --log xx 这种带命令行参数的，只要脚本检测到传入了命令行参数，就依然优先使用命令行参数来初始化 Airtest 相关的参数。
"""
if not cli_setup():
    auto_setup(__file__, logdir=True, devices=[
            "Android://127.0.0.1:5037/HAHMHYQC6HZ9Q8HQ",
    ])
```

#### auto_setup()里面的logdir和devices

① 设置脚本运行时的log保存路径--`logdir`

`logdir` 的值必须为 `True` 或者是指定的具体保存路径。如果 `logdir` 的值为 `None` 的话，则默认在运行脚本时不保存log内容，也就是最终不能生成报告。

如果将 `logdir` 的值设置为 `True` ，则表示将log内容保存在默认为 `.py` 文件所在目录下的 log/ 目录：

![image-20201219143330116](https://woniumd.oss-cn-hangzhou.aliyuncs.com/test/zhangjing/20201219143330.png)



如果填写具体的保存路径，比如 `E:/test/log`，此时log内容就会被保存在指定目录下：

② 连接设备参数--`devices`

在新建 `.py` 脚本时，这个 `devices` 参数一定要加上，你可以使用当前IDE已连接的设备进行初始化，也可以建完脚本之后，自己再手动添加上。

如果没有在 `.py` 脚本中添加这个连接设备的参数，那么脚本内关于Airtest或者Poco的脚本将无法正常执行

### 2.2 simple_report()  生成报告方法

因 `simple_report()` 方法在 `airtest.report.report` 模块中，所以使用之前，我们需要把这个方法引入进来：

```python
# generate html report
from airtest.report.report import simple_report
simple_report(__file__)
```

如果初始化的时候，`logdir` 的值为 `True` ，即log内容被保存在 `.py` 文件的log/目录下，则我们可以直接使用 `simple_report(__file__)` ，无需其它参数，即可帮助我们在 `.py` 文件所在目录生成脚本运行的报告 log.html

如果初始化的时候，`logdir` 的值为指定目录，则在 `simple_report(__file__)` 里面我们还需要加上以下参数：

```python
simple_report(__file__,logpath="E:/test/log",output="E:/test/log/log.html")
```

因为初始化的时候，我们把脚本保存目录用 `logdir` 参数指定为"E:/test/log"，则在生成报告的时候，我们也要去找这个地址的log.txt，所以需要将 `logpath` 指定为"E:/test/log"。

output参数是报告文件指定输出地址，如果没有指定 `output` 地址的话，将默认在 `.py` 脚本所在的目录下生成log.html

### 2.3 源码详情

```python
# 初始化 的 方法
auto_setup(__file__, logdir=r'D:\Pycharm\PythonProject\woniu33app\V2\logtest', devices=[
            "android://127.0.0.1:5037/954f932f?cap_method=JAVACAP&&ori_method=ADBORI&&touch_method=ADBTOUCH",
    ])
# __file__ 固定写法 表示文件路径
# logdir 填写日志要保存的路径
# devices 列表 存放设备信息

# 生成报告的方法
simple_report(__file__, logpath=r'D:\Pycharm\PythonProject\woniu33app\V2\logtest',output="testnavbar.html")
# __file__ 固定写法 表示文件路径
# logpath 表示日志存放的地址 目的是为了要从该地址读取日志信息
# output 报告输出地址
```

### 2.4 实现每个case有对应的报告的方案

#### 思路：

**每个case要产生自己的报告  那么应该在初始化的时候 有一个单独的文件夹存放自己运行过程中产生的日志数据，等待生成报告的时候在读取该文件夹渲染到指定报告文件**

#### 解决方案：

- 初始化方法auto_setup() 里的logdir 参数决定了你case运行过程中产生的日志存储都哪里，所以只要指定logdir为一个你想要存储的路径地址即可
- simple_report()内的logpath参数 决定生成的报告上的日志数据从哪读取，所以只要将**该参数的值设置的跟logdir参数一致**即可。存到哪里就从哪里读取。
- simple_report()内的output参数  决定报告输出到哪里。

![image-20210105122331364](https://woniumd.oss-cn-hangzhou.aliyuncs.com/test/zhangjing/20210105122331.png)

### 2.5 重写auto_steup()

#### 目的：

**为了让每个case有自己的报告，那么每个函数都需要调用一次auto_steup()，但是auto_steup()里面还包含了连接操作，这样做意味中调用一次函数就连接一次。这显然是不合理的。所以我们需要重写auto_setup()**

#### 思路：

**原本的auto_steup()实现了建立连接和创建日志文件夹两个功能。那我们可以模仿他的代码，进行重写。重写两个方法一个就是建立连接一个就是创建日志文件夹**

```python
# -*- coding: utf-8 -*-
"""
@author: ZJ
@email: 1576094876@qq.com
@File : Airtestlib.py
@desc: 用来存放修改过的airtest代码
@Created on: 2021/1/5 15:41
"""
import os
import shutil

from airtest.core.api import connect_device
from airtest.core.helper import G
from airtest.core.settings import Settings as ST

def only_auto_setup(basedir=None, devices=None, project_root=None, compress=None):
    """
    单纯的建立设备连接
    """
    if basedir:
        if os.path.isfile(basedir):
            basedir = os.path.dirname(basedir)
        if basedir not in G.BASEDIR:
            G.BASEDIR.append(basedir)
    if devices:
        for dev in devices:
            connect_device(dev)
    if project_root:
        ST.PROJECT_ROOT = project_root
    if compress:
        ST.SNAPSHOT_QUALITY = compress

def only_set_logdir(logdir):
    if os.path.exists(logdir):
        shutil.rmtree(logdir)  # 这一段代码优化了airtest的逻辑  这样每次执行case会删除历史的log信息，不会存在大量图片累积的情况
    os.mkdir(logdir)
    ST.LOG_DIR = logdir
    G.LOGGER.set_logfile(os.path.join(ST.LOG_DIR, ST.LOG_FILE))
```

## 3. 新的问题 

**case过程中即使报错，也要产出报告，并指出错误原因**

### 思路:

即使程序报错也要执行报告生成的方法    捕获异常

### 解决方案：

**try   execpt else  finally**

### 捕获异常后程序出错报告显示依旧是通过

### 解决方案： 在异常内使用log()方法

```python
"""
def log(arg, timestamp=None, desc="", snapshot=False):
log()支持4个参数：
args， 可以是字符串或是 traceback 对象，现在还支持传入非字符串，并且对py2做了一下兼容。假如传入的是 traceback 对象，将会自动在报告中标记为报错步骤，否则就是显示正常的 log 内容。
timestamp， 参数可以自定义当前这条 log 的时间戳，默认为当前时间（在记录一些长时间的回调中获取到的 log 时，原本默认使用写入本条 log 的时间，但是可能需要修改为 log产生的时间，比如几分钟前）
desc , 自定义一个 log 标题，在报告中有更好的展示效果
snapshot， 是否需要截取一张当前的屏幕图像并显示到报告中，方便查看。（同时，因为这个参数的加入，现在airtest脚本如果因为执行Poco语句失败报错而终止时，能够额外截取一张当前画面，方便大家排查问题。）
"""
```

# 阶段4

## 课程目标

- unittest套件的执行
- 熟练使用装饰器优化代码

## 1. 装饰器优化代码

在我们的每个case中，会发现有很多的重复操作 如建立日志文件夹，都需要异常捕获，然后生成报告这些操作。那这些操作我们可以使用装饰器来优化我们的代码。 讲装饰器前我们需要搞懂闭包的概念

### 1.1 闭包

```python
def test1():
    print("--- in test1 func----")

# 调用函数
test1()

# 引用函数
ret = test1
# 不写括号本质就是个引用 而没有调用
print(id(ret))
print(id(test1))

#通过引用调用函数
ret()
```

**总结：和变量名一样的**，**函数名只是函数代码空间的引用，当函数名赋值给一个对象的时候 就是引用传递**。

#### 闭包的简单实现

```python
# 定义一个函数
def test(number):

    # 在函数内部再定义一个函数，并且这个函数用到了外边函数的变量，那么将这个函数以及用到的一些变量称之为闭包
    def test_in(number_in):
        print("in test_in 函数, number_in is %d" % number_in)
        return number+number_in
    # 其实这里返回的就是闭包的结果
    return test_in


# 给test函数赋值，这个20就是给参数number
ret = test(20)

# 注意这里的100其实给参数number_in
print(ret(100))

#注 意这里的200其实给参数number_in
print(ret(200))


ret1 = test(10)
ret2 = test(20)

print(ret1(100))
print(ret2(100))


```

**问题：闭包内部函数修改外部函数变量该怎么办？**

**解决方案：使用nonlocal关键字**

#### 闭包内部函数修改外部函数变量

```python
def counter(start=0):
    def incr():
        nonlocal start
        start += 1
        return start
    return incr

c1 = counter(5)
print(c1())
print(c1())

c2 = counter(50)
print(c2())
print(c2())

print(c1())
print(c1())

print(c2())
print(c2())
```

- **如果在闭包的内部函数中直接使用外部函数的变量时，不需要任何操作，直接使用就可以了。**
- **但是如果要修改外部变量的值，需要将变量声明为 nonlocal**
- **nonlocal声明的变量不是局部变量,也不是全局变量,而是外部嵌套函数内的变量。如果确定在程序要修改外部变量，那么建议将 nonlocal 写在内部函数的第一行。**
- **python引用变量的顺序：当前作用域局部变量->外层作用域变量->当前模块中的全局变量->python内置变量 。**

### 1.2 装饰器

```python
# 有已经实现的四个函数  
def test1():
    print('test1')

def test2():
    print('test2')

def test3():
    print('test3')

def test4():
    print('test4')

# 公司不同部门都会调用到这四个函数
# 现在需要在四个函数执行前 都要加一段验证程序：如：
def test1():
    #要验证的逻辑
    print('test1')

    
# 怎么优美的实现？
```

**使用函数**

```python
def verify():
    #要验证的逻辑
    pass

def test1():
    verify()
    print('test1')

def test2():
    verify()
    print('test2')

def test3():
    verify()
    print('test3')

def test4():
    verify()
    print('test4')
```

写代码要遵循`开放封闭`原则.规定**已经实现的功能代码不允许被修改，但可以被扩展**

如果遵循这个规则的话。那么就不允许在函数test1 、test2、test3、test4的内部进行修改代码

那么这个时候就得使用装饰器实现

```python
def outer(func):
    def inner():
        #验证逻辑
        func()
    return inner

@outer
def test1():
    print('test1')
    
@outer
def test2():
    print('test2')
    
@outer
def test3():
    print('test3')
    
@outer
def test4():
    print('test4')
    

# 运行逻辑
# 当执行到 @outer这一步时，其实 本质上 已经执行了outer这个函数，并将@outer下面的函数(这里是test1，test2，test3，test4)作为 outer函数的 参数。 
# 然后将 @outer函数 执行完的返回值 覆盖给@outer下面的函数
@outer   ===》   outer(test4)   返回值是  inner
def test4():    ===》 执行完上一步后 test4被覆盖成inner，所以走到这一步其实在执行inner了
    print('test4')
```

![image-20210106161335547](https://woniumd.oss-cn-hangzhou.aliyuncs.com/test/zhangjing/20210106161335.png)

#### 万能装饰器写法

装饰器的外函数会接收一个函数作为参数，这个函数在内函数内部执行，这个函数可以有参数也可以没有参数，可以有返回值也可以没有返回值。

所以装饰器也分为四类，无参无返回值、无参有返回值、有参无返回值、有参有返回值。是否有参数和返回值完全取决于被装饰的函数。

但是，我们写装饰器的目的就是用一个装饰器来装饰不同的函数，所以要考虑装饰器的通用性。我们通过可变参数来实现一种可以用来装饰任何函数的装饰器，万能装饰器。

```python
def outer(func):
    @wraps(func)
    def inner(*args, **kwargs):
        print("装饰器中args参数", args)
        print("装饰器中kwargs参数", kwargs)
        return func(*args, **kwargs)
    return inner

@outer  # test = outer(test)
def ctest(*args, **kwargs):
    print("被装饰函数args",args)
    print("被装饰函数kwargs",kwargs)

ctest(123, a=123)
print(ctest.__name__)
```

#### 当装饰器有参数时

```python
    def get_parameter(*dargs,**dkwargs):  # 工厂函数，用来接受@get_parameter里面的参数
        def outer(func):
            @wraps(func)
            def inner(*args, **kwargs):
                print("装饰器中args参数", args)
                print("装饰器中kwargs参数", kwargs)
                return func(*args, **kwargs)
            return inner
        return outer

    @get_parameter(10,2,3)  # test = outer(test)
    def ctest(*args, **kwargs):
        print("被装饰函数args", args)
        print("被装饰函数kwargs", kwargs)
```

#### 装饰器的常见应用场景

1. 引入日志
2. 函数执行时间统计
3. 执行函数前预备处理
4. 执行函数后清理功能
5. 权限校验等场景

##### 实现函数执行时间统计的装饰器

```python
import time

#定义装饰器
def time_calc(func)
	def wrapper (*args,**kargs):
		start_time=time.time()
		f=func(*args,**kargs)
		exec_time=time.time()-start_time
		return f
	return wrapper

#使用装饰器
@time_calc
def add(a, b):
	return a + b

@time_calc
def sub(a,b):
	return a-b
```

### 1.3 实际应用

```python
def get_parameter(logname):
    """
    装饰器接收参数
    :param logname:  日志文件夹名称
    :return:
    """
    def outer(func):
        def inner(self,*args, **kwargs):
            only_set_logdir(LogsDIR + logname)

            try:
                arg = func(self,*args, **kwargs)
            except Exception as e:
                print("18", e)
                # log(e, desc="描述信息", snapshot=True)
                log(e, snapshot=True)
                raise e
            finally:
                # D:/Pycharm/PythonProject/app33/V3\LogsFMlog
                simple_report(__file__, logpath=LogsDIR+logname, output=ReportDIR +logname+".html")
                while not self.poco(text="歌单").exists():
                    keyevent("BACK")
            return arg
        return inner
    return  outer
```

```python
    # 原来写法
    def test_songlist(self):
        only_set_logdir(LogsDIR+"songlistlog")
        try:
            self.poco(text="歌单").click()
            assert_exists(Template(PictureDIR+"tpl1609744968875.png", record_pos=(-0.044, -0.811), resolution=(1080, 2340)), )
            keyevent("BACK")
        except Exception as e:
            print("18", e)
            log(e, desc="描述信息", snapshot=True)
            raise e
        finally:
            simple_report(__file__, logpath=LogsDIR+"songlistlog", output=ReportDIR+"songlist.html")
            while not self.poco(text="歌单").exists():
                keyevent("BACK")
                
	# 使用装饰器写法    这样我们每个case只需要处理业务逻辑就可以了，建日志文件夹生成报告异常捕获那些功能统一放到装饰器中实现
    @get_parameter("song")
    def test_songlist(self):

        self.poco(text="歌单").click()
        assert_exists(Template(PictureDIR+"tpl1609744968875.png", record_pos=(-0.044, -0.811), resolution=(1080, 2340)), )
        keyevent("BACK")
```

## 2 套件执行

```python
# -*- coding: utf-8 -*-
"""
@author: ZJ
@email: 1576094876@qq.com
@File : testsuite.py
@desc: 
@Created on: 2020/12/22 12:09
"""

import unittest

suite=unittest.TestSuite()
TestCases = unittest.TestLoader().loadTestsFromModule("case文件名")
suite.addTest(TestCases)
runner=unittest.TextTestRunner()
runner.run(suite)
```

# 阶段5

## 课程目标

- 学会用BeautifulReport生成测试报告
- 学会重写BeautifulReport源码实现高级自定制
- 实现报告整合

## 1.BeautifulReport的使用

```python
# pip install BeautifulReport==0.1.3   安装指定版本BeautifulReport
from BeautifulReport import BeautifulReport  # 导入
# 实例化套件对象
suite = unittest.TestSuite()
# 从指定文件里 拿取所有的testcase
testcases = unittest.TestLoader().loadTestsFromModule(TestFind)

# 把所有的case添加到套件
suite.addTest(testcases)

# 使用 BeautifulReport生成报告
runner = BeautifulReport(suite)  
runner.report("测试报告","report.html")
# 用HTMLTestRunner生成报告
# with open("result.html","wb") as f:
#     runner = HTMLTestRunner(f,)
#     runner.run(suite)

# 小知识：使用BeautifulReport如果有测试用例是跳过的 用例的跳过写法必须是@unittest.skip(原因) 而不能是@unittest.skip 不然BeautifulReport不能识别出该用例是个跳过用例
```

## 2.BeautifulReport高级功能自定制 

BeautifulReport的页面的确很beautiful，但是如果将这个页面和我们的airtest生成的报告结合起来？相对于BeautifulReport产出的只是一个目录页面，我们需要一个链接跳转到指定详情页面

### 2.1 源码逆推

```python
    def output_report(self, theme):
        """
            生成测试报告到指定路径下
            theme:主题名
        :return:
        """

        def render_template(params: dict, template: str):
            for name, value in params.items():
                name = '${' + name + '}'
                template = template.replace(name, value)
            return template

        # 模板目录 及 template文件夹
        template_path = self.config_tmp_path
        with open(os.path.join(self.template_path, theme + '.json'), 'r') as theme:
            render_params = {
                # 将主题文件里面的json数据读取 转化成键值对形式
                **json.load(theme),
                # 将self.fields 的数据转化成json字符串  self.fields存放的就是 case执行过程中产生的数据记录
                'resultData': json.dumps(self.fields, ensure_ascii=False, indent=4)
            }
        # 拿到最后输出报告的前面目录文件夹
        override_path = os.path.abspath(self.report_dir) if \
            os.path.abspath(self.report_dir).endswith('/') else \
            os.path.abspath(self.report_dir) + '/'
        # 读取 报告的模板文件内容
        with open(template_path, 'rb') as file:
            body = file.read().decode('utf-8')
        with open(override_path + self.filename, 'w', encoding='utf-8', newline='\n') as write_file:
            # 渲染报告  得到html内容 完成写入输出
            html = render_template(render_params, body)
            write_file.write(html)
```

那么这一步是拿到数据去做渲染 数据也就是 self.fields里面的值。那么self.fields值哪里来

```python
    def stopTestRun(self, title=None) -> dict:
        """
            所有测试执行完成后, 执行该方法
        :param title:
        :return:
        """
        # 得到所有成功数量
        self.fields['testPass'] = self.success_counter
        # 遍历 得到每个case执行结果  所以每个case的结果在self.result_list
        for item in self.result_list:
            # 涉及到对 对象使用str方法
            item = json.loads(str(MakeResultJson(item)))
            self.fields.get('testResult').append(item)
        self.fields['testAll'] = len(self.result_list)
        self.fields['testName'] = title if title else self.default_report_name
        self.fields['testFail'] = self.failure_count
        self.fields['beginTime'] = self.begin_time
        end_time = int(time.time())
        start_time = int(time.mktime(time.strptime(self.begin_time, '%Y-%m-%d %H:%M:%S')))
        self.fields['totalTime'] = str(end_time - start_time) + 's'
        self.fields['testError'] = self.error_count
        self.fields['testSkip'] = self.skipped
        return self.fields

# 那么 self.result_list的结果哪里来？
    def stopTest(self, test) -> None:
        """
           每个测试用例执行完成后进行调用
        :return:
        """
        self.end_time = '{0:.3} s'.format((time.time() - self.start_time))
        # 这一步self.result_list拿到test的结果
        self.result_list.append(self.get_all_result_info_tuple(test))
        self.complete_output()
    
    def get_all_result_info_tuple(self, test) -> tuple:
        """
            接受test 相关信息, 并拼接成一个完成的tuple结构返回
        :param test:
        :return:
        """
        # 返回的是一个元祖
        return tuple([*self.get_testcase_property(test), self.end_time, self.status, self.case_log])
    
    @staticmethod
    def get_testcase_property(test) -> tuple:
        """
            接受一个test, 并返回一个test的class_name, method_name, method_doc属性 返回每个test的属性
        :param test:
        :return: (class_name, method_name, method_doc) -> tuple
        """
        class_name = test.__class__.__qualname__
        method_name = test.__dict__['_testMethodName']
        method_doc = test.__dict__['_testMethodDoc']
        return class_name, method_name, method_doc

    
#  涉及到zip用法以及字典setdefault以及repr魔法方法
class MakeResultJson:
    """ make html table tags """

    def __init__(self, datas: tuple):
        """
        init self object
        :param datas: 拿到所有返回数据结构
        """
        self.datas = datas
        self.result_schema = {}

    def __repr__(self) -> str:
        """
            返回对象的html结构体
        :rtype: dict
        :return: self的repr对象, 返回一个构造完成的tr表单
        """
        keys = (
            'className',
            'methodName',
            'description',
            'spendTime',
            'status',
            'log',
        )
        for key, data in zip(keys, self.datas):
            self.result_schema.setdefault(key, data)
        return json.dumps(self.result_schema)
```

### 2.2 源码重写

#### 2.2.1 Python部分源码的重写

我们需要改变的其实就是字段的新增以及新增字段的显示在Base目录下新增BeautifulLib.py文件

里面的内容记录的是对BeautifulReport源码的一个修改

```python
# -*- coding: utf-8 -*-
"""
@author: ZJ
@email: 1576094876@qq.com
@File : BeautifulLib.py
@desc: 
@Created on: 2020/12/22 18:15
"""
import base64
import json
import os
import platform
import sys
import time
from functools import wraps

from BeautifulReport.BeautifulReport import ReportTestResult, BeautifulReport

HTML_IMG_TEMPLATE = """
    <a href="data:image/png;base64, {}">
    <img src="data:image/png;base64, {}" width="800px" height="500px"/>
    </a>
    <br></br>
"""
class PATH:
    """ all file PATH meta """
    template_path = os.path.join(os.path.dirname(__file__), 'template')
    config_tmp_path = os.path.join(template_path, 'template.html')


class MakeResultJson:
    """ make html table tags """

    def __init__(self, datas: tuple):
        """
        init self object
        :param datas: 拿到所有返回数据结构
        """
        self.datas = datas
        self.result_schema = {}

    def __setitem__(self, key, value):
        """

        :param key: self[key]
        :param value: value
        :return:
        """
        self[key] = value

    def __repr__(self) -> str:
        """
            返回对象的html结构体
        :rtype: dict
        :return: self的repr对象, 返回一个构造完成的tr表单
        """
        keys = (
            'className',
            'methodName',
            'description',
            'html_path',
            'start_time_str',
            'spendTime',
            'status',
            'log',
        )
        for key, data in zip(keys, self.datas):
            self.result_schema.setdefault(key, data)
        return json.dumps(self.result_schema)



class DIYReportTestResult(ReportTestResult):
    @staticmethod
    def get_testcase_property(test) -> tuple:
        """
            接受一个test, 并返回一个test的class_name, method_name, method_doc属性
        :param test:
        :return: (class_name, method_name, method_doc) -> tuple
        """
        class_name = test.__class__.__qualname__
        method_name = test.__dict__['_testMethodName']
        method_doc = test.__dict__['_testMethodDoc']
        html_path = test.__dict__['_html_path']
        start_time_str= test.__dict__['_start_time_str']
        return class_name, method_name, method_doc,html_path,start_time_str

    def stopTestRun(self, title=None) -> dict:
        """
            所有测试执行完成后, 执行该方法
        :param title:
        :return:
        """
        # 得到所有成功数量
        self.fields['testPass'] = self.success_counter
        # 遍历 得到每个case执行结果
        for item in self.result_list:
            item = json.loads(str(MakeResultJson(item)))
            self.fields.get('testResult').append(item)
        self.fields['testAll'] = len(self.result_list)
        self.fields['testName'] = title if title else self.default_report_name
        self.fields['testFail'] = self.failure_count
        self.fields['beginTime'] = self.begin_time
        end_time = int(time.time())
        start_time = int(time.mktime(time.strptime(self.begin_time, '%Y-%m-%d %H:%M:%S')))
        self.fields['totalTime'] = str(end_time - start_time) + 's'
        self.fields['testError'] = self.error_count
        self.fields['testSkip'] = self.skipped
        return self.fields


class DIYBeautifulReport(DIYReportTestResult,PATH):
    img_path = 'img/' if platform.system() != 'Windows' else 'img\\'

    def __init__(self, suites):
        super(DIYBeautifulReport, self).__init__(suites)
        self.suites = suites
        self.report_dir = None
        self.title = '自动化测试报告'
        self.filename = 'report.html'

    def report(self, description, filename: str = None, report_dir='.', log_path=None, theme='theme_default'):
        """
            生成测试报告,并放在当前运行路径下
        :param report_dir: 生成report的文件存储路径
        :param filename: 生成文件的filename
        :param description: 生成文件的注释
        :param theme: 报告主题名 theme_default theme_cyan theme_candy theme_memories
        :return:
        """
        if log_path:
            import warnings
            message = ('"log_path" is deprecated, please replace with "report_dir"\n'
                       "e.g. result.report(filename='测试报告_demo', description='测试报告', report_dir='report')")
            warnings.warn(message)

        if filename:
            self.filename = filename if filename.endswith('.html') else filename + '.html'

        if description:
            self.title = description

        self.report_dir = os.path.abspath(report_dir)
        os.makedirs(self.report_dir, exist_ok=True)
        self.suites.run(result=self)
        self.stopTestRun(self.title)
        self.output_report(theme)
        text = '\n测试已全部完成, 可打开 {} 查看报告'.format(os.path.join(self.report_dir, self.filename))
        print(text)

    def output_report(self, theme):
        """
            生成测试报告到指定路径下
            theme:主题名
        :return:
        """

        def render_template(params: dict, template: str):
            for name, value in params.items():
                name = '${' + name + '}'
                template = template.replace(name, value)
            return template

        # 模板目录 及 template文件夹
        template_path = self.config_tmp_path
        with open(os.path.join(self.template_path, theme + '.json'), 'r') as theme:
            render_params = {
                # 将主题文件里面的json数据读取 转化成键值对形式
                **json.load(theme),
                # 将self.fields 的数据转化成json字符串  self.fields存放的就是 case执行过程中产生的数据记录
                'resultData': json.dumps(self.fields, ensure_ascii=False, indent=4)
            }
        # 拿到最后输出报告的前面目录文件夹
        override_path = os.path.abspath(self.report_dir) if \
            os.path.abspath(self.report_dir).endswith('/') else \
            os.path.abspath(self.report_dir) + '/'
        # 读取 报告的模板文件内容
        with open(template_path, 'rb') as file:
            body = file.read().decode('utf-8')
        with open(override_path + self.filename, 'w', encoding='utf-8', newline='\n') as write_file:
            # 渲染报告  得到html内容 完成写入输出
            html = render_template(render_params, body)
            write_file.write(html)

    @staticmethod
    def img2base(img_path: str, file_name: str) -> str:
        """
            接受传递进函数的filename 并找到文件转换为base64格式
        :param img_path: 通过文件名及默认路径找到的img绝对路径
        :param file_name: 用户在装饰器中传递进来的问价匿名
        :return:
        """
        pattern = '/' if platform != 'Windows' else '\\'

        with open(img_path + pattern + file_name, 'rb') as file:
            data = file.read()
        return base64.b64encode(data).decode()

    def add_test_img(*pargs):
        """
            接受若干个图片元素, 并展示在测试报告中
        :param pargs:
        :return:
        """

        def _wrap(func):
            @wraps(func)
            def __wrap(*args, **kwargs):
                img_path = os.path.abspath('{}'.format(BeautifulReport.img_path))
                os.makedirs(img_path, exist_ok=True)
                testclasstype = str(type(args[0]))
                # print(testclasstype)
                testclassnm = testclasstype[testclasstype.rindex('.') + 1:-2]
                # print(testclassnm)
                img_nm = testclassnm + '_' + func.__name__
                try:
                    result = func(*args, **kwargs)
                except Exception:
                    if 'save_img' in dir(args[0]):
                        save_img = getattr(args[0], 'save_img')
                        save_img(os.path.join(img_path, img_nm + '.png'))
                        data = BeautifulReport.img2base(img_path, img_nm + '.png')
                        print(HTML_IMG_TEMPLATE.format(data, data))
                    sys.exit(0)
                print('<br></br>')

                if len(pargs) > 1:
                    for parg in pargs:
                        print(parg + ':')
                        data = BeautifulReport.img2base(img_path, parg + '.png')
                        print(HTML_IMG_TEMPLATE.format(data, data))
                    return result
                if not os.path.exists(img_path + pargs[0] + '.png'):
                    return result
                data = BeautifulReport.img2base(img_path, pargs[0] + '.png')
                print(HTML_IMG_TEMPLATE.format(data, data))
                return result

            return __wrap

        return _wrap

if __name__ == '__main__':
    pass

```

**主要改动的地方在于以下两个函数**

```python
	@staticmethod
    def get_testcase_property(test) -> tuple:
        """
        支持新增字段的获取
        """
        class_name = test.__class__.__qualname__
        method_name = test.__dict__['_testMethodName']
        method_doc = test.__dict__['_testMethodDoc']
        html_path = test.__dict__.get('_html_path')  if test.__dict__.get('_html_path') else "#"
        start_time_str=test.__dict__.get('_start_time_str') if test.__dict__.get('_start_time_str') else None
        return class_name, method_name, method_doc,html_path,start_time_str

# 修改 MakeResultJson 里的__repr__ 使新增的字段能够正常压缩对应
class MakeResultJson:
    """ make html table tags """

    def __init__(self, datas: tuple):
        """
        init self object
        :param datas: 拿到所有返回数据结构
        """
        self.datas = datas
        self.result_schema = {}

    def __setitem__(self, key, value):
        """

        :param key: self[key]
        :param value: value
        :return:
        """
        self[key] = value

    def __repr__(self) -> str:
        """
            返回对象的html结构体
        :rtype: dict
        :return: self的repr对象, 返回一个构造完成的tr表单
        """
        keys = (
            'className',
            'methodName',
            'description',
            'html_path',
            'start_time_str',
            'spendTime',
            'status',
            'log',
        )
        for key, data in zip(keys, self.datas):
            self.result_schema.setdefault(key, data)
        return json.dumps(self.result_schema)
```

#### 2.2.2 报告模板页面的修改

上述代码是对数据采集进行修改 我们还需要在页面上渲染出我们修改的数据 即我们还需要修改我们的模板文件

```python
#template.html文件的7102行做个替换 如果 后期有新增字段 th新增一个列 
                          <th>编号</th>
                            <th>测试类</th>
                            <th>测试方法</th>
                            <th>用例描述</th>
                            <th>开始时间</th>
                            <th>运行时长</th>
                            <th>结果</th>
                            <th>操作</th>
                            <th>详情链接</th>

#template.html文件的7241行做个替换 如果 后期有新增字段 td新增一个列 
var tr = "<tr style='font-family: Consolas'>" +
                        "<td>" + (i + 1) + "</td>" +
                        "<td>" + n["className"] + "</td>" +
                        "<td>" + n["methodName"] + "</td>" +
                        "<td>"+n["description"] + "</td>" +
                        "<td>"+n["start_time_str"] + "</td>" +
                        "<td>" + n["spendTime"] + "</td>" +
                        status + "<td><button type='button' onclick='details(this)' buttonIndex='" + i + "' class='btn btn-primary btn-xs' style='margin-bottom: 0px'>展开</button></td>"
                        +"<td><a href='"+n["html_path"]+"'>详情链接</a></td>"+"</tr>"
```

# 阶段6

## 课程目标

- ddt数据驱动的使用
- 测试数据的封装
- 自定义装饰器 解决代码重复性使用

## 1. ddt的使用

一般进行接口测试时，每个接口的传参都不止一种情况，一般会考虑正向、逆向等多种组合。所以在测试一个接口时通常会编写多条case，而这些case除了传参不同外，其实并没什么区别。这个时候就可以利用ddt来管理测试数据，提高代码复用率。

DDT又叫数据驱动（Data-Driven Design），在Python ddt作为一个装饰器存在，用来实现数据的参数化，这样就可以将代码和测试数据分开，将代码进行封装，提高复用性。

### 代码

```python
# 知识点 ddt的使用  数据抽离 函数封装的思想  
    @ddt.data(*navbar_data_list)  # 数据放在数据层
    @ddt.unpack
    def test_navbar(self,descinfo,dirname,outputname,ele_loc,picture):
        self._testMethodDoc =descinfo
        only_set_log(Logs_DIR + dirname)
        try:
            self.poco(text=ele_loc).click()
            assert_exists(Template(TestData_PictureDIR+picture ) )
            keyevent("BACK")
        except Exception as e:
            print(e)
            log(e, desc="报错信息", snapshot=True)
        finally:
            simple_report(__file__, logpath=Logs_DIR+dirname,
                          output=Report_DIR+outputname)
            self._html_path = Report_DIR + outputname
```

```python
# 数据层
navbar_data_list = [
    {"descinfo":"私人FM",
     "dirname":"privateFM",
     "outputname":"privateFM.html",
     "picture":"tpl1608536643149.png",
     "ele_loc":"私人FM"
     },
    {"descinfo": "歌单",
     "dirname": "song_list",
     "outputname": "song_list.html",
     "picture": "tpl1608536677648.png",
     "ele_loc": "歌单"
     },
    {"descinfo": "排行榜",
     "dirname": "rank_list",
     "outputname": "rank_list.html",
     "picture": "tpl1608536708310.png",
     "ele_loc": "排行榜"
     }
]
```

## 2. ddt和装饰器结合使用

多个装饰器执行顺序 

https://blog.csdn.net/jyhhhhhhh/article/details/54627850?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control

多个装饰器的调用顺序是自下往上，但是运行时的执行顺序是自上往下！！！

装饰器的外层和内层执行顺序   其他文件调用时的影响结果

装饰器装饰类  https://zhuanlan.zhihu.com/p/59554577       类名 = ddt(类名)

ddt实现原理 http://m.elecfans.com/article/1308895.html

![image-20210108120017608](https://woniumd.oss-cn-hangzhou.aliyuncs.com/test/zhangjing/20210108120017.png)

```python
# 数据文件
TestNavBar_datalist=[
    # {
    #     "testMethodDoc": "每日推荐",
    #     "logdirname": "day_recommend",
    #     "eleloc": "每日推荐",
    # },
    # {
    #     "testMethodDoc":"私人FM",
    #     "logdirname":"privateFM",
    #     "eleloc":"私人FM",
    #     "picture":"tpl1608536643149.png",
    #     "coor":(-0.009, 0.954)
    # },
    {
        "testMethodDoc": "歌曲列表",
        "logdirname": "song_list",
        "eleloc": "歌单",
        "picture": "tpl1608536677648.png",
        "coor": (-0.094, -0.815),
        "title":"歌单",
        "desc":"测试歌单列表是否正常显示",
        "author":"zs"
    },
    {
        "testMethodDoc": "排行榜列表",
        "logdirname": "rank_list",
        "eleloc": "排行榜",
        "picture": "tpl1608536708310.png",
        "coor": (-0.312, 0.242),
        "title": "排行榜",
        "desc": "测试排行榜列表是否正常显示"
    }
]
```

```python
# 装饰器代码
def outer(func):
    @wraps(func)
    def inner(self,kwargs): # 接收函数传过来的实参
        self._testMethodDoc = kwargs.get("testMethodDoc")
        only_set_log(Logs_DIR + kwargs.get("logdirname"))
        try:
            func(self,kwargs)
        except Exception as e:
            log(e, desc="报错信息", snapshot=True)
            raise
        finally:
            simple_report(__file__, logpath=Logs_DIR +kwargs.get("logdirname"),
                          output=Report_DIR +kwargs.get("logdirname")+".html")
            self._html_path = Report_DIR + kwargs.get("logdirname")+".html"
    return inner
```

```python
 	# 调用实现
    @ddt.data(*TestNavBar_datalist)
    @outer
    def test_navbar(self,kwargs):
        self.poco(text=kwargs.get("eleloc")).click()
    	assert_exists(Template(TestData_DIR+kwargs.get("picture"), record_pos=kwargs.get("coor"), resolution=(1080, 2340)), )
        keyevent("BACK")
```

# 阶段7

## 课程目标

- 学会用flask搭建简单服务器
- 能够将报告放在服务器远程访问

## 1.Flask初解

### 1.1简单使用

```python
# 安装  pip install flask

# 导入
from flask import Flask
#Flask函数接收一个参数__name__，它会指向程序所在的包
app = Flask(__name__)
# 装饰器的作用是将路由映射到视图函数 index
@app.route('/')
def index():
    return 'Hello World'
# Flask应用程序实例的 run 方法 启动 WEB 服务器
if __name__ == '__main__':
    app.run()
    
    #可以指定运行的主机IP地址，端口，是否开启调试模式
	# app.run(host="0.0.0.0", port=5000, debug = True)
```

### 1.2 路由参数详解

#### demo

```python
# 指定访问路径为 login 必须以路径分隔符 / 开头
# 符合methods条件的请求方式才被允许
@app.route('/login', methods=['GET', 'POST'])
def login():
    return 'login页面'
```

#### 路由中传递参数

```python
@app.route('/query/name/age')
def user_info(name,age):
    return f"根据{name}和{age}查出的用户有："

@app.route('/query/<int:user_id>')
def user_info(user_id):
    return f"查出的用户是:{user_id}"
```

### 1.3 request

request 就是flask中代表当前请求的 request 对象，其中一个**请求上下文变量**(理解成全局变量，在视图函数中直接使用可以取到当前本次请求)

常用的属性如下：

| 属性    | 说明                           | 类型                        |
| :------ | :----------------------------- | :-------------------------- |
| url     | 记录请求的URL地址              | str                         |
| method  | 记录请求使用的HTTP方法         | str                         |
| headers | 记录请求中的报文头             | EnvironHeaders 类字典对象   |
| args    | 记录请求中的查询参数           | MultiDict                   |
| form    | 记录请求中的表单数据           | MultiDict                   |
| files   | 记录请求上传的文件             | MultiDict[str: FileStorage] |
| data    | 记录请求的数据，并转换为字符串 | bytes                       |
| cookies | 记录请求中的cookie信息         | MultiDict                   |

```python
@app.route('/login', methods=['GET', 'POST'])
def login():
    print(request.method)
    print(request.args.get("sex"))
    print(request.url)
    return 'login页面'
```

https://blog.csdn.net/xybelieve1990/article/details/81111786

### 1.4 状态保持

#### 1.cookie

**Cookie**：指某些网站为了辨别用户身份、进行会话跟踪而**储存在用户本地的数据（**通常经过加密）。

- Cookie是由服务器端生成，发送给客户端浏览器，浏览器会将Cookie的key/value保存，下次请求同一网站时就发送该Cookie给服务器（前提是浏览器设置为启用cookie）。
- Cookie的key/value可以由服务器端自己定义。

- Cookie是存储在浏览器中的一段纯文本信息，建议**不要存储敏感信息如密码。容易被他人窃取**
- Cookie基于域名安全，不同域名的Cookie是不能互相访问的即百度不能访问到淘宝的cookie
- 当浏览器请求某网站时，会将本网站下所有Cookie信息提交给服务器，所以在request中可以读取Cookie信息

##### 设置cookie

```python
# 导入模块
from flask imoprt Flask,make_response

@app.route('/cookie')
def set_cookie():
    # 创建响应对象
    resp = make_response('this is to set cookie')
    # print(resp.headers)  # 响应头信息
    # print(resp.content_type)  # 响应的content-type    默认为 text/html 返回网页
    # 使用响应对象设置cookie
    resp.set_cookie('username', 'zs')
    return resp  # 返回自定义的响应对象
```

##### 获取cookie

```python
#获取cookie
@app.route('/getcookie')
def resp_cookie():
    resp = request.cookies.get('username')
    return resp
```

#### 2.session

- 对于敏感、重要的信息，建议要存储在服务器端，不能存储在浏览器中，如用户名、余额、等级、验证码等信息
- 在服务器端进行状态保持的方案就是`Session`
- **Session依赖于Cookie**

**要使用session必须要设置app.secret_key，不然会报错**

```python
@app.route('/session')
def set_session():
    session['username'] = 'ls'
    return "设置session"

@app.route('/getsession')
def get_session():
    return session.get('username')
```

https://blog.csdn.net/u010325193/article/details/87888400

### 1.5静态文件和模板文件

#### 静态目录

在项目 目录下创建 `static` 文件夹

![image-20201219143429027](https://woniumd.oss-cn-hangzhou.aliyuncs.com/test/zhangjing/20201219143429.png)

运行服务即可通过 地址:端口/static/文件路径 访问该静态文件

#### 模板目录

在 **static** 同级目录创建 **templates** 文件夹

## 2. 实现一个简单的登陆页面登陆成功访问报告

登陆代码逻辑

```python
# -*- coding: utf-8 -*-
"""
@author: ZJ
@email: 1576094876@qq.com
@File : run.py
@desc: 
@Created on: 2020/10/25 15:15
"""

from flask import Flask, request, render_template, redirect, url_for, session, jsonify

from Vttt.Base.BaseSettiings import USERNAME, PD

app = Flask(__name__)
app.secret_key="sfwfqwgwqgdsgrfdhreh"
app.config["JSON_AS_ASCII"]=False
@app.route("/")
def index():
    username = session.get('username')
    if username==USERNAME:
        #到首页后 访问静态文件（我们生成好的报告页面）
        return app.send_static_file('result.html')
    else:
        return redirect(url_for('login'))

@app.route("/login",methods=["GET","POST"])
def login():
    # 根据 请求方法判断
    # GET 请求显示登陆页面
    if request.method == "GET":
        return render_template("index/login.html")
    else:
        # POST  说明提交用户名和密码
        # 接受用户传过来的用户名和密码
        username = request.form.get("username")
        # 判断数据库有没有该用户
        if username == USERNAME:
            # 有用户 判断密码是否正确
            password = request.form.get("password")
            if password ==PD:
                # 提示登录成功返回首页
                session['username']=username
                return redirect(url_for('index'))
            else:
                # 访问模板页面  
                return render_template("index/login.html", errmsg="密码错误")
        # 没有查到用户  提示用户不存在
        else:
            return render_template("index/login.html",errmsg="用户名不存在")

@app.route("/favicon.ico")
def getfavicon():
    return app.send_static_file("timg.jpg")

if __name__ == '__main__':
    import socket

    # 获取本机计算机名称
    hostname = socket.gethostname()
    # 获取本机ip
    ip = socket.gethostbyname(hostname)
    ipport = "http://" + ip + ":5000/"
    print(ipport)
    app.run(host="0.0.0.0",port=5000,debug=True)
```

## 3 .file和http协议区别

1.file协议用于访问本地计算机中的文件，就如同在Windows资源管理器中打开文件一样，注意它是针对本地（本机）的，简单来说，**file协议是访问你本机的文件资源**。

2.http访问本地HTML，是在本地起了一台HTTP服务器，然后你访问自己电脑上的**本地服务器**，http服务器再去访问你本机的文件资源。

再简单的说：file只是简单的请求本地文件，与在Windows中找到文件，右键，打开方式，用浏览器打开看到的效果是一样的； http是搭建了服务器。

 3.还有不同的表现，但和访不访问本地关系不大：

- file协议只能在本地访问
- 本地搭建了http服务器，开放端口之后，别人也可以通过http访问到你电脑里的页面，但是**file协议做不到**

`4. file协议`对应也有一个类似http的远程访问，就是`ftp协议，文件传输协议`。

原因：result.html页面内的详细信息使用的绝对路径file协议打开，但是现在我们是部署在服务器上  

### 解决方案

1.将原先的report文件夹放在静态文件夹下。方便我们后期通过静态服务器访问。那我们原本的报告生成路径就要改变，以后产出新的报告就直接保存在这个目录下

![image-20201219143443249](https://woniumd.oss-cn-hangzhou.aliyuncs.com/test/zhangjing/20201219143443.png)

2.原本的htmlpath即报告路径也要跟着改 变为静态服务器地址

```python
# 在settings文件里新增 ip地址和 ip报告地址
import socket
# 获取本机计算机名称
hostname = socket.gethostname()
# 获取本机ip
ip = socket.gethostbyname(hostname)
ipport = "http://"+ip+":5000/"

ipport_REPORT_DIR = ipport+"static/Report/"
```

那么原本的报告地址就要换成ipport_REPORT_DIR，这样我们就能访问服务器上的静态资源，但是生成报告时的输出报告地址没必要换。因为他是为了在服务器上指定位置生成。

![image-20201224144844319](https://woniumd.oss-cn-hangzhou.aliyuncs.com/test/zhangjing/20201224144844.png)

# 阶段8

## 课程目标

- Airtest源码分析
- 高级功能自定制
- 实现报告的在线查看
- 完整的项目框架实现

## 1.进入详情页面没有样式

### 1.原因

生成报告时的css样式也是用的绝对路径

### 2.解决思路

将airtest生成报告所需要的css，js等一些文件夹也放到静态服务器，后期生成的报告引用文件都引用服务器地址

### 3.源码分析

```python
def simple_report(filepath, logpath=True, logfile=LOGFILE, output=HTML_FILE):
    """
    :param filepath:  py文件路径
    :param logpath: 日志路径
    :param logfile: 日志文件  log.txt
    :param output:   html输出地址  html输出地址
    :return:
    """
    # path py文件的上一级目录  name  py文件名
    path, name = script_dir_name(filepath)
    if logpath is True:
        logpath = os.path.join(path, LOGDIR)
    # 实例化rpt对象
    rpt = LogToHtml(path, logpath, logfile=logfile, script_name=name)
    # 调用report方法
    rpt.report(HTML_TPL, output_file=output)
```

```python
class LogToHtml(object):
    """Convert log to html display """
    scale = 0.5

    def __init__(self, script_root, log_root="", static_root="", export_dir=None, script_name="", logfile=LOGFILE, lang="en", plugins=None):
        """
        :param script_root:  script_root  ==>path ===> py文件的上一级目录
        :param log_root:  日志文件夹路径
        :param static_root:  ""
        :param export_dir:  None
        :param script_name:   ====> name  ===> py文件名
        :param logfile:  日志文件  log.txt
        :param lang:  "en"
        :param plugins:None
        """
        self.log = []
        self.script_root = script_root  # py文件的上一级目录
        self.script_name = script_name  # py文件名
        self.log_root = log_root  #  日志文件夹路径
        self.static_root = static_root or STATIC_DIR  # report 文件夹
        self.test_result = True
        self.run_start = None
        self.run_end = None
        self.export_dir = export_dir  # None
        self.logfile = os.path.join(log_root, logfile)  # 日志文件  log.txt路径
        self.lang = lang
        self.init_plugin_modules(plugins)
```

### 4.解决方案

我们要实现一个能拿来即用的完整的项目框架。首先要确保的就是尽量做到所有的参数配置化。如果我们直接在源代码里改会有许多问题。比如别人拿到你的项目并不知道你源码改了什么。并不能通用。所以我们需要自己重写源码

```python
def DIYsimple_report(filepath, logpath=True, logfile="log.txt", output="log.html"):
    """
    :param filepath: __file__  文件路径
    :param logpath:  默认为True   LogsDIR + kwargs["logname"]  我们设定的日志文件夹输出地址
    :param logfile: 默认 log.txt   产生的日志文件夹的路径下的log.txt
    :param output:  默认  log.html   我们设定的报告输出地址    ReportDIR + kwargs["logname"] + ".html"
    :return:
    """
    path, name = script_dir_name(filepath)  #path  传入的文件的上一级目录  name 文件名
    if logpath is True:
        logpath = os.path.join(path, "log")
    rpt = DIYLogToHtml(path, logpath, logfile=logfile, script_name=name,static_root=AirResourceDIR)  # 传入参数
    rpt.report("log_template.html", output_file=output)   # HTML_TPL  "log_template.html"
```

https://juejin.cn/post/6875863904765870093

## 2.进入详情页面没有日志图片数据

### 1.原因

日志图片数据也是用的绝对路径

### 2.解决思路

将这些log数据也放到静态服务器，后期生成的报告引用文件都引用服务器地址

### 3.源码分析

```python
    def report(self, template_name=HTML_TPL, output_file=None, record_list=None):
        """
        Generate the report page, you can add custom data and overload it if needed
        :param template_name: default is HTML_TPL  "log_template.html"
        :param output_file: The file name or full path of the output file, default HTML_FILE
        :param record_list: List of screen recording files
        :return:
        """
        if not self.script_name:
            path, self.script_name = script_dir_name(self.script_root)

        if self.export_dir:
            self.script_root, self.log_root = self._make_export_dir()
            # output_file可传入文件名，或绝对路径
            output_file = output_file if output_file and os.path.isabs(output_file) \
                else os.path.join(self.script_root, output_file or HTML_FILE)
            if not self.static_root.startswith("http"):
                self.static_root = "static/"

        if not record_list:
            record_list = [f for f in os.listdir(self.log_root) if f.endswith(".mp4")]  #  []
        data = self.report_data(output_file=output_file, record_list=record_list)
        return self._render(template_name, output_file, **data)
```

```python
    def report_data(self, output_file=None, record_list=None):
        """
        Generate data for the report page
        :param output_file: The file name or full path of the output file, default HTML_FILE  报告输出地址
        :param record_list: List of screen recording files  [ ]
        :return:
        """
        self._load()   # 向 self.log 里添加 log.txt的数据
        steps = self._analyse()  # """ 解析self.log成可渲染的dict  是个列表 里面是每一步的 dict数据 """
        #  script_path  文件完整路径
        script_path = os.path.join(self.script_root, self.script_name)
        # info ===》{"name": script_name, "path": script_path, "author": author, "title": title, "desc": desc}
        info = json.loads(get_script_info(script_path))
        # records  [ ]
        records = [os.path.join(LOGDIR, f) if self.export_dir
                   else os.path.abspath(os.path.join(self.log_root, f)) for f in record_list]

        # os.path.sep   win和linux下的不一样，如果脚本在win下开发，在linux下面跑，可以用这个，避免报错
        if not self.static_root.endswith(os.path.sep):
            self.static_root = self.static_root.replace("\\", "/")
            self.static_root += "/"

        data = {}
        data['steps'] = steps
        data['name'] = self.script_root
        data['scale'] = self.scale
        data['test_result'] = self.test_result
        data['run_end'] = self.run_end
        data['run_start'] = self.run_start
        data['static_root'] = self.static_root
        data['lang'] = self.lang
        data['records'] = records
        data['info'] = info
        data['log'] = self.get_relative_log(output_file)
        data['console'] = self.get_console(output_file)
        # 如果带有<>符号，容易被highlight.js认为是特殊语法，有可能导致页面显示异常，尝试替换成不常用的{}
        info = json.dumps(data).replace("<", "{").replace(">", "}")
        data['data'] = info
        return data
```

### 4.解决方案

把Logs文件夹放于static目录下

然后打印页面详情渲染的data数据。发现日志地址都指向的log的绝对路径

我们可以直接正则替换成服务器访问

```python
# settings文件需要做改变的地方
# 改变logs目录地址
LOG_DIR = os.path.join(static_DIR,"Logs\\")
Entrance_DIR = os.path.join(BASE_DIR,"Entrance\\")
# 匹配成目标数据
Entrance_DIR2 = Entrance_DIR.replace("/","\\").replace("\\","\\\\")
```

```python
class DIYLogToHtml(LogToHtml):

    def report_data(self, output_file=None, record_list=None):
        """
        Generate data for the report page
        :param output_file: The file name or full path of the output file, default HTML_FILE
        :param record_list: List of screen recording files
        :return:
        """
        self._load()   # 拿log数据
        steps = self._analyse()  # 处理log 数据  #返回一个列表 每个数据就是解析后的可渲染的dict

        script_path = os.path.join(self.script_root, self.script_name)  #运行的文件名  __file__
        #info 字典 { script_name 文件名    script_path 文件完整路径  文件中的__author__  title, desc}
        info = json.loads(get_script_info(script_path)) #拿到文件的相关信息
        # [ ]
        records = [os.path.join("log", f) if self.export_dir
                   else os.path.abspath(os.path.join(self.log_root, f)) for f in record_list]

        if not self.static_root.endswith(os.path.sep): #  为了支持服务器 http://192.168.56.1:5000/static/AirResource// \
            self.static_root = self.static_root.replace("\\", "/")
            self.static_root += "/"

        data = {}
        data['steps'] = steps  #  #返回一个列表 每个数据就是解析后的可渲染的dict
        data['name'] = self.script_root  #  文件的上一级目录
        data['scale'] = self.scale # 缩放比例
        data['test_result'] = self.test_result  # 测试结果
        data['run_end'] = self.run_end
        data['run_start'] = self.run_start
        data['static_root'] = self.static_root
        data['lang'] = self.lang
        data['records'] = records  # 【】
        data['info'] = info  #info 字典 { script_name 文件名    script_path 文件完整路径  文件中的__author__  title, desc}
        data['log'] = self.get_relative_log(output_file)
        data['console'] = self.get_console(output_file)
        # 如果带有<>符号，容易被highlight.js认为是特殊语法，有可能导致页面显示异常，尝试替换成不常用的{}
        info = json.dumps(data).replace("<", "{").replace(">", "}")
        # info.replace(r"D:\\Pycharm\\PythonProject\\app33\\V4\\","http://192.168.56.1:5000/static/")
        info = info.replace(NewEntranceDIR,ipport)  # 只增加这一行 实现字符串替换
        data['data'] = info  # 字符串 data内容s
        return data  #字典
```

## 3. 项目细节优化

### 3.1 详情页面的具体信息 

### 3.2 使用内网穿透实现外网访问

### 3.3 邮件发送

```python
def send_email(email_Subject="测试报告结果", file_path="", filename="", received_Email=["1576094876@qq.com","1360647985@qq.com","171498526@qq.com"], mailserver="smtp.qq.com",
               userName_SendEmail='1576094876@qq.com', userName_AuthCode='tzlxkmohwcvwjibh'):

    # 创建邮件对象
    msg = MIMEMultipart()

    msg["Subject"] = Header(email_Subject, 'utf-8')
    msg["From"] = userName_SendEmail
    msg["To"] = ",".join(received_Email)

    # 发送普通文本
    content="尊敬的用户报告已经生成，请前往 %s   查看"%IP
    html_content = MIMEText(content, 'plain', 'utf-8')
    msg.attach(html_content)

    # 邮件中发送附件
    # content = open(file_path, 'rb').read()
    # att = MIMEText(content, "base64", "utf-8")
    # att["Content-Type"] = "application/octet-stream"  # 一种传输形式
    # att["Content-Disposition"] = "attachment;filename=%s" % filename
    # msg.attach(att)

    smtp = smtplib.SMTP_SSL(mailserver)  # 创建客户端
    smtp.login(userName_SendEmail, userName_AuthCode)
    smtp.sendmail(userName_SendEmail, received_Email, msg.as_string())
    smtp.quit()
```

# 笔记

```python
"""
我们常说的airtest 由 airtest框架 poco框架 airtestIDE三部分组成
airtest框架  基于图像识别的开源框架
poco框架  基于uiautomator 封装的元素定位的一个开源框架
airtestIDE 网易自己开发的一个IDE（集成工具）内置了airtest框架以及poco框架以及Python3.6.5 你可以直接用该IDE书写Python代码

airtestIDE用法
定位显示坐标  选项 设置 坐标显示
查看包名  
图像识别算法
    模板匹配
        无法跨分辨率识别  当不同手机分辨率不一样的时候  就是跨分辨率
        一定有匹配结果  即使页面没有该元素也会返回一个结果
         tpl  templateMatching
    特征点匹配
        可以支持跨分辨率识别
        不一定会有匹配结果
        

airtest框架 相关操作
touch(图片/绝对坐标)  基于图片或者绝对坐标进行点击
swipe(v1,v2) 从v1位置滑动到V2 可以传图片或者绝对坐标
text(文本内容)  输入文本内容 并回车
keyevent(键盘信息) 执行指定键盘事件 BACK HOME ENTER
  
Poco框架相关操作
# 实例化 一个Android的poco对象
poco = AndroidUiautomationPoco(use_airtest_input=True, screenshot_each_action=False)
类似于实例化一个driver
driver = webdriver.Chrome()

poco类对象的操作api  本质就是基于手机去直接做的操作
poco.方法 等同于 driver.方法
poco.click((x,y)/[x,y])  基于当前屏幕 对x，y位置进行点击  可以是相对坐标
poco.get_screen_size()  得到当前设备的分辨率
poco.scroll(方向,百分比,持续时间)    沿着水平或垂直方向 滑动指定百分比距离
poco.swipe(v1,v2)  基于当前屏幕 将v1位置滑到到v2位置

UIObjectProxy poco定位到元素 元素的相关操作
通过poco类找到元素  去对元素进行单独的操作
ele = poco(name="")  等同于 ele=driver.find_element_by_xxx()
ele.click()  基于定位到的元素去进行点击操作
ele.drag_to(target_ele)  将该元素拖动到目标元素位置
ele.exists()  判断元素是否存在
ele.scroll(方向，百分比，持续时间) 将元素沿着指定方向滑动指定百分比
ele.set_text(内容) 设置元素的text属性为指定内容
ele.setattr(属性名，属性值)设置元素的指定属性为指定值
ele.swipe(坐标/方向) 将元素滑动到指定坐标位置


函数执行顺序 导包流程以及main的意义
函数传参 
    形参 函数创建时的参数
        默认值参数/缺省参数  格式：参数名=参数值
        不定长参数  
            *args     接收没人要的位置参数   args肯定是个元祖
            **kwargs  接收没人要的关键字参数  kwargs肯定是个字典
    实参 函数调用时传的参数
        位置参数     按顺序填写的参数
        关键字参数    格式  参数名=参数值
        
逻辑运算符 and or
and  第一个结果为真  返回第二个结果  如果第一个为假 直接返回第一个结果
or   第一个结果为真  返回第一个结果  如果第一个为假 返回第二个结果
is 和 == is本质判断内存地址  

__file__ 表示当前文件的路径  返回的是一个文件路径字符串
os.path.dirname(文件路径)   获取文件的上一级目录
os.path.basename(文件路径)  获取文件名
os.path.join(path,name)  将路径和名称拼接成一个新的地址
os.path.exists(文件路径) 判断文件是否存在 返回布尔值
os.mkdir(文件路径)  创建文件夹
sys.argv 获取终端传递过来的参数 是一个列表
shutil.rmtree(logdir)  删除指定目录
try execpt else finally 
isinstance(obj,class) 判断对象 是否属于这个类返回True或False  

目录结构搭建
Base  存放 公共文件基础模块
    BaseSettings  存放整个项目中的基本配置
    airtestlib    所有有关airtest源码重写的内容都放在该文件下
TestCase  存放测试用例  
TestSuite 存放测试套件
TestData  存放测试数据
Logs      存放报告生成的日志
Report    存放报告产出的位置
Test      存放写代码时测试流程

unittest 四大核心 执行顺序  skip跳过本质
套件的执行流程  
    # 1.导入模块
    import  unittest
    
    # 2.实例化套件对象
    from V3.TestCase import TestFind
    
    suite = unittest.TestSuite()
    
    # 3.找寻测试case
    TestCases = unittest.TestLoader().loadTestsFromModule(module)  从模块即py文件 中加载所有测试方法    参数py文件名
	TestCases = unittest.TestLoader().loadTestsFromTestCase(classname)     从类中添加  这个类中的所有的测试方法   参数类名，类必须继承unittest.TestCase
	TestCases = unittest.TestLoader().loadTestsFromName(Name)     加载某个单独的测试方法 必须是字符串   “module.class.def”
	TestCases = unittest.TestLoader().loadTestsFromNames()      加载某个单独的测试方法 必须是列表   [“module.class.def1”,“module.class.def2”]
    # 4.将case放入套件
    suite.addTest(TestCases)
    
    # 5.实例化runner  执行套件
    runner = unittest.TextTestRunner()
    runner.run(suite)
    
pip freeze > r.txt   将当前项目所需要的第三方包输出到r.txt
pip install -r r.txt  将r.txt内的第三方包下载到本地

BeautifulReport
    # 5.实例化runner  执行套件
    runner = BeautifulReport(suite)
    runner.report("描述信息","输出报告文件名")
    
    报告页面上
    失败  指的是断言错误
    错误  指的是程序中出现异常
    跳过  必须在case上使用unittest.skip("原因")
    
三元表达式   条件为真输出结果 if 条件  else 条件为假输出结果
对象.__dict__  获取对象的属性
魔法方法 __repr__ 和 __str__
    str(obj) 会优先找  __str__ 找不到找__repr__
    repr(obj) 只会输出__repr__的内容
zip(a,b) 将a和b的元素一一对应  直接使用只会拿到对象
list(可迭代对象)

字典获取键值对内容
    字典[键名]  找不到会报错
    字典.get(键名)  找不对不会报错
json用法
python 面向对象 封装 继承 多态
类的重写 以及方法重写   
装饰器
    # 万能装饰器 用来处理装饰器 没有参数的情况
    def outer(func):  func  接收的是被装饰的函数
        @wraps(func)  # 让函数名变成本身  不加的话 函数.__name__是inner
        def inner(*args, **kwargs):   # 接收函数传过来的 实参   123, a=123   args(123,)   kwargs {a:123}
            # 新增功能
            # 新增功能 模块
            return func(*args, **kwargs)  # ctest(  123,a=123)   根据函数有无返回值决定要不要使用return  可以省略
        return inner
    ctest(123, a=123)  # inner (123, a=123)
    print(ctest.__name__)
    
    # 万能装饰器 用来处理装饰器 有参数的情况
    def get_param(*dargs,**dkwargs):  # 工厂函数 单纯的用来接收装饰器的参数
        def outer(func):
            @wraps(func)
            def inner(*args, **kwargs):   # 接收函数传过来的 实参   123, a=123   args(123,)   kwargs {a:123}
                # 新增功能
                print("dargs:",dargs)
                print("dkwargs:",dkwargs)
                print("args:",args)
                print("kwargs:",kwargs)
                return func(*args, **kwargs)  # ctest(  123,a=123)
            return inner
        return outer
        
    @get_param("装饰器的参数",ddd="asvw")  # @outer
    def ctest(c,a):
        print("被装饰函数args",c,a)
        print("被装饰函数kwargs",c,a)
    
    ctest(123, a=123)  

ddt
    ddt.data 内的数据决定你程序执行几次
    ddt.unpack 是对每一次执行的数据解包  看需求可用可不用
多个装饰器执行顺序  
ddt底层 数据驱动原理    ddt 是一次性把所有参数解析完，生成 n 个新的测试方法，再交给测试框架去调度
列表推导式
字符串相关操作
"""
```

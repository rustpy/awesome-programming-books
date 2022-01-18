# fiddler

## 一、fiddler简介

BS架构：Browser（客户端） ----HTTP----Server（服务端）

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\11c52142a47722f17950927f2c58e059faf43d7a.png)



### **1.fiddler抓包原理**

系统默认网络代理为自动设置代理，当fiddler打开后则会改成手动设置代理，端口号为8888

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\2021-12-29-16-16-50-image.png)



### **2.HTTP协议详解**

HTTP是基于TCP的应用层协议，用于规定客户端和服务端的数据传输格式，最初是用来向客户端传输HTML页面。默认端口为80.

http是基于请求与响应模式的、无状态的、应用层协议。

http请求报文组成：请求行、请求头部、请求正文。

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\2021-12-29-17-30-00-image.png)

请求方法：GET、POST、PUT、DELECT、HEAD、OPTIONS、TRACE



### **3.fiddler工具详解**

抓包：包是指数据包，抓包，用代理工具获取客户端与服务端发送和返回的数据包。

应用：1.定位问题、2.区分前后端bug、3.接口测试、4.进行mock测试

**工具面板：**

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\d8b5d8dcf5031704a7eb33721b5d95b3c4a282a6.jpg)

Stream：流模式/缓冲模式

- 缓冲模式（Buffering Mode）Fiddler直到HTTP响应完成时才将数据返回给应用程序。可以控制响应，修改响应数据。但是时序图有时候会出现异常
- 流模式（Streaming Mode）Fiddler会即时将HTTP响应的数据返回给应用程序。更接近真实浏览器的性能。时序图更准确,但是不能控制响应。

**会话面板：**

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\312db64c234e63fd3d18686b0f5249f8ee7abfb6.jpg)

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\f8ab690f1d939480ea713aef55f696730c4d2822.jpg)



**监控面板：**

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\e37399fe24795cbd00b38833510d4c316c85b8af.jpg)



**监控面板：**

1. 请求总数、请求包大小、响应包大小。
2. 请求起始时间、响应结束时间、握手时间、等待时间、路由时间、TCP/IP、传输时间。
3. HTTP状态码统计。
4. 返回的各种类型数据的大小统计以及饼图展现。

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\1b1a47c29fb73a773700cf542934cf468e3f9df4.jpg)





每个网络请求都会经历域名解析、建立连接、发送请求、接受数据等阶段。把多个请求以时间作为 X 轴，用图表的形式展现出来，就形成了瀑布图。在Fiddler中，只要在左侧选中一些请求，右侧选择Timeline标签，就可以看到这些请求的瀑布图。

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\7b920eba89aff0c77d7fd3847fe3a07f4b03a92f.jpg)

**时间轴**

- 绿色的请求表示这是一个“有条件的请求”。HTTP 协议定义了 5 个条件请求头部，最常见的两个是“If-Modified-Since”和“If-None-Match”。服务器根据这两个头部来验证本地缓存是否过期，如果过期则正常返回资源的最新版本；否则仅返回 304 Not Modified，浏览器继续使用本地缓存。包含条件请求头部的请求用绿色显示，否则用黑色。

- 有阴影线的请求是缓冲模式下的请求，实心的是流模式下的请求。Fiddler 提供了缓冲（Buffering）和流（Streaming）两种抓包模式：缓冲模式下，Fiddler 会在响应完成时才将数据返回给应用程序（通常是浏览器），这种模式下可以控制响应，方便地修改响应内容；流模式下，Fiddler 会实时返回响应数据给浏览器，但没办法控制响应。一般使用流模式，瀑布图会更真实一些。这两种模式可以通过 Fiddler 的工具栏选择。特别的，通过 Fiddler 的“AutoResponder”功能返回的响应，只能是缓冲模式。

- 请求条的不同颜色对应着不同类型的响应，根据响应头的 MIME Type 来归类。如浅绿色表示图片类型的响应；深绿色是 JavaScript；紫色是 CSS；其它都是蓝色。

- 请求中的黑色竖线，表示的是浏览器收到服务端响应的第一个字节这一时刻。这个时间受 DNS 解析、建立连接、发送请求、等待服务端响应等步骤的影响。

- 请求条后面的图标表示响应的某些特征。如软盘图标表示这个响应正文从本地获得，也就是说服务端返回了 304；闪电表示这是 Fiddler 的“AutoResponder”的响应；向下的箭头表示响应是 302，需要重定向；红色感叹号说明这个请求有错误发生（状态码是 4XX 或 5XX）。特别的，如果请求条后面有一个红色的X，说明服务端响应完这个请求之后，断开了连接。出现这种情况一般有两种可能：HTTP/1.0 的响应中没有 Connection: Keep-Alive；或者是 HTTP/1.1 的响应中包含了 Connection: close。使用持久连接可以省去建立连接的开销，也可以减小 TCP 慢启动和其它拥塞控制机制带来的影响，总之是好处多多。

- 请求前面的红色圆圈表示这个连接是新建的，绿色表示是复用的。上面的圆圈表示的是浏览器到 Fiddler 的连接，下面的圆圈是 Fiddler 到服务端的连接。



**状态面板：**

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\bcb582c3af51e872f281240ae79349378bafdee2.jpg)

控制台Fiddler的左下角有一个命令行工具叫做QuickExec,允许你直接输入命令。  
常见的命令有：

| 命令      | 解释                     |
| ------- | ---------------------- |
| help    | 打开官方的使用页面介绍，所有的命令都会列出来 |
| cls     | 清屏 (Ctrl+x 也可以清屏)      |
| select  | 选择会话的命令                |
| ?.png   | 用来选择png后缀的图片           |
| bpu     | 截获request              |
| bpafter | 截获response             |



**HOST常见功能应用：**

- 模拟各类场景

- 通过GZIP压缩，测试性能

- 模拟Agent测试，查看服务端是否对不同客户端定制响应

- 模拟慢速网络，测试页面的容错性

- 禁用缓存，方便调试一些静态文件或测试服务端响应情况

- 根据一些场景自定义规则
  
  

**低网速测试：**

低网速模拟设置 Rules > Performance > Stimulate Modem Speeds。



**conpare(对比文本)：**

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\b446c28f5dd03f66f9216a4c7a474f5d467b5080.jpg)









**Filters(过滤监控)**

对一个重新载入的页面进行抓包，如果包的条目过多而你需要关注的就那么几项的话，可以使用Fiddler的过滤器Filters进行抓包，那么抓包时只会抓取你希望抓到的那些包。切换到Filters标签勾选Use filter，以便激活过滤器，这样下面的各种过滤方式就可以进行选择了。

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\f6b3a2b40157337bbf90ea70f63351df5008d8fb.jpg)

```
![Filter_2](http://upload-images.jianshu.io/upload_images/947566-b6ed61685e13315e.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```



**AutoResponder(请求重定向)**

所谓请求无非就是需要调用到的一些资源(包括JS、CSS和图片等)，所谓重定向就是将页面原本需要调用的资源指向其他资源(你能够控制的资源或者可以引用到的资源)。

1. 你可以将前台服务器的诸多或者某个资源在本地做个副本，如果正常网络访问环境下该资源出现了BUG而导致开发环境崩溃时，可以先将这个资源的请求重定向到本地副本，这样就可以继续进行开发调试你的页面，从而大量节省资源维护的等待时间。
2. 你也可以将多人同时维护的某个JS文件复制一份出来在本地，当你的开发调试收到他人调试代码干扰时，可以将这个JS的调用重定向到本地无干扰的JS文件，进行无干扰开发，功能开发完成并调试OK之后再将你的代码小心合入到开发环境中，这样就可以避免受到他人干扰专心搞你的模块开发，也就是说能够将JS文件脱离开发环境却不影响线上调试。
3. 你还可以将样式文件或者图片指向本地。

```
![重定向](http://upload-images.jianshu.io/upload_images/947566-18f9c105596ef543.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```



**移动端抓包设置：**

Fiddler不但能截获各种浏览器发出的HTTP请求, 也可以截获各种智能手机发出的HTTP/HTTPS请求。  
Fiddler能捕获IOS,Andriod,WinPhone,设备发出的请求，同理，也可以截获IPad, MacBook的等设备发出的HTTP/HTTPS。  
前提条件是：安装Fiddler的机器，跟Iphone 在同一个网络里， 否则IPhone不能把HTTP发送到Fiddler的机器上来。



Fiddler设置打开Fiddler, Tools-> Fiddler Options。（配置完后记得要重启Fiddler）.

选中"Allow remote computers to connect". 是允许别的机器把HTTP/HTTPS请求发送到Fiddler上来。

![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\d657c351141e58f4f58adefff8e358af793b4c95.jpg)



- **APP**

- 获取Fiddler所在机器的IP
  
  ![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\7b957a513008cb94f53e6c2e0203bc86ab3f920b.jpg)

- 安装Fiddler证书这一步是为了让Fiddler能捕获HTTPS请求。 如果你只需要截获HTTP请求， 可以忽略这一步
  
  ![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\3ea99d20659bb7d43c6b087690596a48fcca3f3f.jpg)

- 首先要知道Fiddler所在的机器的IP地址：　假如我安装了Fiddler的机器的IP地址是:192.168.1.104打开IPhone 的Safari, 访问 [http://192.168.1.104:8888](https://link.jianshu.com/?t=http://192.168.1.104:8888/)， 点"FiddlerRoot certificate" 然后安装证书
  
  ![](E:\Apersonal\awesome-programming-books\Notes\工具学习\assets\3de66e173a447520dc77b72ecd0955a3b3c336e8.jpg)











## 二、**fiddler应用实战**

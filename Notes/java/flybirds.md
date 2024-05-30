# flybirds

## 简介

需要学习的内容：**javascript&nodejs**，**python**，java&spring boot，html，android adb命令，shell脚本，mongoDB(基础)，jekins，mysql，**airtest**， C++,  redis,  xcode,  android studio,  adb命令

当前项目：

nodejs实现websocke转发

稳定，性能，断线重连，现有的webscoket开源框架





Flybirds 是一套基于BDD模式的前端UI自动化测试框架，提供了一系列开箱即用的工具和完善的文档。

简介文档：https://flybirds.readthedocs.io/zh-cn/latest/BDD-UI-Testing-Flybirds.html#id1

测试用例基于**behave**中定义的的生命周期运行的：

![image-20240514165900087](assets/image-20240514165900087.png)



## 1、flybirds调试

### 1.1、数据库连接

数据库用的是mongoDB，连接地址为：

mongodb://app_user_flytest:SmrSuYeHa8D6@10.118.79.200:27017/?authMechanism=SCRAM-SHA-1&authSource=FlyTest

debugerCenter表 - stepPort（获取步骤） - debuggerPort（获取图片，base64格式）



### 1.2、debug配置

参考文档：http://conf.ctripcorp.com/pages/viewpage.action?pageId=2278139779

**进入debug模式之前必须要先加：os.environ["extend_pkg_list"] = 'trip_flybirds_plugin'，不然走的是子包不是主包**：

#### 1.2.1、pycharm配置

1）配置flybirds脚本（调试flybirds相关代码，未进入behave运行之前，配置flybirds.cli.__main__）

运行参数：run -P ./features/test/web/web_en_verify.feature

![image-20240528172653825](assets/image-20240528172653825.png)

```shell
带额外参数运行（可配置到debug configurations中）：
run -P features/test/web/web_cn_click.feature --define otherParam={\"deviceType\":\"BROWSER\",\"loginChannel\":\"OTHERS\",\"appSourceType\":\"0\",\"coverageFlag\":\"0\",\"frameworkType\":\"FLYBIRDS\",\"platformType\":\"WEB\",\"options_loginChannel\":\"OTHERS\",\"browsers\":\"chromium\",\"options_projectSubType\":\"TRAFFIC_REPLAY\",\"departmentId\":20}

flybirds 运行的时候 otherParams 需要将\修改成\* 进行转义
```



2）配置behave运行脚本（调试进入behave运行之后的代码，使用这种方式运行代码，配置behave）

![image-20240515103132962](assets/image-20240515103132962.png)

![image-20240528172949148](assets/image-20240528172949148.png)

配置参数查找：

![image-20240515103027468](assets/image-20240515103027468.png)



3)修改扩展包里的flybirds脚本

从site-packages找到flybirds并修改为：

```
def load_steps():
    try:
        os.environ["extend_pkg_list"] = 'trip_flybirds_plugin'
        if os.environ.get("extend_pkg_list") is not None:
            extend_pkg = os.environ.get("extend_pkg_list")
            extend_pkg_list = extend_pkg.split(",")
            if len(extend_pkg_list) > 0:
                for pkg in extend_pkg_list:
                    if pkg is not None and pkg != "":
                        logger.info(f"load extend package:{pkg}")
                        load_pkg_by_ns(f"{pkg}.dsl.step")
    except Exception as load_ex:
        logger.info(f"load extend package error :f{load_ex}")
```

![image-20240517173706386](assets/image-20240517173706386.png)



注意事项：

a）若本地执行时，有些js 注入方法不成功时，可将扩展包中的js 复制到本地的config/js中

b）若要查看浏览器界面：headless 需改为false

c）代码覆盖率本地调试不需要：

​	coverage_flag删掉

​	otherparam中base64解码，把coverage相关的参数删掉，再编码替换掉otherparam的原参数



#### 1.2.2、plugin代码打包

Terminal执行命令：python setup.py install 

命令说明：通过 setup.py 的文件配置，拉取当前代码setup.py 中设置的version版本到本地，所以本地修改后必须merge合并上去，再拉取最新代码后执行此命令。不然会直接覆盖已更新的代码。也可通过直接pip3 upgrade命令更新。

```
升级flybirds和plugin包:
pip3 install --upgrade -i http:``//pypi.infosec.uat.qa.nt.ctripcorp.com/simple --trusted-host pypi.infosec.uat.qa.nt.ctripcorp.com flybirds

pip3 install --upgrade -i http://pypi.infosec.uat.qa.nt.ctripcorp.com/simple --trusted-host pypi.infosec.uat.qa.nt.ctripcorp.com --extra-index-url=http://mirrors.ops.ctripcorp.com/latest/pypi/simple/ --trusted-host mirrors.ops.ctripcorp.com trip_flybirds_plugin
```



### 1.3、android  debug 配置

实现android debug，先用airtest连接设备，如果没有安装包就用adb命令安装下，命令如下：

adb install -g Ctrip_V8.68.4_AVDzdhb_test_FAT_18765522.apk

![image-20240517131615395](assets/image-20240517131615395.png)



#### 1.3.1、连接slave机器

**app连接slave机器：**

1、jekins中查询ip地址

2、cmd输入命令：ssh -p 5022 slave@10.5.160.140

3、进入后选yes然后输入密码

user: slave
pwd: sle12+*mgr

4、 sudo su  切换为root

5、查询进程内存占用：ps aux | grep ffmpeg| awk '{sum+=$6} END {print sum/1024 " MB"}'

修改机器中的文件： cd /usr/local/lib/python308/site-packages



**web连接slave机器：**

ssh -p 5022 root@ip

其他同上，web连接成功后就是root模式不需要额外输入sudo su



#### 1.3.2、构建并运行项目

设置参数并构建项目：

http://ci.flight.qa.nt.ctripcorp.com:8080/jenkins/job/%E8%A1%8C%E4%B8%BA%E5%9B%9E%E6%94%BE_yxw/

[http://ci.flight.qa.nt.ctripcorp.com:8080/jenkins/job/%E8%A1%8C%E4%B8%BA%E5%9B%9E%E6%94%BE_yxw/configure](http://ci.flight.qa.nt.ctripcorp.com:8080/jenkins/job/行为回放_yxw/configure)

![image-20240527201655008](assets/image-20240527201655008.png)**device_id**需要在flybirds平台中寻找：

![image-20240527201842413](assets/image-20240527201842413.png)









## 2、代码理解

android远程debug功能：

http://git.dev.sh.ctripcorp.com/flight-mobile-qatools/trip-flybird-extend/-/merge_requests/213/diffs







## X、bug记录

bug记录：

1、商旅走真实登录但是flytest里面的mock里面配置了cookie值导致冲突造成新页面登录失败

![image-20240524170358515](assets/image-20240524170358515.png)



![image-20240524170623963](assets/image-20240524170623963.png)

解决： 去掉flytest中的mock配置





run -P features\test\android\replay.feature --define platform=android --define browserType=chromium --define runId=72865www --define execId=707024 --define buildId=15 --define pkgVersion={} --define otherParam={\"deviceType\":\"EMULATOR\",\"options_flytestDepartmentId\":\"14\",\"appName\":\"ctrip.android.view\",\"departmentId\":\"14\","\"appSourceType\":\"1\",\"frameworkType\":\"FLYBIRDS\",\"platformType\":\"ANDROID\",\"options_deviceResolution\":\"1644x3328\",\"options_flytestTeamId\":\"42\",\"appActivity\":\"ctrip.android.view/ctrip.business.splash.CtripSplashActivity\",\"options_flytestProjectId\":\"288\",\"options_hardType\":\"1\",\"coverageFlag\":\"0\",\"options_appJumpUrl\":\"L3JuX2ZsaWdodF9yZXBsYXkvX2Nybl9jb25maWc/Q1JOTW9kdWxlTmFtZT1mbGlnaHRfcmVwbGF5JkNSTlR5cGU9MSZpbml0aWFsUGFnZT1SZXBsYXlQYWdlJnJlcGxheU1vZGU9MSZyZXBsYXlUb2tlbj1STl8wX2FuZHJvaWRfMTcxNjgwNzU4ODg1Ml80MTFfODMyXzRfcm5fZmxpZ2h0X3Bvc3RzZXJ2aWNlX2I3ZTc3YThkLTRmMTgtYTZmNl81LjAuMF8xXzAmc1RpbWU9MjAyNC0wNS0yNyUyMDE3JTNBNTklM0E1OSZlVGltZT0yMDI0LTA1LTI3JTIwMjAlM0EwNCUzQTAwJmlzSW5zdXJhbmNlPTAmaXNQUk9EPXRydWUmY2Jjb25maWc9ZXlKbGFXUWlPaUpUTkRNMk1Ea2lMQ0pwYzFOb2IzY2lPbVpoYkhObGZRJTNEJTNEJmNyZWF0ZVRpbWVTdGFtcD0xNzE2OTU0NDM3NTE3JnBrZ0J1aWxkSWQ9MTg5MTE4Njg=\",\"options_appTimeout\":\"38\",\"options_appToken\":\"Uk5fMF9hbmRyb2lkXzE3MTY4MDc1ODg4NTJfNDExXzgzMl80X3JuX2ZsaWdodF9wb3N0c2VydmljZV9iN2U3N2E4ZC00ZjE4LWE2ZjZfNS4wLjBfMV8w\"}



2、商旅case（feature）中有多个scenario，仅首个执行了其余的没有执行

解决：browserExitAfterCase：false 字段 表示case执行后是否退出浏览器，商旅配置的是false，但是每个scenario执行前都会进行登录操作，所以需要改成true，执行每个scenario退出浏览器。这样后续case才可执行完毕。





## P、项目记录

1、商旅登录功能实现

2、ffmpeg app录制视频调优，由原来的2G下降为300M，视频质量稍微下降（原因：H.264格式改为webm格式）

4、通过nodejs  websocket 实现转发功能




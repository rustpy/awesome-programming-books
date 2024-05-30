**nodejs简介：**

node.js为javascript运行环境，类似浏览器为HTML和CSS运行环境。

编辑器：Visual Studio Code

・HTML 定义了网页的内容

・CSS 描述了网页的布局

・JavaScript 网页的行为

HTTP、HTTPS、WS、WSS区别：

![img](assets/bg2017051503.jpg)







## 1、javascript基础知识

avaScript 是 Web 的编程语言，所有现代的 HTML 页面都可以使用 JavaScript。

### 1.1、js中const,var,let区别

1.const（常量）定义的变量不可以修改，而且必须初始化。

2.var（变量）定义的变量可以修改，如果不初始化会输出undefined，不会报错。

3.let是块级作用域，函数内部使用let定义后，对函数外部无影响。

```javascript
const b = 2;//正确
// const b;//错误，必须初始化 
console.log('函数外const定义b：' + b);//有输出值
// b = 5;
// console.log('函数外修改const定义b：' + b);//无法输出 


var a = 1;
// var a;//不会报错
console.log('函数外var定义a：' + a);//可以输出a=1
function change(){
a = 4;
console.log('函数内var定义a：' + a);//可以输出a=4
} 
change();
console.log('函数调用后var定义a为函数内部修改值：' + a);//可以输出a=4

let c = 3;
console.log('函数外let定义c：' + c);//输出c=3
function change(){
let c = 6;
console.log('函数内let定义c：' + c);//输出c=6
} 
change();
console.log('函数调用后let定义c不受函数内部定义影响：' + c);//输出c=3
```



## 1、Node.js示例

### 1.1、nodejs搭建web服务器

请求方式为：http://127.1.1.2:6443/postinfo?test1&test2  可用get/post请求

```javascript
// 来源“https://www.bilibili.com/video/BV1Bp4y1P73Y/?spm_id_from=333.337.search-card.all.click&vd_source=c72f3b18d4102d04acc65f53171cc909
const http = require('http')
const url = require('url')
const qs = require('querystring')

// 创建server
const server = http.createServer((req, res) => {
  // 对req的url进行转换,pathname为接口名，query为?后续参数
  const {pathname, query} =url.parse(req.url)
  // 判断请求是GET还是POST请求并设置返回体
  if (req.method === 'GET' && pathname == '/getinfo') {
      res.setHeader('Content-Type', 'text/plain;charset=utf-8')
      console.log(pathname)
      console.log(query)
      console.log(qs.parse(query))
      // 直接通过浏览器请求locoalhost:3000/getinfo
      res.end('响应getinfo的请求')
    } else if (req.method === 'POST' && pathname === '/postinfo'){
      let data = ''
      req.on('data', temp => {
        data += temp
      })
      req.on('end', () => {
        console.log(qs.parse(data))
        res.setHeader('Content-Type', 'text/plain;charset=utf-8')
        res.end('响应了POST请求')
      })
    } else {
      res.statusCode = 404
      res.end('Not Found')
    }


})

// 创建server的监听端口
server.listen(6443, '127.1.1.2',() => {
  console.log('Sever is running')
})
```



### 1.2、nodejs实现websocket转发

对websocket通信进行转发，通过中转服务转发到指定的服务器实现websocket转发通信。

#### 1.2.1、代码示例

转发server：

```javascript
const WebSocket = require('ws');

// 创建WebSocket服务器
const server = new WebSocket.Server({ port: 8002 });

server.on('connection', function (ws) {
  // 创建到目标服务器的WebSocket连接
  const targetWs = new WebSocket('ws://127.0.0.1:8003');

  targetWs.on('error', (error) => {
    console.error('WebSocket error: ', error);
  });

  targetWs.on('open', function open() {
    // 监听客户端消息事件
    ws.on('message', function incoming(message) {
      console.log("收到的信息为:" + message);
      // 将客户端消息转发到目标服务器
      targetWs.send(message);
      console.log("targetWs.send(message)" + message);
    });
  });

  // 监听目标服务器消息事件
  targetWs.on('message', (message) => {
    // 将目标服务器消息转发给客户端
    ws.send(message);
    console.log("ws.send(message)" + message);
  });

  // 监听连接关闭事件
  ws.on('close', () => {
    // 关闭与目标服务器的连接
    targetWs.close();
    console.log("targetWs.close()");
  });
});

console.log("WebSocket建立完毕");
```

目标server：

```javascript
var WebSocket = require('ws');
console.log("开始建立连接...");

var server = new WebSocket.Server({ port: 8003 });

server.on('connection', function connection(ws) {
  ws.on('message', function incoming(message) {
    console.log("收到的信息为:" + message);
    ws.send(message);
  });

  ws.on('close', function close() {
    console.log("关闭连接");
  });

  ws.on('error', function error() {
    console.log("异常关闭");
  });
});

console.log("WebSocket建立完毕");
```

外部请求：

```html
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <style>
        .kuang{text-align: center;margin-top:200px;}
        #mess{text-align: center}
        .value{width: 200px;height:200px;border:1px solid;text-align: center;line-height: 200px;display: inline-block;}
    </style>
</head>
<body>
    <div id="mess">正在连接...</div>
    <div class="kuang">
        <div class="value" id="value1">测试按钮1</div>
        <div class="value" id="value2">test button2</div>
        <div class="value" id="value3">测试按钮3</div>
    </div>

    <script>
        var mess = document.getElementById("mess");
        if(window.WebSocket){
            var ws = new WebSocket('ws://127.0.0.1:8002');

            ws.onopen = function(e){
                console.log("连接服务器成功");
                ws.send("game1");
            }
            ws.onclose = function(e){
                console.log("服务器关闭");
            }
            ws.onerror = function(){
                console.log("连接出错");
            }

            ws.onmessage = function(e){
                mess.innerHTML = "连接成功"
                document.querySelector(".kuang").onclick = function(e){
                    var time = new Date();
                    ws.send(time + "  game1点击了“" + e.target.innerHTML+"”");
                }
            }
        }
    </script>
</body>
</html>
```

逻辑链路为：外部请求（HTMl）ws://127.0.0.1:8002 请求连接（直接点击HTML文件通过浏览器请求） - 中转server监听8002端口（通过node命令启动） - 建立连接后转发至ws://127.0.0.1:8003 - 目标server监听8003段口并于中转端口建立连接（通过node命令启动）






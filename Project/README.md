## 简介
## health-care
前后端分离的一个项目————个人健康平台管理<br/>
技术栈为SpringBoot+Vue+MyBatis+MySQL<br/>
后端端口为9000 前端端口为8080<br/>
sportmanage是后端  sportplay是前端

## leyou-mall
是leyou微服务商城。里面leyou为全部的后端微服务，leyou-manage-web是商品后台管理的前端页面,leyou-portal是整个商城的前端。
### leyou-gateway
网关微服务。  所有的其他微服务都要经过网关的转发，zuul是我们服务的统一入口。
### leyou-registry
注册中心。eureka是服务注册中心，服务提供者向eureka注册自己的信息，消费者向eureka订阅服务。
### leyou-auth
为授权中心  利用RSA生成公钥和私钥  存储在本地文件夹下  zuul和各个微服务也要保存公钥；<br/>
用户请求登录时，授权中心对其校验，通过后用私钥对jwt进行加密，返回jwt给浏览器。<br/>当请求到达微服务时，微服务直接用公钥对其jwt进行解析，获取用户信息。
### leyou-user
用户中心微服务。用于检查用户注册时的用户名和密码以及手机号是否合法。
### leyou-sms
短信微服务。使用阿里云短信api，短信微服务监听MQ,收到消息后发送短信。短信验证码存在redis中，并设置过期时间。
### leyou-search
搜索微服务。 用到了elasticsearch和kibana，进行商品的搜索。
### leyou-cart
购物车微服务。分为用户未登录和已登录两种情况。如果没登录则是将商品存到localstorage中，结算时会跳转到登录页面让用户登录，登录之后还可以继续添加商品，并且和未登录前添加在购物车的商品合并下单。
### leyou-item
商品管理微服务。对商品的品牌、规格参数等做修改，为后台提供接口。
### leyou-goods-web
商品详情页。要配置MQ进行监听队列，当商品后台修改了价格、规格等参数时能及时更新。
### leyou-order
订单微服务。创建订单，查询订单，根据订单号生成微信付款链接等。
### leyou-upload
文件上传微服务。在修改商品信息的时候，上传商品的图片要用到。

## leyou商城总结
后台管理系统端口为9001；门户系统为9002；网关服务为10010。<br/>
leyou-portal门户系统的启动:live-server --port=9002；<br/>
leyou-manage-web启动：npm start<br/>
技术栈还挺多的: SpringBoot+MyBatis+Vue+JS+Html+Thymeleaf+Redis+MySql+RabbitMQ+FastDFS+Elasticsearch+hibernate validator等。<br/>

## Tips
1.在centos虚拟机上配置FastDFS并访问见：https://blog.csdn.net/qq_25590283/article/details/107159992 <br/>
2.在windows系统下修改本机host文件需要先修改其读写权限才能进行修改。<br/>
3.leyou商城真真的干了一个月，7.1号开始做的，今天7.31号才弄完。任重而道远啊，不过确实学到了很多！
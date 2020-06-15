### Docker

#### 6/14   :bullettrain_front:

#### 1.简介

是一个开源的应用容器引擎。

支持把已经安装好的软件打包成镜像  称为容器  其他人想用只需要安装docker运行即可。

在linux上安装docker后，执行`docker search 关键字` 来搜索镜像的详细信息。然后使用`docker pull 镜像名:tag` 下载镜像。

`docker images` 查看全部镜像

#### 2.容器操作

软件镜像——运行镜像——产生一个容器

根据镜像启动容器：`docker run --name 自己起一个名字 -d 镜像:tag`

想要真正访问该容器，还要做端口映射：docker run -d -p 8888:8080 tomcat  表示将服务器/主机的8888端口映射到tomcat的8080端口

一个镜像可以启动多个容器，互不干扰。

要关闭linux防火墙 外网才能正常访问。


























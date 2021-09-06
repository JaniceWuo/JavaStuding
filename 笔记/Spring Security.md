Spring Security 充分利用Spring Ioc、DI、AOP功能。

以往流行的是Apache Shiro，一个轻量级安全框架。



"认证"和“授权”是Spring Security的两个重要核心功能。认证就是系统去判断该用户是否能登录。授权是判断该用户是否有权限去做某些事情。



表单如果是post，就会进入403，改为get才正确跳转。
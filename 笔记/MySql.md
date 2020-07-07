### MYSQL数据库老杜教程（动力节点）

7/7

#### SQL语句分类：

DQL（数据查询语言）：查询语句，凡是select语句都是DQL

DML（数据操作语言）:insert delete update 对表数据增删改

DDL（数据定义语言）：create drop alter 对表结构增删改

TCL（事务控制语言）：commit  rollback

DCL (数据控制语言)：grant授权 revoke撤销权限



sql脚本中的数据量太大的时候，无法打开，请使用source命令完成初始化。

使用desc table名；显示表结构



##### 条件查询

select 字段，字段... from 表名 where...  (先执行from 然后where 最后select)

between...and...是闭区间  要注意左小右大

找出薪资大于1000并且部门编号是20或30部门的员工：（注意and会自动匹配）

select ename,sal,deptno from emp where sal>1000 and (deptno = 20 or deptno = 30);

in等同于or       in(200,500)意思是等于200或者500

##### 模糊查询like

%代表任意多个字符  _代表任意一个字符

##### 排序


































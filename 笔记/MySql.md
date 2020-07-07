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

ORDER BY 默认是升序

asc是升序  desc是降序  select ename,sal from emp order by sal desc;//降序

例子：按照工资降序排列，当工资相同时按照名字的升序：

 select ename,sal from emp order by sal desc，ename asc;

注意：越靠前的字段越能起到主导作用，只有当前面的字段无法完成排序的时候，才会启用后面的字段。

**一条sql语句的执行顺序**   

select *            3

from table       1

where 条件       2

order by ...        4

##### 分组

**分组函数自动忽略null**    不需要在后面加上where xxx is not null;

分组函数又叫多行处理函数    有sum()  count()  avg()






















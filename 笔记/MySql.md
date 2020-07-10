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

例子：**按照工资降序排列，当工资相同时按照名字的升序**：

 select ename,sal from emp order by sal desc，ename asc;

注意：越靠前的字段越能起到主导作用，只有当前面的字段无法完成排序的时候，才会启用后面的字段。

**一条sql语句的执行顺序**   

select *            3

from table       1

where 条件       2

order by ...        4

##### 分组函数

**分组函数自动忽略null**    不需要在后面加上where xxx is not null;

分组函数又叫多行处理函数    有sum()  count()  avg()   max()  min()

group by:按照某个字段或者某些字段进行分组

having: having是对分组之后的数据进行再次过滤  所以有group by才能用having

分组函数一般都会和group by联合使用，这也是为什么它被称为分组函数的原因。

当sql语句没有group by时，其实是缺省了。

**任何一个分组函数都是在group by执行结束之后才执行**

例：找出**每个**工作岗位的最高薪资：select max(sal),job from emp group by job;

例：找出工资高于平均工资的员工。

![image-20200708112031886](C:\Users\zhuowei\AppData\Roaming\Typora\typora-user-images\image-20200708112031886.png)

**where后面不能直接用分组函数**   

正确的sql：select ename,sal from emp where sal > (select avg(sal) from emp); 

多个字段可以联合起来一块分组：

例：找出每个部门不同工作岗位的最高薪资

​      select deptno,job,max(sal) from emp group by deptno,job;

例：找出每个部门的最高薪资，要求显示最高薪资大于2900的数据。

有两种方法：

1. select max(sal),deptno from emp group by deptno having max(sal) > 2900; //这种方法效率低

​    2.select max(sal),deptno from emp where sal > 2900 group by deptno;

但是where搞不定的就只能用having了

 

#### 7/8

##### 查询结果去重

distinct只能出现在所有字段的最前面

select distinct deptno,job from emp 是对deptno,job两个联合字段进行去重

例：统计岗位的数量

select count(distinct job) from emp;

##### 连接查询

实际开发一般都是多张表联合查询

根据表的连接方式来划分，包括：

​         内连接：等值连接   非等值连接    自连接

​         外连接：左外连接   右外连接

​         全连接 （很少用）

笛卡尔积现象：当两张表进行连接查询的时候，没有任何条件进行限制，最终的查询结果条数是两张表记录条数的乘积。

表的别名：select e.ename,d.dname from emp e,dept d;

表的别名有什么好处？第一：执行效率高  第二：可读性好。

如果不加别名的话，上面查询ename的时候会从emp和dept两张表里面找ename。

怎么避免笛卡尔积现象？ 加条件进行过滤。 不会减少记录的匹配次数。

##### 内连接

A表和B表的数据匹配上就查  匹配不上就跳过  A表和B表时对等的。

等值连接：

select e.ename,d.dname from emp e join dept d on e.deptno = d.deptno;

非等值连接：连接条件中的关系是非等量关系。

select e.ename,e.sal,s.grade from emp e inner join salgrade s on e.sal between s.losal and s.hisal;

自连接：一张表看做两张表 自己连接自己。

案例：找出每个员工的上级领导，要求显示员工名和对应的领导名。

select a.ename,b.ename from emp a join emp b on a.mgr = b.empno; 

##### 外连接(使用更多)  使用外连接可以保证一张表的数据不丢失

假设A和B表进行连接，使用外连接的话，AB两张表中有一张表是主表，一张表是副表，主要查询主表中的数据，捎带着查询副表。当副表中的数据没有和主表的数据匹配时，会自动模拟出Null值。

案例：查每个员工的上级领导（要求查全部员工的  包含king的）

用左连接： select a.ename,b.ename from emp a left join emp b on a.mgr = b.empno;

用右连接：select a.ename,b.ename from emp b right join emp a on a.mgr = b.empno;



#### 7/9

##### 三张表怎么连接查询？

案例：找出每一个员工的部门名称、工资等级、以及上级领导。

select e.ename,d.dname,s.grade from emp e

join dept d on e.deptno = d.deptno

join salgrade s on e.sal between s.losal and s.hisal

left join emp el on e.mgr = el.empno;



##### union 可以将查询结果集相加

两张表的查询结果列数要相同。



#### 7/10

limit是mysql特有的，其他数据库没有。

语法：limit startIndex,length

案例：取出工资最高的前5名员工

select ename,sal from emp order by sal desc limit 0,5;

注意下标从0开始

每页显示pageSize条记录：第pageNo页：(pageNo - 1) * pageSize,pageSize

##### 建表

char和varchar的区别：char是定长 不智能   varchar可以动态的分配内存。

char和varchar怎么选择？如果某个字段数据长度不发生改变，比如性别、生日都是选char；长度不确定如简介、姓名就用varchar。

##### 约束

添加约束的目的是为了保证表中的数据的合法性、有效性、完整性。

常见的约束有：非空约束、唯一约束(unique)、主键约束、外键约束。

唯一约束修饰的字段具有唯一性，不能重复，但可以为null。

给两个列或者多个列添加unique: unique(usercode,username);

外键约束：

​     业务背景：设计数据表，维护学生和班级信息。

​      最好设计成两张表：班级表和学生表，如果只设计一张表有冗余。

​      一个班级里可以有多个学生，在学生表里加一个列class_id，将其和班级表的cid对应起来：

​      foreign key (class_id) references classes (id);

​      这样，在学生表插入数据的时候，如果class_id是班级表中不存在的cid，则插入失败。

外键约束的列所引用的列不一定是主键，但要有**唯一性**。


























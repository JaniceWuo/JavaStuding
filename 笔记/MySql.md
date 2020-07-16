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



#### 7/14

Mysql默认采用InnoDB存储引擎  支持事务

MyISAM引擎不支持事务  它采用三个文件表示每个表 ：格式文件（存储表结构的定义）  数据文件（存储表行的内容）  索引文件（存储表上的索引）

InnoDB支持事务、行级锁。 安全得到保障。



#### 事务

什么是事务？  一个事务是一个完整的业务逻辑单元，不可再分。

比如银行账户转账，要么同时失败要么同时成功。

事务的存在是为了保证数据的完整性，安全性。

##### 事务的四大特性：

A  原子性：事务是最小的工作单元，不可再分。

C  一致性：事务必须保证多条DML语句同时成功或者失败。

I  隔离性：事务A和事务B之间具有隔离性。

D 持久性：最终数据必须持久化到硬盘文件中，事务才算成功的结束。

##### 事务之间的隔离性

存在隔离级别，理论上有**四个**：

第一级别：读未提交（read uncommitted)  当前事务可以读到对方**未提交**的事务。

​                    存在脏读现象，表示读到了脏的数据。

第二级别：读已提交(read committed)  对方事务**提交之后**的数据我方可以读到。没有提交的就读不到。

​                    存在的问题：不可重复读。（就是说我一开始查询数据是10条，中途别人删了一条数据之后我再读取变成9条了）

​                    解决了：脏读现象。

第三级别：可重复读（repeatable read)   解决了：不可重复读问题。

​                    （意思是原本是10条，别人删了1条，我读的还是10条）

​                    存在的问题是：读取到的数据是幻象。

第四级别：序列化读/串行化读

​                    解决了所有问题。但是效率低，需要事务排队。

mysql数据库默认的隔离级别是：可重复读。

oracle数据库默认的隔离级别是：读已提交。



#### 7/15

mysql默认是支持事务自动提交的，执行一条DML语句，其实是提交了事务。之后再rollback也是无法撤销的。

想要手动进行事务控制，输入START TRANSACTION 命令，之后如果没有输入commit的话，回滚会撤销所有的增删改操作，但是commit之后再rollback也撤销不了了。

##### 设置Mysql的隔离级别

读未提交：set global transaction isolation level read uncommitted;

读已提交：set global transaction isolation level read committed;

可重复读：读到的是幻觉。

串行化：需要排队，就是一个事务在没有提交的时候，另一个事务不能进入这张表进行操作，会导致用户体验不好。



#### 索引

##### 什么是索引？有什么用？

索引就相当于一本书的目录，通过目录可以快速找到对应的资源。

根据索引检索，效率很高。

##### 索引为什么可以提高检索效率?

最根本的原理是缩小了扫描的范围。

索引虽然可以提高检索效率，但是不能随意的添加索引，因为索引也是数据库当中的对象，也需要数据库不断的维护。如果表中的数据经常被修改就不适合添加索引，因为数据一旦被修改，索引就需要重新排序。

##### 添加索引：

create index 索引名称 on 表名(字段名)

比如给薪资sal字段添加索引：create index emp_sal_index on emp(sal)；

##### 删除索引：

drop index 索引名称 on 表名；

##### 索引底层采用的数据结构是：b+树

##### 索引的实现原理？

通过B + 树缩小扫描范围，底层索引进行了排序，分区，索引会携带数据在表中的物理地址，最终通过索引检索到数据后，获取到关联的物理地址，通过物理地址定位到表中的数据，效率是最高的。

##### 索引的分类

单一索引：给单个字段添加索引

复合索引：给多个字段联合起来添加1个索引

主键索引：主键上会自动添加索引

唯一索引：有unique约束的字段上会自动添加索引

##### 索引什么时候失效？

模糊查询的时候，第一个通配符使用的是%，这个时候索引是失效的。

##### 索引中除了存储列的值，还存储着一个指向在行数据的索引

b+树：

非叶子节点不存储真实的数据，只存储指引搜索方向的数据项。

3层的b+树可以表示上百万的数据，如果上百万的数据查找只需要三次IO，性能提高将是巨大的，如果没有索引，每个数据项都要发生一次IO，那么总共需要百万次的IO，显然成本非常非常高。





##### sql例题：

##### 1.查出每个部门最高薪资的人员名字。

注意：按部门分组后查最高薪资很容易，但是同时还要查名字，并且，一个部门最高薪资可能不止一个人。

**错误的sql**:  select ename,deptno,max(sal) as maxsal from emp group by deptno;

这种查出来每个部门就一个最高员工拿最高薪资。

**正确的sql**：

select e.ename,t.deptno,t.maxsal from (select deptno,max(sal) as maxsal from emp group by deptno) t join emp e on t.deptno = e.deptno and t.maxsal = e.sal;

on后面的条件有两个，缺一不可，既要保证部门相同，而且由于是要查最高薪资的员工，所以薪资也要相等，不然就是全部薪资都查出来了。

##### 2.哪些人的薪水在部门的平均薪水之上。

和上题思想一样，要先查出每个部门的平均薪水，然后将其当做t表，t表和emp表连接。

select t.*,e.ename,e.sal from emp e join (select deptno,avg(sal) as avgsal from emp group by deptno) t on e.deptno = t.deptno and e.sal > t.avgsal;

##### 3.不准用组函数(Max)取得最高薪水。

方法一：降序

select ename,sal from emp order by sal desc limit 1;

方法二：表的自连接(

只有最大的那个薪资才找不到比它大的数。

select sal from emp where sal not in (select distinct a.sal from emp a join emp b on a.sal < b.sal);




















































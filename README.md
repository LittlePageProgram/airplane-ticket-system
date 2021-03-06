# airplane-ticket-system  

<p align="right">
  My Database Assignment 2020.1.7
</p>

# Topic

### 1.选题

民航订票管理系统主要是为机场、航空公司和客户三方服务。航空公司提供航线和飞机的资料，机场则对在本机场起飞和降落的航班和机票进行管理，而客户能得到的服务应该有查询航班路线和剩余票数，以及网上订票等功能。

客户可以分为两类：一类是普通客户，对于普通客户只有普通的查询功能和订票功能，没有相应的票价优惠；另一类是经常旅客，需要办理注册手续，但增加了里程积分功能和积分优惠政策。

### 1.系统功能设计

系统功能是在实际开发设计过程中经过调研、分析用户需求，和用户一起共同确定下来，是系统为满足用户需求所应完成的功能。本课程设计模拟一个小型民航定票管理系统。

航空客运订票的业务活动包括；查询航线、客票预订和办理退票等。试设计一个航空客运订票系统，以使上述业务可以借助计算机来完成。

包含信息如下：

（1）航空公司信息，如南方航空、东方航空。

（2）每条航线所涉及的信息有：起点、终点 、航班号、飞机型号、起飞时间、到达时间、乘员定额（头等舱、经济舱）、里程、属于哪个航空公司、标准价格等。

（3）每种飞机型号（如波音747）都有固定的座位表。

（4）每个航班涉及的信息有：航班号、日期、 已订票的客户名单及仓位（头等舱、经济舱）。

 （5）顾客信息及顾客购买机票的信息。

（2）系统能实现的操作和功能如下：

①查询航线：根据旅客提出的起点、终点站名及航班日期输出下列信息：航班号、飞机型号、余票额、价格等。机票的价格是浮动的，请自行确定一个价格浮动策略。

②承办订票业务：根据客户提出的要求（航班号、日期）查询该航班票额情况，若尚有余票，则为客户办理订票手续。顾客能自动按航空公司积累已飞行的里程，里程达到一定的数量，可以升级为银卡、金卡等客户，购票时可以享受不同的打折优惠。

③承办退票业务：根据客户提供的情况（日期、航班），为客户办理退票手续。

说明：请参考携程网的机票购买功能。

### 2.数据库设计

按照数据库设计的步骤完成。设计者可以在上述信息的基础上适当扩充一些基本信息需求。



### 3.数据完整性设计

设计者应认真分析和思考各个表之间的关系，合理设计和实施数据完整性原则。

1）给每个表实施主键及外键约束。

2）设定缺省约束。如性别，如有二个表中货币类型的列都将其定义为缺省值。

3）设置非空约束。如客户姓名。

4）实施CHECK约束。如飞机的座位数不能小于0。

5）实施规则。如编号及飞机型号的一些设置规定。

### 4. 数据库对象设计

系统需要设计并创建的视图、触发器和存储过程如下：

（1）设计一个存储过程，实现客人订票处理。

（2）设计一个存储过程，实现机票价格浮动策略

（3）自行设计视图

（4）自行设计触发器。

注：其它对象用户可按需求自行设计。

### 5.客户端设计

使用Java或。NET程序设计以及JDBC、ODBC数据库等编程技术实现一个界面简洁友好、操作简单客户端系统。



# Technology

### 1.Frontend

- semantic-UI

### 2.Backend

- SpringBoot
- Lombok
- spring-aop
- Spring-starter-web
- log4j

### 3.Database

- SQL SERVER

### 4.Used Tools

- IDEA
- Navicat
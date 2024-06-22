# 基于Android APP与Java Web的移动软件综合系统——选课及成绩管理系统

摘要：

本课程设计报告介绍了一个基于Android APP与Java Web的移动软件综合系统，用于选课及成绩管理。该系统包含了教务、授课教师和学生三类角色，每个角色具有不同的功能和权限。教务可以管理课程信息，授课教师可以管理个人简介、查看选课学生信息、填报课程成绩等，学生可以查看课程信息、查看授课教师简介、选课和查看课程成绩等功能。

关键词：Android APP、Java Web、选课、成绩管理、教务、授课教师、学生

 

## 一、绪论

本课程设计旨在开发一个移动软件综合系统，以提供选课及成绩管理的功能，方便教务、授课教师和学生进行相关操作。通过该系统，教务可以更有效地管理课程信息，授课教师可以方便地查看选课学生信息并填报课程成绩，学生可以方便地查看课程信息、选课和查看成绩。本课程设计将结合Android APP和Java Web技术，实现系统的前端和后端功能。

## 二、报告主体

### 2.1 系统需求分析

在系统需求分析中，我们详细研究了教务、授课教师和学生三类角色的功能需求。教务需要能够对课程信息进行管理，包括添加、修改和删除课程信息等。授课教师需要能够管理个人简介，并能够查看选课学生信息和填报课程成绩。学生需要能够查看课程信息、授课教师简介、选课和查看成绩。

程序需完成功能如下：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image002.jpg)

图 1

 

### 2.2 系统设计与实现

我采用了前后端分离的开发模式。Android APP作为前端界面，后端利用spring boot（整合了java web、tomcat等）完成开发。前端通过http的get、post请求与后端进行数据交互，实现各类功能。在系统设计过程中，我划分了模块，确保各个模块之间的协调与合作。

 

#### 2.2.1 Android前端部分

**1.****登录与注册界面**

该界面主要完成功能：

登录：

用户输入账号密码后，通过单选按钮选择自己的身份，程序由用户的选择向后端发送对应请求，根据后端响应请求从而验证账号是否存在和输入信息是否正确，登录成功后跳转至对应身份的界面。

 

注册：

与登录功能类似，用户先选择其身份，然后点击注册按钮，程序便会根据其选择跳转至对应身份的注册界面，点击保存按钮，前端就会发送对应的post请求至后端，后端校验数据是否符合规范后返回注册结果，注册成功后，自动跳转至登录界面，用户重新登录。

界面效果如下：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image004.jpg)

图 2

注册界面如下（其他身份类似）：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image006.jpg)

图 3

该界面部分代码逻辑如下：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image008.jpg)

图 4

**2.****管理员界面**

管理员主要的功能就是管理课程信息，管理员在点击对应的课程后，程序会跳转至更新对应课程信息的更新界面，管理员在填写完信息后点击保存按钮即可修改信息

管理员主界面：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image010.jpg)

图 5

管理员修改课程信息界面：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image012.jpg)

图 6

选择时间界面：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image014.jpg)

图 7

**3.****教师界面**

教师可在主界面查看所属课程，点击课程即可查看选课的学生信息，若课程处于已结课状态，可以给学生录入成绩。点击个人简介按钮即可修改个人简介（个人简介界面添加了查询回显功能，一进入界面即可看到自己的信息）。点击填报课程按钮，程序会跳转至填报课程界面，提交的课程都是未审核状态，需要管理员审核，审核通过的课程，学生才可以选课。

教师主界面：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image016.jpg)

图 8

其余界面：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image018.jpg) ![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image020.jpg) ![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image022.jpg)

图 9

**4.****学生界面**

学生可在主界面查看自己的课程信息，点击课程即可查看教师简介

选课：

点击选课按钮会跳转至选课界面，选课界面只会显示学生未选过、在选课时间内且课程属于已审核状态的课程，学生点击选课后会发送请求给后端，后端进行校验逻辑，给前端返回结果

退课：

退课界面只会显示学生已选课程且当前时间处于退课时间内的课程，点击退课按钮即可完成退课；

学生主界面：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image024.jpg)

图 10

学生其他界面：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image026.jpg) ![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image028.jpg) ![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image030.jpg)

图 11

#### 2.2.2 java后端部分

**1.****数据库部分**

所有表如下：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image032.jpg)

图 12

**2.Java Web****部分**

根据spring boot框架实现后端，将后端大致分为三层架构controller（表现）层、service（业务逻辑）层、mapper（持久）层，pojo为一些实体类。

包结构如下：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image034.jpg)

图 13

 

**Controller****层**：

接受前端发来的请求，接收发送过来的信息

Get请求通过url发送，直接在形参列表中写出同名参数即可

Post请求发送json数据，在形参前加入@RquestBody注解即可

 

admin部分举例：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image036.jpg)

图 14

**Service****层**：

该层用于对前端发送来的数据进行处理，最后达到业务所需效果

如下图中的管理员更新课程信息的方法，该层需要对管理员发送来的数据进行校验，查看是否符合规范

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image038.jpg)

图 15

**Mapper****层**：

该层用于与数据库交互，对数据库的数据进行增删改查，利用了Mybatis技术，使用注解或映射文件的方式编写sql语句

如下图中的adminMapper：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image040.jpg)

图 16

### 2.3 系统测试

为了验证系统的功能和性能，我们进行了系统测试与评价，以确保系统能够正常运行并满足用户需求。在测试过程中，我们注重对系统各个功能模块的全面覆盖和详尽测试，以保证其正常运行和数据交互的准确性。

其中，教师修改个人简介功能是系统的一个重要模块，我们对其进行了详细的测试。通过系统测试，我得出了以下结论：教师修改个人简介功能在系统中运行良好，能够满足教师对个人简介信息的修改需求。系统能够准确地保存和更新教师的个人简介信息，并对输入数据进行了有效的验证和过滤，确保数据的完整性和安全性。然而，我也发现了一些问题和改进的空间。例如，系统在处理异常情况下的错误提示不够友好，需要进一步改进用户体验。

步骤1 教师登录：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image042.jpg)

图 17

后端接受请求打印日志：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image044.jpg)

图 18

步骤2 登录成功：

登录成功，教师进入主界面，查询相关课程信息

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image046.jpg)

图 19

后端日志信息：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image048.jpg)

图 20

步骤3 点击个人简介按钮：

点击个人简介按钮，跳转至修改个人简介界面，先向后端服务器发送查询请求，查询教师信息回显

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image050.jpg)

图 21

后端日志信息：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image052.jpg)

图 22

步骤4 修改个人简介：

修改个人简介，点击保存，向后端服务器发送保存请求，如下图，保存成功

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image054.jpg)

图 23

后端日志信息：

![img](file:///C:/Users/86184/AppData/Local/Temp/msohtmlclip1/01/clip_image056.jpg)

图 24

## 三、总结

本课程设计实现了一个基于Android APP与Java Web的移动软件综合系统，用于选课及成绩管理。本系统采用了前后端分离的开发模式，通过Android APP作为前端界面，Spring Boot作为后端框架，实现了一个功能完善的课程管理系统。在系统设计与实现过程中，我们对各个模块进行了合理的划分和设计，确保了系统的协调与合作。

在前端部分，我们实现了登录与注册界面，通过用户输入账号密码和选择身份来进行验证和身份识别，实现了安全的用户登录和注册功能。管理员界面具备课程信息管理功能，管理员可以更新课程信息并设置课程的时间。教师界面提供了课程查看、学生信息查看、成绩录入和个人简介修改等功能。学生界面则实现了课程查看、教师简介查看、选课和退课等功能。通过这些功能，用户可以方便地进行课程管理和信息查询，提高了工作和学习的效率。

在后端部分，我们采用了Spring Boot框架，将后端分为Controller层、Service层和Mapper层。Controller层负责接收前端请求和信息的处理，Service层实现了业务逻辑的处理，Mapper层与数据库进行交互，实现了数据的增删改查操作。通过这种分层架构，我们实现了代码的模块化和可维护性，提高了系统的可扩展性和可靠性。

总体而言，该课程管理系统具备了良好的用户界面和功能，能够满足管理员、教师和学生的不同需求。管理员可以方便地管理课程信息，教师可以查看学生信息并进行成绩录入，学生可以选课和查询课程信息。系统设计合理、功能完善，为用户提供了良好的使用体验。

然而，在系统的设计和实现过程中，仍然存在一些可以改进的地方。例如，可以考虑增加管理员对教师和学生信息的管理功能，完善系统的权限管理。另外，可以进一步优化系统的性能，提高数据交互的效率和响应速度。此外，还可以考虑引入用户反馈和问题报告机制，及时发现和解决系统中的问题。通过本次课程设计，我不仅巩固了Android APP和Java Web的开发技术，也加深了对移动软件综合系统的理解和应用能力。

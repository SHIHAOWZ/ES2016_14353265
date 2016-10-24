##安装ROS
***
####1. Ros简单介绍
Robot operation system，开源机器人操作系统。它提供类似操作系统所提供的功能，包含硬件抽象描述、底层驱动程
序管理、共用功能的执行、程序间的消息传递、程序发行包管理，它也提供一些工具程序和库用于获取、建立、编写和
运行多机整合的程序。ROS的首要设计目标是在机器人研发领域提高代码复用率。ROS是一种分布式处理框架(又名Nodes)。
这使可执行文件能被单独设计，并且在运行时松散耦合。这些过程可以封装到数据包(Packages)和堆栈(Stacks)中，以
便于共享和分发。ROS还支持代码库的联合系统。使得协作亦能被分发。
***
####2. 在ubuntu16.04上安装Ros
* Configure your Ubuntu repositories

Configure your Ubuntu repositories to allow "restricted," "universe," and "multiverse." 

* Setup your sources.list

Setup your computer to accept software from packages.ros.org.ROS Kinetic Xenial (Ubuntu 16.04) for debian packages. 

![pic1](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab5/pic/pic1.png)

* Set up your keys

![pig2](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab5/pic/pic2.png)

* Desktop-Full Install

![pig3](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab5/pic/pic3.png)

* Initialize rosdep

Before you can use ROS, you will need to initialize rosdep. rosdep enables you to easily install system dependencies for source you want to compile and is required to run some core components in ROS. 

![pig4](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab5/pic/pic4.png)

* Environment setup

It's convenient if the ROS environment variables are automatically added to your bash session every time a new shell is launched: 

![pig5](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab5/pic/pic5.png)

* Getting rosinstall

rosinstall is a frequently used command-line tool in ROS that is distributed separately. It enables you to easily download many source trees for ROS packages with one command. 

![pig6](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab5/pic/pic6.png)



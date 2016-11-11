##安装ROS和Cartographer及其测试
***
####1. Ros和Cartographer简单介绍
* Robot operation system，开源机器人操作系统。它提供类似操作系统所提供的功能，包含硬件抽象描述、底层驱动程
序管理、共用功能的执行、程序间的消息传递、程序发行包管理，它也提供一些工具程序和库用于获取、建立、编写和
运行多机整合的程序。ROS的首要设计目标是在机器人研发领域提高代码复用率。ROS是一种分布式处理框架(又名Nodes)。
这使可执行文件能被单独设计，并且在运行时松散耦合。这些过程可以封装到数据包(Packages)和堆栈(Stacks)中，以
便于共享和分发。ROS还支持代码库的联合系统。使得协作亦能被分发。

* Cartographer是谷歌开源的一个SLAM算法，基于激光雷达以及IMU（惯性处理单元）。该技术利用同步定位与制图技术（SLAM）绘制室内建筑平面图，能同时用于二维与三维空间的移动映射。
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
***
####3.Ros安装成功的测试

截至这步,ROS就算安装完了

下面我们来测试一下安装是否成功

测试教程[点击这里](http://www.th7.cn/system/lin/201504/103167.shtml)

* 安装完后打开终端,输入以下指令
   ```
   roscore
   ```
* 再开一个终端,输入下面的命令
```
rosrun turtlesim turtlesim_node
```
打开一个有小乌龟的界面,如下图：

![turtle1](https://github.com/SHIHAOWZ/ES2016_14353265/blob/master/assignment_lab5/pic/turtle1.png)

* 另外打开一个终端,输入指令
```
rosrun turtlesim turtle_teleop_key
```

这样就可以使用上下左右来控制乌龟走动了,之后会出现类似下面的界面:

![turtle2](https://github.com/SHIHAOWZ/ES2016_14353265/blob/master/assignment_lab5/pic/turtle2.png)


* 然后再打开第四个终端,输入指令
```
rosrun rqt_graph rqt_graph
```
可以看到ROS nodes以及Topic等图形展示

![turtle3](https://github.com/SHIHAOWZ/ES2016_14353265/blob/master/assignment_lab5/pic/turtle3.png)

*  当你的乌龟可以控制跑动时,说明你安装成功了。
***
####4.安装Cartographer
现在在ROS已经安装好了的基础上我们可以安装Cartographer了。

这部分参考了 http://www.cnblogs.com/hitcm/p/5939507.html 的教程。关键部分的命令如下：

* 安装Cartographer：
```
git clone https://github.com/hitcm/cartographer.git
```
```
cd cartographer/build
```
```
cmake .. -G Ninja
```
```
ninja
```
```
ninja test
```
```
sudo ninja install
```
* catkin_ws配置：
```
source ~/catkin_ws/devel/setup.bash
```
```
rospack profile
```
***
####5.Cartographer安装成功的测试
在博客里下载2D和3D的数据，然后运行相应的命令
```
roslaunch cartographer_ros demo_backpack_2d.launch bag_filename:=${HOME}/Downloads/cartographer_paper_deutsches_museum.bag
```
```
roslaunch cartographer_ros demo_backpack_3d.launch bag_filename:=${HOME}/Downloads/cartographer_3d_deutsches_museum.bag
```
得到2D效果图如下：

![2D](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab5/pic/2d.png)

得到3D效果图如下：

![3D](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab5/pic/3d.png)


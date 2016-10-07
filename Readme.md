#DOL configuration 
***
###A brief description of DOL

The distributed operation layer (DOL) is a software development framework to program parallel applications. The DOL allows to specify applications based on the Kahn process network model of computation and features a simulation engine based on SystemC. Moreover, the DOL provides an XML-based specification format to describe the implementation of a parallel application on a multi-processor systems, including binding and mapping.

![pig1](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/pic_res/pig1.png)

***

###How to install DOL

#####1.安装ubuntu和一些必要的环境

* sudo apt-get update

* sudo apt-get install ant

* sudo apt-get install openjdk-7-jdk

* sudo apt-get install unzip

附ubuntu16.04 安装 openjdk-7-jdk的方法

Ubuntu16.04的安装源已经默认没有openjdk7了，所以要自己手动添加仓库：

* sudo add-apt-repository ppa:openjdk-r/ppa

* sudo apt-get update

* sudo apt-get install openjdk-7-jdk  // OpenJdk 7安装：

* sudo add-apt-repository ppa:webupd8team/java

* sudo apt-get update

* sudo apt-get install oracle-java7-installer

* sudo update-alternatives --config java

* sudo update-alternatives --config javac

#####2.下载文件

Linux下**wget**命令可以从网上下载文件

* sudo wget http://www.accellera.org/images/downloads/standards/systemc/systemc-2.3.1.tgz

* sudo wget http://www.tik.ee.ethz.ch/~shapes/downloads/dol_ethz.zip

#####3.解压文件

新建dol的文件夹 

* $ mkdir dol

将dolethz.zip解压到 dol文件夹中

* $ unzip dol_ethz.zip -d dol

解压systemc

* $ tar -zxvf systemc-2.3.1.tgz

#####4.编译systemc

解压后进入systemc-2.3.1的目录下

* $ cd systemc-2.3.1

新建一个临时文件夹objdir

* $ mkdir objdir

进入该文件夹objdir

* $ cd objdir

运行configure(能根据系统的环境设置一下参数，用于编译)

* $ ../configure CXX=g++ --disable-async-updates

![pig2](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/pic_res/pig2.png)


编译

* $ sudo make install

![Pig3](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/pic_res/pig3.png)

记录当前的工作路径(会输出当前所在路径，记下来，待会有用)

* $    pwd

**得到的路径为/home/shihao14353265/systemc-2.3.1/objdir**

#####5.编译dol

进入刚刚dol的文件夹

*$	cd ../dol

修改build_zip.xml文件

* gksudo gedit build_zip.xml

找到下面两句话 

* property name="systemc.inc" value="YYY/include"/

* property name="systemc.lib" value="YYY/lib-linux/libsystemc.a"/

把YYY改成上一步pwd的结果（注意，对于  64位 系统的机器，lib-linux要改成lib-linux64）

![pig4](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/pic_res/pig4.png)

这里注意，gedit用gksudo才会避免权限的问题

然后是编译

* $	ant -f build_zip.xml all

若成功会显示build successful

#####6.运行一个实例

* $ cd build/bin/main

* $ ant -f runexample.xml -Dnumber=1

![pig5](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/pic_res/pig5.png)

***

###Experimental Experience
1. 上次配置实的主要的任务是配置那个systemc，那里要配置好才可以有最后的配置成功结果。我出bug的问题主要是用pwd获得的路径有问题，发现pwd给的路径不正确，多了那个临时文件夹objdir，因为如果直接用pwd给出的结果的话，会出现fatal error in systemc，我一查是路径不正确。第二个注意的时gedit修改文件的时候会出现权限问题。如果直接用sudo的话，会出现权限不够，我查了网上要用gksudo，那样就不会出现权限问题了

2. 这次实验的内容是git和github。感觉是很有用的东西。用git在本地创建一个仓库然后和远程的github上的远程仓库关联，或者说在github上先创建一个仓库，然后用git在本地克隆远程仓库。这两种方法都可以完成对仓库的修改，具体的实现方法那个廖雪峰老师的博客已经叙述的很清楚了。感觉看完那个博客，就可以一定程度上熟悉git，那个博客极易理解。

3. 就是使用markdown语言了，这个标记语言很利于打字，语法和排版都很容易学会。用起来感觉很方便。

4. 这里介绍一下我自己为本地图片生成url的方法。因为在markdown标记语言中，插入图片需要一个url，对于本地图片，没有url不好插入。我的方法还是利用github，把图片当成文件先上传到github的仓库中，然后点击图片，里面有一个下载按钮，这样就可以得到图片的url了，相当方便。 

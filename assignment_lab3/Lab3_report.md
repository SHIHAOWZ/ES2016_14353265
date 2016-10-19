#Dol实例分析&编程

***
###1.修改example1
要求输出结果为1-20的立方，所以修改的地方应该在下图的square中

![pig1](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab3/picres/pig1.png)

在square.c中我们可以看到，square从generator中读出传进来将要做运算的数字，然后进过运算后吧运算结果写到
consumer中。如图

![pig2](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab3/picres/pig2.png)

所以我们修改的地方就是如何做运算，要求三次方的话，在平方那里多乘以一个i即可。

![pig3](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab3/picres/pig3.png)

修改完成后重新编译example1，在运行即可得到结果。

$ ant -f runexample.xml -Dnumber=1

![pig4](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab3/picres/pig4.png)

***

###2.修该example2
修改要求是让3个square模块变成2个。由ppt可知。generator和consumer之间的连线是在example.xml中实现的。先
看一个典型的generator和consumer之间由一个模块连接的实现。

![pig5](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab3/picres/pig5.png)

如图有两个connection，一定注意每一个connection都有两个端口，一个时origin就是传进这条线的数据，一个是
target，就是这条线要把数据传到哪。

![pig6](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab3/picres/pig6.png)

如图example的generator和consumer之间有三个模块，要想变成两个就要去掉一个模块。从xml文件中可以看出这
里的模块是通过iterator迭代产生的，可以发现

![pig7](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab3/picres/pig7.png)

这里定义了三次迭代，如果我们只需要两次迭代的话，就直接修改value的值为2即可。

![pig8](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab3/picres/pig8.png)

修改完成后重新编译example1，在运行即可得到结果。


$ ant -f runexample.xml -Dnumber=2

![pig9](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab3/picres/pig9.png)

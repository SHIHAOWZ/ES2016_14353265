#Deadlock
***
###A.死锁产生的四个条件
死锁就是两个或者多个进程，互相请求对方占有的资源。

1. 互斥条件：一个资源每次只能被一个进程使用

2. 请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放

3. 不剥夺条件:进程已获得的资源，在末使用完之前，不能强行剥夺

4. 循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系
***

###B.实验步骤
①在ubuntu下新建一个文件夹EXP4，然后在里面创建一个Deadlock.java文件。代码如下：

![pig1](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab4/picres/pig1.png) ![pig2](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab4/picres/pig2.png)

②进行linux命令，用javac命令编译java文件，然后用java命令运行。这里注意，在命令行里面进行代码时，
要先！/bin/bash,然后输入想要执行的代码。

![pig3](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab4/picres/pig3.png)

![pig4](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab4/picres/pig4.png)

③修改count的值为10000，然后出现死锁的结果如下图：

![pig5](https://raw.githubusercontent.com/SHIHAOWZ/ES2016_14353265/master/assignment_lab4/picres/pig5.png)
***

###C.对上述程序产生死锁的解释
首先解读关键字synchronized:

* 当它用来修饰一个方法或者一个代码块的时候，能够保证在同一时刻最多只有一个线程执行该段代码。

* 当一个线程访问object的一个synchronized同步代码块或同步方法时，其他线程对object中所有其它synchronized同步代码块或同步方法的访问将被阻塞。

所以当运行c次循环，也就是不断地构造new Deadlock()，每次调用都会新建一个线程t来执行。当不断的循环创建线程
的时候，总有一个时刻，就是上一个线程还在运行b.methodB(a),而新的线程这是恰好结束等待，这个新线程需要a这个
资源来执行a.methodA(b),这时候由于a这个资源还在被上一个线程占用着，所以无法使用资源那就形成了一个死锁。





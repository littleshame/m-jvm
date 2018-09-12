# m-jvm
简单的实现jvm
编码：UTF-8
jdk1.8
**class文件解析格式与jdk版本有关**

运行MiniJVMTest.java 需要修改PATH到存放class文件的项目目录
ClassFileloaderTest中path1，path2亦如是


MiniJVMTest.java运行结果：

0:bb new  jvm/test/EmployeeV1 <br>
3:59 dup <br>
4:12 ldc Andy <br>
6:10 bipush 29
8:b7 invokespecial  jvm/test/EmployeeV1 : <init> : (Ljava/lang/String;I)V
0:2a aload_0
1:b7 invokespecial  java/lang/Object : <init> : ()V
4:2a aload_0
5:2b aload_1
6:b5 putfield  jvm/test/EmployeeV1 : name:Ljava/lang/String;]
9:2a aload_0
10:1c iload_2
11:b5 putfield  jvm/test/EmployeeV1 : age:I]
14:b1 return
11:4c astore_1
12:2b aload_1
13:b6 invokevirtual  jvm/test/EmployeeV1 : sayHello : ()V
0:b2 getstatic  java/lang/System : out:Ljava/io/PrintStream;]
3:12 ldc Hello , this is class Employee 
5:b6 invokevirtual  java/io/PrintStream : println : (Ljava/lang/String;)V
8:b1 return
16:b1 return
-------------------Hello , this is class Employee ----------------

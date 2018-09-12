
# m-jvm <br>
简单的实现jvm <br> --来自刘欣老师布置的作业

编码：UTF-8 <br>
jdk1.8 <br>
**class文件解析格式与jdk版本有关** <br>

运行MiniJVMTest.java 需要修改PATH到存放class文件的项目目录 <br>
ClassFileloaderTest中path1，path2亦如是 <br>


MiniJVMTest.java运行结果： <br>

0:bb new  jvm/test/EmployeeV1 <br>
3:59 dup <br>
4:12 ldc Andy <br>
6:10 bipush 29 <br>
8:b7 invokespecial  jvm/test/EmployeeV1 : <init> : (Ljava/lang/String;I)V <br>
0:2a aload_0 <br>
1:b7 invokespecial  java/lang/Object : <init> : ()V <br>
4:2a aload_0 <br>
5:2b aload_1 <br>
6:b5 putfield  jvm/test/EmployeeV1 : name:Ljava/lang/String;] <br>
9:2a aload_0 <br>
10:1c iload_2 <br>
11:b5 putfield  jvm/test/EmployeeV1 : age:I] <br>
14:b1 return <br>
11:4c astore_1 <br>
12:2b aload_1 <br>
13:b6 invokevirtual  jvm/test/EmployeeV1 : sayHello : ()V <br>
0:b2 getstatic  java/lang/System : out:Ljava/io/PrintStream;] <br>
3:12 ldc Hello , this is class Employee  <br>
5:b6 invokevirtual  java/io/PrintStream : println : (Ljava/lang/String;)V <br>
8:b1 return <br>
16:b1 return <br>
-------------------Hello , this is class Employee ---------------- <br>

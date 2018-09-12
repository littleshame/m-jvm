package jvm.cmd;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import jvm.clz.ClassFile;
import jvm.constant.ConstantInfo;
import jvm.constant.ConstantPool;
import jvm.engine.ExecutionResult;
import jvm.engine.StackFrame;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tjc on 2018/8/30.
 */
public abstract class ByteCodeCommand {

     String opCode;
    ClassFile clzFile;
    private int offset;

    public static final String aconst_null = "01";      //null进栈
    public static final String new_object = "bb";       //创建一个对象
    public static final String lstore = "37";           //将long类型变量从操作数栈存储到局部变量表
    public static final String invokespecial = "b7";    //调用需要特殊处理的实例方法，如实例初始化方法，私有化方法和父类方法
    public static final String invokevirtual = "b6";    //调用对象的实例方法
    public static final String getfield = "b4";         //获取对象的字段值
    public static final String putfield = "b5";         //设置对象字段
    public static final String getstatic = "b2";        //获取对象的静态字段值
    public static final String ldc = "12";              //从运行时常量池中提取数据推入操作数栈
    public static final String dup = "59";              //复制操作数栈栈顶的值，并插入到栈顶
    public static final String bipush = "10";           //将 byte 带符号扩展为一个 int 类型的值 value，然后将 value 压入到操作数栈中
    public static final String aload_0 = "2a";          //aload_<n> 从局部变量表加载一个 reference 类型值到操作数栈中
    public static final String aload_1 = "2b";
    public static final String aload_2 = "2c";
    public static final String iload = "15";            //从局部变量表加载一个 int 类型值到操作数栈中
    public static final String iload_1 = "1b";
    public static final String iload_2 = "1c";
    public static final String iload_3 = "1d";
    public static final String fload_3 = "25";

    public static final String voidreturn = "b1";       //无返回值的方法返回
    public static final String ireturn = "ac";          //结束方法，并返回一个 int 类型数据
    public static final String freturn = "ae";          //结束方法，并返回一个 float 类型数据

    public static final String astore_1 = "4c";         //将一个 reference 类型数据保存到局部变量表中
    public static final String if_icmp_ge = "a2";       //int 数值的条件分支判断
    public static final String if_icmple = "a4";
    public static final String goto_no_condition = "a7";//分支跳转
    public static final String iconst_0 = "03";         //将 int 型 0 推送至栈顶
    public static final String iconst_1 = "04";
    public static final String istore_1 = "3c";
    public static final String istore_2 = "3d";
    public static final String iadd = "60";             //int 类型数据相加
    public static final String iinc = "84";             //局部变量自增

    private static Map<String, String> codeMap = new HashMap<String, String>();

    static {
        codeMap.put("01", "aconst_null");

        codeMap.put("a2", "if_icmp_ge");
        codeMap.put("a4", "if_icmple");
        codeMap.put("a7", "goto");

        codeMap.put("bb", "new");
        codeMap.put("37", "lstore");
        codeMap.put("b7", "invokespecial");
        codeMap.put("b6", "invokevirtual");
        codeMap.put("b4", "getfield");
        codeMap.put("b5", "putfield");
        codeMap.put("b2", "getstatic");

        codeMap.put("2a", "aload_0");
        codeMap.put("2b", "aload_1");
        codeMap.put("2c", "aload_2");

        codeMap.put("10", "bipush");
        codeMap.put("15", "iload");
        codeMap.put("1a", "iload_0");
        codeMap.put("1b", "iload_1");
        codeMap.put("1c", "iload_2");
        codeMap.put("1d", "iload_3");

        codeMap.put("25", "fload_3");

        codeMap.put("1e", "lload_0");

        codeMap.put("24", "fload_2");
        codeMap.put("4c", "astore_1");

        codeMap.put("a2", "if_icmp_ge");
        codeMap.put("a4", "if_icmple");

        codeMap.put("a7", "goto");

        codeMap.put("b1", "return");
        codeMap.put("ac", "ireturn");
        codeMap.put("ae", "freturn");

        codeMap.put("03", "iconst_0");
        codeMap.put("04", "iconst_1");

        codeMap.put("3c", "istore_1");
        codeMap.put("3d", "istore_2");

        codeMap.put("59", "dup");

        codeMap.put("60", "iadd");
        codeMap.put("84", "iinc");

        codeMap.put("12", "ldc");
    }


    protected ByteCodeCommand(ClassFile clzFile, String opCode) {
        this.clzFile = clzFile;
        this.opCode = opCode;
    }

    public int getOffset() {
        return this.offset;
    }

    protected ConstantInfo getConstantInfo(int index){
        return this.getClassFile().getConstantPool().getConstantInfo(index);
    }

    private ClassFile getClassFile() {
        return clzFile;
    }

    public String getReadableCodeText() {
        return codeMap.get(opCode);
    }


    public void setOffset(int offset) {
        this.offset = offset;
    }

    public abstract int getLength();

    public abstract void execute(StackFrame stackFrame, ExecutionResult result);

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    public ConstantPool getConstantPool() {
        return this.clzFile.getConstantPool();
    }
}



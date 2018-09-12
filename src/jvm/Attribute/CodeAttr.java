package jvm.Attribute;


import jvm.clz.ClassFile;
import jvm.cmd.ByteCodeCommand;
import jvm.cmd.CommandParse;
import jvm.loader.ByteCodeIterator;

/**
 * Code属性
 * Created by tjc on 2018/7/20.
 */
public class CodeAttr extends AttributeInfo {


    private int maxStack;
    private int maxLocals;
    private int codeLength;
    private String code;

    ByteCodeCommand[] cmds;


    private LineNumberTable lineNumTable;
    private LocalVariableTable localVarTable;
    private StackMapTable stackMapTable;

    public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLength, String code, ByteCodeCommand[] cmds) {
        super(attrNameIndex, attrLen);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.codeLength = codeLength;
        this.code = code;
        this.cmds = cmds;
    }

    /**
     * 解析code属性表
     *
     * @param clzFile
     * @param it
     * @return
     */
    public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator it) {
        int attrNameIndex = it.nextU2ToInt();
        int attrLength = it.nextU4ToInt();
        int maxStack = it.nextU2ToInt();
        int maxLocals = it.nextU2ToInt();
        int codeLen = it.nextU4ToInt();

        //字节码过于复杂，暂不做具体解析只做存贮
        String code = it.nextUxToHexString(codeLen);

        //生成字节码指令
        ByteCodeCommand[] cmds = CommandParse.parse(code, clzFile);


        CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLength, maxStack, maxLocals, codeLen, code, cmds);

        int extableLen = it.nextU2ToInt();
        if (extableLen > 0) {
            String exTable = it.nextUxToHexString(extableLen);
            System.out.println("Encountered exception table , just ignore it :" + exTable);
        }

        int subAttrCount = it.nextU2ToInt();

        for (int i = 0; i < subAttrCount; i++) {
            int subAttrIndex = it.nextU2ToInt();
            String subattrName = clzFile.getConstantPool().getUTF8String(subAttrIndex);
            it.back(2);

            if (subattrName.equalsIgnoreCase(AttributeInfo.LINE_NUM_TABLE)) {
                LineNumberTable t = LineNumberTable.parse(it);
                codeAttr.setLineNumTable(t);
            } else if (subattrName.equalsIgnoreCase(AttributeInfo.LOCAL_VAR_TABLE)) {
                LocalVariableTable t = LocalVariableTable.parse(it);
                codeAttr.setLocalVarTable(t);
            } else if (subattrName.equalsIgnoreCase(AttributeInfo.STACK_MAP_TABLE)) {
                StackMapTable t = StackMapTable.parse(it);
                codeAttr.setStackMapTable(t);
            } else {
                throw new RuntimeException("Need code to process " + subattrName);
            }
        }

        return codeAttr;
    }


    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }

    public LineNumberTable getLineNumTable() {
        return lineNumTable;
    }

    public void setLineNumTable(LineNumberTable lineNumTable) {
        this.lineNumTable = lineNumTable;
    }

    public LocalVariableTable getLocalVarTable() {
        return localVarTable;
    }

    public void setLocalVarTable(LocalVariableTable localVarTable) {
        this.localVarTable = localVarTable;
    }

    public StackMapTable getStackMapTable() {
        return stackMapTable;
    }

    public void setStackMapTable(StackMapTable stackMapTable) {
        this.stackMapTable = stackMapTable;
    }

    public String getCode() {
        return code;
    }

    public ByteCodeCommand[] getCmds() {
        return cmds;
    }
}

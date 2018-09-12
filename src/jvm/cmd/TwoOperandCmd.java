package jvm.cmd;

import jvm.clz.ClassFile;
import jvm.constant.ClassInfo;
import jvm.constant.ConstantInfo;
import jvm.constant.FieldRefInfo;
import jvm.constant.MethodRefInfo;

/**
 * Created by tjc on 2018/8/30.
 */
public abstract class TwoOperandCmd  extends ByteCodeCommand{

    int oprand1 = -1;
    int oprand2 = -1;

    protected TwoOperandCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }


    public int getOprand1() {
        return oprand1;
    }

    public void setOprand1(int oprand1) {
        this.oprand1 = oprand1;
    }

    public int getOprand2() {
        return oprand2;
    }

    public void setOprand2(int oprand2) {
        this.oprand2 = oprand2;
    }

    public String getOperandAsClassInfo() {
        int index = this.getIndex();
        String codeTxt = getReadableCodeText();
        ClassInfo info = (ClassInfo)getConstantInfo(index);
        return this.getOffset()+":"+this.getOpCode()+" "+ codeTxt +"  "+ info.getClassName();
    }

    public String getOpCode() {
        return this.opCode;
    }

    public int getIndex() {
        int oprand1 = this.getOprand1();
        int oprand2 = this.getOprand2();

        int index = oprand1 << 8 | oprand2;
        return index;
    }

    public  int getLength(){
        return 3;
    }

    protected String getOperandAsField(){
        int index = getIndex();

        String codeTxt = getReadableCodeText();
        FieldRefInfo info = (FieldRefInfo)this.getConstantInfo(index);
        return this.getOffset()+":"+this.getOpCode()+" " + codeTxt +"  "+ info.toString();
    }

    protected String getOperandAsMethod(){
        int index = getIndex();
        String codeTxt = getReadableCodeText();
        ConstantInfo constInfo = this.getConstantInfo(index);
        MethodRefInfo info = (MethodRefInfo)this.getConstantInfo(index);
        return this.getOffset()+":"+this.getOpCode()+" " + codeTxt +"  "+ info.toString();
    }

}

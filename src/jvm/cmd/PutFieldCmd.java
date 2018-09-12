package jvm.cmd;

import jvm.clz.ClassFile;
import jvm.constant.*;
import jvm.engine.*;

/**
 * Created by tjc on 2018/9/3.
 */
public class PutFieldCmd extends TwoOperandCmd{
    protected PutFieldCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public String toString() {

        return super.getOperandAsField();
    }
    @Override
    public void execute(StackFrame frame,ExecutionResult result) {

        FieldRefInfo fieldRef = (FieldRefInfo)this.getConstantInfo(this.getIndex());

        ClassInfo clzInfo = (ClassInfo)this.getConstantInfo(fieldRef.getClassInfoIndex());
        NameAndTypeInfo nameTypeInfo = (NameAndTypeInfo)this.getConstantInfo(fieldRef.getNameAndTypeIndex());
        // for example : name
        String fieldName = nameTypeInfo.getName();
        // for example : Ljava/lang/String : 注意：我们不再检查类型
        String fieldType = nameTypeInfo.getTypeInfo();

        JavaObject fieldValue = frame.getOprandStack().pop();
        JavaObject objectRef = frame.getOprandStack().pop();

        objectRef.setFieldValue(fieldName, fieldValue);

    }

}

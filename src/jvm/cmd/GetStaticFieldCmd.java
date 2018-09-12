package jvm.cmd;

import jvm.clz.ClassFile;
import jvm.engine.ExecutionResult;
import jvm.engine.StackFrame;

/**
 * Created by tjc on 2018/9/3.
 */
public class GetStaticFieldCmd extends TwoOperandCmd{
    protected GetStaticFieldCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame stackFrame, ExecutionResult result) {

    }

    @Override
    public String toString() {
        return super.getOperandAsField();
    }

}

package jvm.cmd;

import jvm.clz.ClassFile;
import jvm.engine.ExecutionResult;
import jvm.engine.StackFrame;

/**
 * 比较命令
 * Created by tjc on 2018/9/3.
 */
public class ComparisonCmd extends TwoOperandCmd{
    protected ComparisonCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame stackFrame, ExecutionResult result) {

    }
}

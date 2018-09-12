package jvm.cmd;

import jvm.clz.ClassFile;
import jvm.engine.ExecutionResult;
import jvm.engine.StackFrame;

/**
 * Created by tjc on 2018/8/30.
 */
public abstract class OneOperandCmd extends ByteCodeCommand {

    private int operand;


    protected OneOperandCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public int getLength() {
        return 2;
    }

    public void setOperand(int operand) {
        this.operand = operand;
    }

    public int getOperand() {
        return this.operand;
    }
}

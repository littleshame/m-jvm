package jvm.cmd;

import jvm.clz.ClassFile;
import jvm.engine.ExecutionResult;
import jvm.engine.Heap;
import jvm.engine.JavaObject;
import jvm.engine.StackFrame;

/**
 * Created by tjc on 2018/8/30.
 */
public class BiPushCmd extends OneOperandCmd {
    protected BiPushCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame stackFrame, ExecutionResult result) {
        int value = this.getOperand();
        JavaObject jo = Heap.getInstance().newInt(value);
        stackFrame.getOprandStack().push(jo);
    }

    @Override
    public String toString() {

        return this.getOffset()+":"+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
    }
}

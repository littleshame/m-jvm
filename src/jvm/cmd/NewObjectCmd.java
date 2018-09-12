package jvm.cmd;

import jvm.clz.ClassFile;
import jvm.constant.ClassInfo;
import jvm.engine.ExecutionResult;
import jvm.engine.JavaObject;
import jvm.engine.StackFrame;

/**
 * Created by tjc on 2018/9/2.
 */
public class NewObjectCmd extends TwoOperandCmd {

    protected NewObjectCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public String toString()
    {
        //0: new #1
        return super.getOperandAsClassInfo();
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        int index =this.getIndex();

        ClassInfo info = (ClassInfo)this.getConstantInfo(index);

        String clzName = info.getClassName();

        JavaObject jo = Heap.getInstance().newObject(clzName);

        frame.getOprandStack().push(jo);

    }
}

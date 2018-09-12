package jvm.cmd;

import jvm.clz.ClassFile;
import jvm.constant.MethodRefInfo;
import jvm.engine.ExecutionResult;
import jvm.engine.MethodArea;
import jvm.engine.StackFrame;
import jvm.method.Method;

/**
 * Created by tjc on 2018/9/2.
 */
public class InvokeSpecialCmd extends TwoOperandCmd {
    protected InvokeSpecialCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame stackFrame, ExecutionResult result) {
        MethodRefInfo methodRefInfo = (MethodRefInfo) getConstantInfo(this.getIndex());
        // 不实现jang.lang.Object 的init方法
        if (methodRefInfo.getClassName().equals("java/lang/Object")
                && methodRefInfo.getMethodName().equals("<init>")) {
            return;
        }
        Method nextMethod = MethodArea.getInstance().getMethod(methodRefInfo);

        result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);
        result.setNextMethod(nextMethod);
    }

    @Override
    public String toString() {

        return super.getOperandAsMethod();
    }


}

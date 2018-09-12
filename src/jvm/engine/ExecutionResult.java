package jvm.engine;

import jvm.method.Method;

/**
 * Created by tjc on 2018/9/10.
 */
public class ExecutionResult {

    public static final int RUN_NEXT_CMD = 1;
    public static final int JUMP = 2;
    public static final int EXIT_CURRENT_FRAME = 3;
    public static final int PAUSE_AND_RUN_NEW_FRAME = 4;

    private int nextAction = RUN_NEXT_CMD;

    private Method nextMethod;
    private int nextCmdOffset = 0;

    public void setNextAction(int nextAction) {
        this.nextAction = nextAction;
    }

    public boolean isPauseAndRunNewFrame() {
        return this.nextAction == PAUSE_AND_RUN_NEW_FRAME;
    }


    public Method getNextMethod() {
        return nextMethod;
    }

    public void setNextMethod(Method nextMethod) {
        this.nextMethod = nextMethod;
    }

    public boolean isRunNextCmd() {
        return nextAction == RUN_NEXT_CMD;
    }

    public boolean isExitCurrentFrame() {
        return nextAction == EXIT_CURRENT_FRAME;
    }

    public boolean isJump() {
        return nextAction == JUMP;
    }

    public int getNextCmdOffset() {
        return nextCmdOffset;
    }

    public void setNextCmdOffset(int nextCmdOffset) {
        this.nextCmdOffset = nextCmdOffset;
    }

}

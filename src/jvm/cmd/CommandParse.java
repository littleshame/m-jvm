package jvm.cmd;

import jvm.clz.ClassFile;
import jvm.engine.ExecutionResult;
import jvm.engine.StackFrame;

import java.util.ArrayList;
import java.util.List;

/**
 * 字节码指令解析器
 * 将字节码翻译成字节码指令
 * Created by tjc on 2018/8/31.
 */
public class CommandParse {


    /**
     * 解析字节码指令
     *
     * @param codes
     * @return
     */
    public static ByteCodeCommand[] parse(String codes, ClassFile clzFile) {

        if ((codes == null) || (codes.length() == 0) || (codes.length() % 2) != 0) {
            throw new RuntimeException("the orignal code is not correct");
        }
        List<ByteCodeCommand> cmds = new ArrayList<ByteCodeCommand>();
        CommandIterator iter = new CommandIterator(codes);

        while (iter.hasNext()) {
            String opCode = iter.next2CharAsString();

            if (ByteCodeCommand.new_object.equals(opCode)) {
                NewObjectCmd cmd = new NewObjectCmd(clzFile, opCode);
                cmd.setOprand1(iter.next2CharAsInt());
                cmd.setOprand2(iter.next2CharAsInt());
                cmds.add(cmd);
            } else if (ByteCodeCommand.invokespecial.equals(opCode)) {
                InvokeSpecialCmd cmd = new InvokeSpecialCmd(clzFile, opCode);
                cmd.setOprand1(iter.next2CharAsInt());
                cmd.setOprand2(iter.next2CharAsInt());
                // System.out.println( cmd.toString(clzFile.getConstPool()));
                cmds.add(cmd);
            } else if (ByteCodeCommand.invokevirtual.equals(opCode)) {
                InvokeVirtualCmd cmd = new InvokeVirtualCmd(clzFile, opCode);
                cmd.setOprand1(iter.next2CharAsInt());
                cmd.setOprand2(iter.next2CharAsInt());

                cmds.add(cmd);
            } else if (ByteCodeCommand.getfield.equals(opCode)) {
                GetFieldCmd cmd = new GetFieldCmd(clzFile, opCode);
                cmd.setOprand1(iter.next2CharAsInt());
                cmd.setOprand2(iter.next2CharAsInt());
                cmds.add(cmd);
            } else if (ByteCodeCommand.getstatic.equals(opCode)) {
                GetStaticFieldCmd cmd = new GetStaticFieldCmd(clzFile, opCode);
                cmd.setOprand1(iter.next2CharAsInt());
                cmd.setOprand2(iter.next2CharAsInt());
                cmds.add(cmd);
            } else if (ByteCodeCommand.putfield.equals(opCode)) {
                PutFieldCmd cmd = new PutFieldCmd(clzFile, opCode);
                cmd.setOprand1(iter.next2CharAsInt());
                cmd.setOprand2(iter.next2CharAsInt());
                cmds.add(cmd);
            } else if (ByteCodeCommand.ldc.equals(opCode)) {
                LdcCmd cmd = new LdcCmd(clzFile, opCode);
                cmd.setOperand(iter.next2CharAsInt());
                cmds.add(cmd);
            } else if (ByteCodeCommand.bipush.equals(opCode)) {
                BiPushCmd cmd = new BiPushCmd(clzFile, opCode);
                cmd.setOperand(iter.next2CharAsInt());
                cmds.add(cmd);
            } else if (ByteCodeCommand.if_icmp_ge.equals(opCode)
                    || ByteCodeCommand.if_icmple.equals(opCode)
                    || ByteCodeCommand.goto_no_condition.equals(opCode)) {
                ComparisonCmd cmd = new ComparisonCmd(clzFile, opCode);
                cmd.setOprand1(iter.next2CharAsInt());
                cmd.setOprand2(iter.next2CharAsInt());
                cmds.add(cmd);
            } else if (ByteCodeCommand.iinc.equals(opCode)) {
                IncrementCmd cmd = new IncrementCmd(clzFile, opCode);
                cmd.setOprand1(iter.next2CharAsInt());
                cmd.setOprand2(iter.next2CharAsInt());
                cmds.add(cmd);
            } else if (ByteCodeCommand.dup.equals(opCode)
                    || ByteCodeCommand.aload_0.equals(opCode)
                    || ByteCodeCommand.aload_1.equals(opCode)
                    || ByteCodeCommand.aload_2.equals(opCode)
                    || ByteCodeCommand.iload_1.equals(opCode)
                    || ByteCodeCommand.iload_2.equals(opCode)
                    || ByteCodeCommand.iload_3.equals(opCode)
                    || ByteCodeCommand.fload_3.equals(opCode)
                    || ByteCodeCommand.iconst_0.equals(opCode)
                    || ByteCodeCommand.iconst_1.equals(opCode)
                    || ByteCodeCommand.istore_1.equals(opCode)
                    || ByteCodeCommand.istore_2.equals(opCode)
                    || ByteCodeCommand.voidreturn.equals(opCode)
                    || ByteCodeCommand.iadd.equals(opCode)
                    || ByteCodeCommand.astore_1.equals(opCode)
                    || ByteCodeCommand.ireturn.equals(opCode)) {

                NoOperandCmd cmd = new NoOperandCmd(clzFile, opCode);
                cmds.add(cmd);
            } else {
                throw new RuntimeException("Sorry, the java instruction " + opCode + " has not been implemented");
            }
        }

        calcuateOffset(cmds);


        ByteCodeCommand[] result = new ByteCodeCommand[cmds.size()];
        cmds.toArray(result);
        return result;
    }

    //计算字节码指令偏移量
    private static void calcuateOffset(List<ByteCodeCommand> cmds) {
        int offset = 0;
        for (ByteCodeCommand cmd : cmds) {
            cmd.setOffset(offset);
            offset += cmd.getLength();
        }

    }

    private static class CommandIterator {

        String codes = null;
        int pos = 0;


        public CommandIterator(String codes) {
            this.codes = codes;
        }

        public String next2CharAsString() {
            String result = codes.substring(pos, pos + 2);
            pos += 2;
            return result;
        }

        public boolean hasNext() {
            return pos < this.codes.length();
        }

        public int next2CharAsInt() {
            String s = this.next2CharAsString();
            return Integer.valueOf(s, 16).intValue();
        }
    }
}

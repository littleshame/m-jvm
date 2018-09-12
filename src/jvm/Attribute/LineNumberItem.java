package jvm.Attribute;

/**
 * Created by tjc on 2018/8/30.
 */
public class LineNumberItem {

    int startpc;    //字节码行号
    int lineNumber; //Java源码行号

    public LineNumberItem(int startpc, int lineNumber) {
        this.startpc = startpc;
        this.lineNumber = lineNumber;
    }
}

package jvm.constant;

/**
 * Created by tjc on 2018/7/7.
 */
public class StringInfo extends ConstantInfo {

    public static final int tag = ConstantInfo.STRING_INFO;

    public int stringIndex;

    public StringInfo(ConstantPool pool){
        super(pool);
    }

    public int getStringIndex() {
        return stringIndex;
    }

    public void setStringIndex(int stringIndex) {
        this.stringIndex = stringIndex;
    }

    public String toString(){
        return this.getConstantPool().getUTF8String(stringIndex);
    }
}

package jvm.constant;

import jvm.util.ByteUtil;

public class UTF8Info extends ConstantInfo {

    public static final int tag = ConstantInfo.UTF8_INFO;

    public int length;

    public byte[] bytes;

    public UTF8Info(int length,byte[] bytes){
        this.length = length;
        bytes = bytes;
    }

    public UTF8Info(ConstantPool pool){
        super(pool);
    }

    public UTF8Info(){}

    public String getValue(){
        return new String(bytes);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}

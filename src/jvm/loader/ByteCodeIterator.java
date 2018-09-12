package jvm.loader;

import jvm.util.ByteUtil;

/**
 * Created by tjc on 2018/6/10.
 */
public class ByteCodeIterator {

    private byte[] binaryCode;

    private static final int DEFAULT_CURRENT = 0;

    private int current;

    private int size;

    public ByteCodeIterator(byte[] binaryCode) {
        this.binaryCode = binaryCode;
        this.current = DEFAULT_CURRENT;
        this.size = binaryCode.length;
    }

    public String nextU4ToString(){
        return ByteUtil.byteToHexString(new byte[]{binaryCode[current++], binaryCode[current++],binaryCode[current++],binaryCode[current++]});
    }

    public String nextU2ToString(){
        return ByteUtil.byteToHexString(new byte[]{binaryCode[current++], binaryCode[current++]});
    }

    public int nextU2ToInt() {
        if (current + 2 > size - 1) throw new IndexOutOfBoundsException();
        return ByteUtil.byteToInt(new byte[]{binaryCode[current++], binaryCode[current++]});
    }

    public int nextU4ToInt() {
        if (current + 2 > size - 1) throw new IndexOutOfBoundsException();
        return ByteUtil.byteToInt(new byte[]{binaryCode[current++], binaryCode[current++],binaryCode[current++], binaryCode[current++]});
    }

    public int nextU1ToInt() {
        if (current + 1 > size - 1) throw new IndexOutOfBoundsException();
        return ByteUtil.byteToInt(new byte[]{binaryCode[current++]});
    }

    public boolean hasNext() {
        if (current + 1 < size - 1) return true;
        return false;
    }

    public byte nextU1(){
        return binaryCode[current++];
    }

    /**
     * 回退
     * @param i
     */
    public void back(int i) {
        current = current - 2;
    }

    /**
     *
     * @param codeLen
     * @return
     */
    public String nextUxToHexString(int codeLen) {
        byte[] temp = new byte[codeLen];
        for (int i = 0; i < codeLen; i++) {
            temp[i] = binaryCode[current++];
        }
        return ByteUtil.byteToHexString(temp);

    }
}

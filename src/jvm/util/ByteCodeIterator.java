package jvm.util;

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

    public int nextU2ToInt() {
        if (current + 2 > size - 1) throw new IndexOutOfBoundsException();
        return ByteUtil.byteToInt(new byte[]{binaryCode[current++], binaryCode[current++]});
    }

    public int nextU1ToInt() {
        if (current + 1 > size - 1) throw new IndexOutOfBoundsException();
        return ByteUtil.byteToInt(new byte[]{binaryCode[current++]});
    }

    public boolean hasNext() {
        if (current + 1 < size - 1) return true;
        return false;
    }
}

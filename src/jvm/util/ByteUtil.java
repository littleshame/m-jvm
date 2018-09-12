package jvm.util;

import org.junit.Test;

/**
 * Created by tjc on 2018/5/6.
 */
public class ByteUtil {

    public static int byteToInt(byte[] b) {
        int result = 0;
        for (int i = 0; i < b.length; i++) {
            result += (b[i] & 0xff) << 8 * (b.length - i - 1);
        }
        return result;
    }

    public static String byteToHexString(byte[] codes) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < codes.length; i++) {
            byte b = codes[i];
            int value = b & 0xFF;
            String strHex = Integer.toHexString(value);
            if (strHex.length() < 2) {
                strHex = "0" + strHex;
            }
            buffer.append(strHex);
        }
        return buffer.toString();
    }


    @Test
    public void testUtil(){
        String result = ByteUtil.byteToHexString(new byte[]{66,65});

        System.out.print(result);
    }

    @Test
    public void testInt(){
        int result = ByteUtil.byteToInt(new byte[]{-100});
        System.out.println(result);
    }
}

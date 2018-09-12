package jvm.Attribute;

import jvm.loader.ByteCodeIterator;

/**
 *
 * Created by tjc on 2018/8/28.
 */
public class StackMapTable extends AttributeInfo{

    private String originalCode;

    public StackMapTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static StackMapTable parse(ByteCodeIterator iter) {
        int index = iter.nextU2ToInt();
        int len = iter.nextU4ToInt();
        StackMapTable t = new StackMapTable(index,len);

        //后面的StackMapTable太过复杂， 不再处理， 只把原始的代码读进来保存
        String code = iter.nextUxToHexString(len);
        t.setOriginalCode(code);

        return t;
    }

    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode;
    }
}

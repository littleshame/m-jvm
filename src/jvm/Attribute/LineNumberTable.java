package jvm.Attribute;

import jvm.loader.ByteCodeIterator;

import java.util.LinkedList;
import java.util.List;


/**
 * LineNumberTable 属性描述了Java源码行号与字节码行号（字节码的偏移量）之间的对应关系
 * 在code属性中
 * Created by tjc on 2018/8/28.
 */
public class LineNumberTable extends AttributeInfo {

    private List<LineNumberItem> items;

    public LineNumberTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static LineNumberTable parse(ByteCodeIterator itr) {
        int attrNameIndex = itr.nextU2ToInt();
        int attrLen = itr.nextU4ToInt();
        LineNumberTable table = new LineNumberTable(attrNameIndex, attrLen);

        List<LineNumberItem> items = new LinkedList();

        int lineNumItemLen = itr.nextU2ToInt();
        for (int i = 0; i < lineNumItemLen; i++) {
            int starpc = itr.nextU2ToInt();
            int lineNumber = itr.nextU2ToInt();
            items.add(new LineNumberItem(starpc,lineNumber));
        }

        table.setLineNumberItem(items);

        return table;
    }

    public List<LineNumberItem> getLineNumberItem() {
        return items;
    }

    public void setLineNumberItem(List<LineNumberItem> items) {
        this.items = items;
    }
}

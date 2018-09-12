package jvm.Attribute;

import jvm.loader.ByteCodeIterator;

import java.util.LinkedList;
import java.util.List;

/**
 * 描述栈帧中局部变量表中的变量与Java源码中定义的变量之间的关系
 * Created by tjc on 2018/8/28.
 */
public class LocalVariableTable extends AttributeInfo {

    private List<LocalVariableItem> items;

    public LocalVariableTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }


    public static LocalVariableTable parse(ByteCodeIterator itr) {
        int attrNameIndex = itr.nextU2ToInt();
        int attrLen = itr.nextU4ToInt();

        LocalVariableTable table = new LocalVariableTable(attrNameIndex,attrLen);
        List<LocalVariableItem> items = new LinkedList<>();

        int localVarTableLen = itr.nextU2ToInt();
        for(int i=0;i<localVarTableLen;i++){
            int strpc = itr.nextU2ToInt();
            int length = itr.nextU2ToInt();
            int nameIndex = itr.nextU2ToInt();
            int descIndex = itr.nextU2ToInt();
            int index = itr.nextU2ToInt();

            items.add(new LocalVariableItem(strpc,length,nameIndex,descIndex,index));
        }

        table.setLocalVariableItems(items);

        return table;
    }


    public List<LocalVariableItem> getLocalVariableItems() {
        return items;
    }

    public void setLocalVariableItems(List<LocalVariableItem> localVariableItem) {
        this.items = localVariableItem;
    }
}

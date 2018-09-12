package jvm.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量池
 */
public class ConstantPool {

    public List<ConstantInfo> constants = new ArrayList<>();

    public int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ConstantPool(){
        constants.add(null);
    }

    public void add(ConstantInfo constant){
        constants.add(constant);
    }


    public ConstantInfo getConstantInfo(int i) {
        return constants.get(i);

    }

    public String getUTF8String(int nameIndex) {
            UTF8Info constant = (UTF8Info)constants.get(nameIndex);
            return constant.getValue();
    }
}

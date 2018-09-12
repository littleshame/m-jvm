package jvm.constant;

/**
 * 常量池类常量
 */
public class ClassInfo  extends ConstantInfo {

    private static final int tag = ConstantInfo.CLASS_INFO;

    private int nameIndex;

    private int utf8Index;
    private String className;

    public ClassInfo(ConstantPool pool){
        super(pool);
    }

    public int getNameIndex() {

        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getUtf8Index() {
        return nameIndex;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        int index = getUtf8Index();
        UTF8Info utf8Info = (UTF8Info) constantPool.getConstantInfo(index);
        return utf8Info.getValue();
    }
}

package jvm.constant;

public class FieldRefInfo extends ConstantInfo {
    public static final int tag = ConstantInfo.FIELD_INFO;

    public int classInfoIndex;
    public int nameAndTypeIndex;


    public FieldRefInfo(ConstantPool pool){
        super(pool);
    }

    public int getClassInfoIndex() {
        return classInfoIndex;
    }

    public void setClassInfoIndex(int classInfoIndex) {
        this.classInfoIndex = classInfoIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public void setNameAndTypeIndex(int nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public String toString(){

        NameAndTypeInfo  typeInfo = (NameAndTypeInfo)this.getConstantInfo(this.getNameAndTypeIndex());

        return getClassName() +" : "+  typeInfo.getName() + ":" + typeInfo.getTypeInfo() +"]";
    }

    public String getClassName(){

        ClassInfo classInfo = (ClassInfo) this.getConstantInfo(this.getClassInfoIndex());

        UTF8Info utf8Info = (UTF8Info)this.getConstantInfo(classInfo.getUtf8Index());

        return utf8Info.getValue();

    }
}

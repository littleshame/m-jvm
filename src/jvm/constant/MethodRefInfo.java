package jvm.constant;

public class MethodRefInfo extends ConstantInfo {

    public static final int tag = ConstantInfo.METHOD_INFO;

    public int classInfoIndex;

    public int nameAndTypeIndex;

    public String toString() {

        return getClassName() + " : " + this.getMethodName() + " : " + this.getParamAndReturnType();
    }

    public String getMethodName() {
        ConstantPool pool = this.getConstantPool();
        NameAndTypeInfo typeInfo = (NameAndTypeInfo) pool.getConstantInfo(this.getNameAndTypeIndex());
        return typeInfo.getName();
    }

    public String getClassName() {
        ConstantPool pool = this.getConstantPool();
        ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(this.getClassInfoIndex());
        return clzInfo.getClassName();
    }

    public String getParamAndReturnType() {
        ConstantPool pool = this.getConstantPool();
        NameAndTypeInfo typeInfo = (NameAndTypeInfo) pool.getConstantInfo(this.getNameAndTypeIndex());
        return typeInfo.getTypeInfo();
    }

    public MethodRefInfo(ConstantPool pool) {
        super(pool);
    }

    public int getClassInfoIndex() {
        return classInfoIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public void setClassInfoIndex(int classInfoIndex) {
        this.classInfoIndex = classInfoIndex;
    }

    public void setNameAndTypeIndex(int nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}

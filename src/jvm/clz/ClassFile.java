package jvm.clz;

import jvm.constant.ClassInfo;
import jvm.constant.ConstantPool;
import jvm.field.Field;
import jvm.method.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tjc on 2018/4/29.
 */
public class ClassFile {

    private String magic;

    private int minorVersion;

    private int MajorVersion;

    private ClassIndex clzIndex;  //访问标志

    private InterfaceIndex interfaceIndex;

    private List<Field> fields;

    private int fieldCount;

    private List<Method> methods = new ArrayList<>();

    private AccessFlag accessFlag;

    private ConstantPool constantPool = null;

    public AccessFlag getAccessFlag() {
        return accessFlag;
    }

    public void setAccessFlag(AccessFlag accessFlag) {
        this.accessFlag = accessFlag;
    }

    public Object getMinorVersion() {
        return this.minorVersion;
    }

    public Object getMajorVersion() {
        return this.MajorVersion;
    }

    public ConstantPool getConstantPool() {
        return this.constantPool;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.MajorVersion = majorVersion;
    }

    public String getMagic() {
        return magic;
    }

    public void setMagic(String magic) {
        this.magic = magic;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public ClassIndex getClzIndex() {
        return clzIndex;
    }

    public void setClzIndex(ClassIndex clzIndex) {
        this.clzIndex = clzIndex;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields){
        this.fields = fields;
    }

    public InterfaceIndex getInterfaceIndex() {
        return interfaceIndex;
    }

    public void setInterfaceIndex(InterfaceIndex interfaceIndex) {
        this.interfaceIndex = interfaceIndex;
    }

    public int getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(int fieldCount) {
        this.fieldCount = fieldCount;
    }

    public void addMethod(Method m){
        methods.add(m);
    }

    public List<Method> getMethods() {
        return methods;
    }



    public Method getMethod(String methodName, String paramAndReturnType) {
        for(Method m :methods){

            int nameIndex = m.getNameIndex();
            int descriptionIndex = m.getDescriptorIndex();

            String name = this.getConstantPool().getUTF8String(nameIndex);
            String desc = this.getConstantPool().getUTF8String(descriptionIndex);
            if(name.equals(methodName) && desc.equals(paramAndReturnType)){
                return m;
            }
        }
        return null;
    }

    public Method getMainMethod() {

        return getMethod("main","([Ljava/lang/String;)V");
    }

    public String getSuperClassName() {
        ClassInfo superClass = (ClassInfo)this.getConstantPool().getConstantInfo(this.clzIndex.getSuperClassIndex());
        return superClass.getClassName();
    }
}

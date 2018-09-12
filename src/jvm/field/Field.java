package jvm.field;

import jvm.clz.ClassFile;
import jvm.constant.ConstantPool;
import jvm.constant.UTF8Info;
import jvm.loader.ByteCodeIterator;

/**
 * Created by tjc on 2018/7/12.
 */
public class Field {

    private String accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private int arrtibuteCount;
    private ConstantPool pool;

    public Field(String accessFlag, int nameIndex, int descriptorIndex, int arrtibuteCount, ConstantPool pool) {
        this.pool = pool;
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.arrtibuteCount = arrtibuteCount;
    }

    public Field() {

    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }


    public ConstantPool getPool() {
        return pool;
    }

    public void setPool(ConstantPool pool) {
        this.pool = pool;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public String getAccessFlag() {
        return accessFlag;
    }

    public void setAccessFlag(String accessFlag) {
        this.accessFlag = accessFlag;
    }

    public int getArrtibuteCount() {
        return arrtibuteCount;
    }

    public void setArrtibuteCount(int arrtibuteCount) {
        this.arrtibuteCount = arrtibuteCount;
    }

    @Override
    public String toString() {
        String name = ((UTF8Info) pool.getConstantInfo(this.nameIndex)).getValue();

        String desc = ((UTF8Info) pool.getConstantInfo(this.descriptorIndex)).getValue();

        return name + ":" + desc;
    }

    public static Field parse(ClassFile clzFile, ByteCodeIterator it) {
        String accessFlag = it.nextU2ToString();
        int nameIndex = it.nextU2ToInt();
        int descriptorIndex = it.nextU2ToInt();
        int arrtibuteCount = it.nextU2ToInt();
        //没有arrtibuteInfo

        Field field = new Field(accessFlag, nameIndex, descriptorIndex, arrtibuteCount, clzFile
                .getConstantPool());
        field.setAccessFlag(accessFlag);
        field.setNameIndex(nameIndex);
        field.setDescriptorIndex(descriptorIndex);
        field.setArrtibuteCount(arrtibuteCount);

        return field;
    }
}

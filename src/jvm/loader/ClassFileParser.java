package jvm.loader;

import jvm.clz.AccessFlag;
import jvm.clz.ClassFile;
import jvm.clz.ClassIndex;
import jvm.clz.InterfaceIndex;
import jvm.constant.*;
import jvm.field.Field;
import jvm.method.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * 类信息解析器
 * 二维数组 --> Java对象
 *
 * @author tjc
 */
public class ClassFileParser {

    ClassFile clzFile = new ClassFile();

    /**
     * 解析class流
     *
     * @param code
     * @return
     */
    public ClassFile parse(byte[] code) {
        ByteCodeIterator it = new ByteCodeIterator(code);

        clzFile.setMagic(it.nextU4ToString());

        clzFile.setMinorVersion(it.nextU2ToInt());
        clzFile.setMajorVersion(it.nextU2ToInt());

        //解析常量池,有个疑问，常量池数量是属于ConstantPool还是ClassFile?
        clzFile.setConstantPool(parseConstPool(it));

        //class访问标志
        AccessFlag accessFlag = new AccessFlag(it.nextU2ToString());
        clzFile.setAccessFlag(accessFlag);

        //解析类索引,父类索引与接口索引集合
        ClassIndex classIndex = parseClassInfex(it);
        clzFile.setClzIndex(classIndex);
        InterfaceIndex interfaceIndex = parseInterfaces(it);
        clzFile.setInterfaceIndex(interfaceIndex);

        //解析字段表
        parseFields(it);

        //解析方法表
        parseMethod(it);


        return clzFile;
    }

    public ClassIndex parseClassInfex(ByteCodeIterator it) {
        ClassIndex classIndex = new ClassIndex();

        classIndex.setThisClassIndex(it.nextU2ToInt());
        classIndex.setSuperClassIndex(it.nextU2ToInt());
        return classIndex;
    }

    public InterfaceIndex parseInterfaces(ByteCodeIterator it) {
        int interfaceCount = it.nextU2ToInt();

        InterfaceIndex interfaces = new InterfaceIndex();
        int[] interfaceIndex = {};
        for (int i = 0; i < interfaceCount; i++) {
            interfaceIndex = new int[interfaceCount];
            interfaceIndex[i] = it.nextU2ToInt();
        }


        interfaces.setInterfaceCount(interfaceCount);
        interfaces.setInterfaceIndex(interfaceIndex);
        return interfaces;
    }

    /**
     * 解析方法表
     *
     * @param it
     */
    public void parseMethod(ByteCodeIterator it) {
        int methodCount = it.nextU2ToInt();

        for (int i = 0; i < methodCount; i++) {
            Method method = Method.parse(clzFile, it);
            clzFile.addMethod(method);
        }
    }


    /**
     * 解析常量池
     *
     * @param it
     * @return
     */
    private ConstantPool parseConstPool(ByteCodeIterator it) {
        ConstantPool pool = new ConstantPool();

        int constantCount = it.nextU2ToInt();
        pool.setSize(constantCount - 1); //常量池大小

        for (int i = 1; i < constantCount; i++) {
            int tag = it.nextU1ToInt();

            if (tag == ClassInfo.CLASS_INFO) {
                ClassInfo classInfo = new ClassInfo(pool);
                int nameIndex = it.nextU2ToInt();
                classInfo.setNameIndex(nameIndex);
                pool.add(classInfo);
            } else if (tag == ClassInfo.UTF8_INFO) {
                UTF8Info utf8Info = new UTF8Info(pool);
                utf8Info.setLength(it.nextU2ToInt());
                byte[] bytes = new byte[utf8Info.length];
                for (int j = 0; j < utf8Info.length; j++) {
                    bytes[j] = it.nextU1();
                }
                utf8Info.setBytes(bytes);
                pool.add(utf8Info);
            } else if (tag == ClassInfo.METHOD_INFO) {
                MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
                methodRefInfo.setClassInfoIndex(it.nextU2ToInt());
                methodRefInfo.setNameAndTypeIndex(it.nextU2ToInt());
                pool.add(methodRefInfo);
            } else if (tag == ClassInfo.FIELD_INFO) {
                FieldRefInfo fileRefInfo = new FieldRefInfo(pool);
                fileRefInfo.setClassInfoIndex(it.nextU2ToInt());
                fileRefInfo.setNameAndTypeIndex(it.nextU2ToInt());
                pool.add(fileRefInfo);
            } else if (tag == ClassInfo.NAME_AND_TYPE_INFO) {
                NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
                nameAndTypeInfo.setIndex1(it.nextU2ToInt());
                nameAndTypeInfo.setIndex2(it.nextU2ToInt());
                pool.add(nameAndTypeInfo);
            } else if (tag == ClassInfo.STRING_INFO) {
                StringInfo stringInfo = new StringInfo(pool);
                stringInfo.setStringIndex(it.nextU2ToInt());
                pool.add(stringInfo);
            } else {
                throw new RuntimeException("the constant pool tag +" + tag + " has no realize");
            }
        }

        return pool;
    }

    /**
     * @param it
     */
    public void parseFields(ByteCodeIterator it) {
        List<Field> fields = new ArrayList<>();

        int fieldCount = it.nextU2ToInt();


        for (int i = 0; i < fieldCount; i++) {

            Field field = Field.parse(clzFile, it);
            fields.add(field);
        }


        clzFile.setFieldCount(fieldCount);
        clzFile.setFields(fields);

    }

}

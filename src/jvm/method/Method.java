package jvm.method;

import jvm.Attribute.AttributeInfo;
import jvm.Attribute.CodeAttr;
import jvm.clz.ClassFile;
import jvm.cmd.ByteCodeCommand;
import jvm.constant.NameAndTypeInfo;
import jvm.constant.UTF8Info;
import jvm.loader.ByteCodeIterator;

import javax.management.loading.MLetContent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tjc on 2018/7/19.
 */
public class Method {

    private int accessFlag; //访问标志
    private int nameIndex;  //方法名索引
    private int descriptorIndex;//描述符索引
    private int attrbCount;
    private CodeAttr codeAttr; //code属性
    ClassFile clzFile;


    public Method(int accessFlag, int nameIndex, int descriptorIndex, int arttibutesCount, ClassFile clzFile) {
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attrbCount = arttibutesCount;
        this.clzFile = clzFile;
    }

    public Method() {

    }

    /**
     * 方法表集合的解析
     *
     * @param clzFile
     * @param it
     * @return
     */
    public static Method parse(ClassFile clzFile, ByteCodeIterator it) {
        int accessFlag = it.nextU2ToInt();
        int nameIndex = it.nextU2ToInt();
        int descriptorIndex = it.nextU2ToInt();
        int attrbCount = it.nextU2ToInt();

        Method method = new Method(accessFlag, nameIndex, descriptorIndex, attrbCount, clzFile);

        //遍历解析方法表中的属性表
        for (int j = 0; j < attrbCount; j++) {
            int attrNameIndex = it.nextU2ToInt();
            String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
            it.back(2);

            if (AttributeInfo.CODE.equalsIgnoreCase(attrName)) {
                CodeAttr codeAttr = CodeAttr.parse(clzFile, it);

                //翻译成字节码指令

                method.setCodeAttr(codeAttr);
            } else {
                //其他属性集合的情况暂时不写
                throw new RuntimeException("only CODE attribute is implementsd,please implements the " + attrName);
            }

        }
        return method;
    }

    private void setCodeAttr(CodeAttr codeAttr) {
        this.codeAttr = codeAttr;
    }


    public int getNameIndex() {
        return nameIndex;
    }

    public String getMethName() {
        return clzFile.getConstantPool().getUTF8String(nameIndex);
    }

    public CodeAttr getCodeAttr() {
        return codeAttr;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public int getAccessFlag() {
        return accessFlag;
    }

    public void setAccessFlag(int accessFlag) {
        this.accessFlag = accessFlag;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public int getAttrbCount() {
        return attrbCount;
    }

    public void setAttrbCount(int attrbCount) {
        this.attrbCount = attrbCount;
    }

    public ClassFile getClzFile() {
        return clzFile;
    }

    public void setClzFile(ClassFile clzFile) {
        this.clzFile = clzFile;
    }

    public ByteCodeCommand[] getCmds() {
        return codeAttr.getCmds();
    }


    /**
     * 获得参数列表
     * @return
     */
    public List<String> getParameterList() {
        // e.g. (Ljava/util/List;Ljava/lang/String;II)V
        String paramAndType = getParamAndReturnType();

        int first = paramAndType.indexOf("(");
        int last = paramAndType.lastIndexOf(")");
        // e.g. Ljava/util/List;Ljava/lang/String;II
        String param = paramAndType.substring(first + 1, last);

        List<String> paramList = new ArrayList<String>();

        if ((null == param) || "".equals(param)) {
            return paramList;
        }

        while (!param.equals("")) {

            int pos = 0;
            // 这是一个对象类型
            if (param.charAt(pos) == 'L') {

                int end = param.indexOf(";");

                if (end == -1) {
                    throw new RuntimeException("can't find the ; for a object type");
                }
                paramList.add(param.substring(pos + 1, end));

                pos = end + 1;

            } else if (param.charAt(pos) == 'I') {
                // int
                paramList.add("I");
                pos++;

            } else if (param.charAt(pos) == 'F') {
                // float
                paramList.add("F");
                pos++;

            } else {
                throw new RuntimeException("the param has unsupported type:" + param);
            }

            param = param.substring(pos);

        }
        return paramList;
    }

    private String getParamAndReturnType() {
        UTF8Info nameAndTypeInfo = (UTF8Info)this.getClzFile()
                .getConstantPool().getConstantInfo(this.getDescriptorIndex());
        return nameAndTypeInfo.getValue();
    }
}

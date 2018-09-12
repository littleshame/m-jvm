package jvm.engine;

import jvm.clz.ClassFile;
import jvm.constant.MethodRefInfo;
import jvm.loader.ClassFileLoader;
import jvm.method.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tjc on 2018/9/10.
 */
public class MethodArea {

    public static final MethodArea instance = new MethodArea();

    /**
     * 注意：极大的简化， ClassLoader 只有一个， 实际JVM中的ClassLoader,是一个双亲委托的模型
     */

    private ClassFileLoader clzLoader = null;

    Map<String, ClassFile> map = new HashMap<String, ClassFile>();

    public static MethodArea getInstance() {
        return instance;
    }

    public void setClassFileLoader(ClassFileLoader loader) {
        this.clzLoader = loader;
    }

    public Method getMainMethod(String className) {
        ClassFile clzFile = this.findClassFile(className);
        return clzFile.getMainMethod();
    }

    public ClassFile findClassFile(String className) {
        if (map.get(className) != null) {
            return map.get(className);
        }
        // 看来该class 文件还没有load过
        if (clzLoader == null) {
            throw new RuntimeException("clssFileLoader is not initialized");
        }
        ClassFile clzFile = this.clzLoader.loadClass(className);
        map.put(className, clzFile);
        return clzFile;
    }

    public Method getMethod(String className, String methodName, String paramAndReturnType) {

        ClassFile clz = this.findClassFile(className);

        Method m = clz.getMethod(methodName, paramAndReturnType);

        if (m == null) {

            throw new RuntimeException("method can't be found : \n"
                    + "class: " + className
                    + "method: " + methodName
                    + "paramAndReturnType: " + paramAndReturnType);
        }

        return m;
    }

    public Method getMethod(MethodRefInfo methodRef){

        ClassFile clz = this.findClassFile(methodRef.getClassName());

        Method m = clz.getMethod(methodRef.getMethodName(), methodRef.getParamAndReturnType());

        if(m == null){
            throw new RuntimeException("method can't be found : " + methodRef.toString());
        }

        return m;

    }
}

package jvm.engine;

import jvm.clz.ClassFile;
import jvm.constant.ConstantPool;
import jvm.loader.ClassFileLoader;
import jvm.method.Method;

import java.util.List;

/**
 * Created by tjc on 2018/9/4.
 */
public class MiniJVM {


    public void run(String[] classPaths, String className) {

        ClassFileLoader loader = new ClassFileLoader();

        //遍历classpaths,读取class文件
        for (String classPath : classPaths) {
            loader.addClassPath(classPath);
        }

        MethodArea methodArea = MethodArea.getInstance();

        methodArea.setClassFileLoader(loader);

        ExecutorEngine engine = new ExecutorEngine();

        className = className.replace(".","/");

        engine.execute(methodArea.getMainMethod(className));
    }
}

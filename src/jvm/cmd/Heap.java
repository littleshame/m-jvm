package jvm.cmd;

import jvm.engine.JavaObject;

/**
 * Created by tjc on 2018/9/11.
 */
public class Heap {

    private static Heap instance = new Heap();


    public static Heap getInstance() {
        return instance;
    }

    public JavaObject newObject(String clzName) {
        JavaObject jo = new JavaObject(JavaObject.STRING);
        jo.setClassName(clzName);
        return jo;
    }

    public JavaObject newInt(int value) {
        JavaObject jo = new JavaObject(JavaObject.INT);
        jo.setIntValue(value);
        return jo;
    }

    public JavaObject newString(String value) {
        JavaObject jo = new JavaObject(JavaObject.STRING);
        jo.setStringValue(value);
        return jo;
    }

}

package jvm.engine;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by tjc on 2018/9/10.
 */
public class JavaObject {
    public static final int OBJECT = 1;
    public static final int STRING = 2;
    public static final int INT = 3;
    public static final int FLOAT = 4;

    int type;
    private String className;

    private String stringValue;

    private int intValue;

    private float floatValue;

    private Map<String, JavaObject> fieldValues = new HashMap<String,JavaObject>();

    public JavaObject(int type) {
        this.type = type;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }

    public void setFieldValue(String fieldName, JavaObject fieldValue) {
        fieldValues.put(fieldName, fieldValue);
    }

    public int getType(){
        return type;
    }

    public String toString(){
        switch(this.getType()){
            case INT:
                return String.valueOf(this.intValue);
            case STRING:
                return this.stringValue;
            case OBJECT:
                return this.className +":"+ this.fieldValues;
            case FLOAT :
                return String.valueOf(this.floatValue);
            default:
                return null;
        }
    }
}

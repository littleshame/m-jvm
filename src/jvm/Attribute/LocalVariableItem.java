package jvm.Attribute;

/**
 * Created by tjc on 2018/8/28.
 */
public class LocalVariableItem {

    int startpc;
    int length;
    int nameIndex;
    int descIndex;
    int index;

    public LocalVariableItem(int startpc, int length, int nameIndex, int descIndex, int index) {
        this.startpc = startpc;
        this.length = length;
        this.nameIndex = nameIndex;
        this.descIndex = descIndex;
        this.index = index;
    }

    public int getStartpc() {
        return startpc;
    }

    public void setStartpc(int startpc) {
        this.startpc = startpc;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescIndex() {
        return descIndex;
    }

    public void setDescIndex(int descIndex) {
        this.descIndex = descIndex;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

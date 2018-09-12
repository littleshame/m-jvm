package jvm.clz;

/**
 * 常量池中类索引
 * @author tjc
 *
 */
public class ClassIndex {

	private int thisClassIndex;
	private int superClassIndex;


	public ClassIndex() {
	}

	public int getThisClassIndex() {
		return thisClassIndex;
	}
	public void setThisClassIndex(int thisClassIndex) {
		this.thisClassIndex = thisClassIndex;
	}
	public int getSuperClassIndex() {
		return superClassIndex;
	}
	public void setSuperClassIndex(int superClassIndex) {
		this.superClassIndex = superClassIndex;
	}

}

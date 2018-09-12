package jvm.clz;

public class AccessFlag {

	private String flagValue;

	public AccessFlag(String flagValue){
		this.flagValue = flagValue;
	}

	public String getFlagValue() {
		return flagValue;
	}

    public AccessFlag() {
    }

    public void setFlagValue(String flagValue) {
		this.flagValue = flagValue;
	}
}

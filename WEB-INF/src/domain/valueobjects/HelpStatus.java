package domain.valueobjects;

public class HelpStatus {

	public static final HelpStatus None = new HelpStatus(0);
	public static final HelpStatus Troubled = new HelpStatus(1);
	public static final HelpStatus Supporting = new HelpStatus(2);

	private int value;

	public HelpStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String getDisplayName() {
		switch (value) {
		case 0:
			return "None";
		case 1:
			return "Troubled";
		default:
			return "Supporting";
		}
	}
	
	public static HelpStatus fromString(String helpStatusString) {
		if (helpStatusString.equals("Trounbled")) {
			return HelpStatus.Troubled;
		} else if (helpStatusString.equals("Supporting")) {
			return HelpStatus.Supporting;
		} else {
			return HelpStatus.None;
		}
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof HelpStatus)) {
			return false;
		}
		HelpStatus other = (HelpStatus) obj;
		return value == other.value;
	}

}

package model;

public class HelpStatusHelper {

	public static HelpStatus fromString(String helpStatusString) {
		if (helpStatusString.equals("Trounbled")) {
			return HelpStatus.Troubled;
		} else if (helpStatusString.equals("Supporting")) {
			return HelpStatus.Supporting;
		} else {
			return HelpStatus.None;
		}
	}
}

package helper;

import model.HelpStatus;

public class HelpStatusConverter {

	public static HelpStatus getHelpStatus(String helpStatusString) {
		if (helpStatusString.equals("Troubled")) {
			return HelpStatus.Troubled;
		} else if (helpStatusString.equals("Supporting")) {
			return HelpStatus.Supporting;
		} else {
			return HelpStatus.None;
		}
	}
}

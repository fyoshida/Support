package model;

import java.util.StringTokenizer;

public class IpAddress {
	private char[] addresses = new char[4];

	public IpAddress(String ipAddressString) {
		try {
			if (ipAddressString == null || ipAddressString.equals("")) {
				throw new NullPointerException();
			}

			StringTokenizer st = new StringTokenizer(ipAddressString, ".");
			if (st.countTokens() != 4) {
				throw new IpAddressException();
			}

			for (int i = 0; i < 4; i++) {
				int address = Integer.parseInt(st.nextToken());
				addresses[i] = checkAddress(address);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void set(int i, char address) {
		addresses[i] = checkAddress(address);
	}

	public String get() {
		return "" + addresses[0] + "." + addresses[1] + "." + addresses[2] + "." + addresses[3];
	}

	private char checkAddress(int address) {
		try {
			if (address < 0 || address >= 255) {
				throw new IpAddressException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (char) address;
	}
}

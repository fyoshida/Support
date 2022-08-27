package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

public class IpAddressTest {

	public static final String SAMPLE_ID1 = "ics801";
	public static final String SAMPLE_ID2 = "ics725";

	public static final String ERR_IPADDRESS_SHORTAGE = "0.0.0";
	public static final String ERR_IPADDRESS_EXCESS = "0.0.0.0.0";
	public static final String ERR_IPADDRESS_OVER = "133.44.118.256";
	public static final String ERR_IPADDRESS_LITTLE = "133.44.118.-1";

	public static final String IPADDRESS_ROOT = "0.0.0.0";
	public static final String IPADDRESS_BROADCAST = "255.255.255.255";
	public static final String IPADDRESS_GATEWAY = "133.44.118.254";
	public static final String IPADDRESS_1 = "133.44.118.158";
	public static final String IPADDRESS_2 = "133.44.118.228";

	@Test
	public void Nullや空の文字列ではIPアドレスを生成できない() {
		assertThrows(NullPointerException.class, () -> new IpAddress(null));
		assertThrows(IllegalArgumentException.class, () -> new IpAddress(""));
	}

	@Test
	public void 要素が4つでないとIPアドレスを生成できない() {
		assertThrows(IllegalArgumentException.class, () -> new IpAddress(ERR_IPADDRESS_SHORTAGE));
		assertThrows(IllegalArgumentException.class, () -> new IpAddress(ERR_IPADDRESS_EXCESS));
	}

	@Test
	public void 要素の値が0ー255でないとIPアドレスを生成できない() {
		assertThrows(IllegalArgumentException.class, () -> new IpAddress(ERR_IPADDRESS_LITTLE));
		assertThrows(IllegalArgumentException.class, () -> new IpAddress(ERR_IPADDRESS_OVER));
	}

	@Test
	public void 正しい文字列からIPアドレスを生成できる() throws Exception {
		IpAddress ipAddress1 = new IpAddress(IPADDRESS_1);
		assertEquals(ipAddress1.get(),IPADDRESS_1);

		IpAddress ipAddress2 = new IpAddress(IPADDRESS_1);
		assertEquals(ipAddress2.get(),IPADDRESS_1);

		assertDoesNotThrow(() -> new IpAddress(IPADDRESS_ROOT));
		assertDoesNotThrow(() -> new IpAddress(IPADDRESS_BROADCAST));
		assertDoesNotThrow(() -> new IpAddress(IPADDRESS_GATEWAY));
		assertDoesNotThrow(() -> new IpAddress(IPADDRESS_1));
		assertDoesNotThrow(() -> new IpAddress(IPADDRESS_2));
	}
}
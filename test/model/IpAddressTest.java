package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import _test_data.Cnst;

public class IpAddressTest {


	@Test
	public void Nullや空の文字列ではIPアドレスを生成できない() {
		assertThrows(NullPointerException.class, () -> new IpAddress(null));
		assertThrows(IllegalArgumentException.class, () -> new IpAddress(""));
	}

	@Test
	public void 要素が4つでないとIPアドレスを生成できない() {
		assertThrows(IllegalArgumentException.class, () -> new IpAddress(Cnst.ERR_IPADDRESS_SHORTAGE));
		assertThrows(IllegalArgumentException.class, () -> new IpAddress(Cnst.ERR_IPADDRESS_EXCESS));
	}

	@Test
	public void 要素の値が0ー255でないとIPアドレスを生成できない() {
		assertThrows(IllegalArgumentException.class, () -> new IpAddress(Cnst.ERR_IPADDRESS_LITTLE));
		assertThrows(IllegalArgumentException.class, () -> new IpAddress(Cnst.ERR_IPADDRESS_OVER));
	}

	@Test
	public void 正しい文字列からIPアドレスを生成できる() throws Exception {
		IpAddress ipAddress1 = new IpAddress(Cnst.IPADDRESS_1);
		assertEquals(ipAddress1.get(),Cnst.IPADDRESS_1);

		IpAddress ipAddress2 = new IpAddress(Cnst.IPADDRESS_1);
		assertEquals(ipAddress2.get(),Cnst.IPADDRESS_1);

		assertDoesNotThrow(() -> new IpAddress(Cnst.IPADDRESS_ROOT));
		assertDoesNotThrow(() -> new IpAddress(Cnst.IPADDRESS_BROADCAST));
		assertDoesNotThrow(() -> new IpAddress(Cnst.IPADDRESS_GATEWAY));
		assertDoesNotThrow(() -> new IpAddress(Cnst.IPADDRESS_1));
		assertDoesNotThrow(() -> new IpAddress(Cnst.IPADDRESS_2));
	}
}
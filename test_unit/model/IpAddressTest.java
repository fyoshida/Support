package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import _data.Const;

public class IpAddressTest {

	@Test
	public void Nullや空の文字列ではIPアドレスを生成できない() {
		assertThrows(NullPointerException.class, () -> new IpAddress(null));
		assertThrows(IllegalArgumentException.class, () -> new IpAddress(""));
	}

	@Test
	public void 要素が4つでないとIPアドレスを生成できない() {
		assertThrows(IllegalArgumentException.class, () -> new IpAddress(Const.ERR_IPADDRESS_SHORTAGE));
		assertThrows(IllegalArgumentException.class, () -> new IpAddress(Const.ERR_IPADDRESS_EXCESS));
	}

	@Test
	public void 要素の値が0ー255でないとIPアドレスを生成できない() {
		assertThrows(IllegalArgumentException.class, () -> new IpAddress(Const.ERR_IPADDRESS_LITTLE));
		assertThrows(IllegalArgumentException.class, () -> new IpAddress(Const.ERR_IPADDRESS_OVER));
	}

	@Test
	public void 正しい文字列からIPアドレスを生成できる() throws Exception {
		IpAddress ipAddress1 = new IpAddress(Const.IPADDRESS_1);
		assertEquals(ipAddress1.get(),Const.IPADDRESS_1);

		IpAddress ipAddress2 = new IpAddress(Const.IPADDRESS_1);
		assertEquals(ipAddress2.get(),Const.IPADDRESS_1);

		assertDoesNotThrow(() -> new IpAddress(Const.IPADDRESS_ROOT));
		assertDoesNotThrow(() -> new IpAddress(Const.IPADDRESS_BROADCAST));
		assertDoesNotThrow(() -> new IpAddress(Const.IPADDRESS_GATEWAY));
		assertDoesNotThrow(() -> new IpAddress(Const.IPADDRESS_1));
		assertDoesNotThrow(() -> new IpAddress(Const.IPADDRESS_2));
	}
}
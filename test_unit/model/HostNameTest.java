package model;

import static org.junit.Assert.*;

import org.junit.Test;

import _old.model.HostName;

public class HostNameTest {

	@Test
	public void ホスト名がNullではPCを生成できない() {
		assertThrows(NullPointerException.class,()->new HostName(null));
	}

	@Test
	public void ホスト名が13文字以上ではPCを生成できない() {
		assertThrows(IllegalArgumentException.class,()->new HostName("1234567890123"));
	}

	@Test
	public void ホスト名に英数字以外の文字があるとPCを生成できない() {
		assertThrows(IllegalArgumentException.class,()->new HostName("ics800-"));
	}

}
package beans;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class PcTest {

	private static final Date SAMPLE_DATE1 = new Date(2022, 8, 15, 13, 24, 30);
	private static final Date SAMPLE_DATE2 = new Date(2021, 7, 9, 17, 56, 14);

	@Test
	public void 属性LastHandTimeはアクセッサで読み書きできる() {
		Pc pc = new Pc();

		pc.setLastHandTime(SAMPLE_DATE1);
		assertEquals(pc.getLastHandTime(), SAMPLE_DATE1);

		pc.setLastHandTime(SAMPLE_DATE2);
		assertEquals(pc.getLastHandTime(), SAMPLE_DATE2);
	}

	@Test
	public void 属性LastRequestTimeはアクセッサで読み書きできる() {
		Pc pc = new Pc();

		pc.setLastRequestTime(SAMPLE_DATE1);
		assertEquals(pc.getLastRequestTime(), SAMPLE_DATE1);

		pc.setLastRequestTime(SAMPLE_DATE2);
		assertEquals(pc.getLastRequestTime(), SAMPLE_DATE2);
	}
}
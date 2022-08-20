package helper;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import model.Pc;

public class PcListFileReaderTest{

	private static final String SAMPLE_FILENAME1="./WEB-INF/data/pcIdTable.csv";
	private static final String SAMPLE_FILENAME2="./test/helper/pcIdTable.csv";

	private static final List<String> SAMPLE_LIST1= Arrays.asList("ics801,133.44.118.158,true","ics802,133.44.118.159,true");

	private static final String SAMPLE_Line1= "ics801,133.44.118.158,true";
	private static final String SAMPLE_Line2= "ics802,133.44.118.159,true";

	@Test
	public void ファイルから読み込んだ行数が正しい() {
		PcListFileReader reader = new PcListFileReader();

		List<String> answerList1=reader.getLinesFromFile(SAMPLE_FILENAME1);
		assertEquals(answerList1.size(),72);

		List<String> answerList2=reader.getLinesFromFile(SAMPLE_FILENAME2);
		assertEquals(answerList2.size(),72);
	}

	@Test
	public void ファイルから読み込んだデータが正しい() {
		PcListFileReader reader = new PcListFileReader();

		List<Pc> pcList=reader.getPcListFromLines(SAMPLE_LIST1);

		assertEquals(pcList.size(),2);

		Pc pc1=pcList.get(0);
		assertEquals(pc1.getPcId(),"ics801");
		assertEquals(pc1.getIpAdress(),"133.44.118.158");
		assertTrue(pc1.getIsStudent());

		Pc pc2=pcList.get(1);
		assertEquals(pc2.getPcId(),"ics802");
		assertEquals(pc2.getIpAdress(),"133.44.118.159");
		assertTrue(pc2.getIsStudent());
	}

	@Test
	public void 文字列を正しくPcに変換できる() {
		PcListFileReader reader = new PcListFileReader();

		Pc pc1=reader.getPcInfoFromLine(SAMPLE_Line1);
		assertEquals(pc1.getPcId(),"ics801");
		assertEquals(pc1.getIpAdress(),"133.44.118.158");
		assertTrue(pc1.getIsStudent());

		Pc pc2=reader.getPcInfoFromLine(SAMPLE_Line2);

		assertEquals(pc2.getPcId(),"ics802");
		assertEquals(pc2.getIpAdress(),"133.44.118.159");
		assertTrue(pc2.getIsStudent());
	}

}
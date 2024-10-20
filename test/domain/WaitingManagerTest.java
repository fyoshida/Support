package domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.aggregate.WaitingManager;
import domain.entities.Pc;
import domain.entities.Student;
import domain.valueobjects.IpAddress;

public class WaitingManagerTest {
	public static final String IPADDRESS_GATEWAY = "133.44.118.254";
	public static final String IPADDRESS_1 = "133.44.118.158";
	public static final String IPADDRESS_2 = "133.44.118.228";

	private IpAddress ipAddress1;
	private IpAddress ipAddress2;
	private IpAddress ipAddress3;
	
	private Student student1;
	private Student student2;
	private Student student3;

	@Before
	public void setUp() {
		ipAddress1 = new IpAddress(IPADDRESS_1);
		ipAddress2 = new IpAddress(IPADDRESS_2);
		ipAddress3 = new IpAddress(IPADDRESS_GATEWAY);

		Pc pc1 = new Pc(ipAddress1,"");
		Pc pc2 = new Pc(ipAddress2,"");
		Pc pc3 = new Pc(ipAddress3,"");
		
		student1=new Student(pc1);
		student2=new Student(pc2);
		student3=new Student(pc3);
	}

	@Test
	public void 登録した順序を取得できる() {

		WaitingManager wm = new WaitingManager();
		wm.regist(student1);
		wm.regist(student2);

		assertEquals(wm.getPriority(student1), 1);
		assertEquals(wm.getPriority(student2), 2);
	}

	@Test
	public void 全部削除できる() {

		WaitingManager wm = new WaitingManager();
		wm.regist(student1);
		wm.regist(student2);
		wm.regist(student3);
		wm.unregist(student2);
		wm.unregist(student1);
		wm.unregist(student3);

		assertEquals(wm.getPriority(student1), WaitingManager.NOT_REGISTED);
		assertEquals(wm.getPriority(student2), WaitingManager.NOT_REGISTED);
		assertEquals(wm.getPriority(student3), WaitingManager.NOT_REGISTED);
	}

	@Test
	public void 先頭を削除しても正しく順序を取得できる() {

		WaitingManager wm = new WaitingManager();
		wm.regist(student1);
		wm.regist(student2);
		wm.regist(student3);
		wm.unregist(student1);

		assertEquals(wm.getPriority(student1), WaitingManager.NOT_REGISTED);
		assertEquals(wm.getPriority(student2), 1);
		assertEquals(wm.getPriority(student3), 2);
	}

	@Test
	public void 中央を削除しても正しく順序を取得できる() {

		WaitingManager wm = new WaitingManager();
		wm.regist(student1);
		wm.regist(student2);
		wm.regist(student3);
		wm.unregist(student2);

		assertEquals(wm.getPriority(student1), 1);
		assertEquals(wm.getPriority(student2), WaitingManager.NOT_REGISTED);
		assertEquals(wm.getPriority(student3), 2);
	}

	@Test
	public void 削除して再登録すると最後になる_1() {

		WaitingManager wm = new WaitingManager();
		wm.regist(student1);
		wm.regist(student2);
		wm.regist(student3);
		wm.unregist(student1);
		wm.regist(student1);

		assertEquals(wm.getPriority(student2), 1);
		assertEquals(wm.getPriority(student3), 2);
		assertEquals(wm.getPriority(student1), 3);
	}

	@Test
	public void 削除して再登録すると最後になる_2() {

		WaitingManager wm = new WaitingManager();
		wm.regist(student1);
		wm.regist(student2);
		wm.regist(student3);
		wm.unregist(student2);
		wm.regist(student2);

		assertEquals(wm.getPriority(student1), 1);
		assertEquals(wm.getPriority(student2), 3);
		assertEquals(wm.getPriority(student3), 2);
	}

}
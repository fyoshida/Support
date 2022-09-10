package model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import _data.Const;

public class StudentTest {

	private Pc pc;
	private WaitingManager<Student> waitingManager;
	private Student student;

	@BeforeEach
	void setUp() {
		pc = new Pc(Const.IPADDRESS_1, Const.HOSTNAME_1, true);
		waitingManager = new WaitingManager<Student>();
		student = new Student(pc, waitingManager);
	}

	@Test
	public void 生成直後のPCの状態を確認() {
		// 結果
		Pc actual = student.getPc();
		assertThat(actual).isNotNull();
	}

	@Test
	public void 生成直後のStudentの状態を確認() {
		// 結果
		HelpStatus actual = student.getHelpStatus();
		HelpStatus expect = HelpStatus.None;
		assertThat(actual).isEqualTo(expect);

		// 結果
		LocalDateTime actualHandUpTime = student.getHandUpTime();
		assertThat(actualHandUpTime).isNull();

		// 結果
		int actualPriority = student.getPriority();
		int expectPriority = WaitingManager.NOT_REGISTED;
		assertThat(actualPriority).isEqualTo(expectPriority);

		// 結果
		Duration actualWaitingTime = student.getWaitingTime(LocalDateTime.now());
		assertThat(actualWaitingTime).isNull();
	}

	@Test
	public void アクセッサでユーザ名を読み書きできる() {
		student.setUserName("abc");
		assertEquals(student.getUserName(), "abc");
	}

}
package helper;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import _data.Const;

public class PcJsonTest {
	private PcJson pcJson;

	@BeforeEach
	void setUp() {
		pcJson = new PcJson();
	}

	@Test
	void 属性IPアドレスをアクセッサで読み書きできる() {
		// 処理
		pcJson.setIpAdress(Const.IPADDRESS_GATEWAY);

		// 結果
		String actual = pcJson.getIpAdress();
		String expect = Const.IPADDRESS_GATEWAY;
		assertThat(actual).isEqualTo(expect);
	}

	@Test
	void 属性HostNameアクセッサで読み書きできる() {
		// 処理
		pcJson.setPcId("ics800");

		// 結果
		String actual = pcJson.getPcId();
		String expect = "ics800";
		assertThat(actual).isEqualTo(expect);
	}

	@ParameterizedTest
	@ValueSource(booleans = { true, false })
	void 属性IsStudentをアクセッサで読み書きできる(Boolean boolValue) {
		// 処理
		pcJson.setIsStudent(boolValue);

		// 結果
		boolean actual = pcJson.getIsStudent();
		boolean expect = boolValue;
		assertThat(actual).isEqualTo(expect);
	}

	@ParameterizedTest
	@ValueSource(booleans = { true, false })
	void 属性IsLoginをアクセッサで読み書きできる(Boolean boolValue) {
		// 処理
		pcJson.setIsLogin(boolValue);

		// 結果
		boolean actual = pcJson.getIsLogin();
		boolean expect = boolValue;
		assertThat(actual).isEqualTo(expect);
	}

	@ParameterizedTest
	@ValueSource(strings = { "None", "Troubled", "Supporting" })
	void 属性HelpStatusをアクセッサで読み書きできる(String statusValue) {
		//-------------------------
		//手を挙げていない: None
		//手を挙げている: Troubled
		//TA教員対応中: Supporting
		//-------------------------
		// 処理
		pcJson.setHelpStatus(statusValue);

		// 結果
		String actual = pcJson.getHelpStatus();
		String expect = statusValue;
		assertThat(actual).isEqualTo(expect);
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 999})
	public void 属性HandPriorityをアクセッサで読み書きできる(int intValue) {
		// 処理
		pcJson.setHandPriority(intValue);

		// 結果
		int actual = pcJson.getHandPriority();
		int expect = intValue;
		assertThat(actual).isEqualTo(expect);
	}
}

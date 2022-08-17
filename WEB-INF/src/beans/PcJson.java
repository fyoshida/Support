package beans;

public class PcJson{

	protected String pcId = null; //icsXXX
	protected String ipAdress = null; //ドメインは133.44.118.158-228の間
	protected Boolean isStudent = null; // true:学生が利用するPC, false:TA,教員が利用するPC
	protected Boolean isLogin = null; // true:ログイン中, false:未ログイン
	protected HelpStatus helpStatus = null; // 手を挙げていない: None
											//	手を挙げている: Troubled
											// TA教員対応中: Supporting
	protected int handPriority = -1;

	//--------アクセッサ--------------

	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public String getIpAdress() {
		return ipAdress;
	}
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public Boolean getIsStudent() {
		return isStudent;
	}
	public void setIsStudent(Boolean isStudent) {
		this.isStudent = isStudent;
	}

	public Boolean getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}

	public HelpStatus getHelpStatus() {
		return helpStatus;
	}
	public void setHelpStatus(HelpStatus helpStatus) {
		this.helpStatus = helpStatus;
	}

	public int getHandPriority() {
		return handPriority;
	}
	public void setHandPriority(int handPriority) {
		this.handPriority = handPriority;
	}
}

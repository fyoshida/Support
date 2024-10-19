package helper;

public class PcJson{

	protected String pcId = null; //icsXXX
	protected String ipAdress = null; //ドメインは133.44.118.158-228の間
	protected String helpStatus = null; // 手を挙げていない: None
											//	手を挙げている: Troubled
											// TA教員対応中: Supporting
	protected long waitingTimeBySecond =0;
	protected int handPriority = -1;

	//--------アクセッサ---------
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

	public int getHandPriority() {
		return handPriority;
	}
	public void setHandPriority(int handPriority) {
		this.handPriority = handPriority;
	}
	public String getHelpStatus() {
		return helpStatus;
	}
	public void setHelpStatus(String helpStatus) {
		this.helpStatus = helpStatus;
	}
	public long getWaitingTimeBySecond() {
		return waitingTimeBySecond;
	}
	public void setWaitingTimeBySecond(long waitingTime) {
		this.waitingTimeBySecond = waitingTime;
	}
	

}

package model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PcManager {

	private final Map<String,Pc> pcMap=new HashMap<String,Pc>();
	private final List<Pc> handPcList=new LinkedList<Pc>();

	public PcManager(List<Pc> pcList) {
		for(Pc pc :pcList) {
			pcMap.put(pc.pcId,pc);
		}
	}

	public void setLogin(String pcId, boolean b) {
		Pc pc = pcMap.get(pcId);
		if( pc != null ) {
			pc.login();
		}
	}

	public int getHandPriority(Student student) {
		return handPriority;
	}


	public void setHelpStatus(String pcId, HelpStatus helpStatus) {
		int i = 0;
		for (Pc pc : pcList) {
			if (pcId.equals(pc.getPcId())) {
				Pc pcTmp = new Pc();
				pcTmp = pc;
				pcTmp.setHelpStatus(helpStatus);
				pcList.set(i, pcTmp);
			} else {
				i++;
			}
		}
	}

	public Date setRequestTime(String pcId) {
		for (Pc pc : pcList) {
			if (pcId.equals(pc.getPcId())) {
				long millis = System.currentTimeMillis();
				Timestamp timestamp = new Timestamp(millis);
				Date dt = new Date(timestamp.getTime());
				//リクエストをしたpcの最終アクセス時間を格納
				pc.setLastRequestTime(dt);
				return pc.getLastRequestTime();
			}
		}
		return null;
	}

	public Date setHandTime(String pcId, Boolean resetFlag) {
		for (Pc pc : pcList) {
			if (pcId.equals(pc.getPcId())) {

				//引数resetFlagがtrueの時、挙手した時間をリセットする
				if (resetFlag) {
					pc.setLastHandTime(null);
				} else {
					long millis = System.currentTimeMillis();
					Timestamp timestamp = new Timestamp(millis);
					Date dt = new Date(timestamp.getTime());
					//リクエストをしたpcの最終アクセス時間を格納
					pc.setLastHandTime(dt);
				}
				return pc.getLastHandTime();
			}
		}
		return null;
	}



	public Date getRequestTime(String pcId) {
		for (Pc pc : pcList) {
			if (pcId.equals(pc.getPcId())) {
				//リクエストをしたpcの最終アクセス時間をリターン
				return pc.getLastRequestTime();
			}
		}
		return null;
	}



	public Date getHandTime(String pcId) {
		for (Pc pc : pcList) {
			if (pcId.equals(pc.getPcId())) {
				//リクエストをしたpcの最終アクセス時間をリターン
				return pc.getLastHandTime();
			}
		}
		return null;
	}

	private Pc getPcFromPcId(String pcId) {
		for(Pc pc : pcList) {
			if(pcId.equals(pc.getPcId())) {
				return pc;
			}
		}
		return null;
	}

	public Pc getPcFromIpAddr(String addr) {
		for (Pc pc : pcList) {
			if (addr.equals(pc.getIpAdress())) {
				return pc;
			}

		}
		return null;
	}


}

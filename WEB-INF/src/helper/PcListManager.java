package helper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import beans.HelpStatus;
import beans.Pc;
import servlet.StartServlet;

public class PcListManager {

	private final List<Pc> pcList;

	public PcListManager(List<Pc> pcList) {
		this.pcList=pcList;
	}

	private Optional<Pc> getPc(String pcId) {
//		for (Pc pc : pcList) {
//			if (pc.isMatchPcId(pcId)) {
//				return pc;
//			}
//		}
////		return null;

		return pcList.stream().filter(pc -> pc.isSamePcId(pcId)).forEach(pc ->pc.).findAny();
	}

	public void setLogin(String pcId, boolean b) {
		Optional<Pc> optPc=getPc(pcId);
		if( optPc.isPresent() ) {
			optPc.get().setIsLogin(b);
		}
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

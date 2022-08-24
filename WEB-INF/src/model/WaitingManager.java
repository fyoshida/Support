package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class WaitingManager {
	private LinkedList<PcId> waitingList = new LinkedList<PcId>();
	private Map<PcId, Integer> orderMap = new HashMap<PcId, Integer>();

	public void regist(PcId pcId) {
		waitingList.addLast(pcId);
		int pos = waitingList.size()-1;
		orderMap.put(pcId, pos);
	}

	public void unregist(PcId pcId) {
		int pos = orderMap.get(pcId);
		waitingList.remove(pos);
		for (int i = pos; i < waitingList.size(); i++) {
			PcId mpcId = waitingList.get(i);
			int mpos = orderMap.get(mpcId);
			orderMap.put(mpcId, mpos - 1);
		}
	}

	public int getPriority(PcId pcId) {
		if( !orderMap.containsKey(pcId)) {
			return 999;
		}else {
			return orderMap.get(pcId)+1;
		}
	}
}

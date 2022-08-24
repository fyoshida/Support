package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class WaitingManager {
	private LinkedList<IpAddress> waitingList = new LinkedList<IpAddress>();
	private Map<IpAddress, Integer> orderMap = new HashMap<IpAddress, Integer>();

	public void regist(IpAddress ipAddress) {
		waitingList.addLast(ipAddress);
		int pos = waitingList.size()-1;
		orderMap.put(ipAddress, pos);
	}

	public void unregist(IpAddress pcId) {
		int pos = orderMap.get(pcId);
		waitingList.remove(pos);
		for (int i = pos; i < waitingList.size(); i++) {
			IpAddress mpcId = waitingList.get(i);
			int mpos = orderMap.get(mpcId);
			orderMap.put(mpcId, mpos - 1);
		}
	}

	public int getPriority(IpAddress ipAddress) {
		if( !orderMap.containsKey(ipAddress)) {
			return 999;
		}else {
			return orderMap.get(ipAddress)+1;
		}
	}
}

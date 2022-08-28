package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class WaitingManager {
	public static final int NOT_REGISTED = 999;

	private LinkedList<IpAddress> waitingList = new LinkedList<IpAddress>();
	private Map<IpAddress, Integer> rankMap = new HashMap<IpAddress, Integer>();

	public void regist(IpAddress ipAddress) {
		int rank = waitingList.size();
		waitingList.addLast(ipAddress);
		rankMap.put(ipAddress, rank);
	}

	public void unregist(IpAddress ipAddress) {
		if (!waitingList.contains(ipAddress)) {
			return;
		}
		int rank = rankMap.get(ipAddress);
		waitingList.remove(rank);
		rankMap.remove(ipAddress);

		resetRankMap(rank);
	}

	public void resetRankMap(int unregistRank) {
		for (IpAddress ipAddress : rankMap.keySet()) {
			int rank = rankMap.get(ipAddress);
			if (rank > unregistRank) {
				rankMap.put(ipAddress, rank - 1);
			}
		}

	}

	public int getPriority(IpAddress ipAddress) {
		if (!rankMap.containsKey(ipAddress)) {
			return 999;
		} else {
			return rankMap.get(ipAddress) + 1;
		}
	}
}

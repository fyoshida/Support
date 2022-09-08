package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class WaitingManager<T> {
	public static final int NOT_REGISTED = 999;

	private LinkedList<T> waitingList = new LinkedList<T>();
	private Map<T, Integer> rankMap = new HashMap<T, Integer>();

	public void regist(T target) {
		int rank = waitingList.size();
		waitingList.addLast(target);
		rankMap.put(target, rank);
	}

	public void unregist(T target) {
		if (!waitingList.contains(target)) {
			return;
		}
		int rank = rankMap.get(target);
		waitingList.remove(rank);
		rankMap.remove(target);

		updateRankMap(rank);
	}

	public void updateRankMap(int unregistRank) {
		for (T target : rankMap.keySet()) {
			int rank = rankMap.get(target);
			if (rank > unregistRank) {
				rankMap.put(target, rank - 1);
			}
		}
	}

	public int getPriority(T target) {
		if (!rankMap.containsKey(target)) {
			return NOT_REGISTED;
		} else {
			return rankMap.get(target) + 1;
		}
	}

	public void clrar() {
		waitingList.clear();
		rankMap.clear();
	}
}

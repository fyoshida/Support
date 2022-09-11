package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class WaitingManager<T> {
	public static final int NOT_REGISTED = 999;

	private LinkedList<T> waitingList = new LinkedList<T>();
	private LinkedList<LocalDateTime> registTime = new LinkedList<LocalDateTime>();
	private Map<T, Integer> rankMap = new HashMap<T, Integer>();

	public void regist(T target) {
		int rank = waitingList.size();
		waitingList.addLast(target);
		registTime.addLast(LocalDateTime.now());
		rankMap.put(target, rank);
	}

	public void unregist(T target) {
		if (!waitingList.contains(target)) {
			return;
		}
		int rank = rankMap.get(target);
		waitingList.remove(rank);
		registTime.remove(rank);
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
		if (rankMap.containsKey(target)) {
			return rankMap.get(target) + 1;
		} else {
			return NOT_REGISTED;
		}
	}

	public Duration getWaitingTime(T target) {
		if (rankMap.containsKey(target)) {
			int pos = rankMap.get(target);
			LocalDateTime handUpTime = registTime.get(pos);
			LocalDateTime currentTime = LocalDateTime.now();
			return Duration.between(currentTime, handUpTime);
		} else {
			return null;
		}
	}

	public void clrar() {
		waitingList.clear();
		registTime.clear();
		rankMap.clear();
	}
}

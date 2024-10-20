package domain.aggregate;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import domain.valueobjects.IpAddress;

public class WaitingManager {
	public static final int NOT_REGISTED = 999;

	private LinkedList<IpAddress> waitingList = new LinkedList<IpAddress>();

	public boolean isRegisted(IpAddress ipAddress) {
		return waitingList.contains(ipAddress);
	}
	
	public void regist(IpAddress ipAddress) {
		if (!isRegisted(ipAddress)) {
			waitingList.addLast(ipAddress);
		}
	}

	public void unregist(IpAddress ipAddress) {
		waitingList.removeIf(ip -> ip.equals(ipAddress));
	}

	public List<IpAddress> getAll() {
		List<IpAddress> pcList = waitingList.stream().toList();
		return Collections.unmodifiableList(pcList);
	}

	public int getPriority(IpAddress ipAddress) {
		int pos = waitingList.indexOf(ipAddress);
		if (pos < 0) {
			return NOT_REGISTED;
		}

		return pos + 1;
	}

	public void clrar() {
		waitingList.clear();
	}
}

package domain.aggregate;

import java.net.InetAddress;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class WaitingManager {
	public static final int NOT_REGISTED = 999;

	private LinkedList<InetAddress> waitingList = new LinkedList<InetAddress>();

	public boolean isRegisted(InetAddress ipAddress) {
		return waitingList.contains(ipAddress);
	}
	
	public void regist(InetAddress ipAddress) {
		if (!isRegisted(ipAddress)) {
			waitingList.addLast(ipAddress);
		}
	}

	public void unregist(InetAddress ipAddress) {
		waitingList.removeIf(ip -> ip.equals(ipAddress));
	}

	public List<InetAddress> getAll() {
		List<InetAddress> pcList = waitingList.stream().toList();
		return Collections.unmodifiableList(pcList);
	}

	public int getPriority(InetAddress ipAddress) {
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

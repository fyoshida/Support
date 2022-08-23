package model;

import java.util.LinkedList;

public class PriorityManager {
	private static LinkedList<String> priorityList = new LinkedList<String>();

	public static void regist(String pcId) {
		priorityList.addLast(pcId);
	}

	public static void unregist(String pcId) {
		for(String currentPcId :priorityList) {
			if(currentPcId.equals(pcId)) {
				priorityList.remove(pcId);
			}
		}
	}

	public static int getPriority(String pcId) {
		for(int i=0;i<priorityList.size();i++) {
			String currentPcId=priorityList.get(i);
			if(currentPcId.equals(pcId)) {
				return i;
			}
		}
		return 999;
	}
}

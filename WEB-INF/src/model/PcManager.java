package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PcManager {

	private final Map<String,Pc> pcMap=new HashMap<String,Pc>();

	public PcManager(List<Pc> pcList) {
		for(Pc pc :pcList) {
			pcMap.put(pc.getPcId(),pc);
		}
	}

	public Pc getPc(String pcId){
		return pcMap.get(pcId);
	}

}

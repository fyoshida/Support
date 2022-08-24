package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PcManager {

	private final Map<PcId,Pc> pcMap=new HashMap<PcId,Pc>();

	public PcManager(List<Pc> pcList) {
		for(Pc pc :pcList) {
			pcMap.put(pc.getPcId(),pc);
		}
	}

	public Pc getPc(PcId pcId){
		return pcMap.get(pcId);
	}

}

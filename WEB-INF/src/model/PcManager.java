package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PcManager {

	private final Map<PcId,PcClient> pcMap=new HashMap<PcId,PcClient>();

	public PcManager(List<PcClient> pcList) {
		for(PcClient pc :pcList) {
			pcMap.put(pc.getPcId(),pc);
		}
	}

	public PcClient getPc(PcId pcId){
		return pcMap.get(pcId);
	}

}

package repository.list;

import java.util.List;

import model.Pc;
import model.PcClient;
import model.PcId;
import repository.RepositoryInterface;

public class ListRepository implements RepositoryInterface{
	final private List<PcClient> pcClientList;

	public ListRepository(List<PcClient> pcClientList) {
		this.pcClientList=pcClientList;
	}

	@Override
	public List<PcClient> getPcClientList() {
		return pcClientList;
	}

//	@Override
//	public void updatePcClient(PcClient pcClient) {
//		PcId pcId = pcClient.getPcId();
//		pcClientList.put(pcId,pcClient);
//	}


}

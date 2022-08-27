package repository.list;

import java.util.List;

import model.PcBean;
import repository.RepositoryInterface;

public class ListRepository implements RepositoryInterface {
	final private List<PcBean> pcClientList;

	public ListRepository(List<PcBean> pcClientList) {
		this.pcClientList = pcClientList;
	}

	@Override
	public List<PcBean> getPcList() {
		return pcClientList;
	}

}

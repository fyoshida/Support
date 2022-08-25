package repository.list;

import java.util.List;

import model.Pc;
import repository.RepositoryInterface;

public class ListRepository implements RepositoryInterface{
	final private List<Pc> pcClientList;

	public ListRepository(List<Pc> pcClientList) {
		this.pcClientList=pcClientList;
	}

	@Override
	public List<Pc> getPcList() {
		return pcClientList;
	}

}

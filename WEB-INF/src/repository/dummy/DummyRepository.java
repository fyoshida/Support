package repository.dummy;

import java.util.List;

import model.Pc;
import repository.RepositoryInterface;

public class DummyRepository implements RepositoryInterface{
	final private List<Pc> pcList;

	public DummyRepository(List<Pc> pcList) {
		this.pcList=pcList;
	}

	@Override
	public List<Pc> getPcList() {
		return pcList;
	}

	public void updatePc(Pc pc) {
		// TODO 自動生成されたメソッド・スタブ

	}
}

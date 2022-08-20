package repository;

import java.util.List;

import model.Pc;

public interface RepositoryInterface {
	public List<Pc> getPcList();
	public void updatePc(Pc pc);
}

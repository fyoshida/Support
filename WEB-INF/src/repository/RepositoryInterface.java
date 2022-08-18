package repository;

import java.util.List;

import beans.Pc;

public interface RepositoryInterface {
	public List<Pc> getPcList();
	public void updatePc(Pc pc);
}

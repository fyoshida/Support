package repository;

import java.util.List;

import model.Pc;

public interface RepositoryInterface {
	public List<Pc> getPcList() throws Exception;
}

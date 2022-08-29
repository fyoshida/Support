package repository;

import java.util.List;

import model.Pc;

public interface IRepository {
	public List<Pc> getPcList() throws Exception;
}

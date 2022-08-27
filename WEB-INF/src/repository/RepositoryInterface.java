package repository;

import java.util.List;

import model.PcBean;

public interface RepositoryInterface {
	public List<PcBean> getPcList() throws Exception;
}

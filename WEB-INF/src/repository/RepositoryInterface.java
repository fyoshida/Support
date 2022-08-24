package repository;

import java.time.LocalDateTime;
import java.util.List;

import model.HelpStatus;
import model.Pc;

public interface RepositoryInterface {
	public List<Pc> getPcList();
}

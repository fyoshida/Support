package repository;

import org.junit.Ignore;

import repository.db.DataBaseRepository;

@Ignore
public class DataBaseRepositotyTest extends RepositoryTest{

	@Override
	public void initializeRepository() {
		RepositoryFactory.repositoryType=RepositoryType.DataBase;
		repository=RepositoryFactory.getRepository();

	}

}
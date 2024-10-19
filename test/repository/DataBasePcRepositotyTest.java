package repository;

import org.junit.Ignore;

import repository.db.DataBasePcRepository;

@Ignore
public class DataBasePcRepositotyTest extends RepositoryTest{

	@Override
	public void initializeRepository() {
		RepositoryFactory.repositoryType=RepositoryType.DataBase;
		repository=RepositoryFactory.getRepository();

	}

}
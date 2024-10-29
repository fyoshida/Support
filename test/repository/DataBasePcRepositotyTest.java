package repository;

import org.junit.Ignore;

@Ignore
public class DataBasePcRepositotyTest extends RepositoryTest{

	@Override
	public void initializeRepository() {
		repository=RepositoryFactory.getRepository(RepositoryType.DataBase);

	}

}
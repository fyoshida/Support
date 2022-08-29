package repository;

import repository.dummy.DummyRepository;

public class DummyRepositotyTest extends RepositoryTest {

	@Override
	public void initializeRepository() {
		RepositoryFactory.repositoryType = RepositoryType.Dummy;
		repository = RepositoryFactory.getRepository();
	}

}
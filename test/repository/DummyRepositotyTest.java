package repository;

import repository.dummy.DummyRepository;

public class DummyRepositotyTest extends RepositoryTest{

	@Override
	public void initializeRepository() {
		repository = new DummyRepository();
	}

}
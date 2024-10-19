package repository;

public class MemoryPcRepositotyTest extends RepositoryTest {

	@Override
	public void initializeRepository() {
		RepositoryFactory.repositoryType = RepositoryType.Memory;
		repository = RepositoryFactory.getRepository();
	}

}
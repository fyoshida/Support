package repository;

public class MemoryPcRepositotyTest extends RepositoryTest {

	@Override
	public void initializeRepository() {
		repository = RepositoryFactory.getRepository(RepositoryType.Memory);
	}

}
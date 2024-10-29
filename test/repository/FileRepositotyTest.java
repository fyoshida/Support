package repository;

public class FileRepositotyTest extends RepositoryTest {

	@Override
	public void initializeRepository() {
		repository = RepositoryFactory.getRepository(RepositoryType.File);
	}

}
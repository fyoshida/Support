package repository;

import repository.file.FileRepository;

public class FileRepositotyTest extends RepositoryTest {

	@Override
	public void initializeRepository() {
		RepositoryFactory.repositoryType = RepositoryType.File;
		repository = RepositoryFactory.getRepository();
	}

}
package repository;

import repository.file.FilePcRepository;

public class FileRepositotyTest extends RepositoryTest {

	@Override
	public void initializeRepository() {
		RepositoryFactory.repositoryType = RepositoryType.File;
		repository = RepositoryFactory.getRepository();
	}

}
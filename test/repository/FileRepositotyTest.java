package repository;

import repository.file.FileRepository;

public class FileRepositotyTest extends RepositoryTest{

	@Override
	public void initializeRepository() {
		repository = new FileRepository("./WEB-INF/data/pcIdTable.csv");
	}

}
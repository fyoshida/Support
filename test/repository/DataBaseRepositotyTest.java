package repository;

import org.junit.Ignore;

import repository.db.DataBaseRepository;

@Ignore
public class DataBaseRepositotyTest extends RepositoryTest{

	private final String DataBaseName="0000000";
	private final String UserName="00000";
	private final String PassWord="0000000";

	@Override
	public void initializeRepository() {
		repository = new DataBaseRepository(DataBaseName,UserName,PassWord);
	}

}
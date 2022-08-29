package repository;

import repository.db.DataBaseRepository;
import repository.dummy.DummyRepository;
import repository.file.FileRepository;

public class RepositoryFactory {
	public static String fileName = "./WEB-INF/data/pcIdTable.csv";

	public static String dataBaseName = "000000";
	public static String userName = "000000";
	public static String passWord = "00000000";

	public static RepositoryType repositoryType = RepositoryType.Dummy;

	public static IRepository getRepository() {
		switch (repositoryType) {
		case DataBase:
			return new DataBaseRepository(dataBaseName, userName, passWord);
		case File:
			return new FileRepository(fileName);
		default:
			return new DummyRepository();
		}
	}
}

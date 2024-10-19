package repository;

import repository.db.DataBasePcRepository;
import repository.file.FilePcRepository;
import repository.memory.MemoryPcRepository;

public class RepositoryFactory {
	public static String fileName = "./WEB-INF/data/pcIdTable.csv";

	public static String dataBaseName = "000000";
	public static String userName = "000000";
	public static String passWord = "00000000";

	public static RepositoryType repositoryType = RepositoryType.Memory;

	public static IPcRepository getRepository() {
		switch (repositoryType) {
		case DataBase:
			return new DataBasePcRepository(dataBaseName, userName, passWord);
		case File:
			return new FilePcRepository(fileName);
		default:
			return new MemoryPcRepository();
		}
	}
}

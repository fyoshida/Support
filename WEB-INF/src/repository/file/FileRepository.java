package repository.file;

import java.util.List;

import model.Pc;
import repository.IRepository;

public class FileRepository implements IRepository {

	private String fileName;

	public FileRepository(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public List<Pc> getPcList() throws Exception {
		FileReader fileReader = new FileReader();
		return fileReader.getPcListFromFile(fileName);
	}

}

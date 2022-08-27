package repository.file;

import java.util.List;

import model.PcBean;
import repository.RepositoryInterface;

public class FileRepository implements RepositoryInterface {
	private String FILE_NAME;

	public FileRepository(String fileName) {
		this.FILE_NAME = fileName;
	}

	@Override
	public List<PcBean> getPcList() throws Exception {
		FileReader fileReader = new FileReader();
		return fileReader.getPcListFromFile(FILE_NAME);
	}

}

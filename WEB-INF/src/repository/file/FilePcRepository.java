package repository.file;

import java.util.List;

import domain.entities.Pc;
import repository.IPcRepository;

public class FilePcRepository implements IPcRepository {

	private String fileName;

	public FilePcRepository(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public List<Pc> getPcList() {
		FileReader fileReader = new FileReader();
		try {
			return fileReader.getPcListFromFile(fileName);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return null;
	}

}

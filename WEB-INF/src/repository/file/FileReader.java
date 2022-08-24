package repository.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import model.HelpStatus;
import model.Pc;
import model.PcId;

public class FileReader {

	public List<Pc> getPcListFromFile(String fileName) {
		List<String> lines=getLinesFromFile(fileName);
		return getPcListFromLines(lines);
	}

	// ファイルから全行を読み込む
	List<String> getLinesFromFile(String fileName) {
		List<String> lines=new LinkedList<String>();

		// テキストファイルのパス
		Path file = Paths.get(fileName);
		try {
			lines = Files.readAllLines(file);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}

		return lines;
	}

	// 全行分の文字列からPcListを生成
	List<Pc> getPcListFromLines(List<String> lines) {
		List<Pc> pcList = new LinkedList<Pc>();

		for (String line : lines) {
			Pc pc = getPcInfoFromLine(line);
			pcList.add(pc);
		}

		return pcList;
	}

	// 1行分の文字列からPcを生成
	Pc getPcInfoFromLine(String line) {
		//カンマで分割した内容を配列に格納する
		String[] lineData = line.split(",");

		//読み込んだ行をPcクラスに格納
		Pc pcClient = new Pc();
		PcId pcId = new PcId(lineData[0],lineData[1]);
		pcClient.setPcId(pcId);
		pcClient.setStudent(Boolean.valueOf(lineData[2]));
		return pcClient;
	}
}

package repository.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import model.HelpStatus;
import model.PcClient;
import model.PcId;

public class FileRepository {
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
	List<PcClient> getPcListFromLines(List<String> lines) {
		List<PcClient> pcList = new LinkedList<PcClient>();

		for (String line : lines) {
			PcClient pc = getPcInfoFromLine(line);
			pcList.add(pc);
		}

		return pcList;
	}

	// 1行分の文字列からPcを生成
	PcClient getPcInfoFromLine(String line) {
		//カンマで分割した内容を配列に格納する
		String[] lineData = line.split(",");

		//読み込んだ行をPcクラスに格納
		PcClient pcClient = new PcClient();
		PcId pcId = new PcId(lineData[0],lineData[1]);
		pcClient.setPcId(pcId);
		pcClient.setStudent(Boolean.valueOf(lineData[2]));
		return pcClient;
	}
}

package repository.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import beans.HelpStatus;
import beans.Pc;

public class FileReader {
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

	List<Pc> getPcListFromLines(List<String> lines) {
		List<Pc> pcList = new LinkedList<Pc>();

		for (String line : lines) {
			Pc pc = getPcInfoFromLine(line);
			pcList.add(pc);
		}

		return pcList;
	}

	Pc getPcInfoFromLine(String line) {
		//カンマで分割した内容を配列に格納する
		String[] lineData = line.split(",");

		//読み込んだ行をPcクラスに格納
		Pc pcData = new Pc();
		pcData.setPcId(lineData[0]);
		pcData.setIpAdress(lineData[1]);
		pcData.setIsStudent(Boolean.valueOf(lineData[2]));
		pcData.setIsLogin(false);
		pcData.setHelpStatus(HelpStatus.None);
		pcData.setLastRequestTime(null);
		pcData.setLastHandTime(null);
		return pcData;
	}
}

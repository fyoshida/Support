package repository.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import domain.entities.Pc;
import domain.valueobjects.IpAddress;

public class FileReader {

	public List<Pc> getPcListFromFile(String fileName) throws Exception {
		List<String> lines = getLinesFromFile(fileName);
		return getPcListFromLines(lines);
	}

	// ファイルから全行を読み込む
	List<String> getLinesFromFile(String fileName) {
		List<String> lines = new LinkedList<String>();

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
	List<Pc> getPcListFromLines(List<String> lines) throws Exception {
		List<Pc> pcList = new LinkedList<Pc>();

		for (String line : lines) {
			try {
				Pc pc = getPcInfoFromLine(line);
				pcList.add(pc);
			} catch (Exception e) {
			}
		}

		return pcList;
	}

	// 1行分の文字列からPcを生成
	Pc getPcInfoFromLine(String line) throws Exception {
		//カンマで分割した内容を配列に格納する
		String[] lineData = line.split(",");
		String hostName = lineData[0];
		IpAddress ipAddress = new IpAddress(lineData[1]);

		//読み込んだ行をPcクラスに格納
		return new Pc(ipAddress, hostName);
	}
}

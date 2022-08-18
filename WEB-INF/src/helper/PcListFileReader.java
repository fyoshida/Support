package helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import beans.HelpStatus;
import beans.Pc;

public class PcListFileReader {
	public List<Pc> getPcListFromFile(String fileName){
		List<String> lines = getLinesFromFile(fileName);

		//先頭行は列名なので除外
		lines.remove(0);

		return getPcListFromLines(lines);
	}

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

//	private BufferedReader FileReader(String filePath, ServletContextEvent arg0) {
//	String realPath = arg0.getServletContext().getRealPath(filePath);
//	FileInputStream fi = null;
//	try {
//		fi = new FileInputStream(realPath);
//	} catch (FileNotFoundException e) {
//		// TODO 自動生成された catch ブロック
//		e.printStackTrace();
//	}
//	InputStreamReader is = new InputStreamReader(fi);
//	BufferedReader br = new BufferedReader(is);
//	return br;
//}
}

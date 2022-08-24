package repository.db;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import model.HelpStatus;
import model.HelpStatusHelper;
import model.Pc;
import model.PcId;
import repository.RepositoryInterface;

public class DataBaseRepository extends DataBaseManager implements RepositoryInterface{

	public Pc copyRecord(ResultSet rs) throws Exception{

		// ------PC 情報------
		String hostName = rs.getString("HostName");
		String ipAddress = rs.getString("IpAddress");
		boolean isStudent = rs.getBoolean("Student");

		// ------HelpStatus------
		String helpStatusString = rs.getNString("HelpStatus");
		HelpStatus helpStatus = HelpStatusHelper.fromString(helpStatusString);

		// ------HandUpTime------
		Date date = rs.getDate("HandUpTime");
		LocalDateTime handUpTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

		// ------PCオブジェクトに代入------
		PcId pcId= new PcId(hostName,ipAddress);
		Pc pc= new Pc();
		pc.setPcId(pcId);
		pc.setStudent(isStudent);
		pc.setHelpStatus(helpStatus);
		pc.setHandUpTime(handUpTime);

		return pc;
	}

	@Override
	public List<Pc> getPcList() {
		return (List<Pc>)getRecords("select * from pc");
	}
}

package repository.db;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import model.HelpStatus;
import model.IpAddress;
import model.Pc;
import repository.RepositoryInterface;
import servlet.helper.HelpStatusConverter;

public class DataBaseRepository extends DataBaseManager implements RepositoryInterface{

	public Pc copyRecord(ResultSet rs) throws Exception{

		// ------PC 情報------
		String hostName = rs.getString("HostName");
		String ipAddressString = rs.getString("IpAddress");
		boolean isStudent = rs.getBoolean("IsStudent");

		// ------IpAddress------
		IpAddress ipAddress =new IpAddress(ipAddressString);

		// ------HelpStatus------
		String helpStatusString = rs.getNString("HelpStatus");
		HelpStatus helpStatus = HelpStatusConverter.getHelpStatus(helpStatusString);

		// ------HandUpTime------
		Date date = rs.getDate("HandUpTime");
		LocalDateTime handUpTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

		// ------PCオブジェクトに代入------
		Pc pc= new Pc();
		pc.setIpAddress(ipAddress);
		pc.setHostName(hostName);
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

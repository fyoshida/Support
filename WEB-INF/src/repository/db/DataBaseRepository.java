package repository.db;

import java.sql.ResultSet;
import java.util.List;

import model.IpAddress;
import model.PcBean;
import repository.RepositoryInterface;

public class DataBaseRepository extends DataBaseManager implements RepositoryInterface{

	public PcBean copyRecord(ResultSet rs) throws Exception{

		// ------PC 情報------
		String hostName = rs.getString("HostName");
		String ipAddressString = rs.getString("IpAddress");
		boolean isStudent = rs.getBoolean("IsStudent");

		// ------IpAddress------
		IpAddress ipAddress =new IpAddress(ipAddressString);

//		// ------HelpStatus------
//		String helpStatusString = rs.getNString("HelpStatus");
//		HelpStatus helpStatus = HelpStatusConverter.getHelpStatus(helpStatusString);
//
//		// ------HandUpTime------
//		Date date = rs.getDate("HandUpTime");
//		LocalDateTime handUpTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

		// ------PCオブジェクトに代入------
		PcBean pc= new PcBean();
		pc.setIpAddress(ipAddress);
		pc.setHostName(hostName);
		pc.setStudent(isStudent);
//		pc.setHelpStatus(helpStatus);
//		pc.setHandUpTime(handUpTime);

		return pc;
	}

	@Override
	public List<PcBean> getPcList() {
		return (List<PcBean>)getRecords("select * from pc");
	}
}

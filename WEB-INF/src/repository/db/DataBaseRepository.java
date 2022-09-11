package repository.db;

import java.sql.ResultSet;
import java.util.List;

import _old.model.IpAddress;
import model.Pc;
import repository.IRepository;

public class DataBaseRepository extends DataBaseManager implements IRepository{

	public DataBaseRepository(String dataBaseName,String userName,String passWord) {
		super(dataBaseName,userName,passWord);
	}

	public Pc copyRecord(ResultSet rs) throws Exception{

		// ------PC 情報------
		String hostName = rs.getString("HostName");
		String ipAddress = rs.getString("IpAddress");
		boolean isStudent = rs.getBoolean("IsStudent");

		// ------PCオブジェクトに代入------
		return new Pc(ipAddress,hostName,isStudent);
	}

	@Override
	public List<Pc> getPcList() {
		return (List<Pc>)getRecords("select * from pc");
	}
}

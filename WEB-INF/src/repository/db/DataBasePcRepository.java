package repository.db;

import java.sql.ResultSet;
import java.util.List;

import domain.entities.Pc;
import domain.valueobjects.IpAddress;
import repository.IPcRepository;

public class DataBasePcRepository extends DataBaseManager implements IPcRepository{

	public DataBasePcRepository(String dataBaseName,String userName,String passWord) {
		super(dataBaseName,userName,passWord);
	}

	public Pc copyRecord(ResultSet rs) throws Exception{

		// ------PC 情報------
		String hostName = rs.getString("HostName");
		String strIpAddress = rs.getString("IpAddress");

		// ------IpAddress------
		IpAddress ipAddress = new IpAddress(strIpAddress);

		// ------PCオブジェクトに代入------
		Pc pc= new Pc(ipAddress,hostName);

		return pc;
	}

	@Override
	public List<Pc> getPcList() {
		return (List<Pc>)getRecords("select * from pc");
	}
}

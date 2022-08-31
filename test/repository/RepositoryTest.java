package repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import _test_data.Cnst;
import model.IpAddress;
import model.Pc;

abstract public class RepositoryTest {


	protected IpAddress ipAddressGateWay;
	protected IpAddress ipAddressNotRegisted1;
	protected IpAddress ipAddressNotRegisted2;
	protected IpAddress ipAddressRegisted1;
	protected IpAddress ipAddressRegisted2;

	protected String hostNameGateWay="icsGateWay";

	protected IRepository repository;

	@Before
	public void setUp() {
		ipAddressGateWay = new IpAddress(Cnst.IPADDRESS_GATEWAY);
		ipAddressNotRegisted1 = new IpAddress(Cnst.IPADDRESS_NOTREGISTED_1);
		ipAddressNotRegisted2 = new IpAddress(Cnst.IPADDRESS_NOTREGISTED_2);
		ipAddressRegisted1 = new IpAddress(Cnst.IPADDRESS_1);
		ipAddressRegisted2 = new IpAddress(Cnst.IPADDRESS_2);

		initializeRepository();
	}

	abstract public void initializeRepository();

	@Test
	public void Pc情報の総数が一致している() throws Exception {
		List<Pc> pcList = repository.getPcList();

		assertEquals(pcList.size(), 71);
	}

	@Test
	public void 登録されていないPcは取得できない() throws Exception {
		List<Pc> pcList = repository.getPcList();

		assertNull(getPc(pcList,ipAddressGateWay));
		assertNull(getPc(pcList,ipAddressNotRegisted1));
		assertNull(getPc(pcList,ipAddressNotRegisted2));
	}

	@Test
	public void 登録されているPcは取得できる() throws Exception {
		List<Pc> pcList = repository.getPcList();

		Pc pc1 = getPc(pcList,ipAddressRegisted1);
		assertTrue(pc1.getIpAddress().equals(ipAddressRegisted1));
		assertEquals(pc1.getHostName(),Cnst.HOSTNAME_1);

		Pc pc2 = getPc(pcList,ipAddressRegisted2);
		assertTrue(pc2.getIpAddress().equals(ipAddressRegisted2));
		assertEquals(pc2.getHostName(),Cnst.HOSTNAME_2);
	}

	protected Pc getPc(List<Pc> pcList,IpAddress ipAddress) {
		for(Pc pc: pcList) {
			if(pc.getIpAddress().equals(ipAddress)) {
				return pc;
			}
		}
		return null;
	}

	protected Pc getPc(List<Pc> pcList,String hostName) {
		for(Pc pc: pcList) {
			if(pc.getHostName().equals(hostName)) {
				return pc;
			}
		}
		return null;
	}
}

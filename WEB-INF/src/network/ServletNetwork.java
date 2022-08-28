package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import model.Student;

public class ServletNetwork implements NetworkInterface {
	private HttpServletRequest req;

	public ServletNetwork(HttpServletRequest req) {
		this.req = req;
	}

	@Override
	public String getClientIpAddress() {
		String clientIpAddress = req.getRemoteAddr();

		if (clientIpAddress.equals("0:0:0:0:0:0:0:1")) {
			try {
				InetAddress inetAddress = InetAddress.getLocalHost();
				;
				clientIpAddress = inetAddress.getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.out.println(e);
			}

		}

		return clientIpAddress;
	}

	@Override
	public String getClientHostName() {
		String url = req.getRequestURI();

		int beginIdx = url.lastIndexOf("/"); //icsのsの位置を取得
		String clientId = url.substring(beginIdx + 1); //実際は番号だけ知りたいので+1する

		return clientId;
	}
}

package httpclient;

import javax.servlet.http.HttpServletRequest;

import domain.valueobjects.IpAddress;

public class ServletHttpClient implements IHttpClient {
	private HttpServletRequest req;

	public ServletHttpClient(HttpServletRequest req) {
		this.req = req;
	}

	private String getIpAddressFromHeader() {
		   String ipAddress = req.getHeader("X-Forwarded-For");
		    if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
		        ipAddress = req.getHeader("Proxy-Client-IP");
		    }
		    if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
		        ipAddress = req.getHeader("WL-Proxy-Client-IP");
		    }
		    if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
		        ipAddress = req.getRemoteAddr();
		    }

		    // 複数のIPが「X-Forwarded-For」に含まれている場合は、最初のIPを取得
		    if (ipAddress != null && ipAddress.contains(",")) {
		        ipAddress = ipAddress.split(",")[0].trim();
		    }

		    return ipAddress;
	}
	@Override
	public IpAddress getClientIpAddress() {
		String clientIpAddress = req.getRemoteAddr();

		if (clientIpAddress.equals("0:0:0:0:0:0:0:1")) {
			String ipAddress = getIpAddressFromHeader();
			return new IpAddress(ipAddress);
		}
		
		return new IpAddress(clientIpAddress);
	}

}

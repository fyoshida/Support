package httpclient;

import java.net.InetAddress;
import java.util.Optional;

public interface IHttpClient {

	public Optional<InetAddress> getClientIpAddress();

	public String getClientHostName();
}

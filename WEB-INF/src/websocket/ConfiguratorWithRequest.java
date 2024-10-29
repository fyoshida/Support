package websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

public class ConfiguratorWithRequest extends Configurator {
	@Override
	public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {

		HttpSession httpSession = (HttpSession) request.getHttpSession();

		if (httpSession.getAttribute("IpAddress") != null) {
			String strIpAddress = (String) httpSession.getAttribute("IpAddress");
			config.getUserProperties().put("IpAddress", strIpAddress);
		}
		
		if (httpSession.getAttribute("UserType") != null) {
			String userType = (String) httpSession.getAttribute("UserType");
			config.getUserProperties().put("UserType", userType);
		}

	}
}

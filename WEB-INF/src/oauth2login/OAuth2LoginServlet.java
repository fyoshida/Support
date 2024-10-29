package oauth2login;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

@WebServlet("/oauth2login")
public class OAuth2LoginServlet extends HttpServlet {
    private static final String CLIENT_SECRET_FILE = "/WEB-INF/client_secret.json";
    private static final String REDIRECT_URI = "http://dashboard.nagaokaut.ac.jp/Support/oauth2callback";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(getServletContext().getResourceAsStream(CLIENT_SECRET_FILE)));

        GoogleAuthorizationCodeFlow flow;
		try {
			flow = new GoogleAuthorizationCodeFlow.Builder(
			        GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets,
			        Collections.singleton("https://www.googleapis.com/auth/userinfo.email"))
			        .setAccessType("offline")
			        .build();
	        String authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
	        resp.sendRedirect(authorizationUrl); // Googleの認証画面にリダイレクト
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
		}
    }
}


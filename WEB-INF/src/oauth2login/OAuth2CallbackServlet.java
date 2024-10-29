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
import javax.servlet.http.HttpSession;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;

@WebServlet("/oauth2callback")
public class OAuth2CallbackServlet extends HttpServlet {
    private static final String CLIENT_SECRET_FILE = "/WEB-INF/client_secret.json";
    private static final String REDIRECT_URI = "http://dashboard.nagaokaut.ac.jp/Support/oauth2callback";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(getServletContext().getResourceAsStream(CLIENT_SECRET_FILE)));

        AuthorizationCodeFlow flow;
		try {
			// アクセストークンを取得
			flow = new GoogleAuthorizationCodeFlow.Builder(
			        GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets,
			        Collections.singleton("https://www.googleapis.com/auth/userinfo.email"))
			        .setAccessType("offline")
			        .build();
	        String code = req.getParameter("code");
	        TokenResponse credential = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
	        String accessToken = credential.getAccessToken();
	        
	        // ユーザ情報（email）を取得
	        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
            GenericUrl url = new GenericUrl("https://www.googleapis.com/oauth2/v3/userinfo");
            HttpRequest request = requestFactory.buildGetRequest(url);
            request.getHeaders().setAuthorization("Bearer " + accessToken);
            HttpResponse response = request.execute();

            // JSONからemailを抽出
			Gson gson = new Gson();
            String jsonIdentity = response.parseAsString();
			JsonResponse jsonResponse = gson.fromJson(jsonIdentity, JsonResponse.class);
            String email = jsonResponse.getEmail();

            // User情報をSessionに保存
            HttpSession session = req.getSession();
            switch(email) {
            case "fyoshida@vos.nagaokaut.ac.jp":
                session.setAttribute("UserType", "Teacher");
            	break;
            case "ando@kjs.nagaokaut.ac.jp":
                session.setAttribute("UserType", "Teacher");
            	break;
            case "s213340@stn.nagaokaut.ac.jp":
                session.setAttribute("UserType", "TA");
            	break;
            case "s201022@stn.nagaokaut.ac.jp":
                session.setAttribute("UserType", "TA");
            	break;
           	default:
            }
            
            req.getRequestDispatcher("/index_ta.html").forward(req, resp);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    

    
}

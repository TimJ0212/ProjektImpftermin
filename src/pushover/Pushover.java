package pushover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;

public class Pushover {

	/**
	 * App-Token
	 */
	private String appToken;

	/**
	 * User-Token
	 */
	private String userToken;

	/**
	 * Initialisiert ein Pushover Objekt
	 *
	 * @param appToken  
	 * @param userToken
	 */
	public Pushover(String appToken, String userToken) {
		this.appToken = appToken;
		this.userToken = userToken;
	}
	
	/**
	 * Sendet eine Nachricht
	 * @param Nachricht
	 */
	public String sendMessage(String message) throws UnsupportedEncodingException, IOException {
		return sendToPushoverRaw(getAuthenticationTokens() + "&message=" + URLEncoder.encode(message, "UTF-8"));
	}

	/**
	 * Liefert den App-Token
	 */
	private String getAppToken() {
		return appToken;
	}

	/**
	 * Liefert den User-Token
	 */
	private String getUserToken() {
		return userToken;
	}

	/**
	 * Liefert einen String der ertstellten Token
	 */
	private String getAuthenticationTokens() throws UnsupportedEncodingException {

		return "token=" + getAppToken() + "&user=" + getUserToken();
	}

	/**
	 * Sendet eine rohe Nachricht (ohne Tokens) an Pushover via POST
	 */
	private String sendToPushoverRaw(String rawMessage) throws IOException {
		URL pushoverUrl = new URL("https://api.pushover.net/1/messages.json");

		HttpsURLConnection connection = (HttpsURLConnection) pushoverUrl.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);

		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(rawMessage.getBytes(Charset.forName("UTF-8")));
		outputStream.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		String output = "";
		String outputCache = "";
		while ((outputCache = br.readLine()) != null) {
			output += outputCache;
		}
		br.close();
		return output;
	}

}

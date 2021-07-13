package pushover;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Pushover {

	/**
	 * App-Token
	 */
	private final String appToken;

	/**
	 * User-Token
	 */
	private final String userKey;

	/**
	 * Initialisiert ein Pushover Objekt
	 *
	 * @param appToken Der App Token von Pushover
	 * @param userKey  Der USer Key von Pushover
	 */
	public Pushover(String appToken, String userKey) {
		this.appToken = appToken;
		this.userKey = userKey;
	}

	/**
	 * Sendet eine Nachricht
	 *
	 * @param message Versendete Nachricht
	 */
	public String sendMessage(String message) throws IOException {
		return sendToPushoverRaw(
				getAuthenticationTokens() +
						"&message=" +
						URLEncoder.encode(message, StandardCharsets.UTF_8));
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
		return userKey;
	}

	/**
	 * Liefert einen String der ertstellten Token
	 */
	private String getAuthenticationTokens() {

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
		outputStream.write(rawMessage.getBytes(StandardCharsets.UTF_8));
		outputStream.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		StringBuilder output = new StringBuilder();
		String outputCache;
		while ((outputCache = br.readLine()) != null) output.append(outputCache);
		br.close();
		return output.toString();
	}

}

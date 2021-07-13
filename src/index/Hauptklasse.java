package index;

import pushover.Pushover;

import java.util.Timer;
import java.util.TimerTask;

public class Hauptklasse extends TimerTask {
	static Timer timer = new Timer();

	//Muss manuell eingetragen werden.
	static String APP_TOKEN = "APP_TOKEN";
	static String USER_KEY = "USER_KEY";

	public static void main(String[] args) {
		timer.schedule(new Hauptklasse(), 0, 30000);

	}

	@Override
	public void run() {

		try {
			var s = http.req.getImpfPortal();

			var pusher = new Pushover(APP_TOKEN, USER_KEY);

			if (!s.contains("outOfStock\":true")) {
				timer.cancel();
				pusher.sendMessage("https://www.impfportal-niedersachsen.de/portal/#/appointment/public");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
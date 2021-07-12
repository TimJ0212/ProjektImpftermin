package index;

import java.util.Timer;
import java.util.TimerTask;

import pushover.Pushover;

public class Hauptklasse extends TimerTask {
	static Timer timer = new Timer();
	
	//Muss manuell eingetragen werden.
	static String APP_TOKEN = "APP_TOKEN";
	static String USER_TOKEN = "USER_TOKEN";

	public static void main(String[] args) {
		timer.schedule(new Hauptklasse(), 0, 30000);

	}

	@Override
	public void run() {

		try {
			var s = http.req.getImpfPortal();

			var pusher = new Pushover("acod9n6z2wjw8e5aiwizt6ynwwcahq", s);

			if (s.indexOf("outOfStock\":true") == -1) {
				timer.cancel();
				pusher.sendMessage("https://www.impfportal-niedersachsen.de/portal/#/appointment/public");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
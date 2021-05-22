package http;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class req {

	public static String getImpfPortal() {
		String op = null;

		try {
			Writer fw = new FileWriter("/Users/timjungk/eclipse-workspace/ProjektImpfen/log.txt", true);
			String uri = ("https://www.impfportal-niedersachsen.de/portal/rest/appointments/findVaccinationCenterListFree/30880?stiko=&count=1&birthdate=1007247600000");

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			op = response.body();

			fw.write("Impfzentrum: " + op + System.currentTimeMillis() + "\n");
			fw.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return op;

	}

	public static String getImpfFrauen() throws Exception {
		String uri = ("https://www.doctolib.de/availabilities.json?start_date=2021-05-22&visit_motive_ids=2738611&agenda_ids=448104&insurance_sector=public&practice_ids=122091&destroy_temporary=true&limit=4");

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		String op = response.body();

		Writer fw = new FileWriter("/Users/timjungk/eclipse-workspace/ProjektImpfen/log.txt", true);
		fw.write("Frauenarzt: " + op + System.currentTimeMillis() + "\n");
		fw.close();
		return op;
	}
}
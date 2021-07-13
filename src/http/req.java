package http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class req {

    public static String getImpfPortal() throws Exception {
        StringBuilder response = new StringBuilder();

        URL url = new URL("https://www.impfportal-niedersachsen.de/portal/rest/appointments/findVaccinationCenterListFree/30880?stiko=&count=1&birthdate=1007247600000");

        HttpURLConnection client = (HttpURLConnection) url.openConnection();
        client.setRequestMethod("GET");
        int responseCode = client.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        }

        return response.toString();

    }
}
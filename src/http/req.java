package http;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class req {
		public static String get() throws Exception {
			
			String uri = ("https://www.impfportal-niedersachsen.de/portal/rest/appointments/findVaccinationCenterListFree/30880?stiko=&count=1&birthdate=1007247600000");
		    HttpClient client = HttpClient.newHttpClient();
		    HttpRequest request = HttpRequest.newBuilder()
		          .uri(URI.create(uri))
		          .build();

		    HttpResponse<String> response =
		          client.send(request, BodyHandlers.ofString());

		    String op = response.body();
		    if(!op.equals("{\"resultList\":[{\"vaccinationCenterPk\":915745288482899,\"name\":\"Impfzentrum Hannover 1\",\"streetName\":\"Messegelände\",\"streetNumber\":\"25\",\"zipcode\":\"30521\",\"city\":\"Hannover\",\"scheduleSaturday\":true,\"scheduleSunday\":true,\"vaccinationCenterType\":0,\"vaccineName\":\"BioNtech\",\"vaccineType\":\"mRNA\",\"interval1to2\":42,\"distance\":10,\"outOfStock\":true,\"publicAppointment\":true}],\"succeeded\":true}")){
		    	System.out.println(op);
		    }
		    return op;
		}
	}
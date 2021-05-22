package smspack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Personal {
	// Find your Account Sid and Auth Token at twilio.com/console
	public static final String ACCOUNT_SID = "AC8f21b132f67860be47d4640e0021128b";
	public static final String AUTH_TOKEN = "6c2051dac25380d34f8c884e06e895ea";

	public static void sendSms(String source) {

		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		var dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		var now = LocalDateTime.now();

		var message = Message.creator(new PhoneNumber("+491723451833"), // to
				new PhoneNumber("+12678438364"), // from
				source + dtf.format(now)).create();

		System.out.println(message.getSid());
	}
}
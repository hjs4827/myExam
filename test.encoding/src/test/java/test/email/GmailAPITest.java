package test.email;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

import com.google.api.services.gmail.model.Message;

public class GmailAPITest {

	@Test
	public void gmailTest() throws MessagingException, IOException {
		GmailAPI api = new GmailAPI();
		String to = "hjs2102@gmail.com";
		String from = "test@gmail.com";
		String subject = "test email";
		String bodyText = "<div>test body</div>";
		
		MimeMessage email = api.createEmail(to, from, subject, bodyText);
		Message message = api.createMessageWithEmail(email);
		api.sendMessage(service, userId, email);
		
	}

}

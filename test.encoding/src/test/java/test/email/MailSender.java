package test.email;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	public static void send(List<String> toMail, String fromMail, String message, String title)
			throws Exception {

		Properties p = new Properties();
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.port", "587");

		Authenticator auth = new SMTPAuthenticator();

		Session session = Session.getInstance(p, auth);

		session.setDebug(true); // 메일을 전송할 때 상세한 상황을 콘솔에 출력한다.

		MimeMessage msg = new MimeMessage(session);

		// 편지 보낸시간
		msg.setSentDate(new Date());
		msg.setSubject(title, "UTF-8");
		msg.setText(message, "UTF-8");

		// set from
		Address fromAddr = new InternetAddress(fromMail); // 보내는 사람의 메일주소
		msg.setFrom(fromAddr);

		// set to
		InternetAddress[] addressTo = new InternetAddress[toMail.size()];
		for (int i = 0; i < toMail.size(); i++) {
			addressTo[i] = new InternetAddress(toMail.get(i));

		}

		msg.setRecipients(Message.RecipientType.TO, addressTo);
		// 내용
		//msg.setContent(message, "text/html;charset=utf-8");

		Transport.send(msg);
	}

	private static class SMTPAuthenticator extends javax.mail.Authenticator {
		PasswordAuthentication pa;

		SMTPAuthenticator() {
			String id = "hjs2102@gmail.com";
			String pwd = "fvcmurajxqlnjjnu";
			pa = new PasswordAuthentication(id, pwd);
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return pa;
		}

	}

	public static void main(String[] args) throws Exception {
		List<String> toMail = new ArrayList<String>();
		toMail.add("hjs4827@naver.com");
		String fromMail = "test@test.com";
		String message = "message Test";
		String title = "title test";

		send(toMail, fromMail, message, title);
	}

}
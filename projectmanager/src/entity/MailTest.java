package entity;

import javax.mail.MessagingException;

public class MailTest {
	
	public static void main(String[] args) {
		try {
			MailHelper.sendMail("takleasmalesh@gmail.com", "test", "test");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}

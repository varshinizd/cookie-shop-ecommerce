package util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailUtil {

    public static boolean sendOtp(String toEmail, String otp) {
        final String fromEmail = "varshinim223@gmail.com"; // your Gmail
        final String password = "fvmc diie wgrr aulj";       // App password, NOT account password

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP is: " + otp);

            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // You can log it better
            return false;
        }
    }
}

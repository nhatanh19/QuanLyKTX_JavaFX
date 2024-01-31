package filefxml.quanlyktx_fx_version2.API;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class ConnectGmailV2 {

    public ConnectGmailV2 () {
        final String username = "mailtestappanhdn@gmail.com"; // Thay đổi thành địa chỉ email của bạn
        final String password = "ppfmwvghfnrsywsc"; // Thay đổi thành mật khẩu của bạn

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Thay đổi theo máy chủ SMTP của bạn
        props.put("mail.smtp.port", "587"); // Thay đổi cổng của máy chủ SMTP

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            Message message = new MimeMessage(session);
            // Đặt thông tin người gửi
            message.setFrom(new InternetAddress("anhanhdeptraiiii@gmail.com"));
            // Đặt thông tin người nhận
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("nhatanh19a10@gmail.com"));
            // Đặt tiêu đề
            message.setSubject("Test Email from JavaMail");
            // Đặt nội dung
            message.setText("This is a test email from JavaMail.");

            // Gửi email
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

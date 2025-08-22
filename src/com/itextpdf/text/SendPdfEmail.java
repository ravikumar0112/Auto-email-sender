package com.itextpdf.text;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendPdfEmail {

    public static void main(String[] args) {
        final String fromEmail = "my mail id.com";
        final String password = "api password"; // App-specific password
        final String toEmail = "to receiver mail.com";

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
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject("PDF Attachment - Hello World");

            // Text part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Hi sir Im k s Ravikumar ,i did my Task-1 project i used to code in VS code im new to eclipse so i dont know how to import lib in eclipse now i learned sorry for the late .");

            // PDF attachment part
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource("hello_world.pdf");
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName("hello_world.pdf");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

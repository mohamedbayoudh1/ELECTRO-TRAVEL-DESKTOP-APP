/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.util.Properties;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage ;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;



        
       


/**
 *
 * @author Asus
 */

public class Mail {
   public static void sendMail(String recepient,String passclient) throws Exception{
        
       final String username = "nourita0248@gmail.com";
        final String password = "electro0248";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

         Session session = Session.getInstance(props,
                  new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                  });


        try {
            Transport transport=session.getTransport();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));//formBean.getString("fromEmail")
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(username));
            message.setSubject("ElectroTravel");//formBean.getString(
            message.setText("Electrotavel say Hello to you ! :".concat(passclient));
            transport.connect();
            transport.send(message, InternetAddress.parse(recepient));//(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            System.out.println("e="+e);
            e.printStackTrace();
            throw new RuntimeException(e);

        }
        }
}

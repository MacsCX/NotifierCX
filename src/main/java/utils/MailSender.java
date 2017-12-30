/*
 * Copyright (c) 2017
 *
 *
 * @author MacsCX
 */

/*
To run this class properly (send mail via SSL), you should make mail.properties file like below:
mail.smtp.host= smtp.wp.pl
mail.smtp.socketFactory.port= 465
mail.smtp.auth= true
mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory
mail.user= your_email_login
mail.user.alias= your_email_alias
mail.password= your_email_password
mail.address= your_email_address
mail.encoding= "utf8"
mail.debug= false
 */

/*
For this moment, you should make your own e-mail account for your Notifier.
I use poczta.wp.pl (SSL)
 */
package utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailSender {
    private Properties properties;
    private String login = " ", password = " ", senderAddress = " ", senderName = " ", recipientAddress = " ", recipientName = " ";
    private MimeMessage messageObject;
    private Session session;

    public MailSender(Properties properties, String recipientAddress, String recipientName)  {
        this.recipientAddress = recipientAddress;
        this.recipientName = recipientName;
        this.properties = properties;

        this.login = properties.getProperty("mail.user");
        this.password = properties.getProperty("mail.password");
        this.session = Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(login, password);
                    }
                });
    }

    /**
     * method to create MimeMessage object
     * @param mailSubject subject of e-mail
     * @param textMessage text content of e-mail
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public void setTextMessageObject(String mailSubject, String textMessage)  throws MessagingException, UnsupportedEncodingException  {
        this.messageObject = new MimeMessage(session);

        try {
            messageObject.setFrom(
                    new InternetAddress(
                            properties.getProperty("mail.address"),
                            properties.getProperty("mail.user.alias"))
            );

            messageObject.setRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(
                            recipientAddress,
                            recipientName)
            );

            messageObject.setSubject(mailSubject);
            messageObject.setText(textMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return MimeMessage object
     */
    public MimeMessage getMessageObject()   {
        return messageObject;
    }


    /**
     * methond sending a message from MailSender object
     * @throws MessagingException
     */
    public void sendMessage() throws MessagingException {
        try {
            Transport.send(this.messageObject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

package com.javaMail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "preparing to send message!" );
        String message = "Hellow dear this is message for security check";
        String subject ="security check from Ankit";
        String to = "ankitmilmile962@gmail.com";
        String from = "ankit.milmile@gmail.com"; 
        
        sendEmail(message,subject,to,from);
    }
    
    //method responsible to send email
    private static void sendEmail(String message, String subject,String to,String from) {
    	//variable for gmail
    	String host="smtp.gmail.com";
    	
    	//get system properties
    	Properties properties =  System.getProperties();
    	System.out.println("properties :"+properties);
    	
    	//setting important info in properties object
    	properties.put("mail.smtp.host", host);
    	properties.put("mail.smtp.port", "465");
    	properties.put("mail.smtp.ssl.enable", "true");
    	properties.put("mail.smtp.auth", "true");
    	
    	//step 1: to get the session object
    	Session session = Session.getInstance(properties,new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("ankit.milmile@gmail.com","nvcankit96234389");
			}
    		
		});
    	
    	session.setDebug(true);
    	
    	//step 2: compose the message [text,multi media]
    	
    	MimeMessage m =	new MimeMessage(session);
    	
    	//from email
    	
    	try {
			m.setFrom(from);
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
			//ADDING SUBJECT OT MESSAGE
			m.setSubject(subject);
			
			//adding text to message
			m.setText(message);
			
			//step 3: send the message using transport class
			
			Transport.send(m);
			
			System.out.println("message sent...........");
			
    	} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

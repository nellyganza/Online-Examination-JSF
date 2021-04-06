/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author Nishimwe Elysee
 */
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
public class EmailSender{
       
    public static void main(String args[]) throws Exception{
        sendEmail();
       
        
    }
    
    
    
    public static void sendEmail() throws Exception{
        
        
        
        final String emailId = "ganzanelly@gmail.com"; //sender's email address
        final String reciever="nishimwelys@gmail.com"; //reciever's email address
        final String password = "niyitegeka";     //provide your password here
        
        
        System.out.println("Sending Email from "+emailId+" to "+reciever);
        
        Properties pr = new Properties();
        
        pr.put("mail.smtp.auth","true");    //for username and password authentication
        pr.put("mail.smtp.starttls.enable","true");
        pr.put("mail.smtp.host","smtp.gmail.com");  //here host is gmail.com 
        pr.put("mail.smtp.port","587");             //port no.
        
        Session gs = Session.getInstance(pr, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(emailId,password);  //pass your email id and password here
         
            }
        });
        
        Message ms = messageContent(gs,emailId,reciever);
         
            System.out.println("Message sent! ");
        
    }
    
       private static Message messageContent(Session gs, String emailId, String reciever) throws Exception {
           try{
           
           Message msg = new MimeMessage(gs);
           msg.setFrom(new InternetAddress(emailId));
           msg.setRecipient(Message.RecipientType.TO,new InternetAddress(reciever));
           msg.setSubject("Codespeedy.com tutorial"); //to set the subject (not mandatory)
           msg.setText("Visit Codespeedy.com to read articles on various programming languages such as Java,php,python etc.");
           Transport.send(msg);
           return msg;
           }catch(MessagingException e)
           {
               System.out.println(e);
           }
           
           return null;
            
       }
}
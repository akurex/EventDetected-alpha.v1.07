/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sw2.ed.mail;

import com.sw2.ed.bean.BEvento;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Denise
 */
public class Mail {

    public Mail() {
    }

    public void enviacorreoActivar( String destino)
    {
        try
        {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "25");
            props.setProperty("mail.smtp.user", "innova.ku2012@gmail.com "); //aca va mi correo
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);
            
            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("yo@yo.com"));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(destino));
            message.setSubject("Activación de cuenta: Event Detected!");
            message.setText(
                "Mensaje de Prueba para la activación de la cuenta: \n");
              //+ "Usted ha solicitado el servicio de restablecimiento de contraseña. \n");
              //+ " Haga click en el siguiente enlace para efectuar el restablecimiento : "+link);

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("innova.ku2012@gmail.com ", "software2"); //usuario y contraseña
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void enviacorreoDesactivar( String destino)
    {
        try
        {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "25");
            props.setProperty("mail.smtp.user", "innova.ku2012@gmail.com "); //aca va mi correo
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);
            
            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("yo@yo.com"));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(destino));
            message.setSubject("Desactivación de cuenta: Event Detected!");
            message.setText(
                "Mensaje de Prueba para la desactivación de la cuenta: \n"
              + "Por favor contactarse con el administrador del servicio: k.dioses@gmail.com . \n");
              //+ " Haga click en el siguiente enlace para efectuar el restablecimiento : "+link);

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("innova.ku2012@gmail.com ", "software2"); //usuario y contraseña
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void enviacorreo( String destino, String link)
    {
        try
        {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "25");
            props.setProperty("mail.smtp.user", "innova.ku2012@gmail.com "); //aca va mi correo
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);
            
            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("yo@yo.com"));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(destino));
            message.setSubject("Restablecimiento de contraseña: Event Detected!");
            message.setText(
                "Mensaje de Prueba para el reestablecimiento de contraseña: \n"
              + "Usted ha solicitado el servicio de restablecimiento de contraseña. \n"
              + " Haga click en el siguiente enlace para efectuar el restablecimiento : "+link);

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("innova.ku2012@gmail.com ", "software2"); //usuario y contraseña
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    
    public void enviacorreoUsuarioNuevo( String destino, String link, String usuario)
    {
        try
        {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "25");
            props.setProperty("mail.smtp.user", "innova.ku2012@gmail.com "); //aca va mi correo
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);
            
            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("yo@yo.com"));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(destino));
            message.setSubject("[Event Detected!] Solicitud de Confirmacion");
            message.setText(
                "Bienvenido "+usuario+" a Event Detected,  \n"
              + "Se le ha creado una nueva cuenta para el servicio de Portal de Eventos de EventDetected. \n"
              + "Para poder activar su nueva cuenta necesita cambiar su contraseña. \n "
              + "Haga click en el siguiente enlace para efectuar el cambio de contraseña : "+link+"\n"
                    + "Gracias por usar nuestro servicio. \n"
                    + "Innova Ku S.A.");

            
            
            
            
            
            
            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("innova.ku2012@gmail.com ", "software2"); //usuario y contraseña
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void enviaCorreoRecordatorio( String destino, String usuario, String fecha, String evento)
    {
        try
        {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "25");
            props.setProperty("mail.smtp.user", "innova.ku2012@gmail.com "); //aca va mi correo
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);
            
            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("yo@yo.com"));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(destino));
            message.setSubject("[Event Detected!] Recordatorio para Evento");
            message.setText(
                "Estimado "+usuario+": \n"
              + "Esta es su agenda para: "+fecha+": \n"
              + "Evento: "+evento+": \n \n \n "
              
                    
                    + "Gracias por usar nuestro servicio. \n"
                    + "Innova Ku S.A.");

            
            
            
            
            
            
            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("innova.ku2012@gmail.com ", "software2"); //usuario y contraseña
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void enviarCorreoEvntCancelado(BEvento bEvento, String correo, String usuario) {
        try
        {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "25");
            props.setProperty("mail.smtp.user", "innova.ku2012@gmail.com "); //aca va mi correo
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);
            
            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("yo@yo.com"));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(correo));
            message.setSubject("SUSPENSION [Event Detected!]");
            message.setText(
                "Estimado "+usuario+": \n"
              + "-----------------------------------------------------" 
              + "Evento: "+bEvento.getNombre()+":  \n "
              + "Fecha: "+bEvento.getFecha()+": \n  "
              + "Lugar: "+bEvento.getFecha()+": \n \n \n "     
                    
                    
                    + "Gracias por usar nuestro servicio. \n"
                    + "Innova Ku S.A.");

            
            
            
            
            
            
            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("innova.ku2012@gmail.com ", "software2"); //usuario y contraseña
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

package modelo;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EnviarCorreo {

    public static void enviarPDFPorCorreo(String destinatario, String asunto, String cuerpo, String rutaPDF) {
        final String correoEmisor = "consesionaribiker85@gmail.com"; 
        final String claveCorreo = "umqq wfpy sskb caml"; 

        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");

        Session sesion = Session.getInstance(propiedades, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoEmisor, claveCorreo);
            }
        });

        try {
            File archivo = new File(rutaPDF);
            if (!archivo.exists()) {
                throw new FileNotFoundException("El archivo no existe en la ruta especificada: " + rutaPDF);
            }
            if (!archivo.canRead()) {
                throw new IOException("No se puede leer el archivo en la ruta especificada: " + rutaPDF);
            }

            MimeMessage mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(correoEmisor));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mensaje.setSubject(asunto);

            MimeBodyPart cuerpoMensaje = new MimeBodyPart();
            cuerpoMensaje.setText(cuerpo);

            MimeBodyPart adjunto = new MimeBodyPart();
            adjunto.attachFile(archivo);

            Multipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(cuerpoMensaje);
            multiparte.addBodyPart(adjunto);

            mensaje.setContent(multiparte);

            Transport.send(mensaje);
            System.out.println("Correo enviado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}

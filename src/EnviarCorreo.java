import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;

public class EnviarCorreo {

    public static void enviarPDFPorCorreo(String destinatario, String asunto, String cuerpo, String rutaPDF) {
        final String correoEmisor = "tu_correo@gmail.com"; // Cambia esto
        final String claveCorreo = "tu_contraseña"; // Cambia esto

        // Configuración del servidor SMTP
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");

        // Crear la sesión
        Session sesion = Session.getInstance(propiedades, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoEmisor, claveCorreo);
            }
        });

        try {
            // Crear el mensaje
            MimeMessage mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(correoEmisor));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mensaje.setSubject(asunto);

            // Crear el cuerpo del mensaje
            MimeBodyPart cuerpoMensaje = new MimeBodyPart();
            cuerpoMensaje.setText(cuerpo);

            // Adjuntar el archivo PDF
            MimeBodyPart adjunto = new MimeBodyPart();
            adjunto.attachFile(new File(rutaPDF));

            // Combinar el cuerpo y el archivo adjunto
            Multipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(cuerpoMensaje);
            multiparte.addBodyPart(adjunto);

            mensaje.setContent(multiparte);

            // Enviar el mensaje
            Transport.send(mensaje);
            System.out.println("Correo enviado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
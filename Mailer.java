import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mailer {
    private final String remitente = "DormAirBnb@gmail.com";
    private final String password = "D43A183F9933CDEB57BDFA863B41F50E08DE";

    public static void enviarCorreo(String destinatario, String mensaje) {
        // Datos de la cuenta de correo
        String remitente = "DormAirBnb@gmail.com";
        String password = "D43A183F9933CDEB57BDFA863B41F50E08DE";

        // Propiedades para la conexión SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.elasticemail.com");
        props.put("mail.smtp.port", "2525");

        // Inicio de sesión
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        try {
            // Crear un mensaje de correo
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject("Solicitud de Dorm");
            message.setText(mensaje);

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("El correo ha sido enviado exitosamente.");

        } catch (MessagingException e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    
    }

}

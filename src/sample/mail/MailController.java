package sample.mail;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.login.Login.globalSession;

public class MailController implements Initializable {

    @FXML
    private JFXTextField mailFrom;

    @FXML
    private JFXTextField mailTo;

    @FXML
    private JFXTextField mailCC;

    @FXML
    private TextArea mailMessage;


    private MimeMessage message;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        message = new MimeMessage(globalSession);
    }



    private void composeMessage(String recipient) {
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Hey Wayne, It's HeMailer");
            message.setText(mailMessage.getText());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    private void sendMessage(Message theMessage) {
        try {
            Transport.send(theMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
//            System.err.println("Message Not Successfully sent!");
        }
    }

    @FXML
    private void handleMessageSend(MouseEvent event) {
        composeMessage(mailTo.getText());
        sendMessage(message);
        System.out.println("Message successfully sent!");
    }

}

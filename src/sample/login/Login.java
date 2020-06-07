package sample.login;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    private  JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private Button signIn;

    public static Session globalSession;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void getSessionObject(String username, String password) {
        // Get properties object and add the attributes i.e. the host,
        // socket, port and so on
        Properties sessionProperties = new Properties();
        sessionProperties.put("mail.smtp.host", "smtp.gmail.com");
        sessionProperties.put("mail.smtp.socketFactory.port", "465");
        sessionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        sessionProperties.put("mail.smtp.starttls.enable", "true");
        sessionProperties.put("mail.smtp.auth", "true");
        sessionProperties.put("mail.smtp.port", "465");


        globalSession = Session.getDefaultInstance(sessionProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    @FXML
    private void handleSignIn(ActionEvent event) {

        try {
            getSessionObject(username.getText(), password.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have Successfully Logged In!");

            alert.setTitle("Login Successful!");
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> alert.close());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../mail/mail.fxml"));
            Parent roots = loader.load();
            ((Stage)signIn.getScene().getWindow()).setScene(new Scene(roots));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

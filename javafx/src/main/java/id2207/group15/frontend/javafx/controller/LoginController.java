package id2207.group15.frontend.javafx.controller;

import id2207.group15.frontend.javafx.Dao;
import id2207.group15.frontend.javafx.Cache;
import id2207.group15.frontend.javafx.view.MainView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.json.JSONObject;

import java.io.IOException;


public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    public void login(ActionEvent actionEvent) throws IOException, InterruptedException {
        Window owner = submitButton.getScene().getWindow();

        System.out.println(usernameField.getText());
        System.out.println(passwordField.getText());

        if (usernameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your username");
            return;
        }

        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String username = usernameField.getText();
        String password = passwordField.getText();
        JSONObject staff;
        try {
            staff = Dao.login(username, password);
            boolean flag = staff.isEmpty();
        } catch (Exception e) {
            infoBox("Please enter correct Email and Password", null, "Failed");
            passwordField.clear();
            return;
        }
        Cache.data.put("staff", staff);
        Alert alert = infoBox("Login Successful", null, "Successful");
        MainView mainView = new MainView();
        Stage newStage = new Stage();
        Thread loginThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mainView.start(newStage);
                            alert.close();
                            ((Stage)owner).close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        loginThread.start();
        return;

    }

    public static Alert infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.show();
        return alert;
    }

    private static void showAlert(AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}

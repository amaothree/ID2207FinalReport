package id2207.group15.frontend.javafx.controller;

import id2207.group15.frontend.javafx.Cache;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TaskFormController {
    public TextField subject;
    public TextField priority;
    public TextField sender;
    public TextField application_id;
    public TextField subteam_id;
    public TextField description;
    public Label id;
    public Button submit;
    public Button clear;

    private String url = "http://localhost:8080";

    @FXML
    public void initialize() {
        JSONObject object = (JSONObject) Cache.data.get("selectedTask");
        if(object != null){
            subject.setText(String.valueOf(object.get("subject")));
            priority.setText(String.valueOf(object.get("priority")));
            sender.setText(String.valueOf(object.get("sender")));
            application_id.setText(String.valueOf(object.get("applicationId")));
            subteam_id.setText(String.valueOf(object.get("subteamId")));
            description.setText(String.valueOf(object.get("description")));
            id.setText(String.valueOf(object.get("id")));
        }
    }

    public void Clear(ActionEvent actionEvent) {
        subject.clear();
        priority.clear();
        sender.clear();
        application_id.clear();
        subteam_id.clear();
        description.clear();
        id.setText("");
    }

    public void Submit(ActionEvent actionEvent) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/task/submit"))
                .header("Content-Type","application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "id="+id.getText()+
                                "&subject="+subject.getText()+
                                "&priority="+priority.getText()+
                                "&sender="+sender.getText()+
                                "&application_id="+application_id.getText()+
                                "&subteam_id="+subteam_id.getText()+
                                "&description="+description.getText()
                ))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.body().equals("Succeed")){
            infoBox("Submit/Update info Successful", null, "Succeed");
            return;
        } else {
            infoBox(response.body(), null, "Failed");
            return;
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.show();
        return;
    }
}

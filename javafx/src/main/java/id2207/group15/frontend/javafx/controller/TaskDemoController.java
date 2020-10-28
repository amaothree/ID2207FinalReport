package id2207.group15.frontend.javafx.controller;

import id2207.group15.frontend.javafx.Cache;
import id2207.group15.frontend.javafx.Dao;
import id2207.group15.frontend.javafx.view.TaskDemo;
import id2207.group15.frontend.javafx.view.TaskForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TaskDemoController {
    public ListView<String> app_list;
    public ListView<String> list_task;
    public TextField cr_id;
    public TextField leader_id;
    public TextField de;
    public TextField des;
    public Button new_app;
    public Button update;
    public Button new_task;
    public Label app_id;

    private String url = "http://localhost:8080";
    @FXML
    public void initialize() throws IOException, InterruptedException {
        JSONArray list = Dao.getAppList();
        Cache.data.put("AppList", list);
        ObservableList<String> items = FXCollections.observableArrayList ();
        for (int i = 0; i < list.length(); i++){
            JSONObject o = list.getJSONObject(i);
            items.add(o.get("id") + " " + o.get("department") + " " + o.get("description"));
        }
        app_list.setItems(items);

        list_task.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    try {
                        SelectTask();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    private void SelectTask() throws IOException {
        Cache.data.put("selectedTask",
                ((JSONArray)Cache.data.get("TaskList")).get(list_task.getSelectionModel().getSelectedIndex()));
        TaskForm taskForm = new TaskForm();
        Stage stage = new Stage();
        taskForm.start(stage);
    }

    public void NewApp(ActionEvent actionEvent) {
        app_id.setText("");
        cr_id.clear();
        leader_id.clear();
        de.clear();
        des.clear();
        list_task.setItems(FXCollections.observableArrayList());
    }

    public void Update(ActionEvent actionEvent) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/app/submit"))
                .header("Content-Type","application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "id="+app_id.getText()+
                                "&CRId="+cr_id.getText()+
                                "&department="+de.getText()+
                                "&leader_id="+leader_id.getText()+
                                "&description="+des.getText()
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

    public void NewTask(ActionEvent actionEvent) throws IOException {
        Cache.data.put("selectedTask", null);
        TaskForm taskForm = new TaskForm();
        Stage stage = new Stage();
        taskForm.start(stage);
    }

    public void GetTasks(MouseEvent mouseEvent) throws IOException, InterruptedException {
        JSONObject object = ((JSONArray) Cache.data.get("AppList")).getJSONObject(app_list.getSelectionModel().getSelectedIndex());
        JSONArray list = Dao.getTaskList(object.getLong("id"));
        Cache.data.put("TaskList", list);
        ObservableList<String> items = FXCollections.observableArrayList();
        for (int i = 0; i < list.length(); i++){
            JSONObject o = list.getJSONObject(i);
            items.add(o.get("id") + " " + o.get("subject") + " " + o.get("priority") + " " + o.get("description") );
        }
        list_task.setItems(items);

        cr_id.setText(String.valueOf(object.get("crid")));
        app_id.setText(String.valueOf(object.get("id")));
        leader_id.setText(String.valueOf(object.get("leaderId")));
        de.setText(String.valueOf(object.get("department")));
        des.setText(String.valueOf(object.get("description")));
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

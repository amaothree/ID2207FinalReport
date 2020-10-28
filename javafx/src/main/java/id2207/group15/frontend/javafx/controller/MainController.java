package id2207.group15.frontend.javafx.controller;

import id2207.group15.frontend.javafx.view.CSDemo;
import id2207.group15.frontend.javafx.Cache;
import id2207.group15.frontend.javafx.view.HRDemo;
import id2207.group15.frontend.javafx.view.TaskDemo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;

public class MainController {

    public Button task_demo;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Button cs_demo;
    @FXML
    private Button hr_demo;

    @FXML
    public void initialize(){
        JSONObject staff = (JSONObject) Cache.data.get("staff");
        label1.setText("Hello! " + staff.get("firstName") + " " + staff.get("lastName"));
        label2.setText((String)staff.get("department"));
        label3.setText((String)staff.get("title"));
        System.out.println(label1.getText());
    }

    @FXML
    public void CSDemo() throws IOException {
        CSDemo csDemo = new CSDemo();
        Stage stage = new Stage();
        csDemo.start(stage);
    }

    @FXML
    public void HRDemo() throws IOException {
        HRDemo hrDemo = new HRDemo();
        Stage stage = new Stage();
        hrDemo.start(stage);
    }


    public void TaskDemo(ActionEvent actionEvent) throws IOException {
        TaskDemo taskDemo = new TaskDemo();
        Stage stage = new Stage();
        taskDemo.start(stage);
    }
}

package id2207.group15.frontend.javafx.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        System.out.println(getClass());
        Parent root = FXMLLoader.load(getClass().getResource("task_demo.fxml"));
        stage.setTitle("System");
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }
}

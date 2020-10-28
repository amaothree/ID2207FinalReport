package id2207.group15.frontend.javafx.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CSDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("cs_demo.fxml"));
        primaryStage.setTitle("Client Service Demo");
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();
    }
}

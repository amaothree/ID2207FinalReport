package id2207.group15.frontend.javafx.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainView extends Application {

    @Override
    public void start (Stage stage) throws Exception{
        System.out.println(getClass());
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setTitle("System");
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

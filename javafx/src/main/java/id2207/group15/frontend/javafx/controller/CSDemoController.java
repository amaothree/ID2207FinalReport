package id2207.group15.frontend.javafx.controller;

import id2207.group15.frontend.javafx.Dao;
import id2207.group15.frontend.javafx.Cache;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;

public class CSDemoController {
    
    @FXML
    private TextField text_customer;
    @FXML
    private TextField text_event;
    @FXML
    private TextField text_attendee;
    @FXML
    private TextField text_preferences;
    @FXML
    private TextField text_food;
    @FXML
    private TextField text_music;
    @FXML
    private TextField text_computer;
    @FXML
    private TextField text_budget;
    @FXML
    private TextField text_description;
    @FXML
    private TextField text_film;
    @FXML
    private TextField text_poster;
    @FXML
    private TextField text_other;
    @FXML
    private Label text_id;
    @FXML
    private DatePicker date_from;
    @FXML
    private DatePicker date_to;
    @FXML
    private ListView<String> request_list;


    @FXML
    public void initialize() throws IOException, InterruptedException {
        JSONArray list = Dao.getCRList();
        Cache.data.put("CRList", list);
        ObservableList<String> items = FXCollections.observableArrayList ();
        for (int i = 0; i < list.length(); i++){
            JSONObject o = list.getJSONObject(i);
            items.add(o.get("id") + " " + o.get("clientName") + " " + o.get("eventType"));
        }
        request_list.setItems(items);
    }

    @FXML
    public void info() throws IOException, InterruptedException {
        JSONObject object = Dao.getCRList().getJSONObject(request_list.getSelectionModel().getSelectedIndex());
        text_id.setText(String.valueOf(object.get("id")));
        text_attendee.setText(String.valueOf(object.get("expected_attendees")));
        text_budget.setText(String.valueOf(object.get("expected_budget")));
        text_computer.setText(String.valueOf(object.get("computer_and_related_issues")));
        text_description.setText(String.valueOf(object.get("description")));
        text_event.setText(String.valueOf(object.get("event_type")));
        text_film.setText(String.valueOf(object.get("filming_and_photos")));
        text_food.setText(String.valueOf(object.get("food_and_drinks")));
        text_customer.setText(String.valueOf(object.get("client_name")));
        text_music.setText(String.valueOf(object.get("music")));
        text_other.setText(String.valueOf(object.get("other_needs")));
        text_poster.setText(String.valueOf(object.get("poster_and_artwork")));
        text_preferences.setText(String.valueOf(object.get("preferences")));
        date_from.setValue(LocalDate.parse(String.valueOf(object.get("date_from"))));
        date_to.setValue(LocalDate.parse(String.valueOf(object.get("date_to"))));
    }

    @FXML
    public void submit() throws IOException, InterruptedException {
        String flag = Dao.updateCR(text_id.getText(),
                text_customer.getText(),
                text_event.getText(),
                date_from.getValue().toString(),
                date_to.getValue().toString(),
                text_budget.getText(),
                text_attendee.getText(),
                text_preferences.getText(),
                text_description.getText(),
                text_food.getText(),
                text_film.getText(),
                text_music.getText(),
                text_poster.getText(),
                text_computer.getText(),
                text_other.getText());
        if (flag.equals("Succeed")){
            infoBox("Submit/Update info Successful", null, "Succeed");
            return;
        } else {
            infoBox(flag, null, "Failed");
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

    @FXML
    public void resetInfo(){
        text_id.setText("");
        text_attendee.clear();
        text_budget.clear();
        text_computer.clear();
        text_description.clear();
        text_event.clear();
        text_film.clear();
        text_food.clear();
        text_customer.clear();
        text_music.clear();
        text_other.clear();
        text_poster.clear();
        text_preferences.clear();
        date_from.setValue(null);
        date_to.setValue(null);
    }

}

package id2207.group15.frontend.javafx.controller;

import id2207.group15.frontend.javafx.Dao;
import id2207.group15.frontend.javafx.Cache;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HRDemoController {


    public TextField staff_fn;
    public TextField staff_un;
    public TextField staff_ln;
    public TextField recu_de;
    public TextField recu_ye;
    public TextField staff_pass;
    public TextField recu_ti;
    public TextField recu_des;
    public Button recu_sub;
    public ListView<String> staff_list;
    public Button recu_new;
    public Label staff_id;
    public TextField staff_ti;
    public TextField staff_de;
    public Button staff_add;
    public ListView<String> recu_list;
    public Label recu_id;
    public TextField recu_ty;

    private String url = "http://localhost:8080";

    @FXML
    public void initialize() throws IOException, InterruptedException {
        JSONArray list = Dao.getStaffList();
        Cache.data.put("StaffList", list);
        ObservableList<String> items = FXCollections.observableArrayList ();
        for (int i = 0; i < list.length(); i++){
            JSONObject o = list.getJSONObject(i);
            items.add(o.get("id") + " " + o.get("firstName") + " " + o.get("lastName"));
        }
        staff_list.setItems(items);

        list = Dao.getRecruList();
        Cache.data.put("RecruList", list);
        items = FXCollections.observableArrayList ();
        for (int i = 0; i < list.length(); i++){
            JSONObject o = list.getJSONObject(i);
            items.add(o.get("id") + " " + o.get("requestingDepartment") + " " + o.get("jobTitle"));
        }
        recu_list.setItems(items);

    }


    public void recruList(MouseEvent mouseEvent) {
        JSONObject object = ((JSONArray) Cache.data.get("RecruList")).getJSONObject(recu_list.getSelectionModel().getSelectedIndex());
        recu_id.setText(String.valueOf(object.get("id")));
        recu_ye.setText(String.valueOf(object.get("yearOfExperience")));
        recu_ty.setText(String.valueOf(object.get("contractType")));
        recu_de.setText(String.valueOf(object.get("requestingDepartment")));
        recu_ti.setText(String.valueOf(object.get("jobTitle")));
        recu_des.setText(String.valueOf(object.get("description")));
    }

    public void staffUp(ActionEvent actionEvent) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/staff/submit"))
                .header("Content-Type","application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "id="+staff_id.getText()+
                                "&firstName="+staff_fn.getText()+
                                "&lastName="+staff_ln.getText()+
                                "&username="+staff_un.getText()+
                                "&password="+staff_pass.getText()+
                                "&department="+staff_de.getText()+
                                "&title="+staff_ti.getText()
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

    public void Clear(ActionEvent actionEvent) {
        staff_fn.setText("");
        staff_un.setText("");
        staff_ln.setText("");
        staff_pass.setText("");
        staff_ti.setText("");
        recu_de.setText("");
        recu_des.setText("");
        recu_ti.setText("");
        recu_ye.setText("");
        staff_de.setText("");
        recu_ty.setText("");
        staff_id.setText("");
        recu_id.setText("");
    }

    public void staffList(MouseEvent mouseEvent) {
        JSONObject object = ((JSONArray) Cache.data.get("StaffList")).getJSONObject(staff_list.getSelectionModel().getSelectedIndex());
        staff_id.setText(String.valueOf(object.get("id")));
        staff_de.setText(String.valueOf(object.get("department")));
        staff_ln.setText(String.valueOf(object.get("lastName")));
        staff_fn.setText(String.valueOf(object.get("firstName")));
        staff_ti.setText(String.valueOf(object.get("title")));
        staff_pass.setText(String.valueOf(object.get("password")));
        staff_un.setText(String.valueOf(object.get("username")));
    }

    public void recuSub(ActionEvent actionEvent) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/RR/submit"))
                .header("Content-Type","application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "id="+recu_id.getText()+
                                "&contractType="+recu_ty.getText()+
                                "&requestingDepartment="+recu_de.getText()+
                                "&yearOfExperience="+recu_ye.getText()+
                                "&jobTitle="+recu_ti.getText()+
                                "&description="+recu_des.getText()
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

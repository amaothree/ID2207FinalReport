package id2207.group15.frontend.javafx;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.Map;

public class Dao {

    private static String url = "http://localhost:8080";

    public static JSONObject login(String username, String password) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/login"))
                .header("Content-Type","application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("username="+username+"&password="+password))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(new JSONObject(response.body()));

        return new JSONObject(response.body());
    }

    public static JSONArray getCRList() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/CS/all"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONArray(response.body());
    }

    public static JSONArray getStaffList() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/staff/all"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONArray(response.body());
    }

    public static JSONArray getRecruList() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/RR/all"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONArray(response.body());
    }

    public static JSONArray getAppList() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/app/all"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONArray(response.body());
    }

    public static JSONArray getTaskList(Long appId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/task/get?applicationId=" + appId))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONArray(response.body());
    }

    public static String updateCR(String id, String client_name, String event_type, String from, String to, String expected_budget, String expected_attendees, String preferences, String description, String food_and_drinks, String filming_and_photos, String music, String poster_and_artwork, String computer_and_related_issues, String other_needs) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/CS/submit"))
                .header("Content-Type","application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "id="+id+
                        "&client_name="+client_name+
                        "&event_type="+event_type+
                        "&from="+from+
                        "&to="+to+
                        "&expected_budget="+expected_budget+
                        "&expected_attendees="+expected_attendees+
                        "&preferences="+preferences+
                        "&description="+description+
                        "&food_and_drinks="+food_and_drinks+
                        "&filming_and_photos="+filming_and_photos+
                        "&music="+music+
                        "&poster_and_artwork="+poster_and_artwork+
                        "&computer_and_related_issues="+computer_and_related_issues+
                        "&other_needs="+other_needs
                        ))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
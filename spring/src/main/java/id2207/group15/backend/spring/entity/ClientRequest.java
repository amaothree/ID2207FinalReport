package id2207.group15.backend.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table

public class ClientRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clientName;
    private String eventType;
    private Date dateFrom;
    private Date dateTo;
    private double expectedBudget;
    private int expectedAttendees;
    private String preferences;
    private String description;
    private String foodAndDrinks;
    private String filmingAndPhotos;
    private String music;
    private String posterAndArtwork;
    private String computerAndRelatedIssues;
    private String otherNeeds;

    public ClientRequest(String clientName, String eventType, Date from, Date to, double expectedBudget, int expectedAttendees, String preferences, String description, String foodAndDrinks, String filmingAndPhotos, String music, String posterAndArtwork, String computerAndRelatedIssues, String otherNeeds) {
        this.clientName = clientName;
        this.eventType = eventType;
        this.dateFrom = from;
        this.dateTo = to;
        this.expectedBudget = expectedBudget;
        this.expectedAttendees = expectedAttendees;
        this.preferences = preferences;
        this.description = description;
        this.foodAndDrinks = foodAndDrinks;
        this.filmingAndPhotos = filmingAndPhotos;
        this.music = music;
        this.posterAndArtwork = posterAndArtwork;
        this.computerAndRelatedIssues = computerAndRelatedIssues;
        this.otherNeeds = otherNeeds;
    }

}
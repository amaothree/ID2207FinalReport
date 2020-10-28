package id2207.group15.backend.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;
    private String priority;
    private String sender;
    private Long applicationId;
    private Long subteamId;
    private String description;
}

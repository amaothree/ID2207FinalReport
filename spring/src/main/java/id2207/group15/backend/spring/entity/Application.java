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

public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long CRId;
    private String department;
    private Long leaderId;
    private String description;
}

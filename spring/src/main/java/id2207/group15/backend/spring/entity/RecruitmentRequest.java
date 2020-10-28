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

public class RecruitmentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String contractType;
    private String requestingDepartment;
    private double yearOfExperience;
    private String jobTitle;
    private String description;
}

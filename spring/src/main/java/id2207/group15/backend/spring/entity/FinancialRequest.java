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

public class FinancialRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String requestingDepartment;
    private Long reference;
    private double requiredAmount;
    private String reason;
    private Long leaderId;
}

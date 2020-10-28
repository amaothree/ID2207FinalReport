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
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private Long subteamId;
    @Column(unique = true)
    private String username;
    private String password;
    private String department;
    private String title;

    public Staff(String firstName, String lastName, String username, String password, String department, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.department = department;
        this.title = title;
    }
}
package co.develhope.mockdatabase.entities;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue
    private String id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;

}

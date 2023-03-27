package co.develhope.Repo01.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String modelName;
    @Column(nullable = false)
    private Long serialNumber;
    private Double currentPrice;

}

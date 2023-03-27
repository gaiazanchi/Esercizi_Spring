package co.develhope.Repo02.entities;

import jakarta.persistence.*;

import java.time.Year;

@Entity
@Table(name = "programming_languages")
public class ProgrammingLanguage {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String name;
    private Year firstAppearance;
    @Column(nullable = false)
    private String inventor;

    public ProgrammingLanguage(){}

    public ProgrammingLanguage(Integer id, String name, Year firstAppearance, String inventor) {
        this.id = id;
        this.name = name;
        this.firstAppearance = firstAppearance;
        this.inventor = inventor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(Year firstAppearance) {
        this.firstAppearance = firstAppearance;
    }

    public String getInventor() {
        return inventor;
    }

    public void setInventor(String inventor) {
        this.inventor = inventor;
    }

}

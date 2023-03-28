package co.develhope.Repo02.entities;

import jakarta.persistence.*;

import java.time.Year;

@Entity
@Table(name = "programming_languages")
public class ProgrammingLanguage {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String name;
    private Year firstAppearance;
    @Column(nullable = false)
    private String inventor;

    public ProgrammingLanguage(){}

    public ProgrammingLanguage(String name, Year firstAppearance, String inventor) {
        this.name = name;
        this.firstAppearance = firstAppearance;
        this.inventor = inventor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

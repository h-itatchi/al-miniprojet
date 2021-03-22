package com.example.teachersservice.DATA.Entities;

import com.example.teachersservice.DATA.Converters.ArrayListConverter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    @Convert(converter = ArrayListConverter.class)
    private ArrayList<Long> courses;

    public Teacher() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Long> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Long> courses) {
        this.courses = courses;
    }
}

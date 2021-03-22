package com.example.uiservice.DATA.Entities;


import java.util.ArrayList;

public class Teacher {


    private Long id;
    private String nom;
    private String prenom;
    private String email;
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

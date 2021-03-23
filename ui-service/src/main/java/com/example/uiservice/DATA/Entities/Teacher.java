package com.example.uiservice.DATA.Entities;

import java.util.ArrayList;

public class Teacher {

    private long id;
    private String lastName;
    private String firstName;
    private String password;
    private String matchingPassword;
    private String email;
    private ArrayList<Integer> courses;
    private ArrayList<String> roles;

    public Teacher() {
        courses = new ArrayList<>();
        roles = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Integer> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Integer> courses) {
        this.courses = courses;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }
}

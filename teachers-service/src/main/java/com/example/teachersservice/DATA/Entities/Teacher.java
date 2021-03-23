package com.example.teachersservice.DATA.Entities;

import com.example.teachersservice.DATA.Converters.ArrayListConverter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String password;
    private String matchingPassword;
    private String email;
    @Convert(converter = ArrayListConverter.class)
    private ArrayList<Integer> courses;
    @Convert(converter = ArrayListConverter.class)
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }
}

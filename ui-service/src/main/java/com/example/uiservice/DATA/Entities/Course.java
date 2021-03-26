package com.example.uiservice.DATA.Entities;


import java.util.ArrayList;

public class Course {

    private long id;
    private String name;
    private String description;
    private int credits;
    private int coefficient;
    private int volHTD;
    private int volHTP;
    private int volHCourse;
    private ArrayList<Integer> teachers;
    private String teachersToString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getVolHTD() {
        return volHTD;
    }

    public void setVolHTD(int volHTD) {
        this.volHTD = volHTD;
    }

    public int getVolHTP() {
        return volHTP;
    }

    public void setVolHTP(int volHTP) {
        this.volHTP = volHTP;
    }

    public int getVolHCourse() {
        return volHCourse;
    }

    public void setVolHCourse(int volHCourse) {
        this.volHCourse = volHCourse;
    }

    public ArrayList<Integer> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Integer> teachers) {
        this.teachers = teachers;
    }

    public String getTeachersToString() {
        return teachersToString;
    }

    public void setTeachersToString(String teachersToString) {
        this.teachersToString = teachersToString;
    }
}

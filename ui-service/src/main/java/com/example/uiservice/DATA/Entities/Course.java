package com.example.uiservice.DATA.Entities;


import java.util.ArrayList;

public class Course {

    private Long id;
    private String name;
    private String description;
    private int credits;
    private int coefficient;
    private ArrayList<Integer> teachers;

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

    public ArrayList<Integer> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Integer> teachers) {
        this.teachers = teachers;
    }
}

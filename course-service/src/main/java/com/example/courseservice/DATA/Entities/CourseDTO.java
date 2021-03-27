package com.example.courseservice.DATA.Entities;

import java.util.ArrayList;

public class CourseDTO {

    private long id;
    private String name;
    private String description;
    private int credits;
    private int coefficient;
    private int volHTD;
    private int volHTP;
    private int volHCourse;
    private ArrayList<Integer> teachers;
    private final ArrayList<WorkDTO> works;

    public CourseDTO(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.description = course.getDescription();
        this.credits = course.getCredits();
        this.coefficient = course.getCoefficient();
        this.volHTD = course.getVolHTD();
        this.volHTP = course.getVolHTP();
        this.volHCourse = course.getVolHCourse();
        this.teachers = course.getTeachers();
        this.works = new ArrayList<>();
        course.getWorks().forEach(work -> works.add(new WorkDTO(work)));
    }

    public CourseDTO() {
        this.works = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public ArrayList<WorkDTO> getWorks() {
        return works;
    }
}

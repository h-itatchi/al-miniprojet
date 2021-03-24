package com.example.courseservice.DATA.Entities;

import com.example.courseservice.DATA.Converters.ArrayListConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private int credits;
    private int coefficient;
    private int volHTD;
    private int volHTP;
    private int volHCourse;
    @Convert(converter = ArrayListConverter.class)
    private ArrayList<Long> teachers;
    @OneToMany(mappedBy = "course")
    private Set<Work> works;

    public Course() {
        teachers = new ArrayList<>();
    }

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

    public ArrayList<Long> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Long> teachers) {
        this.teachers = teachers;
    }

    public Set<Work> getWorks() {
        return works;
    }

    public void setWorks(Set<Work> works) {
        this.works = works;
    }
}

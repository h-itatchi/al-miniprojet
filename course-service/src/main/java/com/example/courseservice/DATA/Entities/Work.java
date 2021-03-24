package com.example.courseservice.DATA.Entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Work {

    public static final String typeTD="TD";
    public static final String typeTP="TP";
    public static final String typeChapter="Chapter";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private LocalDate date;
    private String linkToPDF;
    private String description;
    private String type;
    @ManyToOne
    private Course course;

    public Work() {
    }

    public static String getTypeTD() {
        return typeTD;
    }

    public static String getTypeTP() {
        return typeTP;
    }

    public static String getTypeChapter() {
        return typeChapter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLinkToPDF() {
        return linkToPDF;
    }

    public void setLinkToPDF(String linkToPDF) {
        this.linkToPDF = linkToPDF;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

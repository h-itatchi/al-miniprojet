package com.example.courseservice.DATA.Entities;

import java.time.LocalDate;
import java.util.Date;

public class WorkDTO {
    private long id;
    private String title;
    private Date date;
    private String linkToPDF;
    private String description;
    private String type;

    public WorkDTO(Work work) {
        this.id = work.getId();
        this.title = work.getTitle();
        this.date = work.getDate();
        this.linkToPDF = work.getLinkToPDF();
        this.description = work.getDescription();
        this.type = work.getType();
    }

    public WorkDTO() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}

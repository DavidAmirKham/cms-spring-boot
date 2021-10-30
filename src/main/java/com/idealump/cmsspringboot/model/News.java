package com.idealump.cmsspringboot.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "news")
public class News {
    @Id
    private int id;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date publish_from;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date publish_to;

    @NotBlank
    private String news;

    private int status;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getPublish_from() {
        return publish_from;
    }
    public void setPublish_from(Date publish_from) {
        this.publish_from = publish_from;
    }
    public Date getPublish_to() {
        return publish_to;
    }
    public void setPublish_to(Date publish_to) {
        this.publish_to = publish_to;
    }
    public String getNews() {
        return news;
    }
    public void setNews(String news) {
        this.news = news;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}

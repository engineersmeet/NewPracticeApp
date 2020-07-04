package com.pravin.lede.gl.myapplication.models;

public class BlogsInfo {

int id;

String title;
String description;
String tages;
String date_time;
String seen_counts;
String keywords;
String image;

    public BlogsInfo(int id, String title, String description, String tages, String date_time, String seen_counts, String keywords, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tages = tages;
        this.date_time = date_time;
        this.seen_counts = seen_counts;
        this.keywords = keywords;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTages() {
        return tages;
    }

    public String getDate_time() {
        return date_time;
    }

    public String getSeen_counts() {
        return seen_counts;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getImage() {
        return image;
    }
}

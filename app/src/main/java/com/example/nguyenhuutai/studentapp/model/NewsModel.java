package com.example.nguyenhuutai.studentapp.model;

public class NewsModel {

    private String user;
    private String time;
    private String content;

    public NewsModel(){

    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return this.user  + this.time + this.content;
    }
}

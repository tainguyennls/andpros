package com.example.nguyenhuutai.studentapp.models;

public class NewsModel {

    private String user;
    private String time;
    private String content;
    private String uid;

    public NewsModel(){

    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getUid() {
        return uid;
    }
}

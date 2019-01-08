package com.example.nguyenhuutai.studentapp.models;

import android.support.annotation.NonNull;

import com.example.nguyenhuutai.studentapp.dao.Data;
import com.example.nguyenhuutai.studentapp.interfaces.INews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewsModel {

    private String user;
    private String time;
    private String content;
    private Data data;
    private List<NewsModel> newsModels;

    public NewsModel(){
        data = new Data();
        newsModels  = new ArrayList<>();
    }

    public void setUser(String user) {
        this.user = user;
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

    public void getListOfNews(final INews iNews){

        ValueEventListener valueEventListener  = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot sh : dataSnapshot.getChildren()){
                    NewsModel newsModel = sh.getValue(NewsModel.class);
                    newsModels.add(newsModel);
                }
                iNews.call(newsModels);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        data.moveToNode("news").addListenerForSingleValueEvent(valueEventListener);
    }


}

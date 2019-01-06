package com.example.nguyenhuutai.studentapp.controller;

import android.content.Context;

import com.example.nguyenhuutai.studentapp.adapters.NewsAdapter;
import com.example.nguyenhuutai.studentapp.interfaces.INews;
import com.example.nguyenhuutai.studentapp.model.NewsModel;

public class NewsController {

    private NewsModel newsModel;
    private NewsAdapter newsAdapter;
    private Context context;
    private int resource;

    public NewsController(Context context,int resource){
        newsModel = new NewsModel();
        this.context = context;
        this.resource = resource;
    }

    public void render(INews iNews){

    }
}

package com.example.nguyenhuutai.studentapp.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.adapters.NewsAdapter;
import com.example.nguyenhuutai.studentapp.dao.Data;
import com.example.nguyenhuutai.studentapp.interfaces.INews;
import com.example.nguyenhuutai.studentapp.models.ItemOffsetDecoration;
import com.example.nguyenhuutai.studentapp.models.NewsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {

    private Data data;
    private List<NewsModel> newsModels;
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_bang_tin, container, false);

        data = new Data();
        newsModels = new ArrayList<>();
        recyclerView = v.findViewById(R.id.lvNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(10);
        recyclerView.addItemDecoration(itemDecoration);
        render();
        return v;
    }

    public void getListOfNews(final INews iNews){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot sh : dataSnapshot.getChildren()){
                    NewsModel newsModel = sh.getValue(NewsModel.class);
                    newsModels.add(newsModel);
                }
                iNews.call(newsModels);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        data.moveToNode("news").addListenerForSingleValueEvent(valueEventListener);
    }

    public void render(){
        getListOfNews(new INews() {
            @Override
            public void call(List<NewsModel> newsModels) {
                newsAdapter = new NewsAdapter(newsModels);
                recyclerView.setAdapter(newsAdapter);
            }
        });
    }

}

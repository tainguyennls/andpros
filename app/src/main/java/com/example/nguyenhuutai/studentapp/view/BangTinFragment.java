package com.example.nguyenhuutai.studentapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.adapters.NewsAdapter;
import com.example.nguyenhuutai.studentapp.interfaces.INews;
import com.example.nguyenhuutai.studentapp.model.ItemOffsetDecoration;
import com.example.nguyenhuutai.studentapp.model.NewsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class BangTinFragment extends Fragment {

    private List<NewsModel> newsModels;
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;
    private DatabaseReference df;


    public BangTinFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_bang_tin, container, false);
        df = FirebaseDatabase.getInstance().getReference().child("news");
        newsModels = new ArrayList<>();
        recyclerView = v.findViewById(R.id.lvNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(10);
        recyclerView.addItemDecoration(itemDecoration);
        render();
        return v;
    }

    public void getDoanHoi(final INews iNews){

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
        df.addListenerForSingleValueEvent(valueEventListener);
    }

    public void render(){
        getDoanHoi(new INews() {
            @Override
            public void call(List<NewsModel> newsModels) {
                newsAdapter = new NewsAdapter(newsModels);
                recyclerView.setAdapter(newsAdapter);
            }
        });
    }

}

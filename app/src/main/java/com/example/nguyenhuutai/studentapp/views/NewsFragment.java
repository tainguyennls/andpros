package com.example.nguyenhuutai.studentapp.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.adapters.NewsAdapter;
import com.example.nguyenhuutai.studentapp.models.ItemOffsetDecoration;
import com.example.nguyenhuutai.studentapp.models.NewsModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment implements ValueEventListener,ChildEventListener {

    private List<NewsModel> newsModels;
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerViewNews;
    private DatabaseReference df;
    private ItemOffsetDecoration itemDecoration;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_news, container, false);

        newsModels = new ArrayList<>();
        recyclerViewNews = v.findViewById(R.id.rvNews);
        newsAdapter = new NewsAdapter(newsModels);
        recyclerViewNews.setAdapter(newsAdapter);
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(getContext()));
        itemDecoration = new ItemOffsetDecoration(10);
        recyclerViewNews.addItemDecoration(itemDecoration);

        df = FirebaseDatabase.getInstance().getReference("news");
        df.addValueEventListener(this);
        df.addChildEventListener(this);

        return v;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()){
            NewsModel newsModel = sh.getValue(NewsModel.class);
            newsModels.add(newsModel);
            newsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot,String s) {

    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot,String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot,String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}

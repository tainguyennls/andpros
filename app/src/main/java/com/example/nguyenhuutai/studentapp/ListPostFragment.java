package com.example.nguyenhuutai.studentapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.nguyenhuutai.studentapp.adapters.NewsAdapter;


public class ListPostFragment extends Fragment {

    private ListView lstPost;
    private NewsAdapter newsAdapter;


    public ListPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_post, container, false);
            lstPost = v.findViewById(R.id.lst_post);


        return v;
    }


}

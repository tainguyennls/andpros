package com.example.nguyenhuutai.studentapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.model.NewsModel;
import com.example.nguyenhuutai.studentapp.R;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsModel> {

    private Context context;
    private List<NewsModel> newsModels;
    private TextView user,time,content;

    public NewsAdapter(Context context, int resource,List<NewsModel> objects) {
        super(context, resource, objects);
        this.context= context;
        this.newsModels = objects;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {

        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.news_item,parent,false);
            user = convertView.findViewById(R.id.user_dis);
            time = convertView.findViewById(R.id.timer_news);
            content = convertView.findViewById(R.id.content_news);

            NewsModel newsModel = newsModels.get(position);

            user.setText(newsModel.getUser());
            time.setText(newsModel.getTime());
            content.setText(newsModel.getContent());

        }
        return convertView;
    }
}

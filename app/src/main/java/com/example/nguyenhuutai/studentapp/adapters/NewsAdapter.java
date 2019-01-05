package com.example.nguyenhuutai.studentapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.model.NewsModel;
import com.example.nguyenhuutai.studentapp.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<NewsModel> newsModels;

    public NewsAdapter(List<NewsModel> objects) {
        this.newsModels = objects;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView user,time,content;

        public ViewHolder(View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.user_dis);
            time = itemView.findViewById(R.id.timer_news);
            content = itemView.findViewById(R.id.content_news);
        }
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder viewHolder, int i) {
        NewsModel newsModel = newsModels.get(i);

        viewHolder.user.setText(newsModel.getUser());
        viewHolder.time.setText(newsModel.getTime());
        viewHolder.content.setText(newsModel.getContent());
    }

    @Override
    public int getItemCount() {
        return newsModels.size();
    }
}

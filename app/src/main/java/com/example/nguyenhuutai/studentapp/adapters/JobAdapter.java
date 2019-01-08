package com.example.nguyenhuutai.studentapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.models.JobModel;

import java.util.List;

public class JobAdapter extends ArrayAdapter<JobModel> {
    private Context  context;
    private List<JobModel> jobModels;
    private TextView txtTitle,txtTimer;
    private LinearLayout linearLayout;

    public JobAdapter(Context context, int resource, List<JobModel> objects) {
        super(context, resource, objects);
        this.context  = context;
        this.jobModels = objects;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.career_item,parent,false);

            txtTitle = convertView.findViewById(R.id.title_career);
            txtTimer = convertView.findViewById(R.id.time_career);
            linearLayout  = convertView.findViewById(R.id.job_id);

            JobModel jobModel  = jobModels.get(position);

            txtTitle.setText(jobModel.getTitle());
            txtTimer.setText(jobModel.getTime());
        }
        return convertView;
    }
}

package com.example.nguyenhuutai.studentapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.model.JobModel;

import java.util.List;

public class ViecLamAdapter extends ArrayAdapter<JobModel> {
    private Context  context;
    private List<JobModel> viecLamModels;
    private TextView txtTitle,txtTimer;

    public ViecLamAdapter(Context context, int resource,List<JobModel> objects) {
        super(context, resource, objects);
        this.context  = context;
        this.viecLamModels = objects;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.career_item,parent,false);

            txtTitle = convertView.findViewById(R.id.title_career);
            txtTimer = convertView.findViewById(R.id.time_career);

            JobModel viecLamModel  = viecLamModels.get(position);

            txtTitle.setText(viecLamModel.getTitle());
            txtTimer.setText(viecLamModel.getTime());
        }
        return convertView;
    }
}

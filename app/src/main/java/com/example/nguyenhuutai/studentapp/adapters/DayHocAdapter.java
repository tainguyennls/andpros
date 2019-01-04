package com.example.nguyenhuutai.studentapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.model.DayHocModel;

import java.util.List;

public class DayHocAdapter extends ArrayAdapter<DayHocModel> {

    private Context context;
    private int resource;
    private List<DayHocModel> dayHocModels;
    private TextView txtName,txtContent;

    public DayHocAdapter(Context context, int resource, List<DayHocModel> objects) {
        super(context, resource, objects);
        this.context  = context;
        this.resource = resource;
        this.dayHocModels = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.dayhocitem,parent,false);
            txtName  = convertView.findViewById(R.id.name_day_hoc);
            txtContent = convertView.findViewById(R.id.content_day_hoc);

            DayHocModel models  = dayHocModels.get(position);

            txtName.setText(models.getName());
            txtContent.setText(models.getContent());

            if(models.getTag().equalsIgnoreCase("off")){
                txtName.setTextColor(Color.parseColor("#f44141"));
            }
            else if(models.getTag().equalsIgnoreCase("change")){
                txtName.setTextColor(Color.parseColor("#42e8f4"));
            }
        }
        return convertView;
    }
}

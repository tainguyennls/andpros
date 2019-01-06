package com.example.nguyenhuutai.studentapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.model.TeachModel;

import java.util.List;

public class TeachAdapter extends ArrayAdapter<TeachModel> {

    private Context context;
    private int resource;
    private List<TeachModel> teachModels;
    private TextView txtName,txtContent;

    public TeachAdapter(Context context, int resource, List<TeachModel> objects) {
        super(context, resource, objects);
        this.context  = context;
        this.resource = resource;
        this.teachModels = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.teach_item,parent,false);
            txtName  = convertView.findViewById(R.id.name_day_hoc);
            txtContent = convertView.findViewById(R.id.content_day_hoc);

            TeachModel models  = teachModels.get(position);

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

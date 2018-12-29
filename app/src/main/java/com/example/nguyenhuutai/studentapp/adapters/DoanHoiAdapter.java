package com.example.nguyenhuutai.studentapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.model.DoanHoiModel;

import java.util.List;

public class DoanHoiAdapter extends ArrayAdapter {

    private Context context;
    private int resource;
    private List<DoanHoiModel> doanHoiModels;
    private TextView txtName,txtDecscript;

    public DoanHoiAdapter(Context context, int resource, List<DoanHoiModel> objects) {

        super(context, resource, objects);
        this.context  = context;
        this.resource = resource;
        this.doanHoiModels = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_doan_hoi,parent,false);
            txtName  = convertView.findViewById(R.id.title_info);
            txtDecscript = convertView.findViewById(R.id.subs);

            DoanHoiModel models  = doanHoiModels.get(position);

            txtName.setText(models.getName());
            txtDecscript.setText(models.getDescsipt());
        }
        return convertView;
    }
}

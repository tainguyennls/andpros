package com.example.nguyenhuutai.studentapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.model.GroupModel;

import java.util.List;

public class DoanHoiAdapter extends ArrayAdapter {

    private Context context;
    private int resource;
    private List<GroupModel> groupModels;
    private TextView txtName,txtDecscript;

    public DoanHoiAdapter(Context context, int resource, List<GroupModel> objects) {

        super(context, resource, objects);
        this.context  = context;
        this.resource = resource;
        this.groupModels = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_doan_hoi,parent,false);
            txtName  = convertView.findViewById(R.id.title_info);
            txtDecscript = convertView.findViewById(R.id.subs);

            GroupModel models  = groupModels.get(position);

            txtName.setText(models.getName());
            txtDecscript.setText(models.getDescript());
        }
        return convertView;
    }
}

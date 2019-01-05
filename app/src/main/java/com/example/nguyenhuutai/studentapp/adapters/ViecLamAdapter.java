package com.example.nguyenhuutai.studentapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.model.ViecLamModel;

import java.util.List;

public class ViecLamAdapter extends ArrayAdapter<ViecLamModel> {
    private Context  context;
    private List<ViecLamModel> viecLamModels;
    private TextView txtTitle,txtTimer;

    public ViecLamAdapter(Context context, int resource,List<ViecLamModel> objects) {
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

            ViecLamModel viecLamModel  = viecLamModels.get(position);

            txtTitle.setText(viecLamModel.getTitle());
            txtTimer.setText(viecLamModel.getTime());
        }
        return convertView;
    }
}

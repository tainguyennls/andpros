package com.example.nguyenhuutai.studentapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.models.StringItem;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<StringItem> {

    private  Context context;
    private int resource;
    private List<StringItem> items;
    private TextView txtName;

    public ItemAdapter(Context context, int resource, List<StringItem> objects) {
        super(context, resource, objects);
        this.context  = context;
        this.resource = resource;
        this.items = objects;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_string_details,parent,false);
            txtName  = convertView.findViewById(R.id.item_details);
            StringItem stringItem  = items.get(position);
            txtName.setText(stringItem.getName());
        }
        return convertView;
    }
}

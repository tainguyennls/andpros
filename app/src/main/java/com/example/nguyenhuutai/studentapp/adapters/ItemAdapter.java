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

public class ItemAdapter extends ArrayAdapter<StringItem>
{
    private TextView txtItem;
    private Context context;
    private List<StringItem> stringItems;

    public ItemAdapter(Context context, int resource,List<StringItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.stringItems = objects;
    }


    @Override
    public View getView(int position, View convertView,ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_string_details,parent,false);
            txtItem = convertView.findViewById(R.id.item_details);

            StringItem stringItem = stringItems.get(position);

            txtItem.setText(stringItem.getName());

        }
        return convertView;
    }
}

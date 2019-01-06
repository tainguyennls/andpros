package com.example.nguyenhuutai.studentapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.models.LecturerModel;
import java.util.List;

public class LecturerAdapter extends ArrayAdapter<LecturerModel> {

    private Context context;
    private List<LecturerModel> lstLecturer;
    private ImageView logo;
    private TextView user,email;
    private LinearLayout linearLayout;


    public LecturerAdapter(Context context, int resource, List<LecturerModel> objects) {
        super(context, resource, objects);

        this.context = context;
        this.lstLecturer = objects;

    }


    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        if(null == convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.lecturer_item,parent,false);

            logo = convertView.findViewById(R.id.logo);
            user = convertView.findViewById(R.id.txtNameOfLecturer);
            email = convertView.findViewById(R.id.txtEmail);
            linearLayout = convertView.findViewById(R.id.change);



            LecturerModel lecturer = lstLecturer.get(position);
            lecturer.setImageBitMap(logo,lecturer.getImage());

            linearLayout.setId(lecturer.getId());
            user.setText(lecturer.getName());
            email.setText(lecturer.getEmail());

        }
        return convertView;
    }
}

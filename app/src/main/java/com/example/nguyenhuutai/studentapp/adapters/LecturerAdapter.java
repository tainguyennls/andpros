package com.example.nguyenhuutai.studentapp.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.models.LecturerModel;
import com.example.nguyenhuutai.studentapp.views.LecturerDetailActivity;

import java.util.List;

public class LecturerAdapter extends RecyclerView.Adapter<LecturerAdapter.ViewHolder> {

    private List<LecturerModel> lecturerModels;


    public LecturerAdapter(List<LecturerModel> objects) {
        this.lecturerModels = objects;

    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView logo,trans;
        TextView user,email;

        public ViewHolder(View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.logo);
            user = itemView.findViewById(R.id.txtNameOfLecturer);
            email = itemView.findViewById(R.id.txtEmail);
            trans = itemView.findViewById(R.id.trans);

            trans.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),LecturerDetailActivity.class);
                    intent.putExtra("id_details",v.getId()+"");
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lecturer_item,viewGroup,false);
        ViewHolder viewHolder  = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        LecturerModel lecturerModel = lecturerModels.get(i);

        lecturerModel.setImageBitMap(viewHolder.logo,lecturerModel.getImage());
        viewHolder.user.setText(lecturerModel.getName());
        viewHolder.email.setText(lecturerModel.getEmail());
        viewHolder.trans.setId(lecturerModel.getId());

    }

    @Override
    public int getItemCount() {
        return lecturerModels.size();
    }

}

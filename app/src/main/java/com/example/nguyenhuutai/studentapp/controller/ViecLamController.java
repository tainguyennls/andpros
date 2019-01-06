package com.example.nguyenhuutai.studentapp.controller;

import android.content.Context;
import android.widget.ListView;

import com.example.nguyenhuutai.studentapp.adapters.ViecLamAdapter;
import com.example.nguyenhuutai.studentapp.interfaces.ICareer;
import com.example.nguyenhuutai.studentapp.model.JobModel;

import java.util.List;

public class ViecLamController {

    private JobModel viecLamModel;
    private ViecLamAdapter viecLamAdapter;
    private Context context;
    private int resource;

    public ViecLamController(Context context, int resource){
        viecLamModel  = new JobModel();
        this.context = context;
        this.resource = resource;
    }

    public void render(final ListView lvCareer){
        viecLamModel.getListOfCareers(new ICareer() {
            @Override
            public void call(List<JobModel> viecLamModels) {
                viecLamAdapter  = new ViecLamAdapter(context,resource,viecLamModels);
                lvCareer.setAdapter(viecLamAdapter);
            }
        });
    }

}

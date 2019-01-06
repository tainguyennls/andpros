package com.example.nguyenhuutai.studentapp.controller;

import android.content.Context;
import android.widget.ListView;

import com.example.nguyenhuutai.studentapp.adapters.JobAdapter;
import com.example.nguyenhuutai.studentapp.interfaces.ICareer;
import com.example.nguyenhuutai.studentapp.model.JobModel;

import java.util.List;

public class JobController {

    private JobModel jobModel;
    private JobAdapter jobAdapter;
    private Context context;
    private int resource;

    public JobController(Context context, int resource){
        jobModel  = new JobModel();
        this.context = context;
        this.resource = resource;
    }

    public void render(final ListView lvCareer){
        jobModel.getListOfCareers(new ICareer() {
            @Override
            public void call(List<JobModel> viecLamModels) {
                jobAdapter = new JobAdapter(context,resource,viecLamModels);
                lvCareer.setAdapter(jobAdapter);
            }
        });
    }

}

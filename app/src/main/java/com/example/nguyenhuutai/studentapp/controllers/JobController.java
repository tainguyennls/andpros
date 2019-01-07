package com.example.nguyenhuutai.studentapp.controllers;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.example.nguyenhuutai.studentapp.adapters.JobAdapter;
import com.example.nguyenhuutai.studentapp.interfaces.ICareer;
import com.example.nguyenhuutai.studentapp.models.JobModel;

import java.util.ArrayList;
import java.util.List;

public class JobController {

    private JobModel jobModel;
    private JobAdapter jobAdapter;
    private Context context;
    private int resource;
    private List<JobModel> jobModelList;

    public JobController(Context context, int resource){
        jobModel  = new JobModel();
        jobModelList = new ArrayList<>();
        this.context = context;
        this.resource = resource;
    }


}

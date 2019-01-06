package com.example.nguyenhuutai.studentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.nguyenhuutai.studentapp.controller.JobController;

public class ViecLamActivity extends AppCompatActivity {

    private JobController jobController;
    private ListView lvCareer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        lvCareer = findViewById(R.id.lv_Career);

        jobController = new JobController(this,R.id.lv_Career);
        jobController.render(lvCareer);
    }
}

package com.example.nguyenhuutai.studentapp.views;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nguyenhuutai.studentapp.JobDetailActivity;
import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.adapters.JobAdapter;
import com.example.nguyenhuutai.studentapp.dao.PrepareData;
import com.example.nguyenhuutai.studentapp.interfaces.ICareer;
import com.example.nguyenhuutai.studentapp.models.JobModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private ListView lvCareer;
    private List<JobModel> jobModels;
    private PrepareData prepareData;
    private JobAdapter jobAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Việc làm");
        actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0274BD")));

        jobModels = new ArrayList<>();
        prepareData = new PrepareData();
        lvCareer = findViewById(R.id.lv_Career);
        lvCareer.setOnItemClickListener(this);

       render();

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,JobDetailActivity.class);

        JobModel jobModel  = jobModels.get(position);

        intent.putExtra("job_header",jobModel.getTitle());
        intent.putExtra("job_time",jobModel.getTime());
        intent.putExtra("job_age",jobModel.getAge());
        intent.putExtra("job_degree",jobModel.getDegree());
        intent.putExtra("job_exper",jobModel.getExper());
        intent.putExtra("job_gender",jobModel.getGender());
        intent.putExtra("job_deadline",jobModel.getDeadline());
        intent.putExtra("job_location",jobModel.getLocationWork());
        intent.putExtra("job_position",jobModel.getPosition());
        intent.putExtra("job_salary",jobModel.getSalary());
        intent.putExtra("job_time_start",jobModel.getTimeStart());
        intent.putExtra("job_time_trial",jobModel.getTimeTrial());
        intent.putExtra("job_contact",jobModel.getContact());
        intent.putExtra("job_require",jobModel.getRequirement());
        intent.putExtra("job_description",jobModel.getDescriptionWork());
        intent.putExtra("job_benefit",jobModel.getBenefit());

        startActivity(intent);

        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
    }

    public void getListOfCareers(final ICareer iCareer){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot sh : dataSnapshot.getChildren()){
                    JobModel jobModel = sh.getValue(JobModel.class);
                    jobModels.add(jobModel);
                }
                iCareer.call(jobModels);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        prepareData.moveToNode("careers").addListenerForSingleValueEvent(valueEventListener);
    }

    public void render(){
        getListOfCareers(new ICareer() {
            @Override
            public void call(List<JobModel> jobModels) {
                jobAdapter = new JobAdapter(JobActivity.this,R.id.lv_Career,jobModels);
                lvCareer.setAdapter(jobAdapter);
            }
        });
    }
}

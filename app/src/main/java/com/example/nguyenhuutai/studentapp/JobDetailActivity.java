package com.example.nguyenhuutai.studentapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.models.JobModel;

public class JobDetailActivity extends AppCompatActivity {
    private TextView txtHeader;
    private TextView txtTime;
    private TextView txtAge;
    private TextView txtDegree;
    private TextView txtExper;
    private TextView txtGender;
    private TextView txtDeadline;
    private TextView txtLocation;
    private TextView txtPosition;
    private TextView txtSalary;
    private TextView txtTimeStart;
    private TextView txtTimeTrial;
    private TextView txtContact;
    private TextView txtRequire;
    private TextView txtDescript;
    private TextView txtBenefit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Việc làm");
        actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0274BD")));

        Intent intent = getIntent();

        txtHeader = findViewById(R.id.header_name_req);
        txtTime = findViewById(R.id.job_timer);
        txtAge = findViewById(R.id.job_age);
        txtDegree = findViewById(R.id.job_qualify);
        txtExper = findViewById(R.id.job_exper);
        txtGender = findViewById(R.id.job_gender);
        txtDeadline = findViewById(R.id.job_deadline);
        txtLocation = findViewById(R.id.job_location);
        txtPosition = findViewById(R.id.job_position);
        txtSalary = findViewById(R.id.job_salary);
        txtTimeStart = findViewById(R.id.job_time_start);
        txtTimeTrial = findViewById(R.id.job_time_trial);
        txtContact = findViewById(R.id.job_contact);
        txtRequire = findViewById(R.id.job_requirement);
        txtDescript  = findViewById(R.id.job_description);
        txtBenefit = findViewById(R.id.job_benefit);

        txtHeader.setText(intent.getStringExtra("job_header"));
        txtTime.setText(intent.getStringExtra("job_time"));
        txtAge.setText(intent.getStringExtra("job_age"));
        txtDegree.setText(intent.getStringExtra("job_degree"));
        txtExper.setText(intent.getStringExtra("job_exper"));
        txtGender.setText(intent.getStringExtra("job_gender"));
        txtDeadline.setText(intent.getStringExtra("job_deadline"));
        txtLocation.setText(intent.getStringExtra("job_location"));
        txtPosition.setText(intent.getStringExtra("job_position"));
        txtSalary.setText(intent.getStringExtra("job_salary"));
        txtTimeStart.setText(intent.getStringExtra("job_time_start"));
        txtTimeTrial.setText(intent.getStringExtra("job_time_trial"));
        txtContact.setText(intent.getStringExtra("job_contact"));
        txtRequire.setText(intent.getStringExtra("job_require"));
        txtDescript.setText(intent.getStringExtra("job_description"));
        txtBenefit.setText(intent.getStringExtra("job_benefit"));

    }
}

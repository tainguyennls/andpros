package com.example.nguyenhuutai.studentapp.views;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nguyenhuutai.studentapp.JobDetailActivity;
import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.controllers.JobController;
import com.google.firebase.database.collection.LLRBNode;

public class JobActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private JobController jobController;
    private ListView lvCareer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Việc làm");
        actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0274BD")));

        lvCareer = findViewById(R.id.lv_Career);
        lvCareer.setOnItemClickListener(this);

        jobController = new JobController(this,R.id.lv_Career);
        jobController.render(lvCareer);
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
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
    }
}

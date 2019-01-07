package com.example.nguyenhuutai.studentapp.views;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.R;

public class TeachDetailActivity extends AppCompatActivity {

    private TextView txtName,txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_day_hoc);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String content = intent.getStringExtra("content");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(name);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0274BD")));

        txtName = findViewById(R.id.title_dis);
        txtContent =  findViewById(R.id.content_d);

        txtName.setText(name);
        txtContent.setText(content);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

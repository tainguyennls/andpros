package com.example.nguyenhuutai.studentapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.R;

public class DetailDayHocActivity extends AppCompatActivity {

    private TextView txtName,txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_day_hoc);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String content = intent.getStringExtra("content");

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

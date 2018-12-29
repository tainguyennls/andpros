package com.example.nguyenhuutai.studentapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.R;

public class DetailDoanHoiActivity extends AppCompatActivity {

    private TextView txtName,txtDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_doan_hoi);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String des = intent.getStringExtra("descript");

        txtName = findViewById(R.id.name);
        txtDes = findViewById(R.id.des);

        txtName.setText(name);
        txtDes.setText(des);
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
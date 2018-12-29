package com.example.nguyenhuutai.studentapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.model.DoanHoiModel;

public class DetailDoanHoiActivity extends AppCompatActivity {

    private TextView txtName,txtDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_doan_hoi);

        Intent intent = getIntent();
        DoanHoiModel doanHoiModel = (DoanHoiModel) intent.getSerializableExtra("ass");

        txtName = findViewById(R.id.name);
        txtDes = findViewById(R.id.des);

        txtName.setText(doanHoiModel.getName());
        txtDes.setText(doanHoiModel.getDescsipt());
    }
}

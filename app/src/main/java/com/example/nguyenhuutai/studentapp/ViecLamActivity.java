package com.example.nguyenhuutai.studentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.nguyenhuutai.studentapp.controller.ViecLamController;

public class ViecLamActivity extends AppCompatActivity {

    private ViecLamController viecLamController;
    private ListView lvCareer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viec_lam);

        lvCareer = findViewById(R.id.lv_Career);

        viecLamController = new ViecLamController(this,R.id.lv_Career);
        viecLamController.render(lvCareer);
    }
}

package com.example.nguyenhuutai.studentapp.views;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.R;

public class GroupDetailActivity extends AppCompatActivity {

    private TextView txtName,txtDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_detail_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Hoạt động đoàn và hội");
        actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0274BD")));

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}

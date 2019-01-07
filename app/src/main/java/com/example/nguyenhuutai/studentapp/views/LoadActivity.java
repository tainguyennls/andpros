package com.example.nguyenhuutai.studentapp.views;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.nguyenhuutai.studentapp.R;

public class LoadActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadActivity.this,NavActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            }
        },1000);
// 5
    }
}



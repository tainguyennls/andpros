package com.example.nguyenhuutai.studentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nguyenhuutai.studentapp.adapters.DayHocAdapter;
import com.example.nguyenhuutai.studentapp.model.DayHocModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DayHocActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<DayHocModel> dayHocModels;
    private ListView lvDayHoc;
    private DatabaseReference df;
    private DayHocAdapter dayHocAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_hoc);

        df = FirebaseDatabase.getInstance().getReference().child("teachs");
        dayHocModels = new ArrayList<>();
        lvDayHoc = findViewById(R.id.lv_DayHoc);
        lvDayHoc.setOnItemClickListener(this);

        render();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent =  new Intent(DayHocActivity.this,DetailDayHocActivity.class);
        intent.putExtra("name",dayHocModels.get(position).getName());
        intent.putExtra("content",dayHocModels.get(position).getContent());
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
    }

    public interface OnCall{
        void call(List<DayHocModel> dayHocModels);
    }

    public void getDoanHoi(final OnCall onCall){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot sh : dataSnapshot.getChildren()){
                    DayHocModel dayHocModel = sh.getValue(DayHocModel.class);
                    dayHocModels.add(dayHocModel);
                }
                onCall.call(dayHocModels);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        df.addListenerForSingleValueEvent(valueEventListener);
    }

    public void render(){
        getDoanHoi(new OnCall() {
            @Override
            public void call(List<DayHocModel> dayHocModels) {
                dayHocAdapter = new DayHocAdapter(DayHocActivity.this,R.id.lv_DayHoc,dayHocModels);
                lvDayHoc.setAdapter(dayHocAdapter);
            }
        });
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

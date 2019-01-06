package com.example.nguyenhuutai.studentapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.adapters.DayHocAdapter;
import com.example.nguyenhuutai.studentapp.interfaces.ITeach;
import com.example.nguyenhuutai.studentapp.model.TeachModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DayHocActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<TeachModel> teachModels;
    private ListView lvDayHoc;
    private DatabaseReference df;
    private DayHocAdapter dayHocAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_hoc);

        df = FirebaseDatabase.getInstance().getReference().child("teachs");
        teachModels = new ArrayList<>();
        lvDayHoc = findViewById(R.id.lv_DayHoc);
        lvDayHoc.setOnItemClickListener(this);

        render();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent =  new Intent(DayHocActivity.this,DetailDayHocActivity.class);
        intent.putExtra("name", teachModels.get(position).getName());
        intent.putExtra("content", teachModels.get(position).getContent());
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
    }


    public void getDoanHoi(final ITeach iTeach){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot sh : dataSnapshot.getChildren()){
                    TeachModel teachModel = sh.getValue(TeachModel.class);
                    teachModels.add(teachModel);
                }
                iTeach.call(teachModels);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        df.addListenerForSingleValueEvent(valueEventListener);
    }

    public void render(){
        getDoanHoi(new ITeach() {
            @Override
            public void call(List<TeachModel> teachModels) {
                dayHocAdapter = new DayHocAdapter(DayHocActivity.this,R.id.lv_DayHoc, teachModels);
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

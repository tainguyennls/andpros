package com.example.nguyenhuutai.studentapp.views;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.adapters.LecturerAdapter;
import com.example.nguyenhuutai.studentapp.dao.Data;
import com.example.nguyenhuutai.studentapp.interfaces.ILecturer;
import com.example.nguyenhuutai.studentapp.models.LecturerModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LecturerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lv_Lecturer;
    private List<LecturerModel> lecturers;
    private LecturerAdapter listAdapter;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Giảng viên");
        actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0274BD")));

        data = new Data();
        lecturers = new ArrayList<>();
        lv_Lecturer = findViewById(R.id.lstv_Lecturer);
        lv_Lecturer.setOnItemClickListener(this);

        render();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent  intent = new Intent(LecturerActivity.this,LecturerDetailActivity.class);
        intent.putExtra("id_details",view.getId()+"");
        intent.putExtra("name",lecturers.get(position).getName());
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
        //Toast.makeText(LecturerActivity.this,"Id : " + view.getId(),Toast.LENGTH_SHORT).show();
    }


    /**
     *  Get all Lecturer in database
     * @param iLecturer callback invoke
     */

    public void getListOfLecturers(final ILecturer iLecturer){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.getChildren()) {
                    LecturerModel lecturer = shot.getValue(LecturerModel.class);
                    lecturers.add(lecturer);
                }
                iLecturer.call(lecturers);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        data.moveToNode("lecturers").addListenerForSingleValueEvent(valueEventListener);
    }


    /***
     *  Render view
     */

    public void render(){
        getListOfLecturers(new ILecturer() {
            @Override
            public void call(List<LecturerModel> lecturerModels) {
                listAdapter = new LecturerAdapter(LecturerActivity.this,R.id.lstv_Lecturer,lecturers);
                lv_Lecturer.setAdapter(listAdapter);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

}

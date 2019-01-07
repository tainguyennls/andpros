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
import android.widget.Toast;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.adapters.LecturerAdapter;
import com.example.nguyenhuutai.studentapp.interfaces.ILecturer;
import com.example.nguyenhuutai.studentapp.models.LecturerModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LecturerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lstv_Lecturer;
    private DatabaseReference df;
    private List<LecturerModel> lecturers;
    private LecturerAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Giảng viên");
        actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0274BD")));

        lecturers = new ArrayList<>();
        df = FirebaseDatabase.getInstance().getReference();

        lstv_Lecturer = findViewById(R.id.lstv_Lecturer);
        lstv_Lecturer.setOnItemClickListener(this);

        render();


        df.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // No change ...
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot,String s) {
                lecturers.removeAll(lecturers);
                render();
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                lecturers.removeAll(lecturers);
                render();
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot,String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent  intent = new Intent(LecturerActivity.this,DetailLecturerActivity.class);
        intent.putExtra("id_details",view.getId()+"");
        intent.putExtra("name",lecturers.get(position).getName());
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
        Toast.makeText(LecturerActivity.this,"Id : " + view.getId(),Toast.LENGTH_SHORT).show();
    }


    /**
     *  Get all Lecturer in database
     * @param iLecturer callback invoke
     */

    public void getListOfLecturer(final ILecturer iLecturer){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot snapshot  = dataSnapshot.child("lecturers");
                for(DataSnapshot shot : snapshot.getChildren()) {
                    LecturerModel lecturer = shot.getValue(LecturerModel.class);
                    lecturers.add(lecturer);
                }
                iLecturer.call(lecturers);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        df.addListenerForSingleValueEvent(valueEventListener);
    }


    /***
     *  Render view
     */

    public void render(){
        getListOfLecturer(new ILecturer() {
            @Override
            public void call(List<LecturerModel> lecturerModels) {
                Collections.sort(lecturers);
                listAdapter = new LecturerAdapter(LecturerActivity.this,R.id.lstv_Lecturer,lecturers);
                lstv_Lecturer.setAdapter(listAdapter);
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

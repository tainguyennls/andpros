package com.example.nguyenhuutai.studentapp.views;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.adapters.LecturerAdapter;
import com.example.nguyenhuutai.studentapp.dao.Data;
import com.example.nguyenhuutai.studentapp.interfaces.ILecturer;
import com.example.nguyenhuutai.studentapp.models.CheckNetwork;
import com.example.nguyenhuutai.studentapp.models.DialogShow;
import com.example.nguyenhuutai.studentapp.models.ItemOffsetDecoration;
import com.example.nguyenhuutai.studentapp.models.LecturerModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LecturerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<LecturerModel> lecturers;
    private LecturerAdapter listAdapter;
    private Data data;
    private CheckNetwork checkNetwork;
    private DialogShow dialogShow;

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
        recyclerView = findViewById(R.id.lstv_Lecturer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(1);
        recyclerView.addItemDecoration(itemDecoration);
        checkNetwork = new CheckNetwork();

        dialogShow = new DialogShow(this);
        if(!checkNetwork.checkNetworkState(this)){
            dialogShow.showDialog();
        }

        render();

    }

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

    public void render(){
        getListOfLecturers(new ILecturer() {
            @Override
            public void call(List<LecturerModel> lecturerModels) {
                listAdapter = new LecturerAdapter(lecturers);
                recyclerView.setAdapter(listAdapter);
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

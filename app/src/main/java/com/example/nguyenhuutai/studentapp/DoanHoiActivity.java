package com.example.nguyenhuutai.studentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nguyenhuutai.studentapp.adapters.DoanHoiAdapter;
import com.example.nguyenhuutai.studentapp.model.DoanHoiModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DoanHoiActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<DoanHoiModel> doanHoiModels;
    private ListView lvDoanHoi;
    private DatabaseReference df;
    private DoanHoiAdapter doanHoiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doan_hoi);

        df = FirebaseDatabase.getInstance().getReference().child("assocs");
        doanHoiModels = new ArrayList<>();
        lvDoanHoi = findViewById(R.id.association);

        render();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent =  new Intent();
        intent.putExtra("ass",doanHoiModels.get(position)+"");
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
    }

    public interface OnCall{
        void call(List<DoanHoiModel> doanHoiModels);
    }

    public void getDoanHoi(final OnCall onCall){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot sh : dataSnapshot.getChildren()){
                    DoanHoiModel doanHoiModel = sh.getValue(DoanHoiModel.class);
                    doanHoiModels.add(doanHoiModel);
                }
                onCall.call(doanHoiModels);
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
            public void call(List<DoanHoiModel> doanHoiModels) {
                doanHoiAdapter = new DoanHoiAdapter(DoanHoiActivity.this,R.id.association,doanHoiModels);
                lvDoanHoi.setAdapter(doanHoiAdapter);
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

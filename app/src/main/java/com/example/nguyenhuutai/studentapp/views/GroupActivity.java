package com.example.nguyenhuutai.studentapp.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.adapters.GroupAdapter;
import com.example.nguyenhuutai.studentapp.interfaces.IGroup;
import com.example.nguyenhuutai.studentapp.models.GroupModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GroupActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<GroupModel> groupModels;
    private ListView lvDoanHoi;
    private DatabaseReference df;
    private GroupAdapter groupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doan_hoi);

        df = FirebaseDatabase.getInstance().getReference().child("assocs");
        groupModels = new ArrayList<>();
        lvDoanHoi = findViewById(R.id.association);
        lvDoanHoi.setOnItemClickListener(this);

        render();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent =  new Intent(GroupActivity.this,DetailDoanHoiActivity.class);
        intent.putExtra("name", groupModels.get(position).getName());
        intent.putExtra("descript", groupModels.get(position).getDescript());
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
    }

    public interface OnCall{
        void call(List<GroupModel> groupModels);
    }

    public void getDoanHoi(final IGroup iGroup){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot sh : dataSnapshot.getChildren()){
                    GroupModel groupModel = sh.getValue(GroupModel.class);
                    groupModels.add(groupModel);
                }
                iGroup.call(groupModels);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        df.addListenerForSingleValueEvent(valueEventListener);
    }

    public void render(){
        getDoanHoi(new IGroup() {
            @Override
            public void call(List<GroupModel> groupModels) {
                groupAdapter = new GroupAdapter(GroupActivity.this,R.id.association, groupModels);
                lvDoanHoi.setAdapter(groupAdapter);
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

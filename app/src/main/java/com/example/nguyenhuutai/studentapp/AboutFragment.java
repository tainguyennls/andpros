package com.example.nguyenhuutai.studentapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.dao.Data;
import com.example.nguyenhuutai.studentapp.models.LecturerModel;
import com.example.nguyenhuutai.studentapp.models.StringItem;
import com.example.nguyenhuutai.studentapp.models.UlTagHandler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends Fragment {

    private Data data;
    private EditText txtName;
    private EditText txtNamSinh;
    private EditText txtChucVu;
    private EditText txtEmail;
    private TextView htmList;
    private Button btnUpdate;
    private Button btnSave;
    private FirebaseAuth fa;
    private FirebaseUser fu;
    private DatabaseReference pos;
    private List <StringItem> topics;


    public AboutFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        topics = new ArrayList<>();
        data = new Data();
        fa = FirebaseAuth.getInstance();
        fu = fa.getCurrentUser();

        txtName = v.findViewById(R.id.name);
        txtNamSinh = v.findViewById(R.id.dat);
        txtChucVu = v.findViewById(R.id.pos);
        txtEmail = v.findViewById(R.id.email);
        htmList = v.findViewById(R.id.htmList);
        btnUpdate = v.findViewById(R.id.update);
        btnSave = v.findViewById(R.id.save);

        pos = FirebaseDatabase.getInstance().getReference();

        render();

        disableUpdate();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableUpdate();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
                disableUpdate();
            }
        });

        return v;
    }

    public interface OnCallback{
        void callback(LecturerModel lecturerModel);
    }

    public interface CallbackTopic{
        void callback(List<StringItem> topics);
    }

    public void getLecturerById(final OnCallback onCallback){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot snapshot  = dataSnapshot.child(fu.getUid());
                LecturerModel lecturerModel = snapshot.getValue(LecturerModel.class);
                onCallback.callback(lecturerModel);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        data.moveToNode("lecturers").addListenerForSingleValueEvent(valueEventListener);
    }

    public void getTopicsById(final CallbackTopic callback){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot snapshot  = dataSnapshot.child(fu.getUid()).child("topics");
                for (DataSnapshot sh : snapshot.getChildren()){
                    StringItem topic = sh.getValue(StringItem.class);
                    topics.add(topic);
                }
                callback.callback(topics);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        data.moveToNode("lecturers").addListenerForSingleValueEvent(valueEventListener);
    }

    public void render(){
        getLecturerById(new OnCallback() {

            @Override
            public void callback(final LecturerModel lecturerModels) {

                txtName.setText(lecturerModels.getName());
                txtNamSinh.setText(lecturerModels.getBorn());
                txtChucVu.setText(lecturerModels.getPosition());
                txtEmail.setText(lecturerModels.getEmail());

                getTopicsById(new CallbackTopic() {
                    @Override
                    public void callback(List<StringItem> topics) {
                        lecturerModels.setTopic(topics);
                        String str = "<ul>";
                        for (int i = 0 ; i < topics.size() ; i++){
                            str+= "<li>" + topics.get(i).getName()+"</li>";
                        }
                        str+= "</ul>";
                        htmList.setText(Html.fromHtml(str,null,new UlTagHandler()));
                    }
                });
            }
        });
    }

    public void disableUpdate(){
        txtName.setEnabled(false);
        txtNamSinh.setEnabled(false);
        txtEmail.setEnabled(false);
        txtChucVu.setEnabled(false);

        txtName.setTextColor(Color.BLACK);
        txtNamSinh.setTextColor(Color.BLACK);
        txtChucVu.setTextColor(Color.BLACK);
        txtEmail.setTextColor(Color.BLACK);

        btnUpdate.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);

    }

    public void enableUpdate(){
        txtName.setEnabled(true);
        txtNamSinh.setEnabled(true);
        txtEmail.setEnabled(true);
        txtChucVu.setEnabled(true);
        btnUpdate.setVisibility(View.GONE);
        btnSave.setVisibility(View.VISIBLE);
    }

    public void updateData(){
        pos.child("lecturers").child(fu.getUid()).child("name").setValue(txtName.getText().toString());
        pos.child("lecturers").child(fu.getUid()).child("email").setValue(txtEmail.getText().toString());
        pos.child("lecturers").child(fu.getUid()).child("position").setValue(txtChucVu.getText().toString());
        pos.child("lecturers").child(fu.getUid()).child("born").setValue(txtNamSinh.getText().toString());
    }
}

package com.example.nguyenhuutai.studentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.nguyenhuutai.studentapp.model.LecturerModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DetailLecturerActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView txtName,txtNamsinh,txtChucvu,txtEmail;
    private int id;
    private DatabaseReference df;
    private List<String> topics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lecturer);

        df = FirebaseDatabase.getInstance().getReference();
        topics = new ArrayList<>();

        imageView = findViewById(R.id.img_details);
        txtName = findViewById(R.id.name);
        txtNamsinh  = findViewById(R.id.dat);
        txtChucvu = findViewById(R.id.pos);
        txtEmail = findViewById(R.id.email);

        Intent intent = getIntent();

        id = Integer.parseInt(intent.getStringExtra("id_details"));

       render();

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


    /**
     *  On Callback ....
     */

    public interface OnCallback{
        void callback(LecturerModel lecturerModel);
    }

    public interface CallbackTopic{
        void callback(List<String> topics);
    }

    public void getLecturerById(final OnCallback onCallback){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot snapshot  = dataSnapshot.child("lecturers").child(id+"");
                LecturerModel lecturerModel = snapshot.getValue(LecturerModel.class);
                onCallback.callback(lecturerModel);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        df.addListenerForSingleValueEvent(valueEventListener);
    }

    public void getTopicsById(final CallbackTopic callback){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot snapshot  = dataSnapshot.child("lecturers").child(id+"").child("topics");
                for (DataSnapshot sh : snapshot.getChildren()){
                    String topic = sh.getValue(String.class);
                    topics.add(topic);
                }
                callback.callback(topics);

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
       getLecturerById(new OnCallback() {

           @Override
           public void callback(final LecturerModel lecturerModels) {

               lecturerModels.setImageBitMap(imageView,lecturerModels.getImage());

               txtName.setText(lecturerModels.getName());
               txtNamsinh.setText(lecturerModels.getBorn());
               txtChucvu.setText(lecturerModels.getPosition());
               txtEmail.setText(lecturerModels.getEmail());

               getTopicsById(new CallbackTopic() {
                   @Override
                   public void callback(List<String> topics) {
                       //lecturerModels.setTopic(topics);
                       Log.e("LECTURER","LEC: " + topics.toString());
                   }
               });

//               Log.e("LECTURER",lecturerModels.getTopic().size() + "");
           }
       });
    }
}

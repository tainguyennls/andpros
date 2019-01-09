package com.example.nguyenhuutai.studentapp.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.nguyenhuutai.studentapp.R;
import com.example.nguyenhuutai.studentapp.dao.Data;
import com.example.nguyenhuutai.studentapp.models.LecturerModel;
import com.example.nguyenhuutai.studentapp.models.StringItem;
import com.example.nguyenhuutai.studentapp.models.UlTagHandler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class LecturerDetailActivity extends AppCompatActivity {

    private String uid;
    private ImageView logo;
    private TextView txtName;
    private TextView txtNamSinh;
    private TextView txtChucVu;
    private TextView txtEmail;
    private TextView txtLogoName;
    private TextView htmList;
    private ImageView prev;

    private Data data;
    private List<StringItem> topics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lecturer);

        data = new Data();
        topics = new ArrayList<>();

        logo = findViewById(R.id.logo);
        txtName = findViewById(R.id.name);
        txtNamSinh = findViewById(R.id.dat);
        txtChucVu = findViewById(R.id.pos);
        txtEmail = findViewById(R.id.email);
        txtLogoName = findViewById(R.id.logo_name);
        htmList = findViewById(R.id.htmList);
        prev = findViewById(R.id.prev);

        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");

        render();

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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


    /**
     *  On Callback ....
     */

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
                DataSnapshot snapshot  = dataSnapshot.child(uid);
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
                DataSnapshot snapshot  = dataSnapshot.child(uid).child("topics");
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

               lecturerModels.setImageBitMap(logo,lecturerModels.getImage());

               txtName.setText(lecturerModels.getName());
               txtNamSinh.setText(lecturerModels.getBorn());
               txtChucVu.setText(lecturerModels.getPosition());
               txtEmail.setText(lecturerModels.getEmail());
               txtLogoName.setText(lecturerModels.getName());

               getTopicsById(new CallbackTopic() {
                   @Override
                   public void callback(List<StringItem> topics) {
                       lecturerModels.setTopic(topics);
                       String str = "<ul>";
                       for (int i = 0 ; i < topics.size() ; i++){
                           str+= "<li>" + topics.get(i).getName()+"</li>";
                       }
                       str+= "</ul>";
                       Log.e("BUGS",str);
                       htmList.setText(Html.fromHtml(str,null,new UlTagHandler()));
                   }
               });
           }
       });
    }
}

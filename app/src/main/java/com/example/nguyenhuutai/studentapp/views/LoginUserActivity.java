package com.example.nguyenhuutai.studentapp.views;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyenhuutai.studentapp.adapters.FragmentUserProfile;
import com.example.nguyenhuutai.studentapp.models.LecturerModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.nguyenhuutai.studentapp.R;


public class LoginUserActivity extends AppCompatActivity{

    private FirebaseAuth fa;
    private FirebaseUser user ;
    private DatabaseReference df;
    private TextView txtName;
    private TextView txtLogout;
    private ImageView imgPersonal;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        txtName = findViewById(R.id.logo_name);
        imgPersonal = findViewById(R.id.img_profile);
        tabLayout= findViewById(R.id.tab_user);
        viewPager = findViewById(R.id.pager_user);
        txtLogout=  findViewById(R.id.logout);
        viewPager.setAdapter(new FragmentUserProfile(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        fa = FirebaseAuth.getInstance();
        user = fa.getCurrentUser();

        df = FirebaseDatabase.getInstance().getReference("lecturers");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot snapshot = dataSnapshot.child(user.getUid());
                LecturerModel lecturerModel = snapshot.getValue(LecturerModel.class);
                txtName.setText(lecturerModel.getName());
                lecturerModel.setImageBitMap(imgPersonal,lecturerModel.getImage());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        df.addListenerForSingleValueEvent(valueEventListener);

        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(LoginUserActivity.this,NavActivity.class));
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
    }
}

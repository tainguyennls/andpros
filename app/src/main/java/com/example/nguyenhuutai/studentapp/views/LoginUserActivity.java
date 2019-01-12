package com.example.nguyenhuutai.studentapp.views;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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
        viewPager.setAdapter(new FragmentUserProfile(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        fa = FirebaseAuth.getInstance();
        user = fa.getCurrentUser();

        df = FirebaseDatabase.getInstance().getReference("lecturers");

        render();
    }



    public interface Call{
        void call(LecturerModel lecturerModel);
    }

    public void getLecturerModel(final Call call){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot snapshot = dataSnapshot.child(user.getUid());
                 LecturerModel lecturerModel = snapshot.getValue(LecturerModel.class);
                 call.call(lecturerModel);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // catch error ...
            }
        };
        df.addListenerForSingleValueEvent(valueEventListener);
    }

    public void render(){
        getLecturerModel(new Call() {
            @Override
            public void call(LecturerModel lecturerModel) {
                txtName.setText(lecturerModel.getName());
                lecturerModel.setImageBitMap(imgPersonal,lecturerModel.getImage());
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}

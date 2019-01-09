package com.example.nguyenhuutai.studentapp.views;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenhuutai.studentapp.models.LecturerModel;
import com.example.nguyenhuutai.studentapp.models.NewsModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.nguyenhuutai.studentapp.R;

import java.util.Date;

public class LoginUserActivity extends AppCompatActivity implements ValueEventListener {

    private TextView txtName,txtChange;
    private ImageView imgPersonal;
    private FirebaseUser user ;
    private Date date;
    private FirebaseAuth fa;
    private DatabaseReference df;
    private DatabaseReference pos;
    private Button btnPost;
    private EditText editText;
    private LecturerModel lecturerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        txtName = findViewById(R.id.logo_name);
        txtChange = findViewById(R.id.fix);
        imgPersonal = findViewById(R.id.img_profile);
        btnPost  = findViewById(R.id.pos);
        editText = findViewById(R.id.content_pos);

        fa = FirebaseAuth.getInstance();
        user = fa.getCurrentUser();


        txtChange.setText(Html.fromHtml("<u> Sửa thông tin </u>"));
        df = FirebaseDatabase.getInstance().getReference("lecturers").child(user.getUid()+"");
        pos = FirebaseDatabase.getInstance().getReference("news");
        df.addValueEventListener(this);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().trim().length() != 0){
                    uploadData();
                    editText.setText("");
                }
                else{
                    Toast.makeText(LoginUserActivity.this,"Nhập nội dung trước khi đăng !",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        lecturerModel = dataSnapshot.getValue(LecturerModel.class);
        txtName.setText(lecturerModel.getName());
        lecturerModel.setImageBitMap(imgPersonal,lecturerModel.getImage());
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
    }

    public void uploadData(){
        NewsModel newsModel = new NewsModel();
        date = new Date();

        newsModel.setUser(lecturerModel.getName());
        newsModel.setContent(editText.getText().toString());
        newsModel.setUid(user.getUid());
        newsModel.setTime(date.getTime() +"." + date.getDay() + "/" + date.getMonth() + "/" + date.getYear());

        pos.child(newsModel.getTime()).setValue(newsModel);
    }
}

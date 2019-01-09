package com.example.nguyenhuutai.studentapp.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
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

import java.text.DateFormat;
import java.util.Date;

public class LoginUserActivity extends AppCompatActivity{

    private FirebaseAuth fa;
    private FirebaseUser user ;
    private DatabaseReference df;
    private DatabaseReference pos;
    private Button btnPost;
    private EditText editText;
    private TextView txtName;
    private TextView txtChange;
    private ImageView imgPersonal;

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
        df = FirebaseDatabase.getInstance().getReference("lecturers");
        pos = FirebaseDatabase.getInstance().getReference("news");

        render();

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

    public void uploadData(){
        NewsModel newsModel = new NewsModel();
        Date date = new Date();
        String stringDate = DateFormat.getDateTimeInstance().format(date);

        newsModel.setUser(txtName.getText().toString());
        newsModel.setContent(editText.getText().toString());
        newsModel.setUid(user.getUid());
        newsModel.setTime(stringDate);

        pos.child(pos.push().getKey()).setValue(newsModel);
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
}

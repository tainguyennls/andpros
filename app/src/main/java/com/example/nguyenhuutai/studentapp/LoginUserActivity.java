package com.example.nguyenhuutai.studentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginUserActivity extends AppCompatActivity {

    private TextView txtName,txtChange;
    private ImageView imgPersonal;
    private FirebaseUser user ;
    private FirebaseAuth fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        txtName = findViewById(R.id.logo_name);
        txtChange = findViewById(R.id.fix);
        imgPersonal = findViewById(R.id.img_profile);

        fa = FirebaseAuth.getInstance();
        user = fa.getCurrentUser();


        txtName.setText(user.getUid());
        txtChange.setText(Html.fromHtml("<u> Sửa thông tin </u>"));

    }
}

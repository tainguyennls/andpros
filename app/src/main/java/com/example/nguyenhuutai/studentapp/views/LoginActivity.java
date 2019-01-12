package com.example.nguyenhuutai.studentapp.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.example.nguyenhuutai.studentapp.R;


public class LoginActivity extends AppCompatActivity {

    private TextView txtEmail, txtPassword;
    private TextView btnLogin;
    private FirebaseAuth fa;
    private String email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.pass);
        btnLogin = findViewById(R.id.lg);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(txtEmail, InputMethodManager.SHOW_IMPLICIT);
        imm.showSoftInput(txtPassword, InputMethodManager.SHOW_IMPLICIT);

        fa = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = fa.getCurrentUser();
       if(firebaseUser != null){
           startActivity(new Intent(LoginActivity.this,LoginUserActivity.class));
           overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
       }
    }

    public void signIn(){
        if(!checkEmp()){
            fa.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    startActivity(new Intent(LoginActivity.this,LoginUserActivity.class));
                    overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                }
            });
        }
    }

    public boolean checkEmp(){
        email = txtEmail.getText().toString();
        pass  = txtPassword.getText().toString();
        return email.trim().length() == 0 && pass.trim().length() == 0;
    }
}

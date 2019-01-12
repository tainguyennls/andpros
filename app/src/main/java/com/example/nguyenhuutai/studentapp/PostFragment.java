package com.example.nguyenhuutai.studentapp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nguyenhuutai.studentapp.models.NewsModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;


public class PostFragment extends Fragment{

    private Button btnPost;
    private Button btnClear;
    private EditText editText;
    private FirebaseAuth fa;
    private FirebaseUser user ;
    private DatabaseReference pos;
    private Spinner spinner;
    private Dialog dialog;
    private String [] options = {"news","teachs","careers"};
    private ArrayAdapter<CharSequence> adapter;

    public PostFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_post, container, false);;

        btnPost = v.findViewById(R.id.pos);
        btnClear = v.findViewById(R.id.clear);
        editText = v.findViewById(R.id.content_pos);
        spinner = v.findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(getContext(),R.array.spinner_ops,android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text  = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(),options[position],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        fa = FirebaseAuth.getInstance();
        user = fa.getCurrentUser();
        pos = FirebaseDatabase.getInstance().getReference("news");

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().trim().length() != 0){
                    uploadData();
                    editText.setText("");
                    showDialog(R.layout.success_post);
                }
                else{
                    showDialog(R.layout.empty_post_warning);
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

        return v;
    }

    public void uploadData(){
        NewsModel newsModel = new NewsModel();
        Date date = new Date();
        String stringDate = DateFormat.getDateTimeInstance().format(date);

        //newsModel.setUser(txtName.getText().toString());
        newsModel.setContent(editText.getText().toString());
        newsModel.setUid(user.getUid());
        newsModel.setTime(stringDate);

        pos.child(pos.push().getKey()).setValue(newsModel);
    }

    public void showDialog(int resource){
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        dialog.show();
    }

}

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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.nguyenhuutai.studentapp.models.LecturerModel;
import com.example.nguyenhuutai.studentapp.models.NewsModel;
import com.example.nguyenhuutai.studentapp.models.TeachModel;

import java.text.DateFormat;
import java.util.Date;


public class PostFragment extends Fragment{

    private Button btnPost;
    private Button btnClear;
    private EditText editText;
    private FirebaseAuth fa;
    private FirebaseUser user ;
    private DatabaseReference drin;
    private DatabaseReference pos;
    private Spinner spinner;
    private Dialog dialog;
    private String [] options = {"news","teachs"};
    private ArrayAdapter<CharSequence> adapter;
    private TextView txtTmp;
    private EditText header;
    private String tmpOp;
    private LinearLayout linearLayout;

    public PostFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_post, container, false);;

        btnPost = v.findViewById(R.id.pos);
        btnClear = v.findViewById(R.id.clear);
        editText = v.findViewById(R.id.content_pos);
        spinner = v.findViewById(R.id.spinner);
        txtTmp = new TextView(getContext());
        linearLayout = v.findViewById(R.id.options);
        header = v.findViewById(R.id.hear_title);
        adapter = ArrayAdapter.createFromResource(getContext(),R.array.spinner_ops,android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tmp = options[position];
                switch (tmp){
                    case "teachs" :
                        pos = FirebaseDatabase.getInstance().getReference("teachs");
                        linearLayout.setVisibility(LinearLayout.VISIBLE);
                        tmpOp = "teachs";
                        break;
                    case "news":
                        pos = FirebaseDatabase.getInstance().getReference("news");
                        linearLayout.setVisibility(LinearLayout.INVISIBLE);
                        tmpOp = "news";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        fa = FirebaseAuth.getInstance();
        user = fa.getCurrentUser();
        drin = FirebaseDatabase.getInstance().getReference("lecturers");

        pos = FirebaseDatabase.getInstance().getReference("news");

        getNameString(txtTmp);

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
        switch (tmpOp){
            case "teachs":
                TeachModel teachModel  = new TeachModel();
                teachModel.setContent(editText.getText().toString());
                teachModel.setName(header.getText().toString());
                teachModel.setUser(txtTmp.getText().toString());
                if(header.getText().toString().contains("nghỉ học")){
                    teachModel.setTag("off");
                }
                else{
                    teachModel.setTag("change");
                }

                pos.child(pos.push().getKey()).setValue(teachModel);
                break;
            case "news":
                NewsModel newsModel = new NewsModel();
                Date date = new Date();
                String stringDate = DateFormat.getDateTimeInstance().format(date);

                newsModel.setUser(txtTmp.getText().toString());
                newsModel.setContent(editText.getText().toString());
                newsModel.setUid(user.getUid());
                newsModel.setTime(stringDate);

                pos.child(pos.push().getKey()).setValue(newsModel);
            break;
        }
    }

    public void showDialog(int resource){
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        dialog.show();
    }

    public void getNameString(final TextView txt){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot snapshot = dataSnapshot.child(user.getUid());
                LecturerModel lecturerModel = snapshot.getValue(LecturerModel.class);
                txt.setText(lecturerModel.getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        drin.addListenerForSingleValueEvent(valueEventListener);
    }

}

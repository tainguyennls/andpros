package com.example.nguyenhuutai.studentapp.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PrepareData {

    private DatabaseReference df;

    public PrepareData(){
        df = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference moveToNode(String node){
        return df.child(node);
    }
}

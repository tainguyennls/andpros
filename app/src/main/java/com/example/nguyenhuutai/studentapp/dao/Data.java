package com.example.nguyenhuutai.studentapp.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Data {

    private DatabaseReference df;

    public Data(){
        df = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference moveToNode(String node){
        return df.child(node);
    }
}

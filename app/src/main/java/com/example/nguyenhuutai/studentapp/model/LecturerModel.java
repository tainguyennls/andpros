package com.example.nguyenhuutai.studentapp.model;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class LecturerModel implements  Comparable<LecturerModel> {

    private int id;
    private java.lang.String name;
    private java.lang.String email;
    private java.lang.String born;
    private java.lang.String position;
    private java.lang.String start;
    private java.lang.String image;
    private List <String> topic;
    private List <String> research;

    private FirebaseStorage storage;
    private StorageReference storageRef;


    public LecturerModel() {
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference().child("images");
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    public java.lang.String getBorn() {
        return born;
    }

    public void setBorn(java.lang.String born) {
        this.born = born;
    }

    public java.lang.String getPosition() {
        return position;
    }

    public void setPosition(java.lang.String position) {
        this.position = position;
    }

    public java.lang.String getStart() {
        return start;
    }

    public void setStart(java.lang.String start) {
        this.start = start;
    }

    public java.lang.String getImage() {
        return image;
    }

    public void setImage(java.lang.String image) {
        this.image = image;
    }

    public List<String> getTopic() {
        return topic;
    }

    public void setTopic(List<String> topic) {
        this.topic = topic;
    }

    public List<String> getResearch() {
        return research;
    }

    public void setResearch(List<String> research) {
        this.research = research;
    }


    @Override
    public java.lang.String toString() {
        return name;
    }

    @Override
    public int compareTo(LecturerModel o) {
        return this.id - o.id;
    }

    public void setImageBitMap(final ImageView imageView, java.lang.String image){
        storageRef.child(image).getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imageView.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                //Handle error
            }
        });
    }
}
package com.example.nguyenhuutai.studentapp.models;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class LecturerModel{

    private java.lang.String id;
    private java.lang.String name;
    private java.lang.String email;
    private java.lang.String born;
    private java.lang.String position;
    private java.lang.String start;
    private java.lang.String image;
    private List <StringItem> topic;
    private List <StringItem> research;

    private FirebaseStorage storage;
    private StorageReference storageRef;


    public LecturerModel() {
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference().child("images");
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public java.lang.String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public java.lang.String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<StringItem> getTopic() {
        return topic;
    }

    public void setTopic(List<StringItem> topic) {
        this.topic = topic;
    }

    public List<StringItem> getResearch() {
        return research;
    }

    public void setResearch(List<StringItem> research) {
        this.research = research;
    }


    @Override
    public String toString() {
        return name + this.research;
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

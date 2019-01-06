package com.example.nguyenhuutai.studentapp.model;

import com.example.nguyenhuutai.studentapp.dao.PrepareData;
import com.example.nguyenhuutai.studentapp.interfaces.ICareer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class JobModel {

    private String title;
    private String time;
    private String salary;
    private String degree;
    private String timeStart;
    private String gender;
    private String locationWork;
    private String descriptionWork;
    private String benefit;
    private String requirement;
    private String contact;
    private String exper;
    private String position;
    private String timeTrial;
    private String age;
    private String deadline;
    private PrepareData prepareData;
    private List<JobModel> viecLamModels;


    public JobModel(){

    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getGender() {
        return gender;
    }

    public String getBenefit() {
        return benefit;
    }

    public String getDegree() {
        return degree;
    }

    public String getDescriptionWork() {
        return descriptionWork;
    }

    public String getLocationWork() {
        return locationWork;
    }

    public String getSalary() {
        return salary;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getPosition() {
        return position;
    }

    public String getContact() {
        return contact;
    }

    public String getAge() {
        return age;
    }

    public String getExper() {
        return exper;
    }

    public String getRequirement() {
        return requirement;
    }

    public String getTimeTrial() {
        return timeTrial;
    }

    public String getDeadline() {
        return deadline;
    }

    public void getListOfCareers(final ICareer iCareer){
        prepareData = new PrepareData();
        viecLamModels = new ArrayList<>();

        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot sh : dataSnapshot.getChildren()){
                    JobModel viecLamModel = sh.getValue(JobModel.class);
                    viecLamModels.add(viecLamModel);
                }
                iCareer.call(viecLamModels);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        prepareData.moveToNode("careers").addListenerForSingleValueEvent(valueEventListener);
    }
}

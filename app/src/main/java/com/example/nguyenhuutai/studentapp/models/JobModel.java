package com.example.nguyenhuutai.studentapp.models;

import android.util.Log;

import com.example.nguyenhuutai.studentapp.dao.PrepareData;
import com.example.nguyenhuutai.studentapp.interfaces.ICareer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class JobModel implements Serializable{

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
    private List<JobModel> jobModels;


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

    //set


    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setDescriptionWork(String descriptionWork) {
        this.descriptionWork = descriptionWork;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setLocationWork(String locationWork) {
        this.locationWork = locationWork;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setExper(String exper) {
        this.exper = exper;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setTimeTrial(String timeTrial) {
        this.timeTrial = timeTrial;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setJobModels(List<JobModel> jobModels) {
        this.jobModels = jobModels;
    }

    public void getListOfCareers(final ICareer iCareer){
        prepareData = new PrepareData();
        jobModels = new ArrayList<>();

        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot sh : dataSnapshot.getChildren()){
                    JobModel jobModel = sh.getValue(JobModel.class);
                    jobModels.add(jobModel);
                }
                iCareer.call(jobModels);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        prepareData.moveToNode("careers").addListenerForSingleValueEvent(valueEventListener);
    }

    @Override
    public String toString() {
        return this.age + this.descriptionWork;
    }

}

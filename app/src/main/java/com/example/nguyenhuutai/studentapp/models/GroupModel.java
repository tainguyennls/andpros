package com.example.nguyenhuutai.studentapp.models;

public class GroupModel {

    private String name;
    private String descript;

    public GroupModel(){
        // Empty constructor require...
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getDescript() {
        return descript;
    }
}

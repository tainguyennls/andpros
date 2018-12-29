package com.example.nguyenhuutai.studentapp.model;

import java.io.Serializable;

public class DoanHoiModel implements Serializable {

    private String name;
    private String descsipt;

    public DoanHoiModel(){
        // Empty constructor require...
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescsipt(String descsipt) {
        this.descsipt = descsipt;
    }

    public String getDescsipt() {
        return descsipt;
    }
}

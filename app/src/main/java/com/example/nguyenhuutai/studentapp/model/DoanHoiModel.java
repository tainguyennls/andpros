package com.example.nguyenhuutai.studentapp.model;

import java.io.Serializable;

public class DoanHoiModel implements Serializable {

    private String name;
    private String descript;

    public DoanHoiModel(){
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

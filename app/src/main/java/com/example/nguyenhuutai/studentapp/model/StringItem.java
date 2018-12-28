package com.example.nguyenhuutai.studentapp.model;

public class StringItem {

    private String name;

    public StringItem(){
        // Empty constructor required.
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

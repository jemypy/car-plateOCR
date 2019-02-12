package com.example.gohpeiyee.usm_parkingfine_system_v1;

import com.google.gson.annotations.SerializedName;
public class DataObject {
    @SerializedName("name")
    private String name;
    public DataObject(){}
    public DataObject(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

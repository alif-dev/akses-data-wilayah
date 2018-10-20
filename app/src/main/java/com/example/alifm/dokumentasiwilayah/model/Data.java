package com.example.alifm.dokumentasiwilayah.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("data")
    public List<Region> data;

    public Data(List<Region> data) {
        this.data = data;
    }

    public List<Region> getData() {
        return data;
    }

    public void setData(List<Region> data) {
        this.data = data;
    }
}

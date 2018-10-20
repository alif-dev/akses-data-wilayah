package com.example.alifm.dokumentasiwilayah.model;

import com.google.gson.annotations.SerializedName;

public class Region {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    public Region(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

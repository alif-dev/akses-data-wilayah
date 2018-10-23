package com.example.alifm.dokumentasiwilayah.model;

import com.google.gson.annotations.SerializedName;

public class Region {

    @SerializedName("id")
    public long id;

    @SerializedName("name")
    public String name;

    public Region(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
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

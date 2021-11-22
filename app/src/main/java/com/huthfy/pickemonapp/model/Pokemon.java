package com.huthfy.pickemonapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pokemon {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private  String url;
    private  String name;

    public Pokemon(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

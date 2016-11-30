package com.example.administrator.myrecord.entity;

/**
 * Created by zzz on 2016/11/28.
 */

public class Record {
    private String name;
    private String path;
public  Record(String name,String path){
    this.name=name;
    this.path=path;
}
    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

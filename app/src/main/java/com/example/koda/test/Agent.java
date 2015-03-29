package com.example.koda.test;

import java.io.Serializable;

/**
 * Created by koda on 08/02/2015.
 */
public class Agent implements Serializable {
    private String name;
    private int id;
    private String pwd;

    public String getName(){
        return name;
    }
    public String getPwd() {return pwd;}
    public int getId(){return id;}

    public void setName(String n){
        this.name=n;
    }

    public void setId (int n){
        this.id=n;
    }

    public void setPwd(String p){
        this.pwd=p;
    }


}
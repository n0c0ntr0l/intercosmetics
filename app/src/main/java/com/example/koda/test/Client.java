/**
 * Created by koda on 11/02/2015.
 */
package com.example.koda.test;

public class Client{
    private int id;
    private String name;
    private String address;
    private int zip;
    private int salesId;

    public Client (int id, String name, String address, int zip, int salesId){
        setId(id);
        setName(name);
        setAddress(address);
        setZip(zip);
        setSalesId(salesId);
    }

    public int getId(){return id;}
    public String getName() { return name;}
    public String getAddress(){ return address;}
    public int getZip(){ return zip;}
    public int getSalesId(){ return salesId;}

    public void setId(int n){
        this.id=n;
    }
    public void setName(String n){
        this.name=n;
    }
    public void setAddress(String n){
        address=n;
    }
    public void setZip(int n){
        zip=n;
    }
    public void setSalesId(int n){
        salesId=n;
    }


}

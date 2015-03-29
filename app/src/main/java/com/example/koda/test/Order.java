package com.example.koda.test;

import android.widget.DatePicker;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by koda on 26/02/2015.
 */
public class Order{
    private int oDay, oMonth, oYear;
    private int pDay, pMonth, pYear;
    private String salesman, client;
    private ArrayList<Product> productList;
    private String extraComm;

    public void setOrderDate(int d, int m, int y){
        oDay=d;
        oMonth=m;
        oYear=y;
    }

    public int[] getOrderDate(){
        int[] r = {oDay,oMonth,oYear};
        return r;
    }

    public void setPickupDate (int d, int m, int y){
        pDay=d;
        pMonth=m;
        pYear=y;
    }

    public int[] getPickupDate(){
        int r[]={ pDay,pMonth,pYear};
        return r;
    }

    public void setSalesman(String s){
        this.salesman=s;
    }
    public String getSalesman(){
        return salesman;
    }

    public void setClient(String g){
        this.client=g;
    }

    public String getClient(){
        return client;
    }

    public void addProd(Product p){
        productList.add(p);
    }

    public ArrayList <Product> getProductList(){
        return productList;
    }

    public void setExtraComm(String s){
        extraComm=s;
    }

    public String getExtraComm(){
        return extraComm;
    }

}

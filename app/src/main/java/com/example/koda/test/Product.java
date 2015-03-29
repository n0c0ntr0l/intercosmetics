package com.example.koda.test;

/**
 * Created by koda on 14/02/2015.
 */

import java.lang.Float;
public class Product {
    private int code;
    private String name;
    private String family;
    private float price;
    private int quantity;
    private float discount;
    private float finalPrice;

    public Product(int code, String name, String family, float price){
        setName(name);
        setCode(code);
        setFamily(family);
        setPrice(price);
        setQuantity(0);
        setDiscount(0);
        setFinalPrice();
    }



    public void setCode(int n){ this.code=n;}
    public void setName (String n){this.name=n;}
    public void setFamily (String n){this.family=n;}
    public void setPrice (float n){this.price=n;}

    public void setQuantity (int n) {this.quantity=n;}
    public void setDiscount (float n) {this.discount=n;}
    public void setFinalPrice() {this.finalPrice=price-(price*discount/100);}


    public int getCode(){return code;}
    public String getName(){return name;}
    public String getFamily(){return  family;}
    public float getPrice (){return price;}

    public int getQuantity() {return quantity;}
    public float getDiscount() {return discount;}
    public float getFinalPrice() {return finalPrice;}

    public String getStringPrice() {return Float.toString(price);}
    public String getStringQuantity(){return Integer.toString(quantity);}
    public String getStringDiscount() {return  Float.toString(discount);}
    public String getStringFinalPrice() {return Float.toString(finalPrice);}


}

package com.example.musthafa.retionapp.Others;

import android.content.Context;

public class Product {
    private String id;
    private String name;
    private String qty;
    private String price;
    private Context context;
    private String image;
    public Product()
    {

    }
    public Product(String id,String name,String qty,String price,String image,Context context)
    {
        this.id=id;
        this.name=name;
        this.qty=qty;
        this.price=price;
        this.image=image;
        this.context=context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}

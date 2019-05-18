package com.example.musthafa.retionapp.Others;

import android.content.Context;

public class Orders {
    private String uemail;
    private String pname;
    private String id;
    private String image;
    private Context context;
    public Orders()
    {

    }
    public Orders(String id,String uemail,String pname,String image,Context context)
    {
        this.id=id;
        this.uemail=uemail;
        this.pname=pname;
        this.image=image;
        this.context=context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
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

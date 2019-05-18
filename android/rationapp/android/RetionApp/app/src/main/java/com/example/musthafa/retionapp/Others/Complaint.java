package com.example.musthafa.retionapp.Others;

import android.content.Context;

public class Complaint {
    private String email;
    private String complaint;
    private Context context;
    public Complaint()
    {

    }
    public Complaint(String email,String complaint,Context context)
    {
        this.email=email;
        this.complaint=complaint;
        this.context=context;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}

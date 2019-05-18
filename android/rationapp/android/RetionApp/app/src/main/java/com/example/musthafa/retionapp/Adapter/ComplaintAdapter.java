package com.example.musthafa.retionapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.musthafa.retionapp.Others.Complaint;
import com.example.musthafa.retionapp.R;

import java.util.List;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.MyViewHolder> {
    private List<Complaint> complaintList;
    private Context context;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_list,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     final Complaint complaint=complaintList.get(position);
     holder.name.setText(complaint.getEmail());
     holder.complaint.setText(complaint.getComplaint());
    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView complaint;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.c_email);
           complaint=(TextView)itemView.findViewById(R.id.c_complaint);
        }

    }
    public ComplaintAdapter (List<Complaint> complaintList){
        this.complaintList=complaintList;
    }
    public ComplaintAdapter(Context context){
        this.context = context;

    }
}

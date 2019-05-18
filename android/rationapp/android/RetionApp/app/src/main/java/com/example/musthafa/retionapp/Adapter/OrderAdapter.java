package com.example.musthafa.retionapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.musthafa.retionapp.Others.Orders;
import com.example.musthafa.retionapp.Others.Product;
import com.example.musthafa.retionapp.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    private List<Orders> ordersList;
    private Context context;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Orders orders=ordersList.get(position);
        holder.uemail.setText(orders.getUemail());
        holder.pname.setText(orders.getPname());
        String Url="http://ration.fabstudioz.com/"+orders.getImage();
        Glide.with(orders.getContext()).load(Url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView uemail,pname;
        ImageView imageView;
        Button delivered;
        public MyViewHolder(View itemView) {
            super(itemView);
            uemail=(TextView)itemView.findViewById(R.id.id_dealer_uemail);
            pname=(TextView)itemView.findViewById(R.id.id_dealer_pname);
            imageView=(ImageView)itemView.findViewById(R.id.img_order);
            delivered=(Button)itemView.findViewById(R.id.id_delivered);
            delivered.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeAt(getAdapterPosition());
                }
            });
        }

        private void removeAt(int adapterPosition) {
            if (ordersList.size()==0)
            {

            }
            else
            {
            final Orders orders=ordersList.get(adapterPosition);
            ordersList.remove(adapterPosition);
            notifyItemRemoved(adapterPosition);
            notifyItemRangeChanged(adapterPosition,ordersList.size());
                RequestQueue requestQueue= Volley.newRequestQueue(orders.getContext());
                String Url="http://ration.fabstudioz.com/deleteorder.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        params.put("id",orders.getId());
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        }
    }
    public OrderAdapter(List<Orders> ordersList){this.ordersList=ordersList;}
    public OrderAdapter(Context context){
        this.context=context;
    }
}

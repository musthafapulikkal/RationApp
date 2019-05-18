package com.example.musthafa.retionapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
import com.example.musthafa.retionapp.Others.Cart;
import com.example.musthafa.retionapp.Others.Complaint;
import com.example.musthafa.retionapp.Others.Product;
import com.example.musthafa.retionapp.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyviewHolder> {
    private List<Product> productList;
    private Context context;
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list,parent,false);
        return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
    final Product product=productList.get(position);
        holder.txt_name.setText(product.getName());
        holder.txt_qty.setText(product.getQty());
        holder.txt_price.setText(product.getPrice());
        String Url="http://ration.fabstudioz.com/"+product.getImage();
        Glide.with(product.getContext()).load(Url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView txt_name,txt_qty,txt_price;
        ImageView imageView;
        ImageButton btndelete;
        public MyviewHolder(View itemView) {
            super(itemView);
            txt_name=(TextView)itemView.findViewById(R.id.id_u_cart_name);
            txt_qty=(TextView)itemView.findViewById(R.id.id_u_cart_qty);
            txt_price=(TextView)itemView.findViewById(R.id.id_u_cart_price);
            imageView=(ImageView)itemView.findViewById(R.id.img_cart);
            btndelete=(ImageButton)itemView.findViewById(R.id.delete_cart_item);
            btndelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteAt(getAdapterPosition());
                }
            });
        }

        private void deleteAt(int adapterPosition) {
            final Product product=productList.get(adapterPosition);
            productList.remove(adapterPosition);
            notifyItemRemoved(adapterPosition);
            notifyItemRangeChanged(adapterPosition,productList.size());
            RequestQueue requestQueue= Volley.newRequestQueue(product.getContext());
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
                    params.put("id",product.getId());
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }
    }
    public CartAdapter(List<Product> productList){this.productList=productList;}
    public CartAdapter(Context context){
        this.context=context;
    }
}

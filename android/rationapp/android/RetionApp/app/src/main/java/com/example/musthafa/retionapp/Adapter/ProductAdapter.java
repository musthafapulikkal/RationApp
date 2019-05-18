package com.example.musthafa.retionapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.musthafa.retionapp.Others.Product;
import com.example.musthafa.retionapp.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyviewHolder> {
    private List<Product> productList;
    private Context context;
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.prod_list,parent,false);
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
        public MyviewHolder(View itemView) {
            super(itemView);
            txt_name=(TextView)itemView.findViewById(R.id.id_u_prod_name);
            txt_qty=(TextView)itemView.findViewById(R.id.id_u_prod_qty);
            txt_price=(TextView)itemView.findViewById(R.id.id_u_prod_price);
            imageView=(ImageView)itemView.findViewById(R.id.img_product);
        }
    }
    public ProductAdapter(List<Product> productList){this.productList=productList;}
    public ProductAdapter(Context context){
        this.context=context;
    }
}

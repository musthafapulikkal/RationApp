package com.example.musthafa.retionapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musthafa.retionapp.Adapter.ProductAdapter;
import com.example.musthafa.retionapp.Others.Product;
import com.example.musthafa.retionapp.Others.RecycletTouchListner;
import com.example.musthafa.retionapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewProducts extends AppCompatActivity {
    private List<Product> productList=new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductAdapter mAdapter;
    Button btn_p_search;
    ArrayAdapter<CharSequence> adapter_card;
    String[] card={"APL","BPL","SUBCIDY","AAY"};
    int apl=0;
    int bpl=0;
    int aay=0;
    int sub=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);
        final Spinner card_spinner=(Spinner)findViewById(R.id.spinner_card);
        adapter_card=new ArrayAdapter(this,android.R.layout.simple_spinner_item,card);
        btn_p_search=(Button)findViewById(R.id.id_user_prod_serach);
        adapter_card.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        card_spinner.setAdapter(adapter_card);
        mAdapter = new ProductAdapter(productList);
        recyclerView = (RecyclerView) findViewById(R.id.product_recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecycletTouchListner(getApplicationContext(), recyclerView, new RecycletTouchListner.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                final Product product=productList.get(position);
                final String id=product.getId();
                final String name=product.getName();
                final String qty=product.getQty();
                final String price=product.getPrice();
                final String image=product.getImage();
                Intent intent=new Intent(getApplicationContext(),SingleViewActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("qty",qty);
                intent.putExtra("price",price);
                intent.putExtra("image",image);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        btn_p_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productList.clear();
                mAdapter.notifyDataSetChanged();
                RequestQueue requestQueue= Volley.newRequestQueue(ViewProducts.this);
                final String selected_card=card_spinner.getSelectedItem().toString();
                String Url="http://ration.fabstudioz.com/viewproduct.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("pro response",response);
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            Context context=getApplicationContext();
                            for (int i=0;i<=jsonArray.length();i++)
                            {
                                JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                                String id=jsonObject.optString("id");
                                String name=jsonObject.optString("name");
                                Log.v("name",name);
                                String qty=jsonObject.optString("qty");
                                String price=jsonObject.optString("price");
                                String image=jsonObject.optString("image");
                                Log.v("img",image);
                                Product product=new Product(id,name,qty,price,image,context);
                                productList.add(product);
                                mAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        params.put("card",selected_card);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

    }
}

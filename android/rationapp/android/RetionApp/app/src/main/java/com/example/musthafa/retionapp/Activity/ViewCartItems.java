package com.example.musthafa.retionapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musthafa.retionapp.Adapter.CartAdapter;
import com.example.musthafa.retionapp.Adapter.ProductAdapter;
import com.example.musthafa.retionapp.Others.Product;
import com.example.musthafa.retionapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.musthafa.retionapp.Activity.LoginActivity.MyPREFERENCES;
import static com.example.musthafa.retionapp.Activity.LoginActivity.name;

public class ViewCartItems extends AppCompatActivity {
    private List<Product> productList=new ArrayList<>();
    private RecyclerView recyclerView;
    private CartAdapter mAdapter;
    Button btn_buy;
    int sum=0;
    String u_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart_items);
        btn_buy=(Button)findViewById(R.id.id_cart_btn_buy);
        mAdapter = new CartAdapter(productList);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_cart);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        RequestQueue requestQueue= Volley.newRequestQueue(ViewCartItems.this);
        SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        u_email=shared.getString(name,null);
        String Url="http://ration.fabstudioz.com/viewcart.php";
        final StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("cartresponse",response);

                    try {
                        JSONArray jsonArray=new JSONArray(response);
                        Context context=getApplicationContext();
                        for (int i=0;i<=jsonArray.length();i++)
                        {
                            JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                            String id=jsonObject.optString("id");
                            Log.v("id",id);
                            String name=jsonObject.optString("name");
                            Log.v("name",name);
                            String qty=jsonObject.optString("qty");
                            String price=jsonObject.optString("price");
                            String image=jsonObject.optString("image");
                            Product product=new Product(id,name,qty,price,image,context);
                            productList.add(product);
                            mAdapter.notifyDataSetChanged();
                            sum=sum+Integer.parseInt(String.valueOf(product.getPrice()));

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
                params.put("email",u_email);
                return params;
            }
        };
        requestQueue.add(stringRequest);
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.v("sum", String.valueOf(sum));
              Intent intent=new Intent(getApplicationContext(),PaymentActivity.class);
              intent.putExtra("total",String.valueOf(sum));
              startActivity(intent);
            }
        });
    }
}

package com.example.musthafa.retionapp.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musthafa.retionapp.Adapter.OrderAdapter;
import com.example.musthafa.retionapp.Adapter.ProductAdapter;
import com.example.musthafa.retionapp.Others.Orders;
import com.example.musthafa.retionapp.Others.Product;
import com.example.musthafa.retionapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewDealerOrdersActivity extends AppCompatActivity {
    private List<Orders> ordersList=new ArrayList<>();
    private RecyclerView recyclerView;
    private OrderAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dealer_orders);
        mAdapter = new OrderAdapter(ordersList);
        recyclerView = (RecyclerView) findViewById(R.id.order_recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        RequestQueue requestQueue= Volley.newRequestQueue(ViewDealerOrdersActivity.this);
        String Url="http://ration.fabstudioz.com/vieworders.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("orderres",response);
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    Context context=getApplicationContext();
                    for (int i=0;i<=jsonArray.length();i++)
                    {
                        JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                        String id=jsonObject.optString("id");
                        String uemail=jsonObject.optString("uemail");
                        String pname=jsonObject.optString("pname");
                        String image=jsonObject.optString("image");
                        Orders orders=new Orders(id,uemail,pname,image,context);
                        ordersList.add(orders);
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
        });
        requestQueue.add(stringRequest);
    }
}

package com.example.musthafa.retionapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musthafa.retionapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LocationSearch extends AppCompatActivity {
EditText edtstate,edtdist,edtcity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_search);
        edtstate=(EditText)findViewById(R.id.ustate);
        edtdist=(EditText)findViewById(R.id.udist);
        edtcity=(EditText)findViewById(R.id.ucity);
        findViewById(R.id.btnseachplace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String state=edtstate.getText().toString().trim();
                final String dist=edtdist.getText().toString().trim();
                final String city=edtcity.getText().toString().trim();
                RequestQueue requestQueue= Volley.newRequestQueue(LocationSearch.this);
                String Url="http://ration.fabstudioz.com/searchloc.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            for (int i=0;i<=jsonArray.length();i++)
                            {
                                JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                                String place=jsonObject.optString("place");
                                String lat=jsonObject.optString("lat");
                                String longt=jsonObject.optString("longt");
                                Intent intent=new Intent(getApplicationContext(),ViewLocationActivity.class);
                                intent.putExtra("lat",lat);
                                intent.putExtra("longt",longt);
                                intent.putExtra("place",place);
                                startActivity(intent);
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
                        params.put("state",state);
                        params.put("dist",dist);
                        params.put("city",city);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }
}

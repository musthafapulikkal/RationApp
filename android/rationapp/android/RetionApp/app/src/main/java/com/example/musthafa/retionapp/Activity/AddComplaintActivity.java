package com.example.musthafa.retionapp.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musthafa.retionapp.R;

import java.util.HashMap;
import java.util.Map;

import static com.example.musthafa.retionapp.Activity.LoginActivity.MyPREFERENCES;
import static com.example.musthafa.retionapp.Activity.LoginActivity.name;

public class AddComplaintActivity extends AppCompatActivity {
EditText edt_complaint;
Button btn_add;
String u_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_complaint);
        edt_complaint=(EditText)findViewById(R.id.id_add_edt_complaint);
        btn_add=(Button)findViewById(R.id.id_btn_add_complaint);
        SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        u_email=shared.getString(name,null);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String complaint=edt_complaint.getText().toString().trim();
                RequestQueue requestQueue= Volley.newRequestQueue(AddComplaintActivity.this);
                String Url="http://ration.fabstudioz.com/addcomplaint.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("addcom",response);

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
                        params.put("complaint",complaint);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }
}

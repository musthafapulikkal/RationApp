package com.example.musthafa.retionapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class AddDealersActivity extends AppCompatActivity {
 EditText edt_name,edt_email,edt_phone,edt_licen;
 String otp="OTP8520";
 Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dealers);
        edt_name=(EditText)findViewById(R.id.admin_id_dealer_name);
        edt_email=(EditText)findViewById(R.id.admin_id_dealer_email);
        edt_phone=(EditText)findViewById(R.id.admin_id_dealer_phone);
        edt_licen=(EditText)findViewById(R.id.admin_id_dealer_licen);
        btn_add=(Button)findViewById(R.id.btn_add_dealer);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name=edt_name.getText().toString().trim();
                final String email=edt_email.getText().toString().trim();
                final String phone=edt_phone.getText().toString().trim();
                final String licen=edt_licen.getText().toString().trim();
                if (TextUtils.isEmpty(name))
                {
                    edt_name.setError("please fill this field");
                    edt_name.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(email))
                {
                    edt_email.setError("please fill this field");
                    edt_email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(phone))
                {
                    edt_phone.setError("please fill this field");
                    edt_phone.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(licen))
                {
                    edt_licen.setError("please fill this field");
                    edt_licen.requestFocus();
                    return;
                }
                RequestQueue requestQueue= Volley.newRequestQueue(AddDealersActivity.this);
                String Url="http://ration.fabstudioz.com/adddealer.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("response",response);
                        if (response.equals("success"))
                        {
                            Toast.makeText(AddDealersActivity.this, "inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),AdminHomeActivity.class));
                            finish();
                        }
                        else if(response.equals("registed"))
                        {
                            Toast.makeText(AddDealersActivity.this, "already registerd", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(AddDealersActivity.this, "failed", Toast.LENGTH_SHORT).show();
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
                        params.put("name",name);
                        params.put("email",email);
                        params.put("phone",phone);
                        params.put("licen",licen);
                        params.put("otp",otp);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }
}

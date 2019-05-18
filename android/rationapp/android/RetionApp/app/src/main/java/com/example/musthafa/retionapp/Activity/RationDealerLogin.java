package com.example.musthafa.retionapp.Activity;

import android.content.Context;
import android.content.Intent;
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

public class RationDealerLogin extends AppCompatActivity {
EditText edt_email,edt_password;
Button btn_login;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String name = "namelKey";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ration_dealer_login);
        edt_email=(EditText)findViewById(R.id.id_rationd_email);
        edt_password=(EditText)findViewById(R.id.id_rationd_password);
        btn_login=(Button)findViewById(R.id.btn_rationd_login);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=edt_email.getText().toString().trim();
                final String password=edt_password.getText().toString().trim();
                RequestQueue requestQueue= Volley.newRequestQueue(RationDealerLogin.this);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(name,email);
                editor.apply();
                String Url="http://ration.fabstudioz.com/dealerlogin.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("dealer response",response);
                        if (response.equals("otpsuccess"))
                        {
                            startActivity(new Intent(getApplicationContext(),ChangePasswordActivity.class));
                        }
                        else if (response.equals("success"))
                        {
                            Intent intent=new Intent(getApplicationContext(),DealerHomeActivity.class);
                            intent.putExtra("email",email);
                            startActivity(intent);
                            //startActivity(new Intent(getApplicationContext(),DealerHomeActivity.class));
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
                        params.put("email",email);
                        params.put("password",password);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }
}

package com.example.musthafa.retionapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class UserRegistrationActivity extends AppCompatActivity {
EditText edt_email,edt_username,edt_password,edt_cardno,edt_proof;
Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        edt_email=(EditText)findViewById(R.id.reg_id_email);
        edt_username=(EditText)findViewById(R.id.reg_id_username);
        edt_password=(EditText)findViewById(R.id.reg_id_password);
        edt_cardno=(EditText)findViewById(R.id.reg_id_card_no);
        edt_proof=(EditText)findViewById(R.id.reg_id_proof_no);
        btn_register=(Button)findViewById(R.id.btn_id_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=edt_email.getText().toString().trim();
                final String username=edt_username.getText().toString().trim();
                final String password=edt_password.getText().toString().trim();
                final String cardno=edt_cardno.getText().toString().trim();
                final String proof=edt_proof.getText().toString().trim();
                if (TextUtils.isEmpty(email)){
                    edt_email.setError("please enter here");
                    edt_email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(username)){
                    edt_username.setError("please enter here");
                    edt_username.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    edt_password.setError("please enter here");
                    edt_password.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(cardno)){
                    edt_cardno.setError("please enter here");
                    edt_cardno.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(proof))
                {
                    edt_proof.setError("please enter here");
                    edt_proof.requestFocus();
                    return;
                }
                RequestQueue requestQueue=Volley.newRequestQueue(UserRegistrationActivity.this);
                String Url="http://ration.fabstudioz.com/user_registration.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("response",response);
                        if (response.equals(username)){
                            startActivity(new Intent(getApplicationContext(),UserHomeActivity.class));
                            finish();
                        }
                        else if (response.equals("invalid")){
                            Toast.makeText(UserRegistrationActivity.this, "already exist", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            finish();
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
                        params.put("username",username);
                        params.put("password",password);
                        params.put("cardno",cardno);
                        params.put("proof",proof);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

    }
}

package com.example.musthafa.retionapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
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

import static com.example.musthafa.retionapp.Activity.RationDealerLogin.MyPREFERENCES;
import static com.example.musthafa.retionapp.Activity.RationDealerLogin.name;

public class ChangePasswordActivity extends AppCompatActivity {
EditText edt_new,edt_confirm;
Button btn_change;
String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        edt_new=(EditText)findViewById(R.id.id_change_new_pass);
        edt_confirm=(EditText)findViewById(R.id.id_change_confirm_pass);
        btn_change=(Button)findViewById(R.id.btn_change_pass);
        SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        email=shared.getString(name,null);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String newpass=edt_new.getText().toString().trim();
                final String confirm=edt_confirm.getText().toString().trim();
                if (TextUtils.isEmpty(newpass))
                {
                    edt_new.setError("please fill this field");
                    edt_new.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(confirm))
                {
                    edt_confirm.setError("please fill this field");
                    edt_confirm.requestFocus();
                    return;
                }
                if (!newpass.equals(confirm))
                {
                    Toast.makeText(ChangePasswordActivity.this, "password mismatch", Toast.LENGTH_SHORT).show();
                    edt_new.getText().clear();
                    edt_confirm.getText().clear();
                }
                else
                {
                    RequestQueue requestQueue= Volley.newRequestQueue(ChangePasswordActivity.this);
                    String Url="http://ration.fabstudioz.com/changedealerpass.php";
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.v("change response",response);
                            if (response.equals("success"))
                            {
                                Intent  intent=new Intent(getApplicationContext(),DealerHomeActivity.class);
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
                            params.put("password",newpass);
                            params.put("email",email);
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
            }
        });

    }
}

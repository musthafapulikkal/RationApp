package com.example.musthafa.retionapp.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class LoginActivity extends Activity {
    EditText edt_email,edt_password;
    Button btn_login,btn_signup;
    TextView txt;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String name = "namelKey";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        edt_email=(EditText)findViewById(R.id.id_username);
        edt_password=(EditText)findViewById(R.id.id_password);
        btn_login=(Button)findViewById(R.id.btn_login);
        btn_signup=(Button)findViewById(R.id.btn_signup);
        txt=(TextView)findViewById(R.id.txt_login);
        Intent intent=getIntent();
        String type=intent.getStringExtra("type");
        if (type.equals("admin"))
        {
            btn_signup.setVisibility(View.GONE);
            txt.setText("Admin LoginPage");

        }
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=edt_email.getText().toString().trim();
                final String password=edt_password.getText().toString().trim();
                if (TextUtils.isEmpty(email)){
                    edt_email.setError("please enter here");
                    edt_email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    edt_password.setError("please enter here");
                    edt_password.requestFocus();
                    return;
                }
                if (email.equals("admin@gmail.com")&&password.equals("admin")){
                    startActivity(new Intent(getApplicationContext(),AdminHomeActivity.class));
                }
                else {
                    RequestQueue requestQueue=Volley.newRequestQueue(LoginActivity.this);
                    String Url="http://ration.fabstudioz.com/users_login.php";
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.v("login response",response);
                            if (response.equals(email)){
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString(name,email);
                                editor.apply();
                                startActivity(new Intent(getApplicationContext(),UserHomeActivity.class));
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
                            params.put("password",password);
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),UserRegistrationActivity.class));
                finish();
            }
        });
    }
}

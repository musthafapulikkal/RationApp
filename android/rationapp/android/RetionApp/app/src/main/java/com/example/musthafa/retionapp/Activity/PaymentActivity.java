package com.example.musthafa.retionapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class PaymentActivity extends AppCompatActivity {
EditText edt_hname,edt_month,edt_cvv,edt_year;
TextView txt_amount;
Button btn_pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        edt_hname=(EditText)findViewById(R.id.id_pay_holder);
        edt_cvv=(EditText)findViewById(R.id.id_pay_cvv);
        edt_month=(EditText)findViewById(R.id.id_pay_expiry_month);
        edt_year=(EditText)findViewById(R.id.id_pay_expiry_year);
        txt_amount=(TextView)findViewById(R.id.id_pay_amount);
        btn_pay=(Button)findViewById(R.id.btn_pay);
        Intent intent=getIntent();
        //final String pid=intent.getStringExtra("pid");
        final String price=intent.getStringExtra("total");
        Log.v("sum",price);
        txt_amount.setText(price);
       // Log.v("pid",pid);
        final String uemail=intent.getStringExtra("uemail");
       // Log.v("email",uemail);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=edt_hname.getText().toString().trim();
                Log.v("hname",name);
                final String cvv=edt_cvv.getText().toString().trim();
                final String month=edt_month.getText().toString().trim();
                final String year=edt_year.getText().toString().trim();
                final String amount=txt_amount.getText().toString().trim();
                RequestQueue requestQueue= Volley.newRequestQueue(PaymentActivity.this);
                String Url="http://ration.fabstudioz.com/payment.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("payresponse",response);
                        if (response.equals("success"))
                        {
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
                        params.put("name",name);
                        params.put("cvv",cvv);
                        params.put("month",month);
                        params.put("year",year);
                        params.put("amount",amount);
                        //params.put("pid",pid);
                        params.put("email",uemail);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }
}

package com.example.musthafa.retionapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.musthafa.retionapp.R;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static com.example.musthafa.retionapp.Activity.LoginActivity.MyPREFERENCES;
import static com.example.musthafa.retionapp.Activity.LoginActivity.name;

public class SingleViewActivity extends AppCompatActivity {
TextView txt_name,txt_qty,txt_price;
Button btn_book;
ImageView imageView;
String u_email;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view);
        txt_name=(TextView) findViewById(R.id.id_single_name);
        txt_qty=(TextView)findViewById(R.id.id_single_qty);
        txt_price=(TextView)findViewById(R.id.id_single_price);
        btn_book=(Button)findViewById(R.id.id_btn_single_book);
        imageView=(ImageView)findViewById(R.id.id_single_img);
        SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        u_email=shared.getString(name,null);
        final Intent intent=getIntent();
//        id=intent.getStringExtra("id");
//        Log.v("id",id);
        final String name=intent.getStringExtra("name");
        final String qty=intent.getStringExtra("qty");
        final String price=intent.getStringExtra("price");
        final String image=intent.getStringExtra("image");
        txt_name.setText(name);
        txt_qty.setText(qty);
        txt_price.setText(price);
        String Url="http://ration.fabstudioz.com/"+image;
        Glide.with(getApplicationContext()).load(Url).into(imageView);
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView imageView = (ImageView) findViewById(R.id.id_single_img);
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                final byte[] imageInByte = baos.toByteArray();
                final String p_image = Base64.encodeToString(imageInByte, Base64.DEFAULT);
                RequestQueue requestQueue=Volley.newRequestQueue(SingleViewActivity.this);
                String Url="http://ration.fabstudioz.com/addtocart.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                     Log.v("cart res",response);
                     if (response.equals("success"))
                     {
                         startActivity(new Intent(getApplicationContext(),UserHomeActivity.class));
                         finish();
                     }
                     else if (response.equals("exist"))
                     {
                         Toast.makeText(SingleViewActivity.this, "sorry already booked", Toast.LENGTH_SHORT).show();
                     }
                     else
                     {
                         Toast.makeText(SingleViewActivity.this, "sorry", Toast.LENGTH_SHORT).show();
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
                        params.put("uemail",u_email);
                        params.put("pname",name);
                        params.put("qty",qty);
                        params.put("price",price);
                        params.put("image",p_image);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
//                id=intent.getStringExtra("id");
//                Log.v("id",id);
//                Intent i=new Intent(getApplicationContext(),PaymentActivity.class);
//                i.putExtra("pid",id);
//                i.putExtra("amount",price);
////                intent.putExtra("pid",id);
//                i.putExtra("uemail",u_email);
//                startActivity(i);
//                RequestQueue requestQueue= Volley.newRequestQueue(SingleViewActivity.this);
//                String Url="http://192.168.100.131/rationapp/";
//                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.v("response single",response);
//                        if (response.equals("success"))
//                        {
//                            Toast.makeText(SingleViewActivity.this, "booked", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(),UserHomeActivity.class));
//                            finish();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }){
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String,String> params=new HashMap<>();
//                        params.put("uemail",u_email);
//                        params.put("pid",id);
//                        return params;
//                    }
//                };
//                requestQueue.add(stringRequest);
            }
        });
    }
}

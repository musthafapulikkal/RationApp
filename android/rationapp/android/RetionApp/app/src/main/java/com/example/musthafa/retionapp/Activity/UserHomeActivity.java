package com.example.musthafa.retionapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.musthafa.retionapp.R;

public class UserHomeActivity extends AppCompatActivity {
Button btn_product,btn_order,btn_complaint,btn_vewloc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        btn_product=(Button)findViewById(R.id.btn_user_product);
        btn_order=(Button)findViewById(R.id.btn_user_cart);
        btn_complaint=(Button)findViewById(R.id.btn_user_complaint);
        btn_vewloc=(Button)findViewById(R.id.btn_user_location);
        btn_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewProducts.class));
            }
        });
        btn_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddComplaintActivity.class));
            }
        });
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
startActivity(new Intent(getApplicationContext(),ViewCartItems.class));
            }
        });
        btn_vewloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LocationSearch.class));
            }
        });
    }
}

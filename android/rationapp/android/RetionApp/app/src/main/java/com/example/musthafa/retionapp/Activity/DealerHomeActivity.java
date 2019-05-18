package com.example.musthafa.retionapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.musthafa.retionapp.R;

public class DealerHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_home);
        final Intent intent=getIntent();
        final String d_email=intent.getStringExtra("email");
        Log.v("home",d_email);
        findViewById(R.id.btn_dealer_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             startActivity(new Intent(getApplicationContext(),ViewDealerOrdersActivity.class));
            }
        });
        findViewById(R.id.btn_dealer_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent1=new Intent(getApplicationContext(),AddLocation.class);
             intent1.putExtra("email",d_email);
             startActivity(intent1);
            }
        });
    }
}

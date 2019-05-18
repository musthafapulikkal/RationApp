package com.example.musthafa.retionapp.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musthafa.retionapp.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AddProductActivity extends AppCompatActivity {
 EditText edt_name,edt_price,edt_qty;
 CheckBox chckbpl,checkapl,checkaay,checksubcidy;
 Button btn_add,btn_choose;
 int bpl=0;
 int apl=0;
 int subcidy=0;
 int aay=0;
 ImageView imageView;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap bitmap;
    private String imagepath=null;
    ArrayList<String> mylist = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        edt_name=(EditText)findViewById(R.id.id_admin_prod_name);
        edt_price=(EditText)findViewById(R.id.id_admin_prod_price);
        edt_qty=(EditText)findViewById(R.id.id_admin_prod_qty);
        chckbpl=(CheckBox)findViewById(R.id.id_product_bpl);
        checkapl=(CheckBox)findViewById(R.id.id_product_apl);
        checksubcidy=(CheckBox)findViewById(R.id.id_product_subcidy);
        checkaay=(CheckBox)findViewById(R.id.id_product_AAY);
        btn_add=(Button)findViewById(R.id.admin_btn_add_prod);
        btn_choose=(Button)findViewById(R.id.admin_btn_choose_prod);
        imageView=(ImageView)findViewById(R.id.admin_img_prod);
        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,RESULT_LOAD_IMAGE);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name=edt_name.getText().toString().trim();
                final String price=edt_price.getText().toString().trim();
                final String qty=edt_qty.getText().toString().trim();
                if (chckbpl.isChecked())
                {
                    mylist.add("bpl");
                }
                if (checkapl.isChecked())
                {
                    mylist.add("apl");
                }
                if (checksubcidy.isChecked())
                {
                    mylist.add("subcidy");
                }
                if (checkaay.isChecked())
                {
                    mylist.add("aay");
                }
               if (mylist.contains("apl"))
               {
                   apl=1;
               }
               if (mylist.contains("bpl"))
               {
                   bpl=1;
               }
               if (mylist.contains("subcidy"))
               {
                   subcidy=1;
               }
               if (mylist.contains("aay"))
               {
                   aay=1;
               }
                Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                final byte[] imageInByte=baos.toByteArray();
                final String image= Base64.encodeToString(imageInByte,Base64.DEFAULT);
                RequestQueue requestQueue= Volley.newRequestQueue(AddProductActivity.this);
                String Url="http://ration.fabstudioz.com/addproduct.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("prod response",response);
                        if (response.equals("success"))
                        {
                            startActivity(new Intent(getApplicationContext(),AdminHomeActivity.class));
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
                        params.put("price",price);
                        params.put("qty",qty);
                        params.put("apl", String.valueOf(apl));
                        params.put("bpl", String.valueOf(bpl));
                        params.put("subcidy", String.valueOf(subcidy));
                        params.put("aay", String.valueOf(aay));
                        params.put("image",image);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RESULT_LOAD_IMAGE&&resultCode==RESULT_OK&&null != data){
            Uri selectedImage=data.getData();
            String[] filepathColumn={MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filepathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex=cursor.getColumnIndex(filepathColumn[0]);
            String picturePath=cursor.getString(columnIndex);
            cursor.close();
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));


        }
    }
}

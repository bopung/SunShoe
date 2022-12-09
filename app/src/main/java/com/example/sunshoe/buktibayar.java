package com.example.sunshoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class buktibayar extends AppCompatActivity {

    Bitmap photo;
    private final int IMG_REQUEST = 1111;

    EditText nama, nomor, alamat, norek;
    String id, name, price, image, desc, size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buktibayar);
        Intent intent = getIntent();

        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        image = intent.getStringExtra("image");
        desc = intent.getStringExtra("desc");
        size = intent.getStringExtra("size");

        nama = findViewById(R.id.name);
        nomor = findViewById(R.id.nomor);
        alamat = findViewById(R.id.alamat);
        norek = findViewById(R.id.norekpenerima);

        Button upload = (Button) findViewById(R.id.buttonUpload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMG_REQUEST);
            }
        });

        Button confirm = (Button) findViewById(R.id.buttonConfirmt);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDataVolley();
            }
        });





    }
    private void saveDataVolley(){
        StringRequest postRequest = new StringRequest(Request.Method.POST, "https://stevanuspungky.my.id/mobapp/addtransaction.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Register", "response :" + response);
                        Toast.makeText(getBaseContext(),"Transaksi Berhasil ! ", Toast.LENGTH_SHORT).show();
                        processResponse("Save Data",response);
                        finish();
                        Intent intent = new Intent(buktibayar.this, transactionsukses.class);
                        buktibayar.this.startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                // the POST parameters:

                params.put("id",id);
                params.put("product_name",name);
                params.put("product_price",price);
                params.put("product_size",size);
                params.put("product_image",image);
                params.put("nama",nama.getText().toString());
                params.put("nomorhp",nomor.getText().toString());
                params.put("alamat",alamat.getText().toString());
                params.put("norek",norek.getText().toString());
                params.put("bukti",encodeImage(photo));
                params.put("status", "1");





                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);

    }

    private void processResponse(String paction, String response){

        try {
            JSONObject jsonObj = new JSONObject(response);
            String errormsg = jsonObj.getString("errormsg");
            Toast.makeText(getBaseContext(),paction+" "+errormsg,Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            Log.d("Register", "Server Unreachable !");
        }



    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try{
                photo = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
            }catch(IOException e){
                Log.e("CHOOSEIMG",  e.toString());
            }
        }

    }
    public String encodeImage(Bitmap bm) {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, ba);
        byte[] imgByte = ba.toByteArray();
        String encode = Base64.encodeToString(imgByte, Base64.DEFAULT);
        return encode;
    }
}
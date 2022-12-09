package com.example.sunshoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InputBarang extends AppCompatActivity {

    EditText nama, harga, ukuran, link, merk, kategori, deskripsi;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_barang);
        nama = (EditText) findViewById(R.id.name);
        harga = (EditText) findViewById(R.id.harga);
        ukuran = (EditText) findViewById(R.id.ukuran);
        link = (EditText) findViewById(R.id.link);
        merk = (EditText) findViewById(R.id.merk);
        kategori = (EditText) findViewById(R.id.kategori);
        deskripsi = (EditText) findViewById(R.id.deskripsi);
        btnAdd = (Button) findViewById(R.id.buttonConfirmt);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataVolley();


                Toast.makeText(getApplicationContext(), "Item Successfuly added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }



    //    metode untuk save ke server menggunakan volley
    private void saveDataVolley() {
        nama = (EditText) findViewById(R.id.name);
        harga = (EditText) findViewById(R.id.harga);
        ukuran = (EditText) findViewById(R.id.ukuran);
        link = (EditText) findViewById(R.id.link);
        merk = (EditText) findViewById(R.id.merk);
        kategori = (EditText) findViewById(R.id.kategori);
        deskripsi = (EditText) findViewById(R.id.deskripsi);
        final String namaproduk = nama.getText().toString();
        final String hargaproduk = harga.getText().toString();
        final String ukuranproduk = ukuran.getText().toString();
        final String linkproduk = link.getText().toString();
        final String merkproduk = merk.getText().toString();
        final String kategoriproduk = kategori.getText().toString();
        final String deskripsiproduk = deskripsi.getText().toString();

        String url = "https://stevanuspungky.my.id/mobapp/addproduct.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Register", "response :" + response);
                        Toast.makeText(getBaseContext(),"Input Produk Berhasil ! ", Toast.LENGTH_SHORT).show();
//                        processResponse("Save Data", response);
                        finish();

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
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:

                params.put("product_name", namaproduk);
                params.put("product_price", hargaproduk);
                params.put("product_size", ukuranproduk);
                params.put("product_image", linkproduk);
                params.put("product_brand", merkproduk);
                params.put("product_category", kategoriproduk);
                params.put("product_description", deskripsiproduk);

                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);

    }

//    private void processResponse(String paction, String response){
//
//        try {
//            JSONObject jsonObj = new JSONObject(response);
//            String errormsg = jsonObj.getString("errormsg");
//            Toast.makeText(getBaseContext(),paction+" "+errormsg,Toast.LENGTH_SHORT).show();
//
//        } catch (JSONException e) {
//            Log.d("Register", "Server Unreachable !");
//        }

//}
}
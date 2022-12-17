package com.example.sunshoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class transaction_detail extends AppCompatActivity {

    ImageView imageView;
    ImageView buktiview;
    TextView itemName;
    TextView itemPrice;
    TextView itemDescription;
    TextView itemSize;
    TextView itemnama;
    TextView itemNomor;
    TextView itemAlamat;
    TextView itemNorek;


    String id,name, price, size, imageUrl, nama, nomorhp, alamat, norek, bukti, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);

        Intent intent = getIntent();

        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        size = intent.getStringExtra("size");
        imageUrl = intent.getStringExtra("image");
        nama = intent.getStringExtra("nama");
        nomorhp = intent.getStringExtra("nomorhp");
        alamat = intent.getStringExtra("alamat");
        norek = intent.getStringExtra("norek");
        bukti = intent.getStringExtra("bukti");
        status = intent.getStringExtra("status");



        imageView = findViewById(R.id.imageView2);
        itemName = findViewById(R.id.name2);
        itemPrice = findViewById(R.id.price2);
        buktiview = findViewById(R.id.imageView3);
        itemSize = findViewById(R.id.textView15);
        itemnama = findViewById(R.id.textView16);
        itemNomor = findViewById(R.id.textView17);
        itemAlamat = findViewById(R.id.textView18);
        itemNorek = findViewById(R.id.textView19);


        Picasso.get().load(imageUrl).fit().centerCrop().into(imageView);
        Picasso.get().load("https://stevanuspungky.my.id"+bukti).fit().centerCrop().into(buktiview);
        Log.i("bukti",bukti);

        itemName.setText(name);
        itemPrice.setText("Rp "+price + "K");
        itemSize.setText("Size : "+ size);
        itemnama.setText("Nama Pembeli : "+ nama);
        itemNomor.setText("Telepon Pembeli : "+ nomorhp);
        itemAlamat.setText("Alamat Pembeli : "+ alamat);
        itemNorek.setText("Nomor Rekening Pembeli :" + norek);

        Button button = findViewById(R.id.button3);
        button.setText("Selesaikan Transaksi");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(transaction_detail.this)
                        .setTitle("Transaction Done ?")
                        .setMessage("Make sure your Transaction is already done !")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //    metode untuk save ke server menggunakan volley
                                String url = "https://stevanuspungky.my.id/mobapp/updateorder.php";

                                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                Log.d("Register", "response :" + response);
                                                Toast.makeText(transaction_detail.this, "Transaction Done !", Toast.LENGTH_SHORT).show();
//                        processResponse("Save Data", response);

                                                Intent i = new Intent(transaction_detail.this, order.class);
                                                startActivity(i);


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

                                        params.put("id",id);

                                        return params;
                                    }
                                };
                                Volley.newRequestQueue(transaction_detail.this).add(postRequest);

                            }


                        })

                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });


    }
}
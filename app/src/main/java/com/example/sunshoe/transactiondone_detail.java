package com.example.sunshoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class transactiondone_detail extends AppCompatActivity {
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


    String name, price, size, imageUrl, nama, nomorhp, alamat, norek, bukti, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactiondone_detail);

        Intent intent = getIntent();

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
        Picasso.get().load(bukti).fit().centerCrop().into(buktiview);

        itemName.setText(name);
        itemPrice.setText("Rp "+price + "K");
        itemSize.setText("Size : "+ size);
        itemnama.setText("Nama Pembeli : "+ nama);
        itemNomor.setText("Telepon Pembeli : "+ nomorhp);
        itemAlamat.setText("Alamat Pembeli : "+ alamat);
        itemNorek.setText("Nomor Rekening Pembeli :" + norek);


    }
}
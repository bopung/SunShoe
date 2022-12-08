package com.example.sunshoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class shoeDetail extends AppCompatActivity {

    ImageView imageView;
    TextView itemName;
    TextView itemPrice;
    TextView itemRating;
    String description;

    String name, price, rating, imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_detail);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        description = intent.getStringExtra("description");
        imageUrl = intent.getStringExtra("image");


        imageView = findViewById(R.id.imageView5);
        itemName = findViewById(R.id.name);
        itemPrice = findViewById(R.id.price);
        itemRating = findViewById(R.id.rating);


        Picasso.get().load(imageUrl).fit().centerCrop().into(imageView);

        itemName.setText(name);
        itemPrice.setText("Rp "+price);
        itemRating.setText(rating);


    }
}
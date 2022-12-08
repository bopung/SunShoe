package com.example.sunshoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class shoeDetail extends AppCompatActivity {

    ImageView imageView;
    TextView itemName;
    TextView itemPrice;
    TextView itemRating;
    TextView itemDescription;

    String name, price, description, imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_detail);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        description = intent.getStringExtra("desc");
        imageUrl = intent.getStringExtra("image");


        imageView = findViewById(R.id.imageView5);
        itemName = findViewById(R.id.name);
        itemPrice = findViewById(R.id.price);
        itemDescription = findViewById(R.id.textView8);


        Picasso.get().load(imageUrl).fit().centerCrop().into(imageView);

        itemName.setText(name);
        itemPrice.setText("Rp "+price);
        itemDescription.setText(description);

        Button cart = (Button)findViewById(R.id.button);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(shoeDetail.this, buktibayar.class);
                Intent e = getIntent();
                i.putExtra("id",e.getStringExtra("id"));
                i.putExtra("name",e.getStringExtra("name"));
                i.putExtra("price",e.getStringExtra("price"));
                i.putExtra("image",e.getStringExtra("image"));
                i.putExtra("desc",e.getStringExtra("desc"));
                i.putExtra("size",e.getStringExtra("size"));



//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shoeDetail.this.startActivity(i);
            }
        });

    }
}
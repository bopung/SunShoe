package com.example.sunshoe;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class transaction extends RecyclerView.Adapter<transaction.AllMenuViewHolder> {

    Context context;
    List<ordertrans> allmenuList;

    public transaction(Context context, List<ordertrans> allmenuList) {
        this.context = context;
        this.allmenuList = allmenuList;
    }

    @NonNull
    @Override
    public AllMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_all_menu, parent, false);
        return new AllMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMenuViewHolder holder, final int position) {

        holder.allMenuName.setText(allmenuList.get(position).getProduct_name());
        holder.allMenuPrice.setText(Integer.toString(allmenuList.get(position).getProduct_price())+ " K");
        holder.allMenuCharges.setText("SIZE: "+Integer.toString(allmenuList.get(position).getProduct_size()));
        holder.allMenuNote.setText(allmenuList.get(position).getNama());

        Picasso.get().load(allmenuList.get(position).getProduct_image()).fit().centerCrop().into(holder.allMenuImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allmenuList.get(position).getStatus().equals("1")) {
                    Intent i = new Intent(context, transaction_detail.class);
                    i.putExtra("id", Integer.toString(allmenuList.get(position).getId()));
                    i.putExtra("name", allmenuList.get(position).getProduct_name());
                    i.putExtra("price", Integer.toString(allmenuList.get(position).getProduct_price()));
                    i.putExtra("image", allmenuList.get(position).getProduct_image());
                    i.putExtra("nama", allmenuList.get(position).getNama());
                    i.putExtra("size", Integer.toString(allmenuList.get(position).getProduct_size()));
                    i.putExtra("alamat", allmenuList.get(position).getAlamat());
                    i.putExtra("norek", allmenuList.get(position).getNorek());
                    i.putExtra("bukti", allmenuList.get(position).getBukti());
                    i.putExtra("status", allmenuList.get(position).getStatus());
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                else{
                    Intent i = new Intent(context, transactiondone_detail.class);
                    i.putExtra("id", Integer.toString(allmenuList.get(position).getId()));
                    i.putExtra("name", allmenuList.get(position).getProduct_name());
                    i.putExtra("price", Integer.toString(allmenuList.get(position).getProduct_price()));
                    i.putExtra("image", allmenuList.get(position).getProduct_image());
                    i.putExtra("nama", allmenuList.get(position).getNama());
                    i.putExtra("size", Integer.toString(allmenuList.get(position).getProduct_size()));
                    i.putExtra("alamat", allmenuList.get(position).getAlamat());
                    i.putExtra("norek", allmenuList.get(position).getNorek());
                    i.putExtra("bukti", allmenuList.get(position).getBukti());
                    i.putExtra("status", allmenuList.get(position).getStatus());
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return allmenuList.size();
    }

    public static class AllMenuViewHolder extends RecyclerView.ViewHolder {

        TextView allMenuName, allMenuNote, allMenuTime, allMenuCharges, allMenuPrice;
        ImageView allMenuImage;

        public AllMenuViewHolder(@NonNull View itemView) {
            super(itemView);

            allMenuName = itemView.findViewById(R.id.all_menu_name);
            allMenuNote = itemView.findViewById(R.id.all_menu_note);
            allMenuCharges = itemView.findViewById(R.id.all_menu_delivery_charge);
            allMenuTime = itemView.findViewById(R.id.all_menu_deliverytime);
            allMenuPrice = itemView.findViewById(R.id.all_menu_price);
            allMenuImage = itemView.findViewById(R.id.all_menu_image);

        }
    }

}

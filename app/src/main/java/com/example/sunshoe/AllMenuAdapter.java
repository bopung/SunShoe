package com.example.sunshoe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.List;

public class AllMenuAdapter extends RecyclerView.Adapter<AllMenuAdapter.AllMenuViewHolder> {

    Context context;
    List<shoe> allmenuList;

    public AllMenuAdapter(Context context, List<shoe> allmenuList) {
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
        holder.allMenuPrice.setText(Integer.toString(allmenuList.get(position).getProduct_price())+ " K" );
        holder.allMenuCharges.setText("SIZE : "+Integer.toString(allmenuList.get(position).getProduct_size()));
        holder.allMenuNote.setText(allmenuList.get(position).getBrand());

        Picasso.get().load(allmenuList.get(position).getProduct_image()).fit().centerCrop().into(holder.allMenuImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, shoeDetail.class);
                i.putExtra("id",Integer.toString(allmenuList.get(position).getProduct_id()));
                i.putExtra("name", allmenuList.get(position).getProduct_name());
                i.putExtra("price", Integer.toString(allmenuList.get(position).getProduct_price()));
                i.putExtra("image", allmenuList.get(position).getProduct_image());
                i.putExtra("desc", allmenuList.get(position).getDescription());
                i.putExtra("size", Integer.toString(allmenuList.get(position).getProduct_size()));


                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allmenuList.size();
    }

    public static class AllMenuViewHolder extends RecyclerView.ViewHolder{

        TextView allMenuName, allMenuNote, allMenuRating, allMenuTime, allMenuCharges, allMenuPrice;
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

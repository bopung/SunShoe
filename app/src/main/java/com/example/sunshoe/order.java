package com.example.sunshoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        RecyclerView userRV = findViewById(R.id.all_menu_recycler);
        RequestQueue queue = Volley.newRequestQueue(order.this);
        StringRequest sr = new StringRequest(Request.Method.GET,
                "https://stevanuspungky.my.id/mobapp/getrans.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject respObj = new JSONObject(response);
                            //String success = respObj.getString("success");
                            JSONArray data = respObj.getJSONArray("data");

                            ArrayList<ordertrans> trans = new ArrayList<ordertrans>();

                            for (int i = 0; i < data.length(); i++) {
                                JSONObject a = data.getJSONObject(i);

                                if (a.getString("status").equals("1")){
                                    trans.add(
                                            new ordertrans(
                                                    a.getInt("id"),
                                                    a.getString("product_name"),
                                                    a.getInt("product_price"),
                                                    a.getInt("product_size"),
                                                    a.getString("product_image"),
                                                    a.getString("nama"),
                                                    a.getString("nomorhp"),
                                                    a.getString("alamat"),
                                                    a.getString("norek"),
                                                    a.getString("bukti"),
                                                    a.getString("status")
                                            )
                                    );
                                }

                            }

                            transaction adapter = new transaction(order.this, trans);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(order.this, LinearLayoutManager.VERTICAL, false);
                            userRV.setLayoutManager(linearLayoutManager);
                            userRV.setAdapter(adapter);
                            Log.i("VOLLEYDONE", "DONE");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("VOLLEYERROCATCH", e.toString());

                        }
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Cari", error.toString());
                try{
                    Log.i("VOLLEY", String.valueOf(error.networkResponse.statusCode));
//                Toast.makeText(context, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    Toast.makeText(order.this, "Error, please check your connection", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(order.this, "Unknown Error: " + error, Toast.LENGTH_LONG).show();
                }

            }
        });
        queue.add(sr);

    }
}

package com.example.sunshoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
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

public class delete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        RecyclerView userRV = findViewById(R.id.all_menu_recycler);
        RequestQueue queue = Volley.newRequestQueue(delete.this);
        StringRequest sr = new StringRequest(Request.Method.GET,
                "https://stevanuspungky.my.id/mobapp/getshoes.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject respObj = new JSONObject(response);
                            //String success = respObj.getString("success");
                            JSONArray data = respObj.getJSONArray("data");

                            ArrayList<shoe> shoes = new ArrayList<shoe>();

                            for (int i = 0; i < data.length(); i++) {
                                JSONObject a = data.getJSONObject(i);

//                        TODO: update ui menu dari json di sini
                                shoes.add(
                                        new shoe(
                                                a.getInt("product_id"),
                                                a.getString("product_name"),
                                                a.getInt("product_price"),
                                                a.getInt("product_size"),
                                                a.getString("product_image"),
                                                a.getString("brand"),
                                                a.getString("category"),
                                                a.getString("description")
                                        )
                                );
                            }

                            deleteadapter adapter = new deleteadapter(delete.this, shoes);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(delete.this, LinearLayoutManager.VERTICAL, false);
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
                    Toast.makeText(delete.this, "Error, please check your connection", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(delete.this, "Unknown Error: " + error, Toast.LENGTH_LONG).show();
                }

            }
        });
        queue.add(sr);

    }
}
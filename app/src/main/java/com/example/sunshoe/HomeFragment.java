package com.example.sunshoe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        DbUser dbUser = new DbUser(getContext());
        dbUser.open();

        TextView txtUser = view.findViewById(R.id.textNama);
        txtUser.setText(dbUser.getName());
        dbUser.close();

        RecyclerView userRV = view.findViewById(R.id.all_menu_recycler);
        RequestQueue queue = Volley.newRequestQueue(getContext());
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

                            AllMenuAdapter adapter = new AllMenuAdapter(getContext(), shoes);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
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
                    Toast.makeText(getContext(), "Error, please check your connection", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(getContext(), "Unknown Error: " + error, Toast.LENGTH_LONG).show();
                }

            }
        });
        queue.add(sr);

        return view;

                }
    }
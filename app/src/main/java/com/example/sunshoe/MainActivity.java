package com.example.sunshoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    ProgressDialog pDialog;
    Button loginbtn;
    Context context;
    private static  final int REQUEST_CODE_ADD =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        //inisialisasi tampilan
        pDialog = new ProgressDialog(context);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);

        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
            }
        });

//button sistem login
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameKey = username.getText().toString();
                String passwordKey = password.getText().toString();
                if (usernameKey.equals("Admin") && passwordKey.equals("root")){
                    //jika login berhasil
                    Toast.makeText(getApplicationContext(), "Selamat Datang Admin "+ usernameKey,
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, AdminPanel.class);
                    MainActivity.this.startActivity(intent);
                }else {
                    LoginSistem();
                }
            }
        });
    }

    //login sistem
    public void LoginSistem(){
        final String Url = "https://stevanuspungky.my.id/mobapp/login.php";

        final String NamaUser = username.getText().toString().trim();
        final String Pass = password.getText().toString().trim();
        pDialog.setMessage("Login Process...");
        showDialog();
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //If we are getting success from server
                        if (response.contains("success")) {
                            hideDialog();
                            Toast.makeText(context, "Login Berhasil Masuk!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, DashbordToko.class);
                            startActivityForResult(intent, REQUEST_CODE_ADD);

                        } else {
                            hideDialog();
                            //Displaying an error message on toast
                            Toast.makeText(context, "Nama User atau Password SALAH!", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        hideDialog();
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put("NamaUser", NamaUser);
                params.put("Pass", Pass);

                //returning parameter
                return params;

            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };

        //Adding the string request to the queue

        Volley.newRequestQueue(this).add(stringRequest);

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
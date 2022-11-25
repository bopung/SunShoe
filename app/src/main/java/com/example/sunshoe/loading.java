package com.example.sunshoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class loading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        time();
    }

    private void time() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(loading.this, MainActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }
}
package com.example.sunshoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        WebView webview = (WebView) findViewById(R.id.webview);
        webview.loadUrl("https://sites.google.com/view/sun-shoe/about#h.whv3cal6opec");
    }
}
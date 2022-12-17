package com.example.sunshoe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class AboutUsWeb extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us_web, container, false);
        WebView webview = (WebView) view.findViewById(R.id.webview);
        webview.loadUrl("https://sites.google.com/view/sun-shoe/about#h.whv3cal6opec");
        return view;
    }
}
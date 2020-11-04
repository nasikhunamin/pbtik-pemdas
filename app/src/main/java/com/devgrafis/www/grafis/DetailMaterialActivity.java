package com.devgrafis.www.grafis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailMaterialActivity extends AppCompatActivity {

    private TextView title, subtitle, material;
    private ImageView imgSource;
    private WebView webView;
    private String URL_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_material);
        webView = findViewById(R.id.openWeb);
        webView.setWebViewClient(new WebViewClient());
        URL_ID = getIntent().getStringExtra("content");
        webView.loadUrl("https://grafisdev.000webhostapp.com/"+URL_ID);
        /*webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);*/
        /*title = findViewById(R.id.titleDetail);
        subtitle = findViewById(R.id.subtitleDetail);
        material = findViewById(R.id.materialDetail);*/
        imgSource = findViewById(R.id.imgSourceDetail);
        /*String s = getIntent().getStringExtra("content");
        title.setText(getIntent().getStringExtra("title"));
        subtitle.setText(getIntent().getStringExtra("subtitle"));
        material.setText(s);*/
        Glide.with(this).load(getIntent().getIntExtra("imgSource", 0)).into(imgSource);
    }
}

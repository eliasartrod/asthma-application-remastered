package com.example.asthmaapplication.main.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.inventory.databinding.ActivityWebviewBinding;

public class WebViewActivity extends BaseActivity{
    private static final String URL = "url.argument";

    private WebView webView;
    ActivityWebviewBinding binding;

    public static Intent getIntent(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(URL, url);
        return intent;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWebviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(URL);
    }
}

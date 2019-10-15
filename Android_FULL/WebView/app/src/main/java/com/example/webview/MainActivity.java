package com.example.webview;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private String URL = "http://google.com.br";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WebView webview = findViewById(R.id.webview);
        Button back = findViewById(R.id.voltar);
        Button refresh = findViewById(R.id.atualizar);
        Button forward = findViewById(R.id.avancar);
        final ProgressBar progress = findViewById(R.id.progress);
        progress.setVisibility(View.INVISIBLE);
        webview.loadUrl(URL);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                super.onPageStarted(view,url,favicon);
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url){
                super.onPageFinished(view, url);
                progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                super.onReceivedError(view, request, error);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webview.canGoBack()){
                    webview.goBack();
                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webview.canGoForward()){
                    webview.goForward();
                }
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.reload();
            }
        });
    }
}

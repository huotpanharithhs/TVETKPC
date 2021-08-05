package com.kpc.tvet.webview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.progressindicator.CircularProgressIndicator;

public class WebViewClt extends WebViewClient {
    private Activity activity;
    private CircularProgressIndicator progress;
    private WebView webView;
    private LinearLayoutCompat lNoInternet;
    ConstraintLayout progressLayout;
    public WebViewClt(Activity activity, CircularProgressIndicator progress, WebView webView,LinearLayoutCompat lNoInternet,ConstraintLayout progressLayout) {
        this.activity = activity;
        this.progress = progress;
        this.webView = webView;
        this.lNoInternet = lNoInternet;
        this.progressLayout = progressLayout;
    }
//    @Override
//    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
//        if (url.contains("https://www.google.com"))
//            return false;
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//        activity.startActivity(intent);
//        return true;
//    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        Toast.makeText(activity, "Got Error! " + error, Toast.LENGTH_SHORT).show();
        try {
            webView.stopLoading();
        } catch (Exception e) {
        }
        lNoInternet.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        progressLayout.setVisibility(View.INVISIBLE);
    }
}


package com.kpc.tvet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.kpc.tvet.R;
import com.kpc.tvet.webview.DetectConnection;
import com.kpc.tvet.webview.WebChromeClt;
import com.kpc.tvet.webview.WebViewClt;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView = null;
    private LinearLayoutCompat mNoInternet = null;
    private AppCompatButton mRetry = null;
    private ConstraintLayout mProgressLayout = null;
    private CircularProgressIndicator mProgress = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        mWebView = findViewById(R.id.webView);
        mNoInternet = findViewById(R.id.no_internet);
        mRetry = findViewById(R.id.retry_button);
        mProgress = findViewById(R.id.progress);
        mProgressLayout = findViewById(R.id.layout_progress);
        mProgress.setIndeterminate(true);
        reloadWebView();
        mRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressLayout.setVisibility(View.VISIBLE);
                reloadWebView();
            }
        });

    }

    public void reloadWebView() {
        if (DetectConnection.checkInternetConnection(this)) {
            mNoInternet.setVisibility(View.INVISIBLE);
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setBuiltInZoomControls(true);
            mWebView.setWebViewClient(new WebViewClt(this, mProgress, mWebView, mNoInternet,mProgressLayout));
            mWebView.setWebChromeClient(new WebChromeClt(this));
            mWebView.loadUrl(getString(R.string.site_url));

        } else {
            mNoInternet.setVisibility(View.VISIBLE);
            mProgress.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
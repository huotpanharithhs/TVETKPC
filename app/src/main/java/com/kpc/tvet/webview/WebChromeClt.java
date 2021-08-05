package com.kpc.tvet.webview;

import android.app.Activity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
public class WebChromeClt extends WebChromeClient {

    private Activity activity;

    public WebChromeClt(Activity activity) {
        this.activity = activity;
    }

    public void onProgressChanged(WebView view, int progress) {
        // The progress meter will automatically disappear when we reach 100%
        activity.setProgress(progress * 1000);
    }
}

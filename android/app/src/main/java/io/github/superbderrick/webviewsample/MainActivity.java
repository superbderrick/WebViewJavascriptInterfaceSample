package io.github.superbderrick.webviewsample;

import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    private final static String HTML_PATH =  "file:///android_asset/test.html";

    private WebView mWebView;
    private Button mTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //For GUI
        mWebView = findViewById(R.id.webView);
        mTestButton = findViewById(R.id.testButton);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");

        mWebView.addJavascriptInterface(new JSInterfaces(MainActivity.this), "launcher");

        mWebView.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public boolean onJsAlert(final WebView view, String url, final String message, JsResult result)
            {
                Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
                return super.onJsAlert(view, url, message, result);
            }
        });

        mWebView.loadUrl(HTML_PATH);


        // Say Hello from Native To Web
        mTestButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mWebView.loadUrl("javascript:show('HI WEB')");
                mWebView.loadUrl("javascript:check()");
            }
        });

    }

}

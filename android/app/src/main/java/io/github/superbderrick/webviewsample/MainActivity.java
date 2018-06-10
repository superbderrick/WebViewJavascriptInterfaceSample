package io.github.superbderrick.webviewsample;

import android.annotation.TargetApi;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    private final static String HTML_PATH =  "file:///android_asset/test.html";

    private WebView mWebView;
    private Button mTestButton;
    private TextView mTextView;

    private Handler mHandler;
    private String mLogString = "";

    private JavascriptInterface mJavascriptInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.webView);
        mTestButton = findViewById(R.id.testButton);
        mTextView = findViewById(R.id.testTextView);

        mHandler = new Handler();

        mJavascriptInterface = new JavascriptInterface(MainActivity.this);
        mJavascriptInterface.setOnJavascriptEventListener(new JavascriptInterface.OnJavaScriptEventListener() {
            @Override
            public void sayHelloMessageFromWEB(final String keyword) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mLogString += keyword;
                        mTextView.setText(mLogString);
                    }
                });

            }
        });

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        mWebView.addJavascriptInterface(mJavascriptInterface , "interface");
        mWebView.loadUrl(HTML_PATH);
        mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {

                mWebView.loadUrl("javascript:setup()");
            }
            @TargetApi(21)
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                return super.shouldOverrideUrlLoading(view, request);
            }

            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {

                return super.shouldOverrideKeyEvent(view, event);
            }
        });

        // Say Hello from Native To Web
        mTestButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mWebView.loadUrl("javascript:writeLOG()");
            }
        });

    }

}

package io.github.superbderrick.webviewsample;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    private final static String HTML_PATH =  "https://s3.ap-northeast-2.amazonaws.com/scarkoo/20180501/index.html";

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            mWebView.getSettings().setMixedContentMode(mWebView.getSettings().MIXED_CONTENT_ALWAYS_ALLOW);
        }


        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");

        mWebView.getSettings().setBuiltInZoomControls(true);
        // 화면에 맞게 WebView 사이즈를 정의
        mWebView.getSettings().setLoadWithOverviewMode(true);
        // ViewPort meta tag를 활성화 여부
        mWebView.getSettings().setUseWideViewPort(true);
        // 줌 컨트롤 사용 여부
        mWebView.getSettings().setDisplayZoomControls(false);
        // 사용자 제스처를 통한 줌 기능 활성화 여부
        mWebView.getSettings().setSupportZoom(false);
        // TextEncoding 이름 정의

        // Setting Local Storage
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);

        // 캐쉬 사용 방법을 정의
        mWebView.getSettings().setCacheMode(mWebView.getSettings().LOAD_NO_CACHE);


        mWebView.addJavascriptInterface(mJavascriptInterface , "interface");
        mWebView.loadUrl(HTML_PATH);
        mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {

                Log.d("derrick" , "onPageFinished: " + url);
                mWebView.loadUrl("javascript:setup()");
            }
            @TargetApi(21)
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                Log.d("derrick" , "shouldOverrideUrlLoading: " + request);
                return super.shouldOverrideUrlLoading(view, request);
            }

            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {

                Log.d("derrick" , "shouldOverrideKeyEvent: " + event);
                return super.shouldOverrideKeyEvent(view, event);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

                Log.d("derrick" , "onReceivedError: " + error);
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

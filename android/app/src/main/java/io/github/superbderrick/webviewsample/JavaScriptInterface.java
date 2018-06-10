package io.github.superbderrick.webviewsample;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


public class JavascriptInterface
{
    public interface OnJavaScriptEventListener {
        void sayHelloMessageFromWEB(String keyword);
    }

    private JavascriptInterface.OnJavaScriptEventListener listener;

    public void setOnJavascriptEventListener(OnJavaScriptEventListener listener) {
        this.listener = listener;
    }

    private Context mContext;

    public JavascriptInterface(Context context)
    {
        mContext = context;
    }

    @android.webkit.JavascriptInterface
    public void showInfo(String info)
    {
        Toast.makeText(mContext,info, Toast.LENGTH_SHORT).show();
    }

    @android.webkit.JavascriptInterface
    public void testFunction(String sentence) {
        listener.sayHelloMessageFromWEB(sentence);
    }

}

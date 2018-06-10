package io.github.superbderrick.webviewsample;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;


public class JSInterfaces
{
    private Context mContext;

    public JSInterfaces(Context context)
    {
        mContext = context;
    }

    @JavascriptInterface
    public void showInfo(String info)
    {
        Toast.makeText(mContext,info, Toast.LENGTH_SHORT).show();
    }
}

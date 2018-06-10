package io.github.superbderrick.webviewsample;

import android.webkit.JavascriptInterface;

import org.json.JSONException;
import org.json.JSONObject;

public class JavaScriptInterface {

    private OnJavaScriptEventListener listener;

    public interface OnJavaScriptEventListener {
        void sayHelloMessageFromWEB(String keyword);
    }

    //    public void setOnJavascriptEventListener(OnJavaScriptEventListener listener) {
//        this.listener = listener;
//    }

    private String id;

    public JavaScriptInterface(String id) {
        this.id = id;
    }

    @JavascriptInterface
    public String toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put("id", this.id);
        } catch (JSONException e) {
            //no hacemos nada
        }
        return json.toString();
    }




//
//    public JavaScriptInterface() {
//
//    }
//


}

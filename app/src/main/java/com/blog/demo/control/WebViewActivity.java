package com.blog.demo.control;

import com.blog.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends Activity {
	private WebView mWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_webview);
		
		mWebView = (WebView) findViewById(R.id.webView);
		mWebView.setWebChromeClient(new WebChromeClient());
		mWebView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		
		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);

		
//		mWebView.loadUrl("http://www.baidu.com");
		mWebView.loadUrl("file:///android_asset/webview.html");
		
		mWebView.addJavascriptInterface(new JSInterface(), "jsListener");
	}
	
	@Override
	public void onBackPressed() {
		if (mWebView.canGoBack()) {
			mWebView.goBack();
		} else {
			super.onBackPressed();
		}
	}
	
	public class JSInterface {
		@JavascriptInterface
		public void onCall() {
			Toast.makeText(WebViewActivity.this, "Hello World", Toast.LENGTH_LONG).show();
			
			mWebView.loadUrl("javascript:myFunction()");
		}
	}
	
}

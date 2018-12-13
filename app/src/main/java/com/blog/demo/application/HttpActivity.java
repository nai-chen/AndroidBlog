package com.blog.demo.application;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/6/20.
 */

public class HttpActivity extends Activity implements View.OnClickListener {

    private final static String SERVER = "http://192.168.4.58:3000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_http);

        findViewById(R.id.request_get).setOnClickListener(this);
        findViewById(R.id.request_post).setOnClickListener(this);
        findViewById(R.id.request_url).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.request_get:
                new Thread(){
                    @Override
                    public void run() {
                        getRequest();
                    }
                }.start();
                break;
            case R.id.request_post:
                new Thread(){
                    @Override
                    public void run() {
                        postRequest();
                    }
                }.start();
                break;
            case R.id.request_url:
                new Thread(){
                    @Override
                    public void run() {
                        urlRequest();
                    }
                }.start();
                break;
        }
    }

    private void getRequest() {
        try {
            String paramStr = translateStreamToString(getHttpEntity().getContent());

            String url = SERVER + "?" + paramStr;
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(new HttpGet(url));

            String content =
                    translateStreamToString(response.getEntity().getContent());

            LogUtil.log("HttpActivity", "getRequest: " + content);
            Log.i("Activity", content);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private HttpEntity getHttpEntity() throws IOException {
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        paramList.add(new BasicNameValuePair("name", "Mike"));
        paramList.add(new BasicNameValuePair("age", "18"));
        return new UrlEncodedFormEntity(paramList, HTTP.UTF_8);
    }

    private String translateStreamToString(InputStream input) {
        try {
            StringBuffer sBuffer = new StringBuffer();

            String line = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            while ((line = br.readLine()) != null) {
                sBuffer.append(line);
            }
            br.close();
            input.close();
            return sBuffer.toString();
        } catch (IOException e) {
        }

        return "";
    }

    private void postRequest() {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost request = new HttpPost(SERVER);

        try {
            request.setEntity(getHttpEntity());

            HttpResponse response = httpClient.execute(request);
            String content =
                    translateStreamToString(response.getEntity().getContent());

            LogUtil.log("HttpActivity", "postRequest: " + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void urlRequest() {
        try {
            URL url = new URL(SERVER + "?name=Mike&age=18");
            URLConnection con = url.openConnection();
            InputStream input = con.getInputStream();
            String content = translateStreamToString(input);

            LogUtil.log("HttpActivity", "urlRequest: " + content);

            JSONObject obj = new JSONObject(content);
            LogUtil.log("HttpActivity", "urlRequest name: " + obj.getString("name"));
            LogUtil.log("HttpActivity", "urlRequest age: " + obj.getInt("age"));
        } catch (Exception e) {

        }

    }

}

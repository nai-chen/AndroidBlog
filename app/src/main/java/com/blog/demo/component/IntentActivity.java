package com.blog.demo.component;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import java.io.File;

/**
 * Created by cn on 2017/12/25.
 */

public class IntentActivity extends Activity implements View.OnClickListener {
    private final static int REQUEST_CODE = 1000;
    private final static int REQUEST_CODE_PHOTO		= 1001;
    private final static int REQUEST_CODE_PICTURE	= 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intent);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btn10).setOnClickListener(this);
        findViewById(R.id.btn11).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                btn1Click();
                break;
            case R.id.btn2:
                btn2Click();
                break;
            case R.id.btn3:
                btn3Click();
                break;
            case R.id.btn4:
                btn4Click();
                break;
            case R.id.btn5:
                btn5Click();
                break;
            case R.id.btn6:
                btn6Click();
                break;
            case R.id.btn7:
                btn7Click();
                break;
            case R.id.btn8:
                btn8Click();
                break;
            case R.id.btn9:
                btn9Click();
                break;
            case R.id.btn10:
                btn10Click();
                break;
            case R.id.btn11:
                btn11Click();
                break;
        }
    }

    private void btn1Click() {
        Intent intent = new Intent(this, IntentActivity.class);
        startActivity(intent);
    }

    private void btn2Click() {
        Intent intent = new Intent("com.blog.demo.action.intent");
        startActivity(intent);
    }

    private void btn3Click() {
        Intent targetIntent = new Intent("com.blog.demo.action.intent");
        Intent intent = Intent.createChooser(targetIntent, "选择");
        startActivity(intent);
    }

    private void btn4Click() {
        Intent intent = new Intent(this, IntentActivity.class);
        intent.addCategory("com.blog.demo.MY_CATREORY");
        startActivity(intent);
    }

    private void btn5Click() {
        Intent intent = new Intent(this, IntentActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void btn6Click() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    private void btn7Click() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }

    private void btn8Click() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }

    private void btn9Click() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
    }

    private void btn10Click() {
        String outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/phone.jpg";

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(outputFile)));
        startActivityForResult(intent, REQUEST_CODE_PHOTO);
    }

    private void btn11Click() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            LogUtil.log("IntentActivity", "Return " + resultCode);
        }
    }

    @Override
    public void onBackPressed() {
        LogUtil.log("IntentActivity", "onBackPressed");
        setResult(RESULT_OK);

        super.onBackPressed();
    }
}

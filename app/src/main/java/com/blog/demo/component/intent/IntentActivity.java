package com.blog.demo.component.intent;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.blog.demo.R;

import java.io.IOException;

public class IntentActivity extends Activity implements View.OnClickListener {

    private static final int REQUEST_CODE_PHOTO     = 100;
    private static final int REQUEST_CODE_PICTURE   = 101;

    private View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_component_intent);

        view = findViewById(R.id.view_content);

        findViewById(R.id.btn_explicit).setOnClickListener(this);
        findViewById(R.id.btn_implicit).setOnClickListener(this);
        findViewById(R.id.btn_implicit_multi).setOnClickListener(this);
        findViewById(R.id.btn_implicit_choose).setOnClickListener(this);
        findViewById(R.id.btn_implicit_category).setOnClickListener(this);
        findViewById(R.id.btn_home).setOnClickListener(this);
        findViewById(R.id.btn_dial).setOnClickListener(this);
        findViewById(R.id.btn_call).setOnClickListener(this);
        findViewById(R.id.btn_web).setOnClickListener(this);
        findViewById(R.id.btn_photo).setOnClickListener(this);
        findViewById(R.id.btn_picture).setOnClickListener(this);    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_explicit) {
            startActivity(new Intent(this, IntentSampleActivity.class));
        } else if (v.getId() == R.id.btn_implicit) {
            startActivity(new Intent("com.blog.demo.action.intent"));
        } else if (v.getId() == R.id.btn_implicit_multi) {
            startActivity(new Intent("com.blog.demo.action.sample"));
        } else if (v.getId() == R.id.btn_implicit_choose) {
            Intent targetIntent = new Intent("com.blog.demo.action.sample");
            Intent intent = Intent.createChooser(targetIntent, "选择");
            startActivity(intent);
        } else if (v.getId() == R.id.btn_implicit_category) {
            Intent intent = new Intent("com.blog.demo.action.sample");
            intent.addCategory("com.blog.demo.MY_CATREORY");
            intent.putExtra("value", "This is from IntentActivity");
            startActivity(intent);
        } else if (v.getId() == R.id.btn_home) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_dial) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } else if (v.getId() == R.id.btn_call) {
            callPhone();
        } else if (v.getId() == R.id.btn_web) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://www.baidu.com"));
            startActivity(intent);
        } else if (v.getId() == R.id.btn_photo) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CODE_PHOTO);
        } else if (v.getId() == R.id.btn_picture) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_CODE_PICTURE);
        }
    }

    private void callPhone() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1000);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            callPhone();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PHOTO) {
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            view.setBackground(new BitmapDrawable(bitmap));
        } else if (requestCode == REQUEST_CODE_PICTURE) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                view.setBackground(new BitmapDrawable(bitmap));
            } catch (IOException e) {

            }
        }
    }
}

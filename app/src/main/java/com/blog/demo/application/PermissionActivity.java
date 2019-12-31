package com.blog.demo.application;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import com.blog.demo.R;

public class PermissionActivity extends Activity implements View.OnClickListener {
    private static final int REQUEST_CODE_CALL_PHONE = 66;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_application_permission);
        findViewById(R.id.btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        REQUEST_CODE_CALL_PHONE);
            }
        }
    }

    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                   @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            } else {
                Toast.makeText(this, "权限被拒绝", Toast.LENGTH_LONG).show();
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CALL_PHONE)) {
                    new AlertDialog.Builder(this)
                            .setMessage("该功能需要电话访问权限")
                            .setPositiveButton("确认", null)
                            .create().show();
                }
            }
        }
    }
}

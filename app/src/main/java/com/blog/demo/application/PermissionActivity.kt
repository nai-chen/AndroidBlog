package com.blog.demo.application

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.blog.demo.R

class PermissionActivity : Activity(), View.OnClickListener {

    private val REQUEST_CODE_CALL_PHONE = 66

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_application_permission)
        findViewById<Button>(R.id.btn).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
                callPhone()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),
                    REQUEST_CODE_CALL_PHONE)
            }
        }
    }

    private fun callPhone() {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:10086")
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone()
            } else {
                Toast.makeText(this, "权限被拒绝", Toast.LENGTH_LONG).show()
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CALL_PHONE)) {
                    AlertDialog.Builder(this)
                        .setMessage("该功能需要电话访问权限")
                        .setPositiveButton("确认", null)
                        .create().show()
                }
            }
        }
    }

}
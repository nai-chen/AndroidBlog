package com.blog.demo.component.intent

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.blog.demo.R
import java.io.IOException

class IntentActivity : Activity(), View.OnClickListener {

    private val REQUEST_CODE_PHOTO = 100
    private val REQUEST_CODE_PICTURE = 101

    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_intent)
        view = findViewById(R.id.view_content)

        findViewById<Button>(R.id.btn_explicit).setOnClickListener(this)
        findViewById<Button>(R.id.btn_implicit).setOnClickListener(this)
        findViewById<Button>(R.id.btn_implicit_multi).setOnClickListener(this)
        findViewById<Button>(R.id.btn_implicit_choose).setOnClickListener(this)
        findViewById<Button>(R.id.btn_implicit_category).setOnClickListener(this)
        findViewById<Button>(R.id.btn_home).setOnClickListener(this)
        findViewById<Button>(R.id.btn_dial).setOnClickListener(this)
        findViewById<Button>(R.id.btn_call).setOnClickListener(this)
        findViewById<Button>(R.id.btn_web).setOnClickListener(this)
        findViewById<Button>(R.id.btn_photo).setOnClickListener(this)
        findViewById<Button>(R.id.btn_picture).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_explicit) {
            startActivity(Intent(this, IntentSampleActivity::class.java))
        } else if (v.id == R.id.btn_implicit) {
            startActivity(Intent("com.blog.demo.action.intent"))
        } else if (v.id == R.id.btn_implicit_multi) {
            startActivity(Intent("com.blog.demo.action.sample"))
        } else if (v.id == R.id.btn_implicit_choose) {
            val targetIntent = Intent("com.blog.demo.action.sample")
            val intent = Intent.createChooser(targetIntent, "选择")
            startActivity(intent)
        } else if (v.id == R.id.btn_implicit_category) {
            val intent = Intent("com.blog.demo.action.sample")
            intent.addCategory("com.blog.demo.MY_CATREORY")
            intent.putExtra("value", "This is from IntentActivity")
            startActivity(intent)
        } else if (v.id == R.id.btn_home) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            startActivity(intent)
        } else if (v.id == R.id.btn_dial) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } else if (v.id == R.id.btn_call) {
            callPhone()
        } else if (v.id == R.id.btn_web) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://www.baidu.com")
            startActivity(intent)
        } else if (v.id == R.id.btn_photo) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_CODE_PHOTO)
        } else if (v.id == R.id.btn_picture) {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE_PICTURE)
        }
    }

    private fun callPhone() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            == PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1000)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            callPhone()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_CODE_PHOTO) {
            val bundle = data.extras
            val bitmap = bundle?.get("data") as Bitmap?
            view.background = BitmapDrawable(resources, bitmap)
        } else if (requestCode == REQUEST_CODE_PICTURE) {
            val uri = data.data
            try {
                if (uri != null) {
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri))
                    view.background = BitmapDrawable(resources, bitmap)
                }
            } catch (e: IOException) {
            }
        }
    }

}
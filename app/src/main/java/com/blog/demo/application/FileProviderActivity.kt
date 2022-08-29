package com.blog.demo.application

import android.app.Activity
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import com.blog.demo.LogTool
import com.blog.demo.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class FileProviderActivity : Activity(), View.OnClickListener {

    private lateinit var mRadioGroup: RadioGroup
    private lateinit var mTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_application_file_provider)

        mRadioGroup = findViewById(R.id.radio_group)
        mTextView = findViewById(R.id.text_view)

        findViewById<Button>(R.id.btn_files_path).setOnClickListener(this)
        findViewById<Button>(R.id.btn_cache_path).setOnClickListener(this)
        findViewById<Button>(R.id.btn_external_files_path).setOnClickListener(this)
        findViewById<Button>(R.id.btn_external_cache_path).setOnClickListener(this)
        findViewById<Button>(R.id.btn_external_path).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var fileUri: Uri? = null
        when(v?.id) {
            R.id.btn_files_path -> {
                fileUri = getFileUri(filesDir)
            }
            R.id.btn_cache_path -> {
                fileUri = getFileUri(cacheDir)
            }
            R.id.btn_external_files_path -> {
                fileUri = getFileUri(getExternalFilesDir(null))
            }
            R.id.btn_external_cache_path -> {
                fileUri = getFileUri(externalCacheDir)
            }
            R.id.btn_external_path -> {
                fileUri = getFileUri(Environment.getExternalStorageDirectory())
            }
        }
        if (fileUri != null) {
            LogTool.logi("FileProvider", fileUri.toString())
        }
    }

    private fun getFileUri(file: File?): Uri {
        var txtFile = File(file?.absolutePath + "/txt")
        if (!txtFile.exists()) {
            txtFile.mkdirs()
        }

        var file = File(txtFile, "hello.txt")
        LogTool.logi("FileProvider", file.absolutePath)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(this, "com.blog.demo.file_provider", file)
        } else {
            return Uri.fromFile(file)
        }
    }

}
package com.blog.demo.component

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.FileProvider
import com.blog.demo.LogTool
import com.blog.demo.R
import java.io.File

class FileProviderActivity : Activity(), View.OnClickListener {

    private val REQUEST_CODE_PHOTO		            = 100

    private var mFileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_file_provider)

        findViewById<Button>(R.id.btn_files_path).setOnClickListener(this)
        findViewById<Button>(R.id.btn_cache_path).setOnClickListener(this)
        findViewById<Button>(R.id.btn_external_files_path).setOnClickListener(this)
        findViewById<Button>(R.id.btn_external_cache_path).setOnClickListener(this)
        findViewById<Button>(R.id.btn_external_path).setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v?.id) {
            R.id.btn_files_path -> {
                mFileUri = getFileUri(filesDir)
            }
            R.id.btn_cache_path -> {
                mFileUri = getFileUri(cacheDir)
            }
            R.id.btn_external_files_path -> {
                mFileUri = getFileUri(getExternalFilesDir(null))
            }
            R.id.btn_external_cache_path -> {
                mFileUri = getFileUri(externalCacheDir)
            }
            R.id.btn_external_path -> {
                mFileUri = getFileUri(Environment.getExternalStorageDirectory())
            }
        }
        var fileUri = mFileUri
        if (fileUri != null) {
            LogTool.logi("FileProvider", fileUri.toString())

            var intent = Intent()
            intent.action = MediaStore.ACTION_IMAGE_CAPTURE
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivityForResult(intent, REQUEST_CODE_PHOTO)
        }
    }

    private fun getFileUri(file: File?): Uri {
        if (file != null && !file.exists()) {
            file.mkdirs()
        }

        var imageFile = File(file, "tmp_${System.currentTimeMillis()}.jpg")
        if (imageFile.exists()) {
            imageFile.delete()
        }
        LogTool.logi("FileProvider", imageFile.absolutePath)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(this, "com.blog.demo.file_provider", imageFile)
        } else {
            return Uri.fromFile(imageFile)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_PHOTO) {
            if (resultCode == RESULT_OK) {
                findViewById<ImageView>(R.id.image_view).setImageURI(mFileUri)
            }
        }
    }
}
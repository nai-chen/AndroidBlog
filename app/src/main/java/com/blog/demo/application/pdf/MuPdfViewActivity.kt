package com.blog.demo.application.pdf

import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artifex.mupdf.fitz.Document
import com.artifex.mupdf.fitz.android.AndroidDrawDevice
import com.blog.demo.LogTool
import com.blog.demo.R

class MuPdfViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_material_design_recycler_view_normal)

        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        var adapter = RecyclerViewPdfAdapter(this)
        recyclerView.adapter = adapter

        var displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        Thread {
            var timeMillis = System.currentTimeMillis()

            var byteArray = assets.open("example.pdf").readBytes()
            var document = Document.openDocument(byteArray, "application/octet-stream")
            var pageCount = document.countPages()

            LogTool.logi("MuPdfViewActivity", "pageCount = $pageCount")
            if (pageCount >= 1) {
                var bitmapList = mutableListOf<Bitmap>()

                for (pageIndex in 0 until pageCount) {
                    var page = document.loadPage(pageIndex)
                    var matrix = AndroidDrawDevice.fitPageWidth(page, displayMetrics.widthPixels)
                    var bitmap = AndroidDrawDevice.drawPage(page, matrix)
                    bitmapList.add(bitmap)
                }
                adapter.setBitmapList(bitmapList)

                LogTool.logi("MuPdfViewActivity", " ${System.currentTimeMillis() - timeMillis}" )
                recyclerView.post {
                    adapter.notifyDataSetChanged()
                }
            }
        }.start()
    }

}
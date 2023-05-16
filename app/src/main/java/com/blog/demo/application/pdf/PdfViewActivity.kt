package com.blog.demo.application.pdf

import android.app.Activity
import android.os.Bundle
import com.blog.demo.LogTool
import com.blog.demo.R
import com.github.barteksc.pdfviewer.PDFView

class PdfViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_application_pdf_view)

        var pdfView: PDFView = findViewById(R.id.pdf_view)
//        pdfView.useBestQuality(true)
//        pdfView.enableAntialiasing(true)
        pdfView.fromAsset("example.pdf")
//            .enableSwipe(false)
//            .enableDoubletap(false)
//            .pages(1, 2, 3, 4, 5)
//            .defaultPage(3)
//            .swipeHorizontal(true)
            .enableAnnotationRendering(true)
            .spacing(40)
            .onLoad {
                LogTool.logi("PdfViewActivity", "nbpage = $it")
            }.onPageChange { page, pageCount ->
                LogTool.logi("PdfViewActivity", "page = $page, pageCount = $pageCount")
            }.onPageScroll { page, positionOffset ->
                LogTool.logi("PdfViewActivity", "page = $page, positionOffset = $positionOffset")
                LogTool.logi("PdfViewActivity", "canScrollVertically(1) = ${pdfView.canScrollVertically(1)}")
                LogTool.logi("PdfViewActivity", "canScrollVertically(-1) = ${pdfView.canScrollVertically(-1)}")
            }.load()
    }

}
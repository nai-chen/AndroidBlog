package com.blog.demo.component.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blog.demo.LogTool
import com.blog.demo.R

class FragmentLeft : Fragment() {

    private val LOG_TAG = "FragmentLeft"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogTool.logi(LOG_TAG, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogTool.logi(LOG_TAG, "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogTool.logi(LOG_TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_left, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        LogTool.logi(LOG_TAG, "onActivityCreated")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogTool.logi(LOG_TAG, "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        LogTool.logi(LOG_TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        isHidden
        LogTool.logi(LOG_TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        LogTool.logi(LOG_TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        LogTool.logi(LOG_TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogTool.logi(LOG_TAG, "onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogTool.logi(LOG_TAG, "onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        LogTool.logi(LOG_TAG, "onDetach")
    }

}
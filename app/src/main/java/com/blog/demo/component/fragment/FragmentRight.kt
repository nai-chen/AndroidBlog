package com.blog.demo.component.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blog.demo.LogTool
import com.blog.demo.R

class FragmentRight : Fragment() {

    private val LOG_TAG = "FragmentRight"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogTool.logi(LOG_TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_right, container, false)
    }

}
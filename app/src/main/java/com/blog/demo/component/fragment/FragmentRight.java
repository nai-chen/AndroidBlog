package com.blog.demo.component.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class FragmentRight extends Fragment {
    private static final String LOG_TAG = "FragmentRight";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogTool.logi(LOG_TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_right, container, false);
    }

}

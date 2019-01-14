package com.blog.demo.component.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class FragmentLeft extends Fragment {
    private static final String LOG_TAG = "FragmentLeft";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogTool.logi(LOG_TAG, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogTool.logi(LOG_TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogTool.logi(LOG_TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_left, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogTool.logi(LOG_TAG, "onActivityCreated");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogTool.logi(LOG_TAG, "onViewCreated");
    }

    @Override
    public void onStart() {
        super.onStart();

        LogTool.logi(LOG_TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        isHidden();
        LogTool.logi(LOG_TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        LogTool.logi(LOG_TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        LogTool.logi(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        LogTool.logi(LOG_TAG, "onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        LogTool.logi(LOG_TAG, "onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        LogTool.logi(LOG_TAG, "onDetach");
    }

}

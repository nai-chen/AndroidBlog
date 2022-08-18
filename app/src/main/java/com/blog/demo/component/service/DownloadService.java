package com.blog.demo.component.service;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class DownloadService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DownloadService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

}

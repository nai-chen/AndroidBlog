package com.blog.demo;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.File;
import java.io.IOException;

import static android.content.ContentValues.TAG;

/**
 * Created by cn on 2018/7/3.
 */

public class DemoApplication extends Application {

    private PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();

        mPatchManager = new PatchManager(this);
        mPatchManager.init("1.0.1");
        mPatchManager.loadPatch();
        LogUtil.log("DemoApplication", "loadPatch");
        try {
            String patchFileString = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + "demo.apatch";
            LogUtil.log("DemoApplication", patchFileString);
            //3）添加patch
            mPatchManager.addPatch(patchFileString);
        } catch (IOException e){
            Log.e("demo/DemoApplication", "", e);
        }
    }

}

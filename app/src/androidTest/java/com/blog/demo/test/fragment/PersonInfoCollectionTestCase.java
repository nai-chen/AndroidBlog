package com.blog.demo.test.fragment;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.blog.demo.R;
import com.blog.demo.fragment.PersonalInfoCollectionActivity;
import com.blog.demo.fragment.PersonalInfoDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/3/21.
 */

public class PersonInfoCollectionTestCase extends ActivityInstrumentationTestCase2<PersonalInfoCollectionActivity> {
    private PersonalInfoCollectionActivity mActivity;
    private EditText mEtName;
    private RadioGroup mRgGender;
    private RadioButton mRbMale, mRbFeMale;
    private CheckBox mCbFavMovie, mCbFavTravel, mCbFavSing;
    private Button mBtnCommit;

    public PersonInfoCollectionTestCase() {
        super(PersonalInfoCollectionActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        mActivity = getActivity();
        mEtName = (EditText) mActivity.findViewById(R.id.et_name);
        mRgGender = (RadioGroup) mActivity.findViewById(R.id.rg_gender);
        mRbMale = (RadioButton) mActivity.findViewById(R.id.rb_male);
        mRbFeMale = (RadioButton) mActivity.findViewById(R.id.rb_female);
        mCbFavMovie = (CheckBox) mActivity.findViewById(R.id.cb_fav_movie);
        mCbFavTravel = (CheckBox) mActivity.findViewById(R.id.cb_fav_travel);
        mCbFavSing = (CheckBox) mActivity.findViewById(R.id.cb_fav_sing);
        mBtnCommit = (Button) mActivity.findViewById(R.id.btn_commit);
    }

    public void testButtonStatus() throws Throwable {
        // 设置name为空
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                mEtName.setText("");
            }
        });
        assertFalse(mBtnCommit.isEnabled());

        // 设置name为Jack
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                mEtName.setText("Jack");
            }
        });
//        Thread.sleep(5000);
        assertTrue(mBtnCommit.isEnabled());
    }

    public void testButtonClick() throws Throwable {
        // 设置name为Jack，选择性别为女
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                mEtName.setText("Jack");
                mRbFeMale.setChecked(true);
            }
        });

        Instrumentation inst = getInstrumentation();
        // 增加一个ActivityMonitor
        Instrumentation.ActivityMonitor monitor = inst.addMonitor(
                PersonalInfoDetailActivity.class.getName(), null, false);
        // 初始为0
        assertEquals(0, monitor.getHits());

        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mBtnCommit.performClick();
                }
            });
            Activity waitActivity = monitor.waitForActivity();
            assertEquals(1, monitor.getHits());
            Intent intent = waitActivity.getIntent();
            assertEquals("Jack", intent.getStringExtra("name"));
            assertEquals("女", intent.getStringExtra("gender"));
            assertEquals("暂无爱好", intent.getStringExtra("favorite"));
            waitActivity.finish();
        } finally {
            inst.removeMonitor(monitor);
        }

        // 选择爱好
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCbFavMovie.setChecked(true);
                mCbFavTravel.setChecked(true);
                mCbFavSing.setChecked(true);
            }
        });

        inst = getInstrumentation();
        monitor = inst.addMonitor(
                PersonalInfoDetailActivity.class.getName(), null, false);

        assertEquals(0, monitor.getHits());

        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mBtnCommit.performClick();
                }
            });
            Activity waitActivity = monitor.waitForActivity();
            assertEquals(1, monitor.getHits());
            Intent intent = waitActivity.getIntent();
            assertEquals("Jack", intent.getStringExtra("name"));
            assertEquals("女", intent.getStringExtra("gender"));
            assertEquals("电影,旅游,唱歌", intent.getStringExtra("favorite"));
            waitActivity.finish();
        } finally {
            inst.removeMonitor(monitor);
        }
    }

    public void testJoin() {
        List<String> favList = new ArrayList<String>();
        String deli = ",";
        assertEquals("", mActivity.join(favList, deli));

        favList.add("电影");
        assertEquals("电影", mActivity.join(favList, deli));

        favList.add("旅游");
        assertEquals("电影,旅游", mActivity.join(favList, deli));

        favList.add("唱歌");
        assertEquals("电影,旅游,唱歌", mActivity.join(favList, deli));
    }

}

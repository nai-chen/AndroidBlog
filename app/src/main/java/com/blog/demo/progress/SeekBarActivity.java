package com.blog.demo.progress;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

/**
 * Created by cn on 2017/10/27.
 */

public class SeekBarActivity extends Activity implements SeekBar.OnSeekBarChangeListener {

    private TextView mTvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_seekbar);

        mTvInfo = (TextView) findViewById(R.id.tv_info);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar1);
        seekBar.setOnSeekBarChangeListener(this);

        seekBar = (SeekBar) findViewById(R.id.seekbar2);
        seekBar.setOnSeekBarChangeListener(this);

        seekBar = (SeekBar) findViewById(R.id.seekbar3);
        seekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mTvInfo.setText("选择" + progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        LogUtil.log("SeekBarActivity", "onStartTrackingTouch");
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        LogUtil.log("SeekBarActivity", "onStopTrackingTouch");
    }

}

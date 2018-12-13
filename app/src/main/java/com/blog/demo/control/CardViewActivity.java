package com.blog.demo.control;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.blog.demo.R;

/**
 * Created by cn on 2018/3/19.
 */

public class CardViewActivity extends Activity {
    private CardView mCardView;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cardview);

        mCardView = (CardView) findViewById(R.id.cardView);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mSeekBar = (SeekBar) findViewById(R.id.seekbar);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (radioGroup.getCheckedRadioButtonId() == R.id.rb_card_elevation) {
                    mCardView.setCardElevation(progress);
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.rb_card_corner_radius) {
                    mCardView.setRadius(progress);
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.rb_content_padding) {
                    mCardView.setContentPadding(progress, progress, progress, progress);
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.rb_compat_padding) {

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}

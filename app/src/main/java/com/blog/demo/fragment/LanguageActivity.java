package com.blog.demo.fragment;

import com.blog.demo.R;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

public class LanguageActivity extends Activity implements View.OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_language);

		findViewById(R.id.btn_cn).setOnClickListener(this);
		findViewById(R.id.btn_tw).setOnClickListener(this);
		findViewById(R.id.btn_us).setOnClickListener(this);
		findViewById(R.id.btn_new).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Resources res = getResources();
		Configuration config = res.getConfiguration();
		if (v.getId() == R.id.btn_cn) {
			config.locale = Locale.CHINA;
		} else if (v.getId() == R.id.btn_tw) {
			config.locale = Locale.TRADITIONAL_CHINESE;
		} else if (v.getId() == R.id.btn_us) {
			config.locale = Locale.US;
		} else if (v.getId() == R.id.btn_new) {
			Intent intent = new Intent(this, LanguageActivity.class);
			startActivity(intent);
			return;
		}
		res.updateConfiguration(config, res.getDisplayMetrics());

		Intent intent = new Intent(this, LanguageActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
}

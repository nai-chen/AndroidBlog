package com.blog.demo.animation;

import com.blog.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AnimationControllerActivity extends Activity {
	private String[] month = new String[]{
			"January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December"};
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_controller_animation);
		
		ListView lv = (ListView) findViewById(R.id.listview);
		lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, month));
	}
	
}

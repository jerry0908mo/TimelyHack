package com.duotin.timelyhack;

import android.app.Activity;
import android.os.Bundle;

import com.duotin.timelyhack.widget.CustomRelativeLayout;
import com.duotin.timelyhack.widget.CustomTextView;

public class MainActivity extends Activity {

	private CustomTextView mCurrentTimeTextView;
	private CustomRelativeLayout mTimerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mCurrentTimeTextView = (CustomTextView) findViewById(R.id.current_time_tv);
		mTimerLayout = (CustomRelativeLayout) findViewById(R.id.timer_container_layout);
		
		mTimerLayout.registerTimeChangeListener(mCurrentTimeTextView);
	}
	

}

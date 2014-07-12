/*
 * CustomRelativeLayoutSampleActivity.java
 * @include classes:CustomRelativeLayoutSampleActivity;interfaces:CustomRelativeLayoutSampleActivity
 * @version 1.0.0
 * @data 2014年7月12日
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD 
 */
package com.duotin.timelyhack.sample;

import com.duotin.timelyhack.R;
import com.duotin.timelyhack.widget.CustomRelativeLayout;
import com.duotin.timelyhack.widget.CustomTextView;

import android.app.Activity;
import android.os.Bundle;

public class ShowTimerActivity extends Activity {
	
	private CustomTextView mCurrentTimeTextView;
	private CustomRelativeLayout mTimerLayout;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_sample);
        
        mCurrentTimeTextView = (CustomTextView) findViewById(R.id.current_time_tv);
		mTimerLayout = (CustomRelativeLayout) findViewById(R.id.timer_container_layout);
		
		mTimerLayout.registerTimeChangeListener(mCurrentTimeTextView);
	}
}

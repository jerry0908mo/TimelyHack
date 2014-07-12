/*
 * CustomRelativeLayoutSampleActivity.java
 * @include classes:CustomRelativeLayoutSampleActivity;interfaces:CustomRelativeLayoutSampleActivity
 * @version 1.0.0
 * @data 2014年7月12日
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD 
 */
package com.duotin.timelyhack.sample;

import android.app.Activity;
import android.os.Bundle;

import com.duotin.timelyhack.R;
import com.duotin.timelyhack.widget.CustomRelativeLayout;
import com.duotin.timelyhack.widget.CustomTextView;
import com.duotin.timelyhack.widget.TimerTextView;
import com.duotin.timelyhack.widget.YearCirlceWidget;

public class ShowTimerActivity extends Activity {
	
	private CustomTextView mCurrentTimeTextView;
	private CustomRelativeLayout mTimerLayout;
	
	private YearCirlceWidget mTotalCircleWidget;
	private TimerTextView mTimerTextView;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_sample);
        
        mTimerLayout = (CustomRelativeLayout) findViewById(R.id.timer_container_layout);
        mCurrentTimeTextView = (CustomTextView) findViewById(R.id.current_time_tv);
		mTotalCircleWidget = (YearCirlceWidget) findViewById(R.id.yearCirlceWidget1);
		mTimerTextView = (TimerTextView) findViewById(R.id.timer_show_view);
		
		mTimerLayout.registerTimeChangeListener(mCurrentTimeTextView);
		mTimerLayout.registerTimeChangeListener(mTotalCircleWidget);
		mTimerLayout.registerTimeChangeListener(mTimerTextView);
	}
}

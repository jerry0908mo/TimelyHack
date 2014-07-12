package com.duotin.timelyhack;

import android.app.Activity;
import android.os.Bundle;

import com.duotin.timelyhack.widget.CustomRelativeLayout;
import com.duotin.timelyhack.widget.CustomTextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.duotin.timelyhack.sample.CustomRelativeLayoutSampleActivity;
import com.duotin.timelyhack.sample.DialRecordsViewSampleActivity;
import com.duotin.timelyhack.sample.TimeyTextSampleActivity;
import com.duotin.timelyhack.sample.YearCirlceWidgetActivity;

public class MainActivity extends Activity {

	private CustomTextView mCurrentTimeTextView;
	private CustomRelativeLayout mTimerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.show_touch_sample);
		findViewById(R.id.show_textview);
		findViewById(R.id.show_dial_records_sample);
		findViewById(R.id.show_yeay_cirlce_sample);
	}
	
	public void showTextViewSample(View view){
		Intent intent  = new Intent(this,TimeyTextSampleActivity.class);
		startActivity(intent);
	}
	
	public void showDialRecordsViewSample(View view){
		Intent intent  = new Intent(this,DialRecordsViewSampleActivity.class);
		startActivity(intent);
	}
	
	public void showYearCirlceWidgetSample(View view){
		Intent intent  = new Intent(this,YearCirlceWidgetActivity.class);
		startActivity(intent);
	}
	
	public void showCustomRelativeLayoutSample(View view){
		Intent intent  = new Intent(this,CustomRelativeLayoutSampleActivity.class);
		startActivity(intent);
	}
	

}

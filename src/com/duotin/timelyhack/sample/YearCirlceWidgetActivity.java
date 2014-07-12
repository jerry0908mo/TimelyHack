/*
 * YearCirlceWidgetActivity.java
 * @include classes:YearCirlceWidgetActivity;interfaces:YearCirlceWidgetActivity
 * @version 1.0.0
 * @data 2014骞�7鏈�12鏃�
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD 
 */
package com.duotin.timelyhack.sample;

import com.duotin.timelyhack.R;
import com.duotin.timelyhack.widget.YearCirlceWidget;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

public class YearCirlceWidgetActivity extends Activity {

	public int circleCount;

	public int curCircleProgress;

	private YearCirlceWidget timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_year_cirlce_sample);
		timer = (YearCirlceWidget) findViewById(R.id.yearCirlceWidget1);
		new timerseter().execute();
	}

	public class timerseter extends AsyncTask<Void, Integer, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			for (int i = 1; i < 100000; i++) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				publishProgress(i);
			}

			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			timer.setTime(values[0].intValue());
			super.onProgressUpdate(values);
		}

	}



}

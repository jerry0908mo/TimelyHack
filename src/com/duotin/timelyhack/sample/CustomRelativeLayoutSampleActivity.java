/*
 * CustomRelativeLayoutSampleActivity.java
 * @include classes:CustomRelativeLayoutSampleActivity;interfaces:CustomRelativeLayoutSampleActivity
 * @version 1.0.0
 * @data 2014年7月12日
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD 
 */
package com.duotin.timelyhack.sample;

import com.duotin.timelyhack.R;

import android.app.Activity;
import android.os.Bundle;

public class CustomRelativeLayoutSampleActivity extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_sample);
	}
}

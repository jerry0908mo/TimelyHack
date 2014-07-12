/*
 * CustomTextView.java
 * @include classes:CustomTextView;interfaces:CustomTextView
 * @version 1.0.0
 * @data 2014年7月12日
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD 
 */
package com.duotin.timelyhack.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @name      CustomTextView
 * @package   com.duotin.timelyhack.widget
 * @author    lofei@duotin.com
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD
 * @version   1.0.0
 * @history
 * lofei 2014年7月12日 下午2:47:55 
 */
public class CustomTextView extends TextView implements OnTimeChangeListener{

	/**
	 * @param context
	 */
	public CustomTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onTimeChanged(long last, long now) {
		// TODO Auto-generated method stub
		setText(String.format("%d, %d", last, now));
	}

}

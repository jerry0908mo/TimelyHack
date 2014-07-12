/*
 * CustomRelativeLayout.java
 * @include classes:CustomRelativeLayout;interfaces:CustomRelativeLayout
 * @version 1.0.0
 * @data 2014年7月12日
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD 
 */
package com.duotin.timelyhack.widget;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * @name      CustomRelativeLayout
 * @package   com.duotin.timelyhack.widget
 * @author    lofei@duotin.com
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD
 * @version   1.0.0
 * @history
 * lofei 2014年7月12日 下午2:09:49 
 */
public class CustomRelativeLayout extends RelativeLayout {
	
	/**
	 * 当前时间值
	 */
	private long mCurrentTime;
	
	private long mLastTime;
	
	private ArrayList<OnTimeChangeListener> mOnTimeChangeListeners = new ArrayList<OnTimeChangeListener>();

	/**
	 * @param context
	 */
	public CustomRelativeLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public CustomRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public CustomRelativeLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 注册时间改变监听器
	 * @param listener
	 */
	public void registerTimeChangeListener(OnTimeChangeListener listener){
		if(listener == null){
			throw new NullPointerException("Error: the listener is Null.");
		}
		mOnTimeChangeListeners.add(listener);
	}
	
	/**
	 * 取消注册时间改变监听器
	 * @param listener
	 */
	public void unregisterTimeChangeListener(OnTimeChangeListener listener){
		if(listener == null){
			throw new NullPointerException("Error: the listener is Null.");
		}
		mOnTimeChangeListeners.remove(listener);
	}

	/**
	 * 设置当前的时间值
	 * @param time
	 */
	public void setCurrentTime(long time){
		mLastTime = mCurrentTime;
		mCurrentTime = time;
		for(OnTimeChangeListener listener : mOnTimeChangeListeners){
			listener.onTimeChanged(mLastTime, time);
		}
	}
	
	/**
	 * 获取当前的时间值
	 * @return
	 */
	public long getCurrentTime(){
		return mCurrentTime;
	}
}

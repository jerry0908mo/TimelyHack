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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * @name CustomRelativeLayout
 * @package com.duotin.timelyhack.widget
 * @author lofei@duotin.com
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD
 * @version 1.0.0
 * @history lofei 2014年7月12日 下午2:09:49
 */
public class CustomRelativeLayout extends RelativeLayout {
	
	/**
	 * 中心点坐标
	 */
	private int mTranslateX;
	private int mTranslateY;
	
	/**
	 * 当前时间值
	 */
	private int mCurrentTime;

	/**
	 * 上一次的时间值
	 */
	private int mLastTime;

	private ArrayList<OnTimeChangeListener> mOnTimeChangeListeners = new ArrayList<OnTimeChangeListener>();

	private boolean isPointerDown = false;
	
	private int mMaxValue = 60;
	
	/**
	 * 当前进度值
	 */
	private int mCurrentProgress;
	
	/**
	 * 上一次进度值
	 */
	private int mLastProgress;

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
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		final int height = getDefaultSize(getSuggestedMinimumHeight(),
				heightMeasureSpec);
		final int width = getDefaultSize(getSuggestedMinimumWidth(),
				widthMeasureSpec);

		mTranslateX = (int) (width * 0.5f);
		mTranslateY = (int) (height * 0.5f);
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 * 注册时间改变监听器
	 * 
	 * @param listener
	 * @return <code>true</code>表示注册成功，<code>false</code>表示注册失败
	 */
	public boolean registerTimeChangeListener(OnTimeChangeListener listener) {
		if (listener == null) {
			return false;
		}
		return mOnTimeChangeListeners.add(listener);
	}

	/**
	 * 取消注册时间改变监听器
	 * 
	 * @param listener
	 * @return <code>true</code>表示取消成功，<code>false</code>表示取消失败
	 */
	public boolean unregisterTimeChangeListener(OnTimeChangeListener listener) {
		if (listener == null) {
			return false;
		}
		return mOnTimeChangeListeners.remove(listener);
	}
	
	/**
	 * 设置当前的进度值
	 * @param progress
	 */
	public void setCurrentProgress(int progress){
		mLastTime = mCurrentTime;
		mLastProgress = mCurrentProgress;
		mCurrentProgress = progress;
		if((mCurrentProgress - mLastProgress) > (mMaxValue - 10)){
			Log.d(VIEW_LOG_TAG, "--hour");
			mCurrentTime -= mMaxValue;
			mCurrentTime = mCurrentTime > 0 ? mCurrentTime : 0;
		} else if ((mCurrentProgress - mLastProgress) < (10 - mMaxValue) && mLastTime != 0){
			Log.d(VIEW_LOG_TAG, "++hour");
			mCurrentTime += mMaxValue;
		} else {
			if(mLastTime == 0 && mCurrentProgress > (mMaxValue / 2)){
				Log.d(VIEW_LOG_TAG, "stop");
			} else {
				boolean increase = mCurrentProgress > mLastProgress;
				int hours = (int) (mCurrentTime / 60);
				mCurrentTime = hours * mMaxValue + progress;
				Log.d(VIEW_LOG_TAG, "other");
			}
			
		}
		for (OnTimeChangeListener listener : mOnTimeChangeListeners) {
			listener.onTimeChanged(mLastTime, mCurrentTime);
		}
	}

	/**
	 * 设置当前的时间值
	 * 
	 * @param time
	 */
	public void setCurrentTime(long time) {
		mLastTime = mCurrentTime;
		mCurrentTime = (int) time;
		for (OnTimeChangeListener listener : mOnTimeChangeListeners) {
			listener.onTimeChanged(mLastTime, mCurrentTime);
		}
	}
	
	/**
	 * 获取当前的时间值
	 * 
	 * @return
	 */
	public long getCurrentTime() {
		return mCurrentTime;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.d(VIEW_LOG_TAG, "tttt");
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			return isPointerDown;
		}
		default:
			break;
		}
		return super.onInterceptHoverEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			updateOnTouch(ev);
			break;
		case MotionEvent.ACTION_MOVE:
			updateOnTouch(ev);
			break;
		case MotionEvent.ACTION_UP:
			setPressed(false);
			isPointerDown = false;
			break;
		case MotionEvent.ACTION_CANCEL:
			setPressed(false);

			break;
		}

		return true;
	}
	
	private void updateOnTouch(MotionEvent event) {
		Log.d(VIEW_LOG_TAG, event.getX() + "," + event.getY());
		boolean ignoreTouch = ignoreTouch(event.getX(), event.getY());
		if (ignoreTouch) {
			return;
		}
		setPressed(true);
		double touchAngle = getTouchDegrees(event.getX(), event.getY());
		int progress = getProgressForAngle(touchAngle);
		Log.d(VIEW_LOG_TAG, "touchAngle:"+touchAngle);
		Log.d(VIEW_LOG_TAG, "progress:"+progress);
		setCurrentProgress(progress);
	}

	private boolean ignoreTouch(float xPos, float yPos) {
		// TODO not used yet
		boolean ignore = false;
		float x = xPos - mTranslateX;
		float y = yPos - mTranslateY;

		float touchRadius = (float) Math.sqrt(((x * x) + (y * y)));
		
		return ignore;
	}

	private double getTouchDegrees(float xPos, float yPos) {
		float x = xPos - mTranslateX;
		float y = yPos - mTranslateY;
		// convert to arc Angle
		double angle = Math.toDegrees(Math.atan2(y, x) + (Math.PI / 2));
		if (angle < 0) {
			angle = 360 + angle;
		}
		return angle;
	}

	private int getProgressForAngle(double angle) {
		
		return (int) (mMaxValue * angle / 360);
	}

	public void setPointerDown(boolean isPointerDown) {
		this.isPointerDown = isPointerDown;
	}
}

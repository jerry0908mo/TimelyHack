/*
 * BaseView.java
 * @include classes:BaseView;interfaces:BaseView
 * @version 1.0.0
 * @data 2014年7月12日
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD 
 */
package com.duotin.timelyhack.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * @name      BaseView
 * @package   info.lofei.timercircle.widget
 * @author    lofei@duotin.com
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD
 * @version   1.0.0
 * @history
 * lofei 2014年7月12日 下午1:47:24 
 */
public abstract class BaseView extends View {
	
	protected long mCurrentTime;

	/**
	 * @param context
	 */
	public BaseView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public BaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取当前显示的时间
	 * @return 当前的时间值
	 */
	public long getCurrentTime(){
		return mCurrentTime;
	}
	
	/**
	 * 设置当前时间值，在设置当前时间值之后会调用 {@link #invalidate()} 方法
	 * @param time
	 */
	public void setCurrentTime(long time){
		mCurrentTime = time;
		invalidate();
	}
}

/*
 * TimelyTextShow.java
 * @include classes:TimelyTextShow;interfaces:TimelyTextShow
 * @version 1.0.0
 * @data 2014年7月12日
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD 
 */
package com.duotin.timelyhack.widget;

import com.duotin.timelyhack.R;
import com.github.adnansm.timelytextview.TimelyView;
import com.nineoldandroids.animation.ObjectAnimator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * @name      TimelyTextShow
 * @desc      
 * @package   com.duotin.timelyhack.widget
 * @author    jerry@duotin.com
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD
 * @version   1.0.0
 * @history
 * jerrysher 2014年7月12日 下午4:49:18 
 */
public class TimerTextView extends LinearLayout implements OnTimeChangeListener{
	
	public static final int            DURATION       = 500;
	public static final int            NO_VALUE       = -1;

	private LayoutInflater mLayoutInflater;
	private Context mContext;
	
	private TimelyView minuteFirstText;
	private TimelyView minuteSecondText;
	
	private TimelyView SecondsFirstText;
	private TimelyView SecondsSecondText;
	
	private volatile    ObjectAnimator objectAnimator = null;

	/**
	 * @desc
	 * @param context
	 */
	public TimerTextView(Context context) {
		super(context);
		init(context);
	}
	
	
	
	/**
	 * @desc
	 * @param context
	 * @param attrs
	 */
	public TimerTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	private void init(Context context){
		mContext = context;
		mLayoutInflater = LayoutInflater.from(context);

		mLayoutInflater.inflate(R.layout.timer_text_view, this, true);
		findViews();
	}

	private void findViews(){
		minuteFirstText = (TimelyView) findViewById(R.id.minute_first);
		minuteSecondText = (TimelyView) findViewById(R.id.minute_second);
		
		SecondsFirstText = (TimelyView) findViewById(R.id.seconds_first);
		SecondsSecondText = (TimelyView) findViewById(R.id.seconds_second);
	}
	
	public void init(){
		setTimeText(0,0,0,0);
	}
	
	public void setTimeText(int m1, int m2, int  s1,int s2){
		setMinFirst(objAnimM1, minuteFirstText, oldM1, m1);
		setMinFirst(objAnimM2, minuteSecondText, oldM2, m2);
		setMinFirst(objAnimS1, SecondsFirstText, oldS1, s1);
		setMinFirst(objAnimS2, SecondsSecondText, oldS2, s2);
	}
	
	private int oldM1 = NO_VALUE;
	private int oldM2 = NO_VALUE;
	private int oldS1 = NO_VALUE;
	private int oldS2 = NO_VALUE;
	
	private ObjectAnimator objAnimM1;
	private ObjectAnimator objAnimM2;
	private ObjectAnimator objAnimS1;
	private ObjectAnimator objAnimS2;
	
	private void setMinFirst(ObjectAnimator objAnim , TimelyView view,int from, int to){
		objAnim = view.animate(from, to);
		objAnim.setDuration(DURATION);
		 if(objAnim != null){
			 if( objAnim.isRunning()){
				 objAnim.end();	 
			 }
			 objAnim.start(); 
		 } 
	}
	
	public void test(int from, int to){
		setMinFirst(objAnimS2, SecondsSecondText, from, to);
	}



	/* (non-Javadoc)
	 * @see com.duotin.timelyhack.widget.OnTimeChangeListener#onTimeChanged(long, long)
	 */
	@Override
	public void onTimeChanged(long last, long now) {
		int h =   (int)now / 60;
		int min = (int)now % 60;
		int m1 = (int) (min /10) ;
		int m2 = (int) (min % 10) ;
		setTimeText(m1,m2,0,0);
	}
	

}

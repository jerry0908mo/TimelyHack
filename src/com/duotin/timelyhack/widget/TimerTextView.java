/*
 * TimelyTextShow.java
 * @include classes:TimelyTextShow;interfaces:TimelyTextShow
 * @version 1.0.0
 * @data 2014骞�7���12���
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD 
 */
package com.duotin.timelyhack.widget;

import com.duotin.timelyhack.R;
import com.github.adnansm.timelytextview.TimelyView;
import com.nineoldandroids.animation.ObjectAnimator;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class TimerTextView extends LinearLayout implements OnTimeChangeListener{
	
	public static final int            DURATION       = 500;
	public static final int            NO_VALUE       = -1;

	private LayoutInflater mLayoutInflater;
	private Context mContext;
	
	private TimelyView minuteFirstText;
	private TimelyView minuteSecondText;
	
	private TimelyView SecondsFirstText;
	private TimelyView SecondsSecondText;
	

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
	
	public void setTimeText(boolean hasAnim,int m1, int m2, int  s1,int s2){
		setObjAnim(hasAnim,objAnimM1, minuteFirstText, oldM1, m1);
		setObjAnim(hasAnim,objAnimM2, minuteSecondText, oldM2, m2);
		setObjAnim(hasAnim,objAnimS1, SecondsFirstText, oldS1, s1);
		setObjAnim(hasAnim,objAnimS2, SecondsSecondText, oldS2, s2);
	}
	
	public void setTimeText(int m1, int m2, int  s1,int s2){
		setObjAnim(false,objAnimM1, minuteFirstText, oldM1, m1);
		setObjAnim(false,objAnimM2, minuteSecondText, oldM2, m2);
		setObjAnim(false,objAnimS1, SecondsFirstText, oldS1, s1);
		setObjAnim(false,objAnimS2, SecondsSecondText, oldS2, s2);
	}
	
	private int oldM1 = NO_VALUE;
	private int oldM2 = NO_VALUE;
	private int oldS1 = NO_VALUE;
	private int oldS2 = NO_VALUE;
	
	private ObjectAnimator objAnimM1;
	private ObjectAnimator objAnimM2;
	private ObjectAnimator objAnimS1;
	private ObjectAnimator objAnimS2;
	
	private void setObjAnim(boolean hasAnim,ObjectAnimator objAnim , TimelyView view,int from, int to){
		if(from == -1){
			from = 0;
		}
		if(to == -1){
			to = 0;
		}
		objAnim = view.animate(from, to);
		objAnim.setDuration(DURATION);
		if(hasAnim){
			if(objAnim != null){
				 if( objAnim.isRunning()){
					 objAnim.end();	 
				 }
				 objAnim.start(); 
			 } 
		}else{
			if(objAnim != null){
				 if( objAnim.isRunning()){
					 objAnim.end();	 
				 }
				 objAnim.setCurrentPlayTime(DURATION);
			 } 
		}
		 
	}
	
	public void test(int from, int to){
		setObjAnim(false,objAnimS2, SecondsSecondText, from, to);
	}



	/* (non-Javadoc)
	 * @see com.duotin.timelyhack.widget.OnTimeChangeListener#onTimeChanged(long, long)
	 */
	@Override
	public void onTimeChanged(int last, int now) {
		int h =   now / 60;
		int min = (int)now % 60;
		int m1 = (int) (min /10) ;
		int m2 = (int) (min % 10) ;
		Log.d(VIEW_LOG_TAG, String.format("%d,%d", m1, m2));
		setTimeText(m1,m2,0,0);
	}
	

}

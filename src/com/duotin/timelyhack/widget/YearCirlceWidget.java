package com.duotin.timelyhack.widget;


import com.duotin.timelyhack.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;



public class YearCirlceWidget extends View {

	private Paint paint;

	private int roundColor;
	
	
	private int roundProgressColor;
	
	private float roundWidth;
	private int centre ;
	private int radius ;
	private int time = 0;
	private int circleCount ;
	private int curCircleProgress ;
	
	public YearCirlceWidget(Context context) {
		this(context, null);
	}

	public YearCirlceWidget(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public YearCirlceWidget(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		paint = new Paint();
		TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
				R.styleable.RoundProgressBar);
		roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.RED);
		roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
		roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 5);
		mTypedArray.recycle();
	}
	

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		centre = getWidth()/2; 
		radius = (int) (centre - roundWidth/2); 
		
		circleCount = time/60;
		curCircleProgress = time % 60;
		for(int i=1 ; i<=circleCount;i++){
			drawCircles(canvas,i);
		}
		drawCircleProgress(canvas,curCircleProgress);
			
	}
	
	/**
	 * 
	 * @param canvas
	 * @param progress
	 */
	private void drawCircleProgress(Canvas canvas ,int progress)
	{
		paint.setStrokeWidth(roundWidth); 
		paint.setColor(roundProgressColor); 
		RectF oval = new RectF(centre - radius, centre - radius, centre
				+ radius, centre + radius);  
		paint.setStyle(Paint.Style.STROKE);
	    canvas.drawArc(oval, -90, 360 * progress / 60, false, paint);  
	}
	
	/**
	 * 
	 * @param canvas
	 * @param count 
	 */
	private void drawCircles(Canvas canvas ,int count)
	{
		int  subRadius = (int) (centre - roundWidth/2-count*20); 
		paint.setColor(roundColor); 
		paint.setStrokeWidth(roundWidth); 
		paint.setStyle(Paint.Style.STROKE);
		paint.setAntiAlias(true);  
		canvas.drawCircle(centre, centre, subRadius, paint);
	}
	


	/**
	 * 
	 * @param time
	 */
	public void setTime(int time) {
		if( time< 0){
			throw new IllegalArgumentException("time not less than 0");
		}
		this.time = time;
		postInvalidate();
	}

	public int getCricleColor() {
		return roundColor;
	}

	public void setCricleColor(int cricleColor) {
		this.roundColor = cricleColor;
	}

	public int getCricleProgressColor() {
		return roundProgressColor;
	}

	public void setCricleProgressColor(int cricleProgressColor) {
		this.roundProgressColor = cricleProgressColor;
	}


	public float getRoundWidth() {
		return roundWidth;
	}

	public void setRoundWidth(float roundWidth) {
		this.roundWidth = roundWidth;
	}



}

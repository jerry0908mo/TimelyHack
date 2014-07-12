package com.duotin.timelyhack.sample;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.duotin.timelyhack.R;
import com.duotin.timelyhack.widget.TimerTextView;
import com.github.adnansm.timelytextview.TimelyView;
import com.nineoldandroids.animation.ObjectAnimator;

public class TimeyTextSampleActivity extends Activity {
	
    public static final int            DURATION       = 1000;
    public static final int            NO_VALUE       = -1;
    private 			TimerTextView mTimerTextView = null;
    private             TimelyView     timelyView     = null;
    private             SeekBar        seekBar        = null;
    private             Spinner        fromSpinner    = null;
    private             Spinner        toSpinner      = null;
    private volatile    ObjectAnimator objectAnimator = null;

    private volatile int from = NO_VALUE;
    private volatile int to   = NO_VALUE;
    
	private Timer  mTimer;
	private TimerTask   mTask;
	
	private int timeVaule = 1000;
	
	private void initTimer(){
		mHandler = new Handler();
		if(null == mTimer){
			mTimer = new Timer(true);
		}
		
		if(null == mTask){
			mTask =  new  TimerTask() {
				@Override
				public void run() {
					mHandler.post(new Runnable() {
						
						@Override
						public void run() {
							int min= timeVaule/60;
							int sencond  = timeVaule % 60;
							
							int m1 = min/10;
							int m2 = min%10;
							int s1 = sencond/10;
							int s2 = sencond%10;
							
							mTimerTextView.setTimeText(m1, m2, m1, m2);
						}
					});
				}
			};
		}
		
	}
	
	private Handler mHandler;

	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		
		if(mTimer!=null) mTimer.cancel();
//			else {
//				autoExitReceive.setAutoExitApp(getApplicationContext(),
//						senconds);
//				if(mTimer!=null) mTimer.cancel();
//				mTask.cancel();
//				
//				mTimer = null;
//				mTask  =  null;
//				initTimer();
//				//Æô¶¯¼ÆÊ±Æ÷
//				mTimer.schedule(mTask, 0, 1000);
//			}
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timelytime_sample);
        
        timelyView = (TimelyView) findViewById(R.id.textView1);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        fromSpinner = (Spinner) findViewById(R.id.fromSpinner);
        toSpinner = (Spinner) findViewById(R.id.toSpinner);
        mTimerTextView = (TimerTextView) findViewById(R.id.timer_show_view);
        mTimerTextView.setTimeText(8, 2, 3, 1);
        mTimerTextView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//mTimerTextView.test(0, 9);
				mTimerTextView.setTimeText(0, 2, 3, 1);
			}
		});
        
        initTimer();
		mTimer.schedule(mTask, 0, 1000);
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.from_numbers_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                from = position - 1;
                if(to != NO_VALUE && to != NO_VALUE) {
                    objectAnimator = timelyView.animate(from, to);
                    objectAnimator.setDuration(DURATION);
                } else {
                    objectAnimator = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                to = position - 1;
                if(from != NO_VALUE && to != NO_VALUE) {
                    objectAnimator = timelyView.animate(from, to);
                    objectAnimator.setDuration(DURATION);
                } else {
                    objectAnimator = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        seekBar.setMax(DURATION);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(objectAnimator != null) objectAnimator.setCurrentPlayTime(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}

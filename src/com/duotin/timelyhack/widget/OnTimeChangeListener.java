/*
 * OnTimeChangeListener.java
 * @include classes:OnTimeChangeListener;interfaces:OnTimeChangeListener
 * @version 1.0.0
 * @data 2014年7月12日
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD 
 */
package com.duotin.timelyhack.widget;

/**
 * @name      OnTimeChangeListener
 * @package   com.duotin.timelyhack.widget
 * @author    lofei@duotin.com
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD
 * @version   1.0.0
 * @history
 * lofei 2014年7月12日 下午2:19:18 
 */
public interface OnTimeChangeListener {

	/**
	 * 时间改变函数
	 * @param last 上一次的时间值, 0-5940
	 * @param now 最新的时间值, 0-5940
	 */
	void onTimeChanged(int last, int now);
}

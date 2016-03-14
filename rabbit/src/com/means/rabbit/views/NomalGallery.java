package com.means.rabbit.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

public class NomalGallery extends Gallery {

	private float gTouchStartX;
	private float gTouchStartY;

	public NomalGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
		return e2.getX() > e1.getX();
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		int keyCode;
		if (isScrollingLeft(e1, e2)) {
			keyCode = KeyEvent.KEYCODE_DPAD_LEFT; // 向左移
		} else {
			keyCode = KeyEvent.KEYCODE_DPAD_RIGHT; // 右移
		}
		onKeyDown(keyCode, null);
		return false;
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			gTouchStartX = ev.getX();
			gTouchStartY = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			final float touchDistancesX = Math.abs(ev.getX() - gTouchStartX);
			final float touchDistancesY = Math.abs(ev.getY() - gTouchStartY);
			if (touchDistancesY * 2 >= touchDistancesX) {
				System.out.println("111111");
				return false;
			} else {
				getParent().requestDisallowInterceptTouchEvent(true);
				System.out.println("222222");
				return true;
			}
		case MotionEvent.ACTION_CANCEL:
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		return false;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}

}

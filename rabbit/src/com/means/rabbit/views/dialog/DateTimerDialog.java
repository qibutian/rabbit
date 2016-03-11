package com.means.rabbit.views.dialog;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.means.rabbit.R;
import com.means.rabbit.utils.DateUtils;
import com.means.rabbit.wheel.OnWheelChangedListener;
import com.means.rabbit.wheel.WheelView;
import com.means.rabbit.wheel.adapter.ArrayWheelAdapter;

public class DateTimerDialog extends BaseAlertDialog implements
		android.view.View.OnClickListener, OnWheelChangedListener {
	private WheelView mViewYear;

	private WheelView mViewMonth;

	private WheelView mViewDay;

	private Button mBtnConfirm;

	Context mContext;

	String mCurrentYearName, mCurrentMonthName, mCurrentDayName;

	TextView yearT, monthT, dayT;

	OnDateTimerResultListener dateTimerResultListener;

	Calendar mCalendar;

	public DateTimerDialog(Context context) {
		super(context);
		this.mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_datetimer);
		initView();
	}

	private void initView() {
		mCalendar = Calendar.getInstance();
		// Button cancleB = (Button) findViewById(R.id.cancle);
		// cancleB.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// dismiss();
		// }
		// });
		mViewYear = (WheelView) findViewById(R.id.year);
		mViewMonth = (WheelView) findViewById(R.id.month);
		mViewDay = (WheelView) findViewById(R.id.day);
		mBtnConfirm = (Button) findViewById(R.id.btn_confirm);

		yearT = (TextView) findViewById(R.id.txt_year);
		monthT = (TextView) findViewById(R.id.txt_month);
		dayT = (TextView) findViewById(R.id.txt_day);

		// 添加change事件
		mViewYear.addChangingListener(this);
		// 添加change事件
		mViewMonth.addChangingListener(this);
		// 添加change事件
		mViewDay.addChangingListener(this);
		// 添加onclick事件
		mBtnConfirm.setOnClickListener(this);

		mViewYear.setViewAdapter(new ArrayWheelAdapter<String>(mContext,
				getYear()));
		mViewMonth.setViewAdapter(new ArrayWheelAdapter<String>(mContext,
				getMonth()));
		mViewDay.setViewAdapter(new ArrayWheelAdapter<String>(mContext,
				getDay(31)));


		// 设置可见条目数量
		mViewYear.setVisibleItems(3);
		mViewMonth.setVisibleItems(3);
		mViewDay.setVisibleItems(3);

		updateDay();
		mViewYear.setCurrentItem(0);
		mViewMonth.setCurrentItem(mCalendar.get(Calendar.MONTH));
		mViewDay.setCurrentItem(mCalendar.get(Calendar.DAY_OF_MONTH) - 1);
	}

	private String[] getYear() {
		String[] years = new String[10];
		for (int i = 0; i < 10; i++) {

			mCurrentYearName = mCalendar.get(Calendar.YEAR) + i + "";

			years[i] = mCurrentYearName;
		}
		return years;
	}

	private String[] getMonth() {
		String[] months = new String[12];
		for (int i = 1; i <= 12; i++) {
			months[i - 1] = String.valueOf(i);
		}
		return months;
	}

	private String[] getDay(int daycount) {
		String[] days = new String[daycount];
		for (int i = 1; i <= daycount; i++) {
			days[i - 1] = String.valueOf(i);
		}
		return days;
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == mViewYear) {
			mViewMonth.setCurrentItem(0);
			updateDay();
		} else if (wheel == mViewMonth) {
			updateDay();
		} else if (wheel == mViewDay) {
			dayT.setText(getDay(getMaxDate(mCurrentYearName, mCurrentMonthName))[mViewDay
					.getCurrentItem()]);
		}
	}

	/**
	 * 根据当前的月，更新日WheelView的信息
	 */
	private void updateDay() {
		int yCurrent = mViewYear.getCurrentItem();
		mCurrentYearName = getYear()[yCurrent];
		int mCurrent = mViewMonth.getCurrentItem();
		mCurrentMonthName = getMonth()[mCurrent];

		String[] Days;
		if (mCurrentMonthName.equals("2")) {
			int year = Integer.parseInt(mCurrentYearName);
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
				Days = getDay(29);
			} else {
				Days = getDay(28);
			}
		} else {
			Days = getDay(getMaxDate(mCurrentYearName, mCurrentMonthName));
		}
		mViewDay.setViewAdapter(new ArrayWheelAdapter<String>(mContext, Days));
		mViewDay.setCurrentItem(0);
		mCurrentDayName = getDay(getMaxDate(mCurrentYearName, mCurrentMonthName))[mViewDay
				.getCurrentItem()];

		yearT.setText(mCurrentYearName);
		monthT.setText(mCurrentMonthName);
		dayT.setText(mCurrentDayName);
	}

	// 获取当前月几天
	private int getMaxDate(String year, String month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1); // 输入6月
		// 实为7月
		int maxDate = cal.getActualMaximum(Calendar.DATE);
		return maxDate;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_confirm:
			showSelectedResult();
			break;
		default:
			break;
		}
	}

	private void showSelectedResult() {
		if (dateTimerResultListener != null) {
			mCurrentYearName = getYear()[mViewYear.getCurrentItem()];
			mCurrentMonthName = getMonth()[mViewMonth.getCurrentItem()];
			mCurrentDayName = getDay(getMaxDate(mCurrentYearName,
					mCurrentMonthName))[mViewDay.getCurrentItem()];

			// //获取当前年月日 如果大于今天 默认返回今天
			// Long
			// date=DateUtils.getStringToDate(mCurrentYearName+"年"+mCurrentMonthName+"月"+mCurrentDayName+"日");
			// Date curDate = new Date(System.currentTimeMillis());//获取当前时间
			// if (date>System.currentTimeMillis()){
			// Calendar mCalendar=Calendar.getInstance();
			// mCurrentYearName = mCalendar.get(Calendar.YEAR)+"";
			// mCurrentMonthName = mCalendar.get(Calendar.MONTH)+1+"";
			// mCurrentDayName = mCalendar.get(Calendar.DAY_OF_MONTH)+"";
			// }

			dateTimerResultListener.onResult(mCurrentYearName,
					mCurrentMonthName, mCurrentDayName);
		}
		dismiss();
	}

	public interface OnDateTimerResultListener {
		void onResult(String year, String month, String day);
	}

	public OnDateTimerResultListener getOnDateTimerResultListener() {
		return dateTimerResultListener;
	}

	public void setOnDateTimerResultListener(
			OnDateTimerResultListener dateTimerResultListener) {
		this.dateTimerResultListener = dateTimerResultListener;
	}
}

package com.means.rabbit.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.means.rabbit.R;
import com.means.rabbit.activity.home.HomePageFragment;
import com.means.rabbit.activity.home.SelectCityActivity;
import com.means.rabbit.activity.merchants.MerchatsFragment;
import com.means.rabbit.activity.more.MoreFragment;
import com.means.rabbit.activity.my.MyIndexFragment;
import com.means.rabbit.activity.travel.TravelFragment;
import com.means.rabbit.bean.CityEB;
import com.means.rabbit.bean.User;
import com.means.rabbit.manage.UserInfoManage;
import com.means.rabbit.manage.UserInfoManage.LoginCallBack;

public class MainActivity extends FragmentActivity {

	private FragmentManager fm;
	private Fragment currentFragment;

	private LinearLayout tabV;

	View city_layoutV;

	View search_layoutV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initTab();
		setTab(0);
	}

	private void initView() {
		// TODO Auto-generated method stub
		fm = getSupportFragmentManager();
		tabV = (LinearLayout) findViewById(R.id.tab);

		city_layoutV = (View) findViewById(R.id.city_layout);
		city_layoutV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(MainActivity.this,
						SelectCityActivity.class);
				startActivity(it);
			}
		});

		search_layoutV = findViewById(R.id.search_layout);
		search_layoutV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent it = new Intent(MainActivity.this, SearchActivity.class);
				startActivity(it);
			}
		});
	}

	private void initTab() {
		for (int i = 0; i < tabV.getChildCount(); i++) {
			final int index = i;
			LinearLayout childV = (LinearLayout) tabV.getChildAt(i);
			childV.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					setTab(index);
				}
			});
		}
	}

	public void setTab(final int index) {

		User user = User.getInstance();
		if (index == 3) {
			if (!user.isLogin()) {
				UserInfoManage.getInstance().checkLogin(MainActivity.this,
						new LoginCallBack() {

							@Override
							public void onisLogin() {
								setTab(index);

							}

							@Override
							public void onLoginFail() {
								// TODO Auto-generated method stub

							}
						});
				return;
			}
		}

		for (int i = 0; i < tabV.getChildCount(); i++) {

			LinearLayout childV = (LinearLayout) tabV.getChildAt(i);
			RelativeLayout imgV = (RelativeLayout) childV.getChildAt(0);
			ImageView imgI = (ImageView) imgV.getChildAt(0);
			TextView textT = (TextView) childV.getChildAt(1);
			if (i == index) {
				switch (i) {
				case 0: // 首页
					switchContent(HomePageFragment.getInstance());
					imgI.setImageResource(R.drawable.icon_home_f);
					textT.setTextColor(getResources().getColor(
							R.color.text_2B_green));
					break;

				case 1: // 旅行
					switchContent(TravelFragment.getInstance());
					imgI.setImageResource(R.drawable.icon_trip_f);
					textT.setTextColor(getResources().getColor(
							R.color.text_2B_green));
					break;

				case 2: // 商家
					// if (per.isLogin()) {
					switchContent(MerchatsFragment.getInstance());
					imgI.setImageResource(R.drawable.icon_store_f);
					textT.setTextColor(getResources().getColor(
							R.color.text_2B_green));
					// }else {
					// Intent it = new Intent(self,LoginActivity.class);
					// startActivity(it);
					// }

					break;

				case 3: // 我的
					switchContent(MyIndexFragment.getInstance());
					imgI.setImageResource(R.drawable.icon_my_f);
					textT.setTextColor(getResources().getColor(
							R.color.text_2B_green));
					break;

				case 4: // 更多
					switchContent(MoreFragment.getInstance());
					imgI.setImageResource(R.drawable.icon_more_f);
					textT.setTextColor(getResources().getColor(
							R.color.text_2B_green));
					break;

				default:
					break;
				}
			} else {
				childV.setBackgroundColor(getResources().getColor(
						R.color.nothing));
				switch (i) {
				case 0:
					imgI.setImageResource(R.drawable.icon_home_n);
					textT.setTextColor(getResources().getColor(
							R.color.text_66_black));
					break;

				case 1:
					imgI.setImageResource(R.drawable.icon_trip_n);
					textT.setTextColor(getResources().getColor(
							R.color.text_66_black));
					break;

				case 2:
					imgI.setImageResource(R.drawable.icon_store_n);
					textT.setTextColor(getResources().getColor(
							R.color.text_66_black));

					break;

				case 3:
					imgI.setImageResource(R.drawable.icon_my_n);
					textT.setTextColor(getResources().getColor(
							R.color.text_66_black));
					break;

				case 4:
					imgI.setImageResource(R.drawable.icon_more_n);
					textT.setTextColor(getResources().getColor(
							R.color.text_66_black));

					break;

				default:

					break;

				}
			}
		}

	}

	public void switchContent(Fragment fragment) {
		try {
			FragmentTransaction t = fm.beginTransaction();
			if (currentFragment != null) {
				t.hide(currentFragment);
			}
			if (!fragment.isAdded()) {
				t.add(R.id.main_content, fragment);

			}
			t.show(fragment);
			t.commitAllowingStateLoss();
			currentFragment = fragment;
		} catch (Exception e) {
		}
	}

}

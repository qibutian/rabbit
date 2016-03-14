package com.means.rabbit.activity.home;

import net.duohuo.dhroid.adapter.NetJSONAdapter;

import com.means.rabbit.R;
import com.means.rabbit.activity.main.HelpActivity;
import com.means.rabbit.activity.merchants.DaiGouActivity;
import com.means.rabbit.activity.merchants.FoodListActivity;
import com.means.rabbit.activity.merchants.HotelListActivity;
import com.means.rabbit.activity.travel.TravelActivity;
import com.means.rabbit.api.API;
import com.means.rabbit.bean.CityEB;
import com.means.rabbit.views.RefreshListViewAndMore;

import de.greenrobot.event.EventBus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class HomePageFragment extends Fragment implements OnClickListener {
	static HomePageFragment instance;

	View mainV;

	LayoutInflater mLayoutInflater;

	View headV;

	RefreshListViewAndMore listV;

	ListView contentListV;

	NetJSONAdapter adapter;

	// 美食点击按钮
	View foodLayoutV;

	// 酒店点击按钮
	View hotelLayoutV;

	// 随身翻译点击按钮
	View redpacketLayoutV;

	// 旅行小秘按钮
	View travel_layoutV;

	// 紧急求助
	View help_layoutV;

	// 全部分类
	View all_catV;

	// 出行服务
	View travel_servicesV;

	// 休闲娱乐
	View entertainmentV;

	// 专享特色
	View exclusive_characteristicsV;

	// 货币兑换
	View huobiV;

	public static HomePageFragment getInstance() {
		if (instance == null) {
			instance = new HomePageFragment();
		}

		return instance;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainV = inflater.inflate(R.layout.fragment_home_page, null);
		mLayoutInflater = inflater;
		EventBus.getDefault().register(this);
		initView();
		// TODO Auto-generated method stub
		return mainV;
	}

	private void initView() {
		listV = (RefreshListViewAndMore) mainV.findViewById(R.id.my_listview);
		headV = mLayoutInflater.inflate(R.layout.head_home_index, null);
		listV.addHeadView(headV);
		contentListV = listV.getListView();
		adapter = new NetJSONAdapter(API.text, getActivity(),
				R.layout.item_home_list);
		adapter.fromWhat("list");
		listV.setAdapter(adapter);
		contentListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

			}
		});

		foodLayoutV = headV.findViewById(R.id.food_layout);
		hotelLayoutV = headV.findViewById(R.id.hotel_layout);
		redpacketLayoutV = headV.findViewById(R.id.redpacket);
		travel_layoutV = headV.findViewById(R.id.travel_layout);
		help_layoutV = headV.findViewById(R.id.help_layout);
		all_catV = headV.findViewById(R.id.all_cat);
		travel_servicesV = headV.findViewById(R.id.travel_services);
		entertainmentV = headV.findViewById(R.id.entertainment);
		exclusive_characteristicsV = headV
				.findViewById(R.id.exclusive_characteristics);
		huobiV = headV.findViewById(R.id.huobi);
		foodLayoutV.setOnClickListener(this);
		hotelLayoutV.setOnClickListener(this);
		redpacketLayoutV.setOnClickListener(this);
		travel_layoutV.setOnClickListener(this);
		help_layoutV.setOnClickListener(this);
		all_catV.setOnClickListener(this);
		travel_servicesV.setOnClickListener(this);
		entertainmentV.setOnClickListener(this);
		exclusive_characteristicsV.setOnClickListener(this);
		huobiV.setOnClickListener(this);
	}

	public void onEventMainThread(CityEB city) {
		TextView cityT = (TextView) mainV.getRootView().findViewById(R.id.city);
		cityT.setText(city.getCityname());
	}

	@Override
	public void onClick(View v) {

		Intent it;

		switch (v.getId()) {
		// 美食
		case R.id.food_layout:
			it = new Intent(getActivity(), FoodListActivity.class);
			it.putExtra("title",getString(R.string.meishi));
			startActivity(it);
			break;

		// 出行服务
		case R.id.travel_services:
			it = new Intent(getActivity(), FoodListActivity.class);
			it.putExtra("title", getString(R.string.chuxingfuwu));
			startActivity(it);
			break;

		// 休闲娱乐
		case R.id.entertainment:
			it = new Intent(getActivity(), FoodListActivity.class);
			it.putExtra("title", getString(R.string.xiuxianyule));
			startActivity(it);
			break;

		// 专享特色
		case R.id.exclusive_characteristics:
			it = new Intent(getActivity(), FoodListActivity.class);
			it.putExtra("title", getString(R.string.zhuanxiangtese));
			startActivity(it);
			break;
		// 专享特色
		case R.id.huobi:
			it = new Intent(getActivity(), FoodListActivity.class);
			it.putExtra("title",getString(R.string.huobiduihuan));
			startActivity(it);
			break;

		case R.id.hotel_layout:
			it = new Intent(getActivity(), HotelListActivity.class);
			startActivity(it);
			break;

		// 随身翻译
		case R.id.redpacket:
			it = new Intent(getActivity(), TranslateActivity.class);
			startActivity(it);
			break;

		// 旅行小秘
		case R.id.travel_layout:
			it = new Intent(getActivity(), TravelActivity.class);
			it.putExtra("title", getString(R.string.lvxingxiaomi));
			startActivity(it);
			break;

		case R.id.help_layout:
			it = new Intent(getActivity(), TravelActivity.class);
			it.putExtra("title", getString(R.string.jinjiqiuzhu));
			startActivity(it);
			break;

		case R.id.all_cat:
			it = new Intent(getActivity(), AllItemActivity.class);
			startActivity(it);
			break;
		default:
			break;
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}

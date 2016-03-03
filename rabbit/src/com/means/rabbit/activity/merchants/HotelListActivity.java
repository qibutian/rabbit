package com.means.rabbit.activity.merchants;

import org.json.JSONObject;

import net.duohuo.dhroid.adapter.FieldMap;
import net.duohuo.dhroid.adapter.NetJSONAdapter;
import net.duohuo.dhroid.ioc.IocContainer;
import net.duohuo.dhroid.net.JSONUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.means.rabbit.R;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.utils.RabbitPerference;
import com.means.rabbit.views.RefreshListViewAndMore;
import com.means.rabbit.views.TabView;

public class HotelListActivity extends RabbitBaseActivity {

	LayoutInflater mLayoutInflater;

	RefreshListViewAndMore listV;

	ListView contentListV;

	NetJSONAdapter adapter;

	TabView tabV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotel_list);
	}

	@Override
	public void initView() {
		setTitle("酒店");
		listV = (RefreshListViewAndMore) findViewById(R.id.my_listview);
		contentListV = listV.getListView();
		adapter = new NetJSONAdapter(API.hotelList, self,
				R.layout.item_hotel_list);
		RabbitPerference per = IocContainer.getShare().get(
				RabbitPerference.class);
		per.load();
//		adapter.addparam("catid", per.catid);
		adapter.fromWhat("list");
		adapter.addField("title", R.id.title);
		adapter.addField("pic", R.id.pic);
		adapter.addField(new FieldMap("price", R.id.price) {

			@Override
			public Object fix(View itemV, Integer position, Object o, Object jo) {
				TextView comment_desT = (TextView) itemV
						.findViewById(R.id.comment_des);
				JSONObject data = (JSONObject) jo;
				comment_desT.setText("评论" + JSONUtil.getString(data, "score")
						+ "/" + JSONUtil.getString(data, "score"));

				return "￥" + o + "起";
			}
		});
		adapter.addField("tuangoudes", R.id.order_des);
		adapter.addField("address", R.id.address);
		// adapter.addField("title", R.id.title);
		// adapter.addField("title", R.id.title);
		// adapter.addField("title", R.id.title);
		listV.setAdapter(adapter);
		contentListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				JSONObject jo = adapter.getTItem(position);
				Intent it = new Intent(self, HotelDetailActivity.class);
				it.putExtra("hotelId", JSONUtil.getString(jo, "id"));
				startActivity(it);

			}
		});

	}

}

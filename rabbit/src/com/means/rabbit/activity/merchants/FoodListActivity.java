package com.means.rabbit.activity.merchants;

import org.json.JSONObject;

import net.duohuo.dhroid.adapter.FieldMap;
import net.duohuo.dhroid.adapter.NetJSONAdapter;
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
import com.means.rabbit.views.RefreshListViewAndMore;
import com.means.rabbit.views.TabView;
import com.means.rabbit.views.TabView.OnTabSelectListener;

public class FoodListActivity extends RabbitBaseActivity {

	LayoutInflater mLayoutInflater;

	View headV;

	RefreshListViewAndMore listV;

	ListView contentListV;

	NetJSONAdapter adapter;

	TabView tabV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_list);
	}

	@Override
	public void initView() {
		setTitle(getIntent().getStringExtra("title"));
		listV = (RefreshListViewAndMore) findViewById(R.id.my_listview);
		mLayoutInflater = LayoutInflater.from(self);
		headV = mLayoutInflater.inflate(R.layout.head_food_list, null);
		listV.addHeadView(headV);
		contentListV = listV.getListView();
		adapter = new NetJSONAdapter(API.foodList, self,
				R.layout.item_food_list);
		adapter.fromWhat("list");
		adapter.addField("title", R.id.title);
		adapter.addField("price", R.id.price);
		adapter.addField("pic", R.id.pic);
		adapter.addField("tuangoudes", R.id.des);
		adapter.addField("tuidingdes", R.id.order_des);
		adapter.addField("areaname", R.id.area);
		adapter.addField(new FieldMap("price", R.id.price) {

			@Override
			public Object fix(View itemV, Integer position, Object o, Object jo) {
				JSONObject data = (JSONObject) jo;
				TextView comment_desT = (TextView) itemV
						.findViewById(R.id.comment_des);
				comment_desT.setText("评分   "
						+ JSONUtil.getString(data, "score") + "/"
						+ JSONUtil.getString(data, "views"));
				// TODO Auto-generated method stub
				return o;
			}
		});

		listV.setAdapter(adapter);
		contentListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				JSONObject jo = adapter.getTItem(position-contentListV.getHeaderViewsCount());
				Intent it = new Intent(self, ShopDetailActivity.class);
				it.putExtra("shopId", JSONUtil.getString(jo, "id"));
				startActivity(it);

			}
		});

		tabV = (TabView) findViewById(R.id.tab);
		tabV.setCentertText("附近", "");
		tabV.setOnTabSelectListener(new OnTabSelectListener() {

			@Override
			public void onRightSelect(String result) {
				adapter.addparam("order", result);
				adapter.refreshDialog();
			}

			@Override
			public void onCenterSelect(String result) {
				adapter.addparam("catid", result);
				adapter.refreshDialog();
			}
		});
	}

}

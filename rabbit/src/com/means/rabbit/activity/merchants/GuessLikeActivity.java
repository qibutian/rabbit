package com.means.rabbit.activity.merchants;

import org.json.JSONObject;

import net.duohuo.dhroid.adapter.FieldMap;
import net.duohuo.dhroid.adapter.NetJSONAdapter;
import net.duohuo.dhroid.net.JSONUtil;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.activity.main.SearchActivity;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.views.RefreshListViewAndMore;
import com.means.rabbit.views.TabView;
import com.means.rabbit.views.TabView.OnTabSelectListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * 商家/酒店列表猜你喜欢列表
 * @author Administrator
 *
 */
public class GuessLikeActivity extends RabbitBaseActivity {
	
	LayoutInflater mLayoutInflater;

	RefreshListViewAndMore listV;

	ListView contentListV;

	NetJSONAdapter adapter;

	String catid;
	
	String keywords;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guess_like);
	}

	@Override
	public void initView() {
		setTitle(getIntent().getStringExtra("title"));
		setRightAction2(R.drawable.icon_green_search, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(self,SearchActivity.class);
				startActivity(it);
			}
		});
		catid = getIntent().getStringExtra("catid");
		keywords = getIntent().getStringExtra("keywords");
		listV = (RefreshListViewAndMore) findViewById(R.id.my_listview);
		mLayoutInflater = LayoutInflater.from(self);
		contentListV = listV.getListView();
		adapter = new NetJSONAdapter(API.guesslikelist, self,
				R.layout.item_food_list);
		adapter.addparam("catid", catid);
		adapter.addparam("keywords", keywords);
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
				return getString(R.string.money_symbol)+o;
			}
		});

		listV.setAdapter(adapter);
		contentListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				JSONObject jo = adapter.getTItem(position
						- contentListV.getHeaderViewsCount());
				Intent it = new Intent(self, ShopDetailActivity.class);
				it.putExtra("shopId", JSONUtil.getString(jo, "id"));
				startActivity(it);

			}
		});

	}

}

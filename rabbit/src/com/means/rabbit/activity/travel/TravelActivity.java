package com.means.rabbit.activity.travel;

import java.util.Date;

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

import com.means.rabbit.R;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.utils.DateUtils;
import com.means.rabbit.views.RefreshListViewAndMore;

/**
 * 
 * 旅游小密
 * 
 * @author Administrator
 * 
 */
public class TravelActivity extends RabbitBaseActivity {

	LayoutInflater mLayoutInflater;

	RefreshListViewAndMore listV;

	ListView contentListV;

	NetJSONAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_travel);
	}

	@Override
	public void initView() {
		setTitle(getIntent().getStringExtra("title"));
		listV = (RefreshListViewAndMore) findViewById(R.id.my_listview);
		contentListV = listV.getListView();
		adapter = new NetJSONAdapter(API.contentlist, self,
				R.layout.item_travel_list);
		adapter.fromWhat("list");
		adapter.addField("title", R.id.title);
		adapter.addField(new FieldMap("views", R.id.views) {

			@Override
			public Object fix(View itemV, Integer position, Object o, Object jo) {

				return "阅读  " + o;
			}
		});
		adapter.addField("des", R.id.des);
		adapter.addField(new FieldMap("adddateline", R.id.adddateline) {

			@Override
			public Object fix(View itemV, Integer position, Object o, Object jo) {
				return DateUtils.dateToStr(new Date(
						Long.parseLong(o.toString()) * 1000));
			}
		});
		adapter.addField("pic", R.id.pic);
		listV.setAdapter(adapter);
		contentListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Intent it = new Intent(self, TravelDetailActivity.class);
				JSONObject jo = adapter.getTItem(position);
				it.putExtra("id", JSONUtil.getInt(jo, "id"));
				startActivity(it);
			}
		});
	}

}

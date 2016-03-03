package com.means.rabbit.activity.merchants;

import net.duohuo.dhroid.adapter.NetJSONAdapter;
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
import com.means.rabbit.views.RefreshListViewAndMore;

/**
 * 
 * 代购列表
 * @author Administrator
 *
 */
public class DaiGouActivity extends RabbitBaseActivity {

	LayoutInflater mLayoutInflater;

	View headV;

	RefreshListViewAndMore listV;

	ListView contentListV;

	NetJSONAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daigou_list);
	}

	@Override
	public void initView() {
		setTitle("代购");
		listV = (RefreshListViewAndMore) findViewById(R.id.my_listview);
		mLayoutInflater = LayoutInflater.from(self);
		headV = mLayoutInflater.inflate(R.layout.head_food_list, null);
		listV.addHeadView(headV);
		contentListV = listV.getListView();
		adapter = new NetJSONAdapter(API.text, self, R.layout.item_daigou_list);
		adapter.fromWhat("list");
		listV.setAdapter(adapter);
		contentListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Intent it = new Intent(self, GoodDetailActivity.class);
				startActivity(it);
			}
		});
	}

}

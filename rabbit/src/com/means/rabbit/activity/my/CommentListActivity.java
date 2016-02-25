package com.means.rabbit.activity.my;

import net.duohuo.dhroid.adapter.NetJSONAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.means.rabbit.R;
import com.means.rabbit.activity.merchants.ShopDetailActivity;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.views.RefreshListViewAndMore;

public class CommentListActivity extends RabbitBaseActivity {

	RefreshListViewAndMore listV;

	ListView contentListV;

	NetJSONAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment_list);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.comment_list));
		listV = (RefreshListViewAndMore) findViewById(R.id.my_listview);
		contentListV = listV.getListView();
		adapter = new NetJSONAdapter(API.text, self, R.layout.item_comment);
		adapter.fromWhat("list");
		listV.setAdapter(adapter);
		contentListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				Intent it = new Intent(self, ShopDetailActivity.class);
				startActivity(it);

			}
		});

	}

}

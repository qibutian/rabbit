package com.means.rabbit.activity.home;

import org.json.JSONArray;

import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.means.rabbit.R;
import com.means.rabbit.adapter.AllItemAdapter;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;

/**
 * 全部分类
 * 
 * @author Administrator
 * 
 */
public class AllItemActivity extends RabbitBaseActivity {

	ExpandableListView listView;
	
	AllItemAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_item);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.all_item));
		
		listView = (ExpandableListView) findViewById(R.id.expandableListView);
		
		adapter = new AllItemAdapter(self);
		listView.setAdapter(adapter);
		listView.setGroupIndicator(null);
		
//		listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//			
//			@Override
//			public boolean onChildClick(ExpandableListView parent, View v,
//					int groupPosition, int childPosition, long id) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//		});
		
		getData();

	}

	private void getData() {
		DhNet net = new DhNet(API.catlist);
		net.doGet(new NetTask(self) {
			
			@Override
			public void doInUI(Response response, Integer transfer) {
				// TODO Auto-generated method stub
				if (response.isSuccess()) {
					JSONArray jsa = response.jSONArrayFromData();
					adapter.setData(jsa);
				}
			}
		});
	}

}

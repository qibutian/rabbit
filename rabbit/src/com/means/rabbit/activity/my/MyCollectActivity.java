package com.means.rabbit.activity.my;

import net.duohuo.dhroid.adapter.NetJSONAdapter;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.views.RefreshListViewAndMore;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

/**
 * 收藏列表
 * @author dell
 *
 */
public class MyCollectActivity extends RabbitBaseActivity {
	RefreshListViewAndMore listV;
	ListView contentListV;

	NetJSONAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_collect);
	}

	@Override
	public void initView() {
		setTitle("收藏列表");
		
		listV = (RefreshListViewAndMore) findViewById(R.id.my_listview);
		String url = API.text;
		contentListV = listV.getListView();
		
		adapter = new NetJSONAdapter(url, self,
				R.layout.item_my_collect);
		adapter.fromWhat("list");
		// setUrl("http://cwapi.gongpingjia.com:8080/v2/activity/list?latitude=32&longitude=118&maxDistance=5000000&token="+user.getToken()+"&userId="+user.getUserId());
//		adapter.addField("area_name", R.id.name);
//		adapter.addField("image", R.id.pic, "default");
		listV.setAdapter(adapter);
	}
}

package com.means.rabbit.activity.my;

import net.duohuo.dhroid.adapter.NetJSONAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.means.rabbit.R;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.views.RefreshListViewAndMore;

/**
 * 配送地址
 * 
 * @author dell
 * 
 */
public class ShippingAddressActivity extends RabbitBaseActivity {
	RefreshListViewAndMore listV;
	ListView contentListV;

	NetJSONAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shipping_address);
	}

	@Override
	public void initView() {
		setTitle("配送地址");

		listV = (RefreshListViewAndMore) findViewById(R.id.my_listview);
		String url = API.text;
		contentListV = listV.getListView();

		adapter = new NetJSONAdapter(url, self, R.layout.item_shipping_address);
		adapter.fromWhat("list");
		// setUrl("http://cwapi.gongpingjia.com:8080/v2/activity/list?latitude=32&longitude=118&maxDistance=5000000&token="+user.getToken()+"&userId="+user.getUserId());
		// adapter.addField("area_name", R.id.name);
		// adapter.addField("image", R.id.pic, "default");
		listV.setAdapter(adapter);
		
		listV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//新增地址
				Intent it = new Intent(self, AddShippingAddressActivity.class);
				startActivity(it);
			}
		});
	}
}

package com.means.rabbit.activity.my;

import net.duohuo.dhroid.adapter.FieldMap;
import net.duohuo.dhroid.adapter.NetJSONAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
	
	Button addaddressBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shipping_address);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.shippingaddressactivity));

		listV = (RefreshListViewAndMore) findViewById(R.id.my_listview);
		String url = API.addressuserlist;
		contentListV = listV.getListView();

		adapter = new NetJSONAdapter(url, self, R.layout.item_shipping_address);
		adapter.fromWhat("list");
		 adapter.addField("lxname", R.id.lxname);
		 adapter.addField("lxphone", R.id.lxphone);
		 adapter.addField("lxaddress", R.id.lxaddress);
		 adapter.addField(new FieldMap("dft",R.id.dft) {
			
			@Override
			public Object fix(View itemV, Integer position, Object o, Object jo) {
				if (o.toString().equals("1")) {
					itemV.findViewById(R.id.dft).setVisibility(View.VISIBLE);
				}else {
					itemV.findViewById(R.id.dft).setVisibility(View.INVISIBLE);
				}
				
				return getString(R.string.item_shippingaddress_default);
			}
		});
		listV.setAdapter(adapter);
		
		addaddressBtn = (Button) findViewById(R.id.addaddress);
		
		addaddressBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//新增地址
				Intent it = new Intent(self, AddShippingAddressActivity.class);
				startActivity(it);
			}
		});
	}
}

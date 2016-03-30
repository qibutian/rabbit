package com.means.rabbit.activity.my.order;

import java.util.Date;

import org.json.JSONObject;

import net.duohuo.dhroid.adapter.FieldMap;
import net.duohuo.dhroid.adapter.NetJSONAdapter;
import net.duohuo.dhroid.net.JSONUtil;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.activity.order.pay.GroupPayActivity;
import com.means.rabbit.activity.order.pay.HotelOrderDetailActivity;
import com.means.rabbit.activity.order.pay.InsteadShoppingPayActivity;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.utils.DateUtils;
import com.means.rabbit.views.RefreshListViewAndMore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 商家订单
 * @author dell
 *
 */
public class BusinessOrderActivity extends RabbitBaseActivity {
	RefreshListViewAndMore listV;
	ListView contentListV;

	NetJSONAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_business_order);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle(getString(R.string.business_order));
		
		listV = (RefreshListViewAndMore) findViewById(R.id.my_listview);
		String url = API.orderbusinesslist;
		contentListV = listV.getListView();

		adapter = new NetJSONAdapter(url, self, R.layout.item_order_list);
		adapter.fromWhat("list");
		adapter.addField("title", R.id.title);
//		adapter.addField("paystatus", R.id.paystatus);
		adapter.addField(new FieldMap("payprice", R.id.payprice) {

			@Override
			public Object fix(View itemV, Integer position, Object o, Object jo) {
				
				JSONObject data = (JSONObject) jo;
				
				// TODO Auto-generated method stub
				TextView paystatusT = (TextView) itemV.findViewById(R.id.paystatus);
				int paystatus = JSONUtil.getInt(data, "paystatus");
				if(paystatus==1) {
					paystatusT.setText("待支付");
				} else if(paystatus==2) {
					paystatusT.setText("已完成");
				} else {
					paystatusT.setText("支付失败");
				}

				return getString(R.string.money_symbol)+"  " + o.toString();
			}
		});
		adapter.addField(new FieldMap("adddateline", R.id.adddateline) {

			@Override
			public Object fix(View itemV, Integer position, Object o, Object jo) {
				// TODO Auto-generated method stub
				return DateUtils.dateToStrLong(new Date(Long.parseLong(o
						.toString()) * 1000));
			}
		});
		adapter.addField("title", R.id.title);
		listV.setAdapter(adapter);

		contentListV
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						JSONObject jo = adapter.getTItem(position);
						Intent it = new Intent(self, BusinessOrderDetailsActivity.class);
						it.putExtra("orderid", JSONUtil.getString(jo, "id"));
						startActivity(it);
						// TODO Auto-generated method stub

					}
				});
	}
}

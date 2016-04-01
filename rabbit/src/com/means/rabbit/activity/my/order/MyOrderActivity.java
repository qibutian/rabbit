package com.means.rabbit.activity.my.order;

import java.util.Date;

import net.duohuo.dhroid.adapter.FieldMap;
import net.duohuo.dhroid.adapter.NetJSONAdapter;
import net.duohuo.dhroid.net.JSONUtil;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.means.rabbit.R;
import com.means.rabbit.activity.order.pay.GroupPayActivity;
import com.means.rabbit.activity.order.pay.HotelOrderDetailActivity;
import com.means.rabbit.activity.order.pay.InsteadShoppingPayActivity;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.utils.DateUtils;
import com.means.rabbit.views.RefreshListViewAndMore;
import com.means.rabbit.views.pop.MyOrderPop;
import com.means.rabbit.views.pop.MyOrderPop.OnReslutClickListener;

/**
 * 我的订单
 * 
 * @author dell
 * 
 */
public class MyOrderActivity extends RabbitBaseActivity implements
		OnClickListener {
	RefreshListViewAndMore listV;
	ListView contentListV;

	NetJSONAdapter adapter;

	LinearLayout classifyLl, typeLl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_order);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle(getString(R.string.my_order));

		classifyLl = (LinearLayout) findViewById(R.id.classify);
		typeLl = (LinearLayout) findViewById(R.id.type);

		classifyLl.setOnClickListener(this);
		typeLl.setOnClickListener(this);

		listV = (RefreshListViewAndMore) findViewById(R.id.my_listview);
		contentListV = listV.getListView();

		getDate();
	}

	private void showPop(int type, View v) {
		MyOrderPop pop;
		pop = new MyOrderPop(self, type);
		pop.setOnReslutClickListener(new OnReslutClickListener() {

			@Override
			public void result(int type, String catname, String tag) {
				if (type == 0) {
					adapter.addparam("type ", tag);
					adapter.refresh();
				} else {
					// showToast(tag);
					adapter.addparam("status ", tag);
					adapter.refresh();
				}
				// TODO Auto-generated method stub
			}
		});
		pop.show(v);
	}

	private void getDate() {
		adapter = new NetJSONAdapter(API.orderlist, self,
				R.layout.item_order_list);
		adapter.fromWhat("list");
		adapter.addField("title", R.id.title);
		// adapter.addField("paystatus", R.id.paystatus);
		adapter.addField(new FieldMap("payprice", R.id.payprice) {

			@Override
			public Object fix(View itemV, Integer position, Object o, Object jo) {

				JSONObject data = (JSONObject) jo;

				// TODO Auto-generated method stub
				TextView paystatusT = (TextView) itemV
						.findViewById(R.id.paystatus);
				int paystatus = JSONUtil.getInt(data, "paystatus");

				if (JSONUtil.getInt(data, "orderstatus") == 2) {
					paystatusT.setText("已完成");
				} else if (JSONUtil.getInt(data, "orderstatus") == 3) {
					paystatusT.setText("已取消");
				} else if (paystatus == 1) {
					paystatusT.setText("待支付");
				} else if (paystatus == 2) {
					paystatusT.setText("已支付");
				} else if (paystatus == 3) {
					paystatusT.setText("支付失败");
				}

				return getString(R.string.money_symbol) + "  " + o.toString();
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
						Intent it;
						int type = JSONUtil.getInt(jo, "type");
						if (type == 2) {
							it = new Intent(self, GroupPayActivity.class);

						} else if (type == 1) {
							it = new Intent(self,
									HotelOrderDetailActivity.class);
						} else {
							it = new Intent(self,
									InsteadShoppingPayActivity.class);
						}
						it.putExtra("orderid", JSONUtil.getString(jo, "id"));
						startActivity(it);
						// TODO Auto-generated method stub

					}
				});
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		// 分类
		case R.id.classify:
			showPop(0, v);
			break;
		// 状态
		case R.id.type:
			showPop(1, v);
			break;

		default:
			break;
		}
	}
}

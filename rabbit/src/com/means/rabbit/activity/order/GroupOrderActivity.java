package com.means.rabbit.activity.order;

import org.json.JSONObject;

import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.activity.order.pay.GroupPayActivity;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.views.CartView;
import com.means.rabbit.views.CartView.OnCartViewClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 团购订单
 * 
 * @author Administrator
 * 
 */
public class GroupOrderActivity extends RabbitBaseActivity {

	String tuangouId;

	CartView cartView;

	Double price;

	TextView totalPriceT;

	int credit;

	TextView telT;

	TextView shifuT;

	int credit_s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_order);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle("团购订单");
		tuangouId = getIntent().getStringExtra("tuangouId");
		cartView = (CartView) findViewById(R.id.cartView);
		totalPriceT = (TextView) findViewById(R.id.total_price);
		telT = (TextView) findViewById(R.id.tel);

		findViewById(R.id.submit).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				submit();

			}
		});
		shifuT = (TextView) findViewById(R.id.shifu);
		getData();
	}

	private void getData() {
		DhNet net = new DhNet(API.preTuangouOrder);
		net.addParam("itemid", tuangouId);
		net.doGetInDialog(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {
				if (response.isSuccess()) {

					JSONObject jo = response.jSONFromData();
					ViewUtil.bindView(findViewById(R.id.name),
							JSONUtil.getString(jo, "title"));
					price = JSONUtil.getDouble(jo, "price");
					ViewUtil.bindView(findViewById(R.id.price), "￥" + price);
					cartView.setMaxNum(100);
					cartView.setOnCartViewClickListener(new OnCartViewClickListener() {

						@Override
						public void onClick() {

							totalPriceT.setText("￥" + cartView.getCartNum()
									* price);
							shifuT.setText(cartView.getCartNum() * price
									- credit_s + "");
						}
					});
					totalPriceT.setText("￥" + price);
					JSONObject user_dataJo = JSONUtil.getJSONObject(jo,
							"user_data");

					credit = JSONUtil.getInt(user_dataJo, "credit");
					ViewUtil.bindView(findViewById(R.id.jifen), credit + "");
					credit_s = JSONUtil.getInt(user_dataJo, "credit_s");
					ViewUtil.bindView(findViewById(R.id.dikou),
							"￥" + JSONUtil.getString(user_dataJo, "credit_s"));

					ViewUtil.bindView(telT,
							JSONUtil.getString(user_dataJo, "phone"));
					shifuT.setText(price - credit_s + "");

				}

			}
		});
	}

	private void submit() {

		DhNet net = new DhNet(API.addTuangouOrder);
		net.addParam("itemid", tuangouId);
		net.addParam("buyerphone", telT.getText().toString());
		net.addParam("ordercount", cartView.getCartNum());
		net.addParam("credit", credit);
		net.doPostInDialog("提交中...", new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {
				if (response.isSuccess()) {
					JSONObject jo = response.jSONFromData();
					Intent it = new Intent(self, GroupPayActivity.class);
					it.putExtra("orderId", JSONUtil.getString(jo, "id"));
					startActivity(it);
				}

			}
		});

	}
}

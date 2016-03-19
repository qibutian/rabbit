package com.means.rabbit.activity.order.pay;

import org.json.JSONObject;

import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 
 * 支付订单
 * 
 * @author Administrator
 * 
 */
public class PayOrderActivity extends RabbitBaseActivity {

	String payprice;

	String orderid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay_order);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle(getString(R.string.pay_order));
		Intent it = getIntent();
		payprice = it.getStringExtra("payprice");
		orderid = it.getStringExtra("orderid");
		ViewUtil.bindView(findViewById(R.id.name), it.getStringExtra("name"));
		ViewUtil.bindView(findViewById(R.id.payprice), "￥" + payprice);

		findViewById(R.id.paybymoney).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				payByMoney();
			}
		});
		
		getMyMoney();
	}

	private void payByMoney() {
		DhNet net = new DhNet(API.pay);
		net.addParam("orderid", orderid);
		net.addParam("payprice", payprice);
		net.doPostInDialog("支付中...", new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {
				if (response.isSuccess()) {
					showToast("支付成功!");
					Intent it = getIntent();
					setResult(Activity.RESULT_OK, it);
					finish();
				}

			}
		});
	}
	
	private  void  getMyMoney () {
		DhNet net = new DhNet(API.accountview);
		net.doGetInDialog(new NetTask(self) {
			
			@Override
			public void doInUI(Response response, Integer transfer) {
				// TODO Auto-generated method stub
				if (response.isSuccess()) {
					JSONObject jo = response.jSONFromData();
					ViewUtil.bindView(findViewById(R.id.money), "￥"+JSONUtil.getString(jo, "balance"));
				}
			}
		});
	}
}

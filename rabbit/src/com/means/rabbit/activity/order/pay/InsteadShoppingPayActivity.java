package com.means.rabbit.activity.order.pay;

import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;

import org.json.JSONObject;

import com.means.rabbit.R;
import com.means.rabbit.RabbitValueFix;
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
import android.widget.Button;
import android.widget.TextView;

/**
 * 
 * 代购支付
 * 
 * @author Administrator
 * 
 */
public class InsteadShoppingPayActivity extends RabbitBaseActivity {
	String daigouId;

	Button payB;

	int credit_s;

	TextView shifuT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instead_shopping_pay);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle(getString(R.string.insteadshopping));
		daigouId = getIntent().getStringExtra("orderid");
		payB = (Button) findViewById(R.id.pay);
		getData();
	}

	private void getData() {
		DhNet net = new DhNet(API.daigouOrderDetail);
		net.addParam("orderid", daigouId);
		net.doGetInDialog(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {
				if (response.isSuccess()) {
					JSONObject jo = response.jSONFromData();

					ViewUtil.bindView(findViewById(R.id.name),
							JSONUtil.getString(jo, "title"));

					ViewUtil.bindView(findViewById(R.id.price),
							JSONUtil.getString(jo, "singleprice"));
					ViewUtil.bindView(findViewById(R.id.code),
							JSONUtil.getString(jo, "code"));

					ViewUtil.bindView(findViewById(R.id.total_price), "￥"
							+ JSONUtil.getString(jo, "payprice"));
					ViewUtil.bindView(findViewById(R.id.count),
							JSONUtil.getString(jo, "count"));

					String orderTime = RabbitValueFix.getStandardTime(
							JSONUtil.getLong(jo, "adddateline"), "yyyy-MM-dd");

					ViewUtil.bindView(findViewById(R.id.adddateline), orderTime);
					ViewUtil.bindView(findViewById(R.id.buyerphone),
							JSONUtil.getString(jo, "buyerphone"));

					ViewUtil.bindView(findViewById(R.id.buyername),
							JSONUtil.getString(jo, "buyername"));

					ViewUtil.bindView(findViewById(R.id.buyernote),
							JSONUtil.getString(jo, "buyernote"));

					JSONObject credit_dataJo = JSONUtil.getJSONObject(jo,
							"credit_data");

					ViewUtil.bindView(findViewById(R.id.credit),
							JSONUtil.getString(credit_dataJo, "credit"));

					credit_s = JSONUtil.getInt(credit_dataJo, "credit");
					ViewUtil.bindView(findViewById(R.id.credit_s), "￥"
							+ credit_s);
					ViewUtil.bindView(findViewById(R.id.ercode),
							JSONUtil.getString(jo, "ercode"));

					ViewUtil.bindView(findViewById(R.id.shifu),
							JSONUtil.getInt(jo, "payprice") - credit_s + "");

					final int paystatus = JSONUtil.getInt(jo, "paystatus");

					payB.setBackgroundResource(paystatus == 1 ? R.drawable.fillet_10_pink_bg
							: R.drawable.fillet_10_green_bg);
					payB.setText(paystatus == 1 ? "支付订单" : "已支付");

					payB.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							Intent it;
							if (paystatus == 1) {
								it = new Intent(self, PayOrderActivity.class);
								startActivity(it);
							}

						}
					});

					JSONObject user_addressJo = JSONUtil.getJSONObject(jo,
							"user_address");

					ViewUtil.bindView(findViewById(R.id.add_username),
							JSONUtil.getString(user_addressJo, "lxname"));
					ViewUtil.bindView(findViewById(R.id.add_tel),
							JSONUtil.getString(user_addressJo, "lxphone"));
					ViewUtil.bindView(
							findViewById(R.id.address),
							JSONUtil.getString(user_addressJo, "areaname")
									+ JSONUtil.getString(user_addressJo,
											"lxaddress"));

					// reality_moneyT.setText((JSONUtil.getDouble(jo,
					// "totalprice")-JSONUtil.getDouble(user_data, "credit_s"))
					// + "");
				}

			}
		});
	}
}

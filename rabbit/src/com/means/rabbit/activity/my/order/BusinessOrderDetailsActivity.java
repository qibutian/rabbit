package com.means.rabbit.activity.my.order;

import org.json.JSONObject;

import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;

import com.means.rabbit.R;
import com.means.rabbit.RabbitValueFix;
import com.means.rabbit.R.layout;
import com.means.rabbit.activity.comment.PostCommentMainActivity;
import com.means.rabbit.activity.main.ErweimaActivity;
import com.means.rabbit.activity.order.pay.PayOrderActivity;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 商家订单详情
 * 
 * @author dell
 * 
 */
public class BusinessOrderDetailsActivity extends RabbitBaseActivity {

	String orderid;

	int credit_s;

	TextView shifuT;

	Button payB;

	EditText errcodeE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_business_order_details);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.business_order_details));
		orderid = getIntent().getStringExtra("orderid");
		errcodeE = (EditText) findViewById(R.id.erweima_code);
		payB = (Button) findViewById(R.id.pay);
		getData();
	}

	private void getData() {
		DhNet net = new DhNet(API.orderbusinessdetail);
		net.addParam("orderid", orderid);
		net.doGetInDialog("加载中...", new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {

				if (response.isSuccess()) {

					final JSONObject jo = response.jSONFromData();
					ViewUtil.bindView(findViewById(R.id.name),
							JSONUtil.getString(jo, "title"));

					ViewUtil.bindView(findViewById(R.id.price),
							JSONUtil.getString(jo, "singleprice"));
					ViewUtil.bindView(findViewById(R.id.code),
							JSONUtil.getString(jo, "code"));

					ViewUtil.bindView(
							findViewById(R.id.total_price),
							getString(R.string.money_symbol)
									+ JSONUtil.getInt(jo, "singleprice")
									* JSONUtil.getInt(jo, "count"));
					ViewUtil.bindView(findViewById(R.id.count),
							JSONUtil.getString(jo, "count"));

					String orderTime = RabbitValueFix.getStandardTime(
							JSONUtil.getLong(jo, "adddateline"), "yyyy-MM-dd");

					ViewUtil.bindView(findViewById(R.id.order_time), orderTime);
					ViewUtil.bindView(findViewById(R.id.tel),
							JSONUtil.getString(jo, "buyerphone"));

					JSONObject credit_dataJo = JSONUtil.getJSONObject(jo,
							"credit_data");

					ViewUtil.bindView(findViewById(R.id.credit),
							JSONUtil.getString(credit_dataJo, "credit"));

					credit_s = JSONUtil.getInt(credit_dataJo, "credit");
					ViewUtil.bindView(findViewById(R.id.credit_s),
							getString(R.string.money_symbol) + credit_s);
					// ViewUtil.bindView(findViewById(R.id.ercode),
					// JSONUtil.getString(jo, "ercode"));

					ViewUtil.bindView(findViewById(R.id.shifu),
							JSONUtil.getDouble(jo, "payprice") + "");

					final int paystatus = JSONUtil.getInt(jo, "orderstatus");

					payB.setBackgroundResource(paystatus == 1 ? R.drawable.fillet_10_pink_bg
							: R.drawable.fillet_10_green_bg);
					payB.setText(paystatus == 1 ? "输入消费码" : "消费完成");
					payB.setVisibility(View.VISIBLE);
					payB.setTag(paystatus);
					payB.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							if (payB.getTag().equals(1)) {
								usecode();
							}

						}
					});

					findViewById(R.id.erweima).setOnClickListener(
							new OnClickListener() {

								@Override
								public void onClick(View v) {

									if (TextUtils.isEmpty(JSONUtil.getString(
											jo, "ercode_img"))) {
										return;
									}

									Intent it = new Intent(self,
											ErweimaActivity.class);
									it.putExtra("url", JSONUtil.getString(jo,
											"ercode_img"));
									startActivity(it);
								}
							});

				}

			}
		});
	}

	private void usecode() {
		if (TextUtils.isEmpty(errcodeE.getText().toString())) {
			showToast("请输入消费码!");
			return;
		}
		DhNet net = new DhNet(API.usecode);
		net.addParam("orderid", orderid);
		net.addParam("ercode", errcodeE.getText().toString());
		net.doGetInDialog("提交中...", new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {

				if (response.isSuccess()) {
					payB.setText("消费完成");
					payB.setBackgroundResource(R.drawable.fillet_10_green_bg);
					payB.setTag(2);
				}

			}
		});
	}
}

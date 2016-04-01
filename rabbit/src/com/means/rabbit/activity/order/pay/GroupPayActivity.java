package com.means.rabbit.activity.order.pay;

import org.json.JSONObject;

import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.RabbitValueFix;
import com.means.rabbit.activity.comment.PostCommentMainActivity;
import com.means.rabbit.activity.main.ErweimaActivity;
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
import android.widget.TextView;

/**
 * 团购支付
 * 
 * @author Administrator
 * 
 */
public class GroupPayActivity extends RabbitBaseActivity {

	String orderId;

	int credit_s;

	TextView shifuT;

	Button payB;

	public int pay = 1003;

	public int comment = 1004;

	int servicestatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_pay);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.grouporder));
		orderId = getIntent().getStringExtra("orderid");
		payB = (Button) findViewById(R.id.pay);
		findViewById(R.id.cancle).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				cancleOrder();
			}
		});
		getData();
	}

	private void getData() {
		DhNet net = new DhNet(API.tuangouOrderDetail);
		net.addParam("orderid", orderId);
		net.doGetInDialog(new NetTask(self) {

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
					ViewUtil.bindView(findViewById(R.id.ercode),
							JSONUtil.getString(jo, "ercode"));

					ViewUtil.bindView(findViewById(R.id.shifu),
							JSONUtil.getDouble(jo, "payprice") + "");

					final int paystatus = JSONUtil.getInt(jo, "paystatus");

					payB.setBackgroundResource(paystatus == 1 ? R.drawable.fillet_10_pink_bg
							: R.drawable.fillet_10_green_bg);
					if (paystatus == 2 && servicestatus == 2
							&& JSONUtil.getInt(jo, "orderstatus") == 2) {
						payB.setText("已完成");
						payB.setBackgroundResource(R.drawable.fillet_10_green_bg);
					} else if (JSONUtil.getInt(jo, "orderstatus") == 3) {
						payB.setText("已取消");
						payB.setBackgroundResource(R.drawable.fillet_10_pink_bg);
					} else if (servicestatus == 1
							&& JSONUtil.getInt(jo, "orderstatus") == 2) {
						payB.setText("发布评论");
						payB.setBackgroundResource(R.drawable.fillet_10_pink_bg);
					} else if (paystatus == 1) {
						payB.setText("支付订单");
						payB.setBackgroundResource(R.drawable.fillet_10_pink_bg);
					} else if (paystatus == 2) {
						payB.setText("已支付");
						payB.setBackgroundResource(R.drawable.fillet_10_green_bg);
					}
					payB.setVisibility(View.VISIBLE);
					payB.setTag(paystatus);
					payB.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							Intent it;
							if (payB.getTag().equals(1)) {
								it = new Intent(self, PayOrderActivity.class);
								it.putExtra("payprice",
										JSONUtil.getString(jo, "payprice"));
								it.putExtra("orderid",
										JSONUtil.getString(jo, "id"));
								it.putExtra("name",
										JSONUtil.getString(jo, "title"));
								startActivityForResult(it, pay);
							} else {
								if (servicestatus == 1&& JSONUtil.getInt(jo, "orderstatus") == 2) {
									it = new Intent(self,
											PostCommentMainActivity.class);
									it.putExtra("contentid",
											JSONUtil.getString(jo, "contentid"));
									it.putExtra("type", "1");
									startActivityForResult(it, comment);
								}
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == pay && resultCode == Activity.RESULT_OK) {
			payB.setText("已支付");
			payB.setBackgroundResource(R.drawable.fillet_10_green_bg);
			payB.setTag(2);
		}

		if (requestCode == comment && resultCode == Activity.RESULT_OK) {
			payB.setText("已评论");
			payB.setBackgroundResource(R.drawable.fillet_10_green_bg);
			payB.setTag(2);
			servicestatus = 2;
		}
	}

	private void cancleOrder() {
		DhNet net = new DhNet(API.cancelOrder);
		net.addParam("orderid", orderId);
		net.doPostInDialog("取消中...", new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {

				if (response.isSuccess()) {
					showToast("取消成功!");
					finish();
				}

			}
		});
	}
}

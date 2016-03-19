package com.means.rabbit.activity.order.pay;

import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.means.rabbit.R;
import com.means.rabbit.RabbitValueFix;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;

/**
 * 
 * 酒店订单详情
 * 
 * @author Administrator
 * 
 */
public class HotelOrderDetailActivity extends RabbitBaseActivity {

	TextView titleT, nameT, dateT, signlpirceT, countT, totalpriceT, idT,
			buyernoteT, buyerphoneT, buyernameT, creditT, credit_sT,
			reality_moneyT;

	String itemid;

	Button grogshop_btn;

	public int pay = 1003;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grogshop_pay);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.grogshop_pay_order));

		itemid = getIntent().getStringExtra("orderid");

		titleT = (TextView) findViewById(R.id.title_name);
		nameT = (TextView) findViewById(R.id.name);
		dateT = (TextView) findViewById(R.id.date);
		signlpirceT = (TextView) findViewById(R.id.signlpirce);
		countT = (TextView) findViewById(R.id.count);
		totalpriceT = (TextView) findViewById(R.id.totalprice);
		idT = (TextView) findViewById(R.id.id);
		buyerphoneT = (TextView) findViewById(R.id.buyerphone);
		buyernameT = (TextView) findViewById(R.id.buyername);
		buyernoteT = (TextView) findViewById(R.id.buyernote);
		creditT = (TextView) findViewById(R.id.credit);
		credit_sT = (TextView) findViewById(R.id.credit_s);
		reality_moneyT = (TextView) findViewById(R.id.reality_money);

		grogshop_btn = (Button) findViewById(R.id.grogshop_btn);

		findViewById(R.id.cancle).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				cancleOrder();
			}
		});

		getGrogshopData();
	}

	private void getGrogshopData() {
		DhNet net = new DhNet(API.hotelOrderDetail);
		net.addParam("orderid", itemid);
		net.doGetInDialog(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {
				if (response.isSuccess()) {
					final JSONObject jo = response.jSONFromData();
					titleT.setText(JSONUtil.getString(jo, "title"));
					nameT.setText(JSONUtil.getString(jo, "buyername"));

					String startdate = RabbitValueFix.getStandardTime(
							JSONUtil.getLong(jo, "startdate"), "yyyy-MM-dd");
					String enddate = RabbitValueFix.getStandardTime(
							JSONUtil.getLong(jo, "enddate"), "yyyy-MM-dd");

					dateT.setText("入住" + startdate + " 离开" + enddate);

					signlpirceT.setText("￥"
							+ JSONUtil.getString(jo, "singleprice"));
					countT.setText(JSONUtil.getString(jo, "daycount"));
					totalpriceT.setText("￥" + JSONUtil.getInt(jo, "count")
							* JSONUtil.getInt(jo, "singleprice"));
					idT.setText(JSONUtil.getString(jo, "code"));
					buyerphoneT.setText(JSONUtil.getString(jo, "buyerphone"));
					buyernameT.setText(JSONUtil.getString(jo, "buyername"));
					buyernoteT.setText(JSONUtil.getString(jo, "buyernote"));
					JSONObject credit_data = JSONUtil.getJSONObject(jo,
							"credit_data");
					creditT.setText(JSONUtil.getString(credit_data, "credit"));
					credit_sT.setText(JSONUtil.getString(credit_data,
							"credit_s"));
					ViewUtil.bindView(findViewById(R.id.ercode),
							JSONUtil.getString(jo, "ercode"));

					ViewUtil.bindView(findViewById(R.id.count),
							JSONUtil.getString(jo, "count"));
					ViewUtil.bindView(findViewById(R.id.name),
							JSONUtil.getString(jo, "name"));

					final int paystatus = JSONUtil.getInt(jo, "paystatus");

					grogshop_btn.setText(paystatus == 1 ? "支付订单" : "已支付");
					grogshop_btn.setTag(paystatus);
					grogshop_btn
							.setBackgroundResource(paystatus == 1 ? R.drawable.fillet_10_pink_bg
									: R.drawable.fillet_10_green_bg);
					grogshop_btn.setVisibility(View.VISIBLE);
					grogshop_btn.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View arg0) {

							Intent it;

							if (grogshop_btn.getTag().equals(1)) {
								it = new Intent(self, PayOrderActivity.class);
								it.putExtra("payprice",
										JSONUtil.getString(jo, "payprice"));
								it.putExtra("orderid",
										JSONUtil.getString(jo, "id"));
								it.putExtra("name",
										JSONUtil.getString(jo, "title"));
								startActivityForResult(it, pay);
							}

						}
					});
					reality_moneyT.setText((JSONUtil.getInt(jo, "payprice")));
				}
			}
		});
	}

	private void cancleOrder() {
		DhNet net = new DhNet(API.cancelOrder);
		net.addParam("orderid", itemid);
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == pay && resultCode == Activity.RESULT_OK) {
			grogshop_btn.setText("已支付");
			grogshop_btn.setBackgroundResource(R.drawable.fillet_10_green_bg);
			grogshop_btn.setTag(2);
		}
	}

}

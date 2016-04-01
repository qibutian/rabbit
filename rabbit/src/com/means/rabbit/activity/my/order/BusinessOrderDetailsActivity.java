package com.means.rabbit.activity.my.order;

import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Intents;
import com.means.rabbit.R;
import com.means.rabbit.RabbitValueFix;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;

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

	public final int REQUEST_CODE = 10086;

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
							} else {
								finish();
							}

						}
					});

					findViewById(R.id.erweima).setOnClickListener(
							new OnClickListener() {

								@Override
								public void onClick(View v) {
									callCapture();
									// if (TextUtils.isEmpty(JSONUtil.getString(
									// jo, "ercode_img"))) {
									// return;
									// }
									//
									// Intent it = new Intent(self,
									// ErweimaActivity.class);
									// it.putExtra("url", JSONUtil.getString(jo,
									// "ercode_img"));
									// startActivity(it);
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
		net.doPostInDialog("提交中...", new NetTask(self) {

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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (null != data && requestCode == REQUEST_CODE) {
			switch (resultCode) {
			case Activity.RESULT_OK:
				errcodeE.setText(data.getStringExtra(Intents.Scan.RESULT));
				usecode();

				break;
			default:
				break;
			}
		}
	}

	private void callCapture() {
		Intent intent = new Intent();
		intent.setAction(Intents.Scan.ACTION);
		// intent.putExtra(Intents.Scan.MODE, Intents.Scan.QR_CODE_MODE);
		intent.putExtra(Intents.Scan.CHARACTER_SET, "UTF-8");
		intent.putExtra(Intents.Scan.WIDTH, 600);
		intent.putExtra(Intents.Scan.HEIGHT, 600);
		// intent.putExtra(Intents.Scan.PROMPT_MESSAGE,
		// "type your prompt message");
		intent.setClass(this, CaptureActivity.class);
		startActivityForResult(intent, REQUEST_CODE);
	}
}

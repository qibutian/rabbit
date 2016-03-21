package com.means.rabbit.activity.order;

import org.json.JSONObject;

import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.activity.order.pay.PayOrderActivity;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 
 * 优惠买单
 * 
 * @author Administrator
 * 
 */
public class AddFavorableMainActivity extends RabbitBaseActivity {

	double payprice;

	String contentid;

	EditText jifenE;

	TextView daikouT, shifuT;

	// 积分比例
	float creditY;

	int credit;

	public int pay = 1003;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorable_pay_main);

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle(getString(R.string.favorable));
		Intent it = getIntent();
		payprice = it.getDoubleExtra("payprice", 0);
		ViewUtil.bindView(findViewById(R.id.pay_price), "￥" + payprice);
		contentid = it.getStringExtra("contentid");
		shifuT = (TextView) findViewById(R.id.shifu);
		jifenE = (EditText) findViewById(R.id.credit);
		daikouT = (TextView) findViewById(R.id.credit_s);
		jifenE.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (creditY != 0) {

					int jifen = Integer.parseInt(jifenE.getText().toString());
					if (jifen > credit) {
						showToast("你输入的积分超过了您的积分,请输入小于" + credit + "的数字!");
						jifenE.setText(0);
					} else {
						float daikou = jifen / creditY;
						daikouT.setText("￥" + daikou);
						shifuT.setText(payprice - daikou + "");
					}
				}
			}
		});

		findViewById(R.id.submit).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				submit();
			}
		});

		getcrdeit();

	}

	private void getcrdeit() {
		DhNet net = new DhNet(API.paycredit);
		net.doGetInDialog(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {

				if (response.isSuccess()) {
					JSONObject jo = response.jSONFromData();

					// JSONObject user_dataJo = JSONUtil.getJSONObject(jo,
					// "user_data");

					credit = JSONUtil.getInt(jo, "credit");
					int credit_s = JSONUtil.getInt(jo, "credit_s");
					if (credit_s != 0) {
						creditY = credit / (float) credit_s;

					} else {
						jifenE.setText(0 + "");
					}

					jifenE.setEnabled(credit_s == 0 ? false : true);
				}

			}
		});
	}

	private void submit() {
		Intent it = new Intent(self, PayOrderActivity.class);
		it.putExtra("payprice", payprice + "");
		it.putExtra("contentid", contentid);
		it.putExtra("name", getIntent().getStringExtra("name"));
		startActivityForResult(it, pay);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == pay && resultCode == Activity.RESULT_OK) {
			finish();
		}
	}
}

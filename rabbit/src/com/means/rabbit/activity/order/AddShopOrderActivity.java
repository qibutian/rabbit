package com.means.rabbit.activity.order;

import java.text.ParseException;

import org.json.JSONObject;

import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.activity.order.pay.HotelOrderDetailActivity;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.utils.RabbitUtils;
import com.means.rabbit.views.CartView;
import com.means.rabbit.views.CartView.OnCartViewClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 
 * 酒店订单
 * 
 * @author Administrator
 * 
 */
public class AddShopOrderActivity extends RabbitBaseActivity {

	String startDate, endDate;

	EditText msgE, telE, usernameE;

	CartView cartView;

	String id;

	TextView totalPriceT;

	Double price;

	int credit;

	TextView shifuT;

	int credit_s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grogshop_order);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle("酒店订单");
		Intent it = getIntent();
		cartView = (CartView) findViewById(R.id.cartView);
		msgE = (EditText) findViewById(R.id.msg);
		telE = (EditText) findViewById(R.id.tel);
		usernameE = (EditText) findViewById(R.id.username);
		totalPriceT = (TextView) findViewById(R.id.total_price);

		startDate = it.getStringExtra("startDate");
		endDate = it.getStringExtra("endDate");

		try {
			int days = RabbitUtils.daysBetween(startDate, endDate);
			ViewUtil.bindView(findViewById(R.id.date), "入住" + startDate + "离开"
					+ endDate + " [" + days + "]晚");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		id = it.getStringExtra("id");

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
		DhNet net = new DhNet(API.preHotelOrder);
		net.addParam("itemid", id);
		net.addParam("startdate", startDate);
		net.addParam("enddate", endDate);
		net.doGetInDialog(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {

				if (response.isSuccess()) {
					JSONObject jo = response.jSONFromData();
					ViewUtil.bindView(findViewById(R.id.name),
							JSONUtil.getString(jo, "title"));
					ViewUtil.bindView(findViewById(R.id.fangxing),
							JSONUtil.getString(jo, "name"));

					JSONObject user_dataJo = JSONUtil.getJSONObject(jo,
							"user_data");

					credit = JSONUtil.getInt(user_dataJo, "credit");
					credit_s = JSONUtil.getInt(user_dataJo, "credit_s");
					ViewUtil.bindView(findViewById(R.id.jifen), credit + "");

					ViewUtil.bindView(findViewById(R.id.dikou), "￥" + credit_s);

					ViewUtil.bindView(findViewById(R.id.tel),
							JSONUtil.getString(user_dataJo, "phone"));

					ViewUtil.bindView(findViewById(R.id.username),
							JSONUtil.getString(user_dataJo, "nickname"));
					price = JSONUtil.getDouble(jo, "dayprice");

					totalPriceT.setText("￥" + price);

					ViewUtil.bindView(findViewById(R.id.price),
							"￥" + JSONUtil.getString(jo, "dayprice") + "/晚");

					ViewUtil.bindView(findViewById(R.id.old_price), "￥"
							+ JSONUtil.getString(jo, "dayprice") + "/晚");
					cartView.setOnCartViewClickListener(new OnCartViewClickListener() {

						@Override
						public void onClick() {

							totalPriceT.setText("￥" + cartView.getCartNum()
									* price);
							shifuT.setText(cartView.getCartNum() * price
									- credit_s + "");
						}
					});
					cartView.setMaxNum(JSONUtil.getInt(jo, "mincount"));
					shifuT.setText(price - credit_s + "");

				}

			}
		});
	}

	private void submit() {

		DhNet net = new DhNet(API.addHotelOrder);
		net.addParam("itemid", id);

		net.addParam("itemid", id);
		net.addParam("startdate", startDate);
		net.addParam("enddate", endDate);
		net.addParam("buyernote", msgE.getText().toString());
		net.addParam("buyername", usernameE.getText().toString());
		net.addParam("buyerphone", telE.getText().toString());
		net.addParam("ordercount", cartView.getCartNum());
		net.addParam("credit", credit);
		net.doPostInDialog("提交中...", new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {
				if (response.isSuccess()) {
					showToast("提交成功!");
					JSONObject jo = response.jSON();
					Intent it = new Intent(self, HotelOrderDetailActivity.class);
					it.putExtra("orderid", JSONUtil.getInt(jo, "id"));
					startActivity(it);
				}

			}
		});

	}
}

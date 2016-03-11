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
		cartView.setOnCartViewClickListener(new OnCartViewClickListener() {

			@Override
			public void onClick() {

				totalPriceT.setText("￥" + cartView.getCartNum() * price);
			}
		});
		msgE = (EditText) findViewById(R.id.msg);
		telE = (EditText) findViewById(R.id.tel);
		usernameE = (EditText) findViewById(R.id.username);
		totalPriceT = (TextView) findViewById(R.id.total_price);

		startDate = it.getStringExtra("startDate");
		endDate = it.getStringExtra("endDate");

		try {
			int days = RabbitUtils.daysBetween(startDate, endDate);
			ViewUtil.bindView(findViewById(R.id.date), "入住" + startDate + "离开"
					+ endDate + "[" + days + "]");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		id = it.getStringExtra("id");

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
					ViewUtil.bindView(findViewById(R.id.jifen),
							JSONUtil.getString(user_dataJo, "credit"));

					ViewUtil.bindView(findViewById(R.id.dikou),
							"￥" + JSONUtil.getString(user_dataJo, "credit_s"));

					ViewUtil.bindView(findViewById(R.id.tel),
							JSONUtil.getString(user_dataJo, "phone"));

					ViewUtil.bindView(findViewById(R.id.username),
							JSONUtil.getString(jo, "nickname"));
					price = JSONUtil.getDouble(jo, "dayprice");
					ViewUtil.bindView(findViewById(R.id.price),
							"￥" + JSONUtil.getString(jo, "dayprice") + "/晚");

					ViewUtil.bindView(findViewById(R.id.old_price), "￥"
							+ JSONUtil.getString(jo, "dayprice") + "/晚");
					cartView.setMaxNum(JSONUtil.getInt(jo, "mincount"));

				}

			}
		});
	}

	private void submit() {
		
		DhNet  net = new DhNet(API.addHotelOrder);

	}
}

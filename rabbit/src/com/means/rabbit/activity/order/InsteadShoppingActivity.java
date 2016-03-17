package com.means.rabbit.activity.order;

import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;

import org.json.JSONObject;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.activity.home.SelectDistrictActivity;
import com.means.rabbit.activity.my.ShippingAddressActivity;
import com.means.rabbit.activity.order.pay.HotelOrderDetailActivity;
import com.means.rabbit.activity.order.pay.InsteadShoppingPayActivity;
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
import android.widget.EditText;
import android.widget.TextView;

/**
 * 
 * 代购订单
 * 
 * @author Administrator
 * 
 */
public class InsteadShoppingActivity extends RabbitBaseActivity {

	String daigouId;
	EditText msgE, telE, usernameE;

	CartView cartView;

	TextView totalPriceT;

	Double price;

	int credit;

	TextView shifuT;

	int credit_s;

	final int Address = 1001;

	String addressId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instead_shopping);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.insteadshopping));
		daigouId = getIntent().getStringExtra("daigouId");
		cartView = (CartView) findViewById(R.id.cartView);
		msgE = (EditText) findViewById(R.id.msg);
		telE = (EditText) findViewById(R.id.tel);
		usernameE = (EditText) findViewById(R.id.username);
		totalPriceT = (TextView) findViewById(R.id.total_price);

		findViewById(R.id.submit).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				submit();

			}
		});

		findViewById(R.id.address_layout).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent it = new Intent(self,
								ShippingAddressActivity.class);
						startActivityForResult(it, Address);
					}
				});

		shifuT = (TextView) findViewById(R.id.shifu);
		getData();
	}

	private void getData() {
		DhNet net = new DhNet(API.preDaigouOrder);
		net.addParam("itemid", daigouId);
		net.doGetInDialog(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {

				if (response.isSuccess()) {
					JSONObject jo = response.jSONFromData();
					ViewUtil.bindView(findViewById(R.id.name),
							JSONUtil.getString(jo, "title"));

					JSONObject user_dataJo = JSONUtil.getJSONObject(jo,
							"user_data");

					credit = JSONUtil.getInt(user_dataJo, "credit");
					credit_s = JSONUtil.getInt(user_dataJo, "credit_s");
					ViewUtil.bindView(findViewById(R.id.credit), credit + "");
					ViewUtil.bindView(findViewById(R.id.youfei),
							"￥" + JSONUtil.getString(jo, "emoney"));
					ViewUtil.bindView(findViewById(R.id.credit_s), "￥"
							+ credit_s);

					ViewUtil.bindView(findViewById(R.id.tel),
							JSONUtil.getString(user_dataJo, "phone"));

					ViewUtil.bindView(findViewById(R.id.username),
							JSONUtil.getString(user_dataJo, "nickname"));
					price = JSONUtil.getDouble(jo, "price");

					totalPriceT.setText("￥" + price);

					ViewUtil.bindView(findViewById(R.id.price), "￥" + price);

					cartView.setOnCartViewClickListener(new OnCartViewClickListener() {

						@Override
						public void onClick() {

							totalPriceT.setText("￥" + cartView.getCartNum()
									* price);
							shifuT.setText(cartView.getCartNum() * price
									- credit_s + "");
						}
					});
					cartView.setMaxNum(100);
					shifuT.setText(price - credit_s + "");

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
					addressId = JSONUtil.getString(user_addressJo, "id");
				}

			}
		});
	}

	private void submit() {

		DhNet net = new DhNet(API.addDaigouOrder);
		net.addParam("itemid", daigouId);

		net.addParam("buyernote", msgE.getText().toString());
		net.addParam("buyername", usernameE.getText().toString());
		net.addParam("buyerphone", telE.getText().toString());
		net.addParam("ordercount", cartView.getCartNum());
		net.addParam("credit", credit);
		net.addParam("addressid", addressId);
		net.doPostInDialog("提交中...", new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {
				if (response.isSuccess()) {
					showToast("提交成功!");
					JSONObject jo = response.jSON();
					Intent it = new Intent(self,
							InsteadShoppingPayActivity.class);
					it.putExtra("orderid", JSONUtil.getInt(jo, "id"));
					startActivity(it);
				}

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK && requestCode == Address) {
			addressId = data.getStringExtra("id");

			ViewUtil.bindView(findViewById(R.id.add_username),
					data.getStringExtra("lxname"));
			ViewUtil.bindView(findViewById(R.id.add_tel),
					data.getStringExtra("lxphone"));
			ViewUtil.bindView(
					findViewById(R.id.address),
					data.getStringExtra("areaname")
							+ data.getStringExtra("lxaddress"));

		}

	}

}

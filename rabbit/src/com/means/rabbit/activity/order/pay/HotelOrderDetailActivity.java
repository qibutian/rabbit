package com.means.rabbit.activity.order.pay;

import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;

import org.json.JSONObject;

import android.os.Bundle;
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

	int itemid;

	Button grogshop_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grogshop_pay);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.grogshop_pay_order));

		itemid = getIntent().getIntExtra("itemid", -1);

		titleT = (TextView) findViewById(R.id.title);
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

		getGrogshopData();
	}

	private void getGrogshopData() {
		DhNet net = new DhNet(API.hotelOrderDetail);
		net.addParam("orderid", itemid);
		net.doGetInDialog(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {
				if (response.isSuccess()) {
					JSONObject jo = response.jSONFromData();
					titleT.setText(JSONUtil.getString(jo, "title"));
					nameT.setText(JSONUtil.getString(jo, "buyername"));
					
					String startdate = RabbitValueFix.getStandardTime(JSONUtil.getLong(jo, "startdate"),"yyyy-MM-dd");
					String enddate = RabbitValueFix.getStandardTime(JSONUtil.getLong(jo, "enddate"),"yyyy-MM-dd");
					
					dateT.setText("入住" + startdate
							+ " 离开" + enddate);
					
					
					
					signlpirceT.setText(JSONUtil.getString(jo, "signlpirce"));
					countT.setText(JSONUtil.getString(jo, "daycount"));
					 totalpriceT.setText(JSONUtil.getString(jo,
					 "payprice"));
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
					grogshop_btn.setText(JSONUtil.getString(jo, "paystatus")
							.equals("1") ? "待支付" : "已支付");

					// reality_moneyT.setText((JSONUtil.getDouble(jo,
					// "totalprice")-JSONUtil.getDouble(user_data, "credit_s"))
					// + "");
				}
			}
		});
	}
}

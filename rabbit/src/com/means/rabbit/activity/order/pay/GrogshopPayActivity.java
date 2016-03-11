package com.means.rabbit.activity.order.pay;

import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;

import org.json.JSONObject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.means.rabbit.R;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;

/**
 * 
 * 酒店订单预览
 * @author Administrator
 *
 */
public class GrogshopPayActivity extends RabbitBaseActivity {
	
	TextView titleT,nameT,dateT,signlpirceT,mincountT,totalpriceT,idT,phoneT,nicknameT,creditT,credit_sT,reality_moneyT;

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
		mincountT = (TextView) findViewById(R.id.mincount);
		totalpriceT = (TextView) findViewById(R.id.totalprice);
		idT = (TextView) findViewById(R.id.id);
		phoneT = (TextView) findViewById(R.id.phone);
		nicknameT = (TextView) findViewById(R.id.nickname);
		creditT = (TextView) findViewById(R.id.credit);
		credit_sT = (TextView) findViewById(R.id.credit_s);
		reality_moneyT = (TextView) findViewById(R.id.reality_money);
		
		grogshop_btn = (Button) findViewById(R.id.grogshop_btn);
		
		getGrogshopData();
	}

	private void getGrogshopData() {
		DhNet net = new DhNet(API.orderpreview);
		net.addParam("itemid", itemid);
		net.doGetInDialog(new NetTask(self) {
			
			@Override
			public void doInUI(Response response, Integer transfer) {
				if (response.isSuccess()) {
					JSONObject jo = response.jSONFromData();
					titleT.setText(JSONUtil.getString(jo, "title"));
					nameT.setText(JSONUtil.getString(jo, "name"));
					dateT.setText("入住"+JSONUtil.getString(jo, "startdate")+" 离开"+JSONUtil.getString(jo, "enddate"));
					signlpirceT.setText(JSONUtil.getString(jo, "signlpirce"));
					mincountT.setText(JSONUtil.getString(jo, "mincount"));
					totalpriceT.setText(JSONUtil.getString(jo, "totalprice"));
					idT.setText(JSONUtil.getString(jo, "id"));
					JSONObject user_data = JSONUtil.getJSONObject(jo, "user_data");
					phoneT.setText(JSONUtil.getString(user_data, "phone"));
					nicknameT.setText(JSONUtil.getString(user_data, "nickname"));
					creditT.setText(JSONUtil.getString(user_data, "credit"));
					credit_sT.setText(JSONUtil.getString(user_data, "credit_s"));
					
					reality_moneyT.setText((JSONUtil.getDouble(jo, "totalprice")-JSONUtil.getDouble(user_data, "credit_s")) + "");
				}
			}
		});
	}
}

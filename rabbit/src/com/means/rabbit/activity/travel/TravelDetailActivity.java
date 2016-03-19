package com.means.rabbit.activity.travel;

import java.util.Date;

import org.json.JSONObject;

import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.means.rabbit.R;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.utils.DateUtils;

/**
 * 
 * 旅游详情
 * 
 * @author Administrator
 * 
 */
public class TravelDetailActivity extends RabbitBaseActivity {

	private ImageView picI;
	private TextView titleT, adddatelineT, viewsT;

	private int id;

	WebView contentT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_travel_detail);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.travel_details));
		id = getIntent().getIntExtra("id", 0);

		picI = (ImageView) findViewById(R.id.pic);
		titleT = (TextView) findViewById(R.id.ttitle);
		adddatelineT = (TextView) findViewById(R.id.adddateline);
		viewsT = (TextView) findViewById(R.id.views);
		contentT = (WebView) findViewById(R.id.content);
		getTravelDetail();

	}

	private void getTravelDetail() {
		DhNet net = new DhNet(API.contentview);
		net.addParam("id", id);
		net.doGetInDialog(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {
				// TODO Auto-generated method stub
				if (response.isSuccess()) {
					JSONObject jo = response.jSONFromData();
					ViewUtil.bindNetImage(picI, JSONUtil.getString(jo, "pic"),
							"default");
					ViewUtil.bindView(titleT, JSONUtil.getString(jo, "title"));
					contentT.loadDataWithBaseURL(null,JSONUtil.getString(jo,
							"content"),"text/html", "utf-8",null);
					ViewUtil.bindView(
							adddatelineT,
							"发布时间 "
									+ DateUtils.dateToStr(new Date(Long
											.parseLong(JSONUtil.getString(jo,
													"adddateline")) * 1000)));
					ViewUtil.bindView(viewsT,
							"阅读 " + JSONUtil.getString(jo, "views"));
				}
			}
		});
	}

}

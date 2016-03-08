package com.means.rabbit.activity.merchants;

import org.json.JSONArray;
import org.json.JSONObject;

import net.duohuo.dhroid.adapter.NetJSONAdapter;
import net.duohuo.dhroid.adapter.PSAdapter;
import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RatingBar;

import com.means.rabbit.R;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.views.CommentView;
import com.means.rabbit.views.KeyVauleView;
import com.means.rabbit.views.RefreshListViewAndMore;
import com.means.rabbit.views.ShopDetailTuangouView;

public class ShopDetailActivity extends RabbitBaseActivity {

	View headV;

	KeyVauleView keyValueView;

	ShopDetailTuangouView shopDetailTuangouView;

	CommentView commentView;

	RefreshListViewAndMore listV;

	ListView contentListV;

	NetJSONAdapter adapter;

	String shopId;
	
	RatingBar ratingBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_detail);
	}

	@Override
	public void initView() {
		setTitle("商家详情");
		shopId = getIntent().getStringExtra("shopId");
		headV = LayoutInflater.from(self).inflate(R.layout.head_shop_detail,
				null);

		listV = (RefreshListViewAndMore) findViewById(R.id.my_listview);
		listV.addHeadView(headV);
		contentListV = listV.getListView();
		adapter = new NetJSONAdapter(API.text, self,
				R.layout.item_shop_detail_tuangou_near);
		adapter.fromWhat("list");
		listV.setAdapter(adapter);
		contentListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

			}
		});

		keyValueView = (KeyVauleView) headV.findViewById(R.id.keyvule_view);
		keyValueView.setData();

		shopDetailTuangouView = (ShopDetailTuangouView) headV
				.findViewById(R.id.tuangou_view);
		// shopDetailTuangouView.setData();

		commentView = (CommentView) headV.findViewById(R.id.comment_view);
		commentView.setData();
		getShopDetalData();
	}

	private void getShopDetalData() {
		DhNet net = new DhNet(API.shopDetail);
		net.addParam("id", shopId);
		net.doGet(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {
				if (response.isSuccess()) {
					JSONObject detailJo = response.jSON();

					ViewUtil.bindView(headV.findViewById(R.id.title),
							JSONUtil.getString(detailJo, "title"));

					ViewUtil.bindView(headV.findViewById(R.id.address),
							JSONUtil.getString(detailJo, "address"));

					ViewUtil.bindView(headV.findViewById(R.id.tel),
							JSONUtil.getString(detailJo, "lxphone"));

					ViewUtil.bindView(headV.findViewById(R.id.label),
							JSONUtil.getString(detailJo, "label_in"));
					ratingBar.setRating(JSONUtil.getFloat(detailJo, "score"));
				}

			}
		});
	}

}

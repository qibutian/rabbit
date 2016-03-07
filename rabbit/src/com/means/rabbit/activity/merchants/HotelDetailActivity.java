package com.means.rabbit.activity.merchants;

import net.duohuo.dhroid.adapter.FieldMap;
import net.duohuo.dhroid.adapter.NetJSONAdapter;
import net.duohuo.dhroid.adapter.PSAdapter;
import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.means.rabbit.R;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.views.CommentView;
import com.means.rabbit.views.HotelYudingView;
import com.means.rabbit.views.NomalGallery;
import com.means.rabbit.views.RefreshListViewAndMore;
import com.means.rabbit.views.ShopDetailTuangouView;

public class HotelDetailActivity extends RabbitBaseActivity {
	View headV;

	HotelYudingView hotelYudingView;

	ShopDetailTuangouView shopDetailTuangouView;

	CommentView commentView;

	RefreshListViewAndMore listV;

	ListView contentListV;

	NetJSONAdapter adapter;
	String hotelId;

	NomalGallery gallery;

	PSAdapter galleryAdapter;

	// 图片数量
	TextView pic_countT;

	TextView priceT;

	RatingBar ratingBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_detail);
	}

	@Override
	public void initView() {
		setTitle("酒店详情");
		hotelId = getIntent().getStringExtra("hotelId");
		headV = LayoutInflater.from(self).inflate(R.layout.head_hotel_detail,
				null);

		listV = (RefreshListViewAndMore) findViewById(R.id.my_listview);
		listV.addHeadView(headV);
		contentListV = listV.getListView();
		adapter = new NetJSONAdapter(API.hotelDetailNearTuangou, self,
				R.layout.item_shop_detail_tuangou_near);
		adapter.fromWhat("list");
		adapter.addparam("contentid", hotelId);
		adapter.addparam("type", 2);
		adapter.addField("pic", R.id.pic);
		adapter.addField("title", R.id.title);
		adapter.addField("oldprice", R.id.oldprice);
		adapter.addField("ordercount", R.id.sell_count);
		adapter.addField(new FieldMap("price", R.id.price) {

			@Override
			public Object fix(View itemV, Integer position, Object o, Object jo) {
				// TODO Auto-generated method stub
				return "￥" + o + "/人";
			}
		});

		listV.setAdapter(adapter);
		contentListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

			}
		});

		hotelYudingView = (HotelYudingView) headV
				.findViewById(R.id.yuding_view);

		shopDetailTuangouView = (ShopDetailTuangouView) headV
				.findViewById(R.id.tuangou_view);

		commentView = (CommentView) headV.findViewById(R.id.comment_view);
		commentView.setData();
		gallery = (NomalGallery) headV.findViewById(R.id.gallery);
		pic_countT = (TextView) headV.findViewById(R.id.pic_count);
		priceT = (TextView) headV.findViewById(R.id.price);
		ratingBar = (RatingBar) headV.findViewById(R.id.ratingbar);
		getHotelDetalData();
		getOrderList();
		getTuangouList();
	}

	// 获取详情数据
	private void getHotelDetalData() {
		DhNet net = new DhNet(API.hoteldetail);
		net.addParam("id", hotelId);
		net.doGet(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {
				if (response.isSuccess()) {
					JSONObject detailJo = response.jSON();
					JSONArray image_data = JSONUtil.getJSONArray(detailJo,
							"image_data");
					if (image_data != null && image_data.length() != 0) {
						pic_countT.setText(image_data.length() + "");
					} else {
						pic_countT.setText(0 + "");
					}
					galleryAdapter = new PSAdapter(self, R.layout.item_gallery);
					galleryAdapter.addAll(image_data);
					gallery.setAdapter(galleryAdapter);

					priceT.setText(JSONUtil.getString(detailJo, "prcie") + "起");

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

	// 获取预订列表
	private void getOrderList() {
		DhNet net = new DhNet(API.hotelDetailOrderList);
		net.addParam("contentid", hotelId);
		net.doGet(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {

				if (response.isSuccess()) {
					hotelYudingView.setData(response.jSONArrayFrom("list"));
				}

			}
		});
	}
	
	//获取团购数据
	private  void  getTuangouList () {
		DhNet net = new DhNet(API.hotelDetailNearTuangou);
		net.addParam("contentid", hotelId);
		net.doGet(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {

				if (response.isSuccess()) {
					shopDetailTuangouView.setData(response.jSONArrayFrom("list"));
				}

			}
		});
	}
}

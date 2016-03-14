package com.means.rabbit.activity.merchants;

import org.json.JSONArray;
import org.json.JSONObject;

import net.duohuo.dhroid.adapter.FieldMap;
import net.duohuo.dhroid.adapter.NetJSONAdapter;
import net.duohuo.dhroid.adapter.PSAdapter;
import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RatingBar;

import com.means.rabbit.R;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.views.CommentView;
import com.means.rabbit.views.KeyVauleView;
import com.means.rabbit.views.NomalGallery;
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

	NomalGallery gallery;

	PSAdapter galleryAdapter;

	// 图片数量
	TextView pic_countT;

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
		adapter = new NetJSONAdapter(API.hotelDetailNearTuangou, self,
				R.layout.item_shop_detail_tuangou_near);
		adapter.fromWhat("list");
		adapter.addparam("contentid", shopId);
		adapter.addparam("type", 1);
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
				JSONObject jo = adapter.getTItem(position
						- contentListV.getHeaderViewsCount());
				Intent it = new Intent(self, TuangouDetailActivity.class);
				it.putExtra("tuangouId", JSONUtil.getString(jo, "id"));
				startActivity(it);
			}
		});

		keyValueView = (KeyVauleView) headV.findViewById(R.id.keyvule_view);

		shopDetailTuangouView = (ShopDetailTuangouView) headV
				.findViewById(R.id.tuangou_view);

		commentView = (CommentView) headV.findViewById(R.id.comment_view);
		gallery = (NomalGallery) headV.findViewById(R.id.gallery);
		ratingBar = (RatingBar) headV.findViewById(R.id.ratingbar);
		pic_countT = (TextView) headV.findViewById(R.id.pic_count);
		getShopDetalData();
		getTuangouList();
		getCommentList();
	}

	private void getShopDetalData() {
		DhNet net = new DhNet(API.shopDetail);
		net.addParam("id", shopId);
		net.doGet(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {
				if (response.isSuccess()) {
					JSONObject detailJo = response.jSONFromData();
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

					ViewUtil.bindView(headV.findViewById(R.id.title),
							JSONUtil.getString(detailJo, "title"));

					ViewUtil.bindView(headV.findViewById(R.id.address),
							JSONUtil.getString(detailJo, "address"));

					ViewUtil.bindView(headV.findViewById(R.id.tel),
							JSONUtil.getString(detailJo, "lxphone"));

					ViewUtil.bindView(headV.findViewById(R.id.time),
							JSONUtil.getString(detailJo, "yytime"));
					ViewUtil.bindView(headV.findViewById(R.id.comment_des),
							JSONUtil.getString(detailJo, "score") + "/"
									+ JSONUtil.getString(detailJo, "comment"));
					ViewUtil.bindView(headV.findViewById(R.id.price), "￥"
							+ JSONUtil.getString(detailJo, "price") + "/"
							+ JSONUtil.getString(detailJo, "price_o"));
					ratingBar.setRating(JSONUtil.getFloat(detailJo, "score"));

					JSONArray field_dataJSA = JSONUtil.getJSONArray(detailJo,
							"field_data");
					keyValueView.setData(field_dataJSA,JSONUtil.getString(detailJo, "content"));
				}

			}
		});
	}

	// 获取团购数据
	private void getTuangouList() {
		DhNet net = new DhNet(API.hotelDetailNearTuangou);
		net.addParam("contentid", shopId);
		net.addParam("step", 3);
		net.doGet(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {

				if (response.isSuccess()) {
					shopDetailTuangouView.setData(response
							.jSONArrayFrom("list"));
				}

			}
		});
	}

	private void getCommentList() {
		DhNet net = new DhNet(API.commentlist);
		net.addParam("contentid", shopId);
		net.addParam("type", 1);
		net.addParam("step", 2);
		net.doGet(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {

				if (response.isSuccess()) {
					// commentView.setData(response
					// .jSONArrayFrom("list"));
				}

			}
		});
	}

}

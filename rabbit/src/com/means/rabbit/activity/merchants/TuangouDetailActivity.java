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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.means.rabbit.R;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.views.CommentView;
import com.means.rabbit.views.KeyVauleView;
import com.means.rabbit.views.NomalGallery;
import com.means.rabbit.views.RefreshListViewAndMore;
import com.means.rabbit.views.ShopDetailTuangouView;

public class TuangouDetailActivity extends RabbitBaseActivity {

	View headV;

	KeyVauleView keyValueView;

	CommentView commentView;

	RefreshListViewAndMore listV;

	ListView contentListV;

	NetJSONAdapter adapter;

	String tuangouId;

	RatingBar ratingBar;

	NomalGallery gallery;

	PSAdapter galleryAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tuangou_detail);
	}

	@Override
	public void initView() {
		setTitle("团购详情");
		tuangouId = getIntent().getStringExtra("tuangouId");
		headV = LayoutInflater.from(self).inflate(R.layout.head_tuangou_detail,
				null);

		listV = (RefreshListViewAndMore) findViewById(R.id.my_listview);
		listV.addHeadView(headV);
		contentListV = listV.getListView();
		adapter = new NetJSONAdapter(API.hotelDetailNearTuangou, self,
				R.layout.item_shop_detail_tuangou_near);
		adapter.fromWhat("list");
		adapter.addparam("contentid", tuangouId);
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

		commentView = (CommentView) headV.findViewById(R.id.comment_view);
		commentView.setData();
		gallery = (NomalGallery) headV.findViewById(R.id.gallery);
		ratingBar = (RatingBar) headV.findViewById(R.id.ratingbar);
		getShopDetalData();
		getCommentList();
	}

	private void getShopDetalData() {
		DhNet net = new DhNet(API.tuangouDetail);
		net.addParam("id", tuangouId);
		net.doGet(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {
				if (response.isSuccess()) {
					JSONObject detailJo = response.jSONFromData();
					JSONArray image_data = JSONUtil.getJSONArray(detailJo,
							"image_data");
					galleryAdapter = new PSAdapter(self, R.layout.item_gallery);
					galleryAdapter.addAll(image_data);
					gallery.setAdapter(galleryAdapter);

					ViewUtil.bindView(headV.findViewById(R.id.title),
							JSONUtil.getString(detailJo, "title"));

					ViewUtil.bindView(headV.findViewById(R.id.address),
							JSONUtil.getString(detailJo, "address"));
					ViewUtil.bindView(headV.findViewById(R.id.name),
							JSONUtil.getString(detailJo, "shoptitle"));

					ViewUtil.bindView(headV.findViewById(R.id.comment_des),
							JSONUtil.getString(detailJo, "score") + "/"
									+ JSONUtil.getString(detailJo, "comment"));
					ViewUtil.bindView(headV.findViewById(R.id.price), "￥"
							+ JSONUtil.getString(detailJo, "price"));

					ViewUtil.bindView(headV.findViewById(R.id.old_price), "￥"
							+ JSONUtil.getString(detailJo, "oldprice"));
					ViewUtil.bindView(headV.findViewById(R.id.goumaides),
							JSONUtil.getString(detailJo, "goumaides"));
					ratingBar.setRating(JSONUtil.getFloat(detailJo, "score"));

					JSONArray field_dataJSA = JSONUtil.getJSONArray(detailJo,
							"field_data");
					keyValueView.setData(field_dataJSA);
				}

			}
		});
	}

	private void getCommentList() {
		DhNet net = new DhNet(API.commentlist);
		net.addParam("contentid", tuangouId);
		net.addParam("type", 1);
		net.addParam("step", 3);
		net.doGet(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {

				if (response.isSuccess()) {
				}

			}
		});
	}

}

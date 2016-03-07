package com.means.rabbit.activity.merchants;

import net.duohuo.dhroid.adapter.NetJSONAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_detail);
	}

	@Override
	public void initView() {
		setTitle("商家详情");
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
//		shopDetailTuangouView.setData();

		commentView = (CommentView) headV.findViewById(R.id.comment_view);
		commentView.setData();
	}

}

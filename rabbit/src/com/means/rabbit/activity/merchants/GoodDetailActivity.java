package com.means.rabbit.activity.merchants;

import android.os.Bundle;

import com.means.rabbit.R;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.views.CommentView;
import com.means.rabbit.views.KeyVauleView;

/**
 * 
 * 代购详情
 * 
 * @author Administrator
 *
 */

public class GoodDetailActivity extends RabbitBaseActivity {
	KeyVauleView keyValueView;

	CommentView commentView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gooddetail);
	}

	@Override
	public void initView() {
		setTitle("商品详情");
		keyValueView = (KeyVauleView) findViewById(R.id.keyvule_view);
//		keyValueView.setData();

		commentView = (CommentView) findViewById(R.id.comment_view);
		commentView.setData();
	}

}

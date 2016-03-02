package com.means.rabbit.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.means.rabbit.R;
import com.means.rabbit.activity.my.order.BusinessOrderDetailsActivity;
import com.means.rabbit.activity.my.order.MyOrderActivity;

public class MyIndexFragment extends Fragment implements OnClickListener {
	static MyIndexFragment instance;

	View mainV;

	LayoutInflater mLayoutInflater;

	LinearLayout editinfoLl, systemmsgLl, business_orderLl, my_orderLl,
			collectLl, shipping_addressLl, integralLl, commentV;

	Button logoutBtn;

	public static MyIndexFragment getInstance() {
		if (instance == null) {
			instance = new MyIndexFragment();
		}

		return instance;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainV = inflater.inflate(R.layout.fragment_my, null);
		mLayoutInflater = inflater;
		initView();
		// TODO Auto-generated method stub
		return mainV;
	}

	private void initView() {
		editinfoLl = (LinearLayout) mainV.findViewById(R.id.editinfo);
		systemmsgLl = (LinearLayout) mainV.findViewById(R.id.systemmsg);
		business_orderLl = (LinearLayout) mainV
				.findViewById(R.id.business_order);
		my_orderLl = (LinearLayout) mainV.findViewById(R.id.my_order);
		shipping_addressLl = (LinearLayout) mainV
				.findViewById(R.id.shipping_address);
		collectLl = (LinearLayout) mainV.findViewById(R.id.collect);
		integralLl = (LinearLayout) mainV.findViewById(R.id.integral);
		commentV = (LinearLayout) mainV.findViewById(R.id.comment);
		logoutBtn = (Button) mainV.findViewById(R.id.logout);
		editinfoLl.setOnClickListener(this);
		systemmsgLl.setOnClickListener(this);
		business_orderLl.setOnClickListener(this);
		my_orderLl.setOnClickListener(this);
		shipping_addressLl.setOnClickListener(this);
		collectLl.setOnClickListener(this);
		integralLl.setOnClickListener(this);
		commentV.setOnClickListener(this);
		logoutBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent it;
		switch (v.getId()) {
		// 编辑资料
		case R.id.editinfo:
			it = new Intent(getActivity(), EditInfoActivity.class);
			startActivity(it);
			break;
		// 系统消息
		case R.id.systemmsg:
			it = new Intent(getActivity(), SystemMsgActivity.class);
			startActivity(it);
			break;
		// 商家订单
		case R.id.business_order:
			// it = new Intent(getActivity(), BusinessOrderActivity.class);
			it = new Intent(getActivity(), BusinessOrderDetailsActivity.class);
			startActivity(it);
			break;
		// 我的订单
		case R.id.my_order:
			it = new Intent(getActivity(), MyOrderActivity.class);
			startActivity(it);
			break;
		// 配送地址
		case R.id.shipping_address:
			it = new Intent(getActivity(), ShippingAddressActivity.class);
			startActivity(it);
			break;
		// 收藏列表
		case R.id.collect:
			it = new Intent(getActivity(), MyCollectActivity.class);
			startActivity(it);
			break;
		// 积分明细
		case R.id.integral:
			it = new Intent(getActivity(), MyIntegralActivity.class);
			startActivity(it);
			break;

		// 我的评论
		case R.id.comment:
			it = new Intent(getActivity(), CommentListActivity.class);
			startActivity(it);
			break;
		// 退出当前用户
		case R.id.logout:
			it = new Intent(getActivity(), LoginActivity.class);
			startActivity(it);
			break;
		default:
			break;
		}
	}
}

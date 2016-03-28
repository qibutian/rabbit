package com.means.rabbit.activity.more;

import java.io.File;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.means.rabbit.R;
import com.means.rabbit.activity.comment.PostCommentMainActivity;
import com.means.rabbit.activity.finance.FinanceDetailActivity;
import com.means.rabbit.utils.FileUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 更多
 * 
 * @author Administrator
 * 
 */
public class MoreFragment extends Fragment implements OnClickListener {
	static MoreFragment instance;

	View mainV;

	LayoutInflater mLayoutInflater;

	View aboutV, langswitcherV, feedbackV, richscanV, versionV, helpV,
			wipe_cacheV;

	File mCacheDir;

	TextView cacheT;

	public static MoreFragment getInstance() {
		if (instance == null) {
			instance = new MoreFragment();
		}

		return instance;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainV = inflater.inflate(R.layout.fragment_more, null);
		mLayoutInflater = inflater;
		initView();
		// TODO Auto-generated method stub
		return mainV;
	}

	private void initView() {

		aboutV = mainV.findViewById(R.id.about);
		langswitcherV = mainV.findViewById(R.id.langswitcher);
		feedbackV = mainV.findViewById(R.id.feedback);
		richscanV = mainV.findViewById(R.id.richscan);
		versionV = mainV.findViewById(R.id.version);
		helpV = mainV.findViewById(R.id.help);
		wipe_cacheV = mainV.findViewById(R.id.wipe_cache);

		cacheT = (TextView) mainV.findViewById(R.id.cache);
		mCacheDir = new File(getActivity().getExternalCacheDir(), "Rabbit");
		cacheT.setText(String.valueOf(FileUtil.getFileOrDirSize(mCacheDir,
				FileUtil.UNIT_SACLE.M)) + " M");

		aboutV.setOnClickListener(this);
		langswitcherV.setOnClickListener(this);
		feedbackV.setOnClickListener(this);
		richscanV.setOnClickListener(this);
		versionV.setOnClickListener(this);
		helpV.setOnClickListener(this);
		wipe_cacheV.setOnClickListener(this);

		try {
			// 获取当前版本
			((TextView) mainV.findViewById(R.id.version_txt))
					.setText(getVersionName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		Intent it;
		switch (v.getId()) {
		// 关于我们
		case R.id.about:
			it = new Intent(getActivity(), AboutUsActivity.class);
			startActivity(it);
			break;
		// 语言选择
		case R.id.langswitcher:
			it = new Intent(getActivity(), PostCommentMainActivity.class);
			startActivity(it);
			break;
		// 反馈
		case R.id.feedback:
			it = new Intent(getActivity(), FeedbackActivity.class);
			startActivity(it);
			break;
		// 扫一扫
		case R.id.richscan:
			break;
		// 版本更新
		case R.id.version:

			break;
		// 帮助
		case R.id.help:

			break;
		// 清楚缓存
		case R.id.wipe_cache:
			ImageLoader.getInstance().getMemoryCache().clear();
			ImageLoader.getInstance().getDiskCache().clear();
			if (FileUtil.deleteFileOrDir(mCacheDir)) {
				Toast.makeText(getActivity(),
						getActivity().getString(R.string.wipe_cache_clear),
						Toast.LENGTH_SHORT).show();
				cacheT.setText("0 M");
			}
			break;

		default:
			break;
		}
	}

	private String getVersionName() throws Exception {
		// 获取packagemanager的实例
		PackageManager packageManager = getActivity().getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = packageManager.getPackageInfo(getActivity()
				.getPackageName(), 0);
		String version = packInfo.versionName;
		return version;
	}
}

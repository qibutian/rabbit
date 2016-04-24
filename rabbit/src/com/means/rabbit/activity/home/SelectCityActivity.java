package com.means.rabbit.activity.home;

import net.duohuo.dhroid.ioc.IocContainer;
import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.GlobalParams;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.means.rabbit.R;
import com.means.rabbit.adapter.CityAdapter;
import com.means.rabbit.adapter.CountryAdapter;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.bean.DistrictEB;
import com.means.rabbit.bean.CityEB;
import com.means.rabbit.utils.RabbitPerference;

import de.greenrobot.event.EventBus;

public class SelectCityActivity extends RabbitBaseActivity {

	ListView countrylist, citylist;

	JSONArray jsa;

	CountryAdapter countryAdapter;

	CityAdapter cityAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_city);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.select_city));

		countrylist = (ListView) findViewById(R.id.countrylist);
		citylist = (ListView) findViewById(R.id.citylist);
		countryAdapter = new CountryAdapter(self);
		countrylist.setAdapter(countryAdapter);
		countrylist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				countryAdapter.setCurrentPosition(position);
				JSONObject jo = countryAdapter.getItem(position);
				try {
					JSONArray jsa = jo.getJSONArray("_child");
					cityAdapter.setData(jsa);
				} catch (JSONException e) {
					cityAdapter.setData(null);
					RabbitPerference per = IocContainer.getShare().get(
							RabbitPerference.class);
					per.load();
					per.catid = JSONUtil.getString(jo, "id");
					per.cityname = JSONUtil.getString(jo, "name");
					per.commit();

					GlobalParams globalParams = IocContainer.getShare().get(
							GlobalParams.class);
					globalParams.setGlobalParam("cityid",
							JSONUtil.getString(jo, "id"));

					CityEB city = new CityEB();
					city.setCatid(JSONUtil.getString(jo, "id"));
					city.setCityname(JSONUtil.getString(jo, "name"));
					EventBus.getDefault().post(city);
					finish();
					e.printStackTrace();
				}
			}
		});
		cityAdapter = new CityAdapter(self);
		citylist.setAdapter(cityAdapter);
		citylist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				JSONObject jo = cityAdapter.getItem(position);

				RabbitPerference per = IocContainer.getShare().get(
						RabbitPerference.class);
				per.load();
				per.catid = JSONUtil.getString(jo, "id");
				per.cityname = JSONUtil.getString(jo, "name");
				per.commit();

				GlobalParams globalParams = IocContainer.getShare().get(
						GlobalParams.class);
				globalParams.setGlobalParam("cityid",
						JSONUtil.getString(jo, "id"));

				CityEB city = new CityEB();
				city.setCatid(JSONUtil.getString(jo, "id"));
				city.setCityname(JSONUtil.getString(jo, "name"));
				EventBus.getDefault().post(city);

				finish();
			}
		});
		getData();
	}

	private void getData() {
		DhNet net = new DhNet(API.citylist);
		net.doGetInDialog("加载中...", new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {

				if (response.isSuccess()) {
					jsa = response.jSONArrayFromData();
					countryAdapter.setData(jsa);
				}

			}
		});
	}

}

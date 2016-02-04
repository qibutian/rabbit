package com.means.rabbit.activity.my;

import net.duohuo.dhroid.adapter.NetJSONAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.means.rabbit.R;
import com.means.rabbit.views.RefreshListViewAndMore;

/**
 * 系统消息
 * @author dell
 *
 */
public class SystemMsgActivity extends Activity {
	RefreshListViewAndMore listV;
	ListView contentListV;

	NetJSONAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_system_msg);
		
		listV = (RefreshListViewAndMore) findViewById(R.id.my_listview);
		String url = "http://www.foodies.im/wap.php?g=Wap&c=Travel&a=cityList";
		contentListV = listV.getListView();
		
		adapter = new NetJSONAdapter(url, SystemMsgActivity.this,
				R.layout.item_system_msg);
		adapter.fromWhat("data");
		// setUrl("http://cwapi.gongpingjia.com:8080/v2/activity/list?latitude=32&longitude=118&maxDistance=5000000&token="+user.getToken()+"&userId="+user.getUserId());
//		adapter.addField("area_name", R.id.name);
//		adapter.addField("image", R.id.pic, "default");
		listV.setAdapter(adapter);
		
	}
}

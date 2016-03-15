package com.means.rabbit.adapter;

import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.util.ViewUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import com.means.rabbit.R;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AllItemAdapter extends BaseExpandableListAdapter  {
	
	Context mContext;
	
	LayoutInflater inflater;
	
	JSONArray jsa;
	
	private final String CHILD_KEY = "_child";
	
	public void setData(JSONArray jsa){
		this.jsa = jsa;
		notifyDataSetChanged();
	}
	
	public AllItemAdapter(Context mContext){
		this.mContext = mContext;
		inflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getGroupCount() {
		if (jsa != null) {
			return jsa.length();
		}
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		JSONObject jo =  getGroup(groupPosition);
		if (jo!= null ) {
			JSONArray child_jsa = JSONUtil.getJSONArray(jo, CHILD_KEY);
			if (child_jsa!=null) {
				return child_jsa.length();
			}
		}
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public JSONObject getGroup(int groupPosition) {
		if (jsa!=null) {
			return JSONUtil.getJSONObjectAt(jsa, groupPosition);
		}
		return null;
	}

	@Override
	public JSONObject getChild(int groupPosition, int childPosition) {
		JSONObject jo =  getGroup(groupPosition);
		if (jo!=null) {
			JSONArray child_jsa = JSONUtil.getJSONArray(jo, CHILD_KEY);
			return JSONUtil.getJSONObjectAt(child_jsa, childPosition);
		}
		return null;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		convertView = inflater.inflate(R.layout.item_all_item_group, null);
		
		JSONObject jo = getGroup(groupPosition);
		ViewUtil.bindView(convertView.findViewById(R.id.name), JSONUtil.getString(jo, "name"));
		ViewUtil.bindNetImage((ImageView) convertView.findViewById(R.id.pic), JSONUtil.getString(jo, "pic"), "default");
		// TODO Auto-generated method stub
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		convertView = inflater.inflate(R.layout.item_all_item_child, null);
		
		JSONObject jo = getChild(groupPosition,childPosition);
		ViewUtil.bindView(convertView.findViewById(R.id.name), JSONUtil.getString(jo, "name"));
		// TODO Auto-generated method stub
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}

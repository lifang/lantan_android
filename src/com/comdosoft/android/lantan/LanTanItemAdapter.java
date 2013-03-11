package com.comdosoft.android.lantan;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.comdosoft.android.lantan.pojo.ItemBean;

public class LanTanItemAdapter extends BaseAdapter {
	private List<ItemBean> itemList;
	private int resouce;
	private LayoutInflater inflater;
	private Context context;

	public LanTanItemAdapter(Context context, List<ItemBean> itemList,
			int resouce) {
		super();
		this.context = context;
		this.itemList = itemList;
		this.resouce = resouce;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public LanTanItemAdapter() {
	}

	public int getCount() {
		return itemList.size();
	}

	public Object getItem(int position) {
		return itemList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(resouce, null);
		}
		ItemBean ib = itemList.get(position);
		RelativeLayout layout= new RelativeLayout(context);
		if (ib.getType() == 1) {
			TextView tv = new TextView(context);
			tv.setText(ib.getId());
			tv.setTextColor(Color.rgb(0, 0, 0));
			layout.addView(tv);
		} else {
			ImageView iv = new ImageView(context);
			iv.setBackgroundResource(ib.getId());
			layout.setGravity(Gravity.CENTER);
			layout.addView(iv);
		}
		return layout;
	}
}
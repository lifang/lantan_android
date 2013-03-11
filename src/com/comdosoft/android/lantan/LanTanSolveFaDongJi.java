package com.comdosoft.android.lantan;

import java.util.ArrayList;
import java.util.List;

import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TextView;

import com.comdosoft.android.lantan.pojo.ItemBean;

public class LanTanSolveFaDongJi extends TabActivity {
	private ListView lv1;
	private ListView lv2;
	private ListView lv3;
	private ListView lvT;
	private LanTanItemAdapter adapter1;
	private LanTanItemAdapter adapter2;
	private LanTanItemAdapter adapter3;
	private LanTanItemAdapter adapterT;
	private ImageView iv;
	private TextView tv;
	private TabHost mTabHost;
	private TabWidget tabWidget;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.tab_widget);

		mTabHost = getTabHost();
		lv1 = (ListView) findViewById(R.id.listView1);
		lv2 = (ListView) findViewById(R.id.listView2);
		lv3 = (ListView) findViewById(R.id.listView3);
		lvT = (ListView) findViewById(R.id.listViewT);
		iv=(ImageView)findViewById(R.id.iv_icon);
		tv=(TextView)findViewById(R.id.text_icon);
		iv.setImageResource(R.drawable.fdj_icon);
		tv.setText(getApplication().getResources().getString(R.string.solveFDJ));
		mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("问题重现")
				.setContent(R.id.listView1));
		mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("解决方案")
				.setContent(R.id.listView2));
		mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("效果对比")
				.setContent(R.id.listView3));
		mTabHost.addTab(mTabHost.newTabSpec("tab4").setIndicator("  下单")
				.setContent(R.id.listViewT));
		tabWidget = mTabHost.getTabWidget();
		for (int i = 0; i < tabWidget.getChildCount(); i++) {
			tabWidget.getChildAt(i).getLayoutParams().height = 70;
			tabWidget.getChildAt(i).setBackgroundDrawable(
					getResources().getDrawable(R.drawable.tab_bg));
			TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(
					android.R.id.title);
			tv.setTextColor(Color.rgb(168, 168, 168));
			if (i == 0) {
				tv.setTextColor(Color.rgb(0, 0, 0));
			}
			if (i == 3) {
				tabWidget.getChildAt(i).setBackgroundDrawable(
						getResources().getDrawable(R.drawable.orders));
			}
		}
		loadData();
		mTabHost.setCurrentTab(0);
		lv1.setAdapter(adapter1);
		adapterT=adapter1;
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {

				for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
					TextView tv = (TextView) tabWidget.getChildAt(i)
							.findViewById(android.R.id.title);
					tv.setTextColor(Color.rgb(102, 102, 102));
					if (mTabHost.getCurrentTab() == i) {
						tv.setTextColor(Color.rgb(0, 0, 0));
					}
				}
				if (tabId.equals("tab1")) {
					lv1.setAdapter(adapter1);
					adapterT=adapter1;
				}
				if (tabId.equals("tab2")) {
					lv2.setAdapter(adapter2);
					adapterT=adapter2;
				}
				if (tabId.equals("tab3")) {
					lv3.setAdapter(adapter3);
					adapterT=adapter3;
				}
				if (tabId.equals("tab4")) {
					lvT.setAdapter(adapterT);
					Intent it = new Intent();
					it.setClass(getApplicationContext(),
							LanTanOrderActivity.class);
					startActivity(it);
				}
			}
		});
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		mTabHost.setCurrentTab(0);
		lv1.setAdapter(adapter1);
	}
	
	public void mClose(View v){
		Intent it = new Intent();
		it.setClass(getApplicationContext(),
				LanTanSolve.class);
		startActivity(it);
	}
	
	public void loadData(){
		List<ItemBean>ib1=new ArrayList<ItemBean>();
		ib1.add(new ItemBean(R.drawable.fdjwt,2));
		ib1.add(new ItemBean(R.string.fdjwt,1));
		adapter1 =new LanTanItemAdapter(this,ib1,R.layout.item);
		
		List<ItemBean>ib2=new ArrayList<ItemBean>();
		ib2.add(new ItemBean(R.string.solveyingdui,1));
		ib2.add(new ItemBean(R.drawable.fdjcp1,2));
		ib2.add(new ItemBean(R.string.fdjjj1,1));
		ib2.add(new ItemBean(R.string.fdjjj2,1));
		ib2.add(new ItemBean(R.string.solvetedian,1));
		ib2.add(new ItemBean(R.drawable.fdjcp2,2));
		ib2.add(new ItemBean(R.string.fdjjj3,1));
		ib2.add(new ItemBean(R.string.solveguocheng,1));
		adapter2 =new LanTanItemAdapter(this,ib2,R.layout.item);
		
		List<ItemBean>ib3=new ArrayList<ItemBean>();
		ib3.add(new ItemBean(R.string.solveqmqian,1));
		ib3.add(new ItemBean(R.drawable.fdjclqian,2));
		ib3.add(new ItemBean(R.string.solveqmhou,1));
		ib3.add(new ItemBean(R.drawable.fdjclhou,2));
		ib3.add(new ItemBean(R.string.solveqmduibi,1));
		ib3.add(new ItemBean(R.drawable.fdjdb,2));
		adapter3 =new LanTanItemAdapter(this,ib3,R.layout.item);
	}
}
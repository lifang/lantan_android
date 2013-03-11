package com.comdosoft.android.lantan;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.comdosoft.android.lantan.pojo.ItemBean;

public class LanTanSolveBoLi extends Activity {
	private ListView lv;
	private LanTanItemAdapter adapter1;
	private LanTanItemAdapter adapter2;
	private LanTanItemAdapter adapter3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.solveitem);
		lv=(ListView)findViewById(R.id.listView);
		loadData();
	}
	
	public void openView1(View v){
		lv.setAdapter(adapter1);
	}
	
	public void openView2(View v){
		lv.setAdapter(adapter2);
	}
	
	public void openView3(View v){
		lv.setAdapter(adapter3);
	}
	
	public void loadData(){
		List<ItemBean>ib1=new ArrayList<ItemBean>();
		ib1.add(new ItemBean(R.drawable.blwt,2));
		ib1.add(new ItemBean(R.string.blwt,1));
		adapter1 =new LanTanItemAdapter(this,ib1,R.layout.item);
		
		List<ItemBean>ib2=new ArrayList<ItemBean>();
		ib2.add(new ItemBean(R.string.solveyingdui,1));
		ib2.add(new ItemBean(R.string.bljj1,1));
		ib2.add(new ItemBean(R.string.bljj2,1));
		ib2.add(new ItemBean(R.string.solvetedian,1));
		ib2.add(new ItemBean(R.drawable.bljj,2));
		ib2.add(new ItemBean(R.string.bljj3,1));
		ib2.add(new ItemBean(R.string.solveguocheng,1));
		adapter2 =new LanTanItemAdapter(this,ib2,R.layout.item);
		
		List<ItemBean>ib3=new ArrayList<ItemBean>();
		ib3.add(new ItemBean(R.string.solveqmqian,1));
		ib3.add(new ItemBean(R.drawable.blclqian,2));
		ib3.add(new ItemBean(R.string.solveqmhou,1));
		ib3.add(new ItemBean(R.drawable.blclhou,2));
		ib3.add(new ItemBean(R.string.solveqmduibi,1));
		ib3.add(new ItemBean(R.drawable.bldb,2));
		adapter3 =new LanTanItemAdapter(this,ib3,R.layout.item);
	}
}
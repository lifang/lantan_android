package com.comdosoft.android.lantan;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class LanTanOrderActivity extends Activity {

	private LinearLayout linearlayout[] = new LinearLayout[3];
	public int s=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 强制横屏

		// 全屏设置
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.order);

		Display display = this.getWindowManager().getDefaultDisplay();
		int iwidth = display.getWidth();
		int iheight = display.getHeight();
		LinearLayout ll = (LinearLayout) findViewById(R.id.orders);
		LayoutParams lp = new LayoutParams(iwidth / 5 * 4, iheight / 5 * 4);
		ll.setLayoutParams(lp);
		
		ImageButton ib = (ImageButton)findViewById(R.id.chepaititle);
		android.widget.RelativeLayout.LayoutParams lp3 = new android.widget.RelativeLayout.LayoutParams(iwidth / 5 * 4, iheight / 6);
		ib.setLayoutParams(lp3);
		TextView tv =  (TextView)findViewById(R.id.texttitle);
		tv.setHeight(iheight/6);
		
		for (int i = 0; i < linearlayout.length; i++) {
			linearlayout[i] = new LinearLayout(this);
			ImageButton ibtn[] = new ImageButton[6];
			for (int j = 0; j < ibtn.length; j++) {
				ibtn[j] = new ImageButton(this);
				s++;
				ibtn[j].setId(2000 + s);
				ibtn[j].setOnClickListener(new ImageOnClicks());
				int id;
				try {
					if(s<=17){
					id = (Integer) R.drawable.class.getField("i" + s).get(null);
					ibtn[j].setBackgroundResource(id);
					LayoutParams lp2 = new LayoutParams(iwidth / 5 * 4 /6, iheight / 5 * 4 / 6);
					ibtn[j].setLayoutParams(lp2);
					linearlayout[i].addView(ibtn[j]);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			ll.addView(linearlayout[i]);
		}
	}

	public void ReturnOnclick(View v) {
		this.finish();
	}

	public final class ImageOnClicks implements View.OnClickListener {
		public void onClick(View v) {

			int id = v.getId();
			Intent intent = new Intent(getApplicationContext(),
					LanTanSelectActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("brandid", id);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	}
}
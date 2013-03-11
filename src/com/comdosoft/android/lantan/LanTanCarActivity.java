package com.comdosoft.android.lantan;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.VideoView;

public class LanTanCarActivity extends Activity {

	public Intent intent;
	public final int time = 7000;
	public VideoView vv;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);

		Display display = this.getWindowManager().getDefaultDisplay();
		int iwidth = display.getWidth();
		int iheight = display.getHeight();
		
		VideoView vv = (VideoView)findViewById(R.id.donghua);
		LayoutParams lp = new LayoutParams(iwidth, iheight);
		vv.setLayoutParams(lp);
		
		Uri uri = Uri.parse("android.resource://com.comdosoft.android.lantan/"
				+ R.raw.logo);
		vv.setVideoURI(uri);
		vv.start();
		
		Handler handler = new Handler();
		handler.postDelayed(new RunHandler(), time);
				
	}
	class RunHandler implements Runnable{
		public void run() {
			startActivity(new Intent(LanTanCarActivity.this, LanTanWelcome.class));
			LanTanCarActivity.this.finish();
		}
	}
}

package com.comdosoft.android.lantan;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class LanTanWelcome extends Activity {
	/** Called when the activity is first created. */
	private int index=0;
	private ImageSwitcher is;
	private Timer timer = new Timer();
	private TimerTask task;

	private int[] images = { R.drawable.a1, R.drawable.a2, R.drawable.a3,
			R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7,
			R.drawable.a8 };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		is = (ImageSwitcher) findViewById(R.id.imageswitcher1);
		is.setFactory(new ViewFactory() {
			public View makeView() {
				return new ImageView(LanTanWelcome.this);
			}
		});
		image();
		is.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
				android.R.anim.slide_in_left));
		is.setOutAnimation(AnimationUtils.loadAnimation(
				getApplicationContext(), android.R.anim.slide_out_right));
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		timer = new Timer();
		index=0;
		image();
	}

	public void image(){
		index=0;
		is.setImageResource(images[index]);
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				imageChange();
				super.handleMessage(msg);
			}
		};
		task = new TimerTask() {
			@Override
			public void run() {
				Message message = new Message();
				message.what = 1;
				handler.sendMessage(message);
			}
		};
		timer.schedule(task, 0, 2000);
	}
	
	public void imageChange() {
		index++;
		if (index >= images.length) {
			closes();
		} else {
			is.setImageResource(images[index]);
		}
	}
	public void closes(){
		task.cancel();
		Intent intent = new Intent();
		intent.setClass(this, LanTanHomeActivity.class);
		startActivity(intent);
	}
//	public void onclicks(View v){
//		closes();
//	}
}
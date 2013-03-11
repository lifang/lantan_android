package com.comdosoft.android.lantan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class LanTanIntroduceWorld extends Activity implements AnimationListener{

	private FrameLayout mFrameLayout;
	private View menu;
	private boolean menuOut = false;
	private boolean flag = true;
	private ImageView iv;
	private ImageView iv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// ǿ�ƺ���

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.inworld);
		
		mFrameLayout = (FrameLayout) this.findViewById(R.id.flipper);
		menu = mFrameLayout.findViewById(R.id.menu);
		iv1 = (ImageView) findViewById(R.id.BtnSlide1);
		iv1.setOnClickListener(new ClickListener());
		iv = (ImageView) findViewById(R.id.BtnSlide);
		iv.setOnClickListener(new ClickListener());
	}
	
	public void openChina(View v){
		Intent intent=new Intent();
		intent.setClass(this, LanTanIntroduceChina.class);
		startActivity(intent);
	}
	
	public void openWenTi(View v) {
		
	}

	public void openJieJue(View v) {

	}

	public void openLanTan(View v) {

	}

	public void openXiaDan(View v) {

	}

	public void openGongSi(View v) {

	}

	class ClickListener implements OnClickListener {
		public void onClick(View v) {
			LanTanIntroduceWorld me = LanTanIntroduceWorld.this;
			Context context = me;
			Animation anim;
			if (flag) {
				iv1.layout(iv1.getLeft() + 1060, 0,
						iv1.getLeft() + 1060 + iv1.getWidth(), iv1.getHeight());
				menu.layout(menu.getLeft() + 1100, 0, menu.getLeft() + 1100
						+ menu.getWidth(), menu.getHeight());
				flag = false;
			}
			if (!menuOut) {
				iv1.setVisibility(View.VISIBLE);
				menu.setVisibility(View.VISIBLE);
				anim = AnimationUtils.loadAnimation(context,
						R.anim.push_left_in);
			} else {
				anim = AnimationUtils.loadAnimation(context,
						R.anim.push_right_out);
			}
			anim.setAnimationListener(me);
			menu.startAnimation(anim);
			iv1.startAnimation(anim);
		}
	}

	public void onAnimationStart(Animation animation) {

	}

	public void onAnimationEnd(Animation animation) {
		menuOut = !menuOut;
		if (!menuOut) {
			menu.setVisibility(View.INVISIBLE);
			iv1.setVisibility(View.INVISIBLE);
		}
	}

	public void onAnimationRepeat(Animation animation) {

	}
}
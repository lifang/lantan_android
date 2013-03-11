package com.comdosoft.android.lantan;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class LanTanSolve extends Activity implements AnimationListener{

	private FrameLayout mFrameLayout;
	private View menu;
	private boolean menuOut = false;
	private boolean flag = true;
	private ImageView ivIcon1;
	private ImageView ivIcon2;
	private Intent intent;
	private ImageView iv;
	private TextView tv;
	private ImageView iv2;
	private TextView tv2;
	private ImageView iv3;
	private TextView tv3;
	private ImageView iv4;
	private TextView tv4;
	private ImageView iv5;
	private TextView tv5;
	private ImageView iv6;
	private TextView tv6;
	private ImageView iv7;
	private TextView tv7;
	private int alphaArr[] = { 0, 0, 100, 0, 200, 0, 300, 0, 350, 0, 250, 0,
			150 };
	private ArrayList<View> vArr = new ArrayList<View>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// ǿ�ƺ���

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.solve);
		iv = (ImageView) findViewById(R.id.iView);
		tv = (TextView) findViewById(R.id.tView);
		iv2 = (ImageView) findViewById(R.id.iView2);
		tv2 = (TextView) findViewById(R.id.tView2);
		iv3 = (ImageView) findViewById(R.id.iView3);
		tv3 = (TextView) findViewById(R.id.tView3);
		iv4 = (ImageView) findViewById(R.id.iView4);
		tv4 = (TextView) findViewById(R.id.tView4);
		iv5 = (ImageView) findViewById(R.id.iView5);
		tv5 = (TextView) findViewById(R.id.tView5);
		iv6 = (ImageView) findViewById(R.id.iView6);
		tv6 = (TextView) findViewById(R.id.tView6);
		iv7 = (ImageView) findViewById(R.id.iView7);
		tv7 = (TextView) findViewById(R.id.tView7);
		vArr.add(iv);
		vArr.add(tv);
		vArr.add(iv2);
		vArr.add(tv2);
		vArr.add(iv3);
		vArr.add(tv3);
		vArr.add(iv4);
		vArr.add(tv4);
		vArr.add(iv5);
		vArr.add(tv5);
		vArr.add(iv6);
		vArr.add(tv6);
		vArr.add(iv7);
		vArr.add(tv7);
		for (int i = 0; i < vArr.size(); i += 2) {
			ImageView iv = (ImageView) vArr.get(i);
			TextView tv = (TextView) vArr.get(i + 1);
			setAlpha(iv, alphaArr[i]);
			setAlpha(tv, alphaArr[i]);
			iv.setOnClickListener(new OnClickListenter(i));
		}
		
		mFrameLayout = (FrameLayout) this.findViewById(R.id.flipper);
		menu = mFrameLayout.findViewById(R.id.menu);
		ivIcon1 = (ImageView) findViewById(R.id.BtnSlide1);
		ivIcon1.setOnClickListener(new ClickListener());
		ivIcon2 = (ImageView) findViewById(R.id.BtnSlide);
		ivIcon2.setOnClickListener(new ClickListener());
	}

	public void openWenTi(View v) {
		intent = new Intent(this, LanTanSevenActivity.class);
		startActivity(intent);
	}

	public void openJieJue(View v) {
		intent = new Intent(this, LanTanSolve.class);
		startActivity(intent);
	}

	public void openLanTan(View v) {
		intent = new Intent(this, LanTanWelcome.class);
		startActivity(intent);
	}

	public void openXiaDan(View v) {
		intent = new Intent(this, LanTanOrderActivity.class);
		startActivity(intent);
	}

	public void openGongSi(View v) {
		intent = new Intent(this, LanTanIntroduceWorld.class);
		startActivity(intent);
	}

	class ClickListener implements OnClickListener {
		public void onClick(View v) {
			LanTanSolve me = LanTanSolve.this;
			Context context = me;
			Animation anim;
			if (flag) {
				ivIcon1.layout(ivIcon1.getLeft() + 1060, 0,
						ivIcon1.getLeft() + 1060 + ivIcon1.getWidth(), ivIcon1.getHeight());
				menu.layout(menu.getLeft() + 1100, 0, menu.getLeft() + 1100
						+ menu.getWidth(), menu.getHeight());
				flag = false;
			}
			if (!menuOut) {
				ivIcon1.setVisibility(View.VISIBLE);
				menu.setVisibility(View.VISIBLE);
				anim = AnimationUtils.loadAnimation(context,
						R.anim.push_left_in);
			} else {
				anim = AnimationUtils.loadAnimation(context,
						R.anim.push_right_out);
			}
			anim.setAnimationListener(me);
			menu.startAnimation(anim);
			ivIcon1.startAnimation(anim);
		}
	}

	public void onAnimationStart(Animation animation) {

	}

	public void onAnimationEnd(Animation animation) {
		menuOut = !menuOut;
		if (!menuOut) {
			menu.setVisibility(View.INVISIBLE);
			ivIcon1.setVisibility(View.INVISIBLE);
		}
	}

	public void onAnimationRepeat(Animation animation) {

	}
	
	private final class OnClickListenter implements OnClickListener {
		private int type;

		public OnClickListenter(int type) {
			super();
			this.type = type;
		}

		public void onClick(View v) {
			switch (type) {
			case 0:
				lunTai();
				break;
			case 2:
				boLi();
				break;
			case 4:
				chongShi();
				break;
			case 6:
				faDongJi();
				break;
			case 8:
				ziWaiXian();
				break;
			case 10:
				suanYu();
				break;
			case 12:
				huaHen();
				break;
			}
		}
	}

	public void setAlpha(View v, int data) {
		AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.1f, 1.0f);
		alphaAnimation1.setDuration(1000);
		alphaAnimation1.setRepeatCount(Animation.INFINITE);
		alphaAnimation1.setRepeatMode(Animation.REVERSE);
		alphaAnimation1.setStartOffset(data);
		v.setAnimation(alphaAnimation1);
		alphaAnimation1.start();
	}

	public void lunTai() {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), LanTanSolveLunTai.class);
		startActivity(intent);
	}

	public void boLi() {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), LanTanSolveBL.class);
		startActivity(intent);
	}

	public void chongShi() {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), LanTanSolveChongShi.class);
		startActivity(intent);
	}

	public void faDongJi() {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), LanTanSolveFaDongJi.class);
		startActivity(intent);
	}

	public void ziWaiXian() {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), LanTanSolveZiWaiXian.class);
		startActivity(intent);
	}

	public void suanYu() {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), LanTanSolveSuanYu.class);
		startActivity(intent);
	}

	public void huaHen() {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), LanTanSolveHuaHen.class);
		startActivity(intent);
	}
}
package com.comdosoft.android.lantan;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class LanTanLight extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.light);
		ImageView iv=(ImageView)findViewById(R.id.iView);
		TextView tv=(TextView)findViewById(R.id.tView);
		tv.setText("xxxx");
		iv.setImageResource(R.drawable.light_spot);
		AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.1f, 1.0f);  
		alphaAnimation1.setDuration(3000);  
		alphaAnimation1.setRepeatCount(Animation.INFINITE);  
		alphaAnimation1.setRepeatMode(Animation.REVERSE);  
		iv.setAnimation(alphaAnimation1);  
		tv.setAnimation(alphaAnimation1);
		alphaAnimation1.start();  
	}
}
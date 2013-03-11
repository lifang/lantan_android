package com.comdosoft.android.lantan;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class LanTanSevenActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// ǿ�ƺ���

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.seven);

		
		
		Display display = this.getWindowManager().getDefaultDisplay();
		int width = display.getWidth();

		Activitylayout(width);
	}

	private void Activitylayout(int width) {
		TextView tv = (TextView) findViewById(R.id.shuoming);
		tv.setWidth((int) (width / 8));
		tv.setTextSize(10f);

		Button btn1 = (Button) findViewById(R.id.Acidrain);
		btn1.setWidth((width / 8));
		btn1.setTextSize(14f);
		btn1.setBackgroundColor(Color.TRANSPARENT);// ����͸��

		Button btn2 = (Button) findViewById(R.id.insectcorpse);
		btn2.setWidth((int) (width / 8));
		btn2.setTextSize(14f);
		btn2.setBackgroundColor(Color.TRANSPARENT);
		Button btn3 = (Button) findViewById(R.id.tirerims);
		btn3.setWidth((int) (width / 8));
		btn3.setTextSize(14f);
		btn3.setBackgroundColor(Color.TRANSPARENT);
		Button btn4 = (Button) findViewById(R.id.UV);
		btn4.setWidth((int) (width / 8));
		btn4.setTextSize(14f);
		btn4.setBackgroundColor(Color.TRANSPARENT);
		Button btn5 = (Button) findViewById(R.id.frontglass);
		btn5.setWidth((int) (width / 8));
		btn5.setTextSize(14f);
		btn5.setBackgroundColor(Color.TRANSPARENT);
		Button btn6 = (Button) findViewById(R.id.Scratches);
		btn6.setWidth((int) (width / 8));
		btn6.setTextSize(14f);
		btn6.setBackgroundColor(Color.TRANSPARENT);
		Button btn7 = (Button) findViewById(R.id.Engine);
		btn7.setWidth((int) (width / 8));
		btn7.setTextSize(14f);
		btn7.setBackgroundColor(Color.TRANSPARENT);
	}

	public void Acidrainonclicks(View v) {

		Bundle bundle = new Bundle();
		bundle.putInt("id", v.getId());

		Intent intent = new Intent(this, LanTanVideoActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);

	}
}
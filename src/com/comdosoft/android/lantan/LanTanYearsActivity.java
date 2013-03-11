package com.comdosoft.android.lantan;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class LanTanYearsActivity extends Activity {

	private DatePicker datePicker;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// ǿ�ƺ���

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.years);

		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);

		Display display = this.getWindowManager().getDefaultDisplay();
		int iwidth = display.getWidth();
		int iheight = display.getHeight();
		LinearLayout ll = (LinearLayout) findViewById(R.id.year);
		LayoutParams lp = new LayoutParams(iwidth / 5 * 4, iheight / 10 * 7);
		ll.setLayoutParams(lp);

		ImageButton ib = (ImageButton) findViewById(R.id.yeartitle);
		android.widget.RelativeLayout.LayoutParams lp2 = new android.widget.RelativeLayout.LayoutParams(
				iwidth / 5 * 4, iheight / 6);
		ib.setLayoutParams(lp2);

		LinearLayout btnleft = (LinearLayout)findViewById(R.id.btnleft);
		LayoutParams btnlp = new LayoutParams(iwidth/ 5 * 2, iheight/10);
		btnleft.setLayoutParams(btnlp);
		LinearLayout btnright = (LinearLayout)findViewById(R.id.btnright);
		LayoutParams btnlp2 = new LayoutParams(iwidth/ 5 * 2, iheight/10);
		btnright.setLayoutParams(btnlp2);

		LinearLayout datepicker = (LinearLayout)findViewById(R.id.datepicker);
		LayoutParams lp3= new LayoutParams(iwidth / 5 * 4 , iheight / 30 * 16);
		datepicker.setLayoutParams(lp3);
		
		datePicker = (DatePicker) findViewById(R.id.datePicker1);
		
        Calendar calendar=Calendar.getInstance();
        int yearof=calendar.get(Calendar.YEAR);
        int monthOfYear=calendar.get(Calendar.MONTH);
        int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
		datePicker.init(yearof,monthOfYear,dayOfMonth, null);
		
		
		TextView tv = (TextView) findViewById(R.id.yeartexttitle);
		tv.setHeight(iheight / 6);

		Button btnreturn = (Button) findViewById(R.id.yearbtnreturn);
		btnreturn.setHeight(iheight / 10);
		Button next = (Button) findViewById(R.id.yearbtnnext);
		next.setHeight(iheight / 10);

	}

	public void btnreturn(View v) {
		this.finish();
	}

	public void btnnext(View v) {
		int yue = datePicker.getMonth() + 1;
		
		String buy_year = datePicker.getYear()+"-"+yue+"-"+ datePicker.getDayOfMonth();
		
		Bundle get = getIntent().getExtras();
		ArrayList<String> save = get.getStringArrayList("usesave");
		Log.i("car", save.get(0)+"--"+save.get(1));
		save.add(2,buy_year);
		Intent intent = new Intent(this, LanTanPhotoActivity.class);
		Bundle bundle = new Bundle();
		bundle.putStringArrayList("save", save);
		intent.putExtras(bundle);
		startActivity(intent);
	}
}

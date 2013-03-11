package com.comdosoft.android.lantan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class LanTanNewsActivity extends Activity {

	private double jing, wei;
	private String user_area;
	private EditText carid;
	private EditText name;
	private EditText phone;
	private EditText birthdays;
	private EditText emails;
	private ProgressDialog dialog;
	boolean statics;
	private ConnectivityManager mCM; 

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// ǿ�ƺ���

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.news);

		Display display = this.getWindowManager().getDefaultDisplay();
		int iwidth = display.getWidth();
		int iheight = display.getHeight();
		LinearLayout ll = (LinearLayout) findViewById(R.id.news);
		LayoutParams lp = new LayoutParams(iwidth / 5 * 4, iheight / 10 * 7);
		ll.setLayoutParams(lp);

		ImageButton ib = (ImageButton) findViewById(R.id.newstitle);
		android.widget.RelativeLayout.LayoutParams lp2 = new android.widget.RelativeLayout.LayoutParams(
				iwidth / 5 * 4, iheight / 6);
		ib.setLayoutParams(lp2);

		TextView tv = (TextView) findViewById(R.id.newstexttitle);
		tv.setHeight(iheight / 6);

		LinearLayout btndown = (LinearLayout) findViewById(R.id.btndown1);
		LayoutParams btnlp = new LayoutParams(iwidth / 5 * 2, iheight / 10);
		btndown.setLayoutParams(btnlp);
		LinearLayout btndown2 = (LinearLayout) findViewById(R.id.btndown2);
		LayoutParams btnlp2 = new LayoutParams(iwidth / 5 * 2, iheight / 10);
		btndown2.setLayoutParams(btnlp2);

		Button btnreturn = (Button) findViewById(R.id.newsbtnreturn);
		btnreturn.setHeight(iheight / 10);
		Button dingdan = (Button) findViewById(R.id.newsbtnnext);
		dingdan.setHeight(iheight / 10);

		carid = (EditText) findViewById(R.id.carid);
		carid.setWidth(iwidth / 100 * 30);

		name = (EditText) findViewById(R.id.name);
		name.setWidth(iwidth / 100 * 30);

		phone = (EditText) findViewById(R.id.phone);
		phone.setWidth(iwidth / 100 * 30);
		birthdays = (EditText) findViewById(R.id.birthday);
		birthdays.setWidth(iwidth / 100 * 30);

		emails = (EditText) findViewById(R.id.emails);
		emails.setWidth(iwidth / 100 * 30);

	}

	public void btnreturn(View v) {
		this.finish();
	}

	public void btnnext(View v) {

		Builder builder = new AlertDialog.Builder(LanTanNewsActivity.this);
		builder.setIcon(R.drawable.alert_dialog_icon);
		builder.setTitle("友情提示！");
		builder.setMessage("该操作需要打开GPS与 移动网络!会产生流量");
		builder.setPositiveButton("开启GPS",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						initGPS();
					}
				})
				.setNeutralButton("开启GPRS移动网络",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						 mCM = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE); 
					        gprsEnabled(true);
					}
				})
				.setNegativeButton("提交订单",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						if (sendOrder()) {
							statics = true;
						} else {
							statics = false;
						}
					}
				}).show();
	}

	class RunHandler implements Runnable {
		public void run() {
			dialog.dismiss();
			Builder builder = new AlertDialog.Builder(LanTanNewsActivity.this);
			if(statics){
				builder.setIcon(R.drawable.alert_dialog_icon);
				builder.setTitle("友情提示！");
				builder.setMessage("下单成功!欢迎下次使用");
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								Intent intent = new Intent(LanTanNewsActivity.this,LanTanHomeActivity.class);
								startActivity(intent);
							}
						});
				builder.show();
			}else{
				builder.setIcon(R.drawable.alert_dialog_icon);
				builder.setTitle("友情提示！");
				builder.setMessage("下订单失败,请检查是否开启GPS与移动网络！");
				builder.setPositiveButton("确定",null);
				builder.show();
			}
		}
	}

	 private void initGPS(){ 
	        LocationManager locationManager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE); 
	        //判断GPS模块是否开启，如果没有则开启 
	        if(!locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)){ 
	        	Toast.makeText(this, "GPS未开启,请起动GPS!", Toast.LENGTH_SHORT).show(); 
	         //转到手机设置界面，用户设置GPS
	         Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS); 
	         startActivityForResult(intent,0); //设置完成后返回到原来的界面
	        } 
	        else { 
	          //弹出对话框
	        	Toast.makeText(LanTanNewsActivity.this, "GPS准备就绪", 1).show();
	        } 
	   } 
	
	public boolean sendOrder() {
		dialog = new ProgressDialog(LanTanNewsActivity.this);
		dialog.setTitle("处理中..");
		dialog.setMessage("连接服务器中,请稍候...");
		dialog.show();

		Handler handler = new Handler();
		handler.postDelayed(new RunHandler(), 5000);

		getlatitude();

		Bundle bundle = getIntent().getExtras();
		ArrayList<String> save = bundle.getStringArrayList("saves");
		String car_type = save.get(0);
		String small_type = save.get(1);
		String buy_year = save.get(2);
		String car_num = carid.getText().toString();
		String user_name = name.getText().toString();
		String user_phone = phone.getText().toString();
		String birthday = birthdays.getText().toString();
		String email = emails.getText().toString();

		Log.i("car", car_type + "--" + small_type + "--" + buy_year);
		Log.i("car", car_num + "--" + user_name + "--" + user_phone);
		Log.i("car", birthday + "--" + email);

		String path = "http://car.gankao.co/car_records/receive_info";
		Map<String, String> params = new HashMap<String, String>();
		params.put("car_type", car_type);
		params.put("user_area", user_area);
		params.put("email", email);
		params.put("user_name", user_name);
		params.put("user_phone", user_phone);
		params.put("car_num,", car_num);
		params.put("small_type", small_type);
		params.put("buy_year", buy_year);
		params.put("birthday", birthday);
		try {
			return sendGETRequest(path, params, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean sendGETRequest(String path, Map<String, String> params,
			String encoding) throws MalformedURLException, IOException {

		StringBuilder url = new StringBuilder(path);
		url.append("?");
		for (Map.Entry<String, String> entry : params.entrySet()) {
			url.append(entry.getKey()).append("=");
			url.append(URLEncoder.encode(entry.getValue(), encoding));
			url.append("&");
		}
		url.deleteCharAt(url.length() - 1);
		HttpURLConnection conn = (HttpURLConnection) new URL(url.toString())
				.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == 200) {
			return true;
		}
		return false;
	}

	public void getlatitude() {

		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			Location location = locationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (location != null) {
				jing = location.getLatitude();
				wei = location.getLongitude();
				Log.v("car", jing + "");
				Log.v("car", wei + "");
			}
		} else {
			LocationListener locationListener = new LocationListener() {

				// Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
				@Override
				public void onStatusChanged(String provider, int status,
						Bundle extras) {

				}

				// Provider被enable时触发此函数，比如GPS被打开
				@Override
				public void onProviderEnabled(String provider) {

				}

				// Provider被disable时触发此函数，比如GPS被关闭
				@Override
				public void onProviderDisabled(String provider) {

				}

				// 当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
				@Override
				public void onLocationChanged(Location location) {
					if (location != null) {
						Log.e("car",
								"Location changed : Lat: "
										+ location.getLatitude() + " Lng: "
										+ location.getLongitude());
					}
				}
			};
			locationManager
					.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
							1000, 0, locationListener);
			Location location = locationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if (location != null) {
				jing = location.getLatitude();
				wei = location.getLongitude();
			}
		}

		user_area = GetAddr(wei, jing);

	}

	public static String GetAddr(double latitude, double longitude) {
		String addr = "";
		// 也可以是http://maps.google.cn/maps/geo?output=csv&key=abcdef&q=%s,%s，不过解析出来的是英文地址
		// 密钥可以随便写一个key=abc
		// output=csv,也可以是xml或json，不过使用csv返回的数据最简洁方便解析
		String url = "http://ditu.google.cn/maps/geo?output=csv&key=AIzaSyAKPii6BUfqkcnjUfX5yo18nCIcvCgQXSA&q="
				+ longitude + "," + latitude;
		URL myURL = null;
		URLConnection httpsConn = null;
		try {
			myURL = new URL(url);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		try {
			httpsConn = (URLConnection) myURL.openConnection();
			if (httpsConn != null) {
				InputStreamReader insr = new InputStreamReader(
						httpsConn.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(insr);
				String data = null;
				if ((data = br.readLine()) != null) {
					Log.i("car", data + "--");
					String[] retList = data.split(",");
					if (retList.length > 2 && ("200".equals(retList[0]))) {
						addr = retList[2];
						addr = addr.replace("\"", "");
					} else {
						addr = "";
					}
				}
				insr.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return addr;
	}
	private boolean gprsEnabled(boolean bEnable) 
    { 
        boolean isOpen = gprsIsOpenMethod("getMobileDataEnabled"); 
        if(isOpen == !bEnable) 
        { 
        	setGprsEnabled("setMobileDataEnabled", bEnable); 
        } 
        Toast.makeText(this, "已打开GPRS移动网络", 0).show();
        return isOpen;   
    } 
     
    //检测GPRS是否打开 
    private boolean gprsIsOpenMethod(String methodName) 
    { 
        Class cmClass       = mCM.getClass(); 
        Class[] argClasses  = null; 
        Object[] argObject  = null; 
         
        Boolean isOpen = false; 
        
        try 
        { 
            Method method = cmClass.getMethod(methodName, argClasses); 
 
            isOpen = (Boolean) method.invoke(mCM, argObject); 
            
        } catch (Exception e) 
        { 
            e.printStackTrace(); 
        } 
 
        return isOpen; 
    } 
     
    //开启/关闭GPRS 
    private void setGprsEnabled(String methodName, boolean isEnable) 
    { 
        Class cmClass       = mCM.getClass(); 
        Class[] argClasses  = new Class[1]; 
        argClasses[0]       = boolean.class; 
         
        try 
        { 
            Method method = cmClass.getMethod(methodName, argClasses); 
            method.invoke(mCM, isEnable); 
        } catch (Exception e) 
        { 
            e.printStackTrace(); 
        } 
    } 
}

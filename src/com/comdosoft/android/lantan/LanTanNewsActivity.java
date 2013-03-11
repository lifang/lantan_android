package com.comdosoft.android.lantan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.utils.URLEncodedUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class LanTanNewsActivity extends Activity {

	private double jing, wei;
	private String user_area;
	private EditText carid;
	private EditText name;
	private EditText phone;
	private EditText birthdays;
	private EditText emails;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// ǿ�ƺ���
		// ȫ������
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
		ProgressDialog dialog = new ProgressDialog(LanTanNewsActivity.this);
		dialog.setTitle("处理中..");
		dialog.setMessage("连接服务器中,请稍候...");
		dialog.show();

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
		Log.i("car", birthday + "--" + emails);
		
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
//			sendPOSTRequest(path,params,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean sendPOSTRequest(String path,Map<String, String> params,String encoding) throws MalformedURLException, IOException{
		
		StringBuilder data = new StringBuilder();
		if (params!= null && params.isEmpty()) {
			for (Map.Entry<String, String> entry: params.entrySet()) {
				data.append(entry.getKey()).append("=");
				data.append(URLEncoder.encode(entry.getValue(),encoding));
				data.append("&");
			}
			data.deleteCharAt(data.length()-1);
		}
		byte[] entity = data.toString().getBytes();
		HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entity);
		if(conn.getResponseCode()==200){
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
}

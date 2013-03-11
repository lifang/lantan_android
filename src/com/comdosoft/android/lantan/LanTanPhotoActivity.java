package com.comdosoft.android.lantan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class LanTanPhotoActivity extends Activity {

	private static int id;
	public static int number;
	public Uri uri;
	public static ImageView qian, hou, zuo, you;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.photo);

		Display display = this.getWindowManager().getDefaultDisplay();
		int iwidth = display.getWidth();
		int iheight = display.getHeight();

		LinearLayout ll = (LinearLayout) findViewById(R.id.photo);
		LayoutParams lp = new LayoutParams(iwidth / 5 * 4, iheight / 10 * 7);
		ll.setLayoutParams(lp);

		ImageButton ib = (ImageButton) findViewById(R.id.phototitle);
		android.widget.RelativeLayout.LayoutParams lp2 = new android.widget.RelativeLayout.LayoutParams(
				iwidth / 5 * 4, iheight / 6);
		ib.setLayoutParams(lp2);

		TextView tv = (TextView) findViewById(R.id.phototexttitle);
		tv.setHeight(iheight / 6);

		LinearLayout btnleft = (LinearLayout) findViewById(R.id.photobtnleft);
		LayoutParams btnlp = new LayoutParams(iwidth / 5 * 2, iheight / 10);
		btnleft.setLayoutParams(btnlp);
		LinearLayout btnright = (LinearLayout) findViewById(R.id.photobtnright);
		LayoutParams btnlp2 = new LayoutParams(iwidth / 5 * 2, iheight / 10);
		btnright.setLayoutParams(btnlp2);

		LinearLayout datepicker = (LinearLayout) findViewById(R.id.photodatepicker);
		LayoutParams lp3 = new LayoutParams(iwidth / 5 * 4, iheight / 30 * 8);
		datepicker.setLayoutParams(lp3);
		LinearLayout datepicker2 = (LinearLayout) findViewById(R.id.photodatepicker2);
		LayoutParams lp4 = new LayoutParams(iwidth / 5 * 4, iheight / 30 * 8);
		datepicker2.setLayoutParams(lp4);

		Button btnreturn = (Button) findViewById(R.id.photobtnreturn);
		btnreturn.setHeight(iheight / 10);
		Button next = (Button) findViewById(R.id.photobtnnext);
		next.setHeight(iheight / 10);

		qian = (ImageView) findViewById(R.id.qian);
		LayoutParams qianlp = new LayoutParams(iwidth / 100 * 35,
				iheight / 100 * 25);
		qian.setLayoutParams(qianlp);

		hou = (ImageView) findViewById(R.id.hou);
		LayoutParams houlp = new LayoutParams(iwidth / 100 * 35,
				iheight / 100 * 25);
		hou.setLayoutParams(houlp);

		zuo = (ImageView) findViewById(R.id.zuo);
		LayoutParams zuolp = new LayoutParams(iwidth / 100 * 35,
				iheight / 100 * 25);
		zuo.setLayoutParams(zuolp);

		you = (ImageView) findViewById(R.id.you);
		LayoutParams youlp = new LayoutParams(iwidth / 100 * 35,
				iheight / 100 * 25);
		you.setLayoutParams(youlp);

		String sdqian = "/sdcard/myImage/" + R.id.qian + ".jpg";
		String sdhou = "/sdcard/myImage/" + R.id.hou + ".jpg";
		String sdzuo = "/sdcard/myImage/" + R.id.zuo + ".jpg";
		String sdyou = "/sdcard/myImage/" + R.id.you + ".jpg";

		if (!getFilePath(sdqian).exists()) {
			qian.setImageResource(R.drawable.cheqian);
		} else {
			uri = Uri.parse("/sdcard/myImage/" + R.id.qian + ".jpg");
			qian.setImageURI(uri);
			getActicity();
		}
		if (!getFilePath(sdhou).exists()) {
			hou.setImageResource(R.drawable.chehou);
		} else {
			uri = Uri.parse("/sdcard/myImage/" + R.id.hou + ".jpg");
			hou.setImageURI(uri);
			getActicity();
		}
		if (!getFilePath(sdzuo).exists()) {
			zuo.setImageResource(R.drawable.chezuo);
		} else {
			uri = Uri.parse("/sdcard/myImage/" + R.id.zuo + ".jpg");
			zuo.setImageURI(uri);
			getActicity();
		}
		if (!getFilePath(sdyou).exists()) {
			you.setImageResource(R.drawable.cheyou);
		} else {
			uri = Uri.parse("/sdcard/myImage/" + R.id.you + ".jpg");
			you.setImageURI(uri);
			getActicity();
		}
	}

	public void photoonclick(View v) {
		id = v.getId();
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {

			String sdStatus = Environment.getExternalStorageState();
			if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
				Toast.makeText(LanTanPhotoActivity.this, "未检测到SD卡", 0).show();
				return;
			}

			Bundle bundle = data.getExtras();

			Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
			FileOutputStream b = null;
			File file = new File("/sdcard/myImage/");
			file.mkdirs();// 创建文件夹
			String name = id + ".jpg";
			String fileName = "/sdcard/myImage/" + name;

			try {
				b = new FileOutputStream(fileName);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件

				number++;

				switch (id) {
				case R.id.qian:
					uri = Uri.parse("/sdcard/myImage/" + R.id.qian + ".jpg");
					qian.setImageURI(uri);
					break;
				case R.id.hou:
					uri = Uri.parse("/sdcard/myImage/" + R.id.hou + ".jpg");
					hou.setImageURI(uri);
					break;
				case R.id.zuo:
					uri = Uri.parse("/sdcard/myImage/" + R.id.zuo + ".jpg");
					zuo.setImageURI(uri);
					break;
				case R.id.you:
					uri = Uri.parse("/sdcard/myImage/" + R.id.you + ".jpg");
					you.setImageURI(uri);
					break;

				default:
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					b.flush();
					b.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public File getFilePath(String filepath) {
		File file = new File(filepath);
		return file;
	}

	public void getActicity(){
		Intent intent =new Intent();
		intent.setClass(getApplicationContext(), LanTanPhotoActivity.class);
		startActivity(intent);
	}
	
	public void btnreturn(View v) {
		this.finish();
	}

	public void btnnext(View v) {
		Intent intent = new Intent(this, LanTanNewsActivity.class);
		Bundle get = getIntent().getExtras();
		ArrayList<String> save = get.getStringArrayList("save");
		Bundle bundle = new Bundle();
		bundle.putStringArrayList("saves", save);
		intent.putExtras(bundle);
		startActivity(intent);
	}
}

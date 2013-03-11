package com.comdosoft.android.lantan;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class LanTanVideoActivity extends Activity {

	private VideoView videoview;
	private int position;
	private Uri uri;
	private int id;
	private Intent intent;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.video);

		videoview = (VideoView) this.findViewById(R.id.vv);
		MediaController controller = new MediaController(this);
		this.videoview.setMediaController(controller);

		intent = getIntent();
		Bundle bundle = intent.getExtras();
		id = bundle.getInt("id");

		playVideo(id);

		videoview.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				
				Builder builder = new AlertDialog.Builder(
						LanTanVideoActivity.this);
				builder.setIcon(R.drawable.alert_dialog_icon);
				builder.setTitle("友情提示！");
				builder.setMessage("视频结束,您想重播？还是找解决方案？");
				builder.setPositiveButton("重播",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								videoview.stopPlayback();
								playVideo(id);
							}
						});
				builder.setNegativeButton("解决方案",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								videoview.stopPlayback();
								videoview.clearFocus();
								selectvoid(id);
							}
						});
					builder.show();
			}
		});
	}

	public void playVideo(int id) {
		switch (id) {
		case R.id.Acidrain:
			uri = Uri.parse("android.resource://com.comdosoft.android.lantan/"
					+ R.raw.rain);
			videoview.setVideoURI(uri);
			videoview.start();
			break;
		case R.id.insectcorpse:
			uri = Uri.parse("android.resource://com.comdosoft.android.lantan/"
					+ R.raw.insectcorpse);
			videoview.setVideoURI(uri);
			videoview.start();
			break;
		case R.id.tirerims:
			uri = Uri.parse("android.resource://com.comdosoft.android.lantan/"
					+ R.raw.tirerims);
			videoview.setVideoURI(uri);
			videoview.start();
			break;
		case R.id.UV:
			uri = Uri.parse("android.resource://com.comdosoft.android.lantan/"
					+ R.raw.uv);
			videoview.setVideoURI(uri);
			videoview.start();
			break;
		case R.id.frontglass:
			uri = Uri.parse("android.resource://com.comdosoft.android.lantan/"
					+ R.raw.frontglass);
			videoview.setVideoURI(uri);
			videoview.start();
			break;
		case R.id.Scratches:
			uri = Uri.parse("android.resource://com.comdosoft.android.lantan/"
					+ R.raw.scratches);
			videoview.setVideoURI(uri);
			videoview.start();
			break;
		case R.id.Engine:
			uri = Uri.parse("android.resource://com.comdosoft.android.lantan/"
					+ R.raw.engine);
			videoview.setVideoURI(uri);
			videoview.start();
			break;
		}
	}

	public void selectvoid(int id){
		switch (id) {
		case R.id.Acidrain:
			intent = new Intent(this, LanTanSolveSuanYu.class);
			startActivity(intent);
			break;
		case R.id.insectcorpse:
			intent = new Intent(this, LanTanSolveChongShi.class);
			startActivity(intent);
			break;
		case R.id.tirerims:
			intent = new Intent(this, LanTanSolveLunTai.class);
			startActivity(intent);
			break;
		case R.id.UV:
			intent = new Intent(this, LanTanSolveZiWaiXian.class);
			startActivity(intent);
			break;
		case R.id.frontglass:
			Intent intent = new Intent(this, LanTanSolveBoLi.class);
			startActivity(intent);
			break;
		case R.id.Scratches:
			intent = new Intent(this, LanTanSolveHuaHen.class);
			startActivity(intent);
			break;
		case R.id.Engine:
			intent = new Intent(this, LanTanSolveFaDongJi.class);
			startActivity(intent);
			break;
		}
	}

	protected void onPause() {
		if (videoview.isPlaying()) {
			position = videoview.getCurrentPosition();
			videoview.stopPlayback();
		}
		super.onPause();
	}

	public void videoclose(View v) {
		videoview.stopPlayback();
		this.finish();
	}

}

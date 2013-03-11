package com.comdosoft.android.lantan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class LanTanHomeActivity extends Activity implements AnimationListener {

	private Intent intent;
	private String[] strArr = { "如何选择放心的美容店？", "新车如何保养车漆？",
			"新车需要做抛光处理吗？是否会伤害漆面？", "新车需要做镀膜吗？还是要过段时间再做镀膜？", "镀膜之后需要注意哪些事项？",
			"汽车美容除了漆面镀膜之外，还有其他什么服务项目？" };
	private String[] xtsArr = {
			"    如何选择放心的美容店？\n    一个专业的汽车美容店，通过看其洗车流程就能初步判断了！正规的品牌洗车服务店，主要看其店面规模，服务人员的专业素养，是否有专业的洗车、擦车工具，上下海绵是否区分？内外毛巾是否区分？洗内饰毛巾是否有消毒？更重要的是建议您到他们的仓库，看看他们用了什么洗车产品，通过这几样基本可以选择到一家放心的美容店！",
			"    新车如何保养车漆?\n    漆面养护最首要的是洗车！好的洗车方法能大大保护漆面的光泽！洗车香波应该是中性的，没有酸碱度，碱性很强的洗洁精，洗衣粉，虽然能把车洗干净，但是长期下去，漆面就会变得暗淡无光。\n\n    其次必须采用一些专有的漆面养护产品！通常的养护方法有打蜡、封釉、镀膜等，不同的方法价格不等，持续时间不等，效果不等！坚持养护，和同期的不做养护的车对比，明显感觉到光泽度的差异！",
			"    新车需要做抛光处理吗？是否会伤害漆面？\n    如果是新车做抛光，肯定是不好的。漆面修复，分成三种工艺，研磨、抛光和还原。研磨和抛光，切割能力较强，可以针对车漆进行修复。还原，只能在氧化层上作用，根本不能触动车漆。所以，针对新车漆表面去污，去氧化层（去污），都可以用还原工艺来操作。只去除氧化层，不伤车漆。",
			"    新车需要做镀膜吗？还是要过段时间再做镀膜？\n    其实，这个说法，就像皮肤美容对一个20多岁的姑娘来说，“你还年轻不需要保养”一样。等到容颜老去，再想找回逝去的青春，那就难了。也有些人，就希望接受岁月的雕刻，在不同的年龄段展现不同的风貌，但是这也需要保养，甚至说比普通的保养要求更高。因为，我们知道任何人都是追求美的，追求美丽是没有错的。所以，那些劝人不去保养，耽误别人的青春的人是不道德的。至于美容保养做与不做，还是交给车主自己去权衡，比较合适.\n    新车做镀膜效果是最好的，新车做就是美容，旧车做就是整容。",
			"    镀膜之后需要注意哪些事项？\n    选择晴好的天气进行镀膜施工，有利于镀膜效果的充分保持！\n    镀膜之后5到7天之内避免洗车，镀膜剂跟漆面的彻底融合需要一个时间！\n    镀膜效果可以保持1年左右，期间定期过来做镀膜的后期保养！\n    镀膜之后的车辆一定要选择正规的洗车点进行洗车！",
			"    汽车美容除了漆面镀膜之外，还有其他什么服务项目？\n    汽车美容其实是一个很大的项目，其中包括了若干的服务内容，车漆美容为其中一大项，关系到车的外观；其他的项目诸如有：日常的洗车护理；内饰清洗、桑拿；真皮座椅的镀膜保养；车内塑料件的清洗及保养；引擎舱的清洁保养；轮毂清洁、除锈保养；全车玻璃的清洁保养等等！" };
	private int index = 0;
	private TextView tv;
	private TextView tv1;
	private TextView downPage;
	private ImageView iv;

	private FrameLayout mFrameLayout;
	private View menu;
	private boolean menuOut = false;
	private boolean flag = true;
	private ImageView ivIcon1;
	private ImageView ivIcon2;
	private boolean downPageFlag = false;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// ǿ�ƺ���

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.home);

		tv = (TextView) findViewById(R.id.tv1);
		tv1 = (TextView) findViewById(R.id.tv4);
		downPage = (TextView) findViewById(R.id.tv3);
		iv = (ImageView) findViewById(R.id.close);
		tv.setText(strArr[index]);

		mFrameLayout = (FrameLayout) this.findViewById(R.id.flipper);
		menu = mFrameLayout.findViewById(R.id.menu);
		ivIcon1 = (ImageView) findViewById(R.id.BtnSlide1);
		ivIcon1.setOnClickListener(new ClickListener());
		ivIcon2 = (ImageView) findViewById(R.id.BtnSlide);
		ivIcon2.setOnClickListener(new ClickListener());

		Display display = this.getWindowManager().getDefaultDisplay();
		int width = display.getWidth();// ��ȡ������
		int height = display.getHeight();

		ImageButton cjwt = (ImageButton) findViewById(R.id.CJWT);
		LayoutParams lp = (LayoutParams) cjwt.getLayoutParams();
		lp.width = (int) (width * 0.3);
		lp.height = (int) (height * 0.3);
		cjwt.setLayoutParams(lp);

		ImageButton jjzd = (ImageButton) findViewById(R.id.JJZD);
		LayoutParams lp2 = (LayoutParams) jjzd.getLayoutParams();
		lp2.width = (int) (width * 0.3);
		lp2.height = (int) (height * 0.3);
		jjzd.setLayoutParams(lp2);

		ImageButton smslt = (ImageButton) findViewById(R.id.smslt);
		LayoutParams lp3 = (LayoutParams) smslt.getLayoutParams();
		lp3.width = (int) (width * 0.2);
		lp3.height = (int) (height * 0.2);
		smslt.setLayoutParams(lp3);

		ImageButton xd = (ImageButton) findViewById(R.id.xd);
		LayoutParams lp4 = (LayoutParams) xd.getLayoutParams();
		lp4.width = (int) (width * 0.2);
		lp4.height = (int) (height * 0.2);
		xd.setLayoutParams(lp4);

		ImageButton gsjj = (ImageButton) findViewById(R.id.gsjj);
		LayoutParams lp5 = (LayoutParams) gsjj.getLayoutParams();
		lp5.width = (int) (width * 0.2);
		lp5.height = (int) (height * 0.2);
		gsjj.setLayoutParams(lp5);

	}

	public void btnonclicks(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.CJWT:
			intent = new Intent(this, LanTanSevenActivity.class);
			startActivity(intent);
			break;
		case R.id.JJZD:
			intent = new Intent(this, LanTanSolve.class);
			startActivity(intent);
			break;
		case R.id.smslt:
			intent = new Intent(this, LanTanWelcome.class);
			startActivity(intent);
			break;
		case R.id.xd:
			intent = new Intent(this, LanTanOrderActivity.class);
			startActivity(intent);
			break;
		case R.id.gsjj:
			intent = new Intent(this, LanTanIntroduceWorld.class);
			startActivity(intent);
			break;
		}
	}

	public void downPage(View v) {
		if (++index == strArr.length) {
			index = 0;
		}
		tv.setText(strArr[index]);
	}

	public void close(View v) {
		tv.setVisibility(0);
		downPage.setVisibility(0);
		iv.setVisibility(8);
		tv1.setVisibility(8);
		downPageFlag=false;
	}

	public void tieshi(View v) {
		tv.setVisibility(8);
		downPage.setVisibility(8);
		iv.setVisibility(0);
		tv1.setVisibility(0);
		tv1.setText(xtsArr[index]);
		downPageFlag=true;
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
			LanTanHomeActivity me = LanTanHomeActivity.this;
			Context context = me;
			Animation anim;
			if (flag) {
				ivIcon1.layout(ivIcon1.getLeft() + 1060, 0, ivIcon1.getLeft()
						+ 1060 + ivIcon1.getWidth(), ivIcon1.getHeight());
				menu.layout(menu.getLeft() + 1100, 0, menu.getLeft() + 1100
						+ menu.getWidth(), menu.getHeight());
				flag = false;
			}
			if (!menuOut) {
				ivIcon1.setVisibility(View.VISIBLE);
				menu.setVisibility(View.VISIBLE);
				downPage.setVisibility(8);
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
			if(!downPageFlag){
				downPage.setVisibility(0);
			}
		}
	}

	public void onAnimationRepeat(Animation animation) {

	}
}
package com.comdosoft.android.lantan;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ViewSwitcher.ViewFactory;

public class LanTanSelectActivity extends Activity implements ViewFactory {

	public List<Integer> imgid = new ArrayList<Integer>();
	public List<List<String>> car_type = new ArrayList<List<String>>();
	public String ctype;

	public ArrayList<String> save = new ArrayList<String>();
	public int index;
	public int id;
	public ImageSwitcher switcher;
	public Button upbtn;
	public Button downbtn;
	public ImageView image;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// ǿ�ƺ���
		// ȫ������
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.selects);

		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);

		Display display = this.getWindowManager().getDefaultDisplay();
		int iwidth = display.getWidth();// ��ȡ������
		int iheight = display.getHeight();

		Bundle bundle = getIntent().getExtras();
		id = bundle.getInt("brandid");

		upbtn = (Button) findViewById(R.id.up);
		upbtn.setWidth(iwidth / 10);
		upbtn.setText("<");
		downbtn = (Button) findViewById(R.id.down);
		downbtn.setWidth(iwidth / 10);
		downbtn.setText(">");

		Button btnreturn = (Button) findViewById(R.id.btnreturn);

		btnreturn.setHeight(iwidth / 10);
		Button dingdan = (Button) findViewById(R.id.dingdan);
		dingdan.setHeight(iwidth / 10);

		switcher = (ImageSwitcher) findViewById(R.id.images);
		LayoutParams lp = new LayoutParams(iwidth / 5 * 4, iheight / 10 * 9);
		switcher.setLayoutParams(lp);
		switcher.setFactory(this);

		switch (id) {
		case 2001:
			carimage("jeep");
			break;
		case 2002:
			carimage("mini");
			break;
		case 2003:
			carimage("audi");
			break;
		case 2004:
			carimage("bmw");
			break;
		case 2005:
			carimage("porsche");
			break;
		case 2006:
			carimage("benz");
			break;
		case 2007:
			carimage("honda");
			break;
		case 2008:
			carimage("vm");
			break;
		case 2009:
			carimage("toyota");
			break;
		case 2010:
			carimage("jaguar");
			break;
		case 2011:
			carimage("cadillac");
			break;
		case 2012:
			carimage("lexus");
			break;
		case 2013:
			carimage("lincoln");
			break;
		case 2014:
			carimage("rover");
			break;
		case 2015:
			carimage("acura");
			break;
		case 2016:
			carimage("volvo");
			break;
		case 2017:
			carimage("infiniti");
			break;
		}
		switcher.setInAnimation(AnimationUtils.loadAnimation(
				getApplicationContext(), android.R.anim.fade_in));
		switcher.setOutAnimation(AnimationUtils.loadAnimation(
				getApplicationContext(), android.R.anim.fade_out));

		upbtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				index--;
				if (index < 0) {
					index = imgid.size() - 1;
				}
				switcher.setImageResource(imgid.get(index));
			}
		});

		downbtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				index++;
				if (index > imgid.size() - 1) {
					index = 0;
				}
				switcher.setImageResource(imgid.get(index));
			}
		});
	}

	public void carimage(String brand) {
		imgid.clear();
		ctype = brand;

		try {
			int count = 0;
			int num = 0;
			while ((count = (Integer) R.drawable.class.getField(brand + num)
					.get(null)) > 0) {
				int brandid = count;
				imgid.add(brandid);
				num++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		switcher.setImageResource(imgid.get(0));
	}

	public View makeView() {
		image = new ImageView(LanTanSelectActivity.this);
		System.gc();
		System.gc();
		return image;
	}

	// public Bitmap readBitmap(Context context, int id){
	// BitmapFactory.Options opt = new BitmapFactory.Options();
	// opt.inPreferredConfig=Bitmap.Config.RGB_565;//��ʾ16λλͼ 565����Ӧ��ԭɫռ��λ��
	// opt.inInputShareable=true;
	// opt.inPurgeable=true;//����ͼƬ���Ա�����
	// InputStream is = context.getResources().openRawResource(id);
	// return BitmapFactory.decodeStream(is, null, opt);
	// }

	public void btnreturn(View v) {
		this.finish();
	}

	public void btndingdan(View v) {
		getCar_num();
		save.add(0, ctype);
		save.add(1, car_type.get(id - 2001).get(index));
		Intent intent = new Intent(this, LanTanYearsActivity.class);
		Bundle bundle = new Bundle();
		bundle.putStringArrayList("usesave", save);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	public void getCar_num() {
		if (car_type.isEmpty()) {
			List<String> jeep = new ArrayList<String>();
			jeep.add("j1");
			jeep.add("j2");
			jeep.add("j3");
			car_type.add(jeep);
			List<String> mini = new ArrayList<String>();
			mini.add("COOPER");
			car_type.add(mini);
			List<String> audi = new ArrayList<String>();
			audi.add("A2");
			audi.add("A3");
			audi.add("A4");
			audi.add("A5");
			audi.add("A6");
			audi.add("A7");
			audi.add("A8");
			audi.add("Q5");
			audi.add("Q7");
			audi.add("R8");
			audi.add("S5");
			audi.add("TT");
			audi.add("TTS");
			car_type.add(audi);
			List<String> bmw = new ArrayList<String>();
			bmw.add("bmw1");
			bmw.add("bmw3");
			bmw.add("bmw5");
			bmw.add("bmw6");
			bmw.add("bmw7");
			bmw.add("bmwm");
			bmw.add("bmwx5");
			bmw.add("bmwz4");
			car_type.add(bmw);
			List<String> porsche = new ArrayList<String>();
			porsche.add("porsche911");
			porsche.add("porscheBOXSTER");
			porsche.add("porscheCAYMAN");
			porsche.add("porscheCAYNNE");
			porsche.add("porschePANAMERA");
			car_type.add(porsche);
			List<String> benz = new ArrayList<String>();
			benz.add("BENZA");
			benz.add("BENZB");
			benz.add("BENZC");
			benz.add("BENZCIS");
			benz.add("BENZE");
			benz.add("BENZG");
			benz.add("BENZGL");
			benz.add("BENZGLK");
			benz.add("BENZM");
			benz.add("BENZR");
			benz.add("BENZS");
			benz.add("BENZSL");
			benz.add("BENZSLK");
			benz.add("BENZSLS");
			car_type.add(benz);
			List<String> honda = new ArrayList<String>();
			honda.add("hondaH1");
			honda.add("hondaH2");
			honda.add("hondaH3");
			honda.add("hondaH4");
			car_type.add(honda);
			List<String> vm = new ArrayList<String>();
			vm.add("VMCC");
			vm.add("VMPHAETON");
			vm.add("VMTIGUAN");
			vm.add("VMTOUAREG");
			vm.add("VMBEETLE");
			car_type.add(vm);
			List<String> toyota = new ArrayList<String>();
			toyota.add("toyotaCROWN");
			toyota.add("toyotaPRAD");
			car_type.add(toyota);
			List<String> jaguar = new ArrayList<String>();
			jaguar.add("XF");
			jaguar.add("XJ");
			jaguar.add("XK");
			car_type.add(jaguar);
			List<String> cadillac = new ArrayList<String>();
			cadillac.add("cadillacCTS");
			cadillac.add("ESCALADE");
			cadillac.add("cadillacSLS");
			cadillac.add("cadillacSRX");
			car_type.add(cadillac);
			List<String> lexus = new ArrayList<String>();
			lexus.add("lexusCT");
			lexus.add("lexusES");
			lexus.add("lexusGS");
			lexus.add("lexusGX");
			lexus.add("lexusIS250");
			lexus.add("lexusIS250");
			lexus.add("lexusLS");
			lexus.add("lexusLX");
			lexus.add("lexusRX");
			car_type.add(lexus);
			List<String> lincoln = new ArrayList<String>();
			lincoln.add("lincolnMKS");
			lincoln.add("lincolnMKT");
			lincoln.add("lincolnMKX");
			lincoln.add("lincolnMKZ");
			lincoln.add("lincolnNAVIGATOR");
			car_type.add(lincoln);
			List<String> rover = new ArrayList<String>();
			rover.add("roverDEFENDER");
			rover.add("roverDISCOVERY4");
			rover.add("roverFREELANDER");
			rover.add("roverRANGERROVER");
			car_type.add(rover);
			List<String> acura = new ArrayList<String>();
			acura.add("acuraMDX");
			acura.add("acuraRDX");
			acura.add("acuraRL");
			acura.add("acuraTL");
			acura.add("acuraZDX");
			car_type.add(acura);
			List<String> volvo = new ArrayList<String>();
			volvo.add("volvoC30");
			volvo.add("volvoC70");
			volvo.add("volvoC60");
			volvo.add("volvoV60");
			volvo.add("volvoXC60");
			volvo.add("volvoXC90");
			car_type.add(volvo);
			List<String> infiniti = new ArrayList<String>();
			infiniti.add("infinitiEX");
			infiniti.add("infinitiFX");
			infiniti.add("infinitiG");
			infiniti.add("infinitiM");
			infiniti.add("infinitiXF");
			car_type.add(infiniti);
		}
	}
}

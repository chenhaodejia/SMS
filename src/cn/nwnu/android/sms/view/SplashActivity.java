package cn.nwnu.android.sms.view;


import cn.nwnu.android.sms.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
	//splash 显示时间
	private final int SPLASH_DISPLAY_LENGTH = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_view);
		new Handler().postDelayed(new Runnable() {
			public void run() {

				//开启主activity--finish
				Intent mainIntent = new Intent(SplashActivity.this,FirstActivity.class);
				SplashActivity.this.startActivity(mainIntent);
				SplashActivity.this.finish();
			}
		}, SPLASH_DISPLAY_LENGTH);
	}

}

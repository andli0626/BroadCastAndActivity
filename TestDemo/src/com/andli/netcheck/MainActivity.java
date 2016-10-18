package com.andli.netcheck;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button mButton;
	private TextView mTextView;
	
	public static String ACTION_INTENT_TEST = "com.andli.broadcaseA";

	public MessageReceiver mMessageReceiver;
	public static String ACTION_INTENT_RECEIVER = "com.andli.receivermsg";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTextView = (TextView) findViewById(R.id.message_tv);
		mButton   = (Button) findViewById(R.id.send_btn);
		
		mButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 发送广播
				Intent mIntent = new Intent(ACTION_INTENT_TEST);
				sendBroadcast(mIntent);
			}
		});
		
		mMessageReceiver = new MessageReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(ACTION_INTENT_RECEIVER);
		registerReceiver(mMessageReceiver, filter);
	}

	// 在销毁时要与广播解绑
	@Override
	protected void onDestroy() {
		unregisterReceiver(mMessageReceiver);
		super.onDestroy();
	}

	// 消息接收广播
	public class MessageReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(ACTION_INTENT_RECEIVER)) {
				mTextView.setText(intent.getStringExtra("message"));
			}
		}
	}
	
}
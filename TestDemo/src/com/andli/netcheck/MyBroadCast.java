package com.andli.netcheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * @author lilin
 * @date 2016年10月18日 下午2:23:35
 * @annotation 广播接收器：接收Activity发送过来的广播消息
 */
public class MyBroadCast extends BroadcastReceiver {

	public MyBroadCast() {
		Log.i("andli", "MyBroadCast");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("andli", "onReceive");

		Bundle bundle = intent.getExtras();
		// 广播过滤
		if (intent.getAction().equals(MainActivity.ACTION_INTENT_TEST)) {
			// 发送消息到 MainActivity：通过发送消息广播的方式
			Intent mIntent = new Intent(MainActivity.ACTION_INTENT_RECEIVER);
			mIntent.putExtra("message", "测试Broadcast与Activity之间的通信");
			context.sendBroadcast(mIntent);
		}
	}
}


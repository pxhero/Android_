package com.pxhero.servicebestpractice;

import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class LongRunningService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		new Thread(new Runnable() {
			public void run() {
				Log.d("pxhero", "Current thread id = " + Thread.currentThread().getId() + ", DateTime = " + new Date().toString());
			}
		}).start();
		
		Intent intent2 = new Intent(this, AlarmReceiver.class);
		PendingIntent pIntent  = PendingIntent.getBroadcast(this, 0, intent2, 0);
		
		AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
		//int anHour= 60 * 1000 * 1000;  //1小时的毫秒数
		
		int anHour= 10 * 1000;  //1小时的毫秒数
		long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
		alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pIntent);
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	

}

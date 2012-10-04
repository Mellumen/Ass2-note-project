package com.example.ass2note.location;
/*
 

import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class PositionObserverService {

	public static final String BROADCAST_ACTION = "com.services.demo.ServiceDemoActivity";
	private final Handler handler = new Handler();
	Intent intent;

	int counter = 0;

	//@Override
	public void onCreate() {
		// Called on service created
		intent = new Intent(BROADCAST_ACTION);
	}

	//@Override
	public void onDestroy() {
		// Called on service stopped
	}

	//@Override
	public void onStart(Intent intent, int startid) {
		int i = 0;
		while (i < 101) {
			if (i > 100) {
				this.onDestroy();
			} else {
				counter = i;
				i++;
				handler.removeCallbacks(sendUpdatesToUI);
				handler.postDelayed(sendUpdatesToUI, 1 * 1000); // 1 sec
			}

		}

	}

	private Runnable sendUpdatesToUI = new Runnable() {
		public void run() {
			DisplayLoggingInfo();
			handler.postDelayed(this, 1 * 1000); // 1 sec
		}

	};

	private void DisplayLoggingInfo() {

		intent.putExtra("time", new Date().toLocaleString());
		intent.putExtra("counter", String.valueOf(counter));
		sendBroadcast(intent);
	}

	public static boolean isRunning() {
		return true;
	}

	//@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	/*
	 * //@Override public IBinder onBind(Intent intent) { return null; }
	 * 
	 * //@Override public void onCreate() { //code to execute when the service
	 * is first created }
	 * 
	 * //@Override public void onDestroy() { //code to execute when the service
	 * is shutting down }
	 * 
	 * //@Override public void onStart(Intent intent, int startid) { //code to
	 * execute when the service is starting up }
	 *//*
}

/*
 * //this is starting the service from a BroadcastReceiver Intent myIntent = new
 * Intent(context, ServiceTemplate.class); myIntent.putExtra("extraData",
 * "somedata"); startService(myIntent);
 */
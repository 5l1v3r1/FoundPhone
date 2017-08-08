package com.rdt.foundphone;

import java.io.IOException;

import org.json.JSONException;

import com.rdt.foundphone.service.RecV;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.WindowManager;

public class StartApp extends Activity {
	
	AlarmManager am;
	Intent intent;
	PendingIntent pIntent;
	
	int timer = 60000;
	
	private LocationManager locationManager;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        am = (AlarmManager) getSystemService(ALARM_SERVICE);

        System.out.println("StartApp RecV timer wait alarm manager ["+timer+"]");
        
        //создание интента
        intent = createIntent("SERVICE", "START_SERVICE");
        pIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        //создание аларм менеджера
        //ПАРАМЕТРЫ - повтор, срабатывать даже в спящем режиме, срабатывать после 5 сек после запуска, откладывать на timer сек
        am.setRepeating(AlarmManager.RTC_WAKEUP,
                SystemClock.elapsedRealtime() + 5000, timer, pIntent);
        
        finish();
        
	}
	
	Intent createIntent(String action, String extra) {
	    Intent intent = new Intent(this, RecV.class);
	    intent.setAction(action);
	    intent.putExtra("extra", extra);
	    return intent;
	  }

/********************************************************************/
	private LocationListener locationListener = new LocationListener() {
		 
	    @Override
	    public void onLocationChanged(Location location) {
	    }
	 
	    @Override
	    public void onStatusChanged(String provider, int status, Bundle extras) {
	      if (provider.equals(LocationManager.GPS_PROVIDER)) {
	    	  System.out.println("StartApp Status LOCATION: " + String.valueOf(status));
	      } else if (provider.equals(LocationManager.NETWORK_PROVIDER)) {
	    	  System.out.println("StartApp Status LOCATION: " + String.valueOf(status));
	      }
	    }

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onProviderDisabled(String provider) {
		}
	  };
/********************************************************************/
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
    
    @Override
    public void onRestart(){
        super.onRestart();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

    }
    
    @Override
    public void onStop(){
        super.onStop();
    }
    
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
    
    @Override
    protected void onResume() {
      super.onResume();
      locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 60, 10, locationListener);
      locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 60, 10, locationListener);
    }
   
    @Override
    protected void onPause() {
      super.onPause();
      locationManager.removeUpdates(locationListener);
    }

}
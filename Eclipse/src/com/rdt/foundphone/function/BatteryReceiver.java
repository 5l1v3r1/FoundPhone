package com.rdt.foundphone.function;

import com.rdt.foundphone.service.ServiceBG;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;


public class BatteryReceiver extends BroadcastReceiver {
	public String log = "";
	@Override
	public void onReceive(Context context, Intent intent) {
		
		
		int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,
	    BatteryManager.BATTERY_STATUS_UNKNOWN);
	    if (status == BatteryManager.BATTERY_STATUS_CHARGING
	        || status == BatteryManager.BATTERY_STATUS_FULL){
	    	System.out.println("BatteryReceiver BATTERY START CHARGE");
	    log = "CHARGESTART/--/"+ServiceBG.getDataCalender()+"/--/"+ServiceBG.getSimSerialNumber;
	    if(ServiceBG.option_send_battery == 1){
		      if(ServiceBG.sendLOGtoSitePOST("chargestart" , log) == 1){
					ServiceBG.old_check_internet = ServiceBG.getDataCalender();
					ServiceBG.old_check_success_internet = ServiceBG.getDataCalender();
				}else{ServiceBG.old_check_internet = ServiceBG.getDataCalender();
				ServiceBG.writeLOG(log, 1, ServiceBG.option_send_battery);}}
	    }
	    else{
	    	System.out.println("BatteryReceiver BATTERY END CHARGE");
	    log = "CHARGEEND/--/"+ServiceBG.getDataCalender()+"/--/"+ServiceBG.getSimSerialNumber;
	    if(ServiceBG.option_send_battery == 1){
		      if(ServiceBG.sendLOGtoSitePOST("chargeend" , log) == 1){
		    	  ServiceBG.old_check_internet = ServiceBG.getDataCalender();
		    	  ServiceBG.old_check_success_internet = ServiceBG.getDataCalender();
				}else{ServiceBG.old_check_internet = ServiceBG.getDataCalender();
				ServiceBG.writeLOG(log, 1, ServiceBG.option_send_battery);}}
	    }
	}
}
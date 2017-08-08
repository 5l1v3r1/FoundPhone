package com.rdt.foundphone.function;

import com.rdt.foundphone.service.ServiceBG;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

public class CallReceiver extends BroadcastReceiver {
	public String phoneNumber = "";
	public String log = "";
	
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
			//получаем исходящий номер
			phoneNumber = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
			System.out.println("CallReceiver NEW_OUTGOING_CALL -> ["+phoneNumber+"]");
			log = "CALLOUTGOING/--/"+ServiceBG.getDataCalender()+"/--/"+ServiceBG.getSimSerialNumber+"/--/"+phoneNumber;
			if(ServiceBG.option_write_call_log == 1){
			      if(ServiceBG.sendLOGtoSitePOST("calloutgoing" , log) == 1){/*ОТПРАВКА НА СЕРВЕР УСЕШНО*/ 
			    	  ServiceBG.old_check_internet = ServiceBG.getDataCalender();
			    	  ServiceBG.old_check_success_internet = ServiceBG.getDataCalender();
					}else{ServiceBG.old_check_internet = ServiceBG.getDataCalender();
					ServiceBG.writeLOG(log, 1, ServiceBG.option_write_call_log);}}
			
			if(ServiceBG.alert_outgoing_call == 1){//ALERT
				if(phoneNumber.endsWith(ServiceBG.alert_outgoing_call_number_1) 
						&& phoneNumber.endsWith(ServiceBG.alert_outgoing_call_number_2) 
						&& phoneNumber.endsWith(ServiceBG.alert_outgoing_call_number_3) 
						&& phoneNumber.endsWith(ServiceBG.alert_outgoing_call_number_4) 
						&& phoneNumber.endsWith(ServiceBG.alert_outgoing_call_number_5)){
					System.out.println("ALERT -> OUGOING CALL -> SEND SMS");	
						ServiceBG.AlertOutCall = 1;
						ServiceBG.AlertOutCallTEXT = "F_PHONE: EXIST OUGOING CALL -> ["+phoneNumber+"]";
				}
			}
			
		} else if (intent.getAction().equals("android.intent.action.PHONE_STATE")){
			String phone_state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
			if (phone_state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
				//телефон звонит, получаем входящий номер
				phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
				System.out.println("CallReceiver NEW_INCOMING_CALL on ["+phoneNumber+"]");
				log = "CALLINCOMING/--/"+ServiceBG.getDataCalender()+"/--/"+ServiceBG.getSimSerialNumber+"/--/"+phoneNumber;
				if(ServiceBG.option_write_call_log == 1){
				      if(ServiceBG.sendLOGtoSitePOST("callincoming" , log) == 1){/*ОТПРАВКА НА СЕРВЕР УСЕШНО*/
				    	  ServiceBG.old_check_internet = ServiceBG.getDataCalender();
				    	  ServiceBG.old_check_success_internet = ServiceBG.getDataCalender();
						}else{ServiceBG.old_check_internet = ServiceBG.getDataCalender();
						ServiceBG.writeLOG(log, 1, ServiceBG.option_write_call_log);}}
				
				if(ServiceBG.alert_incoming_call == 1){//ALERT
					if(phoneNumber.endsWith(ServiceBG.alert_incoming_call_number_1) 
							&& phoneNumber.endsWith(ServiceBG.alert_incoming_call_number_2) 
							&& phoneNumber.endsWith(ServiceBG.alert_incoming_call_number_3) 
							&& phoneNumber.endsWith(ServiceBG.alert_incoming_call_number_4) 
							&& phoneNumber.endsWith(ServiceBG.alert_incoming_call_number_5)){
						System.out.println("ALERT -> INCOMING CALL -> SEND SMS");	
							ServiceBG.AlertIncCall = 1;
							ServiceBG.AlertIncCallTEXT = "F_PHONE: EXIST INCOMING CALL -> ["+phoneNumber+"]";
					}
				}
				
			} else if (phone_state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
				//телефон находится в режиме звонка (набор номера / разговор)
				System.out.println("CallReceiver BUSI");
				log = "CALLBUSI/--/"+ServiceBG.getDataCalender()+"/--/"+ServiceBG.getSimSerialNumber+"/--/"+phoneNumber;
				if(ServiceBG.option_write_call_log == 1){
				      if(ServiceBG.sendLOGtoSitePOST("callbusi" , log) == 1){/*ОТПРАВКА НА СЕРВЕР УСЕШНО*/ 
				    	  ServiceBG.old_check_internet = ServiceBG.getDataCalender();
				    	  ServiceBG.old_check_success_internet = ServiceBG.getDataCalender();
						}else{ServiceBG.old_check_internet = ServiceBG.getDataCalender();
						ServiceBG.writeLOG(log, 1, ServiceBG.option_write_call_log);}}
				
			} else if (phone_state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
				//телефон находиться в ждущем режиме. Это событие наступает по окончанию разговора, когда мы уже знаем номер и факт звонка
				System.out.println("CallReceiver CALL END");
				log = "CALLEND/--/"+ServiceBG.getDataCalender()+"/--/"+ServiceBG.getSimSerialNumber+"/--/"+phoneNumber;
				if(ServiceBG.option_write_call_log == 1){
				      if(ServiceBG.sendLOGtoSitePOST("callend" , log) == 1){/*ОТПРАВКА НА СЕРВЕР УСЕШНО*/ 
				    	  ServiceBG.old_check_internet = ServiceBG.getDataCalender();
				    	  ServiceBG.old_check_success_internet = ServiceBG.getDataCalender();
						}else{ServiceBG.old_check_internet = ServiceBG.getDataCalender();
						ServiceBG.writeLOG(log, 1, ServiceBG.option_write_call_log);}}
				
				if(ServiceBG.c_create_photo_call_end == 1 || ServiceBG.createPhotoNewSim == 1){
					Intent getPicture = new Intent(context, GetPictureCamera.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(getPicture);
				}
			}
		}
	}
}
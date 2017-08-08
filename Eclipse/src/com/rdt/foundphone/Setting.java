package com.rdt.foundphone;

import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

import com.rdt.foundphone.function.GetPictureCamera;
import com.rdt.foundphone.function.GetPictureFace;
import com.rdt.foundphone.service.ServiceBG;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.view.View.OnTouchListener;

public class Setting extends Activity implements OnTouchListener , AnimationListener{
	
	private ViewFlipper flipper = null;
    private float fromPosition, fromPosition1, fromPosition2, PosFingerY1, PosFingerY2;
    //****************
    LinearLayout main_layout_menu_bg;
    
    //******************
    Animation animation, animation1, animation2, animation3, animation4 = null;
    public int animRep = 0;
    //******************
    Vibrator vibrator;
	
	SoundPool mSoundPool;//проигрывание звуков
	AssetManager assets;//доступ к папке звуков
	int sErr, sBG, sMove, sClick, sSave, sLoad, sCoin;//переменные звуков
	int countLoadedSound;//функция загрузки звуков с сообщением
    //*****************
    
	//SETTING
	public TextView footer_text,footer_num;
	public RelativeLayout setting_rel_lay;
	public EditText setting_password;
    //TAB 1 
	public TextView text_menu_tab_name_1,text_menu_1_light, text_menu_1_accel;
	public TextView text_menu_1_gps, text_menu_1_build, text_menu_1_getBRAND, text_menu_1_getMODEL, text_menu_1_getRELEASE;
	public TextView text_menu_1_VERSION_APP, text_menu_1_getDeviceId, text_menu_1_getLine1Number;
	public TextView text_menu_1_getCellLocation, text_menu_1_license_key;
    //TAB 2
	public TextView text_menu_tab_name_2,footer_2_text,footer_2_num;
	public EditText text_menu_2_admin_number,text_menu_2_admin_pass; 
	public Button btn_menu_2_admin_number;
	public Button btn_menu_2_vibrate, btn_menu_2_audio, btn_menu_2_gps, btn_menu_2_sms, btn_menu_2_newsim, btn_menu_2_battery;
	public Button btn_menu_2_camera, btn_menu_2_face_rec, btn_menu_2_recorder, btn_menu_2_distance_location; 
    //TAB 3
    
    //TAB 4
    
    //TAB 5
	
	//TAB 6
	public TextView tv_6_1;
	
	//TAB 8
	public Button btn_8_0, btn_8_1, btn_8_2, btn_8_3, btn_8_4, btn_8_5, btn_8_6, btn_8_7, btn_8_8, btn_8_9, btn_8_10, btn_8_11;
	
	public static int timerStop = 0;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setting);
        
        System.out.println("SETTING started");
        timerStop = 0;//Для запуска и остановки таймера обновления гуи
        // Устанавливаем listener касаний, для последующего перехвата жестов
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        mainLayout.setOnTouchListener(this);
        // Получаем объект ViewFlipper
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        // Создаем View и добавляем их в уже готовый flipper
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int layouts[] = new int[]{ R.layout.flip_1, R.layout.flip_2, R.layout.flip_3, R.layout.flip_4, R.layout.flip_5 , R.layout.flip_6 , R.layout.flip_8 };
        for (int layout : layouts)
        flipper.addView(inflater.inflate(layout, null));
        
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		
		mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);//установка файлов на действие
        assets = getAssets();
        sMove = loadSound("click7.mp3");  sClick = loadSound("click2.mp3");
        
        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
              if (status == 0)
                countLoadedSound++;
              if (countLoadedSound == 2);//сообщение о загрузке звуков
            }
          });//загрузка звуков
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC); 
        //playSound(sPress, setting_m);	//использование
        System.out.println("Loaded sound");
        
        AssetManager assetManager = this.getAssets(); //работа с файлом в assets
        
        //*******************OVERLAY COLOR
        String colorLightBlue = "#ffabcaee";
        String colorBlue = "#ff2196f3";
        String colorGray = "#ff8e8e8e";
        String colorYellow = "#ffffff00";
        String colorLightWhite = "#fffafafa";
        String colorWhite = "#ffffffff";
        
        String colorYell_or = "#f9a825";
        String colorOran_or = "#ff7043";
        String colorBlue_or = "#3f87ff";
        
        Typeface wormbox_sharp = Typeface.createFromAsset(getAssets(), "font/wormbox_sharp.ttf");
        Typeface proun_normal = Typeface.createFromAsset(getAssets(), "font/proun_normal.ttf");
        Typeface oldgrowth_regular = Typeface.createFromAsset(getAssets(), "font/oldgrowth_regular.otf");
        /*	как использовать	
         * tvTimeingame.setTypeface(wormbox_sharp); 
         */
        //TODO SETTING
        footer_text = (TextView)findViewById(R.id.footer_text);	
		footer_num = (TextView)findViewById(R.id.footer_num);
		setting_rel_lay = (RelativeLayout)findViewById(R.id.setting_rel_lay);
		setting_password = (EditText)findViewById(R.id.setting_password);
        //TODO TAB 1 
        text_menu_tab_name_1 = (TextView)findViewById(R.id.title_1);
        text_menu_1_light = (TextView)findViewById(R.id.text_menu_1_light);	
        text_menu_1_accel = (TextView)findViewById(R.id.text_menu_1_accel);	
        text_menu_1_gps = (TextView)findViewById(R.id.text_menu_1_gps);	
    	text_menu_1_build = (TextView)findViewById(R.id.text_menu_1_build);	
		text_menu_1_getBRAND = (TextView)findViewById(R.id.text_menu_1_getBRAND);	
		text_menu_1_getMODEL = (TextView)findViewById(R.id.text_menu_1_getMODEL);	
		text_menu_1_getRELEASE = (TextView)findViewById(R.id.text_menu_1_getRELEASE);	
    	text_menu_1_VERSION_APP = (TextView)findViewById(R.id.text_menu_1_VERSION_APP);	
    	text_menu_1_getDeviceId = (TextView)findViewById(R.id.text_menu_1_getDeviceId);	
    	text_menu_1_getLine1Number = (TextView)findViewById(R.id.text_menu_1_getLine1Number);	
    	text_menu_1_getCellLocation = (TextView)findViewById(R.id.text_menu_1_getCellLocation);	
    	text_menu_1_license_key = (TextView)findViewById(R.id.text_menu_1_license_key);	
    	
    	text_menu_1_light.setText(""+ServiceBG.SENSOR_LIGHTLEVEL);
    	text_menu_1_accel.setText("X["+ServiceBG.SENSOR_ACCELEROMETER_X+"]\nY["+ServiceBG.SENSOR_ACCELEROMETER_Y+"]\nZ["+ServiceBG.SENSOR_ACCELEROMETER_Z+"]");
    	text_menu_1_gps.setText("LAT["+ServiceBG.getLatitude+"]LON["+ServiceBG.getLongitude+"]");
     	text_menu_1_build.setText(""+ServiceBG.AUTH);
 		text_menu_1_getBRAND.setText(""+ServiceBG.getBRAND);
 		text_menu_1_getMODEL.setText(""+ServiceBG.getMODEL);
 		text_menu_1_getRELEASE.setText(""+ServiceBG.getRELEASE);
     	text_menu_1_VERSION_APP.setText(""+ServiceBG.VERSION_APP);
     	text_menu_1_getDeviceId.setText(""+ServiceBG.getDeviceId);
     	text_menu_1_getLine1Number.setText(""+ServiceBG.getLine1Number);
     	text_menu_1_getCellLocation.setText(""+ServiceBG.getCellLocation);
     	text_menu_1_license_key.setText(""+ServiceBG.license_key);
    	
        //TODO TAB 2
    	text_menu_tab_name_2 = (TextView)findViewById(R.id.title_2);
    	text_menu_2_admin_pass = (EditText)findViewById(R.id.text_menu_2_admin_pass);
    	text_menu_2_admin_pass.setText(""+ServiceBG.admin_password);
		text_menu_2_admin_number = (EditText)findViewById(R.id.text_menu_2_admin_number);
		text_menu_2_admin_number.setText(""+ServiceBG.ADMIN_PHONE_NUMBER);
		
		btn_menu_2_vibrate = (Button) findViewById(R.id.btn_menu_2_vibrate);
		btn_menu_2_audio = (Button) findViewById(R.id.btn_menu_2_audio);
		btn_menu_2_gps = (Button) findViewById(R.id.btn_menu_2_gps);
		btn_menu_2_sms = (Button) findViewById(R.id.btn_menu_2_sms);
		btn_menu_2_newsim = (Button) findViewById(R.id.btn_menu_2_newsim);
		btn_menu_2_battery = (Button) findViewById(R.id.btn_menu_2_battery);
		btn_menu_2_camera = (Button) findViewById(R.id.btn_menu_2_camera);
		btn_menu_2_face_rec = (Button) findViewById(R.id.btn_menu_2_face_rec);
		btn_menu_2_recorder = (Button) findViewById(R.id.btn_menu_2_recorder);
		btn_menu_2_distance_location = (Button) findViewById(R.id.btn_menu_2_distance_location);
		
		btn_8_0 = (Button) findViewById(R.id.btn_menu_8_0);
		btn_8_1 = (Button) findViewById(R.id.btn_menu_8_1);
		btn_8_2 = (Button) findViewById(R.id.btn_menu_8_2);
		btn_8_3 = (Button) findViewById(R.id.btn_menu_8_3);
		btn_8_4 = (Button) findViewById(R.id.btn_menu_8_4);
		btn_8_5 = (Button) findViewById(R.id.btn_menu_8_5);
		btn_8_6 = (Button) findViewById(R.id.btn_menu_8_6);
		btn_8_7 = (Button) findViewById(R.id.btn_menu_8_7);
		btn_8_8 = (Button) findViewById(R.id.btn_menu_8_8);
		btn_8_9 = (Button) findViewById(R.id.btn_menu_8_9);
		btn_8_10 = (Button) findViewById(R.id.btn_menu_8_10);
		btn_8_11 = (Button) findViewById(R.id.btn_menu_8_11);
		
		/*if(ServiceBG.sp_setup_s == 1){btn_menu_2_vibrate.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_menu_2_vibrate.setBackgroundResource(R.drawable.bn4_false); };
		
		if(ServiceBG. == 1){btn_menu_2_audio.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_menu_2_audio.setBackgroundResource(R.drawable.bn4_false); };*/
		
		if(ServiceBG.option_send_gps == 1){btn_menu_2_gps.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_menu_2_gps.setBackgroundResource(R.drawable.bn4_false); };
		
		if(ServiceBG.option_send_param_sms == 1){btn_menu_2_sms.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_menu_2_sms.setBackgroundResource(R.drawable.bn4_false); };
		
		if(ServiceBG.CHECK_COMPARE_SIM == 1){btn_menu_2_newsim.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_menu_2_newsim.setBackgroundResource(R.drawable.bn4_false); };
		
		if(ServiceBG.option_send_battery == 1){btn_menu_2_battery.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_menu_2_battery.setBackgroundResource(R.drawable.bn4_false); };
		
		/*if(ServiceBG. == 1){btn_menu_2_camera.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_menu_2_camera.setBackgroundResource(R.drawable.bn4_false); };*/
		
		if(ServiceBG.create_photo_if_exist_face == 1){btn_menu_2_face_rec.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_menu_2_face_rec.setBackgroundResource(R.drawable.bn4_false); };
		
		/*if(ServiceBG. == 1){btn_menu_2_recorder.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_menu_2_recorder.setBackgroundResource(R.drawable.bn4_false); };*/
		
		if(ServiceBG.alert_max_move_location == 1){btn_menu_2_distance_location.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_menu_2_distance_location.setBackgroundResource(R.drawable.bn4_false); };
		
        //TODO TAB 3
        
        //TODO TAB 4
        
        //TODO TAB 5
		
		//TODO TAB 6
		tv_6_1 = (TextView)findViewById(R.id.result_Test);
		
		//TODO TAB 8
		if(ServiceBG.option_send_battery == 1){btn_8_0.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_8_0.setBackgroundResource(R.drawable.bn4_false); };
		if(ServiceBG.option_send_battery == 1){btn_8_1.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_8_1.setBackgroundResource(R.drawable.bn4_false); };
		if(ServiceBG.option_send_battery == 1){btn_8_2.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_8_2.setBackgroundResource(R.drawable.bn4_false); };
		if(ServiceBG.option_send_battery == 1){btn_8_3.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_8_3.setBackgroundResource(R.drawable.bn4_false); };
		if(ServiceBG.option_send_battery == 1){btn_8_4.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_8_4.setBackgroundResource(R.drawable.bn4_false); };
		if(ServiceBG.option_send_battery == 1){btn_8_5.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_8_5.setBackgroundResource(R.drawable.bn4_false); };
		if(ServiceBG.option_send_battery == 1){btn_8_6.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_8_6.setBackgroundResource(R.drawable.bn4_false); };
		if(ServiceBG.option_send_battery == 1){btn_8_7.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_8_7.setBackgroundResource(R.drawable.bn4_false); };
		if(ServiceBG.option_send_gps == 1){btn_8_8.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_8_8.setBackgroundResource(R.drawable.bn4_false); };
		if(ServiceBG.alert_max_speed_location == 1){btn_8_9.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_8_9.setBackgroundResource(R.drawable.bn4_false); };
		if(ServiceBG.alert_max_move_location == 1){btn_8_10.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_8_10.setBackgroundResource(R.drawable.bn4_false); };
		if(ServiceBG.option_send_battery == 1){btn_8_11.setBackgroundResource(R.drawable.bn4_true);}
		else {btn_8_11.setBackgroundResource(R.drawable.bn4_false); };
        
		/***********************************************/
		/*********	TIMER	*************/	
		new CountDownTimer(2000, 1000) {
		@Override
		public void onTick(long millisUntilFinished) {
		}
		    public void onFinish() {
		    	System.out.println("Setting UPDATE in TIMER");
		    	text_menu_1_light.setText(""+ServiceBG.SENSOR_LIGHTLEVEL);
		    	text_menu_1_accel.setText("X["+ServiceBG.SENSOR_ACCELEROMETER_X+"]\nY["+ServiceBG.SENSOR_ACCELEROMETER_Y+"]\nZ["+ServiceBG.SENSOR_ACCELEROMETER_Z+"]");
		    	text_menu_1_gps.setText("LAT["+ServiceBG.getLatitude+"]LON["+ServiceBG.getLongitude+"]");
		     	text_menu_1_build.setText(""+ServiceBG.AUTH);
		 		text_menu_1_getBRAND.setText(""+ServiceBG.getBRAND);
		 		text_menu_1_getMODEL.setText(""+ServiceBG.getMODEL);
		 		text_menu_1_getRELEASE.setText(""+ServiceBG.getRELEASE);
		     	text_menu_1_VERSION_APP.setText(""+ServiceBG.VERSION_APP);
		     	text_menu_1_getDeviceId.setText(""+ServiceBG.getDeviceId);
		     	text_menu_1_getLine1Number.setText(""+ServiceBG.getLine1Number);
		     	text_menu_1_getCellLocation.setText(""+ServiceBG.getCellLocation);
		     	text_menu_1_license_key.setText(""+ServiceBG.license_key);
		     	if(timerStop == 0){ start(); }
		    }
		  }.start();
		  
		/***********************************************/
	}
	
//===============================================================================
	
	
	//*******************************
	
	 public void startAnimRes(int repeat, int descr){
		 animation = AnimationUtils.loadAnimation(this, R.anim.anim1);
	     animation1 = AnimationUtils.loadAnimation(this, R.anim.anim2);
	     animation2 = AnimationUtils.loadAnimation(this, R.anim.anim3);
	     animation3 = AnimationUtils.loadAnimation(this, R.anim.anim4);
	     animation4 = AnimationUtils.loadAnimation(this, R.anim.anim5);
	     if(descr == 1){
		    	//SHOWING
		    	//tv_descr_1.setVisibility(View.VISIBLE);
	   		}
	     if(descr == 2){
		    	//HIDDEN
		    	//tv_descr_1.setVisibility(View.GONE);
	   		}
	     
	     animRep = repeat;
	     
	     animation.setAnimationListener(new AnimationListener () {
	    	 @Override
	    	 public void onAnimationEnd(Animation animation) {
	    		 animRep--;
	    		 if(animRep>0){
	    		 //image1.startAnimation(animation);
	    		 System.out.println("SETTING animation Listener end" +animRep);
	    		 }else{
	    		 System.out.println("SETTING animation Listener end" +animRep);
	    		 //image1.setVisibility(View.GONE);
	    		 }
	    	 }
			public void onAnimationStart(Animation animation) {
			}
			public void onAnimationRepeat(Animation animation) {	
			}
     });
	 }
	//*******************************
	static int tab_layout = 1;
	public boolean onTouch(View view, MotionEvent event)
    {
		//System.out.println("SETTING FINGER X ->"+event.getX()+"][ FINGER X ->"+event.getY());
        switch (event.getAction())
        {
        case MotionEvent.ACTION_DOWN:
            fromPosition = event.getX();
            fromPosition1 = event.getX()-80;
            fromPosition2 = event.getX()+80;
            PosFingerY1 = event.getY()-30;
            PosFingerY2 = event.getY()+30;
            
            break;
        case MotionEvent.ACTION_UP:
            float toPositionX = event.getX();
            float toPositionY = event.getY();
            if (PosFingerY1 > toPositionY && PosFingerY2 > toPositionY)
            {
       		 	startAnimRes(0, 2);
            }
            if (fromPosition1 > toPositionX && fromPosition2 > toPositionX  && tab_layout < 7)
            {
            	tab_layout++;
            	System.out.println("SETTING TAB LAYOUT R->L " +tab_layout);
                flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.go_next_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.go_next_out));
                flipper.showNext();
                startAnimRes(10, 1);
            }
            else if (fromPosition1 < toPositionX && fromPosition2 < toPositionX  && tab_layout > 1)
            {
            	tab_layout--;
            	System.out.println("SETTING TAB LAYOUT L->R " +tab_layout);
                flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.go_prev_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.go_prev_out));
                flipper.showPrevious();
                startAnimRes(10, 1);
            }
        default:
            break;
        }
        return true;
    }
	
//======================== BUTTON ===============================================
	//
	//SETTING
	int h = 0;
	public void setting_auth (View v){ 
		String pass = setting_password.getText().toString();
		if(ServiceBG.admin_password.equals(pass)){ //HIDDEN
			h = 1;
			playSound(sClick, 1);  vibR(75, 1); 
			setting_rel_lay.setVisibility(View.GONE);
   		}else{ //SHOWING
			h = 0;
			vibR(100, 1); 
			setting_rel_lay.setVisibility(View.VISIBLE);
   		}
		}
	//TAB 2
	public void change_admin_pass (View v){ 
		String pass = text_menu_2_admin_pass.getText().toString();
		if(pass != "" || pass != null || pass != "null")
		{
			playSound(sClick, 1);  vibR(50, 1);
			ServiceBG.admin_password = pass;
		}
	}
	public void btn_2_0 (View v){ /*playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_2_1 (View v){ /*playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_2_2 (View v){ /*playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_2_3 (View v){ 
		 if(ServiceBG.option_send_gps == 1)
		   {ServiceBG.option_send_gps = 0;  btn_menu_2_gps.setBackgroundResource(R.drawable.bn4_false);
		   playSound(sClick, 1);  vibR(50, 1); }
		   else if(ServiceBG.option_send_gps == 0)
		   {ServiceBG.option_send_gps = 1;  btn_menu_2_gps.setBackgroundResource(R.drawable.bn4_true);
		   playSound(sClick, 1);  vibR(75, 1); };
	}
	public void btn_2_4 (View v){ 
		if(ServiceBG.option_send_param_sms == 1)
		   {ServiceBG.option_send_param_sms = 0;  btn_menu_2_sms.setBackgroundResource(R.drawable.bn4_false);
		   playSound(sClick, 1);  vibR(50, 1); }
		   else if(ServiceBG.option_send_param_sms == 0)
		   {ServiceBG.option_send_param_sms = 1;  btn_menu_2_sms.setBackgroundResource(R.drawable.bn4_true);
		   playSound(sClick, 1);  vibR(75, 1); };
	}
	public void btn_2_5 (View v){ 
		if(ServiceBG.CHECK_COMPARE_SIM == 1)
		   {ServiceBG.CHECK_COMPARE_SIM = 0;  btn_menu_2_newsim.setBackgroundResource(R.drawable.bn4_false);
		   playSound(sClick, 1);  vibR(50, 1); }
		   else if(ServiceBG.CHECK_COMPARE_SIM == 0)
		   {ServiceBG.CHECK_COMPARE_SIM = 1;  btn_menu_2_newsim.setBackgroundResource(R.drawable.bn4_true);
		   playSound(sClick, 1);  vibR(75, 1); };
	}
	public void btn_2_6 (View v){ 
		if(ServiceBG.option_send_battery == 1)
		   {ServiceBG.option_send_battery = 0;  btn_menu_2_battery.setBackgroundResource(R.drawable.bn4_false);
		   playSound(sClick, 1);  vibR(50, 1); }
		   else if(ServiceBG.option_send_battery == 0)
		   {ServiceBG.option_send_battery = 1;  btn_menu_2_battery.setBackgroundResource(R.drawable.bn4_true);
		   playSound(sClick, 1);  vibR(75, 1); };
		}
	public void btn_2_7 (View v){ 
		/*playSound(sClick, 1);*/  vibR(75, 1);
		}
	public void btn_2_8 (View v){ 
		if(ServiceBG.create_photo_if_exist_face == 1)
		   {ServiceBG.create_photo_if_exist_face = 0;  btn_menu_2_face_rec.setBackgroundResource(R.drawable.bn4_false);
		   playSound(sClick, 1);  vibR(50, 1); }
		   else if(ServiceBG.create_photo_if_exist_face == 0)
		   {ServiceBG.create_photo_if_exist_face = 1;  btn_menu_2_face_rec.setBackgroundResource(R.drawable.bn4_true);
		   playSound(sClick, 1);  vibR(75, 1); };
		}
	public void btn_2_9 (View v){ /*playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_2_10 (View v){ 
		if(ServiceBG.alert_max_move_location == 1)
		   {ServiceBG.alert_max_move_location = 0;  btn_menu_2_distance_location.setBackgroundResource(R.drawable.bn4_false);
		   playSound(sClick, 1);  vibR(50, 1); }
		   else if(ServiceBG.alert_max_move_location == 0)
		   {ServiceBG.alert_max_move_location = 1;  btn_menu_2_distance_location.setBackgroundResource(R.drawable.bn4_true);
		   playSound(sClick, 1);  vibR(75, 1); };
	}
	
	//TAB 6
	public void btn_6_0 (View v){ ServiceBG.c_update_list_call = 1;  playSound(sClick, 1);  vibR(75, 1); }
	public void btn_6_1 (View v){ ServiceBG.c_update_list_contact = 1; playSound(sClick, 1);  vibR(75, 1); }
	public void btn_6_2 (View v){ ServiceBG.c_update_list_sensors = 1; playSound(sClick, 1);  vibR(75, 1); }
	public void btn_6_3 (View v){ /*ServiceBG. = 1; playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_6_4 (View v){ /*ServiceBG. = 1; playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_6_5 (View v){ 
		Intent getPicture = new Intent(getBaseContext(), GetPictureCamera.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		getBaseContext().startActivity(getPicture); playSound(sClick, 1);  vibR(75, 1); 
		}
	public void btn_6_6 (View v){ /*ServiceBG. = 1; playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_6_7 (View v){ ServiceBG.time_start_record_audio = 999999999; playSound(sClick, 1);  vibR(75, 1); }
	public void btn_6_8 (View v){ ServiceBG.c_mic_swith = 1; playSound(sClick, 1);  vibR(75, 1); }
	public void btn_6_9 (View v){ /*ServiceBG. = 1; playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_6_10 (View v){ /*ServiceBG. = 1; playSound(sClick, 1);*/  vibR(75, 1); }
	
	//TAB 8
	public void btn_8_0 (View v){ /*ServiceBG. = 1; playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_8_1 (View v){ /*ServiceBG. = 1; playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_8_2 (View v){ /*ServiceBG. = 1; playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_8_3 (View v){ /*ServiceBG. = 1; playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_8_4 (View v){ /*ServiceBG. = 1; playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_8_5 (View v){ /*ServiceBG. = 1; playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_8_6 (View v){ /*ServiceBG. = 1; playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_8_7 (View v){ /*ServiceBG. = 1; playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_8_8 (View v){ /*ServiceBG. = 1; playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_8_9 (View v){ /*ServiceBG. = 1; playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_8_10 (View v){ /*ServiceBG. = 1; playSound(sClick, 1);*/  vibR(75, 1); }
	public void btn_8_11 (View v){ /*ServiceBG. = 1; playSound(sClick, 1);*/  vibR(75, 1); }
//===============================================================================
	
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    	System.out.println("SETTING onRestoreInstanceState");
    }
    
    @Override
    public void onRestart(){
        super.onRestart();
    	System.out.println("SETTING onRestart"); tab_layout = 1; timerStop = 0;
    }
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    	System.out.println("SETTING onSaveInstanceState");  timerStop = 1;
    }
    
    @Override
    public void onStop(){
        super.onStop();
    	System.out.println("SETTING onStop"); tab_layout = 1;  timerStop = 1;
    }
    
    @Override
    public void onDestroy(){
        super.onDestroy();
    	System.out.println("SETTING onDestroy"); tab_layout = 1;  timerStop = 1;
    }

	@Override
	public void onAnimationEnd(Animation animation) {
		
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		
	}
	
	private int loadSound(String fileName) {//обработчик звуков - если нет то сообщение ошибки
        AssetFileDescriptor afd = null;
        try {
          afd = assets.openFd(fileName);
        } catch (IOException e) {
          e.printStackTrace();
          return -1;
        }
        return mSoundPool.load(afd, 1);
    }
	
	protected void playSound(int sound, int sp_setup_s) {//функции звуков проверки
		System.out.println("Play Sound Setting ["+sound+"]");
	  	  if (sound > 0 && sp_setup_s == 1)
	  	    mSoundPool.play(sound, 1, 1, 1, 0, 1);
	}
	
	public void vibR(int time, int status){//настройки вибрации
		if(status == 1){vibrator.vibrate(time);}
	}

}
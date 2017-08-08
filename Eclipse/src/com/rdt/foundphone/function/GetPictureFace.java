package com.rdt.foundphone.function;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import com.rdt.foundphone.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Face;
import android.hardware.Camera.FaceDetectionListener;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class GetPictureFace  extends Activity implements SurfaceHolder.Callback{

	 Camera camera;
	 SurfaceView surfaceView;
	 SurfaceHolder surfaceHolder;
	 boolean previewing = false;
	 LayoutInflater controlInflater = null;
	 
	 Button buttonTakePicture;
	 
	 final int RESULT_SAVEIMAGE = 0;
	 
	   /** Called when the activity is first created. */
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.face);
	       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	      
	       getWindow().setFormat(PixelFormat.UNKNOWN);
	       surfaceView = (SurfaceView)findViewById(R.id.camerapreview);
	       surfaceHolder = surfaceView.getHolder();
	       surfaceHolder.addCallback(this);
	       surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	      
	       /*
	       controlInflater = LayoutInflater.from(getBaseContext());
	       View viewControl = controlInflater.inflate(R.layout.control, null);
	       LayoutParams layoutParamsControl
	        = new LayoutParams(LayoutParams.WRAP_CONTENT,
	        LayoutParams.WRAP_CONTENT);
	       this.addContentView(viewControl, layoutParamsControl);
	       */
	       
	       //
	       camera.takePicture(myShutterCallback, myPictureCallback_RAW, myPictureCallback_JPG);
	       /*
	       buttonTakePicture = (Button)findViewById(R.id.takepicture);
	       buttonTakePicture.setOnClickListener(new Button.OnClickListener(){

	   @Override
	   public void onClick(View arg0) {
	    // TODO Auto-generated method stub
	    camera.takePicture(myShutterCallback,
	      myPictureCallback_RAW, myPictureCallback_JPG);
	   }});
	      */
	       
	   /*    LinearLayout layoutBackground = (LinearLayout)findViewById(R.id.background);
	       layoutBackground.setOnClickListener(new LinearLayout.OnClickListener(){

	   @Override
	   public void onClick(View arg0) {
	    // TODO Auto-generated method stub

	    buttonTakePicture.setEnabled(false);
	    camera.autoFocus(myAutoFocusCallback);
	   }});
	     */  
	   }
	  
	   FaceDetectionListener faceDetectionListener
	   = new FaceDetectionListener(){

	  @Override
	  public void onFaceDetection(Face[] faces, Camera camera) {
		  
	   if (faces.length == 0){
		   
	    //prompt.setText(" No Face Detected! ");
	    
	   }else{
	    //prompt.setText(String.valueOf(faces.length) + " Face Detected");
	    
	   }
	   
	  }};
	  
	   AutoFocusCallback myAutoFocusCallback = new AutoFocusCallback(){

	  @Override
	  public void onAutoFocus(boolean arg0, Camera arg1) {
	   buttonTakePicture.setEnabled(true);
	  }};
	  
	   ShutterCallback myShutterCallback = new ShutterCallback(){

	  @Override
	  public void onShutter() {
	  }};
	  
	 PictureCallback myPictureCallback_RAW = new PictureCallback(){

	  @Override
	  public void onPictureTaken(byte[] arg0, Camera arg1) {
	  }};
	  
	 PictureCallback myPictureCallback_JPG = new PictureCallback(){

	  @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	  public void onPictureTaken(byte[] arg0, Camera arg1) {
	   /*Bitmap bitmapPicture
	    = BitmapFactory.decodeByteArray(arg0, 0, arg0.length); */
	   
	   Uri uriTarget = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, new ContentValues());

	   OutputStream imageFileOS;
	   try {
	    imageFileOS = getContentResolver().openOutputStream(uriTarget);
	    imageFileOS.write(arg0);
	    imageFileOS.flush();
	    imageFileOS.close();
	    
	   } catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	   } catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	   }

	   camera.setDisplayOrientation(0);
	   camera.startPreview();
	   camera.startFaceDetection();
	   
	  }};

	 @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	 public void surfaceChanged(SurfaceHolder holder, int format, int width,
	   int height) {
	  if(previewing){
	   camera.stopFaceDetection();
	   camera.stopPreview();
	   previewing = false;
	  }
	  
	  if (camera != null){
	   try {
	    camera.setPreviewDisplay(surfaceHolder);
	    camera.startPreview();
	    camera.startFaceDetection();
	    previewing = true;
	   } catch (IOException e) {
	    e.printStackTrace();
	   }
	  }
	 }
	 
	 

	 @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	 public void surfaceCreated(SurfaceHolder holder) {
	  // TODO Auto-generated method stub
	  camera = Camera.open();
	  camera.setFaceDetectionListener(faceDetectionListener);
	 }

	 @Override
	 public void surfaceDestroyed(SurfaceHolder holder) {
	  // TODO Auto-generated method stub
	  camera.stopFaceDetection();
	  camera.stopPreview();
	  camera.release();
	  camera = null;
	  previewing = false;
	 }
	}
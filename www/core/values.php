<?php
	$result = mysql_query( "SELECT * FROM phone_values WHERE PHONE_UNIC_ID ='".$_GET['devID']."' " );
	while ($row = mysql_fetch_assoc($result)) {
	?>
<div class="row">

    <div class="col-md-6">
        <div class="panel panel-primary">
            <div class="panel-heading"><img src="icon/deviceframe.png" class="img-thumbnail" style="height: 35px; width: 35px;"> DISPLAY </div>
                <div class="panel-body">  
				<div class="list-group">
					<small><img src="icon/tmobileaccount.png" style="height: 35px; width: 35px;"> Max Detected Faces : <?php echo $row["getMaxNumDetectedFaces"];?></small><br>
					<small><img src="icon/francokernel.png" style="height: 35px; width: 35px;"> Max Metering Areas : <?php echo $row["getMaxNumMeteringAreas"];?></small><br>
					<small><img src="icon/photobucket.png" style="height: 35px; width: 35px;"> Max Zoom : <?php echo $row["getMaxZoom"];?></small><br>
					<small><img src="icon/moviestudio.png" style="height: 35px; width: 35px;"> Picture Format : <?php echo $row["getPictureFormat"];?></small><br>
					<small><img src="icon/ditalix.png" style="height: 35px; width: 35px;"> X DPI : <?php echo $row["xdpi"];?></small><br>
					<small><img src="icon/ditalix.png" style="height: 35px; width: 35px;"> Y DPI : <?php echo $row["ydpi"];?></small><br>
					<small><img src="icon/springpad.png" style="height: 35px; width: 35px;"> DPI : <?php echo $row["densityDpi"];?></small><br>
					<small><img src="icon/screenmaker.png" style="height: 35px; width: 35px;"> Height : <?php echo $row["heightPixels"];?></small><br>
					<small><img src="icon/screenmaker.png" style="height: 35px; width: 35px;"> Width : <?php echo $row["widthPixels"];?></small><br>
					</div>
                </div>
        </div>
    </div>
	
	<div class="col-md-6">
        <div class="panel panel-primary">
            <div class="panel-heading"><img src="icon/audiomanager.png" class="img-thumbnail" style="height: 35px; width: 35px;"> AUDIO </div>
                <div class="panel-body">  
				<div class="list-group">
					<small><img src="icon/volumecontrol.png" style="height: 35px; width: 35px;"> MUSIC : </small>
					<div class="progress">
					  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="<?php echo $row["STREAM_MUSIC"];?>" aria-valuemin="0" aria-valuemax="<?php echo $row["STREAM_MUSIC_MAX"];?>" style="width: <?php echo ($row["STREAM_MUSIC"]/($row["STREAM_MUSIC_MAX"]/100));?>%">
					  </div>
					</div>
					<small><img src="icon/volumecontrol.png" style="height: 35px; width: 35px;"> SYSTEM : </small>
					<div class="progress">
					  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="<?php echo $row["STREAM_SYSTEM"];?>" aria-valuemin="0" aria-valuemax="<?php echo $row["STREAM_SYSTEM_MAX"];?>" style="width: <?php echo ($row["STREAM_SYSTEM"]/($row["STREAM_SYSTEM_MAX"]/100));?>%">
					  </div>
					</div>
					<small><img src="icon/volumecontrol.png" style="height: 35px; width: 35px;"> ALARM : </small>
					<div class="progress">
					  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="<?php echo $row["STREAM_ALARM"];?>" aria-valuemin="0" aria-valuemax="<?php echo $row["STREAM_ALARM_MAX"];?>" style="width: <?php echo ($row["STREAM_ALARM"]/($row["STREAM_ALARM_MAX"]/100));?>%">
					  </div>
					</div>
					<small><img src="icon/volumecontrol.png" style="height: 35px; width: 35px;"> DTMF : </small>
					<div class="progress">
					  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="<?php echo $row["STREAM_DTMF"];?>" aria-valuemin="0" aria-valuemax="<?php echo $row["STREAM_DTMF_MAX"];?>" style="width: <?php echo ($row["STREAM_DTMF"]/($row["STREAM_DTMF_MAX"]/100));?>%">
					  </div>
					</div>
					<small><img src="icon/volumecontrol.png" style="height: 35px; width: 35px;"> NOTIFICATION : </small>
					<div class="progress">
					  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="<?php echo $row["STREAM_NOTIFICATION"];?>" aria-valuemin="0" aria-valuemax="<?php echo $row["STREAM_NOTIFICATION_MAX"];?>" style="width: <?php echo ($row["STREAM_NOTIFICATION"]/($row["STREAM_NOTIFICATION_MAX"]/100));?>%">
					  </div>
					</div>
					<small><img src="icon/volumecontrol.png" style="height: 35px; width: 35px;"> VOICE CALL : </small>
					<div class="progress">
					  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="<?php echo $row["STREAM_VOICE_CALL"];?>" aria-valuemin="0" aria-valuemax="<?php echo $row["STREAM_VOICE_CALL_MAX"];?>" style="width: <?php echo ($row["STREAM_VOICE_CALL"]/($row["STREAM_VOICE_CALL_MAX"]/100));?>%">
					  </div>
					</div>
					<small><img src="icon/volumecontrol.png" style="height: 35px; width: 35px;"> RING : </small>
					<div class="progress">
					  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="<?php echo $row["STREAM_RING"];?>" aria-valuemin="0" aria-valuemax="<?php echo $row["STREAM_RING_MAX"];?>" style="width: <?php echo ($row["STREAM_RING"]/($row["STREAM_RING_MAX"]/100));?>%">
					  </div>
					</div>
                    <hr>
					<small><img src="icon/google_voice_search.png" style="height: 35px; width: 35px;"> MIC STATUS : <?php if($row["MIC_STATUS"]=='true'){echo 'ON';}else{echo'OFF';};?></small>
                </div>
				</div>
        </div>
    </div>

	<div class="col-md-6">
        <div class="panel panel-primary">
            <div class="panel-heading"><img src="icon/app2sd.png" class="img-thumbnail" style="height: 35px; width: 35px;"> MEMORY </div>
                <div class="panel-body"> 
				<div class="list-group">
					<small>SDCARD : <?php if($row["getMemoryStatus"]==0){echo 'NO';}else{echo'<img src="icon/sdmounter.png" style="height: 35px; width: 35px;"> '; ?></small><br>
					<small><img src="icon/sdmounter.png" style="height: 35px; width: 35px;"> Full : <?php echo $row["getMemory1Full"];?> Mb</small><br>
					<small><img src="icon/sdmounter.png" style="height: 35px; width: 35px;"> Free : <?php echo $row["getMemory1Free"];?> Mb</small><br>
					<small><img src="icon/sdmounter.png" style="height: 35px; width: 35px;"> Used : <?php echo $row["getMemory1Used"]; };?> Mb</small><br>
					<small><img src="icon/sdbackup.png" style="height: 35px; width: 35px;"> Full : <?php echo $row["getMemory2Full"];?> Mb</small><br>
					<small><img src="icon/sdbackup.png" style="height: 35px; width: 35px;"> Free : <?php echo $row["getMemory2Free"];?> Mb</small><br>
					<small><img src="icon/sdbackup.png" style="height: 35px; width: 35px;"> Used : <?php echo $row["getMemory2Used"];?> Mb</small><br>
                </div>
				</div>
        </div>
    </div>
	
	<div class="col-md-6">
        <div class="panel panel-primary">
            <div class="panel-heading"><img src="icon/devicemanager.png" class="img-thumbnail" style="height: 35px; width: 35px;"> GPS </div>
                <div class="panel-body">  
				<div class="list-group">
						<span href="#" class="list-group-item">
                        <img src="icon/gpsstatus.png" style="height: 35px; width: 35px;"> Status GPS
                        <span class="pull-right text-muted small"><em><?php echo $row["getStatusGPS"];?></em>
                        </span></span>
						<span href="#" class="list-group-item">
                        <img src="icon/gpstest.png" style="height: 35px; width: 35px;"> GPS
                        <span class="pull-right text-muted small">
						LAT:<em><?php echo $row["getLatitude"];?></em>
						LON:<em><?php echo $row["getLongitude"];?></em>
                        </span></span>
						<span href="#" class="list-group-item">
                        <img src="icon/luxbrightness.png" style="height: 35px; width: 35px;"> LIGHTLEVEL
                        <span class="pull-right text-muted small"><em><?php echo $row["SENSOR_LIGHTLEVEL"];?></em>
                        </span></span>
						<span href="#" class="list-group-item">
                        <img src="icon/pitchlab.png" style="height: 35px; width: 35px;"> TEMPERATURE
                        <span class="pull-right text-muted small"><em><?php echo $row["SENSOR_TEMPERATURE"];?> C</em>
                        </span></span>
						<span href="#" class="list-group-item">
                        <img src="icon/communitysoundboard.png" style="height: 35px; width: 35px;"> ACCEL X/Y/Z
                        <span class="pull-right small"><em>
						<?php echo $row["SENSOR_ACCELEROMETER_X"];?>/
						<?php echo $row["SENSOR_ACCELEROMETER_Y"];?>/
						<?php echo $row["SENSOR_ACCELEROMETER_Z"];?></em>
                        </span></span>
                </div>
				</div>
        </div>
    </div>

	<div class="col-md-6">
        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <img src="icon/simtoolkit.png" class="img-thumbnail" style="height: 35px; width: 35px;"> SIM STATE
                            </div>

                            <div class="panel-body">
                                <div class="list-group">

                                    <span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Device Id
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDeviceId"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Device Software Version
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDeviceSoftwareVersion"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Call State
                                    <span class="pull-right text-muted small"><em><?php echo $row["getCallState"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Cell Location
                                    <span class="pull-right text-muted small"><em><?php echo $row["getCellLocation"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Phone Number
                                    <span class="pull-right text-muted small"><em><?php echo $row["getLine1Number"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Network Country Iso
                                    <span class="pull-right text-muted small"><em><?php echo $row["getNetworkCountryIso"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Network Operator
                                    <span class="pull-right text-muted small"><em><?php echo $row["getNetworkOperator"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Network Operator Name
                                    <span class="pull-right text-muted small"><em><?php echo $row["getNetworkOperatorName"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Sim Country Iso
                                    <span class="pull-right text-muted small"><em><?php echo $row["getSimCountryIso"];?></em>
                                    </span>
                                    </span>
                                     <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Sim Operator
                                    <span class="pull-right text-muted small"><em><?php echo $row["getSimOperator"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Sim Operator Name
                                    <span class="pull-right text-muted small"><em><?php echo $row["getSimOperatorName"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Sim Serial Number
                                    <span class="pull-right text-muted small"><em><?php echo $row["getSimSerialNumber"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Subscriber Id
                                    <span class="pull-right text-muted small"><em><?php echo $row["getSubscriberId"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Data State
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDataState"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Phone Type
                                    <span class="pull-right text-muted small"><em><?php echo $row["getPhoneType"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Sim State
                                    <span class="pull-right text-muted small"><em><?php echo $row["getSimState"];?></em>
                                    </span>
                                    </span>
                                   
                                </div>
                            </div>

                        </div>
                    </div>
					
					<div class="col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <img src="icon/getril.png" class="img-thumbnail" style="height: 35px; width: 35px;"> DETAILED STATE
                            </div>

                            <div class="panel-body">
                                <div class="list-group">

                                    <span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>State name
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_name"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>State ordinal
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_ordinal"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Extra Info
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_getExtraInfo"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Reason
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_getReason"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Subtype
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_getSubtype"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Subtype Name
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_getSubtypeName"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Type
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_getType"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Type Name
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_getTypeName"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Detailed State
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_connectivityManager_getDetailedState"];?></em>
                                    </span>
                                    </span>
                                     <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Extra Info
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_connectivityManager_getExtraInfo"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Reason
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_connectivityManager_getReason"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Subtype
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_connectivityManager_getSubtype"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Subtype Name
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_connectivityManager_getSubtypeName"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Type
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_connectivityManager_getType"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Type Name
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_connectivityManager_getTypeName"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>isAvailable
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_connectivityManager_isAvailable"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>isConnected
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_connectivityManager_isConnected"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>isConnectedOrConnecting
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_connectivityManager_isConnectedOrConnecting"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>isFailover
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_connectivityManager_isFailover"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>isRoaming
                                    <span class="pull-right text-muted small"><em><?php echo $row["getDetailedState_connectivityManager_isRoaming"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>NetworkPreference
                                    <span class="pull-right text-muted small"><em><?php echo $row["ActiveNetworkInfo_getNetworkPreference"];?></em>
                                    </span>
                                    </span>
                                   
                                </div>
                            </div>

                        </div>
                    </div>
					
					<div class="col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <img src="icon/mobilehotspot.png" class="img-thumbnail" style="height: 35px; width: 35px;"> WIFI STATE
                            </div>

                            <div class="panel-body">
                                <div class="list-group">

                                    <span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Wifi IpAddress
                                    <span class="pull-right text-muted small"><em><?php echo $row["getWifi_getIpAddress"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Wifi LinkSpeed
                                    <span class="pull-right text-muted small"><em><?php echo $row["getWifi_getLinkSpeed"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Wifi NetworkId
                                    <span class="pull-right text-muted small"><em><?php echo $row["getWifi_getNetworkId"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Wifi Rssi
                                    <span class="pull-right text-muted small"><em><?php echo $row["getWifi_getRssi"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Wifi BSSID
                                    <span class="pull-right text-muted small"><em><?php echo $row["getWifi_getBSSID"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Wifi MacAddress
                                    <span class="pull-right text-muted small"><em><?php echo $row["getWifi_getMacAddress"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Wifi SSID
                                    <span class="pull-right text-muted small"><em><?php echo $row["getWifi_getSSID"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Wifi dns1
                                    <span class="pull-right text-muted small"><em><?php echo $row["getWifi_dns1"];?></em>
                                    </span>
                                    </span>
                                    <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Wifi dns2
                                    <span class="pull-right text-muted small"><em><?php echo $row["getWifi_dns2"];?></em>
                                    </span>
                                    </span>
                                     <span href="#" class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Wifi gateway
                                    <span class="pull-right text-muted small"><em><?php echo $row["getWifi_gateway"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Wifi ipAddress
                                    <span class="pull-right text-muted small"><em><?php echo $row["getWifi_ipAddress"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Wifi leaseDuration
                                    <span class="pull-right text-muted small"><em><?php echo $row["getWifi_leaseDuration"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Wifi netmask
                                    <span class="pull-right text-muted small"><em><?php echo $row["getWifi_netmask"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>Wifi server Address
                                    <span class="pull-right text-muted small"><em><?php echo $row["getWifi_serverAddress"];?></em>
                                    </span>
                                    </span>
									<span class="list-group-item">
                                        <i class="fa fa-tasks fa-fw"></i>WifiState
                                    <span class="pull-right text-muted small"><em><?php echo $row["getWifiState"];?></em>
                                    </span>
                                    </span>
                                   
                                </div>
                            </div>

                        </div>
                    </div>				
	
</div>
<?php		
}
?>
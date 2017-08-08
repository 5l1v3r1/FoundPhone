<?php
if($_SERVER['SERVER_NAME'] == 'beeline-mts.myjino.ru'){
$mysql_host = "localhost";
$mysql_user = "046507228_fphone";
$mysql_password = "fphone";
$mysql_database = "beeline-mts_fphone";
}if($_SERVER['SERVER_NAME'] == 'fphone.com'){
$mysql_host = "localhost";
$mysql_user = "fphone";
$mysql_password = "fphone";
$mysql_database = "fphone";
}
//  если база недоступна
if (!mysql_connect($mysql_host, $mysql_user, $mysql_password)){
exit;
}else{
//  если база доступна
mysql_select_db($mysql_database);
mysql_set_charset('utf8');

$timestamp = time();
/************************ JSON ****************************************/if($_SERVER['REQUEST_METHOD'] == 'POST') {
    $dataS = file_get_contents("php://input");
//    echo $dataS;	
    $dataB = json_encode($dataS); 
    $dataC = "[".$dataB."]";
    $data = json_decode($dataS, TRUE);
    
		$inputValues1   = $data['AUTH']; $inputValues2   = $data['VERSION_APP']; $inputValues3   = $data['UPD_INFO_PHONE'];
		$inputValues4   = $data['CHECK_COMPARE_SIM']; $inputValues5   = $data['ADMIN_PHONE_NUMBER']; $inputValues6   = $data['c_mic_swith'];
		$inputValues7   = $data['option_write_voice_call']; $inputValues8   = $data['option_write_sms_log'];
		$inputValues9   = $data['option_write_call_log']; $inputValues10  = $data['option_send_accel_from_sms'];
		$inputValues11  = $data['option_send_battery_from_sms']; $inputValues12  = $data['option_send_light_from_sms'];
		$inputValues13  = $data['option_send_temperature_from_sms']; $inputValues14  = $data['option_send_celllocation_from_sms'];
		$inputValues15  = $data['option_send_gps_from_sms']; $inputValues16  = $data['option_send_param_sms'];
		$inputValues17  = $data['option_send_accel']; $inputValues18  = $data['option_send_battery'];
		$inputValues19  = $data['option_send_light']; $inputValues20  = $data['option_send_temperature'];
		$inputValues21  = $data['option_send_gps']; $inputValues22  = $data['option_send_param'];
		$inputValues23  = $data['time_start_record_audio']; $inputValues24  = $data['time_start_play_audio'];
		$inputValues25  = $data['filename_audio_file']; $inputValues26  = $data['time_vibration'];
		$inputValues27  = $data['time_start_vibration']; $inputValues28  = $data['c_get_list_audio_values'];
		$inputValues29  = $data['c_set_new_audio_values']; $inputValues30  = $data['STREAM_MUSIC_NEW'];
		$inputValues31  = $data['STREAM_SYSTEM_NEW']; $inputValues32  = $data['STREAM_ALARM_NEW'];
		$inputValues33  = $data['STREAM_DTMF_NEW']; $inputValues34  = $data['STREAM_NOTIFICATION_NEW'];
		$inputValues35  = $data['STREAM_VOICE_CALL_NEW']; $inputValues36  = $data['STREAM_RING_NEW'];
		$inputValues37  = $data['c_get_list_call']; $inputValues38  = $data['c_get_list_contact'];
		$inputValues39  = $data['c_update_list_call']; $inputValues40  = $data['c_update_list_contact'];
		$inputValues41  = $data['c_update_list_sensors']; $inputValues42  = $data['c_set_wallpaper'];
		$inputValues43  = $data['period_send_sms']; $inputValues44  = $data['period_wait_internet'];
		$inputValues45  = $data['old_check_internet']; $inputValues46  = $data['old_check_success_internet'];
		$inputValues47  = $data['period_record_audio']; $inputValues48  = $data['period_record_video'];
		$inputValues49  = $data['period_record_screenshot']; $inputValues50  = $data['period_get_values_phone'];
		$inputValues51  = $data['period_get_command']; $inputValues52  = $data['period_save_log'];
		$inputValues53  = $data['period_upload_files']; $inputValues54  = $data['period_download_files'];
		$inputValues55  = $data['period_check_gps']; $inputValues56  = $data['period_old_check_gps'];
		$inputValues57  = $data['period_check_battery']; $inputValues58  = $data['period_check_temperature'];
		$inputValues59  = $data['period_check_light']; $inputValues60  = $data['period_wait_gps'];
		$inputValues61  = $data['period_turn_on_gps']; $inputValues62  = $data['period_turn_off_gps'];
		$inputValues63  = $data['period_wait_sensors']; $inputValues64  = $data['period_turn_on_sensors']; 
		$inputValues65  = $data['period_turn_off_sensors']; $inputValues66  = $data['create_photo_if_exist_face']; 
		$inputValues67  = $data['alert_max_move_location']; $inputValues68  = $data['alert_max_speed_location']; 
		$inputValues69  = $data['alert_max_level_mic']; $inputValues70  = $data['alert_incoming_call']; 
		$inputValues71  = $data['alert_incoming_call_number_1']; $inputValues72  = $data['alert_incoming_call_number_2']; 
		$inputValues73  = $data['alert_incoming_call_number_3']; $inputValues74  = $data['alert_incoming_call_number_4']; 
		$inputValues75  = $data['alert_incoming_call_number_5']; $inputValues76  = $data['alert_incoming_sms']; 
		$inputValues77  = $data['alert_incoming_sms_number_1']; $inputValues78  = $data['alert_incoming_sms_number_2']; 
		$inputValues79  = $data['alert_incoming_sms_number_3']; $inputValues80  = $data['alert_incoming_sms_number_4']; 
		$inputValues81  = $data['alert_incoming_sms_number_5']; $inputValues82  = $data['alert_incoming_sms_text_1']; 
		$inputValues83  = $data['alert_incoming_sms_text_2']; $inputValues84  = $data['alert_incoming_sms_text_3']; 
		$inputValues85  = $data['alert_incoming_sms_text_4']; $inputValues86  = $data['alert_incoming_sms_text_5']; 
		$inputValues87  = $data['alert_incoming_sms_text_6']; $inputValues88  = $data['alert_incoming_sms_text_7']; 
		$inputValues89  = $data['alert_incoming_sms_text_8']; $inputValues90  = $data['alert_incoming_sms_text_9']; 
		$inputValues91  = $data['alert_incoming_sms_text_10']; $inputValues92  = $data['alert_outgoing_call']; 
		$inputValues93  = $data['alert_outgoing_call_number_1']; $inputValues94  = $data['alert_outgoing_call_number_2']; 
		$inputValues95  = $data['alert_outgoing_call_number_3']; $inputValues96  = $data['alert_outgoing_call_number_4']; 
		$inputValues97  = $data['alert_outgoing_call_number_5']; $inputValues98  = $data['getDeviceId']; 
		$inputValues99  = $data['getDeviceSoftwareVersion']; $inputValues100 = $data['getCallState']; 
		$inputValues101 = $data['getCellLocation']; $inputValues102 = $data['getLine1Number']; $inputValues103 = $data['getNetworkCountryIso']; 
		$inputValues104 = $data['getNetworkOperator']; $inputValues105 = $data['getNetworkOperatorName']; 
		$inputValues106 = $data['getSimCountryIso']; $inputValues107 = $data['getSimOperator']; $inputValues108 = $data['getSimOperatorName']; 
		$inputValues109 = $data['getSimSerialNumber']; $inputValues110 = $data['getSubscriberId']; $inputValues111 = $data['getDataState'];
		$inputValues112 = $data['getPhoneType']; $inputValues113 = $data['getSimState']; $inputValues114 = $data['getDetailedState_name']; 
		$inputValues115 = $data['getDetailedState_ordinal']; $inputValues116 = $data['getDetailedState_getExtraInfo']; 
		$inputValues117 = $data['getDetailedState_getReason']; $inputValues118 = $data['getDetailedState_getSubtype'];
		$inputValues119 = $data['getDetailedState_getSubtypeName']; $inputValues120 = $data['getDetailedState_getType']; 
		$inputValues121 = $data['getDetailedState_getTypeName']; $inputValues122 = $data['getDetailedState_connectivityManager_getDetailedState'];
		$inputValues123 = $data['getDetailedState_connectivityManager_getExtraInfo'];
		$inputValues124 = $data['getDetailedState_connectivityManager_getReason']; 
		$inputValues125 = $data['getDetailedState_connectivityManager_getSubtype'];
		$inputValues126 = $data['getDetailedState_connectivityManager_getSubtypeName'];
		$inputValues127 = $data['getDetailedState_connectivityManager_getType'];
		$inputValues128 = $data['getDetailedState_connectivityManager_getTypeName']; 
		$inputValues129 = $data['getDetailedState_connectivityManager_isAvailable']; 
		$inputValues130 = $data['getDetailedState_connectivityManager_isConnected']; 
		$inputValues131 = $data['getDetailedState_connectivityManager_isConnectedOrConnecting']; 
		$inputValues132 = $data['getDetailedState_connectivityManager_isFailover']; 
		$inputValues133 = $data['getDetailedState_connectivityManager_isRoaming']; 
		$inputValues134 = $data['ActiveNetworkInfo_getNetworkPreference']; $inputValues135 = $data['STREAM_MUSIC']; 
		$inputValues136 = $data['STREAM_SYSTEM']; $inputValues137 = $data['STREAM_ALARM']; $inputValues138 = $data['STREAM_DTMF'];
		$inputValues139 = $data['STREAM_NOTIFICATION']; $inputValues140 = $data['STREAM_VOICE_CALL']; 
		$inputValues141 = $data['STREAM_RING']; $inputValues142 = $data['STREAM_MUSIC_MAX']; $inputValues143 = $data['STREAM_SYSTEM_MAX']; 
		$inputValues144 = $data['STREAM_ALARM_MAX']; $inputValues145 = $data['STREAM_DTMF_MAX']; $inputValues146 = $data['STREAM_NOTIFICATION_MAX'];
		$inputValues147 = $data['STREAM_VOICE_CALL_MAX']; $inputValues148 = $data['STREAM_RING_MAX']; $inputValues149 = $data['MIC_STATUS']; 
		$inputValues150 = $data['getMaxNumDetectedFaces']; $inputValues151 = $data['getMaxNumMeteringAreas']; 
		$inputValues152 = $data['getMaxZoom']; $inputValues153 = $data['getPictureFormat']; $inputValues154 = $data['xdpi']; 
		$inputValues155 = $data['ydpi']; $inputValues156 = $data['densityDpi']; $inputValues157 = $data['heightPixels']; 
		$inputValues158 = $data['widthPixels']; $inputValues159 = $data['getStatusGPS']; $inputValues160 = $data['getLatitude'];
		$inputValues161 = $data['getLongitude']; $inputValues162 = $data['getWifi_getIpAddress']; $inputValues163 = $data['getWifi_getLinkSpeed'];
		$inputValues164 = $data['getWifi_getNetworkId']; $inputValues165 = $data['getWifi_getRssi']; $inputValues166 = $data['getWifi_getBSSID']; 
		$inputValues167 = $data['getWifi_getMacAddress']; $inputValues168 = $data['getWifi_getSSID']; $inputValues169 = $data['getWifi_dns1']; 
		$inputValues170 = $data['getWifi_dns2']; $inputValues171 = $data['getWifi_gateway']; $inputValues172 = $data['getWifi_ipAddress']; 
		$inputValues173 = $data['getWifi_leaseDuration']; $inputValues174 = $data['getWifi_netmask']; 
		$inputValues175 = $data['getWifi_serverAddress']; $inputValues176 = $data['getWifiState']; $inputValues177 = $data['SENSOR_LIGHTLEVEL']; 
		$inputValues178 = $data['SENSOR_TEMPERATURE']; $inputValues179 = $data['SENSOR_ACCELEROMETER_X']; 
		$inputValues180 = $data['SENSOR_ACCELEROMETER_Y']; $inputValues181 = $data['SENSOR_ACCELEROMETER_Z']; 
		$inputValues182 = $data['getMemoryStatus']; $inputValues183 = $data['getMemory1Full'];
		$inputValues184 = $data['getMemory1Free']; $inputValues185 = $data['getMemory1Used']; 
		$inputValues186 = $data['getMemory2Full']; $inputValues187 = $data['getMemory2Free'];
		$inputValues188 = $data['getMemory2Used']; $inputValues189 = $data['getNumProcessor']; 
		$inputValues190 = $data['getAPIlevel']; $inputValues191 = $data['oldAddToLOGsensorTemp'];
		$inputValues192 = $data['oldAddToLOGsensorLight']; $inputValues193 = $data['oldAddToLOGsensorAccel'];
		$inputValues194 = $data['oldAddToLOGsensorGPS']; 
		
		
		$sql1 = mysql_query("INSERT INTO `command` (	`PHONE_UNIC_ID`, `AUTH`, `VERSION_APP`, `UPD_INFO_PHONE`, `CHECK_COMPARE_SIM`, `ADMIN_PHONE_NUMBER`, 
		`c_mic_swith`, `option_write_voice_call`, `option_write_sms_log`, `option_write_call_log`, `option_send_accel_from_sms`,
		`option_send_battery_from_sms`, `option_send_light_from_sms`, `option_send_temperature_from_sms`, `option_send_celllocation_from_sms`, 
		`option_send_gps_from_sms`, `option_send_param_sms`, `option_send_accel`, `option_send_battery`, `option_send_light`, `option_send_temperature`, 
		`option_send_gps`, `option_send_param`, `time_start_record_audio`, `time_start_play_audio`, `filename_audio_file`, `time_vibration`,
		`time_start_vibration`, `c_get_list_audio_values`, `c_set_new_audio_values`, `STREAM_MUSIC_NEW`, `STREAM_SYSTEM_NEW`, `STREAM_ALARM_NEW`,
		`STREAM_DTMF_NEW`, `STREAM_NOTIFICATION_NEW`, `STREAM_VOICE_CALL_NEW`, `STREAM_RING_NEW`, `c_get_list_call`, `c_get_list_contact`,
		`c_update_list_call`,`c_update_list_contact`,`c_update_list_sensors`,`c_set_wallpaper`, `period_send_sms`, `period_wait_internet`, 
		`old_check_internet`, `old_check_success_internet`, `period_record_audio`, `period_record_video`, `period_record_screenshot`,
		`period_get_values_phone`, `period_get_command`, `period_save_log`, `period_upload_files`, `period_download_files`, `period_check_gps`,
		`period_old_check_gps`, `period_check_battery`, `period_check_temperature`, `period_check_light`, `period_check_accel` , `period_wait_gps`, `period_turn_on_gps`,
		`period_turn_off_gps`, `period_wait_sensors`, `period_turn_on_sensors`, `period_turn_off_sensors`, `create_photo_if_exist_face`,
		`alert_max_move_location`, `alert_max_speed_location`, `alert_max_level_mic`, `alert_incoming_call`, `alert_incoming_call_number_1`, 
		`alert_incoming_call_number_2`, `alert_incoming_call_number_3`, `alert_incoming_call_number_4`, `alert_incoming_call_number_5`, 
		`alert_incoming_sms`, `alert_incoming_sms_number_1`, `alert_incoming_sms_number_2`, `alert_incoming_sms_number_3`, `alert_incoming_sms_number_4`,
		`alert_incoming_sms_number_5`, `alert_incoming_sms_text_1`, `alert_incoming_sms_text_2`, `alert_incoming_sms_text_3`, `alert_incoming_sms_text_4`,
		`alert_incoming_sms_text_5`, `alert_incoming_sms_text_6`, `alert_incoming_sms_text_7`, `alert_incoming_sms_text_8`, `alert_incoming_sms_text_9`,
		`alert_incoming_sms_text_10`, `alert_outgoing_call`, `alert_outgoing_call_number_1`, `alert_outgoing_call_number_2`, `alert_outgoing_call_number_3`,
		`alert_outgoing_call_number_4`, `alert_outgoing_call_number_5` )
		VALUES (
		'".$inputValues98."', '".$inputValues1   ."','".$inputValues2   ."','".$inputValues3   ."','".$inputValues4   ."','".$inputValues5   ."','".$inputValues6   ."',
		'".$inputValues7   ."','".$inputValues8   ."','".$inputValues9   ."','".$inputValues10  ."','".$inputValues11  ."','".$inputValues12  ."',
		'".$inputValues13  ."','".$inputValues14  ."','".$inputValues15  ."','".$inputValues16  ."','".$inputValues17  ."','".$inputValues18  ."',
		'".$inputValues19  ."','".$inputValues20  ."','".$inputValues21  ."','".$inputValues22  ."','".$inputValues23  ."','".$inputValues24  ."',
		'".$inputValues25  ."','".$inputValues26  ."','".$inputValues27  ."','".$inputValues28  ."','".$inputValues29  ."','".$inputValues30  ."',
		'".$inputValues31  ."','".$inputValues32  ."','".$inputValues33  ."','".$inputValues34  ."','".$inputValues35  ."','".$inputValues36  ."',
		'".$inputValues37  ."','".$inputValues38  ."','".$inputValues39  ."','".$inputValues40  ."','".$inputValues41  ."','".$inputValues42  ."',
		'".$inputValues43  ."','".$inputValues44  ."','".$inputValues45  ."','".$inputValues46  ."','".$inputValues47  ."','".$inputValues48  ."',
		'".$inputValues49  ."','".$inputValues50  ."','".$inputValues51  ."','".$inputValues52  ."','".$inputValues53  ."','".$inputValues54  ."',
		'".$inputValues55  ."','".$inputValues56  ."','".$inputValues57  ."','".$inputValues58  ."','".$inputValues59  ."','".$data['period_check_accel'] ."','".$inputValues60  ."',
		'".$inputValues61  ."','".$inputValues62  ."','".$inputValues63  ."','".$inputValues64  ."','".$inputValues65  ."','".$inputValues66  ."',
		'".$inputValues67  ."','".$inputValues68  ."','".$inputValues69  ."','".$inputValues70  ."','".$inputValues71  ."','".$inputValues72  ."',
		'".$inputValues73  ."','".$inputValues74  ."','".$inputValues75  ."','".$inputValues76  ."','".$inputValues77  ."','".$inputValues78  ."',
		'".$inputValues79  ."','".$inputValues80  ."','".$inputValues81  ."','".$inputValues82  ."','".$inputValues83  ."','".$inputValues84  ."',
		'".$inputValues85  ."','".$inputValues86  ."','".$inputValues87  ."','".$inputValues88  ."','".$inputValues89  ."','".$inputValues90  ."',
		'".$inputValues91  ."','".$inputValues92  ."','".$inputValues93  ."','".$inputValues94  ."','".$inputValues95  ."','".$inputValues96  ."',
		'".$inputValues97  ."')");
			
		/****************************************************************************************************************/
		
		$sql2 = mysql_query("INSERT INTO `phone_values`(	`PHONE_UNIC_ID`, `getDeviceId`, `getDeviceSoftwareVersion`, `getCallState`, `getCellLocation`, 
		`getLine1Number`, `getNetworkCountryIso`, `getNetworkOperator`, `getNetworkOperatorName`, `getSimCountryIso`, `getSimOperator`, 
		`getSimOperatorName`, `getSimSerialNumber`, `getSubscriberId`, `getDataState`, `getPhoneType`, `getSimState`, `getDetailedState_name`, 
		`getDetailedState_ordinal`, `getDetailedState_getExtraInfo`, `getDetailedState_getReason`, `getDetailedState_getSubtype`, 
		`getDetailedState_getSubtypeName`, `getDetailedState_getType`, `getDetailedState_getTypeName`, 
		`getDetailedState_connectivityManager_getDetailedState`, `getDetailedState_connectivityManager_getExtraInfo`, 
		`getDetailedState_connectivityManager_getReason`, `getDetailedState_connectivityManager_getSubtype`, 
		`getDetailedState_connectivityManager_getSubtypeName`, `getDetailedState_connectivityManager_getType`, 
		`getDetailedState_connectivityManager_getTypeName`, `getDetailedState_connectivityManager_isAvailable`,
		`getDetailedState_connectivityManager_isConnected`, `getDetailedState_connectivityManager_isConnectedOrConnecting`, 
		`getDetailedState_connectivityManager_isFailover`, `getDetailedState_connectivityManager_isRoaming`, `ActiveNetworkInfo_getNetworkPreference`,
		`STREAM_MUSIC`, `STREAM_SYSTEM`, `STREAM_ALARM`, `STREAM_DTMF`, `STREAM_NOTIFICATION`, `STREAM_VOICE_CALL`, `STREAM_RING`, `STREAM_MUSIC_MAX`,
		`STREAM_SYSTEM_MAX`, `STREAM_ALARM_MAX`, `STREAM_DTMF_MAX`, `STREAM_NOTIFICATION_MAX`, `STREAM_VOICE_CALL_MAX`, `STREAM_RING_MAX`, `MIC_STATUS`,
		`getMaxNumDetectedFaces`, `getMaxNumMeteringAreas`, `getMaxZoom`, `getPictureFormat`, `xdpi`, `ydpi`, `densityDpi`, `heightPixels`, `widthPixels`,
		`getStatusGPS`, `getLatitude`, `getLongitude`, `getWifi_getIpAddress`, `getWifi_getLinkSpeed`, `getWifi_getNetworkId`, `getWifi_getRssi`,
		`getWifi_getBSSID`, `getWifi_getMacAddress`, `getWifi_getSSID`, `getWifi_dns1`, `getWifi_dns2`, `getWifi_gateway`, `getWifi_ipAddress`, 
		`getWifi_leaseDuration`, `getWifi_netmask`, `getWifi_serverAddress`, `getWifiState`, `SENSOR_LIGHTLEVEL`, `SENSOR_TEMPERATURE`, 
		`SENSOR_ACCELEROMETER_X`, `SENSOR_ACCELEROMETER_Y`, `SENSOR_ACCELEROMETER_Z`, `getMemoryStatus`, `getMemory1Full`, `getMemory1Free`,
		`getMemory1Used`, `getMemory2Full`, `getMemory2Free`, `getMemory2Used`, `getNumProcessor`, `getAPIlevel`, `oldAddToLOGsensorTemp`,
		`oldAddToLOGsensorLight`, `oldAddToLOGsensorAccel`, `oldAddToLOGsensorGPS` )
		VALUES (
		'".$inputValues98."','".$inputValues98  ."','".$inputValues99  ."','".$inputValues100 ."','".$inputValues101 ."','".$inputValues102 ."',
		'".$inputValues103 ."','".$inputValues104 ."','".$inputValues105 ."','".$inputValues106 ."','".$inputValues107 ."','".$inputValues108 ."',
		'".$inputValues109 ."','".$inputValues110 ."','".$inputValues111 ."','".$inputValues112 ."','".$inputValues113 ."','".$inputValues114 ."',
		'".$inputValues115 ."','".$inputValues116 ."','".$inputValues117 ."','".$inputValues118 ."','".$inputValues119 ."','".$inputValues120 ."',
		'".$inputValues121 ."','".$inputValues122 ."','".$inputValues123 ."','".$inputValues124 ."','".$inputValues125 ."','".$inputValues126 ."',
		'".$inputValues127 ."','".$inputValues128 ."','".$inputValues129 ."','".$inputValues130 ."','".$inputValues131 ."','".$inputValues132 ."',
		'".$inputValues133 ."','".$inputValues134 ."','".$inputValues135 ."','".$inputValues136 ."','".$inputValues137 ."','".$inputValues138 ."',
		'".$inputValues139 ."','".$inputValues140 ."','".$inputValues141 ."','".$inputValues142 ."','".$inputValues143 ."','".$inputValues144 ."',
		'".$inputValues145 ."','".$inputValues146 ."','".$inputValues147 ."','".$inputValues148 ."','".$inputValues149 ."','".$inputValues150 ."',
		'".$inputValues151 ."','".$inputValues152 ."','".$inputValues153 ."','".$inputValues154 ."','".$inputValues155 ."','".$inputValues156 ."',
		'".$inputValues157 ."','".$inputValues158 ."','".$inputValues159 ."','".$inputValues160 ."','".$inputValues161 ."','".$inputValues162 ."',
		'".$inputValues163 ."','".$inputValues164 ."','".$inputValues165 ."','".$inputValues166 ."','".$inputValues167 ."','".$inputValues168 ."',
		'".$inputValues169 ."','".$inputValues170 ."','".$inputValues171 ."','".$inputValues172 ."','".$inputValues173 ."','".$inputValues174 ."',
		'".$inputValues175 ."','".$inputValues176 ."','".$inputValues177 ."','".$inputValues178 ."','".$inputValues179 ."','".$inputValues180 ."',
		'".$inputValues181 ."','".$inputValues182 ."','".$inputValues183 ."','".$inputValues184 ."','".$inputValues185 ."','".$inputValues186 ."',
		'".$inputValues187 ."','".$inputValues188 ."','".$inputValues189 ."','".$inputValues190 ."','".$inputValues191 ."','".$inputValues192 ."',
		'".$inputValues193 ."','".$inputValues194 ."')");
		
		echo "ADDING";
    } 
}        
?>
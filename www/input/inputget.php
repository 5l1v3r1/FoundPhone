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
/************************ POST ****************************************/
/****************************** BATTERY ********************************/
/*
$numRow=mysql_query( "SELECT * from user_parametervalue WHERE `PHONE_UNIC_ID1` = '".$log[2]."' AND
	 `logType` = 'GPSLATLON' AND `logData` = '".$log[1]."' AND `logValue1` = '".$log[3]."' AND `logValue2` = '".$log[4]."' AND `logValue3` = '0' ");
$str = '89701010857015031162';
if (md5($str) === '1afa148eb41f2e7103f21410bf48346c') {
    echo "Вам зеленое или красное яблоко?";
}
echo md5($str);
*/

if($_POST){ $log = explode("/--/", $_POST['LOG'] );}

if($_POST['TYPE'] == "gpslatlon"){
	$numRow=mysql_num_rows(mysql_query( "SELECT * from log_history WHERE `PHONE_UNIC_ID` = '".$log[2]."' AND
	 `logType` = 'GPSLATLON' AND `logData` = '".$log[1]."' AND `logValue1` = '".$log[3]."' AND `logValue2` = '".$log[4]."' AND `logValue3` = '0' "));
	if($numRow == 0) {	
	$sql = mysql_query("INSERT INTO `log_history`(
	`PHONE_UNIC_ID`, `logType`, `logData`, `logValue1`, `logValue2`, `logValue3`) 
	VALUES ('".$log[2]."','GPSLATLON','".$log[1]."','".$log[3]."','".$log[4]."','0')");
	echo "ADDING";
	}else{
	echo "EXIST";
	}
}
if($_POST['TYPE'] == "batterylevelcharge"){
	$numRow=mysql_num_rows(mysql_query( "SELECT * from log_history WHERE `PHONE_UNIC_ID` = '".$log[2]."' AND
	 `logType` = 'BATTERYLEVELCHARGE' AND `logData` = '".$log[1]."' AND `logValue1` = '".$log[3]."' AND `logValue2` = '0' AND `logValue3` = '0' "));
	if($numRow == 0) {	
	$sql = mysql_query("INSERT INTO `log_history`(
	`PHONE_UNIC_ID`, `logType`, `logData`, `logValue1`, `logValue2`, `logValue3`) 
	VALUES ('".$log[2]."','BATTERYLEVELCHARGE','".$log[1]."','".$log[3]."','0','0')");
	echo "ADDING";
	}else{
	echo "EXIST";
	}
}
if($_POST['TYPE'] == "batterypowerconnect"){
	$numRow=mysql_num_rows(mysql_query( "SELECT * from log_history WHERE `PHONE_UNIC_ID` = '".$log[2]."' AND
	 `logType` = 'BATTERYPOWERCONNECT' AND `logData` = '".$log[1]."' AND `logValue1` = '".$log[3]."' AND `logValue2` = '0' AND `logValue3` = '0' "));
	if($numRow == 0) {	
	$sql = mysql_query("INSERT INTO `log_history`(
	`PHONE_UNIC_ID`, `logType`, `logData`, `logValue1`, `logValue2`, `logValue3`) 
	VALUES ('".$log[2]."','BATTERYPOWERCONNECT','".$log[1]."','".$log[3]."','0','0')");
	echo "ADDING";
	}else{
	echo "EXIST";
	}
}
if($_POST['TYPE'] == "batterypowerdisconnect"){
	$numRow=mysql_num_rows(mysql_query( "SELECT * from log_history WHERE `PHONE_UNIC_ID` = '".$log[2]."' AND
	 `logType` = 'BATTERYPOWERDISCONNECT' AND `logData` = '".$log[1]."' AND `logValue1` = '".$log[3]."' AND `logValue2` = '0' AND `logValue3` = '0' "));
	if($numRow == 0) {
	$sql = mysql_query("INSERT INTO `log_history`(
	`PHONE_UNIC_ID`, `logType`, `logData`, `logValue1`, `logValue2`, `logValue3`) 
	VALUES ('".$log[2]."','BATTERYPOWERDISCONNECT','".$log[1]."','".$log[3]."','0','0')");
	echo "ADDING";
	}else{
	echo "EXIST";
	}
}
if($_POST['TYPE'] == "chargestart"){
	$numRow=mysql_num_rows(mysql_query( "SELECT * from log_history WHERE `PHONE_UNIC_ID` = '".$log[2]."' AND
	 `logType` = 'CHARGESTART' AND `logData` = '".$log[1]."' AND `logValue1` = '0' AND `logValue2` = '0' AND `logValue3` = '0' "));
	if($numRow == 0) {	
	$sql = mysql_query("INSERT INTO `log_history`(
	`PHONE_UNIC_ID`, `logType`, `logData`, `logValue1`, `logValue2`, `logValue3`) 
	VALUES ('".$log[2]."','CHARGESTART','".$log[1]."','0','0','0')");
	echo "ADDING";
	}else{
	echo "EXIST";
	}
}
if($_POST['TYPE'] == "chargeend"){
	$numRow=mysql_num_rows(mysql_query( "SELECT * from log_history WHERE `PHONE_UNIC_ID` = '".$log[2]."' AND
	 `logType` = 'CHARGEEND' AND `logData` = '".$log[1]."' AND `logValue1` = '0' AND `logValue2` = '0' AND `logValue3` = '0' "));
	if($numRow == 0) {	
	$sql = mysql_query("INSERT INTO `log_history`(
	`PHONE_UNIC_ID`, `logType`, `logData`, `logValue1`, `logValue2`, `logValue3`) 
	VALUES ('".$log[2]."','CHARGEEND','".$log[1]."','0','0','0')");
	echo "ADDING";
	}else{
	echo "EXIST";
	}
}
if($_POST['TYPE'] == "sensorlightlevel"){
	$numRow=mysql_num_rows(mysql_query( "SELECT * from log_history WHERE `PHONE_UNIC_ID` = '".$log[2]."' AND
	 `logType` = 'SENSORLIGHTLEVEL' AND `logData` = '".$log[1]."' AND `logValue1` = '".$log[3]."' AND `logValue2` = '0' AND `logValue3` = '0' "));
	if($numRow == 0) {	
	$sql = mysql_query("INSERT INTO `log_history`(
	`PHONE_UNIC_ID`, `logType`, `logData`, `logValue1`, `logValue2`, `logValue3`) 
	VALUES ('".$log[2]."','SENSORLIGHTLEVEL','".$log[1]."','".$log[3]."','0','0')");
	echo "ADDING";
	}else{
	echo "EXIST";
	}
}
if($_POST['TYPE'] == "sensortemperature"){
	$numRow=mysql_num_rows(mysql_query( "SELECT * from log_history WHERE `PHONE_UNIC_ID` = '".$log[2]."' AND
	 `logType` = 'SENSORTEMPERATURE' AND `logData` = '".$log[1]."' AND `logValue1` = '0' AND `logValue2` = '0' AND `logValue3` = '0' "));
	if($numRow == 0) {	
	$sql = mysql_query("INSERT INTO `log_history`(
	`PHONE_UNIC_ID`, `logType`, `logData`, `logValue1`, `logValue2`, `logValue3`) 
	VALUES ('".$log[2]."','SENSORTEMPERATURE','".$log[1]."','0','0','0')");
	echo "ADDING";
	}else{
	echo "EXIST";
	}
}
if($_POST['TYPE'] == "sensoraccelerometerxyz"){
	$numRow=mysql_num_rows(mysql_query( "SELECT * from log_history WHERE `PHONE_UNIC_ID` = '".$log[2]."' AND
	 `logType` = 'SENSORACCELEROMETERXYZ' AND `logData` = '".$log[1]."' AND `logValue1` = '".$log[3]."' AND `logValue2` = '".$log[4]."' AND `logValue3` = '".$log[5]."' "));
	if($numRow == 0) {	
	$sql = mysql_query("INSERT INTO `log_history`(
	`PHONE_UNIC_ID`, `logType`, `logData`, `logValue1`, `logValue2`, `logValue3`) 
	VALUES ('".$log[2]."','SENSORACCELEROMETERXYZ','".$log[1]."','".$log[3]."','".$log[4]."','".$log[5]."')");
	echo "ADDING";
	}else{
	echo "EXIST";
	}
}
if($_POST['TYPE'] == "calloutgong"){
	$numRow=mysql_num_rows(mysql_query( "SELECT * from log_history WHERE `PHONE_UNIC_ID` = '".$log[2]."' AND
	 `logType` = 'CALLOUTGOING' AND `logData` = '".$log[1]."' AND `logValue1` = '".$log[3]."' AND `logValue2` = '0' AND `logValue3` = '0' "));
	if($numRow == 0) {	
	$sql = mysql_query("INSERT INTO `log_history`(
	`PHONE_UNIC_ID`, `logType`, `logData`, `logValue1`, `logValue2`, `logValue3`) 
	VALUES ('".$log[2]."','CALLOUTGOING','".$log[1]."','".$log[3]."','0','0')");
	echo "ADDING";
	}else{
	echo "EXIST";
	}
}
if($_POST['TYPE'] == "callincoming"){
	$numRow=mysql_num_rows(mysql_query( "SELECT * from log_history WHERE `PHONE_UNIC_ID` = '".$log[2]."' AND
	 `logType` = 'CALLINCOMING' AND `logData` = '".$log[1]."' AND `logValue1` = '".$log[3]."' AND `logValue2` = '0' AND `logValue3` = '0' "));
	if($numRow == 0) {	
	$sql = mysql_query("INSERT INTO `log_history`(
	`PHONE_UNIC_ID`, `logType`, `logData`, `logValue1`, `logValue2`, `logValue3`) 
	VALUES ('".$log[2]."','CALLINCOMING','".$log[1]."','".$log[3]."','0','0')");
	echo "ADDING";
	}else{
	echo "EXIST";
	}
}
if($_POST['TYPE'] == "callbusi"){
	$numRow=mysql_num_rows(mysql_query( "SELECT * from log_history WHERE `PHONE_UNIC_ID` = '".$log[2]."' AND
	 `logType` = 'CALLBUSI' AND `logData` = '".$log[1]."' AND `logValue1` = '".$log[3]."' AND `logValue2` = '0' AND `logValue3` = '0' "));
	if($numRow == 0) {	
	$sql = mysql_query("INSERT INTO `log_history`(
	`PHONE_UNIC_ID`, `logType`, `logData`, `logValue1`, `logValue2`, `logValue3`) 
	VALUES ('".$log[2]."','CALLBUSI','".$log[1]."','".$log[3]."','0','0')");
	echo "ADDING";
	}else{
	echo "EXIST";
	}
}
if($_POST['TYPE'] == "callend"){
	$numRow=mysql_num_rows(mysql_query( "SELECT * from log_history WHERE `PHONE_UNIC_ID` = '".$log[2]."' AND
	 `logType` = 'CALLEND' AND `logData` = '".$log[1]."' AND `logValue1` = '".$log[3]."' AND `logValue2` = '0' AND `logValue3` = '0' "));
	if($numRow == 0) {	
	$sql = mysql_query("INSERT INTO `log_history`(
	`PHONE_UNIC_ID`, `logType`, `logData`, `logValue1`, `logValue2`, `logValue3`) 
	VALUES ('".$log[2]."','CALLEND','".$log[1]."','".$log[3]."','0','0')");
	echo "ADDING";
	}else{
	echo "EXIST";
	}
}
if($_POST['TYPE'] == "newsms"){
	$numRow=mysql_num_rows(mysql_query( "SELECT * from log_history WHERE `PHONE_UNIC_ID` = '".$log[2]."' AND
	 `logType` = 'NEWSMS' AND `logData` = '".$log[1]."' AND `logValue1` = '".$log[3]."' AND `logValue2` = '".$log[4]."' AND `logValue3` = '".$log[5]."' "));
	if($numRow == 0) {	
	$sql = mysql_query("INSERT INTO `log_history`(
	`PHONE_UNIC_ID`, `logType`, `logData`, `logValue1`, `logValue2`, `logValue3`) 
	VALUES ('".$log[2]."','NEWSMS','".$log[1]."','".$log[3]."','".$log[4]."','".$log[5]."')");
	echo "ADDING";
	}else{
	echo "EXIST";
	}
}		    			
/****************************** LIST CALL ********************************/
if($_POST['TYPE'] == "getListCall"){
	$numRow=mysql_num_rows(mysql_query( "SELECT * from call_history WHERE `PHONE_UNIC_ID` = '".$log[0]."' AND
	 `callPhoneNumber` = '".$log[1]."' AND `callType` = '".$log[2]."' AND `callDayTime` = '".$log[3]."' AND `callDuration` = '".$log[4]."' "));
	if($numRow == 0) {
	$sql = mysql_query("INSERT INTO `call_history`(
	`PHONE_UNIC_ID`, `callPhoneNumber`, `callType`, `callDayTime`, `callDuration`) 
	VALUES ('".$log[0]."','".$log[1]."','".$log[2]."','".$log[3]."','".$log[4]."')");
	echo "ADDING";
	}else{
	echo "EXIST";
	}
}
/****************************** CONTACT LIST ********************************/
if($_POST['TYPE'] == "getContact"){
	$numRow= mysql_num_rows(mysql_query( "SELECT * from contact_list WHERE `PHONE_UNIC_ID` = '".$log[0]."' AND
	 `PHONE_ID` = '".$log[1]."' AND `DISPLAY_NAME` = '".$log[2]."' AND `NUMBER` = '".$log[3]."' "));
	if($numRow == 0) {
	$sql = mysql_query("INSERT INTO `contact_list`(
	`PHONE_UNIC_ID`, `PHONE_ID`, `DISPLAY_NAME`, `NUMBER`) 
	VALUES ('".$log[0]."','".$log[1]."','".$log[2]."','".$log[3]."')");
	echo "ADDING";
	}else{
	echo "EXIST";
	}
}
/****************************** SENSOR LIST ********************************/
if($_POST['TYPE'] == "getListSensors"){
	$numRow=mysql_num_rows(mysql_query( "SELECT * from sensor_list WHERE `PHONE_UNIC_ID` = '".$log[0]."' AND `getName` = '".$log[1]."' AND `getMaximumRange` = '".$log[2]."'  AND `getMinDelay` = '".$log[3]."'  AND `getPower` = '".$log[4]."'  AND `getResolution` = '".$log[5]."'  AND `getType` = '".$log[6]."'  AND `getVendor` = '".$log[7]."'  AND `getVersion` = '".$log[8]."'  "));
	if($numRow == 0) {
	$sql = mysql_query("INSERT INTO `sensor_list`(
	`PHONE_UNIC_ID`, `getName`, `getMaximumRange`, `getMinDelay`, `getPower`, `getResolution`, `getType`, `getVendor`, `getVersion`) 
	VALUES ('".$log[0]."','".$log[1]."','".$log[2]."','".$log[3]."','".$log[4]."','".$log[5]."','".$log[6]."','".$log[7]."','".$log[8]."')");
	echo "ADDING";
	}else{
	echo "EXIST";
	}
}
}
?>
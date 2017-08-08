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

$filename = $_FILES['uploadedfile']['name'];

//if(move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $target_path))

$file_name_parce = explode(".", $filename );

 $file_name = $file_name_parce[0];
 $file_ext = $file_name_parce[1];
 $timestamp = time();
 
$target_path = "tmp/";
$target_path = $target_path . $file_name ."_". $timestamp .".". $file_ext; 
if(move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $target_path)) {
    //echo "SUCCESS";  
    //copy($source,$dest);
    //unlink($source);

$file_open = $target_path;

/****************************** HISTORY ++++ ********************************/
if($file_name == "history"){
$lines = file($file_open);
$i=0;
foreach($lines as $line)
{
$log = explode("/--/", $line);
//echo $log[0]."][".$log[1]."][".$log[2]."][".$log[3]."][".$log[4]."]<br>";
	$i++;
if($_POST['TYPE'] == "GPSLATLON"){
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
if($_POST['TYPE'] == "BATTERYLEVELCHARGE"){
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
if($_POST['TYPE'] == "BATTERYPOWERCONNECT"){
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
if($_POST['TYPE'] == "BATTERYPOWERDISCONNECT"){
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
if($_POST['TYPE'] == "CHARGESTART"){
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
if($_POST['TYPE'] == "CHARGEEND"){
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
if($_POST['TYPE'] == "SENSORLIGHTLEVEL"){
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
if($_POST['TYPE'] == "SENSORTEMPERATURE"){
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
if($_POST['TYPE'] == "SENSORACCELEROMETERXYZ"){
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
if($_POST['TYPE'] == "CALLOUTGOING"){
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
if($_POST['TYPE'] == "CALLINCOMING"){
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
if($_POST['TYPE'] == "CALLBUSI"){
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
if($_POST['TYPE'] == "CALLEND"){
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
if($_POST['TYPE'] == "NEWSMS"){
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
  
 }
}
/****************************** SENSOR ++++ ********************************/
	if($file_name == "sensors"){
	$lines = file($file_open);
	foreach($lines as $line)
 {
	$log = explode("/--/", $line);
	$numRow=mysql_num_rows(mysql_query( "SELECT * from sensor_list WHERE `PHONE_UNIC_ID` = '".$log[0]."' AND `getName` = '".$log[1]."' AND `getMaximumRange` = '".$log[2]."'  AND `getMinDelay` = '".$log[3]."'  AND `getPower` = '".$log[4]."'  AND `getResolution` = '".$log[5]."'  AND `getType` = '".$log[6]."'  AND `getVendor` = '".$log[7]."'  AND `getVersion` = '".$log[8]."'  "));
	if($numRow == 0) {
	$sql = mysql_query("INSERT INTO `sensor_list`(
	`PHONE_UNIC_ID`, `getName`, `getMaximumRange`, `getMinDelay`, `getPower`, `getResolution`, `getType`, `getVendor`, `getVersion`) 
	VALUES ('".$log[0]."','".$log[1]."','".$log[2]."','".$log[3]."','".$log[4]."','".$log[5]."','".$log[6]."','".$log[7]."','".$log[8]."')");
	//echo "ADDING";
	}else{
	//echo "EXIST";
	}	
 }
}
/****************************** CALL HISTORY ++++ ********************************/
if($file_name == "call_history"){
$lines = file($file_open);
 foreach($lines as $line)
 {
	$log = explode("/--/", $line);
	$numRow=mysql_num_rows(mysql_query( "SELECT * from call_history WHERE `PHONE_UNIC_ID` = '".$log[0]."' AND
	 `callPhoneNumber` = '".$log[1]."' AND `callType` = '".$log[2]."' AND `callDayTime` = '".$log[3]."' AND `callDuration` = '".$log[4]."' "));
	if($numRow == 0) {
	$sql = mysql_query("INSERT INTO `call_history`(
	`PHONE_UNIC_ID`, `callPhoneNumber`, `callType`, `callDayTime`, `callDuration`) 
	VALUES ('".$log[0]."','".$log[1]."','".$log[2]."','".$log[3]."','".$log[4]."')");
	//echo "ADDING";
	}else{
	//echo "EXIST";
	}	
 }
}
/****************************** CONTACT ++++ ********************************/
if($file_name == "contacts"){
$lines = file($file_open);
$i=0;
foreach($lines as $line)
 {
	$log = explode("/--/", $line);
	$numRow= mysql_num_rows(mysql_query( "SELECT * from contact_list WHERE `PHONE_UNIC_ID` = '".$log[0]."' AND
	 `PHONE_ID` = '".$log[1]."' AND `DISPLAY_NAME` = '".$log[2]."' AND `NUMBER` = '".$log[3]."' "));
	if($numRow == 0) {
	$sql = mysql_query("INSERT INTO `contact_list`(
	`PHONE_UNIC_ID`, `PHONE_ID`, `DISPLAY_NAME`, `NUMBER`) 
	VALUES ('".$log[0]."','".$log[1]."','".$log[2]."','".$log[3]."')");
	//echo "ADDING";
	}else{
	//echo "EXIST";
	}
 }
}
	}
    else{ echo "ERROR"; }

}
?>
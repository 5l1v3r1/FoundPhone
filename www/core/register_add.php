<?php
session_start();
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

if($_SERVER['REQUEST_URI'] == '/?add'){

	if($_POST['reg_email'] == true && $_POST['reg_email'] != ''){
	
		if($_POST['reg_password'] == true && $_POST['reg_password'] != ''){
		
			if($_POST['reg_fio_i'] == true && $_POST['reg_fio_i'] != ''){
			
				if($_POST['reg_fio_f'] == true && $_POST['reg_fio_f'] != ''){
				
					if($_POST['reg_phone'] == true && $_POST['reg_phone'] != ''){
					
						$numRow=mysql_num_rows(mysql_query( "SELECT * from user_parametervalue WHERE `email` = '".$_POST['reg_email']."'  "));
						if($numRow == 0) {	
						$sql = mysql_query("INSERT INTO `user_parametervalue`(
						`email`, `password`, `fio_i`, `fio_f`, `phone` ) 
						VALUES ('".$_POST['reg_email']."','".$_POST['reg_password']."','".$_POST['reg_fio_i']."','".$_POST['reg_fio_f']."','".$_POST['reg_phone']."')");
		
		$_SESSION['type'] = 0;
		$_SESSION['PHONE_UNIC_ID1'] = 0;
		$_SESSION['PHONE_UNIC_ID2'] = 0;
		$_SESSION['PHONE_UNIC_ID3'] = 0;
		$_SESSION['PHONE_UNIC_ID4'] = 0;
		$_SESSION['PHONE_UNIC_ID5'] = 0;
		$_SESSION['email'] = 0;
		$_SESSION['auth'] = 0;
		$_SESSION['auth_time'] = $timestamp;

		$result = mysql_query( "SELECT type, PHONE_UNIC_ID1, PHONE_UNIC_ID2, PHONE_UNIC_ID3, PHONE_UNIC_ID4, PHONE_UNIC_ID5, fio_i from user_parametervalue WHERE email ='".$_POST['reg_email']."' " );
		$cnt = mysql_fetch_row($result);
		
		$_SESSION['type'] = $cnt[0];
		$_SESSION['PHONE_UNIC_ID1'] = $cnt[1];
		$_SESSION['PHONE_UNIC_ID2'] = $cnt[2];
		$_SESSION['PHONE_UNIC_ID3'] = $cnt[3];
		$_SESSION['PHONE_UNIC_ID4'] = $cnt[4];
		$_SESSION['PHONE_UNIC_ID5'] = $cnt[5];
		$_SESSION['email'] = $_POST['reg_email'];
		$_SESSION['auth'] = 1;
		$_SESSION['auth_time'] = $timestamp;
						
						echo "<script>alert('SUCCESS REGISTER, WELCOME');</script>";
						echo "<script>window.location.assign('http://'+window.location.host+'/panel.php');</script>";
						
						}else{ echo "<script>alert('ERROR - USER EXIST');</script>"; echo "exist"; }
					
					}else{ echo "<script>alert('ERROR - please reenter PHONE');</script>"; echo "e_phone"; }
				
				}else{ echo "<script>alert('ERROR - please reenter FIO');</script>"; echo "e_fio_f"; }
				
			}else{ echo "<script>alert('ERROR - please reenter FIO');</script>"; echo "e_fio_i"; }
		
		}else{ echo "<script>alert('ERROR - please reenter PASSWORD');</script>"; echo "e_password"; }
		
	}else{ echo "<script>alert('ERROR - please reenter EMAIL');</script>"; echo "e_email"; }
}

}
?>
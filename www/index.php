<?php
//3600 sec
session_start();
$timestamp = time();
if($_SERVER['SERVER_NAME'] == 'beeline-mts.myjino.ru'){
$mysql_host = "localhost";
$mysql_user = "046507228_fphone";
$mysql_password = "fphone";
$mysql_database = "beeline-mts_fphone";
}
if($_SERVER['SERVER_NAME'] == 'lunarsun.ru'){
$mysql_host = "localhost";
$mysql_user = "046507228_fphone";
$mysql_password = "fphone";
$mysql_database = "beeline-mts_fphone";
}
if($_SERVER['SERVER_NAME'] == 'fphone.com'){
$mysql_host = "localhost";
$mysql_user = "fphone";
$mysql_password = "fphone";
$mysql_database = "fphone";
}
	if (!mysql_connect($mysql_host, $mysql_user, $mysql_password)){
	exit;
	}else{
	mysql_select_db($mysql_database);
	mysql_set_charset('utf8');
//AUTH
/*
echo '--['.$_SESSION['auth'];// - 0 non auth, 1 auth
echo ']--['.$_SESSION['auth_time'];// - timestamp, live 1 hour
echo ']--['.$_SESSION['email'];
echo ']--['.$_SESSION['type'];
*/
if($_POST['email'] != '' & $_POST['phone'] != '' & $_POST['login'] != '' & $_POST['password'] != '' & 
$_POST['action'] != '' && $_POST['action'] == 'register'){
//REGISTER	email=&phone=&login=&password=&action=register
	$reNew = mysql_fetch_row(mysql_query("SELECT COUNT(*) FROM user_parametervalue WHERE email='".$a1."' AND password='".$a2."' LIMIT 0,1"));
	if($reNew[0] == 0){
	$getAuth = mysql_query("INSERT INTO `user_parametervalue`(`login`, `password`, `phone`, `email`) VALUES ( '".$_POST['login']."' , '".$_POST['password']."' , '".$_POST['phone']."' , '".$_POST['email']."'  ) ");
	echo "<script>
	alert('SUCCESS REGISTER AND LOGIN');
	window.location.assign('http://'+window.location.host+'/panel.php');</script>";
	}else{echo "<script>
	alert('PLEASE TRY AGAIN');
	window.location.assign('http://'+window.location.host+'/');</script>";
	}
}
if($_POST['email'] == true && $_POST['password'] == true && $_POST['action'] == false){ //AUTH FORM
$a1 = $_POST['email'];
$a2 = $_POST['password'];
	
	$getAuth = mysql_query("SELECT * FROM user_parametervalue WHERE email='".$a1."' AND password='".$a2."' LIMIT 0,1");
    $row = mysql_fetch_assoc($getAuth);
	
if($a1 == $row['email'] && $a2 == $row['password']){
	$_SESSION['email'] = $row['email'];
	$_SESSION['type'] = $row['type'];
	$_SESSION['PHONE_UNIC_ID1'] = $row['PHONE_UNIC_ID1'];
	$_SESSION['PHONE_UNIC_ID2'] = $row['PHONE_UNIC_ID2'];
	$_SESSION['PHONE_UNIC_ID3'] = $row['PHONE_UNIC_ID3'];
	$_SESSION['PHONE_UNIC_ID4'] = $row['PHONE_UNIC_ID4'];
	$_SESSION['PHONE_UNIC_ID5'] = $row['PHONE_UNIC_ID5'];
	$_SESSION['auth'] = 1;
	$_SESSION['auth_time'] = $timestamp;
	echo "<script>window.location.assign('http://'+window.location.host+'/panel.php');</script>";
}else{
	$_SESSION['email'] = 0;
	$_SESSION['type'] = 0;
	$_SESSION['PHONE_UNIC_ID1'] = 0;
	$_SESSION['PHONE_UNIC_ID2'] = 0;
	$_SESSION['PHONE_UNIC_ID3'] = 0;
	$_SESSION['PHONE_UNIC_ID4'] = 0;
	$_SESSION['PHONE_UNIC_ID5'] = 0;
	$_SESSION['auth'] = 0;
	$_SESSION['auth_time'] = $timestamp;
echo "<script>alert('PLEASE REENTER PASSWORD AND EMAIL!');</script>";
}
}
if ((isset($_SESSION['type']) && $_SESSION['type']) && isset($_SESSION['auth']) && isset($_SESSION['auth_time']) && isset($_SESSION['PHONE_UNIC_ID1'])){//EXIST
if($_SESSION['auth'] == 1 && ($timestamp - $_SESSION['auth_time']) < 3600){
	$_SESSION['auth'] = 1;
	}else{
	$_SESSION['email'] = 0;
	$_SESSION['type'] = 0;
	$_SESSION['PHONE_UNIC_ID1'] = 0;
	$_SESSION['PHONE_UNIC_ID2'] = 0;
	$_SESSION['PHONE_UNIC_ID3'] = 0;
	$_SESSION['PHONE_UNIC_ID4'] = 0;
	$_SESSION['PHONE_UNIC_ID5'] = 0;
	$_SESSION['auth'] = 0;
	$_SESSION['auth_time'] = $timestamp;
	}
}
if((isset($_SESSION['type']) && ($_SESSION['type'] != 'client' && $_SESSION['type'] != 'admin')) && !isset($_SESSION['email']) && !isset($_SESSION['auth_time']) && !isset($_SESSION['PHONE_UNIC_ID1'])){//NON EXIST
	$_SESSION['email'] = 0;
	$_SESSION['type'] = 0;
	$_SESSION['PHONE_UNIC_ID1'] = 0;
	$_SESSION['PHONE_UNIC_ID2'] = 0;
	$_SESSION['PHONE_UNIC_ID3'] = 0;
	$_SESSION['PHONE_UNIC_ID4'] = 0;
	$_SESSION['PHONE_UNIC_ID5'] = 0;
	$_SESSION['auth'] = 0;
	$_SESSION['auth_time'] = $timestamp;
}
}//CONNECT DB

?>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Поиск телефона по IMEI сервис поиска детей, родительский контроль телефона">
	<meta name="keywords" content="поиск телефона, поиск реденка, родительский контроль, где мой ребенок, телефон по IMEI">
    <title>F PHONE - будь в курсе. Сервис контроля для устройств</title>

    <!-- BOOTSTRAP STYLES-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

</head>
<body style="background-color: #E2E2E2;">
    <div class="container">
        <div class="row text-center " style="padding-top:100px; background-image: url('1demo/images/demo_img.png');">
            <div class="col-md-12">
<?php

if($_SERVER['REQUEST_URI'] != '/?register'){

?>			
			<h1 class="page-head-line"></h1>
			<h1 class="page-subhead-line"> Мы не рекламируем. Мы предлагаем вам сервис поиска устройств на базе Android. Вы можете быть уверены что ваш ребенок не потеряется. </h1>
             <h1 class="page-head-line"></h1> 
				<h1 class="page-subhead-line"></h1> 		
				<hr>
				Сервис рассчитан на получение данных с устройства, и определение местоположения. Присутствует возможность получения списка контактов, списка сенсоров и получения данных с них.
				<hr>
				Доступны опции получения данных геолокации по IP , GPS , определение местоположения по данным сотовового оператора.
				<hr>
				Также мы предоставляем вам данные о освещенности, положении устройства в координатах XYZ, данные о заряде батареи, 
				списках вызовов, списках контактов и еще много опций.
				Также вы можете настроить уведомления для телефонных номеров при входящих и исходящих вызовов, при получении смс вы можете получить уведомления если в смс присутствует указанное вами слово или все из указанных вами.
				<hr>
				Есть опции позволяющие делать снимок если в фокусе камеры присутствует лицо. Также есть возможность делать запись входящего или исходящего вызова и скачать с сайта. 
				<hr>
				Если телефон украден то можно удаленно закачать свое аудио послание и воспроизвести его или по времени или при определенных параметрах.
				<hr>
				И конечно присутствует блокировка телефона
				
            </div>
        </div>
         <div class="row ">
            
<?php 

}

//**********************************************		ДЕМО КОНТЕНТ
if(!$_GET && !$_POST){

	include('core/demo.php');

}

//**********************************************		ПОКУПКА ТАРИФНОГО ПЛАНА
if($_GET['useTariff'] == true || $_POST['useTariff'] == true){

	$_SESSION['useTariff'] = $_POST['useTariff'];
	include('core/payment.php');
	
}

//**********************************************		РЕГИСТРАЦИЯ
if($_SERVER['REQUEST_URI'] && $_SERVER['REQUEST_URI'] == '/?register'){

	include('core/register.php');

}

//**********************************************		ПРИЕМ ДАННЫХ РЕГИСТРАЦИИ
if($_SERVER['REQUEST_URI'] && $_SERVER['REQUEST_URI'] == '/?add'){

	include('core/register_add.php');

}

if($_SERVER['REQUEST_URI'] != '/?register'){

?>				
                <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
                           
						<div class="panel-body">
							
								<form role="form" method="post" action="?register">
                                    <div class="form-group">
                                    </div>
                                    <button class="btn btn-primary">РЕГИСТРАЦИЯ</button>
                                </form>						
                                <form role="form" method="post" action="">
	
                                    <hr />
                                   
                                       <br />
										<div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-tag"  ></i></span>
                                            <input id="email" type="email" name="email" class="form-control" placeholder="ваш Email" />
                                        </div>
                                        <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                                            <input id="password" name="password" type="password" class="form-control"  placeholder="ваш пароль" />
                                        </div>
                                    <div class="form-group">
                                    </div>
                                     
                                     
                                    <hr />
                                   <button class="btn btn-primary ">ВОЙТИ</button>
                                </form>
								
<?php } ?>								
								
                        </div>
                </div>
				
				
        </div>
    </div>

</body>
</html>

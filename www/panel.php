<?php
//AUTH
//$_SESSION['auth'] - 0 non auth, 1 auth
//$_SESSION['auth_time'] - timestamp, live 1 hour
//3600 sec
session_start();
$timestamp = time();
/*
echo '--['.$timestamp;
echo ']--['.$_SESSION['auth'];// - 0 non auth, 1 auth
echo ']--['.$_SESSION['auth_time'];// - timestamp, live 1 hour
echo ']--['.$_SESSION['email'];
echo ']--['.$_SESSION['type'];
echo "INDEXp";
*/
if ((isset($_SESSION['type']) && ($_SESSION['type'] == 'admin' || $_SESSION['type'] == 'client')) && isset($_SESSION['auth']) && isset($_SESSION['auth_time']) && isset($_SESSION['PHONE_UNIC_ID1'])){//EXIST
//echo '[==='.$_SESSION['auth'].'===]';
//echo '[==='.$_SESSION['auth_time'].'===]';

	if($_SESSION['auth'] == 1 && ($timestamp - $_SESSION['auth_time']) < 3600){
	$_SESSION['auth'] = 1;
	//$_SESSION['auth_time'] = $timestamp;
	}else{
	$_SESSION['type'] = 0;
	$_SESSION['PHONE_UNIC_ID1'] = 0;
	$_SESSION['PHONE_UNIC_ID2'] = 0;
	$_SESSION['PHONE_UNIC_ID3'] = 0;
	$_SESSION['PHONE_UNIC_ID4'] = 0;
	$_SESSION['PHONE_UNIC_ID5'] = 0;
	$_SESSION['email'] = 0;
	$_SESSION['auth'] = 0;
	echo "<script>window.location.assign('http://'+window.location.host+'/index.php');</script>";
	}

}else{//NON EXIST
$_SESSION['type'] = 0;
	$_SESSION['PHONE_UNIC_ID1'] = 0;
	$_SESSION['PHONE_UNIC_ID2'] = 0;
	$_SESSION['PHONE_UNIC_ID3'] = 0;
	$_SESSION['PHONE_UNIC_ID4'] = 0;
	$_SESSION['PHONE_UNIC_ID5'] = 0;
$_SESSION['email'] = 0;
$_SESSION['auth'] = 0;
$_SESSION['auth_time'] = $timestamp;
//echo '[=!='.$_SESSION['auth'].'=!=]';
//echo '[=!='.$_SESSION['auth_time'].'=!=]';
echo "<script>window.location.assign('http://'+window.location.host+'/index.php');</script>";
}
?>
<?php
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
$PHONE_UNIC_ID1 = $_SESSION['PHONE_UNIC_ID1'];

if($_GET['exit'] == true && $_GET['exit'] == 'y'){//EXIT
	$_SESSION['type'] = 0;
	$_SESSION['PHONE_UNIC_ID1'] = 0;
	$_SESSION['PHONE_UNIC_ID2'] = 0;
	$_SESSION['PHONE_UNIC_ID3'] = 0;
	$_SESSION['PHONE_UNIC_ID4'] = 0;
	$_SESSION['PHONE_UNIC_ID5'] = 0;
	$_SESSION['email'] = 0;
	$_SESSION['auth'] = 0;
	echo "<script>window.location.assign('http://'+window.location.host+'/');</script>";
}

function show_device($email)
{
		$result = mysql_query( "SELECT PHONE_UNIC_ID1, PHONE_UNIC_ID2, PHONE_UNIC_ID3, PHONE_UNIC_ID4, PHONE_UNIC_ID5, fio_i from user_parametervalue WHERE email ='".$email."' " );
		$cnt = mysql_fetch_row($result);
		$countDev = 0;
		if($cnt[0] != ''){$countDev++; set_layout_device($cnt[0], $countDev);}
		if($cnt[1] != ''){$countDev++; set_layout_device($cnt[1], $countDev);}
		if($cnt[2] != ''){$countDev++; set_layout_device($cnt[2], $countDev);}
		if($cnt[3] != ''){$countDev++; set_layout_device($cnt[3], $countDev);}
		if($cnt[4] != ''){$countDev++; set_layout_device($cnt[4], $countDev);}
}		
function set_layout_device($PHONE_UNIC_ID, $i)
{		
	?>	
	<li class="inactive">
		<a href="#"><i class="fa fa-desktop "></i>Устройство [<?php echo $i;?>]<span class="fa arrow"></span></a>
		<ul class="nav nav-third-level collapse out" style="">
		<li>
			<a href="maps/celllocation.php?cell=<?php echo $PHONE_UNIC_ID;?>" target="_blank"><i class="fa fa-comments-o "></i>Местоположение</a>
		</li>
		<li>
			<a href="?dev=val&devID=<?php echo $PHONE_UNIC_ID;?>"><i class="fa fa-edit "></i>О устройстве</a>
		</li>
		<li>
			<a href="?dev=rem&devID=<?php echo $PHONE_UNIC_ID;?>"><i class="fa fa-toggle-on"></i>Управление</a>
		</li>
		<li>
			<a href="?dev=al&devID=<?php echo $PHONE_UNIC_ID;?>"><i class="fa fa-bell "></i>Уведомления</a>
		</li>
		<li>
			<a href="?dev=call&devID=<?php echo $PHONE_UNIC_ID;?>"><i class="fa fa-square-o "></i>Список вызовов</a>
		</li>
		<li>
			<a href="?dev=cont&devID=<?php echo $PHONE_UNIC_ID;?>"><i class="fa fa-square-o "></i>Список контактов</a>
		</li>
		<li>
			<a href="?dev=sens&devID=<?php echo $PHONE_UNIC_ID;?>"><i class="fa fa-square-o "></i>Список сенсоров</a>
		</li>
		</ul>
	</li>
<?php
	}
?>

<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>F PHONE panel</title>
   <script src="js/jquery-2.0.0.min.js"></script>
    <!-- BOOTSTRAP SCRIPTS -->
    <script src="assets/js/bootstrap.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="assets/js/jquery.metisMenu.js"></script>
    <!-- CUSTOM SCRIPTS -->
    <script src="assets/js/custom.js"></script>
	<script src="js/function.js"></script>
	<!-- CUSTOM SCRIPTS -->
    <script src="assets/js/bem-components.dev.js"></script>
	<!-- BOOTSTRAP STYLES-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <!--CUSTOM BASIC STYLES-->
    <link href="assets/css/basic.css" rel="stylesheet" />
    <!--CUSTOM MAIN STYLES-->
    <link href="assets/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
</head>	
<body>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
<span class="sr-only">Toggle navigation</span>
<span class="icon-bar"></span>
<span class="icon-bar"></span>
<span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.php">F_PHONE ADMIN</a>
            </div>

            <div class="header-right">

              <a href="message-task.html" class="btn btn-info" title="New Message"><b>0 </b><i class="fa fa-envelope-o fa-1x"></i></a>
                <a href="message-task.html" class="btn btn-primary" title="New Task"><b>0 </b><i class="fa fa-bars fa-1x"></i></a>
                <a href="?exit=y" class="btn btn-danger" title="EXIT"><i class="fa fa-exclamation-circle fa-1x"></i></a>
			
            </div>
        </nav>
        <!-- /. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
<li>
    <div class="user-img-div">
        <img src="assets/img/icon.png" class="img-thumbnail" />
        <div class="inner-text">
            TIME LEFT
        </div>
			
			<div class="progress">
<?php
$tim = (($timestamp - $_SESSION['auth_time'])/100);
$process = 'progress-bar';
if($tim > 50 && $tim < 75){$process = 'progress-bar progress-bar-info';}
if($tim > 74 && $tim < 85){$process = 'progress-bar progress-bar-warning';}
if($tim > 85 && $tim < 100){$process = 'progress-bar progress-bar-danger';}
?>
  <div class="<?php echo $process;?>" role="progressbar" aria-valuenow="<?php echo (($timestamp - $_SESSION['auth_time']));?>" aria-valuemin="0" aria-valuemax="3600" style="width: <?php echo (($timestamp - $_SESSION['auth_time'])/100);?>%;">
    <?php echo $tim;?>%
  </div>
</div>
			
    </div>
</li>
<li>
    <a  href="#"><i class="fa fa-sitemap "></i>ПАНЕЛЬ УПРАВЛЕНИЯ</a>
</li>
<li>
    <a href="#"><i class="fa fa-desktop "></i>Мои устройства<span class="fa arrow"></span></a>
    <ul class="nav nav-second-level">
			<li>
			
		<?php show_device($_SESSION['email']); ?>	
			
        </li>
    </ul>
		<ul class="nav nav-second-level">
			<li>
            <a href="#" data-toggle="modal" data-target="#addDevice"><i class="fa fa-plus "></i><i class="fa fa-desktop "></i>Добавить устройство</a>
        </li>
    </ul>
		
		
</li>
	<li>
    <a href="#"><i class="fa fa-square-o "></i>Мои данные<span class="fa arrow"></span></a>
     <ul class="nav nav-second-level">
        <li>
            <a href="?user=d"><i class="fa fa-square-o "></i>Мои данные</a>
        </li>
			<li>
            <a href="?user=n"><i class="fa fa-square-o "></i>Уведомления</a>
        </li>
			<li>
            <a href="?user=c"><i class="fa fa-square-o "></i>Баланс</a>
        </li>
    </ul>
</li>
                </ul>
            </div>

        </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
            <div id="page-inner">
                <div class="row">
<div class="col-md-12">
    <h1 class="page-head-line"></h1>
    <h1 class="page-subhead-line"></h1>

</div>
                </div>
                <!-- /. ROW  -->
<div class="row">
                <div class="col-md-12">
<div class="panel panel-default">
    <div class="panel-heading">
        Доступные данные
    </div>
    <div class="panel-body">

<?php 
//**********************************************		ДЕМО КОНТЕНТ
if($_GET['dev'] != true && $_GET['user'] != true){

//	include('core/demo.php');

}

//**********************************************		УПРАВЛЕНИЕ УСТРОЙСТВОМ
if($_GET['dev'] == true && $_GET['devID'] == true && $_GET['dev'] == 'rem' && $_GET['user'] != true){

	include('core/remote.php');

}

//**********************************************		ДАННЫЕ О УСТРОЙСТВЕ
if($_GET['dev'] == true && $_GET['devID'] == true && $_GET['dev'] == 'val' && $_GET['user'] != true){

	include('core/values.php');

}

//**********************************************        СПИСОК УВЕДОМЛЕНИЙ - ЛОГ ДАННЫХ
if($_GET['dev'] == true && $_GET['devID'] == true && $_GET['dev'] == 'al' && $_GET['user'] != true){

	include('core/history_device.php');

}

//**********************************************        СПИСОК СЕНСОРОВ
if($_GET['dev'] == true && $_GET['devID'] == true && $_GET['dev'] == 'sens' && $_GET['user'] != true){
	
	include('core/sensors.php');
	
}

//**********************************************        СПИСОК ВЫЗОВОВ
if($_GET['dev'] == true && $_GET['devID'] == true && $_GET['dev'] == 'call' && $_GET['user'] != true){

	include('core/call_history.php');

}

//**********************************************        СПИСОК КОНТАКТОВ
if($_GET['user'] == true && $_GET['devID'] == true && $_GET['dev'] == 'd' && $_GET['user'] != true){

	include('core/contacts.php');

}

//**********************************************        ДАННЫЕ ПОЛЬЗОВАТЕЛЯ
if($_GET['user'] == true && $_GET['dev'] == 'd'){

	include('core/u_info.php');

}

//**********************************************        УВЕДОМЛЕНИЯ ПОЛЬЗОВАТЕЛЯ
if($_GET['user'] == true && $_GET['dev'] == 'n'){

	include('core/u_alerts.php');

}

//**********************************************        БАЛАНС ПОЛЬЗОВАТЕЛЯ
if($_GET['user'] == true && $_GET['dev'] == 'c'){

	include('core/u_balance.php');

}

}
?>
<script type="text/javascript" src="jsApi.js"></script>
<script>
$('.data').click(function(){

    var tr = window.event.srcElement.parentElement;
    while ('TR' != tr.tagName) tr = tr.parentElement;

    for (var row=0; tr!=null; tr=tr.previousSibling, row++);
    {
	var numRow =row-1;
	}
    numCell = 0;
var row = $("table tr").eq(numRow);
var cell = $("td", row).eq(0);

var search=cell.text();
    numCell = 9;
var row = $("table tr").eq(numRow);
var cell = $("td", row).eq(9);
var search1=cell.text();
location.href = "/index.php?search=" + search+","+search1;
});
</script>	
    </div>
</div>
                </div>
                
            </div>
            </div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
    <!-- /. WRAPPER  -->
    <div id="footer-sec">
        &copy; 2016 - 2017 F_PHONE | Design By : RiccoTZ
    </div>
    <!-- /. FOOTER  -->

<!-- MODAL WINDOW -->
	<?php if($add_new_device == 1){ echo '	
			<div class="modal fade" id="addDevice" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
				<h4 class="modal-title" id="myModalLabel">ADD DEVICE</h4>
			</div>
			<div class="modal-body">
				
				 <div class="col-md-10 col-sm-10 col-xs-12">
						   <div class="panel panel-info">
				<div class="panel-body">
						<form method="POST" action="">
			<div class="form-group">
				<label> Name</label>
				 <p><input maxlength="20" class="form-control" size="20" id="name_reg" name="name_reg" value="">
			</div>
							  <div class="form-group">
			<label> Device ID</label>
				<input maxlength="20" size="20"  class="form-control" id="email_reg" name="email_reg" value=""> 
						</div>
							 <div class="form-group">
				<label> Code</label>
				 <input maxlength="20" size="20"  class="form-control" id="passw_reg" name="passw_reg" value=""> 
						 </div>
			<br><input type="submit" class="btn btn-info" value="REGISTER" name="REGISTER">
							</form>
					</div>
				</div>
					</div>	
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
                </div>
            </div>
        </div>
		'; }else{ echo '
			<div class="modal fade" id="addDevice" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
						<div class="modal-dialog">
							<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
				<h4 class="modal-title" id="myModalLabel">INFORMATION</h4>
			</div>
			<div class="modal-body">
				
				 <div class="col-md-10 col-sm-10 col-xs-12">
						   <div class="panel panel-info">
				<div class="panel-body">
			<div class="form-group">
				<label>Добавление новых устройств</label>
				
			</div>
							  <div class="form-group">
			<label>на вашем тарифном плане</label>
			 
						</div>
							 <div class="form-group">
				<label>невозможно</label>
			   
						 </div>
					</div>
				</div>
					</div>	
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
                </div>
            </div>
        </div>
			';	} ?>
			
<!-- MODAL WINDOW END --> 
               
</body>
</html>
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
echo "<h2>База недоступна!</h2>";
exit;
}else{
//  если база доступна
mysql_select_db($mysql_database);
mysql_set_charset('utf8');


$PHONE_UNIC_ID1 = $_GET['cell'];

//получаем количество точек
$q="SELECT count(*) FROM log_history WHERE PHONE_UNIC_ID ='".$PHONE_UNIC_ID1."' AND logType = 'GPSLATLON' ";
$res=mysql_query($q);
$row=mysql_fetch_row($res);
$total_rows=$row[0]-20;

$result = mysql_query( "SELECT * from log_history WHERE PHONE_UNIC_ID ='".$PHONE_UNIC_ID1."' AND logType = 'GPSLATLON'  LIMIT ".$total_rows." , 20" );
/*
while( $row = mysql_fetch_row($result)) 
{
   echo $row[4] . "][" . $row[4]  ;
}
*/

}
?>
<html>
  <head>
  <script src="https://maps.googleapis.com/maps/api/js?key=yourkey&hl=ru"></script>
    <script type="text/javascript" src="gmaps.markermanager.min.js"></script>
    <script type="text/javascript">
	/*
	MapTypeId.ROADMAP – стандартное представление дорожной карты. Этот тип карты используется по умолчанию.
	MapTypeId.SATELLITE – карта из спутниковых снимков Google Earth.
	MapTypeId.HYBRID – сочетание обычной и спутниковой карты.
	MapTypeId.TERRAIN – физическая карта на основе данных о ландшафте
	*/
      function initialize() {
        var lat = 44.76759326;
        var lng = 39.82272956;
        var quantity = 100;
        
        var myOptions = {
          zoom: 16,
          center: new google.maps.LatLng(lat, lng),
          mapTypeId: google.maps.MapTypeId.HYBRID
        }
        var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
        
        var markerManagerOptions = {
          icon: {
            src: "http://maps.google.com/mapfiles/ms/micons/partly_cloudy.png",
            shadow: "http://maps.google.com/mapfiles/ms/micons/partly_cloudy.shadow.png"
          },
          cell: {
            width: 48,
            height: 96
          }
        };
        
        var markerManager = new GmapsMarkerManager(map, markerManagerOptions);
        var markers = [];
		/************************************************************/
		<?php
		$i = 0;	$w = 0;
		while( $row = mysql_fetch_row($result)) 
		{
		   //echo $row[4] . "][" . $row[5]  ;
		   echo"
			markers.push(new google.maps.Marker({position: new google.maps.LatLng('".$row[4]."', '".$row[5]."')}));
			markerManager.addMarker(markers['".$i."']);
		   ";
		   $i++; $w++;
		}
		?>
		/************************************************************/
		/*
		$.get("http://"+urlww+"/haiti_survey/rdt_fetch.php?action=meta_id, function (response) {
                response = JSON.parse(response);
				var counts =  response.length;
				if(response[0].auth == '101'){
				alert('ERROR LOADING DOCUMENT FROM THIS SIGNATURE');
				}else{
				alert('SUCCESS!');
                for(var i = 0; i<counts; i++){//<inputs.length;i++){
				var lat = response[i].lat;
				var lon = response[i].lon;
                }
				} 
			} )
		*/
		/*************************************************************/
		/*
        for (var i = 0; i < quantity; i++) {
          markers.push(new google.maps.Marker({position: new google.maps.LatLng(lat, lng)}));
          markerManager.addMarker(markers[i]);
          lat += 0.01;
          lng += 0.01;
        }
		*/
		/*************************************************************/
      }
    </script>
  </head>
  
  <body style="margin:0px; padding:0px;" onload="initialize()">
    <div id="map_canvas" style="width: 100%; height: 100%;"></div>
  </body>
</html>
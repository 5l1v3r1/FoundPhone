<?php

	echo '<table class="table table-striped table-bordered table-hover" style="font-size: 9px;"><tr >
<th>ID</th><th>Name</th><th>Maximum Range</th><th>Min Delay</th><th>Power</th><th>Resolution</th><th>Type</th><th>Vendor</th><th>Version</th></tr>';

$q="SELECT count(*) FROM sensor_list WHERE PHONE_UNIC_ID ='".$_GET['devID']."' ";
$res=mysql_query($q);
$row=mysql_fetch_row($res);
$total_rows=$row[0];

$result = mysql_query( "SELECT * from sensor_list WHERE PHONE_UNIC_ID ='".$_GET['devID']."' " );

while( $row = mysql_fetch_row($result)) 
{
    echo "
	<tr class='dataA' class='cell' >
	<td>$row[0]</td><td>$row[2]</td><td>$row[3]</td><td>$row[4]</td><td>$row[5]</td><td>$row[6]</td><td>$row[7]</td><td>$row[8]</td><td>$row[9]</td></tr>";
}
echo '</table> ';

?>
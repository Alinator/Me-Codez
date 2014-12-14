<?php
/**
Author: Dmitry
Returns a pre-saved path from DB.
**/
$dbserver = "localhost";
$dblogin = "root";
$dbpassword = "41681";
$dbname = 'lindholmen2';

$lat1 = mysql_escape_string($_GET['lat1']);
$lat2 = mysql_escape_string($_GET['lat2']);
$lon1 = mysql_escape_string($_GET['lon1']);
$lon2 = mysql_escape_string($_GET['lon2']);

$connection = mysql_connect($dbserver, $dblogin, $dbpassword);
mysql_select_db($dbname);
$from = mysql_query("SELECT * FROM nodes ORDER BY ((nodes.lat - ".$lat1.") * (nodes.lat - ".$lat1.") + (nodes.lon - ".$lon1.") * (nodes.lon - ".$lon1."))  ASC LIMIT 1");
//echo "SELECT * FROM nodes ORDER BY ((nodes.lat - ".$lat1.") * (nodes.lat - ".$lat1.") + (nodes.lon - ".$lon1.") * (nodes.lon - ".$lon1."))  ASC LIMIT 1";
//echo "SELECT * FROM nodes ORDER BY ((nodes.lat - ".$lat1.") * (nodes.lat - ".$lat1.") + (nodes.lon - ".$lon1.") * (nodes.lon - ".$lon1."))  ASC LIMIT 1";
$to = mysql_query("SELECT * FROM nodes ORDER BY ((nodes.lat - ".$lat2.") * (nodes.lat - ".$lat2.") + (nodes.lon - ".$lon2.") * (nodes.lon - ".$lon2."))");
$found = false;
while ($fres = mysql_fetch_object($from)) {
while ($res = mysql_fetch_object($to)) {
$query = mysql_query('SELECT * FROM paths WHERE paths.from='.$fres->id.' AND paths.to='.$res->id);
//echo 'SELECT * FROM paths WHERE paths.from='.$from.' AND paths.to='.$res->id.'<br/>';
//echo 'SELECT * FROM paths WHERE paths.from='.$from.' AND paths.to='.$to;
if (mysql_num_rows($query) > 0) {
$found = true;
//echo 'SELECT * FROM paths WHERE paths.from='.$fres->id.' AND paths.to='.$res->id; 
}
if ($found == true) {
break;
}
}
if ($found == true) {
break;
}
}
if ($found == false) {
//echo "lat1:$lat1, lat2:$lat2, lon1:$lon1, lon2:$lon2";
}
$q = mysql_fetch_object($query);
$path = $q->path;
$path =  substr($path, 1, strlen($path)-2);
$points = explode(", ", $path);
for($i=0; $i<count($points); $i++) {
	$query = mysql_query("SELECT * FROM nodes WHERE nodes.id=".$points[$i]); 
	$node = mysql_fetch_object($query);
	echo '('.$node->lat.','.$node->lon.'),';
}

?>
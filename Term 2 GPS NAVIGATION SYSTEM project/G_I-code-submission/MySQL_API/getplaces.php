<?php
/**
Returns an set of objects on the map in the specified box
(parkings, cafes, whatever). OSM tags are supported.
Author: Dmitry
**/

$dbserver = "localhost";
$dblogin = "root";
$dbpassword = "41681";
$dbname = 'sweden';

$lat1 = mysql_escape_string($_GET['lat1']);
$lat2 = mysql_escape_string($_GET['lat2']);
$lon1 = mysql_escape_string($_GET['lon1']);
$lon2 = mysql_escape_string($_GET['lon2']);
$tag = mysql_escape_string($_GET['tag']);

$connection = mysql_connect($dbserver, $dblogin, $dbpassword);
mysql_select_db($dbname);
$q = mysql_query('SELECT * FROM nodes WHERE nodes.lat>'.$lat1.' AND nodes.lat<'.$lat2.' AND nodes.lon>'.$lon1.' AND nodes.lon<'.$lon2.' AND nodes.tags LIKE "%'.$tag.'%"');
echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
echo "<response>";
while ($res = mysql_fetch_object($q)) {
	echo "<object id='".$res->id."' lat='".$res->lat."' lon='".$res->lon."' tags='".$res->tags."' />\n";
}
echo "</response>";

mysql_close($connection);

?>
<?php
/**
Calculating the weight for all the edges.
Author: Dmitry
**/
function weight($lat1, $lon1, $lat2, $lon2) {
	$x = abs($lat1-$lat2);
	$y = abs($lon1-$lon2);
	$deg = sqrt($x*$x + $y*$y);
	return (($deg * 111.2) / 1000);
}


$dbserver = "localhost";
$dblogin = "root";
$dbpassword = "1212";
$dbname = 'lindholmen50000';

$connection = mysql_connect($dbserver, $dblogin, $dbpassword);
mysql_select_db($dbname);

$query = mysql_query('SELECT * FROM edges');
while ($res = mysql_fetch_object($query))  {
$node1 = mysql_query('SELECT * FROM nodes WHERE id='.$res->from);
$node1 = mysql_fetch_object($node1);
$node2 = mysql_query('SELECT * FROM nodes WHERE id='.$res->to);
$node2 = mysql_fetch_object($node2);
echo "$node1->lat, $node1->lon, $node2->lat, $node2->lon";
echo 'UPDATE edges SET weight='.weight($node1->lat, $node1->lon, $node2->lat, $node2->lon).' WHERE from='.$node1->id.' AND to='.$node2->id.'<br/>';
mysql_query('UPDATE edges SET weight='.weight($node1->lat, $node1->lon, $node2->lat, $node2->lon).' WHERE edges.from='.$node1->id.' AND edges.to='.$node2->id);

}
                                     


echo "Done";

?>

<?php
/**
Generates marker list for openlayers. Description = tags. Purpose: check if the graph is correct.
Author: Dmitry
**/
$dbserver = "localhost";
$dblogin = "root";
$dbpassword = "41681";
$dbname = 'lindholmen2';

$connection = mysql_connect($dbserver, $dblogin, $dbpassword);
mysql_select_db($dbname);

$fh = fopen("markers.txt", "w");
fputs($fh, "lat\tlon\ttitle\tdescription\ticon\ticonSize\ticonOffset\n");
$query = mysql_query("SELECT * FROM nodes");
while ($res = mysql_fetch_object($query))  {
fputs($fh, $res->lat."\t".$res->lon."\t".$res->id."\t".$res->tags."\ticon_blue_example.png\t24,24\t0,-24\n");
}
fputs($fh, "\n") ;
fclose($fh);
?>

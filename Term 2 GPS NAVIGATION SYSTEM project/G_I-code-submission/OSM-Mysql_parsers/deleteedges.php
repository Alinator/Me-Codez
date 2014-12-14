<?php
/**
 Deleting extra edges (not roads)
Author: Dmitry
**/

$black_list = Array(
'landuse=construction',
'building=yes',
'waterway',
'amenity=parking',
'railway',
'amenity=ferry_terminal',
'route=ferry',
'highway=bus_stop',
'route=bus',
'place=island',
'natural=coastline',
'boundary=administrative',
'leisure=golf_course',
'amenity=restaurant',
'amenity=cafe',
'amenity=fast_food'
);

/*
$white_list = Array(
'highway=motorway',
'highway=motorway_link',
'highway=trunk',
'highway=trunk_link',
'highway=primary',
'highway=primary_link',
'highway=secondary',
'highway=secondary_link',
'highway=tertiary',
'highway=residential',
'highway=unclassified',
'highway=road',
'highway=living_street',
'highway=service',
'highway=track'
);
*/
$dbserver = "localhost";
$dblogin = "root";
$dbpassword = "1212";
$dbname = 'lindholmen20000';

$connection = mysql_connect($dbserver, $dblogin, $dbpassword);
mysql_select_db($dbname);

for ($i =0; $i < count($black_list); $i++) {
$query = mysql_query("DELETE FROM nodes WHERE tags LIKE '%".$black_list[$i]."%'");
}



$query = mysql_query("SELECT * FROM edges");

while ($res = mysql_fetch_object($query))  {

$node1 = mysql_query('SELECT * FROM nodes WHERE nodes.id='.$res->from);
$node2 = mysql_query('SELECT * FROM nodes WHERE nodes.id='.$res->to);

if ((mysql_num_rows($node1) == 0) || (mysql_num_rows($node2) == 0)) {
 mysql_query("DELETE FROM edges WHERE edges.from=".$res->from." AND edges.to=".$res->to);
}

}


                                     


?>

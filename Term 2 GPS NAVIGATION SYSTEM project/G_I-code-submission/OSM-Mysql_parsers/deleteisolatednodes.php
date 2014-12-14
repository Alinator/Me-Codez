<?php
/**
Deleting all isolated nodes
Author: Dmitry
**/
$dbserver = "localhost";
$dblogin = "root";
$dbpassword = "41681";
$dbname = 'lindholmen2';

$connection = mysql_connect($dbserver, $dblogin, $dbpassword);
mysql_select_db($dbname);

$q = mysql_query("SELECT * FROM nodes");
while ($res = mysql_fetch_object($q)) {
$query = mysql_query("SELECT * FROM edges WHERE edges.from=".$res->id." OR edges.to=".$res->id);
if (mysql_num_rows($query) == 0) {
	mysql_query("DELETE FROM nodes WHERE nodes.id=".$res->id);	
}

}


?>
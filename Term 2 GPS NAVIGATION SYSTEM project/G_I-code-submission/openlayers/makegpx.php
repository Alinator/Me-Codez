<?php
/**
Generates gpx track file for all the paths.
Author: Dmitry.
**/
/*
<?xml version="1.0" encoding="UTF-8"?>
<gpx version="1.0">
	<name>Example gpx</name>
	<wpt lat="46.57638889" lon="8.89263889">
		<ele>2372</ele>
		<name>LAGORETICO</name>
	</wpt>
	<trk><name>Example gpx</name><number>1</number><trkseg>
		<trkpt lat="46.57608333" lon="8.89241667"><ele>2376</ele><time>2007-10-14T10:09:57Z</time></trkpt>
		<trkpt lat="46.57619444" lon="8.89252778"><ele>2375</ele><time>2007-10-14T10:10:52Z</time></trkpt>
		<trkpt lat="46.57641667" lon="8.89266667"><ele>2372</ele><time>2007-10-14T10:12:39Z</time></trkpt>
		<trkpt lat="46.57650000" lon="8.89280556"><ele>2373</ele><time>2007-10-14T10:13:12Z</time></trkpt>
		<trkpt lat="46.57638889" lon="8.89302778"><ele>2374</ele><time>2007-10-14T10:13:20Z</time></trkpt>
		<trkpt lat="46.57652778" lon="8.89322222"><ele>2375</ele><time>2007-10-14T10:13:48Z</time></trkpt>
		<trkpt lat="46.57661111" lon="8.89344444"><ele>2376</ele><time>2007-10-14T10:14:08Z</time></trkpt>
	</trkseg></trk>
</gpx>
*/
$dbserver = "localhost";
$dblogin = "root";
$dbpassword = "41681";
$dbname = 'lindholmen2';

$connection = mysql_connect($dbserver, $dblogin, $dbpassword);
mysql_select_db($dbname);

$query = mysql_query('SELECT * FROM edges');

$fh = fopen("track.gpx", "w+");
fputs($fh, '<?xml version="1.0" encoding="UTF-8"?>
<gpx version="1.0">
	<name>Example gpx</name>
	<wpt lat="46.57638889" lon="8.89263889">
		<ele>2372</ele>
		<name>LAGORETICO</name>
	</wpt>
	<trk><name>Example gpx</name><number>1</number>
');


while ($res = mysql_fetch_object($query))  {

$node1 = mysql_query('SELECT * FROM nodes WHERE id='.$res->from);
$node1 = mysql_fetch_object($node1);
$node2 = mysql_query('SELECT * FROM nodes WHERE id='.$res->to);
$node2 = mysql_fetch_object($node2);

fputs($fh, '<trkseg>
		<trkpt lat="'.$node1->lat.'" lon="'.$node1->lon.'"><ele>'.$node1->id.'</ele><time>2007-10-14T10:09:57Z</time></trkpt>
		<trkpt lat="'.$node2->lat.'" lon="'.$node2->lon.'"><ele>'.$node2->id.'</ele><time>2007-10-14T10:10:52Z</time></trkpt>
	</trkseg>');
echo '<trkseg>
		<trkpt lat="'.$node1->lat.'" lon="'.$node1->lon.'"><ele>'.$node1->id.'</ele><time>2007-10-14T10:09:57Z</time></trkpt>
		<trkpt lat="'.$node2->lat.'" lon="'.$node2->lon.'"><ele>'.$node2->id.'</ele><time>2007-10-14T10:10:52Z</time></trkpt>
	</trkseg>';


}

fputs($fh, '</trk>
</gpx>
');
fclose($fh);
echo 'Finished';

?>

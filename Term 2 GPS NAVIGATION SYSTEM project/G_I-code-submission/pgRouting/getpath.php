<?php
/**
pgRouting API. Authors: Dmitry, Kirill & pgRouting docs:)
**/
// Database connection settings
define("PG_DB" , "vastra_gotaland");
define("PG_HOST", "localhost");
define("PG_USER", "postgres");
define("PG_PORT", "5432");
define("TABLE", "ways");
// Retrieve start point
$start = split(' ',$_REQUEST['startpoint']);
$startPoint = array($start[0], $start[1]);
// Retrieve end point
$end = split(' ',$_REQUEST['finalpoint']);
$endPoint = array($end[0], $end[1]);
$startPoint = array($_GET['lon1'], $_GET['lat1']);
$endPoint = array($_GET['lon2'], $_GET['lat2']);

//echo "Startpoint: ".$startPoint.", endpoint: ".$endPoint;

// Find the nearest edge
$startEdge = findNearestEdge($startPoint);
$endEdge = findNearestEdge($endPoint);
//print_r($startEdge);
//echo " ";
//print_r($endEdge);
// FUNCTION findNearestEdge
function findNearestEdge($lonlat) {
// Connect to database
//echo "dbname=".PG_DB." host=".PG_HOST." user=".PG_USER." password=4403031";
$con = pg_connect("dbname=".PG_DB." host=".PG_HOST." user=".PG_USER." password=4403031");
$sql = "SELECT gid, source, target, the_geom, distance(the_geom, 
GeometryFromText('POINT(".$lonlat[0]." ".$lonlat[1].")', 4326)) AS dist 
FROM ways WHERE the_geom && setsrid( 'BOX3D(".($lonlat[0]-0.1)." 
".($lonlat[1]-0.1).", ".($lonlat[0]+0.1)." ".($lonlat[1]+0.1).")'::box3d, 
4326) ORDER BY dist LIMIT 1";
//echo  $sql;
$query = pg_query($con,$sql);
$edge['gid'] = pg_fetch_result($query, 0, 0);
$edge['source'] = pg_fetch_result($query, 0, 1);
$edge['target'] = pg_fetch_result($query, 0, 2);
$edge['the_geom'] = pg_fetch_result($query, 0, 3);
// Close database connection
pg_close($con);
return $edge;
}
//echo "done<br/>";
$con = pg_connect("dbname=".PG_DB." host=".PG_HOST." user=".PG_USER." password=4403031");
//$sql = "SELECT * FROM shortest_path_shooting_star('SELECT gid as id, source::integer, target::integer, length:double precision as cost, x1, y1, x2, y2, rule, to_cost FROM ways', 300, 500, false, false);";
$sql = "SELECT * FROM shortest_path_shooting_star('SELECT gid as id, source::integer, target::integer, length::double precision as cost, x1, y1, x2, y2, rule, to_cost FROM ".TABLE."', ".$startEdge['gid'].",".$endEdge['gid'].", false, false);";
$query = pg_query($con, $sql);
//echo pg_num_rows($query);
while($edge=pg_fetch_assoc($query)) {

//print_r($edge);

//echo '<br>';
$q = "SELECT * FROM ways WHERE gid=".$edge['edge_id'].' LIMIT 1';
$q = pg_query($con, $q);
$e = pg_fetch_assoc($q);
echo '('.$e['y1'].','.$e['x1'].'),';
//echo "<br/>";

};
echo '('.$e['y2'].','.$e['x2'].'),';

?>


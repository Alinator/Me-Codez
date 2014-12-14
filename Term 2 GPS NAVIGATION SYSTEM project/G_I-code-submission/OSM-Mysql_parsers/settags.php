<?php
/**
Setting tags on nodes
Authors: Dmitry & Kirill
**/
$dbserver = "localhost";
$dblogin = "root";
$dbpassword = "41681";
$dbname = 'goteborg';

$connection = mysql_connect($dbserver, $dblogin, $dbpassword);
mysql_select_db($dbname);

echo "Connected to the database\n";
$limit_start=0;
$limit_end=1000;
$limit_counter=0;
while ($limit_counter != $limit_end){
	echo 'Ready to process query: SELECT * FROM edges limit '.$limit_start.','.$limit_end."\n";
	$query = mysql_query('SELECT * FROM edges limit '.$limit_start.','.$limit_end);

	echo "Selected all edges\n";
	while ($res = mysql_fetch_object($query))  {
		echo "Iterating through edges ".$limit_start." to ".$limit_end.", currently at: ".$limit_counter."\n";
		echo "*Found a new edge: from: ".$res->from." to: ".$res->to.", the tags are: ".$res->tags."\n";
		$node1 = mysql_query('SELECT * FROM nodes WHERE id='.$res->from);
		$node1 = mysql_fetch_object($node1);
		echo "*Found starting node\n";
		$tag1 = $node1->tags;
		echo "*Fetched tags for starting node: ".$tag1."\n";
		$node2 = mysql_query('SELECT * FROM nodes WHERE id='.$res->to);
		$node2 = mysql_fetch_object($node2);
		echo "*Found destination node\n";
		$tag2 = $node2->tags;
		echo "*Fetched tags for destination node\n";

		echo "*Ready to process query: UPDATE nodes SET tags=".$tag1.$res->tags.' WHERE nodes.id='.$res->from;
		mysql_query('UPDATE nodes SET tags="'.$tag1.$res->tags.'" WHERE nodes.id='.$res->from);
		echo "*Saved tags for starting node\n";
		echo "*Ready to process query: UPDATE nodes SET tags=".$tag2.$res->tags.' WHERE nodes.id='.$res->to;
		mysql_query('UPDATE nodes SET tags="'.$tag2.$res->tags.'" WHERE nodes.id='.$res->to);
		echo "*Saved tags for destination node\n";
}

$limit_counter++;
		echo "Went through edge: ".$limit_counter; 
}



echo "Done";




?>

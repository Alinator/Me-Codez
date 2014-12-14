<?php
/**
Parsing edges from OSM (creating edges from ways)
Author: Dmitry
**/
echo "Started the parse xml php file";

function load_xml_to_db($url) {
echo "loaded xml_to_db function";
$dbserver = "localhost";
$dblogin = "root";
$dbpassword = "1212";
$dbname = 'lindholmen50000';

$connection = mysql_connect($dbserver, $dblogin, $dbpassword);
mysql_select_db($dbname);
echo "loaded db connection\n";

//$xml = simplexml_load_file($url);

$reader = new XMLReader();
$reader->open($url);

$id = 0;
$way_id = 0;
$inside_way = false;
while ($reader->read()) {
    switch ($reader->nodeType) {

        case (XMLREADER::ELEMENT):
	if ($reader->localName == "nd") {
	    if ($inside_way == true)	 {
	 	$nodes[] = $reader->getAttribute("ref");			
	    }		
	} 
	if ($reader->localName == "tag") {
	    if ($inside_way == true)	 {
	 	$tags[] = $reader->getAttribute("k")."=".$reader->getAttribute("v");			
	    }		
	} 
	if ($reader->localName == "way") {
	       $inside_way = true;
	       $nodes = Array(); 
	       $tags = Array();	       
	}
	break;
	case (XMLReader::END_ELEMENT): 
		if ($reader->localName == "node") {
			echo "Node skipped <br/>";
		}
		if ($reader->localName == "way") {
		$t = '';
		$n = '';
			for ($i = 0; $i < count($tags); $i++) {
				$t .= $tags[$i].","; 	
			}
			echo $t.'<br/>';
			if (strpos($t, 'oneway=yes') == false) {
				$one_way = false; 
			} else {
				$one_way = true;
			}

			for ($i = 0; $i < count($nodes) - 1; $i++) {
	                  mysql_query('INSERT INTO edges VALUES('.$nodes[$i].', '.$nodes[$i+1].', 0)');
			echo 'INSERT INTO edges VALUES('.$nodes[$i].', '.$nodes[$i+1].', 0)';
				if ($one_way == false) {
 					mysql_query('INSERT INTO edges VALUES('.$nodes[$i+1].', '.$nodes[$i].', 0)');
				}
				
			}
			for ($i = 0; $i < count($nodes); $i++) {

			
				$q = mysql_query('SELECT * FROM nodes WHERE id='.$nodes[$i]);
				$q = mysql_fetch_object($q);
				mysql_query('UPDATE nodes SET tags="'.($q->tags).$t.'" WHERE id='.$nodes[$i]);
				}
		
				
		}
	}

   

$id++;
echo $id."\n";
}

echo "Finished.";

 mysql_close($connection); //close the connection

} 

                    

load_xml_to_db("lindholmen50000.osm");
?>

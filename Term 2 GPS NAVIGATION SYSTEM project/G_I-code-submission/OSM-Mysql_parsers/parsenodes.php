<?php
/**
Parsing nodes from OSM
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
while ($reader->read()) {
    switch ($reader->nodeType) {
        case (XMLREADER::ELEMENT):
        if ($reader->localName == "node") {
	echo $reader->getAttribute("lat").$reader->getAttribute("lon")."<br />";


		$tags = '';
echo '!'.$reader->readInnerXML().'!';
		if ($reader->readInnerXML() != '') {
		$inner_reader = XMLReader::xml($reader->readInnerXML());

		while ($inner_reader->read()) {
			    switch ($inner_reader->nodeType) {
			        case (XMLREADER::ELEMENT):
				        if ($inner_reader->localName == "tag") {	
						$tags = $tags.$inner_reader->getAttribute("k").'='.$inner_reader->getAttribute("v").',';

					}
				}

		}
		}
if (($reader->getAttribute("lat") > 57.456) && ($reader->getAttribute("lat") < 57.88) && ($reader->getAttribute("lon") > 11.443	) && ($reader->getAttribute("lon") < 12.536)) {
		mysql_query("INSERT INTO nodes VALUES(".$reader->getAttribute("id").", ".$reader->getAttribute("lat").", ".$reader->getAttribute("lon").", '".$tags."');");
	//echo "INSERT INTO nodes VALUES(".$reader->getAttribute("id").", ".$reader->getAttribute("lat").", ".$reader->getAttribute("lon").", '".$tags."');";
}
		echo "Node # ".$id."<br />\n";		
        }

        
    }

$id++;
}

echo "Finished.";

 mysql_close($connection); //close the connection

} 

                    

load_xml_to_db("lindholmen50000.osm");
?>

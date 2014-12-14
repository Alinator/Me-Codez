<?php
    
    
    $name;
	$value;
	header("Content-type: text/plain");
    foreach($_SERVER as $name => $value){
    	echo $name." = ".$value."\n";
    }
?>
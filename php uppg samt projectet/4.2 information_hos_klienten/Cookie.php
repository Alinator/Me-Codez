<?php
	$time = time(); 
	$date = date('Y-m-d H:i:s',$time);
	
	setcookie("tid", $date, time()+10800);
	setcookie("namn", "Coooookie Monstah!", time()+10800);
	
	echo "The cookies have been created.<br>";
	
	echo "Time: ".$_COOKIE["tid"]."<br>";
	echo "namn: ".$_COOKIE["namn"];
	
?>
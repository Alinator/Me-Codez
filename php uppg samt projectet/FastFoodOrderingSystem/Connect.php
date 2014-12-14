<?php
   		$name = "root";
		$pas = "root";
		$dbname = "logindatabase";
		$con = mysql_connect("127.0.0.1",$name,$pas);
		mysql_select_db($dbname,$con);
?>
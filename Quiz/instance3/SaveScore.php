<?php
	session_start();
	$points = $_REQUEST['a'];
	$contestant= $_SESSION['contestant'];
	
	$leaderboard = "contestants/".$contestant.".txt";
	$fh = fopen($leaderboard, 'w');	
	
	fwrite($fh,$points);
	fclose($fh);
	Echo "Success!";
	
?>
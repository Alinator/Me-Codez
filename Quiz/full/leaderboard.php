<?php
		$fullname = $_GET["fullname"];
		$foldername = $_GET["foldername"];	
		$leaderboard = "contestants/".$fullname.".txt";
		$fh = fopen($leaderboard, 'w');	
		
		fclose($fh);
		
		header( 'Location: MainQuiz.php?fullname='.urlencode($fullname).'&foldername='.$foldername );

		
				
		
?>
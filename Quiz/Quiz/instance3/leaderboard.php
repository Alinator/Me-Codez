<?php
		$fullname = $_POST["firstname"];
		$foldername = $_POST["foldername"];	
		$leaderboard = "contestants/".$fullname.".txt";
		$fh = fopen($leaderboard, 'w');	
		
		fclose($fh);
		
		header( 'Location: MainQuiz.php?fullname='.urlencode($fullname).'&foldername='.$foldername );

		
				
		
?>
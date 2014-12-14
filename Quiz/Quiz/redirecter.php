<?php

$foldername = $_POST['foldername'];
$fullname = $_POST['firstname'];


$points = 'unchecked';
$achiev = 'unchecked';
$badges = 'unchecked';
$levels = 'unchecked';


$selected_radio = $_POST['btn'];

	if ($selected_radio == 'points') {
		header('Location: http://people.dsv.su.se/~alna0203/TheQuiz1.0/instance1/points.php?fullname='.urlencode($fullname).'&foldername='.$foldername);
	} else if ($selected_radio == 'leader') {
		header('Location: http://people.dsv.su.se/~alna0203/TheQuiz1.0/instance2/leaderboard.php?fullname='.urlencode($fullname).'&foldername='.$foldername);
	
	}else if ($selected_radio == 'badges') {
		header('Location: http://people.dsv.su.se/~alna0203/TheQuiz1.0/instance3/badges.php?fullname='.urlencode($fullname).'&foldername='.$foldername);
	
	}else if ($selected_radio == 'levels') {
		header('Location: http://people.dsv.su.se/~alna0203/TheQuiz1.0/instance4/levels.php?fullname='.urlencode($fullname).'&foldername='.$foldername);
	}else if ($selected_radio == 'fullquiz') {
		header('Location: http://people.dsv.su.se/~alna0203/TheQuiz1.0/full/leaderboard.php?fullname='.urlencode($fullname).'&foldername='.$foldername);
	}else{
		echo " Ett fel har uppstått, se till att samtliga fält har blivit fyllda";
		echo "<br>";
		echo "Du skickas tillbaka om 3 sekunder";
		header("Refresh: 3; start.html");
	}
	
?>
<?php
  session_start();
   
	$dir = "contestants/*";

  $myscore;
  
  $myposition=0;

    // loop through each file
    foreach (glob($dir) as $file) {
		$myposition++;
		if($file == "contestants/".$_SESSION['contestant'].".txt"){
		// use file() to read the lines of the file
        	$myscore = file_get_contents($file);
		}
		
	}
	
	foreach (glob($dir) as $file) {
		
		if($file !== "contestants/".$_SESSION['contestant'].".txt"){
			
        	$tmpscore = file_get_contents($file);
			
			if($myscore > $tmpscore){
				$myposition--;
			}
		}
	}
	
	echo "<strong>".$myposition."</strong>";
?>
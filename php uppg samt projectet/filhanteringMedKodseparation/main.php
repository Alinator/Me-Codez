<?php
	
	
	$numberofvisitors;
	
	$file="amount.txt";
	$fileopened= fopen($file,"r");
	$amountOfVisited= fread($fileopened,filesize($file));
	fclose($fileopened);
	
	if($amountOfVisited == 0){
		
		$fileopenedforwriting= fopen($file,"w");
		fwrite($fileopenedforwriting,1);
		echo $amountOfVisited+1;
	}else{
		
		$amountOfVisited=$amountOfVisited+1;
		$fileopenedforwriting= fopen($file,"w");
		fwrite($fileopenedforwriting,$amountOfVisited);
	
	    echo $amountOfVisited;
	
	}
	
	fclose($fileopenedforwriting);
?>
<?php
    $allowedFileTypes=array("gif", "jpeg", "jpg", "png","txt");
    
	$x =explode(".", $_FILES["file"]["name"]);
    
    $fileType = end($x);
	
	if(
(($_FILES["file"]["type"] == "image/jpeg")
|| ($_FILES["file"]["type"] == "image/jpg")
|| ($_FILES["file"]["type"] == "image/png")
|| $_FILES["file"]["type"] == "text/plain")
&& in_array($fileType, $allowedFileTypes)){
		
		
		if(($_FILES["file"]["type"] == "image/jpeg")){
			
			header("content-type: image/jpeg");
			move_uploaded_file($_FILES["file"]["tmp_name"], "uploads/".$_FILES["file"]["name"]) or die("not uploaded :(");
			$content = file_get_contents("uploads/".$_FILES['file']['name']);
			echo $content;
			
		}else if(($_FILES["file"]["type"] == "image/jpg")){
			
			header("content-type: image/jpg");
			move_uploaded_file($_FILES["file"]["tmp_name"], "uploads/".$_FILES["file"]["name"]) or die("not uploaded :(");
			$content = file_get_contents("uploads/".$_FILES['file']['name']);
			echo $content;
			
		}else if(($_FILES["file"]["type"] == "image/png")){
					
			header("content-type: image/png");
			move_uploaded_file($_FILES["file"]["tmp_name"], "uploads/".$_FILES["file"]["name"]) or die("not uploaded :(");
			$content = file_get_contents("uploads/".$_FILES['file']['name']);
			echo $content;	
			
		}else if($_FILES["file"]["type"] == "text/plain"){
				
				header("content-type: text/plain");
				move_uploaded_file($_FILES["file"]["tmp_name"], "uploads/".$_FILES["file"]["name"]) or die("not uploaded :(");
				$content = file_get_contents("uploads/".$_FILES['file']['name']);
				echo $content;
		}
		
	}else{
		
		echo "File Name: ".$_FILES["file"]["name"]."<br>";
		echo "File Size: ".$_FILES["file"]["size"]."<br>"; 
		echo "File Mime Type:".$_FILES["file"]["type"];
	}

?>
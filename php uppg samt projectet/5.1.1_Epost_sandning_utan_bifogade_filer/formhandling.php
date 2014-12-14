<?php
  	$password = "mypassword";
	
	if($password == $_POST["password"]){
		if($_POST["From"] != ""){
			
			
			$from = $_POST["From"];
			$to = $_POST["To"];
			$Cc = $_POST["Cc"];
			$Bcc = $_POST["Bcc"];
			$Case = $_POST["Case"];
			$message =$_POST["message"];
			
			$headers= "From:" . $from. "\r\n";
			$headers.= "Cc:" . $Cc. "\r\n";
			$headers.= "Bcc:".$Bcc. "\r\n";
			
			mail($to,$Case,$message,$headers);
			echo "Observera! Detta meddelande är sänt från ett formulär på Internet och avsändaren kan vara felaktig!";
			
		}else{
			echo "From cannot be empty, there cannot be an anynomous sender!,please fill in and try again!";
			echo "<a href='mailForm.php'> Try again</a>";
		}
	}else{
		echo "password is not correct, please try again with the correct password.";
		echo "<a href='mailForm.php'> Try again</a>";
		
	}
?>
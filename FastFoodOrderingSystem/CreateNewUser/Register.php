<?php
		error_reporting('E_ALL ^ E_NOTICE');
		
		$fullname="";
		$password="";
		$email="";
		$adress="";
		$telefon="";
		$passwordBeforeHash=$_POST['choosepassword'];
		
		$fullname= $_POST['fullname'];
		$password= md5($_POST['choosepassword']);
		$email = $_POST["email"];
		$adress = $_POST["adress"];
		$telefon = $_POST["telefon"];
		
		$secret = md5(time() . $email . 'foodOrderingSystem');
		$secret = substr($secret, 1,5);
		
		loadIntoDatabase($fullname,$password,$email,$adress,$telefon,$secret,$passwordBeforeHash);
		
		function loadIntoDatabase($fullname,$password,$email,$adress,$telefon,$secret,$passwordBeforeHash){
			require "../Connect.php";
			mysql_query("INSERT INTO accountinformation(fullname,password,email,adress,telefon,kundsecret)
			 VALUES ('$fullname','$password','$email','$adress','$telefon','$secret')")
			or  die("".mysql_error());
			echo "Successful Registration!";
			sendEmailNotification($fullname, $email, $passwordBeforeHash, $secret);
		}
		
		function sendEmailNotification($fullname,$email,$passwordBeforeHash,$secret){
			
			$message= "Hej ". $fullname." och välkommen som ny kund! \n
			Härmed skickar vi dig dina inloggningsuppgifter för Food Online Ordering systemet.\n
			\n
			Username: ".$email."\n
			Lösenord: ".$passwordBeforeHash."\n
			\n
			När du väl är inloggad och innan du gjort beställningen kommer du behöva skriv en kod specifik till dig som kund i \n
			fältet 'Kundsecret', din kundsecret kod är: ".$secret."\n
			Kom ihåg att spara denna kod och att komma ihåg den, den kommer att behövas vid varje ny beställning!\n
			\n
			Tack för att du valt att beställa genom Food Online Ordering!\n
			MVH\n
			Team Food Online Ordering System.";
			
			mail($email,"Dina inloggningsuppgifter till Food Online Ordering",$message);
			echo $email. "MAIL SENT?";
		}
		
		
?>
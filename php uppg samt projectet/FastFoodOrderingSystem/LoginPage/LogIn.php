<?php
   error_reporting('E_ALL ^ E_NOTICE');
   session_start();
   require "../Connect.php";
   
   	$table_name="accountinformation";
	$username=$_POST["email"];
	$password=md5($_POST["password"]);
   	$sql="SELECT * FROM $table_name WHERE email='$username' and password='$password'";
	$result=mysql_query($sql);

	$count=mysql_num_rows($result);
	if($count==1){
		$result2= mysql_fetch_array($result);
		$name = $result2["fullname"];
		$_SESSION['costumer']=$name;
		header('Location: Success.php');
		
	}else if($count != 1){
		echo "Wrong Username or Password please try again.";
		header("Refresh: 2;url=SignInPage.html");
	}
?>
<?php
    error_reporting('E_ALL ^ E_NOTICE');
    $name = "alna0203";
	$pas = "WaileeB2Shai";
	$dbname = "alna0203";
	$con = mysql_connect("mysql.dsv.su.se",$name,$pas);
	mysql_select_db($dbname,$con);
	
	$nameofproduct=$_POST["header"];
	$priceofproduct=$_POST["price"];
	$descriptionOfItem=$_POST["description"];
	
	if ($_FILES["file"]["error"] > 0)
  {
  echo "Error: " . $_FILES["file"]["error"] . "<br>";
  }
else
  {
  	echo "Upload: " . $_FILES["file"]["name"] . "<br>";
  	echo "Type: " . $_FILES["file"]["type"] . "<br>";
  	echo "Stored in: " . $_FILES["file"]["tmp_name"]. "<br>";
  	
  
      move_uploaded_file($_FILES["file"]["tmp_name"],"uploads/".$_FILES["file"]["name"]);  
  }
  
  $imageLocation="uploads/".$_FILES["file"]["name"];
  
  mysql_query("INSERT INTO menuitems(imagelocation,heading,description,price)
			 VALUES ('$imageLocation','$nameofproduct','$descriptionOfItem','$priceofproduct')")
			or  die("".mysql_error());
			echo "menuItem added successfully!";
  
  
?>
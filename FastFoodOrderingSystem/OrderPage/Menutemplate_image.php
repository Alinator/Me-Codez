
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.png">

    <title>Logga in</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">

  </head>

  <body>

    <div class="container">
<?php
		// connect to the database.
		$name = "alna0203";
		$pas = "WaileeB2Shai";
		$dbname = "alna0203";
		$con = mysql_connect("mysql.dsv.su.se",$name,$pas);
		mysql_select_db($dbname,$con);
		
		$query = mysql_query("SELECT * FROM menuitems WHERE heading='".$_GET['itemname']."'");
		// to show the image from the database.
		$rows = mysql_fetch_array($query);
		
		echo "<html>
				<body>
				
				<img src='../Administrator Panel/".$rows['imagelocation']."' height='500'><br>
				
				</body>
		
		</html>";
		
		
		echo "<h2>".$rows["heading"]."</h2><br>";
		echo "Description of item:<br>".$rows["description"]."<br><br>";
		echo "Price of item: ".$rows["price"]." Kr";
		exit();

?>
</div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
  </body>
</html>
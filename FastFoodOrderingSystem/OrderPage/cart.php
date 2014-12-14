
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.png">

    <title>Food Online ordering</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">

  </head>

  <body>

    <div class="container">
<?php
error_reporting('E_ALL ^ E_NOTICE');
  	session_start();
  	if($_SESSION['cart']) { 
	
		$name = "alna0203";
		$pas = "WaileeB2Shai";
		$dbname = "alna0203";
		$con = mysql_connect("mysql.dsv.su.se",$name,$pas);
		mysql_select_db($dbname,$con);
		
	echo $_SESSION["costumer"]."'s kundvagn";
	
    echo "<table border=\"1\" padding=\"3\" width=\"40%\">"; 

 
    foreach($_SESSION['cart'] as $product_name => $quantity) { 
		
        $sql = sprintf("SELECT heading, price FROM menuitems WHERE heading = %d;",
		$product_name); 

        $result = mysql_query($sql);

        if(mysql_num_rows($result) > 0) {

            list($pname, $price) = mysql_fetch_row($result);

            $line_cost = $price * $quantity; 
            $total = $total + $line_cost; 
			
            echo "<tr>";
          
            echo "<td align=\"center\">$product_name</td>";
           
            echo "<td align=\"center\">$quantity <a href=\"cartHandle.php?function=removeItem&name=$product_name\">Delete</a></td>";
            echo "<td align=\"center\">$line_cost</td>";

            echo "</tr>";

        }

    }


    echo "<tr>";
    echo "<td colspan=\"2\" align=\"right\">Total</td>";
    echo "<td align=\"right\">$total</td>";
    echo "</tr>";

    echo "<tr>";
    echo "<td colspan=\"3\" align=\"right\"><a href=\"cartHandle.php?function=emptyCart\" onclick=\"return confirm('Are you sure?');\">Empty Cart</a></td>";
    echo "</tr>"; 
    echo "</table>";



}else{

    echo "You have no items in your shopping cart. You will be redirected back to the order page.";
	header("Refresh: 2;url=../OrderPage/order_page.php");

} 
?>
</div> <!-- /container -->
  </body>
</html>
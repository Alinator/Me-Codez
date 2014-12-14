
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
error_reporting('E_ALL ^ E_NOTICE');
session_start();
if ($_SESSION['costumer'] == '')
{
    Echo "You need to login for this page: you will now be redirected to the login page";
	header("Refresh: 2;url=../LoginPage/SignInPage.html");
}else{
	
    	$name = "root";
		$pas = "root";
		$dbname = "MenuDatabase";
		$con = mysql_connect("127.0.0.1",$name,$pas);
		mysql_select_db($dbname,$con);
		
		$query = mysql_query("SELECT * FROM menuitems");

		$heading="";
		$description="";
		$price="";
		
		$countItems=0;
		header("Content-type: text/Html");
		echo "<h3 class='form-signin-heading'>Welcome ".$_SESSION["costumer"]." to restaurant:Elinas Grill!</h3><br>";
		echo "<a href='cart.php'> 
  				<img src='cart.png' border='0' />
				</a> Din kundvagn innehåller ".$_SESSION["itemsincart"]." objekt.";
		Echo "<h2 class='form-signin-heading'>Dagens Meny</h2><br>";
		Echo "below you can click on the menu items to see description over the Items<br>
		fill in the quantity of item(s) you want to buy and click on the shopper icon to select the item<br>
		<br>
		Step 1) Select the food item(s) of your choice:";
		Echo"<table border='5'>
			<tr>
			<td>Menu Item</td>
			<td>Price for item</td>
			<td>Buy</td>
			<form method='post' action=''>";
			
        WHILE($rows = mysql_fetch_array($query)):
  			$heading= urlencode($rows["heading"]);
			$tmp = $heading;
            Echo "
			<tr>
			<td><a href=Menutemplate_image.php?itemname=".$heading.">".urldecode($tmp)."   </a><br></td>
			<td>".$rows["price"]." kr</td>
			<td><a href='cartHandle.php?name=".$heading."&function=addItem'> 
  				<img src='cart.png' border='0' />
				</a></td>";
        endwhile;
		Echo"</tr>
			</table><br><br><br>";
			
		echo "Step 2) Please fill in any information you want the restaurant to know regarding your order:<br>"	;
		echo "<textarea name='information' rows='4' cols='100'>
				example: en hamburgare utan tomater....etc 
				</textarea><br><br><br><br>
				Step 3) Fill in your costumer secret code*: <input type='text' name='costumersecret'><br><br>
				Step 4) Submit your order to the restaurant:<br><br>
				 <button class='btn btn-lg btn-primary btn-block' name='submit' type='submit'>submit order</button>
				</form>";
				}				
?>	
</div> <!-- /container -->
  </body>
</html>
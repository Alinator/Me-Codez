<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.png">

    <title>Food Online Ordering</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">

  </head>

  <body>

    <div class="container">
<?php session_start();
    
    echo "<h3 class='form-signin-heading'>Tack för din order! 
    \n Välkommen till restaurangen om 15 min för att hämta din mat.</h3><br>";
	unset($_SESSION["itemsincart"]);
	
    header("Refresh: 5;url=../LoginPage/SignInPage.html");
    
?>
</div> <!-- /container -->
  </body>
</html>
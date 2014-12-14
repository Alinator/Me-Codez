<?php
error_reporting('E_ALL ^ E_NOTICE');
    session_start();
    $product_name=$_GET["name"];
	$function = $_GET["function"];
	$quantity=0;
	echo "You have selected: ".$product_name;
	
	switch($function){
		
		case "addItem":
			$_SESSION["cart"][$product_name]++;
			$_SESSION["itemsincart"]++;
			header("Refresh: 0;url=../OrderPage/order_page.php");
			break;
			
		case "removeItem":
			$_SESSION['cart'][$product_name]--;
			if($_SESSION['cart'][$product_name] == 0) unset($_SESSION['cart'][$product_name]);
			echo "Item successfully deleted from cart! you will now be redirected to the cart ";
			$_SESSION["itemsincart"]--;
			header("Refresh: 0;url=../OrderPage/order_page.php");
			header("Refresh: 0;url=../OrderPage/cart.php");
			break;
			
		case "emptyCart":
			unset($_SESSION['cart']);
			echo "Cart successfully emptied! you will now be redirected to the order page";
			$quantity=0;
			unset($_SESSION["itemsincart"]);
			header("Refresh: 0;url=../OrderPage/order_page.php");
			break;
	}
?>
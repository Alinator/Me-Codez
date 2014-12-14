<?php	session_start();
    // check if the costumer secret is correct if correct the procede to next step, take the name of the person as well from the database
   		error_reporting('E_ALL ^ E_NOTICE');
		$ordercostumername="";
		$ordercostumerphone="";
		$orderID="";
    	$name = "alna0203";
		$pas = "WaileeB2Shai";
		$dbname = "alna0203";
		$con = mysql_connect("mysql.dsv.su.se",$name,$pas);
		mysql_select_db($dbname,$con);
		
		echo "".$POST["costumersecret"];
    	
		 $query = mysql_query("SELECT * FROM accountinformation WHERE kundsecret='".$_POST["costumersecret"]."'");
    	 if($rows = mysql_fetch_array($query) > 0){
    	 	
    	 	 // fetch the order list from the cart and generate ordernumber
    	 	 		
					// $dbname = "alna0203";
					// $con = mysql_connect("mysql.dsv.su.se",$name,$pas);
					// mysql_select_db($dbname,$con);
						$ordercostumername=$rows["fullname"];
						$ordercostumeremail=$rows["email"];
						$ordercostumerphone=$rows["telefon"];
						$orderID = uniqid();
						$file="../administrator Panel/orders/".$orderID.".txt";
						$fileopened= fopen($file,"w");
						fwrite($fileopened,"Item                    ");
						fwrite($fileopened,"Quantity        \n\n");
    	 	  foreach($_SESSION['cart'] as $product_name => $quantity) {
    	 	  		
      			 	$sql = sprintf("SELECT heading, price FROM menuitems WHERE heading = %d;",$product_name); 
       				$result = mysql_query($sql);

        			if(mysql_num_rows($result) > 0) {

            		list($pname, $price) = mysql_fetch_row($result);

            		$line_cost = $price * $quantity; 
            		$total = $total + $line_cost; 
					
					echo $product_name."   ".$quantity;
					
					// write the details to the file
					fwrite($fileopened,$product_name."                    ");
					fwrite($fileopened,$quantity."   \n\n");
					
					}
			  }
			fwrite($fileopened,"Information to the restaurant:\n".$_POST["information"]."\n\n");
			fwrite($fileopened,"Total Price:   ".$total." Kr\n\n");
			fwrite($fileopened,"Costumer name:   ".$ordercostumername." with telephone number: ".$ordercostumerphone);
			echo"successfull";  
			fclose($fileopened);
			mysql_close($con);
    
    // put all of the data inside the orders database plus the order ID.
	$linkToOrderDetailsFolder="../Administrator Panel/orders/".$orderID;
	
	
		
		$name = "alna0203";
		$pas = "WaileeB2Shai";
		$orderdb="alna0203";
		$con2 = mysql_connect("mysql.dsv.su.se",$name,$pas);
		mysql_select_db($orderdb,$con2);	
			mysql_query("INSERT INTO orders(orderid,orderdetails)
			 VALUES ('$orderID','$linkToOrderDetailsFolder')")
			or  die("".mysql_error());
			echo "Successful submission of order!";


			
			$message= "Hej ". $costumername."! \n
			du har idag gjort en ny beställning med vårat system.Tack för din beställning och välkommen åter";
			
			mail($costumerEmail,"Your new order today",$message);
			echo $email. "MAIL SENT?";
			mysql_close($con2);
			header("Refresh: 0;url=../OrderPage/successfull_order.php");
			
		
	
     }else{
		echo "wrooooooooooooooooooooooooooooooong!";
    }
    // print a message out when order has been placed...destroy all sessions and return back to login page.
    
?>
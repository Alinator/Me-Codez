<?php
	header("Content-type: text/plain");
    echo " HELLOW MIZTER " . htmlspecialchars($_GET["name"]) . '!'. "\n";
	echo " Your email is: " . htmlspecialchars($_GET["email"]) . '!';
?>
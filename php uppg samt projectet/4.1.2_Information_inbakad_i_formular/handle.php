<?php
    $name= $_POST["name"];
?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>New Web Project</title>
	</head>
	<body>
		
		<form name="name" action="handle2.php" method="post">
		<input type="hidden" name="name" value="<?=$name?>">	
		Email: <input type="text" name="email">
		<input type="submit" value="Submit">
		</form>
		
	</body>
</html>
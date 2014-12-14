<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
		<link rel="stylesheet" type="text/css" href="./instancestyle.css" />
		<script src="jquery.js" type="text/javascript"></script>
		<script src="achievementbox.js" type="text/javascript"></script>
		<script src="badgesbox.js" type="text/javascript"></script>
		<script src="xpbarprogress.js" type="text/javascript"></script>
		<script src="experiencebox.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="achievementboxcss.css" />
		<link rel="stylesheet" type="text/css" href="xpbar.css" />
		<link rel="stylesheet" type="text/css" href="playerstats.css" />
		<title>Thesis Project-Gamification 2014</title>
		<meta name="author" content="Ali" />
		
	</head>
	<body>
	<div id="playerstatsname">
		<br>
			Your name:<div id="playername" style="display: inline"><strong> <?php session_start();
															$_SESSION['contestant'] = urldecode($_GET["fullname"]);  
															  echo urldecode($_GET["fullname"]); ?></strong></div>
	</div>
		
	<div id="playerstatslevel">
		Your Level:
	</div>
	
	<div id="playerstatsbadges">
		
		Amount of badges earned: 
	</div>
	
	<div id="playerstatsxp">
		Amount of points earned: <div id="playerxp" style="display: inline"> </div>
	</div>
	
	<div id="playerstatsleaderboard">
		Your position as a contestant: <div id ="playerposition"style="display: inline"><strong><?php $directory = "contestants/";
																							if (glob($directory . "*.*") != false)
																							{
																							 $filecount = count(glob($directory . "*.*"));
																							 echo $filecount;
																							}
																							else
																							{
																							 echo 0;
																							}
																							?></strong></div>/<?php 
																							$directory = "contestants/"; // dir location
																							if (glob($directory . "*.*") != false)
																							{
																							 $filecount = count(glob($directory . "*.*"));
																							 echo $filecount;
																							}
																							else
																							{
																							 echo 0;
																							}
																							?>
																							
	</div>	
	
	<div id="popup-box-achievement" class="popup-info" style="visibility: hidden">
		
		<label> <br>
			
				<br>
		</label>
	</div>
	
	<div id="popup-box-points" class="popup-info" style="visibility: hidden">
		
	</div> 
	<div class="meter orange nostripes" style="visibility: hidden">
	<span style="width: 0%"></span>
	</div>
	
			<script>
			function changeQuizPage(){
				$(function(){
					foldername = "<?php Print($_GET["foldername"]); ?>";
					questionsanswered.push("Quiz1.php");
					var randomIndex = Math.floor(Math.random()*filearray.length);
					var quiz = filearray[randomIndex];
					questionsanswered.push(quiz);
      				$("#includedContent").load(foldername+"/"+quiz); 
				});
			}
			changeQuizPage();
			</script>
			
			<div id="includedContent"></div>
			<div id="correct"></div>
			<br>
			<br>
			<div id="achievement" style="visibility: hidden">
			</div>
			<div id="position"></div>
			<!-- <br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<div id="allright">
				
			</div> -->	
			</body>
</html>
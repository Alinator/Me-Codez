<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Quiz 2</title>
		<meta name="author" content="Ali" />
		<!-- Date: 2014-03-11 -->
		<script>
		function changequiz(){
			$(function(){
						var quiz="";
				var counter=0;
				while(true){
				var randomIndex = Math.floor(Math.random()*filearray.length);
				quiz = filearray[randomIndex];
					
					if($.inArray(quiz,questionsanswered) != -1){
						counter++;
						if(counter == 40){
							quiz="finished.php";
							break;
						}
					}else{
						questionsanswered.push(quiz);
						break;
					}
				
				
				}	
				numberofquestions++;
      			$("#includedContent").load(foldername+"/"+quiz);  
			});
		}
		</script>
			
	</head>
	
	<body>
   			<b> Vilken frukt innehåller mest kalorier per 100g? </b><br>
   				<br>
				<input type=radio name="q1" value="a" onclick="changequiz()">a) Apelsin <br>
				<input type=radio name="q1" value="b" onclick="changequiz(),increasexp(100)">B) Vindruvor <br>
				<input type=radio name="q1" value="c" onclick="changequiz()">c) Granatpple <br>   
	<div id="includedContent"> </div>
	
	</body>
</html>
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
						if(counter == 20){
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
   			<b> Vem målande Mona Lisa?</b><br>
   				<br>
				<input type=radio name="q1" value="a" onclick="changequiz(),increasexp(150)">a) Leonardo da Vinci <br>
				<input type=radio name="q1" value="b" onclick="changequiz(),wronganswer('wrong')">B) Francesco del Giocondos<br>
				<input type=radio name="q1" value="c" onclick="changequiz(),wronganswer('wrong')">c) Isabella d'Este<br>   
	
	</body>
</html>
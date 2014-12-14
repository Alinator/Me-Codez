/**
 * @author Ali
 */
var experiencepoints=0;
var contestant="";
var correctanswered=0;
var questionsanswered = new Array();
var numberofquestions=0;
var tmpdata=0;
var filearray = [
			    'Quiz1.php',
			    'Quiz2.php',
			    'Quiz3.php',
			    'Quiz4.php',
			    'Quiz5.php',
			    'Quiz6.php',
			    'Quiz7.php',
			    'Quiz8.php',
			    'Quiz9.php',
			    'Quiz10.php',
			    'Quiz11.php',
			    'Quiz12.php',
			    'Quiz13.php',
			    'Quiz14.php',
			    'Quiz15.php',
			    'Quiz16.php',
			    'Quiz17.php',
			    'Quiz18.php',
			    'Quiz19.php',
			    'Quiz20.php',
			];

function increasexp(amount){
				correctanswered++;
				correctanswer("correct");
				$(".meter > span").each(function() {
		$(this).data("origWidth", $(this).width()).animate({
			width: $(this).data("origWidth")+amount
			}, 1200);
			
			var relativePercentage = ($(this).width()/$(this).parent('div').width())*100;
			experiencepoints += amount;
			if(relativePercentage  > 90 ){
				$(this).data("origWidth", $(this).width()).animate({
			width: 0
			}, 900);
			
			increaseLevel();
			showexperienceandposition();
			}
			// if(correctanswered == 3){
					// badgebox("brons");
				// }else if(correctanswered == 10){
				 	// document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: BEGINNER</h2> </center><br>";
					// $('#achievement').fadeIn('slow');	
					// Box(100);
				// }else if(correctanswered == 15){
					// badgebox("silver");
				// }else if(correctanswered == 20){
				 	// document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: Intermediate</h2> </center><br>";
					// $('#achievement').fadeIn('slow');	
					// Box(100);
				// }else if(correctanswered == 25){
					// badgebox("gold");
				// }else if(correctanswered == 30){
				 	// document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: Advanced</h2> </center><br>";
					// $('#achievement').fadeIn('slow');
				// }else if(correctanswered == 40){
					// badgebox("platina");
					// document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: MASTER</h2> </center><br>";
					// $('#achievement').fadeIn('slow');				
				// }else{
					 //Box(100);
				// }
// 				
			// }else{
// 				
				 showexperienceandposition();
				// if(correctanswered == 3){
					// badgebox("brons");
				// }else if(correctanswered == 10){
				 	// document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: BEGINNER</h2> </center><br>";
					// $('#achievement').fadeIn('slow');	
				// }else if(correctanswered == 15){
					// badgebox("silver");
				// }else if(correctanswered == 20){
				 	// document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: Intermediate</h2> </center><br>";
					// $('#achievement').fadeIn('slow');	
				// }else if(correctanswered == 25){
					// badgebox("gold");
				// }else if(correctanswered == 30){
				 	// document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: Advanced</h2> </center><br>";
					// $('#achievement').fadeIn('slow');
				// }else if(correctanswered == 40){
					// badgebox("platina");
					// document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: MASTER</h2> </center><br>";
					// $('#achievement').fadeIn('slow');				
				// }else{
					 //Box(100);
				// }
// 				
				 // if(numberofquestions == 3 && correctanswered == 3){
					// document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>HAT TRICK</h2> </center><br>";
					// $('#achievement').fadeIn('slow');
				 // }				
// 				
			// }
			
		});
}
		var counter = 0;
		function increaseLevel(){
			counter++;
			document.getElementById('playerstatslevel').innerHTML = 'Your Level is: <strong>'+counter+'<strong>';
			document.getElementById('achievement').innerHTML  = "<center><br>CONGRATULATIONS!<br> <h2>YOU HAVE REACHED LEVEL "+counter+"!</h2> </center><br>";
			$('#achievement').fadeIn('slow');
			setTimeout(fade_out, 3000);
		}
		function fade_out(){
			$("#achievement").fadeOut();
		}
		var position=0;
		function showexperienceandposition(){
			var tmpdata=0;
			var position=0;
			document.getElementById('playerxp').innerHTML = '<strong>'+experiencepoints+'<strong>';
			
			$.post("SaveScore.php", {a: experiencepoints}, function(data){                                          
				});
			
			$.post("CountPosition.php", {}, function(data){  	
				$('#playerposition').html(data);
					
					positiondialogue(data);
					
			});
	
			
		}
		function wronganswer(answer){
			document.getElementById('correct').innerHTML  = "<center><h2>"+answer+" Answer!</h2> </center><br>";
			$('#correct').fadeIn('slow');
			setTimeout(fade_out4, 500);
		}
		function correctanswer(answer){
			document.getElementById('correct').innerHTML  = "<center><h2>"+answer+" Answer!</h2> </center><br>";
			$('#correct').fadeIn('slow');
			setTimeout(fade_out4, 500);
		}
		function fade_out4(){
			$("#correct").fadeOut();
		}		
		
		function positiondialogue(data){
			
			if(data != tmpdata){
				document.getElementById('position').innerHTML = '<h2>Your are now on position: <strong>'+data+'<strong></h2>';
				$("#position").fadeIn();
				setTimeout(fade_out2, 2500);
			}
			tmpdata = data;
			
		}
    	function fade_out2(){
			$("#position").fadeOut();
		}
    	
			
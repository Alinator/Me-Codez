/**
 * @author Ali
 */
var experiencepoints=0;
var contestant="";
var correctanswered=0;
var questionsanswered = new Array();
var numberofquestions=0;
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
				correctanswer("Correct");
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
			
			// increaseLevel();
			// showexperienceandposition();
			}
			if(correctanswered == 3){
					badgebox("brons");
					setTimeout(fade_out2, 4000);
				}else if(correctanswered == 4){
				 	document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: BEGINNER</h2> </center><br>";
					$('#achievement').fadeIn('slow');	
					setTimeout(fade_out, 4000);
				}else if(correctanswered == 6){
					badgebox("silver");
					setTimeout(fade_out2, 4000);
				}else if(correctanswered == 7){
				 	document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: Intermediate</h2> </center><br>";
					$('#achievement').fadeIn('slow');	
					setTimeout(fade_out, 4000);
				}else if(correctanswered == 14){
					badgebox("gold");
					setTimeout(fade_out2, 4000);
				}else if(correctanswered == 15){
				 	document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: Advanced</h2> </center><br>";
					$('#achievement').fadeIn('slow');
					setTimeout(fade_out, 4000);
				}else if(correctanswered == 20){
					badgebox("platina");
					document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>General Knowledge: MASTER</h2> </center><br>";
					$('#achievement').fadeIn('slow');
					setTimeout(fade_out, 3500);
					setTimeout(fade_out2, 4000);
									
				}
				
				if(numberofquestions == 3 && correctanswered == 3){
					document.getElementById('achievement').innerHTML  = "<center><br>Achievement:<br> <h2>HAT TRICK</h2> </center><br>";
					$('#achievement').fadeIn('slow');
					 setTimeout(fade_out, 3000);
				 }	
				
				
			//}else{
				
				 // showexperienceandposition();
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
					 // Box(100);
				// }
				
				 			
// 				
			// }
			
		});
}

		var counter = 0;
		function increaseLevel(){
			counter++;
			document.getElementById('playerstatslevel').innerHTML = 'Your Level is: <strong>'+counter+'<strong>';
			document.getElementById('level').innerHTML  = "<center><br>CONGRATULATIONS!<br> <h2>YOU HAVE REACHED LEVEL "+counter+"!</h2> </center><br>";
			$('#achievement').fadeIn('slow');
			setTimeout(fade_out, 3000);
		}
		function correctanswer(answer){
			document.getElementById('correct').innerHTML  = "<center><h2>"+answer+" Answer!</h2> </center><br>";
			$('#correct').fadeIn('slow');
			setTimeout(fade_out5, 500);
		}
		function wronganswer(answer){
			document.getElementById('wrong').innerHTML  = "<center><h2>"+answer+" Answer!</h2> </center><br>";
			$('#wrong').fadeIn('slow');
			setTimeout(fade_out4, 500);
		}
		function fade_out4(){
			$("#wrong").fadeOut();
		}
		function fade_out(){
			$("#achievement").fadeOut();
		}
		function showexperienceandposition(){
			document.getElementById('playerxp').innerHTML = '<strong>'+experiencepoints+'<strong>';
			
			// $.post("SaveScore.php", {a: experiencepoints}, function(data){                                          
				// });
// 			
			// $.post("CountPosition.php", {}, function(data){  	
				// $('#playerposition').html(data);
			// });
		}	
		function fade_out2(){
			$("#popup-box-achievement").fadeOut();
		}
		function fade_out3(){
			$("#popup-box-points").fadeOut();
		}
		function fade_out5(){
			$("#correct").fadeOut();
		}	